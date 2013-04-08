package jyl.view.dao.imp;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

import jyl.util.JdbcUtil;
import jyl.util.Log4jLoader;
import jyl.util.MyCalendar;
import jyl.view.chart.entity.DayK;
import jyl.view.chart.entity.DayPrices;
import jyl.view.dao.PriceDao;

import org.apache.log4j.Logger;

public class PriceDaoJdbcImp implements PriceDao
{
	static Logger logger = Logger.getLogger(PriceDaoJdbcImp.class);
	public DayPrices getDayK(String stockCode, Date beginTime,Date overTime)
	{

		Connection con1 = JdbcUtil.getConnection();
		List <DayK> daysList=new ArrayList<DayK>();
		DayPrices dp = new DayPrices();
		float highest=0f;
		float lowest=0f;
		dp.setContainer(daysList);
		String before = "select * from tdxdata.";
		String after = " where changeTime between ? and ? order by changeTime desc";
		
			try {
				PreparedStatement pre=con1.prepareStatement(before+stockCode+after);
				if(beginTime!=null)
				pre.setDate(1, beginTime);
				if(overTime!=null)
				pre.setDate(2, overTime);
				ResultSet rs=pre.executeQuery();
			//	rs.first();
				int i = 0;
				int highestNum = 0 ;
				int lowestNum = 0;
				while(rs.next()){
					i++;
					Calendar cal=new GregorianCalendar();
					cal.setTime(rs.getDate(1));
					 float open = rs.getFloat(2) ;
					 float close = rs.getFloat(3);
					 float high = rs.getFloat(4) ;
					 float low  = rs.getFloat(5);
					 long volumn = rs.getLong(6);
					 if(high>highest)
					 {
						 highest = high;
						 highestNum = i;
					 }
					 if(low<lowest)
					 {
						 lowest = low;
						 lowestNum = i;
					 }
					 DayK dk = new DayK(cal,open,close,high,low,volumn);
					 daysList.add(dk);
				}
				dp.setHighest(highest);
				dp.setLowest(lowest);
				dp.setHighestNum(highestNum);
				dp.setLowestNum(lowestNum);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			logger.info("daylistlength"+daysList.size());
			
		return dp;
	}

	public void getDayKLimit(String stockCode, Date beginTime,int limit,DayPrices dp)
	{

		Connection con1 = JdbcUtil.getConnection();
		HashMap<Calendar,DayK> containerExtend = dp.getContainerExtend();
		List <DayK> daysList=dp.getContainer();
		//dp.setContainer(daysList);
		String before = "select * from tdxdata.";
		String after = " where changeTime <? order by changeTime desc limit ?";
		
			try {
				String sql = before+stockCode+after;
				logger.info("sql"+sql);
				
				PreparedStatement pre=con1.prepareStatement(sql);
				if(beginTime!=null)
				pre.setDate(1, beginTime);
				pre.setInt(2, limit);
				ResultSet rs=pre.executeQuery();
			//	rs.first();
				while(rs.next()){
					Calendar cal=new GregorianCalendar();
					cal.setTime(rs.getDate(1));
					 float open = rs.getFloat(2) ;
					 float close = rs.getFloat(3);
					 float high = rs.getFloat(4) ;
					 float low  = rs.getFloat(5);
					 long volumn = rs.getLong(6);
					 DayK dk = new DayK(cal,open,close,high,low,volumn);
					 daysList.add(dk);
					 containerExtend.put(dk.getChangeTime(), dk);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			logger.info("daylistlength"+daysList.size());
			
		
	}
	
	public DayPrices getDayKDefault(String stockCode)
	{	/*
		 * 默认取得离当前时间最近的6个月的数据
		 */
		DayPrices dp = new DayPrices();
		float highest=0f;
		float lowest=0f;	
		Connection con1 = JdbcUtil.getConnection();
		List <DayK> daysList=new ArrayList<DayK>();
		HashMap<Calendar,DayK> containerExtend = new HashMap<Calendar,DayK>();
		dp.setContainerExtend(containerExtend);
		dp.setContainer(daysList);
		String before = "select * from tdxdata.";
		String after = " order by changeTime desc limit 100";
			try {
				String sql = before+stockCode+after;
				logger.info("sql"+sql);
				
				PreparedStatement pre=con1.prepareStatement(sql);
			/*	if(beginTime!=null)
				pre.setDate(1, beginTime);
				if(overTime!=null)
				pre.setDate(2, overTime);*/
				ResultSet rs=pre.executeQuery();
			//	rs.first();
				 int highestNum = 0 ;
					int lowestNum = 0;
					int i =0;
				while(rs.next()){
					i++;
					Calendar cal=new GregorianCalendar();
					cal.setTime(rs.getDate(1));
					 float open = rs.getFloat(2) ;
					 float close = rs.getFloat(3);
					 float high = rs.getFloat(4) ;
					 float low  = rs.getFloat(5);
					 long volumn = rs.getLong(6);
					
					 if(high>highest)
					 {
						 highest = high;
						 highestNum = i;
					 }
					 if(((low<lowest)&&(low>0))||(lowest==0))
					 {
						 lowest = low;
						 lowestNum = i;
					 }
					 DayK dk = new DayK(cal,open,close,high,low,volumn);
					 containerExtend.put(dk.getChangeTime(), dk);
					 daysList.add(dk);
				}
				dp.setHighest(highest);
				dp.setLowest(lowest);
				dp.setHighestNum(highestNum);
				dp.setLowestNum(lowestNum);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			logger.info("daylistlength"+daysList.size());
			
		return dp;
	}
	public static void main(String args[])
	{
		Log4jLoader.loaddefault();
		PriceDaoJdbcImp tdj = new PriceDaoJdbcImp();
		
		//DayPrices dp= tdj.getDayKLimit("999999",new Date(2009-1900,02,11),10);
		//List<DayK> dk = dp.getContainer();
		/*List<DayK> dk = tdj.getDayKDefault("999999").getContainer();
		for(DayK day :dk)
		{
			logger.info(MyCalendar.getString(day.getChangeTime())+"/t"+day.getHigh()+"/t"+day.getClose());
		}*/
		//tdj.insertdata("E:\\stock\\tdxdata\\999999.TXT");
	}

	public void getWidowData(String stockCode, Calendar changeTime,
			String[] dataNames, DayPrices now) {
		// TODO Auto-generated method stub
		
		Connection con1 = JdbcUtil.getConnection();
		String sqlTemp = "select changetime,";  //必须有时间,用时间进行匹配
		int paramLength = dataNames.length-1;
		if("999999".equals(stockCode))//
		{
			for(int i = 0;i<= paramLength;i++ )
			{
				if(i<paramLength)
				sqlTemp = sqlTemp +" "+dataNames[i] +" , ";
				else
					sqlTemp = sqlTemp +dataNames[i];
			}
			sqlTemp = sqlTemp+"  from "+"sse.record where changetime >= "+MyCalendar.getString(changeTime)+" order by changeTime desc ";
			
		}else //如果是上证指数以外的其他股票代码
		{
			
		}
		try {
			Statement pre=con1.createStatement();
			 ResultSet rs = pre.executeQuery(sqlTemp);
			 HashMap<Calendar,DayK> containerExtend = now.getContainerExtend();
			 while(rs.next()){
					Calendar cal=new GregorianCalendar();
					cal.setTime(rs.getDate(1));
					DayK dk = containerExtend.get(cal);
					HashMap<String, Float> datas = dk.getExtendDatas();
					for(String name :dataNames)
					{
						Float value = rs.getFloat(name);
					datas.put(name, value);
					}
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.info("SQLException",e);
		}
		logger.info("sqlTemp : "+sqlTemp);
			}
	
}
