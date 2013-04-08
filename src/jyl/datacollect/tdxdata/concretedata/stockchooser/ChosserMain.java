package jyl.datacollect.tdxdata.concretedata.stockchooser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.Date;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Set;
import java.util.Map.Entry;
import jyl.datacollect.tdxdata.dao.TdxDataJdbcImp;
import jyl.util.MyCalendar;
import org.apache.log4j.Logger;

public class ChosserMain {

	/**
	 * @param args
	 * 存放数，最外层key为股票代码
	 * 第二层Key为交易日期
	 * 第三层Key为指标名称
	 */
	
	static Logger logger = Logger.getLogger(ChosserMain.class);
	String cofingDirection = "D:\\gfzq\\T0002\\blocknew";
	float upAndDown ;//选股条件的涨跌幅度
	private HashMap<String,HashMap<Calendar,HashMap<String,Float>>> allDatas
	= 	new HashMap<String,HashMap<Calendar,HashMap<String,Float>>> ();
	
	private HashMap<String,HashMap<Calendar,HashMap<String,Float>>> conformsDatas
	= 	new HashMap<String,HashMap<Calendar,HashMap<String,Float>>> ();

	private Date beginDate;

	private Date overDay;

	private boolean chooseAllDays =false;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ChosserMain cm = new ChosserMain();
		cm.chooseFromTodayDatas(0.09f,0.15f,-0.025f);
	//	cm.chooseFromAllDatas(0.09f,0.15f,-0.025f);
	}

	private void chooseFromTodayDatas(float netBigBuyPercentLevel,float totalBigBuyRatio,float upDown) {
		// TODO Auto-generated method stub
		overDay =new java.sql.Date( Calendar.getInstance().getTime().getTime());
		 beginDate	 =new java.sql.Date( MyCalendar.backDayNotWeekEnd(Calendar.getInstance()).getTime().getTime());
		
		/*Calendar cal = Calendar.getInstance();
		cal=MyCalendar.backDayNotWeekEnd(cal);
		overDay =new java.sql.Date( cal.getTime().getTime());
		beginDate	 =new java.sql.Date( MyCalendar.backDayNotWeekEnd(Calendar.getInstance()).getTime().getTime());
		*/
		setUpAndDown(upDown);
		initDatas(netBigBuyPercentLevel,totalBigBuyRatio);
		createUpDownDatas(netBigBuyPercentLevel,totalBigBuyRatio);
		getConformedDatas();
		generateTdxBlockFile();
		showDatas();
	}

	/*
	 * 创建选中的股票板块
	 */
	private void generateTdxBlockFile() {
		// TODO Auto-generated method stub
		File directory = new File(cofingDirection);
		String fileName = MyCalendar.getStringEight(Calendar.getInstance()).substring(4)+".blk";
		File target = new File(cofingDirection,fileName);
		FileOutputStream fis=null;
		OutputStreamWriter fr=null;
		try {
			fis = new FileOutputStream(target);
			fr = new OutputStreamWriter(fis);
		//	fr.write("\n");
			Set<Entry<String, HashMap<Calendar, HashMap<String, Float>>>> 
		       set1 = conformsDatas.entrySet();
			for(Entry<String, HashMap<Calendar, HashMap<String, Float>>> entry1 : set1)
			{
				HashMap<Calendar, HashMap<String, Float>> map1 = entry1.getValue();
				String stockCode = entry1.getKey();
				fr.write("\n0"+stockCode);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally
		{
			try {
				fr.close();
				fis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	private void chooseFromAllDatas(float netBigBuyPercentLevel,float totalBigBuyRatio,float upDown) {
		// TODO Auto-generated method stub
		chooseAllDays  = true;
		setUpAndDown(upDown);
		initDatas(netBigBuyPercentLevel,totalBigBuyRatio);
		createUpDownDatas(netBigBuyPercentLevel,totalBigBuyRatio);
		getConformedDatas();
		showDatas();
	}

	private void showDatas() {
		// TODO Auto-generated method stub
		Set<Entry<String, HashMap<Calendar, HashMap<String, Float>>>> 
	       set1 = conformsDatas.entrySet();
		logger.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"+"showDatas"+"~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		
		for(Entry<String, HashMap<Calendar, HashMap<String, Float>>> entry1 : set1)
		{
			HashMap<Calendar, HashMap<String, Float>> map1 = entry1.getValue();
			Set<Entry<Calendar, HashMap<String, Float>>> 
		       set2 = map1.entrySet();
			String stockCode = entry1.getKey();
			logger.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~stockcode : "+stockCode+"~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			for(Entry<Calendar, HashMap<String, Float>> entry2 : set2)
			{
				Calendar cal = entry2.getKey();
				/*
				 * 不考虑非周末的法定节假日的情况,暂时使用
				 */
				
				HashMap<String, Float> datas = entry2.getValue();
				Float upAndDownThisDay = datas.get("upAndDownThisDay");
				Float upAndDownNextDay = datas.get("upAndDownNextDay");
				logger.info("date: "+MyCalendar.getString(cal)+" upAndDownThisDay : "
						+upAndDownThisDay+" upAndDownNextDay :"+upAndDownNextDay+"\n");
			}
		}
	}

	/*
	 * 停牌复牌后的数据予以忽略
	 * 符合条件的数据存到conformsDatas中
	 */
	private void createUpDownDatas(float netBigBuyPercentLevel, float totalBigBuyRatio2) {
		// TODO Auto-generated method stub
		Set<Entry<String, HashMap<Calendar, HashMap<String, Float>>>> 
       set1 = allDatas.entrySet();
		
		for(Entry<String, HashMap<Calendar, HashMap<String, Float>>> entry1 : set1)
		{
			HashMap<Calendar, HashMap<String, Float>> map1 = entry1.getValue();
			Set<Entry<Calendar, HashMap<String, Float>>> 
		       set2 = map1.entrySet();
			for(Entry<Calendar, HashMap<String, Float>> entry2 : set2)
			{
				Calendar cal = entry2.getKey();
				/*
				 * 不考虑非周末的法定节假日的情况,暂时使用
				 */
				Calendar beforeDay = MyCalendar.backDayNotWeekEnd (cal);
				Calendar afterDay = MyCalendar.nextDayNotWeekEnd(cal);
				HashMap<String, Float> datas = entry2.getValue();
				Float close1 = datas.get("close");
				Float netBigBuyPercent = datas.get("netBigBuyPercent");
				Float totalBigBuyRatio = datas.get("totalBigBuyRatio");
				HashMap<String, Float> datasBefore = map1.get(beforeDay);
				HashMap<String, Float> datasAfter = map1.get(afterDay);
				Float closeBefore = null;
				Float closeAfter = null;
				if(datasBefore!=null)
				  closeBefore = datasBefore.get("close");
				if(datasAfter!=null)
				  closeAfter = datasAfter.get("close");
				if(close1!=null&&close1>0&&closeBefore!=null&&closeBefore>0&&netBigBuyPercent>
				netBigBuyPercentLevel&&totalBigBuyRatio>totalBigBuyRatio2)
				{
					Float upAndDown = (close1 - closeBefore)/closeBefore;
					if(upAndDown<this.upAndDown)//如果符合条件
					{
						datas.put("upAndDownThisDay", upAndDown);
						 HashMap<Calendar,HashMap<String,Float>> mapTemp = conformsDatas.get(entry1.getKey());
							if(mapTemp ==null){
								mapTemp = new HashMap<Calendar,HashMap<String,Float>>();
								conformsDatas.put(entry1.getKey(), mapTemp);
							}
							mapTemp.put(cal, datas);
						if(closeAfter!=null&&closeAfter>0)
						{
							float upAndDownNextDay = (closeAfter - close1)/close1;
							datas.put("upAndDownNextDay", upAndDownNextDay);
							
						}
							
					}
				}
			} 
			
		}
	}

	private void getConformedDatas() {
		// TODO Auto-generated method stub
		
	}

	private void initDatas(float netBigBuyPercentLevel, float totalBigBuyRatio) {
		// TODO Auto-generated method stub
		TdxDataJdbcImp tj = new TdxDataJdbcImp();
		tj.getAllDatas(netBigBuyPercentLevel,totalBigBuyRatio,allDatas,beginDate,overDay,chooseAllDays);
	}

	public void setUpAndDown(float upAndDown) {
		this.upAndDown = upAndDown;
	}

}
