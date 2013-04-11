package jyl.index.vrs.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import jyl.codegenerate.srbaseclass.AbstractBaseDAO;
import jyl.index.vrs.bean.VrsIndexDataBeanImp;

public abstract class VrsIndexDataDaoAbstract extends AbstractBaseDAO 

{


public void save(VrsIndexDataBeanImp bean,Connection con)
{String sqlStr = "insert into sse.index_vr_related_data(index_id,stock_code,count_days,trade_date,index_value)values(?,?,?,?,?)";
ArrayList<Object> values = new ArrayList<Object> ();
Object objIndexId = bean.getIndexId();
  values.add(objIndexId);
Object objStockCode = bean.getStockCode();
  values.add(objStockCode);
Object objCountDays = bean.getCountDays();
  values.add(objCountDays);
Object objTradeDate = bean.getTradeDate();
  values.add(objTradeDate);
Object objIndexValue = bean.getIndexValue();
  values.add(objIndexValue);

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



public void saveBeans(String keyStr, List<VrsIndexDataBeanImp> beans,Connection con)
{String sqlStrs = "";
/*
 * private Double ave_change_ratiodouble ;//今天昨天均价变化率
	private Double change_ratio_close_begindouble ;//今天收盘 开盘
	private Double change_ratio_avg_closedouble ;//今天均价收盘
 */
for(VrsIndexDataBeanImp bean:beans)
{
Object objIndexId = bean.getIndexId();
String IndexIdvalueStr = convertObj2string(objIndexId); 
Object objStockCode = bean.getStockCode();
String StockCodevalueStr = convertObj2string(objStockCode); 
Object objCountDays = bean.getCountDays();
String CountDaysvalueStr = convertObj2string(objCountDays); 
Object objTradeDate = bean.getTradeDate();
String TradeDatevalueStr = convertObj2string(objTradeDate); 
Object objIndexValue = bean.getIndexValue();
String IndexValuevalueStr = convertObj2string(objIndexValue); 
Object objStockCodeInt = bean.getStockCodeInt();
String StockCodeIntValue = convertObj2string(objStockCodeInt); 
Object changeRatio = bean.getChangeRatio();
String changeRatioStr = convertObj2string(changeRatio); 
Object ave_change_ratio = bean.getAve_change_ratio();
String ave_change_ratioStr = convertObj2string(ave_change_ratio); 
Object change_ratio_close_begin = bean.getChange_ratio_close_begin();
String change_ratio_close_beginStr = convertObj2string(change_ratio_close_begin); 
Object change_ratio_avg_close = bean.getChange_ratio_avg_close();
String change_ratio_avg_closeStr = convertObj2string(change_ratio_avg_close); 


if("".equals(sqlStrs))
sqlStrs = "replace into sse.index_vr_related_data"+keyStr+"(index_id,stock_code,change_ratio,ave_change_ratio,change_ratio_close_begin," +
		"change_ratio_avg_close,stock_code_int,count_days,trade_date,index_value)values(" +
		""+IndexIdvalueStr+","+StockCodevalueStr+","+changeRatioStr+","+ave_change_ratioStr+","+change_ratio_close_beginStr+","+change_ratio_avg_closeStr+","+StockCodeIntValue+","+CountDaysvalueStr+","+TradeDatevalueStr+","+IndexValuevalueStr+")";
else

sqlStrs = sqlStrs+",("+IndexIdvalueStr+","+StockCodevalueStr+","+changeRatioStr+"," +ave_change_ratioStr+","+change_ratio_close_beginStr+","+change_ratio_avg_closeStr+","+
		""+StockCodeIntValue+","+CountDaysvalueStr+","+TradeDatevalueStr+","+IndexValuevalueStr+")";}
try {
	excuteSqlStrsSustainCon( sqlStrs, con);
} catch (SQLException e) {
e.printStackTrace();
System.out.println("sqlStrs:"+sqlStrs);
}}



public ArrayList<VrsIndexDataBeanImp>  getBeans(String  querySql,Connection con)
{ArrayList<VrsIndexDataBeanImp> result = new ArrayList<VrsIndexDataBeanImp>();
	
		Statement st = null;
		try {  
	 st = con.createStatement();
		ResultSet rs = st.executeQuery(querySql);
		while(rs.next())
			{
	VrsIndexDataBeanImp bean = new VrsIndexDataBeanImp();
		bean.setIndexId(rs.getInt("index_id"));
		bean.setStockCode(rs.getString("stock_code"));
		bean.setCountDays(rs.getInt("count_days"));
		bean.setTradeDate(rs.getTimestamp("trade_date"));
		bean.setIndexValue(rs.getDouble("index_value"));

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
	 		//TODO 需要修改代码生成器的相应位置
	 		if(con!=null)
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	 	}
		if(result.size()>0)
			return result;
		else return null;
		}
}

