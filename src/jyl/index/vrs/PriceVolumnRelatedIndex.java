package jyl.index.vrs;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

import jyl.datacollect.sina.dailytrade.datafetcher.bean.DailyTradeInfoBeanImp;
import jyl.datacollect.sina.dailytrade.datafetcher.dao.DailyTradeInfoDaoImp;
import jyl.index.vrs.bo.IndexCounterAbstract;
import jyl.index.vrs.mondel.VrsModel;
import jyl.index.vrs.pricevolumn.bean.PriceVolumnIndexBeanImp;
import jyl.index.vrs.pricevolumn.dao.PriceVolumnIndexDaoImp;
import jyl.util.DatabaseHelper;
import jyl.util.Log4jLoader;

import org.apache.log4j.Logger;

/**
 * 类描述：   
 * 创建人：jinyongliang
 * 创建时间：Apr 19, 2013 5:29:26 PM   
 * 修改人：jinyongliang   
 * 修改时间：Apr 19, 2013 5:29:26 PM   
 * 修改备注：   
 * @version 
 */
public class PriceVolumnRelatedIndex extends IndexCounterAbstract {
	
	DailyTradeInfoDaoImp dailyTradeDao = new DailyTradeInfoDaoImp();
	PriceVolumnIndexDaoImp vrsDao = new PriceVolumnIndexDaoImp();
	static Logger logger = Logger.getLogger(PriceVolumnRelatedIndex.class);


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Log4jLoader.loaddefault();
		PriceVolumnRelatedIndex pvd = new PriceVolumnRelatedIndex();
		logger.info("begin");
		pvd.countFullPeriodData();
		/*Double amount = 11234.45d;
		Long volumn = 7778l;
		Double price = amount / volumn;
		System.out.println("price:"+price);*/
	}


	/* (non-Javadoc)
	 * @see jyl.index.vrs.bo.IndexCounterAbstract#countOneStock(java.lang.String)
	 */
	@Override
	public void countOneStock(String string) {
		// TODO 实现不同
		VrsModel vm = new VrsModel();
		//String querySql = baseQuerySql+string+"' and amount >0 ORDER BY trade_date DESC  LIMIT 25)t ORDER BY trade_date ";
		String querySql = baseQuerySql+string+"' and amount >0 ORDER BY trade_date  "; 
		String user = "root";
			String password = "manager";
			 String url = "jdbc:mysql://localhost:3306/sse?useUnicode=true&amp;characterEncoding=UTF-8";
		try {
			if(con==null||con.isClosed())			
				con = DatabaseHelper.getAdConnectionByParams(url,user,password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		long t1 = System.currentTimeMillis();
		ArrayList<DailyTradeInfoBeanImp> arrs = dailyTradeDao.getBeans( querySql,con);
		long t2 = System.currentTimeMillis();
		System.out.println("查询消耗时间:"+(t2-t1));
		if(arrs!=null)
		System.out.println("arrsLength:"+arrs.size());
		else return;
		ArrayList<PriceVolumnIndexBeanImp> resultArr;
		try {
			resultArr = countDatasrVrs(arrs);
			long t3 = System.currentTimeMillis();
			System.out.println("计算消耗时间:"+(t3-t2));
			if(con==null||con.isClosed())			
				con = DatabaseHelper.getAdConnectionByParams(url,user,password);
			vrsDao.saveBeansDivided(resultArr,con);
			long t4 = System.currentTimeMillis();
			System.out.println("保存数据消耗时间:"+(t4-t3));
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * @param arrs
	 * @return
	 * @throws NoSuchMethodException 
	 * @throws SecurityException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 */
	private ArrayList<PriceVolumnIndexBeanImp> countDatasrVrs(
			ArrayList<DailyTradeInfoBeanImp> arrs) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		ArrayList<PriceVolumnIndexBeanImp> resultArr = new ArrayList<PriceVolumnIndexBeanImp>();
		HashMap<Timestamp,PriceVolumnIndexBeanImp> map = new HashMap<Timestamp,PriceVolumnIndexBeanImp>(); //用于计算后续均价变化
		 int[] countDaysArr = {1,3,5,10,20,60,120};
		HashMap<Integer,Double> avg_2close_ratio_bef = new HashMap<Integer,Double> ();//均价与市价（前一天收盘价与今日收盘价均值）偏差累计（比率累计）
		HashMap<Integer,Double> avg_cost_bef_today_ratio = new HashMap<Integer,Double> ();//当前均价与 前N天均价变动率
		HashMap<Integer,Long> avg_cost_bef_today_volumn = new HashMap<Integer,Long> ();//前N天volumn合计
		HashMap<Integer,Double> avg_cost_bef_today_amount = new HashMap<Integer,Double> ();//前N天amount合计

		
		HashMap<Integer,Double> avg_cost_after_today_ratio = new HashMap<Integer,Double> ();//当前均价与 后N天均价变动率
		HashMap<Integer,Long> avg_cost_after_volumn = new HashMap<Integer,Long> ();//后N天volumn合计
		HashMap<Integer,Double> avg_cost_after_amount = new HashMap<Integer,Double> ();//后N天amount合计
		
		// TODO now_jyl,计算指标
		//avg_2close_ratio_bef_1     均价与市价（前一天收盘价与今日收盘价均值）偏差累计（比率累计）
		//HashMap<String,ArrayList<VrsIndexDataBeanImp>> resultMap =new HashMap<String,ArrayList<VrsIndexDataBeanImp>>();
		// ArrayList<VrsIndexDataBeanImp> result = new  ArrayList<VrsIndexDataBeanImp>();
		// 从数组1位置开始
		//增加一个指标，当日数据变化
		 double exp = 10E-6;
		for(int i=1;i<arrs.size();i++)
		{
			PriceVolumnIndexBeanImp bean = new PriceVolumnIndexBeanImp();
			DailyTradeInfoBeanImp dailyBean = arrs.get(i);
			bean.setStockCode(dailyBean.getStockCode());
			bean.setTradeDate(dailyBean.getTradeDate());
			DailyTradeInfoBeanImp yesBean = arrs.get(i-1);
			Float colsedToday = dailyBean.getCloseToday();
			Float colsedYes = yesBean.getCloseToday();
			Float beginToday = yesBean.getBeginToday();
			Double avgToday = dailyBean.getAmount()/dailyBean.getVolumn();
			Double avgYes = yesBean.getAmount()/yesBean.getVolumn();
			Long volumnToday = dailyBean.getVolumn();
			Double amountToday = dailyBean.getAmount();
			Double avg_2close_ratio_bef_todady = null;//今天均价与市价（前一天收盘价与今日收盘价均值）偏差累计（比率累计）
			 
			 //TODO   now_jyl
			 
			if((avgToday*2-colsedToday-colsedYes>exp)||(colsedToday+colsedYes-avgToday*2>exp))
				avg_2close_ratio_bef_todady = (double) ((avgToday*2-colsedToday-colsedYes)*100/(colsedToday+colsedYes));
			else
				avg_2close_ratio_bef_todady = 0d;
			
		
			
			for(int j=0;j<countDaysArr.length;j++)
			{
				if(j==0)
					continue;//当天数据不需要另外的处理
				int countDay = countDaysArr[j];
				Double avg_2close_ratio_bef_cmid = avg_2close_ratio_bef.get(j)==null?0:avg_2close_ratio_bef.get(j);
				//增加部分
				avg_2close_ratio_bef_cmid = avg_2close_ratio_bef_cmid+avg_2close_ratio_bef_todady;
				
				//均价成本与前N天均价成本比率计算使用
				Long avg_cost_bef_volumn_mid = avg_cost_bef_today_volumn.get(j)==null?0:avg_cost_bef_today_volumn.get(j);
				Double avg_cost_bef_amount_mid = avg_cost_bef_today_amount.get(j)==null?0:avg_cost_bef_today_amount.get(j);
				Double avg_cost_bef_N_today_ratio =null;
				avg_cost_bef_volumn_mid = avg_cost_bef_volumn_mid+volumnToday;
				avg_cost_bef_amount_mid = avg_cost_bef_amount_mid+amountToday;
				
				//减少部分
				if(i>countDay)
				{
					
					DailyTradeInfoBeanImp periodFistDay = arrs.get(i-countDay);
					DailyTradeInfoBeanImp periodFistDayBefore = arrs.get(i-countDay-1);
					Float colsedPeriodFirstDay = periodFistDay.getCloseToday();
					Float colsedPeriodFirstDayBefore = periodFistDayBefore.getCloseToday();
					Double avgColsedPeriodFirstDay = periodFistDay.getAmount()/periodFistDay.getVolumn();
					Double avgColsedPeriodFirstDayBefore = periodFistDayBefore.getAmount()/periodFistDayBefore.getVolumn();
					Double avg_2close_ratio_bef_2del = null;//用于删除时的数据
					Long volumnToday_2del = periodFistDay.getVolumn();
					Double amountToday_2del = periodFistDay.getAmount();
					//计算 countDay 前的数据 
					if((avgColsedPeriodFirstDay*2-colsedPeriodFirstDay-colsedPeriodFirstDayBefore>exp)||(colsedPeriodFirstDay+colsedPeriodFirstDayBefore-avgColsedPeriodFirstDay*2>exp))
						avg_2close_ratio_bef_2del = (double) ((avgColsedPeriodFirstDay*2-colsedPeriodFirstDay-colsedPeriodFirstDayBefore)*100/(colsedPeriodFirstDay+colsedPeriodFirstDayBefore));
					else
						avg_2close_ratio_bef_2del = 0d;
					avg_2close_ratio_bef_cmid = avg_2close_ratio_bef_cmid-avg_2close_ratio_bef_2del;
					
					//均价成本与前N天均价成本比率计算使用
					avg_cost_bef_volumn_mid = avg_cost_bef_volumn_mid-volumnToday_2del;
					avg_cost_bef_amount_mid = avg_cost_bef_amount_mid-amountToday_2del;
					Double avg_before_N = avg_cost_bef_amount_mid/avg_cost_bef_volumn_mid;
					//avg_cost_bef_today_ratio
					if(avgYes-avg_before_N>exp||avg_before_N-avgYes>exp)
					{
						avg_cost_bef_N_today_ratio = (avgYes - avg_before_N )/avg_before_N*100;
						
					}else
						avg_cost_bef_N_today_ratio =0d;
					
					
					//TODO jyl_关键处理， 后续N天均价变化率计算,不同的 j对应不同的bean
					Double avg_N_before = amountToday_2del/volumnToday_2del;
					Double avg_ratio_after = null;//后N天均价变化率
					if(avg_N_before-avg_before_N>exp||avg_before_N-avg_N_before>exp)
					{
						avg_ratio_after = (avg_before_N - avg_N_before )/avg_N_before*100;//后N天均价变化率
						
					}else
						avg_ratio_after =0d;
					
					Timestamp tsm = periodFistDay.getTradeDate();
					PriceVolumnIndexBeanImp beanBeforeN = map.get(tsm);
					if(beanBeforeN!=null)
					{
				//	beanBeforeN.setAvgCostAfter10TodayRatio(avg_ratio_after);// 反射设置值
					String methodName = "setAvgCostAfter"+countDay+"TodayRatio";
					Class clazz = beanBeforeN.getClass().getSuperclass();
					Method mt = clazz.getDeclaredMethod(methodName,Double.class); 
					 if(avg_ratio_after!=null)
					mt.invoke(beanBeforeN, avg_ratio_after);
					}
					
				}
					
				
				//TODO 中间值存储及指标值计算
				avg_2close_ratio_bef.put(j, avg_2close_ratio_bef_cmid);//指标值
				avg_cost_bef_today_volumn.put(j, avg_cost_bef_volumn_mid);//中间值
				avg_cost_bef_today_amount.put(j, avg_cost_bef_amount_mid);//中间值
				avg_cost_bef_today_ratio.put(j, avg_cost_bef_N_today_ratio); //指标值
				//setAvg2closeRatioBef1  
				//setAvgCostBef3TodayRatio
				String methodName = "setAvg2closeRatioBef"+countDay;
				Class clazz = bean.getClass().getSuperclass();
				Method [] methods = clazz.getDeclaredMethods();
			    for(Method m :methods)
			    {
			    	String mN = m.getName();
			    }
				Method mt = clazz.getDeclaredMethod(methodName,Double.class); //setAvgCostBef3TodayRatio
				 if(avg_2close_ratio_bef_cmid!=null)
				mt.invoke(bean, avg_2close_ratio_bef_cmid);
				
				methodName =  "setAvgCostBef"+countDay+"TodayRatio";
				 mt = clazz.getDeclaredMethod(methodName,Double.class); 
				 if(avg_cost_bef_N_today_ratio!=null)
				mt.invoke(bean, avg_cost_bef_N_today_ratio);
				
				
				
			}
			
			
			resultArr.add(bean);
			map.put(bean.getTradeDate(), bean);
			
			
			//PriceVolumnIndexBeanImp
		}
		//TODO  jyl_now bean设置各项指标值(大盘量缩)
				//TODO 创建入库数据(把map中的数据转化存储到array中)
		/*
		 * 大盘量缩需单独计算
		 * 1.计算MA值（30）
		 * 2.计算60范围内比值（最大值只需逐个比较即可得到）
		 *   2.1 找到最大值
		 * 3.需要考虑如果减少计算量
		 * 4.多种组合,(30,60),(50,100)等
		 */
		TreeMap<Integer,Long> simpleMoveAvgValueMap = new TreeMap<Integer,Long>();
		int divValue = 1000;
		TreeMap<Integer,ArrayList<VolumnSimpleAvgValue>> simpleMoveAvgValueDays = new TreeMap<Integer,ArrayList<VolumnSimpleAvgValue>>();
		for(int i=1;i<arrs.size();i++)
		{
			arrs.get(i);
			long currentvolumn = arrs.get(i).getVolumn();
			if(i==1)
			{
			if(currentvolumn<divValue)
				divValue = 1;
			}
			currentvolumn = currentvolumn/divValue;
			for(volumnSizeChangeIndex oneIndex : volumnSizeChangeIndex.values())
			{
				//增加值
				int maDays = oneIndex.getMaDays();
				Long value = simpleMoveAvgValueMap.get(maDays);
				if(value ==null)
					simpleMoveAvgValueMap.put(maDays, currentvolumn);
				else
				{
					simpleMoveAvgValueMap.put(maDays, value+currentvolumn);
				}
				
				//减少值
				if(i>maDays&&value!=null)
				{
					value = simpleMoveAvgValueMap.get(maDays);
					DailyTradeInfoBeanImp periodFistDay = arrs.get(i-maDays);
					simpleMoveAvgValueMap.put(maDays, value-(periodFistDay.getVolumn()/divValue));

				}
				 
				//存入到 simpleMoveAvgValueDays 中
				ArrayList<VolumnSimpleAvgValue> oneIntervalArrValue = simpleMoveAvgValueDays.get(maDays);
				if(oneIntervalArrValue == null)
				{
					oneIntervalArrValue = new ArrayList<VolumnSimpleAvgValue>();
					simpleMoveAvgValueDays.put(maDays, oneIntervalArrValue);
				}
			     if(i>maDays)
				{
			    //设置值
				VolumnSimpleAvgValue  volumnSimpleAvgValue = new VolumnSimpleAvgValue();
				volumnSimpleAvgValue.setTradeDate( arrs.get(i).getTradeDate());
				volumnSimpleAvgValue.setVolumn(simpleMoveAvgValueMap.get(maDays));
				oneIntervalArrValue.add(volumnSimpleAvgValue);
				}
			}
		}
		
		//计算最终值
		
		for(volumnSizeChangeIndex oneIndex : volumnSizeChangeIndex.values())
		{
			//程序简化处理，后续可进行优化
			int mDays = oneIndex.getMaDays();
			int compareDays = oneIndex.getCompareDays();
			ArrayList<VolumnSimpleAvgValue> oneIntervalArrValue = simpleMoveAvgValueDays.get(mDays);
			int intervalMaxDataPos = 0;
			long maxDataValue =0;
			for(int i=0;i<oneIntervalArrValue.size();i++)
			{
				long volumnValue = oneIntervalArrValue.get(i).getVolumn();
				if(volumnValue>maxDataValue)
				{
					maxDataValue = volumnValue;
					intervalMaxDataPos = i;
				}
				else
				{
					//判断是否要重新查找最大值
					if(intervalMaxDataPos<(i-compareDays))//当前最大值所在位置在筛选区间范围之外
					{
						maxDataValue =0;
						for(int j = intervalMaxDataPos+1;j<i;j++)
						{
							long volumnValueJ = oneIntervalArrValue.get(j).getVolumn();
							if(volumnValueJ>maxDataValue)
							{
								maxDataValue = volumnValueJ;
								intervalMaxDataPos = j;
							}
						}
					}
				}
				//volumeReduceChange_30_60
				String methodName = "setVolumeReduceChange"+mDays+""+compareDays;
			// volumnValue数值溢出
				if(i>compareDays&&volumnValue> exp)
				{
					Double volumeReduceChange = maxDataValue*1.0d/volumnValue;
					Timestamp tmp =  oneIntervalArrValue.get(i).getTradeDate();
					PriceVolumnIndexBeanImp beanBeforeN = map.get(tmp);
					//TODO 需要使用反射来设置值(大盘量缩)
					if(volumeReduceChange!=null){
					Class clazz = beanBeforeN.getClass().getSuperclass();
					Method mt = clazz.getDeclaredMethod(methodName,Double.class); 
					mt.invoke(beanBeforeN, volumeReduceChange);
					}
				}
				
			}
		}
		
		
		return resultArr;
	}



	/* (non-Javadoc)
	 * @see jyl.index.vrs.bo.IndexCounterAbstract#initParams()
	 */
	@Override
	public void initParams() {
		// TODO 初始化dao参数
		dailyTradeDao = new DailyTradeInfoDaoImp();
		vrsDao = new PriceVolumnIndexDaoImp();
	}
	
	

}
enum volumnSizeChangeIndex
{
	index30_60(30,60),index50_100(50,100),index100_300(100,300);
	public int getMaDays() {
		return maDays;
	}
	public int getCompareDays() {
		return compareDays;
	}
	public void setMaDays(int maDays) {
		this.maDays = maDays;
	}
	public void setCompareDays(int compareDays) {
		this.compareDays = compareDays;
	}
	volumnSizeChangeIndex(Integer maDays,Integer compareDays){
		this.maDays = maDays;
		this.compareDays = compareDays;
	};
	int maDays;
	int compareDays;
}

class VolumnSimpleAvgValue{
	private long volumn;
	private Timestamp tradeDate;
	public long getVolumn() {
		return volumn;
	}
	public Timestamp getTradeDate() {
		return tradeDate;
	}
	public void setVolumn(long volumn) {
		this.volumn = volumn;
	}
	public void setTradeDate(Timestamp tradeDate) {
		this.tradeDate = tradeDate;
	}
	
}
