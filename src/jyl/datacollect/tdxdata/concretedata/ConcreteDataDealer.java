package jyl.datacollect.tdxdata.concretedata;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import jyl.datacollect.tdxdata.dao.TdxDataJdbcImp;
import jyl.datacollect.tdxdata.entity.HistoryData;
import jyl.util.Log4jLoader;
import jyl.util.MyCalendar;

import org.apache.log4j.Logger;

/*
 * 可以根据效果继续修改，加上均价等数据分析
 */
public class ConcreteDataDealer {
	
	//String storeFile = "E:\\stock\\tdxdata\\concrete";
	String storeFile = "E:\\stock\\tdxdata\\concreteszag";
	
	String dayStr = "20100726";
	
	int bigChangeLevel = 100000;//每笔成交金额10万作为大单标准
	int smallChangeLevel = 5000;//每笔成交金额5000作为小单标准
	HashMap<String,List<Float>> container = new HashMap<String,List<Float>> ();
	HashMap<String,Float>  commuteVolumeMap = new HashMap<String,Float>();
	HashMap<String,Float> closeDataMapYes = new HashMap<String,Float>();//前一日的收盘价
	Set<String> blockCode ;
	private boolean lack =false;
	static Logger logger = Logger.getLogger(ConcreteDataDealer.class);
	
