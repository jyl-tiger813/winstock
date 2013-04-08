package jyl.util.mondrian.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import jyl.codegenerate.srbaseclass.AbstractBaseDAO;
import jyl.util.mondrian.bean.TimeDimGenerateBeanImp;


public abstract class TimeDimGenerateDaoAbstract extends AbstractBaseDAO 



{


public void save(TimeDimGenerateBeanImp bean,Connection con)
{String sqlStr = "insert into incrementbak.time_by_day(time_id,the_date,the_day,the_month,the_year,day_of_month,week_of_year,month_of_year,quarter)values(?,?,?,?,?,?,?,?,?)";
ArrayList<Object> values = new ArrayList<Object> ();
Object objTimeId = bean.getTimeId();
  values.add(objTimeId);
Object objTheDate = bean.getTheDate();
  values.add(objTheDate);
Object objTheDay = bean.getTheDay();
  values.add(objTheDay);
Object objTheMonth = bean.getTheMonth();
  values.add(objTheMonth);
Object objTheYear = bean.getTheYear();
  values.add(objTheYear);
Object objDayOfMonth = bean.getDayOfMonth();
  values.add(objDayOfMonth);
Object objWeekOfYear = bean.getWeekOfYear();
  values.add(objWeekOfYear);
Object objMonthOfYear = bean.getMonthOfYear();
  values.add(objMonthOfYear);
Object objQuarter = bean.getQuarter();
  values.add(objQuarter);

try {
	excutePreparedParams( sqlStr,  values, con);
} catch (SQLException e) {
e.printStackTrace();
}finally{
if(con!=null)
try {
	con.close();
} catch (SQLException e) {
e.printStackTrace();
}
}}



public void saveBeans(ArrayList<TimeDimGenerateBeanImp> beans,Connection con)
{String sqlStrs = "";
for(TimeDimGenerateBeanImp bean:beans)
{
Object objTimeId = bean.getTimeId();
String TimeIdvalueStr = convertObj2string(objTimeId); 
Object objTheDate = bean.getTheDate();
String TheDatevalueStr = convertObj2string(objTheDate); 
Object objTheDay = bean.getTheDay();
String TheDayvalueStr = convertObj2string(objTheDay); 
Object objTheMonth = bean.getTheMonth();
String TheMonthvalueStr = convertObj2string(objTheMonth); 
Object objTheYear = bean.getTheYear();
String TheYearvalueStr = convertObj2string(objTheYear); 
Object objDayOfMonth = bean.getDayOfMonth();
String DayOfMonthvalueStr = convertObj2string(objDayOfMonth); 
Object objWeekOfYear = bean.getWeekOfYear();
String WeekOfYearvalueStr = convertObj2string(objWeekOfYear); 
Object objMonthOfYear = bean.getMonthOfYear();
String MonthOfYearvalueStr = convertObj2string(objMonthOfYear); 
Object objQuarter = bean.getQuarter();
String QuartervalueStr = convertObj2string(objQuarter); 
if("".equals(sqlStrs))
sqlStrs = "insert into incrementbak.time_by_day(time_id,the_date,the_day,the_month,the_year,day_of_month,week_of_year,month_of_year,quarter)values("+TimeIdvalueStr+","+TheDatevalueStr+","+TheDayvalueStr+","+TheMonthvalueStr+","+TheYearvalueStr+","+DayOfMonthvalueStr+","+WeekOfYearvalueStr+","+MonthOfYearvalueStr+","+QuartervalueStr+")";
else

sqlStrs = sqlStrs+",("+TimeIdvalueStr+","+TheDatevalueStr+","+TheDayvalueStr+","+TheMonthvalueStr+","+TheYearvalueStr+","+DayOfMonthvalueStr+","+WeekOfYearvalueStr+","+MonthOfYearvalueStr+","+QuartervalueStr+")";}
try {
excuteSqlStrs( sqlStrs, con);
} catch (SQLException e) {
e.printStackTrace();
}finally{
if(con!=null)
try {
	con.close();
} catch (SQLException e) {
e.printStackTrace();
}
}}



public ArrayList<TimeDimGenerateBeanImp>  getBeans(String  querySql,Connection con)
{ArrayList<TimeDimGenerateBeanImp> result = new ArrayList<TimeDimGenerateBeanImp>();
	
		Statement st = null;
		try {  
	 st = con.createStatement();
		ResultSet rs = st.executeQuery(querySql);
		while(rs.next())
			{
	TimeDimGenerateBeanImp bean = new TimeDimGenerateBeanImp();
		bean.setTimeId(rs.getInt("TimeId"));
		bean.setTheDate(rs.getTimestamp("TheDate"));
		bean.setTheDay(rs.getString("TheDay"));
		bean.setTheMonth(rs.getString("TheMonth"));
		bean.setTheYear(rs.getInt("TheYear"));
		bean.setDayOfMonth(rs.getString("DayOfMonth"));
		bean.setWeekOfYear(rs.getInt("WeekOfYear"));
		bean.setMonthOfYear(rs.getInt("MonthOfYear"));
		bean.setQuarter(rs.getString("Quarter"));

		result.add(bean);
}
	} catch (SQLException e) {
	 e.printStackTrace(); 	}
	 	finally{
	 
	 		if(st!=null)
	 		try {
	 			st.close();
	 		} catch (SQLException e) {
	 		e.printStackTrace();
	 	}
	 	}
		if(result.size()>0)
			return result;
		else return null;
		}
}

