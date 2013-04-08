package jyl.datacollect.tdxdata.concretedata;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;

import jyl.datacollect.tdxdata.concretedata.comparator.imp.Comparator1;
import jyl.datacollect.tdxdata.concretedata.comparator.imp.Comparator2;
import jyl.datacollect.tdxdata.concretedata.entity.DayConcreteEntity;
import jyl.datacollect.tdxdata.dao.TdxDataJdbcImp;
import jyl.util.Log4jLoader;
import jyl.util.MyCalendar;

import org.apache.log4j.Logger;

/*
 * 当前仅仅只有对第二天数据的影响,没有对后面多日数据影响的统计
 */
public class EffectiveAanalys {

	/**
	 * @param args
	 */
	static Logger logger = Logger.getLogger(EffectiveAanalys.class);
	public static String compareParm = null;//当前比较的依据
	HashMap<String,DayConcreteEntity> currentDayDatas = new HashMap<String,DayConcreteEntity>();
	ArrayList<DayConcreteEntity> currentDayDatasArr = new ArrayList<DayConcreteEntity> ();
	static HashMap<String,Float>  commuteVolumeMap = new HashMap<String,Float>();
	static int compareNum = 30;//前n数据和后n数据比较
	Calendar firstDay;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Log4jLoader.loaddefault();
		EffectiveAanalys efa = new EffectiveAanalys();
		efa.analyseBoom();
		/*efa.dataIni(MyCalendar.getDateFromEightStr("20100728"),5);
		 Collections.sort(efa.currentDayDatasArr,new Comparator1());
		efa.analyse(10);
		Collections.sort(efa.currentDayDatasArr,new Comparator2());
		efa.analyse(10);*/
	}
	
	/*
	 * 考虑涨跌因素,大单买大于大单卖,当日跌和涨部分第二日涨幅比较
	 */
	public void analyseBoom()
	{
		dataIni(MyCalendar.getDateFromEightStr("20100729"),2);
		ArrayList<DayConcreteEntity> currentDayDatasArrUp =  new ArrayList<DayConcreteEntity> ();
		ArrayList<DayConcreteEntity> currentDayDatasArrDown =  new ArrayList<DayConcreteEntity> ();
		float totalup = 0.0f;
		float totaldown = 0.0f;
		
		for(DayConcreteEntity de : currentDayDatasArr)
		{ float netBigBuyPercent = de.datas.get("netBigBuyPercent");
		if(netBigBuyPercent>0){ //大单买大于大单卖
			Float closeYest = de.getClosePrice().get(firstDay);
			Float closeToday = de.getClosePrice().get(MyCalendar.nextDayNotWeekEnd(firstDay));		
			Float closeYest1 = de.getClosePrice().get(MyCalendar.backDayNotWeekEnd(firstDay));		
		//	logger.info("firstDay"+":"+MyCalendar.getString(firstDay));
		//	logger.info("closeYest1"+":"+closeYest1);
			if(closeYest1!=null&&closeYest1>0&&closeYest!=null&&closeYest>0&&closeToday!=null&&closeToday>0){
				if(closeYest>closeYest1)
			
		{
				totalup = totalup+(closeToday-closeYest)/closeYest;
			currentDayDatasArrUp.add(de);
		}else{
			totaldown = totaldown+(closeToday-closeYest)/closeYest;
			currentDayDatasArrDown.add(de);
		}
		}
		}
		}
		logger.info("up : "+"\t"+totalup/currentDayDatasArrUp.size()+"down : "+totaldown/currentDayDatasArrDown.size());
	}
	private void analyse(int num) {
		// TODO Auto-generated method stub
		logger.info("analyse i :"+num+"  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		float forwad = 0.0f;
		float backward = 0.0f;
		int forwadNum = 0;
		int backwardNum = 0;
		for(int i=0;i<num;i++)
		{
			//logger.info(message);
			DayConcreteEntity de=currentDayDatasArr.get(i);
		//	logger.info(de.getStockCode()+" : "+de.datas.get(compareParm));
			Float closeYest = de.getClosePrice().get(firstDay);
		//	logger.info("firstDay "+firstDay.getTime().getTime());
		//	logger.info("closeYest "+closeYest);
			Float closeToday = de.getClosePrice().get(MyCalendar.nextDayNotWeekEnd(firstDay));
		//	logger.info("closeToday "+closeToday);
			if(closeToday!=null&&closeToday>0){
				float temp1 = (closeToday-closeYest)/closeYest;
				//backward = backward+ temp1;
				logger.info(de.getStockCode()+" "+temp1);
			forwad = forwad+temp1;
		forwadNum = forwadNum +1;
			}
		}
		logger.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		for(int i = currentDayDatasArr.size() - num;i<currentDayDatasArr.size();i++)
		{
			DayConcreteEntity de=currentDayDatasArr.get(i);
		//	logger.info(de.getStockCode()+" : "+de.datas.get(compareParm));
			Float closeYest = de.getClosePrice().get(firstDay);
			Float closeToday = de.getClosePrice().get(MyCalendar.nextDayNotWeekEnd(firstDay));
		if(closeToday!=null&&closeToday>0)
		{
			float temp1 = (closeToday-closeYest)/closeYest;
			backward = backward+ temp1;
		backwardNum = backwardNum +1;
		logger.info(de.getStockCode()+" "+temp1);
		}
		}
		logger.info("analyse result: \n forward:"+forwad/forwadNum+"\nbackward:"+backward/backwardNum);
		logger.info("analyse i :"+num+"  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	}

	private void dataIni(Date date, int i) {
		// TODO Auto-generated method stub
		 firstDay = Calendar.getInstance();
		firstDay.setTime(date);
		logger.info("firstDay:"+MyCalendar.getString(firstDay));
		Calendar lastDay = MyCalendar.nextNDayExceptWeekEnd(firstDay,i);
		Calendar firstDaytemp =  MyCalendar.backNDayExceptWeekEnd(firstDay,2);
		logger.info("lastDay:"+MyCalendar.getString(lastDay));
		java.sql.Date lastDate = new java.sql.Date(lastDay.getTime().getTime());
		Date dateTemp =  new java.sql.Date(firstDaytemp.getTime().getTime());
		
		TdxDataJdbcImp tdj = new TdxDataJdbcImp();
		
		 tdj.getOneDayData(date,currentDayDatas,currentDayDatasArr);
		 tdj.getOpenClose(currentDayDatas,dateTemp,lastDate);
	//	 commuteVolumeMap = tdj.getComuteVolume();
	
		
		/* logger.info("length : "+currentDayDatasArr.size());
		 for(DayConcreteEntity de : currentDayDatasArr)
		 {
			 logger.info(de.getStockCode()+" "+de.getDatas().get("totalBigBuyRatio")+" "+de.getDatas().get("totalBigSellRatio"));
		 }
*/	}

	public static HashMap<String, Float> getCommuteVolumeMap() {
		return commuteVolumeMap;
	}

	public static String getCompareParm() {
		return compareParm;
	}

	public static void setCompareParm(String compareParm) {
		EffectiveAanalys.compareParm = compareParm;
	}


}