	public ConcreteDataDealer ()
	{
		/*
		 * 得到板块代码集合
		 */
		setBlockCode();
	}
	/**
	 * @param args
	 * 使用单线程，如果有时间可以改成Nio方式
	 */
	
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		Log4jLoader.loaddefault();
		ConcreteDataDealer cdd = new ConcreteDataDealer();
		//cdd.bigBuySell();
		cdd.getAllDatas();
		//cdd.configAndDeal();
	}

	/*
	 * 买卖合计比较
	 */
	private void bigBuySell() {
		// TODO Auto-generated method stub
		//Calendar c1 = new GregorianCalendar();
		//	c1.My
			//c1 = MyCalendar.backDayNotWeekEnd(c1);
		Calendar c1 = MyCalendar.createCalendar("2010-11-27");
			dayStr = MyCalendar.getStringEight(c1);
			TdxDataJdbcImp tjd = new TdxDataJdbcImp();
			while(c1.after(MyCalendar.createCalendar("2010-07-23"))){
				c1 = MyCalendar.backDayNotWeekEnd(c1);
				dayStr = MyCalendar.getStringEight(c1);
			commuteVolumeMap = tjd.getComuteVolume();
			getDataBS();
			//TdxDataJdbcImp tjd = new TdxDataJdbcImp();
			tjd.insertHistroryDatas(c1,container,"tdxhistory.data_szag1");
			}
	}

	private void getDataBS() {
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
			getDataOneFileBS(f);
		}
	}

	private void getDataOneFileBS(File f) {
		// TODO Auto-generated method stub
		List<Float> data = new ArrayList<Float>();
		logger.info("fileName :" +f.getName());
		String stockCode = f.getName().replace(".TXT", "");
		container.put(stockCode, data);
		try {
			FileInputStream fis = new FileInputStream(f);
			InputStreamReader fr = new InputStreamReader(fis,"GBK");
			BufferedReader bfr = new BufferedReader(fr);
			for(int i=0;i<5;i++){
		//	logger.info("bfr"+i+" : "+
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
			totalUp =totalUp/close;   //总计向上变动幅度除以当天的价格得到向上变动百分比
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

			
			
			//jyl
			
			 
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

	public void getAllDatas() {
		// TODO Auto-generated method stub
		Calendar c1 = MyCalendar.createCalendar("2010-12-24");//new GregorianCalendar();
		logger.info("calendar:"+MyCalendar.getString(c1)+c1.get(Calendar.HOUR_OF_DAY));
	//	c1.My
		//c1 = MyCalendar.backDayNotWeekEnd(c1);
		dayStr = MyCalendar.getStringEight(c1);
		logger.info("dayStr1"+dayStr);
		TdxDataJdbcImp tjd = new TdxDataJdbcImp();
		while(c1.before(MyCalendar.createCalendar("2011-04-26"))){
			c1 = MyCalendar.nextDayNotWeekEnd(c1);
			Calendar yes = MyCalendar.backDayNotWeekEnd(c1);
			dayStr = MyCalendar.getStringEight(c1);
			logger.info("dayStr2"+dayStr);
		commuteVolumeMap = tjd.getComuteVolume();
		closeDataMapYes = tjd.getCloseDataYes(yes);
		getData();
		if(lack ==true)
		{
			lack=false;
			continue;
		}
		//TdxDataJdbcImp tjd = new TdxDataJdbcImp();
		tjd.insertConcreteData(c1,container);
		}
	}

	/*
	 * 得到板块代码
	 */
	private void  setBlockCode() {
		// TODO Auto-generated method stub00006
		TdxDataJdbcImp tdj = new TdxDataJdbcImp();
		
		blockCode = tdj.getBlockCode();
	}
	
	public void configAndDeal()
	{
		Calendar c1 = new GregorianCalendar();
		c1.add(Calendar.DAY_OF_YEAR,1);	
	//	Calendar c1 = MyCalendar.createCalendar("2010-11-11");
		c1 = MyCalendar.backDayNotWeekEnd(c1);
		Calendar yes = MyCalendar.backDayNotWeekEnd(c1);
		dayStr = MyCalendar.getStringEight(c1);
		TdxDataJdbcImp tjd = new TdxDataJdbcImp();
		commuteVolumeMap = tjd.getComuteVolume();
		closeDataMapYes = tjd.getCloseDataYes(yes);
		getData();
		//TdxDataJdbcImp tjd = new TdxDataJdbcImp();
		tjd.insertConcreteData(c1,container);
	}
	private void getData() {
		// TODO Auto-generated method stub
		File baseFile = new File(storeFile);
		
		File dayFile = new File(baseFile,dayStr);
		logger.info("dayStr:"+dayStr);
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
		if(files==null) 
		{
			 lack  = true;
			return;
		}
		for(File f : files)
		{
			getDataOneFile(f);
		}
	}

	private void getDataOneFile(File f) 
	{
		// TODO Auto-generated method stub
		List<Float> oneStock = new ArrayList<Float>();
		logger.info("fileName :" +f.getName());
		String stockCode = f.getName().replace(".TXT", "");
		if(!blockCode.contains(stockCode))
			return;//如果板块股票中没有该股票,返回
		logger.info("closeDataMapYes :" +closeDataMapYes.size());
		Float closeYes = null;
		if(closeDataMapYes.size()>0)
			closeYes = closeDataMapYes.get(stockCode);
		container.put(stockCode, oneStock);
		try {
			FileInputStream fis = new FileInputStream(f);
			InputStreamReader fr = new InputStreamReader(fis,"GBK");
			BufferedReader bfr = new BufferedReader(fr);
			for(int i=0;i<5;i++){
		//	logger.info("bfr"+i+" : "+
					bfr.readLine();
			}
			float totalVolumn = 0.0f; //成交总量,手数
			float totalAmount = 0.0f;//成交金额
			float totalChangeNumber = 0.0f; //总换手笔数
			float perChangeAmount = 0.0f; //平均每笔成交金额
			float totalBigBuy = 0.0f;  //总买
			float totalBigSell = 0.0f;//总卖
			float totalOherBuy = 0.0f;
			float upPrice = 0.0f;
			float totalUp = 0.0f;
			float totalDown = 0.0f;
			float totalOherSell = 0.0f;
			float totalSmallBuy = 0.0f; 
			float totalSmallSell = 0.0f;
			float totalBigBuyRatio = 0.0f; 
			float totalBigSellRatio = 0.0f;
			float totalOherBuyRatio = 0.0f;
			float totalOherSellRatio = 0.0f;
			float totalSmallBuyRatio = 0.0f; 
			float totalSmallSellRatio = 0.0f;
			float totalBuyRatio = 0.0f; //为大中小的和
			float totalSellRatio = 0.0f;
			float open = 0.0f;
			float close = 0.0f;
			float netBigBuyPercent = 0.0f;
			float upAndDownThisDay = 0.0f;
			
			
			/*while(bfr.ready())
			{
				String temp = bfr.readLine();
				logger.info("tepm 1"+temp);
			}
			if(3>2)
			return;*/
			
			String []datas = null;
			
			if(bfr.ready())
			{
				String temp1 = bfr.readLine();
				String first [] =	temp1.split("[\\s]+");
			float firstPrice = Float.parseFloat(first[1]);
			
			upPrice = firstPrice;
			open = firstPrice;
			float bigchangevol = bigChangeLevel/firstPrice/100;
			float smallchangevol = smallChangeLevel/firstPrice/100;
			
			
			while(bfr.ready())
			{
				String temp = bfr.readLine();
				datas = temp.split("[\\s]+");
				float volum = Float.parseFloat(datas[2]);
				float nowPrice = Float.parseFloat(datas[1]);
				float upDown = nowPrice -upPrice;
				upPrice = nowPrice;
				float changeNum = Float.parseFloat(datas[3]);
				float volumnPerChange = 0.0f;
				totalChangeNumber = totalChangeNumber+changeNum ;
				 if(upDown>0.009)// 正股的最小变动单位为0.01元
				    {
					totalUp= totalUp +upDown;
				    }
				    else if(upDown<-0.009)
				    {
					totalDown = totalDown+upDown;
				    }
				if (changeNum > 0)
					volumnPerChange = volum / changeNum;
				if (datas.length > 4 && ("B".equals(datas[4]))) {
					if (volumnPerChange >= bigchangevol) {
						totalBigBuy = totalBigBuy + volum;
					} else if (volumnPerChange <= smallchangevol) {
						totalSmallBuy = totalSmallBuy + volum;
					} else {
						totalOherBuy = totalOherBuy + volum;;
					}
				} else if (datas.length > 4 && ("S".equals(datas[4]))) {
					if (volumnPerChange >= bigchangevol) {
						totalBigSell = totalBigSell + volum;
					} else if (volumnPerChange <= smallchangevol) {
						totalSmallSell = totalSmallSell + volum;
					} else {
						totalOherSell = totalOherSell + volum;
					}
				}
	
			}
			close = Float.parseFloat(datas[1]);
			if(close>0){
				totalUp =totalUp/close;   //总计向上变动幅度除以当天的价格得到向上变动百分比
				totalDown = totalDown/close;
				
				}
			totalVolumn = totalBigBuy + totalBigSell + totalOherBuy
					+ totalOherSell + totalSmallBuy + totalSmallSell;
			totalAmount = totalVolumn * (close+open)/2/100;//单位为万
			perChangeAmount = totalAmount/totalChangeNumber;
			 totalBigBuyRatio = totalBigBuy/totalVolumn; 
			 totalBigSellRatio = totalBigSell/totalVolumn; 
			 totalOherBuyRatio = totalOherBuy/totalVolumn; 
			 totalOherSellRatio = totalOherSell/totalVolumn; 
			 totalSmallBuyRatio = totalSmallBuy/totalVolumn; 
			 totalSmallSellRatio = totalSmallSell/totalVolumn; 
			 totalBuyRatio = totalBigBuyRatio+totalOherBuyRatio+totalSmallBuyRatio; //为大中小的和
			 totalSellRatio = totalBigSellRatio+totalOherSellRatio+totalSmallSellRatio;
			 Float cumuteVol = commuteVolumeMap.get(stockCode);
			 System.out.println("closeYes:"+closeYes);
			 
			if( close > 0)
				if(closeYes!=null){
			 upAndDownThisDay = (close-closeYes)/closeYes;
			upAndDownThisDay = upAndDownThisDay <= 0.1 && upAndDownThisDay>-0.1?upAndDownThisDay:-1;
			 }
			
			if(cumuteVol==null)//防止没在计划内的数据处理产生异常
				 return;
			 netBigBuyPercent = (totalBigBuy-totalBigSell)/commuteVolumeMap.get(stockCode);
			}
			
			 oneStock.add( totalVolumn);
			 oneStock.add(  totalAmount);
			 oneStock.add(  totalChangeNumber);
			 oneStock.add(  perChangeAmount);
			 oneStock.add(  totalBigBuy);
			 oneStock.add(  totalBigSell);
			 oneStock.add( 	totalOherBuy);
			 oneStock.add(  totalOherSell);
			 oneStock.add(  totalSmallBuy);
			 oneStock.add(  totalSmallSell);
			 oneStock.add(  totalBigBuyRatio);
			 oneStock.add(  totalBigSellRatio);
			 oneStock.add(  totalOherBuyRatio);
			 oneStock.add(  totalOherSellRatio);
			 oneStock.add(  totalSmallBuyRatio);
			 oneStock.add(  totalSmallSellRatio);
			 oneStock.add(  totalBuyRatio);
			 oneStock.add(  totalSellRatio);
			 oneStock.add(  open);
			 oneStock.add(  close);
			 oneStock.add(  netBigBuyPercent);
			 oneStock.add(  upAndDownThisDay);
			 oneStock.add(  totalUp);
			 oneStock.add(  totalDown);
			 
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

}
