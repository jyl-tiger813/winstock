package jyl.index.vrs.mondel;

import java.util.ArrayList;
import java.util.HashMap;

import jyl.datacollect.sina.dailytrade.datafetcher.bean.DailyTradeInfoBeanImp;
import jyl.index.vrs.bean.VrsIndexDataBeanImp;

/**
 * 类描述： vr(1),fvr(2),jvr(3),fjvr(4),成交均价与开收盘平均价差值
 * 创建人：jinyongliang
 * 创建时间：Mar 21, 2013 1:58:02 PM   
 * 修改人：jinyongliang   
 * 修改时间：Mar 21, 2013 1:58:02 PM   
 * 修改备注：   
 * @version 
 */
public class VrsModel{
	
	static int[] countDaysArr = {5,10,21};
	HashMap<Integer,Long> middleValuesIncrease = new HashMap<Integer,Long> ();//用于存储中间有效数据
	HashMap<Integer,Long> middledecreaseValues = new HashMap<Integer,Long> ();//用于存储中间有效数据
	HashMap<Integer,Long> equalsValuesMap = new HashMap<Integer,Long> ();//用于存储中间有效数据
	HashMap<Integer,Long> avgMiddleValuesIncrease = new HashMap<Integer,Long> ();//用于存储中间有效数据
	HashMap<Integer,Long> avgValuesDecrease = new HashMap<Integer,Long> ();//用于存储中间有效数据
	HashMap<Integer,Long> avgequalValuesMap= new HashMap<Integer,Long> ();//用于存储中间有效数据

