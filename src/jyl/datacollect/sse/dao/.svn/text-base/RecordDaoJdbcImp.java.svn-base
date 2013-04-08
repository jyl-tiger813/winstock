package jyl.datacollect.sse.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import jyl.datacollect.sse.entity.Cease;
import jyl.datacollect.sse.entity.Record;
import jyl.util.JdbcUtil;
import jyl.util.MyCalendar;

public class RecordDaoJdbcImp implements RecordDao{
	private static Connection con;
	private static String sql1="insert into sse.record values (?,?,?,?,?,?,?,?,?)";
	private static String sql2="insert into sse.cease values (?)";
	private static String ceaseDays="select changeTime from sse.cease where changeTime>=? and changeTime<=?";
	private static String hasRecordDays="select changeTime from sse.record where changeTime>=? and changeTime<=? ";
	private static String saveLackSql="insert into sse.lack values (?)";
	private static String lackSql="select changeTime from sse.lack where changeTime>=? and changeTime<=? ";
	private static String cleanLack = "delete from sse.lack";

	@Override
	public List<Record> queryList(Calendar cal1, Calendar cal2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveCE(Cease cease) {
		PreparedStatement pre=null;
		// TODO Auto-generated method stub
		if(con==null)
			 con=JdbcUtil.getConnection();
		try {
			 pre=con.prepareStatement(sql2);
			 pre.setDate(1, new java.sql.Date (cease.getTime().getTime().getTime()));
			 pre.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				pre.close();
			//	JdbcUtil.release(null, null, con);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public void saveRD(Record record) {
		/*
		 * private float maketTotal;//市价总值
	private float circulateTotal;//流通市值
	private float volumn;//成交量
	private float amount;//成交金额
	private float hundredShare;//成交笔数
	private float perGet;//平均市盈率
		 */
		// TODO Auto-generated method stub
		Connection con1=getCon();
		
		try {
			PreparedStatement pre=con1.prepareStatement(sql1);
			pre.setDate(1, new java.sql.Date (record.getTime().getTime().getTime()));
			pre.setFloat(2, record.getMaketTotal());
			pre.setFloat(3, record.getCirculateTotal());
			pre.setFloat(4, record.getVolumn());
			pre.setFloat(5, record.getAmount());
			pre.setFloat(6, record.getHundredShare());
			pre.setFloat(7, record.getPerGet());
			pre.setFloat(8, record.getChangeRatio());
			pre.setFloat(9, record.getAmountPerChange());
			
			pre.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Connection getCon() {
		if(con==null)
			setCon();
		return con;
	}

	public static void setCon() {
		RecordDaoJdbcImp.con = JdbcUtil.getConnection();;
	}
/**
 * 得到有记录日期的集合
 * @param beginDay
 * @param overDay
 * @return
 */
	public List<Calendar> getHasRecordDays(Calendar beginDay, Calendar overDay) {
		// TODO Auto-generated method stub
		return getDaysList(beginDay,overDay,hasRecordDays);
	}
	
	public List<Calendar> getCeaseDays(Calendar beginDay, Calendar overDay) {
		// TODO Auto-generated method stub
		return getDaysList(beginDay,overDay,ceaseDays);
	}
	
	public List<Calendar> getLack(Calendar beginDay, Calendar overDay) {
		// TODO Auto-generated method stub
		return getDaysList(beginDay,overDay,lackSql);
	}
	private List<Calendar> getDaysList(Calendar beginDay, Calendar overDay,String sql) {
		// TODO Auto-generated method stub
	//	System.out.println("jyl "+sql+"\n"+MyCalendar.getString(beginDay)+"\n"+MyCalendar.getString(overDay));
		Connection con1=getCon();
		List <Calendar> daysList=new ArrayList<Calendar>();
			try {
				PreparedStatement pre=con1.prepareStatement(sql);
				if(beginDay!=null)
				pre.setDate(1, new java.sql.Date (beginDay.getTime().getTime()));
				if(overDay!=null)
				pre.setDate(2, new java.sql.Date (overDay.getTime().getTime()));
				ResultSet rs=pre.executeQuery();
			//	rs.first();
				while(rs.next()){
					Calendar cal=new GregorianCalendar();
					cal.setTime(rs.getDate(1));
				daysList.add(daysList.size(),cal);
					
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("daylistlength"+daysList.size());
			
		return daysList;
	}

	public void saveLack(List<Calendar> lack) {
		// TODO Auto-generated method stub
		/*
		 * 插入数据前需要先清空表
		 */
		Connection con1=getCon();
		try {
			con1.setAutoCommit(false);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		PreparedStatement pre;
		
		try {
			/*
			 * 必须先清空表
			 */
			pre = con1.prepareStatement(cleanLack);
			pre.execute();
			con1.commit();
			pre=con1.prepareStatement(saveLackSql);
			int i = 0;
			for(Calendar cal :lack){
				++i;
				pre.setDate(1,new java.sql.Date (cal.getTime().getTime()) );
				pre.addBatch();
				if(i%100==0)
				{
				pre.executeBatch();
				con1.commit();
				}
			}
			pre.executeBatch();
			con1.commit();
			con1.setAutoCommit(true);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
