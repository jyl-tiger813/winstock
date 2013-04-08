package jyl.datacollect.sina.dailytrade.datafetcher.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import jyl.codegenerate.srbaseclass.AbstractBaseDAO;
import jyl.datacollect.sina.dailytrade.datafetcher.bean.DailyTradeInfoBeanImp;

public abstract class DailyTradeInfoDaoAbstract extends AbstractBaseDAO 

{


public void save(DailyTradeInfoBeanImp bean,Connection con)
{String sqlStr = "insert into sse.stock_trade_daily_detail(stock_code,trade_date,closed_yes,begin_today,close_today,amount,volumn,maxprice,minprice,ts)values(?,?,?,?,?,?,?,?,?,?)";
ArrayList<Object> values = new ArrayList<Object> ();
Object objId = bean.getId();
  values.add(objId);
Object objStockCode = bean.getStockCode();
  values.add(objStockCode);
Object objTradeDate = bean.getTradeDate();
  values.add(objTradeDate);
Object objClosedYes = bean.getClosedYes();
  values.add(objClosedYes);
Object objBeginToday = bean.getBeginToday();
  values.add(objBeginToday);
Object objCloseToday = bean.getCloseToday();
  values.add(objCloseToday);
Object objAmount = bean.getAmount();
  values.add(objAmount);
Object objVolumn = bean.getVolumn();
  values.add(objVolumn);
Object objMaxprice = bean.getMaxprice();
  values.add(objMaxprice);
Object objMinprice = bean.getMinprice();
  values.add(objMinprice);
Object objTs = bean.getTs();
  values.add(objTs);

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



public void saveBeans(List<DailyTradeInfoBeanImp> beans,Connection con)
{String sqlStrs = "";
for(DailyTradeInfoBeanImp bean:beans)
{
Object objId = bean.getId();
String IdvalueStr = convertObj2string(objId); 
Object objStockCode = bean.getStockCode();
String StockCodevalueStr = convertObj2string(objStockCode); 
Object objTradeDate = bean.getTradeDate();
String TradeDatevalueStr = convertObj2string(objTradeDate); 
Object objClosedYes = bean.getClosedYes();
String ClosedYesvalueStr = convertObj2string(objClosedYes); 
Object objBeginToday = bean.getBeginToday();
String BeginTodayvalueStr = convertObj2string(objBeginToday); 
Object objCloseToday = bean.getCloseToday();
String CloseTodayvalueStr = convertObj2string(objCloseToday); 
Object objAmount = bean.getAmount();
String AmountvalueStr = convertObj2string(objAmount); 
Object objVolumn = bean.getVolumn();
String VolumnvalueStr = convertObj2string(objVolumn); 
Object objMaxprice = bean.getMaxprice();
String MaxpricevalueStr = convertObj2string(objMaxprice); 
Object objMinprice = bean.getMinprice();
String MinpricevalueStr = convertObj2string(objMinprice); 
Object objTs = bean.getTs();
String TsvalueStr = convertObj2string(objTs); 
if("".equals(sqlStrs))
sqlStrs = "replace into sse.stock_trade_daily_detail(stock_code,trade_date,closed_yes,begin_today,close_today,amount,volumn,maxprice,minprice)values("+StockCodevalueStr+","+TradeDatevalueStr+","+ClosedYesvalueStr+","+BeginTodayvalueStr+","+CloseTodayvalueStr+","+AmountvalueStr+","+VolumnvalueStr+","+MaxpricevalueStr+","+MinpricevalueStr+")";
else

sqlStrs = sqlStrs+",("+StockCodevalueStr+","+TradeDatevalueStr+","+ClosedYesvalueStr+","+BeginTodayvalueStr+","+CloseTodayvalueStr+","+AmountvalueStr+","+VolumnvalueStr+","+MaxpricevalueStr+","+MinpricevalueStr+")";}
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



public ArrayList<DailyTradeInfoBeanImp>  getBeans(String  querySql,Connection con)
{ArrayList<DailyTradeInfoBeanImp> result = new ArrayList<DailyTradeInfoBeanImp>();
	
		Statement st = null;
		try {  
	 st = con.createStatement();
		ResultSet rs = st.executeQuery(querySql);
		while(rs.next())
			{
			DailyTradeInfoBeanImp bean = new DailyTradeInfoBeanImp();
		bean.setId(rs.getLong("id"));
		bean.setStockCode(rs.getString("stock_code"));
		bean.setTradeDate(rs.getTimestamp("trade_date"));
		bean.setClosedYes(rs.getFloat("closed_yes"));
		bean.setBeginToday(rs.getFloat("begin_today"));
		bean.setCloseToday(rs.getFloat("close_today"));
		bean.setAmount(rs.getDouble("amount"));
		bean.setVolumn(rs.getLong("volumn"));
		bean.setMaxprice(rs.getFloat("maxprice"));
		bean.setMinprice(rs.getFloat("minprice"));
		bean.setTs(rs.getTimestamp("ts"));

		result.add(bean);
}
	} catch (SQLException e) {
	 e.printStackTrace(); 	}
	finally{
		if(st!=null){
			try {
				st.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
		if(result.size()>0)
			return result;
		else return null;
		}
}

