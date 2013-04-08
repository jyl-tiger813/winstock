package jyl.util;

import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Set;

import jyl.datacollect.tdxdata.concretedata.GetBlockCode;
import jyl.datacollect.tdxdata.dao.TdxDataJdbcImp;

import org.apache.log4j.Logger;

public class MyCalendar {
	
	/*
	 * 把1993-03-13格式的字符转换成日期
	 */
	static Logger logger = Logger.getLogger(GetBlockCode.class);
	static Set<String> ceaseDay;
	public static Calendar createCalendar(String ymd){
		String [] params=ymd.split("-");
		return new  GregorianCalendar(Integer.parseInt(params[0]),
				Integer.parseInt(params[1])-1,Integer.parseInt(params[2]));
	}
	
	public static String getString(Calendar cal){
		int month = cal.get(Calendar.MONTH)+1;
		int date = cal.get(Calendar.DATE);
		String monthStr = month>9?month+"" : "0"+month;
		String dateStr = date>9?date+"" : "0"+date;
		return (cal.get(Calendar.YEAR)+"-"+monthStr+"-"+dateStr);
	}
	
	public static String getStringEight(Calendar cal){
		int month = cal.get(Calendar.MONTH)+1;
		int date = cal.get(Calendar.DATE);
		String monthStr = month>9?month+"" : "0"+month;
		String dateStr = date>9?date+"" : "0"+date;
		return (cal.get(Calendar.YEAR)+monthStr+dateStr);
	}
	public static void main(String args[]){
		
		/*Calendar cal=MyCalendar.createCalendar("2009-2-01");
		String date =MyCalendar.getString(cal);
		System.out.println(date);
		String date1 = "2009/03/04";
		Date d1 = transToDate1(date1 );
		cal.setTime(d1);
		System.out.println(MyCalendar.getString(cal));*/
		java.sql.Date d1=MyCalendar.getDateFromEightStr("20090908");
		Calendar cal = new GregorianCalendar();
		cal.setTime(d1);
		System.out.println(MyCalendar.getString(cal));
	}
	/*
	 * 把20090908格式日期转换成数据库日期格式
	 */
	public static Date getDateFromEightStr(String string) {
		// TODO Auto-generated method stub
		int year =Integer.parseInt( string.substring(0, 4))-1900;
		int month =Integer.parseInt( string.substring(4, 6))-1;
		int day =Integer.parseInt( string.substring(6, 8));
		return new java.sql.Date(year,month,day);
	}

	public static Calendar getCalendarFromEightStr(String string) {
		// TODO Auto-generated method stub
		int year =Integer.parseInt( string.substring(0, 4))-1900;
		int month =Integer.parseInt( string.substring(4, 6))-1;
		int day =Integer.parseInt( string.substring(6, 8));
		return new  GregorianCalendar(year,	month,day);
	
	}

	/*
	 * 把1993/03/13格式的字符转换成日期
	 */
	
	public static Date transToDate1(String trim) {
		// TODO Auto-generated method stub
		String [] temp = trim.trim().split("/");
		int year = Integer.parseInt(temp[0])-1900;
		int month = Integer.parseInt(temp[1])-1;
		int day = Integer.parseInt(temp[2]); 
		return new java.sql.Date(year,month,day);
	}
	public static Calendar nextDayNotWeekEnd(Calendar cal){
		if(ceaseDay==null)
			setCeaseDay();
		Calendar temp = Calendar.getInstance();
		temp.setTimeInMillis(cal.getTimeInMillis());
		temp.add(Calendar.DAY_OF_YEAR,1);
		while(temp.get(Calendar.DAY_OF_WEEK)==Calendar.SATURDAY||
				temp.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY||
				ceaseDay.contains(MyCalendar.getStringEight(temp)))
			temp.add(Calendar.DAY_OF_YEAR,1);		
				return temp;								
		}
	
	public static Calendar nextNDayExceptWeekEnd(Calendar cal,int n){
		Calendar temp = Calendar.getInstance();
		temp.setTimeInMillis(cal.getTimeInMillis());
		for(int i = 0;i<n;i++)	
		{
			temp = nextDayNotWeekEnd(temp);
		}
				return temp;								
		}

	public static Calendar backDayNotWeekEnd(Calendar c1) {
		// TODO Auto-generated method stub
		if(ceaseDay==null)
		setCeaseDay();
		Calendar temp = Calendar.getInstance();
		logger.info("temp1 : "+MyCalendar.getString(temp)+temp.get(Calendar.HOUR_OF_DAY));
		temp.setTimeInMillis(c1.getTimeInMillis());
		logger.info("temp2 : "+MyCalendar.getString(temp)+" "+
		temp.get(Calendar.HOUR_OF_DAY)+" "+temp.get(Calendar.MINUTE)+" "+temp.get(Calendar.SECOND));
		
		temp.add(Calendar.DAY_OF_YEAR,-1);
		logger.info("temp3 : "+MyCalendar.getString(temp)+" "+
				temp.get(Calendar.HOUR_OF_DAY)+" "+temp.get(Calendar.MINUTE)+" "+temp.get(Calendar.MILLISECOND));
		logger.info("contain:"+ceaseDay.contains(temp));
		while(temp.get(Calendar.DAY_OF_WEEK)==Calendar.SATURDAY||
				temp.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY||
				ceaseDay.contains(MyCalendar.getStringEight(temp)))
			temp.add(Calendar.DAY_OF_YEAR,-1);		
				return temp;								
		}
	private static void setCeaseDay() {
		// TODO Auto-generated method stub
		TdxDataJdbcImp tdj = new TdxDataJdbcImp();
		 ceaseDay = tdj.getCeaseDayThisYear();//得到停牌日的集合
		
	}

	public static Calendar backNDayExceptWeekEnd(Calendar cal,int n){
		Calendar temp = Calendar.getInstance();
		temp.setTimeInMillis(cal.getTimeInMillis());
		for(int i = 0;i<n;i++)	
		{
			temp = backDayNotWeekEnd(temp);
		}
				return temp;								
		}
}
