package jyl.index.vrs.etl;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import jyl.datacollect.sina.dailytrade.datafetcher.bean.StockNameBeanImp;
import jyl.datacollect.sina.dailytrade.datafetcher.dao.StockNameDaoImp;
import jyl.index.vrs.bo.IndexCounterAbstract;
import jyl.util.DatabaseHelper;

/**
 * 类描述：   
 * 创建人：jinyongliang
 * 创建时间：Apr 22, 2013 11:26:18 PM   
 * 修改人：jinyongliang   
 * 修改时间：Apr 22, 2013 11:26:18 PM   
 * 修改备注：   
 * @version 
 */
public class FilterBy2Indexs  {
	protected     Connection con = null;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FilterBy2Indexs fid = new FilterBy2Indexs();
		fid.DealAllDatas();
		
	}

	/**
	 * 
	 */
	private void DealAllDatas() {
		// TODO Auto-generated method stub
		StockNameDaoImp snDao = new StockNameDaoImp();
		String querySql = "select * from sse.stocknames where isdealed <>1";
		//String querySql = "select * from sse.stocknames where  code_id = '600559' ";
		Connection con = null;
		//	con = ds.getConnection();
			 String user = "root";
			String password = "manager";
			 String url = "jdbc:mysql://localhost:3306/sse?useUnicode=true&amp;characterEncoding=UTF-8";
					
			 try {
				if(con==null||con.isClosed())			
						con = DatabaseHelper.getAdConnectionByParams(url,user,password);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		ArrayList<StockNameBeanImp>  stockBeans = snDao.getBeans(querySql, con);
		int i =0 ;
		for(StockNameBeanImp bean : stockBeans)
		{
		i++;
		System.out.println("i:"+i);
		dealOneStock(bean.getCodeId());
		modifyStatus(bean);
		}
	}
	
	/**
	 * @param codeId
	 */
	private void dealOneStock(String codeId) {
		// TODO Auto-generated method stub
		String baseSql = 
		     " REPLACE INTO index_vr_related_data4_10_pirce_volumn_related_index"+
		     " (index_value,stock_code,trade_date,avg_2close_ratio_bef_3"+
		    " ,avg_2close_ratio_bef_5,avg_2close_ratio_bef_10,avg_2close_ratio_bef_20,"+
		    " avg_cost_bef_20_today_ratio,avg_cost_bef_10_today_ratio,avg_cost_bef_60_today_ratio ,"+
		    " avg_cost_after_3_today_ratio,avg_cost_after_5_today_ratio,avg_cost_after_10_today_ratio,"+
		    " avg_cost_after_20_today_ratio,avg_cost_after_60_today_ratio)"+
		    " SELECT index_value,stock_code,trade_date,avg_2close_ratio_bef_3"+
		    " ,avg_2close_ratio_bef_5,avg_2close_ratio_bef_10,avg_2close_ratio_bef_20,"+
		    " avg_cost_bef_20_today_ratio,avg_cost_bef_10_today_ratio,avg_cost_bef_60_today_ratio ,"+
		    " avg_cost_after_3_today_ratio,avg_cost_after_5_today_ratio,avg_cost_after_10_today_ratio,"+
		    " avg_cost_after_20_today_ratio,avg_cost_after_60_today_ratio"+
		    " FROM( SELECT index_value ,t2.* FROM("+
		    "    SELECT * FROM sse.index_vr_related_data4_10"+
		  " WHERE  index_value > 1000"+
		   " AND  stock_code = '?1')t1,"+
		     " (SELECT * FROM sse.pirce_volumn_related_index"+
		"  WHERE  stock_code = '?1')t2 WHERE t1.stock_code = t2.stock_code "+
		 " AND t1.trade_date = t2.trade_date ) AS t3";
		  String sql = baseSql.replaceAll("\\?1", codeId);
		  con = setCon ();
		  try {
			DatabaseHelper.excuteSqlStrsSustainCon( sql,con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @param bean
	 */
	public void modifyStatus(StockNameBeanImp bean) {
		// TODO Auto-generated method stub
		String sql = " UPDATE stocknames SET isdealed =1 WHERE code_id = '?1'";
		String exeSql = sql.replace("?1", bean.getCodeId());
			con = setCon ();
			
			Statement st = null;
		 try {
			st = con.createStatement();
			st.execute(exeSql);
			System.out.println("exeSql:"+exeSql);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public Connection setCon (){
		 String user = "root";
			String password = "manager";
			 String url = "jdbc:mysql://localhost:3306/sse?useUnicode=true&amp;characterEncoding=UTF-8";
				try {
					if(con==null||con.isClosed())			
						con = DatabaseHelper.getAdConnectionByParams(url,user,password);
					return con;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return null;
				}
	}
}
