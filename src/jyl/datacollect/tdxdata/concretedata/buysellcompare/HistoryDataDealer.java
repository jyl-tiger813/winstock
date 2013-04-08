package jyl.datacollect.tdxdata.concretedata.buysellcompare;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import jyl.datacollect.tdxdata.dao.TdxDataJdbcImp;
import jyl.datacollect.tdxdata.entity.HistoryData;
import jyl.util.MyCalendar;

import org.apache.log4j.Logger;

public class HistoryDataDealer {

	/**
	 * @param args
	 */
	static Logger logger = Logger.getLogger(HistoryDataDealer.class);
	String dayStr = "20100726";
	HashMap<Date,List<Float>> container = new HashMap<Date,List<Float>> ();
	HashMap<String,List<Float>> container1;
	String storeFile = "E:\\stock\\tdxdata\\history";
	
	String stockName;
	private TdxDataJdbcImp tjd =  new TdxDataJdbcImp();;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HistoryDataDealer hdd = new HistoryDataDealer();
		//hdd.dealStock("000651");
		hdd.dealStockAll();
	}

	private void dealStockAll() {
		// TODO Auto-generated method stub
	//	storeFile ="E:\\stock\\tdxdata\\history";
		storeFile = "E:\\stock\\tdxdata\\concreteszag";
		 dayStr = "20100816";
		Calendar c1 =  MyCalendar.createCalendar("2010-09-11");
			TdxDataJdbcImp tjd = new TdxDataJdbcImp();
			while(c1.after(MyCalendar.createCalendar("2010-08-08"))){
				c1 = MyCalendar.backDayNotWeekEnd(c1);
				dayStr = MyCalendar.getStringEight(c1);
				container1 = new HashMap<String,List<Float>> ();
				getData();	
			//TdxDataJdbcImp tjd = new TdxDataJdbcImp();
			tjd.insertHistroryDatas(c1,container1,"tdxhistory.data_szag1");
			}
	}