	int beforeDays = 0;//前面有效数据天数，决定当前计算值是否有效入库
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**
	 * @上升成交量与下降成交量比值,进一
	 * @return
	 */
	public   HashMap<String,ArrayList<VrsIndexDataBeanImp>> countDatasVrs(
			ArrayList<DailyTradeInfoBeanImp> arrs) {
		HashMap<String,ArrayList<VrsIndexDataBeanImp>> resultMap =new HashMap<String,ArrayList<VrsIndexDataBeanImp>>();
		// ArrayList<VrsIndexDataBeanImp> result = new  ArrayList<VrsIndexDataBeanImp>();
		// 从数组1位置开始
		//增加一个指标，当日数据变化
		 double exp = 10E-6;
		for(int i=1;i<arrs.size();i++)
		{
			DailyTradeInfoBeanImp dailyBean = arrs.get(i);
			DailyTradeInfoBeanImp yesBean = arrs.get(i-1);
			Float colsedToday = dailyBean.getCloseToday();
			Float colsedYes = yesBean.getCloseToday();
			Float beginToday = yesBean.getBeginToday();
			Double avgToday = dailyBean.getAmount()/dailyBean.getVolumn();
			Double avgYes = yesBean.getAmount()/yesBean.getVolumn();
			Double changeRatio = null;
			 Double ave_change_ratio  = null;//今天昨天均价变化率
			 Double change_ratio_close_begin  = null;//今天收盘 开盘
			 Double change_ratio_avg_close  = null;//今天均价收盘
			if(colsedToday-colsedYes>exp||colsedYes-colsedToday>exp)
				changeRatio = (double) ((colsedToday-colsedYes)*100/colsedYes);
			else
				changeRatio = 0d;
			
			//均价处理
			
			if(avgToday-avgYes>exp||avgYes-avgToday>exp)
				ave_change_ratio = (double) ((avgToday-avgYes)*100/avgYes);
			else
				ave_change_ratio = 0d;
			
			//开收盘价格变动率
			if(colsedToday-beginToday>exp||beginToday-colsedToday>exp)
				change_ratio_close_begin = (double) ((colsedToday-beginToday)*100/avgYes);
			else
				change_ratio_close_begin = 0d;
			
			//均价收盘变动率
			
			if(avgToday-colsedToday>exp||colsedToday-avgToday>exp)
				change_ratio_avg_close = (double) ((avgToday-colsedToday)*100/avgYes);
			else
				change_ratio_avg_close = 0d;
			
			for(int j=0;j<countDaysArr.length;j++)
			{
				int countDay = countDaysArr[j];
				Long middleValues = middleValuesIncrease.get(j)==null?0:middleValuesIncrease.get(j);
				Long decreaseValues = middledecreaseValues.get(j)==null?0:middledecreaseValues.get(j);
				Long equalValues = equalsValuesMap.get(j)==null?0:equalsValuesMap.get(j);
				Long avgMiddleValues= avgMiddleValuesIncrease.get(j)==null?0:avgMiddleValuesIncrease.get(j);
				Long avgDecreaseValues = avgValuesDecrease.get(j)==null?0:avgValuesDecrease.get(j);
				Long avgequalValues = avgequalValuesMap.get(j)==null?0:avgequalValuesMap.get(j);
				
				
				//增加部分
				//当日上涨
				//vr,fvr计算
				if(colsedToday-colsedYes>exp)
				{
					middleValues = middleValues + dailyBean.getVolumn();
					
				}//当日下跌
				else if(colsedYes-colsedToday>exp)
				{
					decreaseValues = decreaseValues + dailyBean.getVolumn();
					
				}
				//
				else//当天持平
				{
					equalValues = equalValues + dailyBean.getVolumn();
				}
				
				
				//jvr,fjvr计算
				
				if(avgToday-avgYes>exp)
				{
					avgMiddleValues = avgMiddleValues + dailyBean.getVolumn();
					
				}//当日下跌
				else if(avgYes-avgToday>exp)
				{
					avgDecreaseValues = avgDecreaseValues + dailyBean.getVolumn();
					
				}
				//
				else//当天持平
				{
					avgequalValues = avgequalValues + dailyBean.getVolumn();
				}
				
				
				//减少部分
				if(i>countDay)
				{
					
					DailyTradeInfoBeanImp periodFistDay = arrs.get(i-countDay);
					DailyTradeInfoBeanImp periodFistDayBefore = arrs.get(i-countDay-1);
					Float colsedPeriodFirstDay = periodFistDay.getCloseToday();
					Float colsedPeriodFirstDayBefore = periodFistDayBefore.getCloseToday();
					Double avgColsedPeriodFirstDay = periodFistDay.getAmount()/periodFistDay.getVolumn();
					Double avgColsedPeriodFirstDayBefore = periodFistDayBefore.getAmount()/periodFistDayBefore.getVolumn();
					
					//vr、fvr
					if(colsedPeriodFirstDay-colsedPeriodFirstDayBefore>exp)
					{
						middleValues = middleValues - arrs.get(i-countDay).getVolumn();
					}
					else if(colsedPeriodFirstDayBefore - colsedPeriodFirstDay>exp)
					{
						decreaseValues = decreaseValues - arrs.get(i-countDay).getVolumn();

					}else 
					{
						equalValues = equalValues -  arrs.get(i-countDay).getVolumn();

					}
					
					
					//jvr、fjvr
					if(avgColsedPeriodFirstDay-avgColsedPeriodFirstDayBefore>exp)
					{
						avgMiddleValues = avgMiddleValues - arrs.get(i-countDay).getVolumn();
					}
					else if(avgColsedPeriodFirstDayBefore - avgColsedPeriodFirstDay>exp)
					{
						avgDecreaseValues = avgDecreaseValues - arrs.get(i-countDay).getVolumn();

					}else 
					{
						avgequalValues = avgequalValues -  arrs.get(i-countDay).getVolumn();

					}
					
					//决定入库数据
					
					if((decreaseValues+equalValues)>exp)
					{
						Double vrDay = 100.00*(2*middleValues+equalValues)/(2*decreaseValues+equalValues);
						VrsIndexDataBeanImp vrs = new VrsIndexDataBeanImp();
						vrs.setIndexId(1);
						vrs.setCountDays(countDay);
						vrs.setIndexValue(vrDay);
						vrs.setStockCode(dailyBean.getStockCode());
						vrs.setStockCodeInt(Integer.parseInt(dailyBean.getStockCode()));
						vrs.setTradeDate(dailyBean.getTradeDate());
						vrs.setChangeRatio(changeRatio);
						vrs.setAve_change_ratio(ave_change_ratio);
						vrs.setChange_ratio_close_begin(change_ratio_close_begin);
						vrs.setChange_ratio_avg_close(change_ratio_avg_close);
						addBean2Map(vrs,resultMap);
					}
					else
					{
						VrsIndexDataBeanImp vrs = new VrsIndexDataBeanImp();
						vrs.setIndexId(1);
						vrs.setCountDays(countDay);
						vrs.setIndexValue(100000d);
						vrs.setStockCode(dailyBean.getStockCode());
						vrs.setStockCodeInt(Integer.parseInt(dailyBean.getStockCode()));
						vrs.setTradeDate(dailyBean.getTradeDate());
						vrs.setChangeRatio(changeRatio);
						vrs.setAve_change_ratio(ave_change_ratio);
						vrs.setChange_ratio_close_begin(change_ratio_close_begin);
						vrs.setChange_ratio_avg_close(change_ratio_avg_close);
						addBean2Map(vrs,resultMap);
					}
					if((middleValues+equalValues)>exp)
					{
						Double fvrDay = 100.00*(2*decreaseValues+equalValues)/(2*middleValues+equalValues);
						VrsIndexDataBeanImp vrs = new VrsIndexDataBeanImp();
						vrs.setIndexId(2);
						vrs.setCountDays(countDay);
						vrs.setIndexValue(fvrDay);
						vrs.setStockCode(dailyBean.getStockCode());
						vrs.setTradeDate(dailyBean.getTradeDate());
						vrs.setChangeRatio(changeRatio);
						vrs.setAve_change_ratio(ave_change_ratio);
						vrs.setChange_ratio_close_begin(change_ratio_close_begin);
						vrs.setChange_ratio_avg_close(change_ratio_avg_close);
						addBean2Map(vrs,resultMap);
					}else
					{
					VrsIndexDataBeanImp vrs = new VrsIndexDataBeanImp();
					vrs.setIndexId(2);
					vrs.setCountDays(countDay);
					vrs.setIndexValue(10000d);
					vrs.setStockCode(dailyBean.getStockCode());
					vrs.setTradeDate(dailyBean.getTradeDate());
					vrs.setChangeRatio(changeRatio);
					vrs.setAve_change_ratio(ave_change_ratio);
					vrs.setChange_ratio_close_begin(change_ratio_close_begin);
					vrs.setChange_ratio_avg_close(change_ratio_avg_close);
					addBean2Map(vrs,resultMap);}
					
					
					//均值数据
					if((avgDecreaseValues+avgequalValues)>exp)
					{
						Double vrDay = 100.00*(2*avgMiddleValues+avgequalValues)/(2*avgDecreaseValues+avgequalValues);
						VrsIndexDataBeanImp vrs = new VrsIndexDataBeanImp();
						vrs.setIndexId(3);
						vrs.setCountDays(countDay);
						vrs.setIndexValue(vrDay);
						vrs.setStockCode(dailyBean.getStockCode());
						vrs.setTradeDate(dailyBean.getTradeDate());
						vrs.setChangeRatio(changeRatio);
						vrs.setAve_change_ratio(ave_change_ratio);
						vrs.setChange_ratio_close_begin(change_ratio_close_begin);
						vrs.setChange_ratio_avg_close(change_ratio_avg_close);
						addBean2Map(vrs,resultMap);
					}else
					{
						VrsIndexDataBeanImp vrs = new VrsIndexDataBeanImp();
						vrs.setIndexId(3);
						vrs.setCountDays(countDay);
						vrs.setIndexValue(10000d);
						vrs.setStockCode(dailyBean.getStockCode());
						vrs.setTradeDate(dailyBean.getTradeDate());
						vrs.setChangeRatio(changeRatio);
						vrs.setAve_change_ratio(ave_change_ratio);
						vrs.setChange_ratio_close_begin(change_ratio_close_begin);
						vrs.setChange_ratio_avg_close(change_ratio_avg_close);
						addBean2Map(vrs,resultMap);
					}
					
					
					if((avgMiddleValues+avgequalValues)>exp)
					{
						Double vrDay = 100.00*(2*avgDecreaseValues+avgequalValues)/(2*avgMiddleValues+avgequalValues);
						VrsIndexDataBeanImp vrs = new VrsIndexDataBeanImp();
						vrs.setIndexId(4);
						vrs.setCountDays(countDay);
						vrs.setIndexValue(vrDay);
						vrs.setStockCode(dailyBean.getStockCode());
						vrs.setTradeDate(dailyBean.getTradeDate());
						vrs.setChangeRatio(changeRatio);
						vrs.setAve_change_ratio(ave_change_ratio);
						vrs.setChange_ratio_close_begin(change_ratio_close_begin);
						vrs.setChange_ratio_avg_close(change_ratio_avg_close);
						addBean2Map(vrs,resultMap);
					}else
					{
						VrsIndexDataBeanImp vrs = new VrsIndexDataBeanImp();
						vrs.setIndexId(4);
						vrs.setCountDays(countDay);
						vrs.setIndexValue(10000d);
						vrs.setStockCode(dailyBean.getStockCode());
						vrs.setTradeDate(dailyBean.getTradeDate());
						vrs.setChangeRatio(changeRatio);
						vrs.setAve_change_ratio(ave_change_ratio);
						vrs.setChange_ratio_close_begin(change_ratio_close_begin);
						vrs.setChange_ratio_avg_close(change_ratio_avg_close);
						addBean2Map(vrs,resultMap);
					}
					}
				
				middleValuesIncrease.put(j, middleValues);
				middledecreaseValues.put(j, decreaseValues);
				equalsValuesMap.put(j, equalValues);
				avgMiddleValuesIncrease.put(j, avgMiddleValues);
				avgValuesDecrease.put(j, avgDecreaseValues);
				avgequalValuesMap.put(j, avgequalValues);
				//计算jvr和fjvr
			}
		}
		return resultMap;
	}

	/**
	 * @param vrs
	 * @param resultMap
	 */
	private void addBean2Map(VrsIndexDataBeanImp vrs,
			HashMap<String, ArrayList<VrsIndexDataBeanImp>> resultMap) {
		// TODO 修改当前需要进行计算的指标
		String keyStr = vrs.getIndexId()+"_"+vrs.getCountDays();
		//has done : 4_10
	/*	if(!"1_10".equals(keyStr))//||"1_10".equals(keyStr)||"3_10".equals(keyStr)))
			return;*/
		ArrayList<VrsIndexDataBeanImp> arrResult = resultMap.get(keyStr);
		if(arrResult==null)
		{
			arrResult = new ArrayList<VrsIndexDataBeanImp>();
			resultMap.put(keyStr, arrResult);
		}
		arrResult.add(vrs);
	}

}
