package jyl.index.vrs.pricevolumn.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import jyl.codegenerate.srbaseclass.AbstractBaseDAO;
import jyl.index.vrs.pricevolumn.bean.PriceVolumnIndexBeanImp;

public abstract class PriceVolumnIndexDaoAbstract extends AbstractBaseDAO 



{


public void save(PriceVolumnIndexBeanImp bean,Connection con)
{String sqlStr = "insert into sse.pirce_volumn_related_index(id,stock_code,trade_date,avg_2close_ratio_bef_1,avg_2close_ratio_bef_3,avg_2close_ratio_bef_5,avg_2close_ratio_bef_10,avg_2close_ratio_bef_20,avg_2close_ratio_bef_60,avg_2close_ratio_bef_120,avg_cost_bef_1_today_ratio,avg_cost_bef_3_today_ratio,avg_cost_bef_5_today_ratio,avg_cost_bef_10_today_ratio,avg_cost_bef_20_today_ratio,avg_cost_bef_60_today_ratio,avg_cost_bef_120_today_ratio,avg_cost_after_1_today_ratio,avg_cost_after_3_today_ratio,avg_cost_after_5_today_ratio,avg_cost_after_10_today_ratio,avg_cost_after_20_today_ratio,avg_cost_after_60_today_ratio,avg_cost_after_120_today_ratio,view_real_avg_ratio_before_1,view_real_avg_ratio_before_3,view_real_avg_ratio_before_5,view_real_avg_ratio_before_10,view_real_avg_ratio_before_20,view_real_avg_ratio_before_60,view_real_avg_ratio_before_120,ts)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
ArrayList<Object> values = new ArrayList<Object> ();
Object objId = bean.getId();
  values.add(objId);
Object objStockCode = bean.getStockCode();
  values.add(objStockCode);
Object objTradeDate = bean.getTradeDate();
  values.add(objTradeDate);
Object objAvg2closeRatioBef1 = bean.getAvg2closeRatioBef1();
  values.add(objAvg2closeRatioBef1);
Object objAvg2closeRatioBef3 = bean.getAvg2closeRatioBef3();
  values.add(objAvg2closeRatioBef3);
Object objAvg2closeRatioBef5 = bean.getAvg2closeRatioBef5();
  values.add(objAvg2closeRatioBef5);
Object objAvg2closeRatioBef10 = bean.getAvg2closeRatioBef10();
  values.add(objAvg2closeRatioBef10);
Object objAvg2closeRatioBef20 = bean.getAvg2closeRatioBef20();
  values.add(objAvg2closeRatioBef20);
Object objAvg2closeRatioBef60 = bean.getAvg2closeRatioBef60();
  values.add(objAvg2closeRatioBef60);
Object objAvg2closeRatioBef120 = bean.getAvg2closeRatioBef120();
  values.add(objAvg2closeRatioBef120);
Object objAvgCostBef1TodayRatio = bean.getAvgCostBef1TodayRatio();
  values.add(objAvgCostBef1TodayRatio);
Object objAvgCostBef3TodayRatio = bean.getAvgCostBef3TodayRatio();
  values.add(objAvgCostBef3TodayRatio);
Object objAvgCostBef5TodayRatio = bean.getAvgCostBef5TodayRatio();
  values.add(objAvgCostBef5TodayRatio);
Object objAvgCostBef10TodayRatio = bean.getAvgCostBef10TodayRatio();
  values.add(objAvgCostBef10TodayRatio);
Object objAvgCostBef20TodayRatio = bean.getAvgCostBef20TodayRatio();
  values.add(objAvgCostBef20TodayRatio);
Object objAvgCostBef60TodayRatio = bean.getAvgCostBef60TodayRatio();
  values.add(objAvgCostBef60TodayRatio);
Object objAvgCostBef120TodayRatio = bean.getAvgCostBef120TodayRatio();
  values.add(objAvgCostBef120TodayRatio);
Object objAvgCostAfter1TodayRatio = bean.getAvgCostAfter1TodayRatio();
  values.add(objAvgCostAfter1TodayRatio);
Object objAvgCostAfter3TodayRatio = bean.getAvgCostAfter3TodayRatio();
  values.add(objAvgCostAfter3TodayRatio);
Object objAvgCostAfter5TodayRatio = bean.getAvgCostAfter5TodayRatio();
  values.add(objAvgCostAfter5TodayRatio);
Object objAvgCostAfter10TodayRatio = bean.getAvgCostAfter10TodayRatio();
  values.add(objAvgCostAfter10TodayRatio);
Object objAvgCostAfter20TodayRatio = bean.getAvgCostAfter20TodayRatio();
  values.add(objAvgCostAfter20TodayRatio);
Object objAvgCostAfter60TodayRatio = bean.getAvgCostAfter60TodayRatio();
  values.add(objAvgCostAfter60TodayRatio);
Object objAvgCostAfter120TodayRatio = bean.getAvgCostAfter120TodayRatio();
  values.add(objAvgCostAfter120TodayRatio);
Object objViewRealAvgRatioBefore1 = bean.getViewRealAvgRatioBefore1();
  values.add(objViewRealAvgRatioBefore1);
Object objViewRealAvgRatioBefore3 = bean.getViewRealAvgRatioBefore3();
  values.add(objViewRealAvgRatioBefore3);
Object objViewRealAvgRatioBefore5 = bean.getViewRealAvgRatioBefore5();
  values.add(objViewRealAvgRatioBefore5);
Object objViewRealAvgRatioBefore10 = bean.getViewRealAvgRatioBefore10();
  values.add(objViewRealAvgRatioBefore10);
Object objViewRealAvgRatioBefore20 = bean.getViewRealAvgRatioBefore20();
  values.add(objViewRealAvgRatioBefore20);
Object objViewRealAvgRatioBefore60 = bean.getViewRealAvgRatioBefore60();
  values.add(objViewRealAvgRatioBefore60);
Object objViewRealAvgRatioBefore120 = bean.getViewRealAvgRatioBefore120();
  values.add(objViewRealAvgRatioBefore120);
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



public void saveBeans(List<PriceVolumnIndexBeanImp> beans,Connection con)
{String sqlStrs = "";
for(PriceVolumnIndexBeanImp bean:beans)
{
Object objId = bean.getId();
String IdvalueStr = convertObj2string(objId); 
Object objStockCode = bean.getStockCode();
String StockCodevalueStr = convertObj2string(objStockCode); 
Object objTradeDate = bean.getTradeDate();
String TradeDatevalueStr = convertObj2string(objTradeDate); 
Object objAvg2closeRatioBef1 = bean.getAvg2closeRatioBef1();
String Avg2closeRatioBef1valueStr = convertObj2string(objAvg2closeRatioBef1); 
Object objAvg2closeRatioBef3 = bean.getAvg2closeRatioBef3();
String Avg2closeRatioBef3valueStr = convertObj2string(objAvg2closeRatioBef3); 
Object objAvg2closeRatioBef5 = bean.getAvg2closeRatioBef5();
String Avg2closeRatioBef5valueStr = convertObj2string(objAvg2closeRatioBef5); 
Object objAvg2closeRatioBef10 = bean.getAvg2closeRatioBef10();
String Avg2closeRatioBef10valueStr = convertObj2string(objAvg2closeRatioBef10); 
Object objAvg2closeRatioBef20 = bean.getAvg2closeRatioBef20();
String Avg2closeRatioBef20valueStr = convertObj2string(objAvg2closeRatioBef20); 
Object objAvg2closeRatioBef60 = bean.getAvg2closeRatioBef60();
String Avg2closeRatioBef60valueStr = convertObj2string(objAvg2closeRatioBef60); 
Object objAvg2closeRatioBef120 = bean.getAvg2closeRatioBef120();
String Avg2closeRatioBef120valueStr = convertObj2string(objAvg2closeRatioBef120); 
Object objAvgCostBef1TodayRatio = bean.getAvgCostBef1TodayRatio();
String AvgCostBef1TodayRatiovalueStr = convertObj2string(objAvgCostBef1TodayRatio); 
Object objAvgCostBef3TodayRatio = bean.getAvgCostBef3TodayRatio();
String AvgCostBef3TodayRatiovalueStr = convertObj2string(objAvgCostBef3TodayRatio); 
Object objAvgCostBef5TodayRatio = bean.getAvgCostBef5TodayRatio();
String AvgCostBef5TodayRatiovalueStr = convertObj2string(objAvgCostBef5TodayRatio); 
Object objAvgCostBef10TodayRatio = bean.getAvgCostBef10TodayRatio();
String AvgCostBef10TodayRatiovalueStr = convertObj2string(objAvgCostBef10TodayRatio); 
Object objAvgCostBef20TodayRatio = bean.getAvgCostBef20TodayRatio();
String AvgCostBef20TodayRatiovalueStr = convertObj2string(objAvgCostBef20TodayRatio); 
Object objAvgCostBef60TodayRatio = bean.getAvgCostBef60TodayRatio();
String AvgCostBef60TodayRatiovalueStr = convertObj2string(objAvgCostBef60TodayRatio); 
Object objAvgCostBef120TodayRatio = bean.getAvgCostBef120TodayRatio();
String AvgCostBef120TodayRatiovalueStr = convertObj2string(objAvgCostBef120TodayRatio); 
Object objAvgCostAfter1TodayRatio = bean.getAvgCostAfter1TodayRatio();
String AvgCostAfter1TodayRatiovalueStr = convertObj2string(objAvgCostAfter1TodayRatio); 
Object objAvgCostAfter3TodayRatio = bean.getAvgCostAfter3TodayRatio();
String AvgCostAfter3TodayRatiovalueStr = convertObj2string(objAvgCostAfter3TodayRatio); 
Object objAvgCostAfter5TodayRatio = bean.getAvgCostAfter5TodayRatio();
String AvgCostAfter5TodayRatiovalueStr = convertObj2string(objAvgCostAfter5TodayRatio); 
Object objAvgCostAfter10TodayRatio = bean.getAvgCostAfter10TodayRatio();
String AvgCostAfter10TodayRatiovalueStr = convertObj2string(objAvgCostAfter10TodayRatio); 
Object objAvgCostAfter20TodayRatio = bean.getAvgCostAfter20TodayRatio();
String AvgCostAfter20TodayRatiovalueStr = convertObj2string(objAvgCostAfter20TodayRatio); 
Object objAvgCostAfter60TodayRatio = bean.getAvgCostAfter60TodayRatio();
String AvgCostAfter60TodayRatiovalueStr = convertObj2string(objAvgCostAfter60TodayRatio); 
Object objAvgCostAfter120TodayRatio = bean.getAvgCostAfter120TodayRatio();
String AvgCostAfter120TodayRatiovalueStr = convertObj2string(objAvgCostAfter120TodayRatio); 
Object objViewRealAvgRatioBefore1 = bean.getViewRealAvgRatioBefore1();
String ViewRealAvgRatioBefore1valueStr = convertObj2string(objViewRealAvgRatioBefore1); 
Object objViewRealAvgRatioBefore3 = bean.getViewRealAvgRatioBefore3();
String ViewRealAvgRatioBefore3valueStr = convertObj2string(objViewRealAvgRatioBefore3); 
Object objViewRealAvgRatioBefore5 = bean.getViewRealAvgRatioBefore5();
String ViewRealAvgRatioBefore5valueStr = convertObj2string(objViewRealAvgRatioBefore5); 
Object objViewRealAvgRatioBefore10 = bean.getViewRealAvgRatioBefore10();
String ViewRealAvgRatioBefore10valueStr = convertObj2string(objViewRealAvgRatioBefore10); 
Object objViewRealAvgRatioBefore20 = bean.getViewRealAvgRatioBefore20();
String ViewRealAvgRatioBefore20valueStr = convertObj2string(objViewRealAvgRatioBefore20); 
Object objViewRealAvgRatioBefore60 = bean.getViewRealAvgRatioBefore60();
String ViewRealAvgRatioBefore60valueStr = convertObj2string(objViewRealAvgRatioBefore60); 
Object objViewRealAvgRatioBefore120 = bean.getViewRealAvgRatioBefore120();
String ViewRealAvgRatioBefore120valueStr = convertObj2string(objViewRealAvgRatioBefore120); 
Object objTs = bean.getTs();
String TsvalueStr = convertObj2string(objTs); 
if("".equals(sqlStrs))
sqlStrs = "insert into sse.pirce_volumn_related_index(stock_code,trade_date,avg_2close_ratio_bef_1,avg_2close_ratio_bef_3,avg_2close_ratio_bef_5,avg_2close_ratio_bef_10,avg_2close_ratio_bef_20,avg_2close_ratio_bef_60,avg_2close_ratio_bef_120,avg_cost_bef_1_today_ratio,avg_cost_bef_3_today_ratio,avg_cost_bef_5_today_ratio,avg_cost_bef_10_today_ratio,avg_cost_bef_20_today_ratio,avg_cost_bef_60_today_ratio,avg_cost_bef_120_today_ratio,avg_cost_after_1_today_ratio,avg_cost_after_3_today_ratio,avg_cost_after_5_today_ratio,avg_cost_after_10_today_ratio,avg_cost_after_20_today_ratio,avg_cost_after_60_today_ratio,avg_cost_after_120_today_ratio,view_real_avg_ratio_before_1,view_real_avg_ratio_before_3,view_real_avg_ratio_before_5,view_real_avg_ratio_before_10,view_real_avg_ratio_before_20,view_real_avg_ratio_before_60,view_real_avg_ratio_before_120)values("+StockCodevalueStr+","+TradeDatevalueStr+","+Avg2closeRatioBef1valueStr+","+Avg2closeRatioBef3valueStr+","+Avg2closeRatioBef5valueStr+","+Avg2closeRatioBef10valueStr+","+Avg2closeRatioBef20valueStr+","+Avg2closeRatioBef60valueStr+","+Avg2closeRatioBef120valueStr+","+AvgCostBef1TodayRatiovalueStr+","+AvgCostBef3TodayRatiovalueStr+","+AvgCostBef5TodayRatiovalueStr+","+AvgCostBef10TodayRatiovalueStr+","+AvgCostBef20TodayRatiovalueStr+","+AvgCostBef60TodayRatiovalueStr+","+AvgCostBef120TodayRatiovalueStr+","+AvgCostAfter1TodayRatiovalueStr+","+AvgCostAfter3TodayRatiovalueStr+","+AvgCostAfter5TodayRatiovalueStr+","+AvgCostAfter10TodayRatiovalueStr+","+AvgCostAfter20TodayRatiovalueStr+","+AvgCostAfter60TodayRatiovalueStr+","+AvgCostAfter120TodayRatiovalueStr+","+ViewRealAvgRatioBefore1valueStr+","+ViewRealAvgRatioBefore3valueStr+","+ViewRealAvgRatioBefore5valueStr+","+ViewRealAvgRatioBefore10valueStr+","+ViewRealAvgRatioBefore20valueStr+","+ViewRealAvgRatioBefore60valueStr+","+ViewRealAvgRatioBefore120valueStr+")";
else

sqlStrs = sqlStrs+",("+StockCodevalueStr+","+TradeDatevalueStr+","+Avg2closeRatioBef1valueStr+","+Avg2closeRatioBef3valueStr+","+Avg2closeRatioBef5valueStr+","+Avg2closeRatioBef10valueStr+","+Avg2closeRatioBef20valueStr+","+Avg2closeRatioBef60valueStr+","+Avg2closeRatioBef120valueStr+","+AvgCostBef1TodayRatiovalueStr+","+AvgCostBef3TodayRatiovalueStr+","+AvgCostBef5TodayRatiovalueStr+","+AvgCostBef10TodayRatiovalueStr+","+AvgCostBef20TodayRatiovalueStr+","+AvgCostBef60TodayRatiovalueStr+","+AvgCostBef120TodayRatiovalueStr+","+AvgCostAfter1TodayRatiovalueStr+","+AvgCostAfter3TodayRatiovalueStr+","+AvgCostAfter5TodayRatiovalueStr+","+AvgCostAfter10TodayRatiovalueStr+","+AvgCostAfter20TodayRatiovalueStr+","+AvgCostAfter60TodayRatiovalueStr+","+AvgCostAfter120TodayRatiovalueStr+","+ViewRealAvgRatioBefore1valueStr+","+ViewRealAvgRatioBefore3valueStr+","+ViewRealAvgRatioBefore5valueStr+","+ViewRealAvgRatioBefore10valueStr+","+ViewRealAvgRatioBefore20valueStr+","+ViewRealAvgRatioBefore60valueStr+","+ViewRealAvgRatioBefore120valueStr+")";}
try {
	excuteSqlStrsSustainCon( sqlStrs, con);
} catch (SQLException e) {
e.printStackTrace();
}}



public ArrayList<PriceVolumnIndexBeanImp>  getBeans(String  querySql,Connection con)
{ArrayList<PriceVolumnIndexBeanImp> result = new ArrayList<PriceVolumnIndexBeanImp>();
	
		Statement st = null;
		try {  
	 st = con.createStatement();
		ResultSet rs = st.executeQuery(querySql);
		while(rs.next())
			{
	PriceVolumnIndexBeanImp bean = new PriceVolumnIndexBeanImp();
		bean.setId(rs.getLong("id"));
		bean.setStockCode(rs.getString("stock_code"));
		bean.setTradeDate(rs.getTimestamp("trade_date"));
		bean.setAvg2closeRatioBef1(rs.getDouble("avg_2close_ratio_bef_1"));
		bean.setAvg2closeRatioBef3(rs.getDouble("avg_2close_ratio_bef_3"));
		bean.setAvg2closeRatioBef5(rs.getDouble("avg_2close_ratio_bef_5"));
		bean.setAvg2closeRatioBef10(rs.getDouble("avg_2close_ratio_bef_10"));
		bean.setAvg2closeRatioBef20(rs.getDouble("avg_2close_ratio_bef_20"));
		bean.setAvg2closeRatioBef60(rs.getDouble("avg_2close_ratio_bef_60"));
		bean.setAvg2closeRatioBef120(rs.getDouble("avg_2close_ratio_bef_120"));
		bean.setAvgCostBef1TodayRatio(rs.getDouble("avg_cost_bef_1_today_ratio"));
		bean.setAvgCostBef3TodayRatio(rs.getDouble("avg_cost_bef_3_today_ratio"));
		bean.setAvgCostBef5TodayRatio(rs.getDouble("avg_cost_bef_5_today_ratio"));
		bean.setAvgCostBef10TodayRatio(rs.getDouble("avg_cost_bef_10_today_ratio"));
		bean.setAvgCostBef20TodayRatio(rs.getDouble("avg_cost_bef_20_today_ratio"));
		bean.setAvgCostBef60TodayRatio(rs.getDouble("avg_cost_bef_60_today_ratio"));
		bean.setAvgCostBef120TodayRatio(rs.getDouble("avg_cost_bef_120_today_ratio"));
		bean.setAvgCostAfter1TodayRatio(rs.getDouble("avg_cost_after_1_today_ratio"));
		bean.setAvgCostAfter3TodayRatio(rs.getDouble("avg_cost_after_3_today_ratio"));
		bean.setAvgCostAfter5TodayRatio(rs.getDouble("avg_cost_after_5_today_ratio"));
		bean.setAvgCostAfter10TodayRatio(rs.getDouble("avg_cost_after_10_today_ratio"));
		bean.setAvgCostAfter20TodayRatio(rs.getDouble("avg_cost_after_20_today_ratio"));
		bean.setAvgCostAfter60TodayRatio(rs.getDouble("avg_cost_after_60_today_ratio"));
		bean.setAvgCostAfter120TodayRatio(rs.getDouble("avg_cost_after_120_today_ratio"));
		bean.setViewRealAvgRatioBefore1(rs.getDouble("view_real_avg_ratio_before_1"));
		bean.setViewRealAvgRatioBefore3(rs.getDouble("view_real_avg_ratio_before_3"));
		bean.setViewRealAvgRatioBefore5(rs.getDouble("view_real_avg_ratio_before_5"));
		bean.setViewRealAvgRatioBefore10(rs.getDouble("view_real_avg_ratio_before_10"));
		bean.setViewRealAvgRatioBefore20(rs.getDouble("view_real_avg_ratio_before_20"));
		bean.setViewRealAvgRatioBefore60(rs.getDouble("view_real_avg_ratio_before_60"));
		bean.setViewRealAvgRatioBefore120(rs.getDouble("view_real_avg_ratio_before_120"));
		bean.setTs(rs.getTimestamp("ts"));

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

