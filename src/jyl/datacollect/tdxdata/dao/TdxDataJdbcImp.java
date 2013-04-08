package jyl.datacollect.tdxdata.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import jyl.datacollect.tdxdata.concretedata.entity.DayConcreteEntity;
import jyl.util.JdbcUtil;
import jyl.util.Log4jLoader;
import jyl.util.MyCalendar;
import jyl.view.chart.entity.DayK;

import org.apache.log4j.Logger;

public class TdxDataJdbcImp
{
	String insertBeforeStr = "insert into tdxdata.";
	String insertAfterStr = " values (?,?,?,?,?,?)";
	String []params ={ 
		"totalVolumn",
	   "totalAmount",
	   "totalChangeNumber",
	   "perChangeAmount",
	   "totalBigBuy",
	   "totalBigSell",
	  	"totalOherBuy",
	   "totalOherSell",
	   "totalSmallBuy",
	   "totalSmallSell",
	   "totalBigBuyRatio",
	   "totalBigSellRatio",
	   "totalOherBuyRatio",
	   "totalOherSellRatio",
	   "totalSmallBuyRatio",
	   "totalSmallSellRatio",
	   "totalBuyRatio",
	   "totalSellRatio",
	   "open",
	   "close",
	   "netBigBuyPercent"};
	static Logger logger = Logger.getLogger(TdxDataJdbcImp.class);
	public void insertdata(String url) {
		// TODO Auto-generated method stub
		Connection con1=JdbcUtil.getConnection();
		try {
			con1.setAutoCommit(false);		
		File f = new File(url);
		String fileName = f.getName().replace(".TXT", "");
		PreparedStatement pre = null;
	
			pre = con1.prepareStatement(insertBeforeStr+fileName+insertAfterStr);
	
			InputStreamReader isr = new InputStreamReader(new FileInputStream(f),"GBK");
			BufferedReader bfr = new BufferedReader(isr);
		//	bfr.readLine();
		//	bfr.readLine();
			//bfr.readLine();
			//bfr.readLine();
		//	bfr.readLine();//跳过前面三行
			int i = 0;
			while(bfr.ready())
			{   i++;
				String temp = bfr.readLine();
				System.out.println(temp);
				String datas [] = temp.trim().split("\\s+");
				System.out.println("length "+datas.length);
				java.sql.Date changeDate = MyCalendar.transToDate1(datas[0].trim());
				System.out.println("date "+changeDate);
				if(changeDate == null)
					continue;
				float open = Float.parseFloat(datas[1].trim());
				float high = Float.parseFloat(datas[2].trim());
				float low = Float.parseFloat(datas[3].trim());
				float close = Float.parseFloat(datas[4].trim());
				long volumn = Long.parseLong(datas[5].trim());
				pre.setDate(1,changeDate);
				pre.setFloat(2, open);
				pre.setFloat(3, close);
				pre.setFloat(4, high);
				pre.setFloat(5, low);
				pre.setLong(6, volumn);
				pre.addBatch();
				if(i%100==0)
				{
				pre.executeBatch();
				
				}
			}
			pre.executeBatch();
			con1.commit();
		} 
		catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		 catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				con1.setAutoCommit(true);
				con1.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public List<DayK> getDayK (String stockCode, Date beginTime,Date overTime)
	{
		Connection con1 = JdbcUtil.getConnection();
		List <DayK> daysList=new ArrayList<DayK>();
		String before = "select * from tdxdata.";
		String after = " where changeTime between ? and ?";
			try {
				PreparedStatement pre=con1.prepareStatement(before+stockCode+after);
				if(beginTime!=null)
				pre.setDate(1, beginTime);
				if(overTime!=null)
				pre.setDate(2, overTime);
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
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("daylistlength"+daysList.size());
			
		return daysList;
	}
	
	public static void main(String args[])
	{
		Log4jLoader.loaddefault();
		TdxDataJdbcImp tdj = new TdxDataJdbcImp();
		tdj.setBlockName();
		/*List <String>re = tdj.getBlockCode();
		for(String day :re)
		{
			logger.info("re"+day);
		}*/
		/*List<DayK> dk = tdj.getDayK("999999",MyCalendar.transToDate1("2008/05/01") , MyCalendar.transToDate1("2008/06/01"));
		for(DayK day :dk)
		{
			logger.info(MyCalendar.getString(day.getChangeTime())+"/t"+day.getHigh()+"/t"+day.getClose());
		}*/
		//tdj.insertdata("E:\\stock\\tdxdata\\999999.TXT");
	}

	
	private void setBlockName() {
		// TODO Auto-generated method stub
		String sqlStr1 = "select stockcode from tdxdataanalysis.szczandcybz";
		String sqlStr2 = "update tdxdataanalysis.szagblock set blockname = 'szcz' where stockcode = ?";
		ArrayList <String> szczandcybz = new ArrayList<String>();
		Connection con1=JdbcUtil.getConnection();
		PreparedStatement pre = null;
		try {
			pre = con1.prepareStatement(sqlStr1);
			ResultSet rs = pre.executeQuery();
			//	rs.first();
			while (rs.next()) {

				String stockcode = rs.getString(1);

				szczandcybz.add(stockcode);
			}
			pre = con1.prepareStatement(sqlStr2);
			con1.setAutoCommit(false);
			int i = 0;
			for(String str : szczandcybz )
			{
				i++;
				pre.setString(1, str);
				pre.addBatch();
				if (i % 100 == 0) {
					pre.executeBatch();
				}
			}
			pre.executeBatch();
			con1.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void insertblockdata(Set<HashMap<String, String>> stocks, String string) {
		// TODO Auto-generated method stub
		logger.info("insertblockdata");
		//String sqlstr = "insert into tdxdataanalysis.szczandcybz values (?,?,?)";
		String sqlstr = "insert into "+string+" values (?,?)";
		Connection con1=JdbcUtil.getConnection();
		
			try {
				con1.setAutoCommit(false);
				PreparedStatement pre = null;
				pre = con1.prepareStatement(sqlstr);
				int i = 0;
				for (HashMap<String, String> stock : stocks)
				{
					/*
					 * oneRowData.put("stockCode",temp1[0]);
					oneRowData.put("stockName",new String(temp1[1].getBytes(),"utf-8"));
					oneRowData.put("commuteVolumn"
					 */
					i++;
				//	System.out.println("entry "+entry.getKey()+" "+entry.getValue());
					pre.setString(1, stock.get("stockCode"));
					logger.info("stockCode "+stock.get("stockCode")+"stockName : "+stock.get("stockName")+" volumn : "+stock.get("commuteVolumn"));
					
				pre.setString(2,stock.get("stockName"));// "测试");//
			//	pre.setFloat(3,3.3f);
				
				//	pre.setFloat(3,Float.parseFloat(stock.get("commuteVolumn")));
					pre.addBatch();
					if (i % 100 == 0) {
						pre.executeBatch();

					}
				}
				pre.executeBatch();
				con1.commit();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			try {
				con1.setAutoCommit(true);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	public Set<String> getBlockCode() {
		// TODO Auto-generated method stub
		Set<String> blockecode = new HashSet<String>();
	//	String sqlstr = "select stockcode from  tdxdataanalysis.szczandcybz  ";
		String sqlstr = "select stockcode from  tdxdataanalysis.szagblock where isworking ='0'  ";
		
		Connection con1=JdbcUtil.getConnection();
		try {
			PreparedStatement pre = con1.prepareStatement(sqlstr);
			ResultSet rs = pre.executeQuery();
			//	rs.first();
			while (rs.next()) {

				String stockcode = rs.getString(1);

				blockecode.add(stockcode);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return blockecode;
	}

	public void insertConcreteData(Calendar c1,
			HashMap<String, List<Float>> container) {
		// TODO Auto-generated method stub
		java.sql.Date date = new Date(c1.getTime().getTime());
	//	String sqlStr = "insert into tdxdataanalysis.concretedata values(";
		String sqlStr = "replace into tdxdataanalysis.concretedata_szag values(";
		
		for(int i=0;i<23;i++)
		{
			if(i<22)
				sqlStr = sqlStr+" ?, ";
			else 
				sqlStr = sqlStr+" ? ,?,-1,?,?)";
		}
		Set<Map.Entry<String, List<Float>>> set = container.entrySet();
		Connection con1=JdbcUtil.getConnection();
		
		try {
			con1.setAutoCommit(false);
			PreparedStatement pre = null;
			pre = con1.prepareStatement(sqlStr);
			int j = 0;
			for(Map.Entry<String, List<Float>> entry : set)
			{
				String stockCode = entry.getKey();
				List<Float> stockData = entry.getValue();	
			//	Set<Map.Entry<String, Float>> datas = stockData.entrySet();
				pre.setDate(1, date);
				pre.setString(2, stockCode);
				logger.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~stockCode : "+stockCode+"\n\n");
				int i =3;
				for( Float data: stockData){
					pre.setFloat(i, data);
				//	logger.info("key : "+data.getKey()+" value : "+data.getValue());
					i++;
				}
				pre.addBatch();
				j++;
				if (j % 100 == 0) {
					pre.executeBatch();
				}
			}
			pre.executeBatch();
			con1.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		 
	}

	public void getOneDayData(Date date, HashMap<String, DayConcreteEntity> currentDayDatas, ArrayList<DayConcreteEntity> currentDayDatasArr) {
		// TODO Auto-generated method stub
	//	HashMap<String,DayConcreteEntity> currentDayDatas =new HashMap<String,DayConcreteEntity> ();
		String sqlStr = "select * from tdxdataanalysis.concretedata where changetime = ? and totalVolumn >0 ";
		
		Connection con1=JdbcUtil.getConnection();
		try {
			PreparedStatement pre = con1.prepareStatement(sqlStr);
			pre.setDate(1, date);
			ResultSet rs=pre.executeQuery();
			//	rs.first();
				while(rs.next()){
					 String stockCode = rs.getString("stockCode");
					 DayConcreteEntity de = new DayConcreteEntity();
					 de.setStockCode(stockCode);
					 HashMap<String,Float> datas = de.getDatas();
					 for(String str : params )
					 {
						 datas.put(str, rs.getFloat(str));
					 }
					 currentDayDatas.put(stockCode, de);
					 currentDayDatasArr.add(de);
					 
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void getOpenClose(HashMap<String, DayConcreteEntity> currentDayDatas, Date date, Date lastDay) {
		// TODO Auto-generated method stub
		String sqlStr = "select changetime,stockcode,open,close from tdxdataanalysis.concretedata where changetime >=? and changetime <=? ";	
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date);
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(lastDay);
		logger.info("cal1"+MyCalendar.getString(cal1));
		logger.info("cal2"+MyCalendar.getString(cal2));
		Connection con1=JdbcUtil.getConnection();
		try {
			PreparedStatement pre = con1.prepareStatement(sqlStr);
			pre.setDate(1, date);
			pre.setDate(2, lastDay);
			ResultSet rs=pre.executeQuery();
			//	rs.first();
				while(rs.next()){
					 String stockCode = rs.getString("stockCode");
					 DayConcreteEntity de = currentDayDatas.get(stockCode);
					 if(de!=null){
					 Float open =  rs.getFloat("open");
					 Float close =  rs.getFloat("close");
				//	 logger.info("close "+close);
					 java.sql.Date dateTem = rs.getDate("changetime");
					 Calendar cal = Calendar.getInstance();
					 cal.setTime(dateTem) ;
					// logger.info("dateTem "+dateTem.getTime());
					// logger.info("cal "+cal.getTime().getTime());
					 de.getOpenPrice().put(cal, open);
					 de.getClosePrice().put(cal, close);
					 }
					 
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public HashMap<String, Float> getComuteVolume() {
		// TODO Auto-generated method stub
		HashMap<String, Float> blockecode = new HashMap<String, Float>();
		String sqlstr = "select stockcode,commutevolumn from  tdxdataanalysis.szagblock  ";
		Connection con1=JdbcUtil.getConnection();
		try {
			PreparedStatement pre = con1.prepareStatement(sqlstr);
			ResultSet rs = pre.executeQuery();
			//	rs.first();
			while (rs.next()) {

				String stockcode = rs.getString(1);
				Float volumn = rs.getFloat(2);
				blockecode.put(stockcode,volumn);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return blockecode;
	}

	/*
	 * 得到所有符合初步条件的数据
	 */
	public void getAllDatas(float netBigBuyPercentLevel,
			float totalBigBuyRatio,
			HashMap<String, HashMap<Calendar, HashMap<String, Float>>> allDatas, Date beginDate, Date overDay, boolean chooseAllDays) {
		// TODO Auto-generated method stub
		String sqlTemp = "select changetime,stockcode,netBigBuyPercent,totalBigBuyRatio,close from " +
	//	" tdxdataanalysis.concretedata  ";		
		" tdxdataanalysis.concretedata_szag ";
		if(!chooseAllDays){
			sqlTemp =sqlTemp+"where changetime>=? and changetime<=? ";
				}
		Connection con1=JdbcUtil.getConnection();
		try {
			PreparedStatement pre = con1.prepareStatement(sqlTemp);
			if(!chooseAllDays){
				pre.setDate(1, beginDate);
				pre.setDate(2, overDay);
					}
				
			ResultSet rs=pre.executeQuery();
			//	rs.first();
				while(rs.next()){
					 String stockCode = rs.getString("stockCode");
					 HashMap<Calendar,HashMap<String,Float>> map1 = allDatas.get(stockCode);
						if(map1 ==null){
							map1 = new HashMap<Calendar,HashMap<String,Float>>();
							allDatas.put(stockCode, map1);
						}
						java.sql.Date changeDate = rs.getDate("changetime");
						//System.out.println ("changeDate"+changeDate);
						Calendar changeTime = Calendar.getInstance();
						changeTime.setTime(changeDate);
						HashMap<String,Float> map2 = map1.get(changeTime);
						if(map2==null)
						{
							map2 = new HashMap<String,Float>();
							map1.put(changeTime,map2);
						}
					 Float close =  rs.getFloat("close");
					 Float nbp =  rs.getFloat("netBigBuyPercent");
					 Float tbb =  rs.getFloat("totalBigBuyRatio");
					 map2.put("netBigBuyPercent", nbp);
					 map2.put("totalBigBuyRatio", tbb);
					 map2.put("close", close); 
					 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void insertBuySellCompare(Calendar c1, HashMap<String, List<Float>> container) {
		// TODO Auto-generated method stub
		java.sql.Date date = new Date(c1.getTime().getTime());
		//	String sqlStr = "insert into tdxdataanalysis.concretedata values(";
			String sqlStr = "insert into tdxbuysellcompare.concretedata_szag values(";
			
			for(int i=0;i<11;i++)
			{
				if(i<10)
					sqlStr = sqlStr+" ?, ";
				else 
					sqlStr = sqlStr+" ? ,'-1','-1')";//-1代表没有数据,涨跌百分比
			}
			logger.info("sqlStr"+sqlStr);
			Set<Map.Entry<String, List<Float>>> set = container.entrySet();
			Connection con1=JdbcUtil.getConnection();
			
			try {
				con1.setAutoCommit(false);
				PreparedStatement pre = null;
				pre = con1.prepareStatement(sqlStr);
				int j = 0;
				for(Map.Entry<String, List<Float>> entry : set)
				{
					String stockCode = entry.getKey();
					List<Float> stockData = entry.getValue();	
				//	Set<Map.Entry<String, Float>> datas = stockData.entrySet();
					pre.setDate(2, date);
					pre.setString(1, stockCode);
					logger.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~stockCode : "+stockCode+"\n\n");
					int i =3;
					for( Float data: stockData){
						pre.setFloat(i, data);
					//	logger.info("key : "+data.getKey()+" value : "+data.getValue());
						i++;
					}
					pre.addBatch();
					j++;
					if (j % 100 == 0) {
						pre.executeBatch();
					}
				}
				pre.executeBatch();
				con1.commit();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	}

	public void getdatasForBuySellUpD(HashMap<String, HashMap<Calendar, HashMap<String, Float>>> allDatas, String tableName, String beginDate) {
		// TODO Auto-generated method stub
		String sqlTemp = "select changetime,stockcode,close from " +tableName+" where changetime >='"+beginDate+"'";
		//	" tdxdataanalysis.concretedata  ";		
		//	" tdxbuysellcompare.concretedata_szag  ";
			Connection con1=JdbcUtil.getConnection();
			try {
				PreparedStatement pre = con1.prepareStatement(sqlTemp);
			//	pre.setFloat(1, netBigBuyPercentLevel);
				//pre.setFloat(2, totalBigBuyRatio);
				ResultSet rs=pre.executeQuery();
				//	rs.first();
					while(rs.next()){
						 String stockCode = rs.getString("stockCode");
						 HashMap<Calendar,HashMap<String,Float>> map1 = allDatas.get(stockCode);
							if(map1 ==null){
								map1 = new HashMap<Calendar,HashMap<String,Float>>();
								allDatas.put(stockCode, map1);
							}
							java.sql.Date changeDate = rs.getDate("changetime");
							Calendar changeTime = Calendar.getInstance();
							changeTime.setTime(changeDate);
							HashMap<String,Float> map2 = map1.get(changeTime);
							if(map2==null)
							{
								map2 = new HashMap<String,Float>();
								map1.put(changeTime,map2);
							}
						 Float close =  rs.getFloat("close");
						 map2.put("close", close); 
						 }
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	public void setBuySellUpDowns(
			HashMap<String, HashMap<Calendar, HashMap<String, Float>>> allDatas, String tableName) {
		// TODO Auto-generated method stub
		String sqlTemp = "update "+tableName+" set upAndDownThisDay = ?," +
				"upAndDownNextDay = ? where stockcode = ? and changetime = ? " ;
			Connection con1=JdbcUtil.getConnection();
			
			try {
				con1.setAutoCommit(false);
				PreparedStatement pre = con1.prepareStatement(sqlTemp);
					Set<Entry<String, HashMap<Calendar, HashMap<String, Float>>>> dataset = allDatas.entrySet();
					int i = 0;
					for(Entry<String, HashMap<Calendar, HashMap<String, Float>>> data:dataset)
					{
						i++;
						String stockcode = data.getKey();
						Set<Entry<Calendar, HashMap<String, Float>>> dataset1 = data.getValue().entrySet();
						for(Entry<Calendar, HashMap<String, Float>> data1 : dataset1 )
						{
							java.sql.Date changeTime = new Date(data1.getKey().getTime().getTime());
							System.out.println(stockcode+MyCalendar.getString(data1.getKey()));
							pre.setFloat(1, data1.getValue().get("upAndDownThisDay")!=null?data1.getValue().get("upAndDownThisDay") :-1f);
							pre.setFloat(2, data1.getValue().get("upAndDownNextDay")!=null?data1.getValue().get("upAndDownNextDay") :-1f);
							pre.setDate(4, changeTime);
							pre.setString(3, stockcode);
							pre.addBatch();
							pre.addBatch();
							if(i%100==0)
								pre.executeBatch();
						}
						pre.executeBatch();
						con1.commit();
					}
					
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
			
	}

	public void insertHistroryDatas(String string,
			HashMap<Date, List<Float>> container) {
		// TODO Auto-generated method stub
		String sqlStr = "replace into tdxhistory.data_szag values(";
		
		for(int i=0;i<17;i++)
		{
			if(i<16)
				sqlStr = sqlStr+" ?, ";
			else 
				sqlStr = sqlStr+" ? ,'-1','-1','-1','-1','-1','-1','-1')";//-1代表没有数据,涨跌百分比
		}
		logger.info("sqlStr"+sqlStr);
		Set<Map.Entry<Date, List<Float>>> set = container.entrySet();
		Connection con1=JdbcUtil.getConnection();
		
		try {
			con1.setAutoCommit(false);
			PreparedStatement pre = null;
			pre = con1.prepareStatement(sqlStr);
			int j = 0;
			for(Map.Entry<Date, List<Float>> entry : set)
			{
			//	String stockCode = entry.getKey();
				List<Float> stockData = entry.getValue();	
			//	Set<Map.Entry<String, Float>> datas = stockData.entrySet();
				pre.setDate(2, entry.getKey());
				pre.setString(1, string);
				int i =3;
				for( Float data: stockData){
					pre.setFloat(i, data);
				//	logger.info("key : "+data.getKey()+" value : "+data.getValue());
					i++;
				}
				pre.addBatch();
				j++;
				if (j % 100 == 0) {
					pre.executeBatch();
				}
			}
			pre.executeBatch();
			con1.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void insertHistroryDatas(Calendar c1,
			HashMap<String, List<Float>> container1,String tableName) {
		// TODO Auto-generated method stub
		java.sql.Date date = new java.sql.Date(c1.getTime().getTime());
String sqlStr = "replace into "+tableName+" values(";
		
		for(int i=0;i<17;i++)
		{
			if(i<16)
				sqlStr = sqlStr+" ?, ";
			else 
				sqlStr = sqlStr+" ? ,'-1','-1','-1','-1','-1','-1','-1')";//-1代表没有数据,涨跌百分比
		}
		logger.info("sqlStr"+sqlStr);
		Set<Map.Entry<String, List<Float>>> set = container1.entrySet();
		Connection con1=JdbcUtil.getConnection();
		
		try {
			con1.setAutoCommit(false);
			PreparedStatement pre = null;
			pre = con1.prepareStatement(sqlStr);
			int j = 0;
			for(Map.Entry<String, List<Float>> entry : set)
			{
			  String stockCode = entry.getKey();
				List<Float> stockData = entry.getValue();	
			//	Set<Map.Entry<String, Float>> datas = stockData.entrySet();
				pre.setDate(2, date);
				pre.setString(1, entry.getKey());
				int i =3;
				logger.info("stockCode : "+stockCode);
				for( Float data: stockData){
					pre.setFloat(i, data);
				//	logger.info("key : "+data.getKey()+" value : "+data.getValue());
					i++;
				}
				pre.addBatch();
				j++;
				if (j % 100 == 0) {
					pre.executeBatch();
				}
			}
			pre.executeBatch();
			con1.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("jyl",e);
		}
	}

	public void getTotalDownDatasOneStock(String stockCode,HashMap<Date, List<Float>> datas, List<Date> dateList) {
		// TODO Auto-generated method stub
		String sqlTemp = "select changetime,totalDown from " +
		//	" tdxdataanalysis.concretedata  ";		
			"  tdxhistory.data_szag where stockcode = ?  order by changetime asc";
			Connection con1=JdbcUtil.getConnection();
			try {
				PreparedStatement pre = con1.prepareStatement(sqlTemp);
			//	pre.setFloat(1, netBigBuyPercentLevel);
				//pre.setFloat(2, totalBigBuyRatio);
				pre.setString(1, stockCode);
				ResultSet rs=pre.executeQuery();
				//	rs.first();
					while(rs.next()){
							java.sql.Date changeDate = rs.getDate("changetime");
							List<Float> da = new ArrayList<Float>();
							dateList.add(changeDate);
							Float totalDown =  rs.getFloat("totalDown");
							da.add(totalDown);
							datas.put(changeDate, da);
						}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	}

	public void setTotalDownDatasOneStock(String stockCode,
			HashMap<Date, List<Float>> datas) {
		// TODO Auto-generated method stub
		String sqlTemp = "update tdxhistory.data_szag set totalDown10=?,"+
				"totalDown20=?, totalDown50=?, totalDown100=?,totalDown250 = ?" +
		" where stockcode = ? and changetime = ? " ;
	Connection con1=JdbcUtil.getConnection();
	try {
		PreparedStatement pre = con1.prepareStatement(sqlTemp);
			Set<Entry<Date, List<Float>>>dataset1 = datas.entrySet();
			int j = 0;
				for(Entry<Date, List<Float>> data1 : dataset1 )
				{
					List <Float> dayDatas = data1.getValue();
					for(int i=1;i<dayDatas.size();i++)
					{
						pre.setFloat(i, dayDatas.get(i));
						
					}
					pre.setDate(7, data1.getKey());
					pre.setString(6, stockCode);
					pre.addBatch();
					pre.addBatch();
					j++;
					if(j%100==0)
						pre.executeBatch();
				}
			
			pre.executeBatch();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
	
	}

	public List<String> getBlockCode(String string) {
		// TODO Auto-generated method stub
		List blockecode = new ArrayList();
		//	String sqlstr = "select stockcode from  tdxdataanalysis.szczandcybz  ";
			String sqlstr = "select stockcode from  tdxdataanalysis."+string+"  where hasComplete ='f' ";
			
			Connection con1=JdbcUtil.getConnection();
			try {
				PreparedStatement pre = con1.prepareStatement(sqlstr);
				ResultSet rs = pre.executeQuery();
				//	rs.first();
				while (rs.next()) {

					String stockcode = rs.getString(1);

					blockecode.add(stockcode);
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
			return blockecode;
	}

	public void setHs300Dealed(String stockCode) {
		// TODO Auto-generated method stub
		String sqlstr = "update    tdxdataanalysis.hs300  set hasComplete ='t' where stockcode=? ";
		logger.info("sqlstr : "+sqlstr);
		Connection con1=JdbcUtil.getConnection();
		try {
			PreparedStatement pre = con1.prepareStatement(sqlstr);
			pre.setString(1, stockCode);
			//	rs.first();
			pre.execute();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void insertConcreteDataBS(Calendar c1,
			HashMap<String, List<Float>> container) {
		// TODO Auto-generated method stub
		java.sql.Date date = new Date(c1.getTime().getTime());
		//	String sqlStr = "insert into tdxdataanalysis.concretedata values(";
		String sqlStr = "insert into tdxbuysellcompare.concretedata_szag values(";
		
		for(int i=0;i<11;i++)
		{
			if(i<10)
				sqlStr = sqlStr+" ?, ";
			else 
				sqlStr = sqlStr+" ? ,'-1','-1')";//-1代表没有数据,涨跌百分比
		}
			Set<Map.Entry<String, List<Float>>> set = container.entrySet();
			Connection con1=JdbcUtil.getConnection();
			
			try {
				con1.setAutoCommit(false);
				PreparedStatement pre = null;
				pre = con1.prepareStatement(sqlStr);
				int j = 0;
				for(Map.Entry<String, List<Float>> entry : set)
				{
					String stockCode = entry.getKey();
					List<Float> stockData = entry.getValue();	
				//	Set<Map.Entry<String, Float>> datas = stockData.entrySet();
					pre.setDate(1, date);
					pre.setString(2, stockCode);
					logger.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~stockCode : "+stockCode+"\n\n");
					int i =3;
					for( Float data: stockData){
						pre.setFloat(i, data);
					//	logger.info("key : "+data.getKey()+" value : "+data.getValue());
						i++;
					}
					pre.addBatch();
					j++;
					if (j % 100 == 0) {
						pre.executeBatch();
					}
				}
				pre.executeBatch();
				con1.commit();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			//jyl
			/*java.sql.Date date = new Date(c1.getTime().getTime());
			//	String sqlStr = "insert into tdxdataanalysis.concretedata values(";
				String sqlStr = "insert into tdxbuysellcompare.concretedata_szag values(";
				
				for(int i=0;i<11;i++)
				{
					if(i<10)
						sqlStr = sqlStr+" ?, ";
					else 
						sqlStr = sqlStr+" ? ,'-1','-1')";//-1代表没有数据,涨跌百分比
				}
				logger.info("sqlStr"+sqlStr);
				Set<Map.Entry<String, List<Float>>> set = container.entrySet();
				Connection con1=JdbcUtil.getConnection();
				
				try {
					con1.setAutoCommit(false);
					PreparedStatement pre = null;
					pre = con1.prepareStatement(sqlStr);
					int j = 0;
					for(Map.Entry<String, List<Float>> entry : set)
					{
						String stockCode = entry.getKey();
						List<Float> stockData = entry.getValue();	
					//	Set<Map.Entry<String, Float>> datas = stockData.entrySet();
						pre.setDate(2, date);
						pre.setString(1, stockCode);
						logger.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~stockCode : "+stockCode+"\n\n");
						int i =3;
						for( Float data: stockData){
							pre.setFloat(i, data);
						//	logger.info("key : "+data.getKey()+" value : "+data.getValue());
							i++;
						}
						pre.addBatch();
						j++;
						if (j % 100 == 0) {
							pre.executeBatch();
						}
					}
					pre.executeBatch();
					con1.commit();
			
	}
	*/
}

	public Set<String> getCeaseDayThisYear() {
		// TODO Auto-generated method stub
		/*
		 * jyltodo 有时间应该加上单元测试的内容
		 */
		Calendar today = Calendar.getInstance();
		today.set(today.get(Calendar.YEAR), 0, 1);
		logger.info("firstday"+MyCalendar.getString(today));
		String sqlstr = "select ceaseday from  tdxdataanalysis.ceaseday where ceaseday>=?";
		java.sql.Date firstDay =new java.sql.Date(today.getTimeInMillis());
		Connection con1=JdbcUtil.getConnection();
		 Set<String> result = new HashSet<String>();
		try {
			PreparedStatement pre = con1.prepareStatement(sqlstr);
			pre.setDate(1, firstDay);
			ResultSet rs = pre.executeQuery();
			//	rs.first();
			while (rs.next()) {

				today.setTime(rs.getDate(1));
			//	logger.info("ceaseday:"+MyCalendar.getString(today)+" "+
				//		today.get(Calendar.HOUR_OF_DAY)+" "+today.get(Calendar.MINUTE)+" "+today.get(Calendar.MILLISECOND));
				result.add(MyCalendar.getStringEight(today));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		logger.info("size : "+result.size());
		return result;
	}

	public HashMap<String, Float> getCloseDataYes(Calendar yes) {
		// TODO Auto-generated method stub
		HashMap<String, Float> closeDatas = new HashMap<String, Float>();
		String sqlstr = "select stockcode, close from tdxdataanalysis.concretedata_szag where changetime = ?";
		logger.info("date:"+MyCalendar.getString(yes));
		java.sql.Date day =new java.sql.Date(yes.getTimeInMillis());
		Connection con1=JdbcUtil.getConnection();
		PreparedStatement pre;
		try {
			pre = con1.prepareStatement(sqlstr);
			pre.setDate(1, day);
			ResultSet rs = pre.executeQuery();
			//	rs.first();
			while (rs.next()) {
				closeDatas.put(rs.getString(1),rs.getFloat(2));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return closeDatas;
	}
}
