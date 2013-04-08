package jyl.datacollect.tdxdata.concretedata.buysellcompare;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Set;
import java.util.Map.Entry;
import jyl.datacollect.tdxdata.dao.TdxDataJdbcImp;
import jyl.util.Log4jLoader;
import jyl.util.MyCalendar;
import org.apache.log4j.Logger;

public class BuySellUpDownSetter {

	/**
	 * 计算第二天的股票升降幅度
	 */
	static Logger logger = Logger.getLogger(BuySellUpDownSetter.class);
	
	private HashMap<String,HashMap<Calendar,HashMap<String,Float>>> allDatas
	= 	new HashMap<String,HashMap<Calendar,HashMap<String,Float>>> ();
	
	private String tableName;
	
	private String beginDate = "2011-07-22" ;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Log4jLoader.loaddefault();
		BuySellUpDownSetter bus = new BuySellUpDownSetter();
	//	busSetLackDatas("tdxdataanalysis.concretedata_szag");
		bus.setUpDown("tdxdataanalysis.concretedata_szag");
		//bus.setUpDown("tdxhistory.data_szag");
	}

	private static void busSetLackDatas(String string) {
		// TODO Auto-generated method stub
		
	}

	public void setUpDown(String string) {
		// TODO Auto-generated method stub
		this.tableName = string;
		getDatas(allDatas);
		countDatas();
		updateDatas(allDatas);
	}

	private void updateDatas(HashMap<String, HashMap<Calendar, HashMap<String, Float>>> allDatas) {
		// TODO Auto-generated method stub
		TdxDataJdbcImp tj = new TdxDataJdbcImp();
		tj.setBuySellUpDowns(allDatas,tableName);
	}

	private void getDatas(HashMap<String, HashMap<Calendar, HashMap<String, Float>>> allDatas2) {
		// TODO Auto-generated method stub
		TdxDataJdbcImp tj = new TdxDataJdbcImp();
		tj.getdatasForBuySellUpD(allDatas2,tableName,beginDate);
	}

	private void countDatas() {
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
					Calendar beforeDay = MyCalendar.backDayNotWeekEnd (cal);
					Calendar afterDay = MyCalendar.nextDayNotWeekEnd(cal);
					HashMap<String, Float> datas = entry2.getValue();
					Float close1 = datas.get("close");
						HashMap<String, Float> datasBefore = map1.get(beforeDay);
					HashMap<String, Float> datasAfter = map1.get(afterDay);
					Float closeBefore = null;
					Float closeAfter = null;
					if(datasBefore!=null)
					  closeBefore = datasBefore.get("close");
					if(datasAfter!=null)
					  closeAfter = datasAfter.get("close");
					if(close1>0)
					{
						if(closeBefore!=null&&closeBefore>0)
							datas.put("upAndDownThisDay", (close1-closeBefore)/closeBefore);
						if(closeAfter!=null&&closeAfter>0)
							datas.put("upAndDownNextDay", (closeAfter-close1)/close1);		
					}
					
					}
				} 
				
			}
	}

	