/*
 * 某日多支股票
 */
	private void getData() {
		// TODO Auto-generated method stub
		File baseFile = new File(storeFile);
		
		File dayFile = new File(baseFile,dayStr);
		System.out.println(dayStr);
		File [] files =dayFile.listFiles(new FilenameFilter(){

			@Override
			public boolean accept(File dir, String name) {
				// TODO Auto-generated method stub
				if(name.contains(".TXT"))
				return true;
				else 
				return false;
			}

			}); 
		for(File f : files)
		{
			getDataOneFile1(f);
		}
	}

	private void getDataOneFile1(File f) {
		// TODO Auto-generated method stub
		List<Float> data = new ArrayList<Float>();
		logger.info("fileName :" +f.getName());
		String stockCode = f.getName().replace(".TXT", "");
		container1.put(stockCode, data);
		try {
			FileInputStream fis = new FileInputStream(f);
			InputStreamReader fr = new InputStreamReader(fis,"GBK");
			BufferedReader bfr = new BufferedReader(fr);
			for(int i=0;i<5;i++){
//logger.info("bfr"+i+" : "+
					bfr.readLine();
			}
			float buyvolumn = 0.0f;
			float sellvolumn = 0.0f;
			float buyvolumnRatio = 0.0f;
			float totalvolumn = 0.0f; //成交总量,手数
			float close = 0.0f;
			String []datas = null;
			float upPrice = 0.0f;//上一条成交交易的价格
			float totalUp = 0.0f;
			float totalDown = 0.0f;
			float buyvolumn1 = 0.0f; //5%比例的大单
			float sellvolumn1 = 0.0f;
			float buyvolumn2 = 0.0f;//10%比例的大单
			float sellvolumn2 = 0.0f;
			float buyvolumn3 = 0.0f;//10%比例的小单
			float sellvolumn3 = 0.0f;
			float buyvolumn4 = 0.0f;//5%比例的小单
			float sellvolumn4 = 0.0f;
			if(bfr.ready())
			{
				String temp1 = bfr.readLine();
				String first [] =	temp1.split("[\\s]+");
			float firstPrice = Float.parseFloat(first[1]);
			upPrice = firstPrice;
			float totalAmount = 0.0f;
			List<HistoryData> hsd = new ArrayList<HistoryData>();
		//	Collections.sort(hsd);
			while(bfr.ready())
			{
				String temp = bfr.readLine();
				datas = temp.split("[\\s]+");
				float volum = Float.parseFloat(datas[2]);
				float nowPrice = Float.parseFloat(datas[1]);
				float upDown = nowPrice -upPrice;
				
				upPrice = nowPrice;
				totalvolumn = totalvolumn+volum;
				
					
				if (volum > 0)
				{	
					if (datas.length > 4 )
					{
						String bs = datas[4];
						HistoryData hd = new HistoryData();
						hd.setBs(bs);
						hd.setVolumn(volum);
						hsd.add(hd);
					}
						totalAmount = totalAmount + volum*nowPrice;
					    if(upDown>0.009)// 正股的最小变动单位为0.01元
					    {
						buyvolumn = buyvolumn + volum;
						totalUp= totalUp +upDown;
					    }
					    else if(upDown<-0.009)
					    {
						sellvolumn = sellvolumn + volum;
						totalDown = totalDown+upDown;
					    }
						}
				}
			
			close = Float.parseFloat(datas[1]);
			 buyvolumnRatio = (buyvolumn-sellvolumn)/totalvolumn;  //主动买与主动卖与总成交量的差值
			 Collections.sort(hsd);//按照降序进行排序
			 float conform1 = totalvolumn*0.05f;
			 float conform2 = totalvolumn*0.1f;			
			 float conform3 = totalvolumn*0.9f;			
			 float conform4 = totalvolumn*0.95f;	
			 float buyzone1 = 0;
			 float buyzone2 = 0;			
			 float buyzone3 = 0f;			
			 float buyzone4 = 0f;
			 float sellzone1 = 0f;
			 float sellzone2 = 0f;
			 float sellzone3 = 0f;
			 float sellzone4 = 0f;
			
			 float buy = 0;
			 for (HistoryData hd: hsd)
			 {
				
				 float volumn = hd.getVolumn();
				 String bs = hd.getBs();
				 System.out.println("volumn : "+volumn);
				 buy = buy +volumn;
				 if (buy < conform1) {
					 if("B".equals(bs))
						buyzone1 = buyzone1 + volumn;
					 else if("S".equals(bs))
						 sellzone1 = sellzone1 + volumn;
					} else if (buy < conform2 && buy > conform1) {
						 if("B".equals(bs))
						buyzone2 = buyzone2 + volumn;
						 else if("S".equals(bs))
							 sellzone2 = sellzone2 + volumn;
					} else if (buy > conform3 && buy<conform4) {
						 if("B".equals(bs))
						buyzone3 = buyzone3 + volumn;
						 else if("S".equals(bs))
							 sellzone3 = sellzone3 + volumn;
					} else if (buy > conform4) {
						 if("B".equals(bs))
						buyzone4 = buyzone4 + volumn;
						 else if("S".equals(bs))
							 sellzone4 = sellzone4 + volumn;
					} else {
						break;
					}
			 }
			 buyvolumn1 = buyzone1; //10%比例的大单
			 sellvolumn1 = sellzone1;
			 buyvolumn2 = buyzone2;//15%比例的大单
			 sellvolumn2 = sellzone2;
			 buyvolumn3 = buyzone3;//20%比例的大单
			 sellvolumn3 = sellzone3;
			 buyvolumn4 = buyzone4;//30%比例的大单
			 sellvolumn4 = sellzone4;
			}
			
			logger.info("close : "+close);
			if(close>0){
			totalUp =totalUp/close;
			totalDown = totalDown/close;
			
			}
			

			data.add( buyvolumn);
			data.add( sellvolumn);
			data.add( buyvolumnRatio);
			data.add( totalvolumn); //成交总量,手数
			data.add( close);
			data.add( totalUp);
			data.add( totalDown);
			data.add(buyvolumn1);
			data.add(sellvolumn1);
			data.add(buyvolumn2);
			data.add(sellvolumn2);
			data.add(buyvolumn3);
			data.add(sellvolumn3);
			data.add(buyvolumn4);
			data.add(sellvolumn4);
			
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void dealStock(String string) {
		// TODO Auto-generated method stub
		stockName = string;
		getDatas(string);
		insertDatas(string,container);
	}

	private void insertDatas(String string,
			HashMap<Date, List<Float>> container) {
		// TODO Auto-generated method stub
		TdxDataJdbcImp tjd = new TdxDataJdbcImp();
		tjd.insertHistroryDatas(string,container);
	}

	/*
	 * 某支股票某段时间
	 */
	private void getDatas( final String string) {
		// TODO Auto-generated method stub
		File baseFile = new File(storeFile);
		File stockFiles = new File(baseFile,string);
		File [] files =stockFiles.listFiles(new FilenameFilter(){

			@Override
			public boolean accept(File dir, String name) {
				// TODO Auto-generated method stub
				if(name.contains(string+".TXT"))
				return true;
				else 
				return false;
			}

			}); 
		for(File f : files)
		{
			getDataOneFile(f);
		}
	}

	private void getDataOneFile(File f) {
		/*
		 * String [] columns ={"buyvolumn","sellvolumn","buyvolumnRatio",
				"totalvolumn","close",
				"upDownToday","upDownYest"};
	
		 */
		// TODO Auto-generated method stub
		
		
		String date = f.getName().split("-")[0];
		Date cal = MyCalendar.getDateFromEightStr(date);
		List <Float>data = new ArrayList<Float>();
		try {
			FileInputStream fis = new FileInputStream(f);
			InputStreamReader fr = new InputStreamReader(fis,"GBK");
			BufferedReader bfr = new BufferedReader(fr);
			for(int i=0;i<5;i++){
//logger.info("bfr"+i+" : "+
					bfr.readLine();
			}
			float buyvolumn = 0.0f;
			float sellvolumn = 0.0f;
			float buyvolumnRatio = 0.0f;
			float totalvolumn = 0.0f; //成交总量,手数
			float close = 0.0f;
			String []datas = null;
			float upPrice = 0.0f;//上一条成交交易的价格
			float totalUp = 0.0f;
			float totalDown = 0.0f;
			float buyvolumn1 = 0.0f; //10%比例的大单
			float sellvolumn1 = 0.0f;
			float buyvolumn2 = 0.0f;//15%比例的大单
			float sellvolumn2 = 0.0f;
			float buyvolumn3 = 0.0f;//20%比例的大单
			float sellvolumn3 = 0.0f;
			float buyvolumn4 = 0.0f;//30%比例的大单
			float sellvolumn4 = 0.0f;
			if(bfr.ready())
			{
				String temp1 = bfr.readLine();
				String first [] =	temp1.split("[\\s]+");
			float firstPrice = Float.parseFloat(first[1]);
			upPrice = firstPrice;
			float totalAmount = 0.0f;
			List<HistoryData> hsd = new ArrayList<HistoryData>();
		//	Collections.sort(hsd);
			while(bfr.ready())
			{
				String temp = bfr.readLine();
				datas = temp.split("[\\s]+");
				float volum = Float.parseFloat(datas[2]);
				float nowPrice = Float.parseFloat(datas[1]);
				float upDown = nowPrice -upPrice;
				
				upPrice = nowPrice;
				totalvolumn = totalvolumn+volum;
				
					
				if (volum > 0)
				{	
					if (datas.length > 4 )
					{
						String bs = datas[4];
						HistoryData hd = new HistoryData();
						hd.setBs(bs);
						hd.setVolumn(volum);
						hsd.add(hd);
					}
						totalAmount = totalAmount + volum*nowPrice;
					    if(upDown>0.009)// 正股的最小变动单位为0.01元
					    {
						buyvolumn = buyvolumn + volum;
						totalUp= totalUp +upDown;
					    }
					    else if(upDown<-0.009)
					    {
						sellvolumn = sellvolumn + volum;
						totalDown = totalDown+upDown;
					    }
						}
				}
			
			close = Float.parseFloat(datas[1]);
			 buyvolumnRatio = (buyvolumn-sellvolumn)/totalvolumn;  //主动买与主动卖与总成交量的差值
			 Collections.sort(hsd);//按照降序进行排序
			 float conform1 = totalvolumn*0.05f;
			 float conform2 = totalvolumn*0.15f;			
			 float conform3 = totalvolumn*0.2f;			
			 float conform4 = totalvolumn*0.3f;	
			 float buyzone1 = 0;
			 float buyzone2 = 0;			
			 float buyzone3 = 0f;			
			 float buyzone4 = 0f;
			 float sellzone1 = 0f;
			 float sellzone2 = 0f;
			 float sellzone3 = 0f;
			 float sellzone4 = 0f;
			
			 float buy = 0;
			 for (HistoryData hd: hsd)
			 {
				
				 float volumn = hd.getVolumn();
				 String bs = hd.getBs();
				 System.out.println("volumn : "+volumn);
				 buy = buy +volumn;
				 if (buy < conform1) {
					 if("B".equals(bs))
						buyzone1 = buyzone1 + volumn;
					 else if("S".equals(bs))
						 sellzone1 = sellzone1 + volumn;
					} else if (buy < conform2) {
						 if("B".equals(bs))
						buyzone2 = buyzone2 + volumn;
						 else if("S".equals(bs))
							 sellzone2 = sellzone2 + volumn;
					} else if (buy < conform3) {
						 if("B".equals(bs))
						buyzone3 = buyzone3 + volumn;
						 else if("S".equals(bs))
							 sellzone3 = sellzone3 + volumn;
					} else if (buy < conform4) {
						 if("B".equals(bs))
						buyzone4 = buyzone4 + volumn;
						 else if("S".equals(bs))
							 sellzone4 = sellzone4 + volumn;
					} else {
						break;
					}
			 }
			 buyvolumn1 = buyzone1; //5%比例的大单
			 sellvolumn1 = sellzone1;
			 buyvolumn2 = buyzone2;//15%比例的大单
			 sellvolumn2 = sellzone2;
			 buyvolumn3 = buyzone3;//20%比例的大单
			 sellvolumn3 = sellzone3;
			 buyvolumn4 = buyzone4;//30%比例的大单
			 sellvolumn4 = sellzone4;
			}
			
			logger.info("close : "+close);
			if(close>0){
			totalUp =totalUp/close;
			totalDown = totalDown/close;
			
			}
			 bfr .close();
			 fr .close();
			 fis.close();
			
			
			data.add( buyvolumn);
			data.add( sellvolumn);
			data.add( buyvolumnRatio);
			data.add( totalvolumn); //成交总量,手数
			data.add( close);
			data.add( totalUp);
			data.add( totalDown);
			data.add(buyvolumn1);
			data.add(sellvolumn1);
			data.add(buyvolumn2);
			data.add(sellvolumn2);
			data.add(buyvolumn3);
			data.add(sellvolumn3);
			data.add(buyvolumn4);
			data.add(sellvolumn4);

			container.put(cal, data);
			
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void dealHs300(final String stockCode) {
		// TODO Auto-generated method stub
		File baseFile = new File(storeFile);
		File stockFiles = new File(baseFile,"temp");
		File [] files =stockFiles.listFiles(new FilenameFilter(){

			@Override
			public boolean accept(File dir, String name) {
				// TODO Auto-generated method stub
				if(name.contains(".TXT"))
				return true;
				else 
				return false;
			}

			}); 
		for(File f : files)
		{
			getDataOneFile(f);
			//f.delete();//删除掉文件
		}
		
		tjd .insertHistroryDatas(stockCode,container);
		tjd.setHs300Dealed(stockCode);
		for(File f : files)
		{
			f.delete();//删除掉文件
		}
	}

}
