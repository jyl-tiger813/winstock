package jyl.datacollect.sina.dailytrade.datafetcher.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import jyl.codegenerate.srbaseclass.AbstractBaseDAO;
import jyl.datacollect.sina.dailytrade.datafetcher.bean.StockNameBeanImp;

public abstract class StockNameDaoAbstract extends AbstractBaseDAO 



{


public void save(StockNameBeanImp bean,Connection con)
{String sqlStr = "insert into sse.stocknames(code_id,stock_name)values(?,?)";
ArrayList<Object> values = new ArrayList<Object> ();
Object objCodeId = bean.getCodeId();
  values.add(objCodeId);
Object objStockName = bean.getStockName();
  values.add(objStockName);

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



public void saveBeans(ArrayList<StockNameBeanImp> beans,Connection con)
{String sqlStrs = "";
for(StockNameBeanImp bean:beans)
{
Object objCodeId = bean.getCodeId();
String CodeIdvalueStr = convertObj2string(objCodeId); 
Object objStockName = bean.getStockName();
String StockNamevalueStr = convertObj2string(objStockName); 
if("".equals(sqlStrs))
sqlStrs = "insert into sse.stocknames(code_id,stock_name)values("+CodeIdvalueStr+","+StockNamevalueStr+")";
else

sqlStrs = sqlStrs+",("+CodeIdvalueStr+","+StockNamevalueStr+")";}
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



public ArrayList<StockNameBeanImp>  getBeans(String  querySql,Connection con)
{ArrayList<StockNameBeanImp> result = new ArrayList<StockNameBeanImp>();
	
		Statement st = null;
		try {  
	 st = con.createStatement();
		ResultSet rs = st.executeQuery(querySql);
		while(rs.next())
			{
	StockNameBeanImp bean = new StockNameBeanImp();
		bean.setCodeId(rs.getString("code_id"));
		bean.setStockName(rs.getString("stock_name"));

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

