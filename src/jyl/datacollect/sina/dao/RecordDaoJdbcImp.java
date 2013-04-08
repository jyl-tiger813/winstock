package jyl.datacollect.sina.dao;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import jyl.datacollect.sina.bo.StockFirstDayInfo;
import jyl.util.JdbcUtil;

public class RecordDaoJdbcImp implements RecordDao{
	private static Connection con;
	private static String saveStocks="insert into sse.record values (?,?,?,?,?,?,?)";

	public static Connection getCon() {
		if(con==null)
			setCon();
		return con;
	}

	public static void setCon() {
		RecordDaoJdbcImp.con = JdbcUtil.getConnection();;
	}
	public void saveLack(List<Calendar> lack) {
		// TODO Auto-generated method stub
		Connection con1=getCon();
		PreparedStatement pre;
		try {
			pre=con1.prepareStatement(saveStocks);
			for(Calendar cal :lack){
				pre.setDate(1,new java.sql.Date (cal.getTime().getTime()) );
				pre.addBatch();
			}
			pre.executeBatch();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void saveRDs(List<StockFirstDayInfo> records) {
		// TODO Auto-generated method stub
		Connection con1=getCon();
		PreparedStatement pre;
		/*
		 * private String stockNum;//证券代码
	private String stockName;//证券简称
	private Calendar issueTime;//上网发行日期
	private Calendar toMarketTime;//上市日期  
	private int issueAmount;//发行数量(万股) 
	private int netIssueAmount;//上网发行数量(万股) 
	private float issuePrice;//发行价格(元) 
	private float ratio;//市盈率 
	private boolean integrySym;//信息是否已经采集完整
		 */
		 
		try {
			pre=con1.prepareStatement(saveStocks);
			for(StockFirstDayInfo sfd :records){
				pre.setString(1,sfd.getStockNum() );
				pre.setString(2,sfd.getStockName() );
				pre.setDate(3, new java.sql.Date(sfd.getIssueTime().getTime().getTime()) );
				pre.setDate(4, new java.sql.Date(sfd.getToMarketTime().getTime().getTime()) );
				pre.setInt(5,sfd.getIssueAmount() );
				pre.setInt(6,sfd.getNetIssueAmount() );
				
				pre.setFloat(7, sfd.getIssuePrice());
				pre.setFloat(8, sfd.getRatio());
				pre.setBoolean(9, sfd.isIntegrySym());
				pre.addBatch();
			}
			pre.executeBatch();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
