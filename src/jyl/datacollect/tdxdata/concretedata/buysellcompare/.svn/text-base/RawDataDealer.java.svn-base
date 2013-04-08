package jyl.datacollect.tdxdata.concretedata.buysellcompare;

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
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

import jyl.datacollect.tdxdata.concretedata.ConcreteDataDealer;
import jyl.datacollect.tdxdata.dao.TdxDataJdbcImp;
import jyl.util.Log4jLoader;
import jyl.util.MyCalendar;

import org.apache.log4j.Logger;

public class RawDataDealer {

	/**
	 * @param args
	 */
	//String storeFile = "E:\\stock\\tdxdata\\concreteszag";
	String storeFile = "E:\\stock\\tdxdata\\concrete";
	
	String dayStr = "20100726";
	
	int bigChangeLevel = 120000;//每笔成交金额在12万以上视为对敲
	HashMap<String,List<Float>> container = new HashMap<String,List<Float>> ();
	HashMap<String,Float>  commuteVolumeMap = new HashMap<String,Float>();
	static Logger logger = Logger.getLogger(ConcreteDataDealer.class);
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Log4jLoader.loaddefault();
		RawDataDealer rdd = new RawDataDealer();
		rdd.getAllDatas();
		//rdd.configAndDeal();
	
	}
	private void configAndDeal() {
		// TODO Auto-generated method stub
		
	}
	private void getAllDatas() {
		// TODO Auto-generated method stub
	//	Calendar c1 = new GregorianCalendar();
		//	c1.My
			//c1 = MyCalendar.backDayNotWeekEnd(c1);
		//	dayStr = MyCalendar.getStringEight(c1);
		dayStr = "20100816";
		Calendar c1 =  MyCalendar.createCalendar("2010-08-06");
			TdxDataJdbcImp tjd = new TdxDataJdbcImp();
			while(c1.after(MyCalendar.createCalendar("2010-07-23"))){
				c1 = MyCalendar.backDayNotWeekEnd(c1);
				dayStr = MyCalendar.getStringEight(c1);
			commuteVolumeMap = tjd.getComuteVolume();
			getData();
			
			//TdxDataJdbcImp tjd = new TdxDataJdbcImp();
			tjd.insertBuySellCompare(c1,container);
			}
	}
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
			getDataOneFile(f);
		}
	}
	private void getDataOneFile(File f) {
		/*
		 * 	String [] columns ={"buyvolumn","sellvolumn","netbuyvolumnpercent","buyvolumnRatio",
				"totalvolumn","close","amountPerChange","cheatBuyVolumn","cheatSellVolumn"};//其他数据可以通过计算得到
	*/
		List<Float> oneStock = new ArrayList<Float>();
		logger.info("fileName :" +f.getName());
		String stockCode = f.getName().replace(".TXT", "");
		container.put(stockCode, oneStock);
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
				float netbuyvolumnpercent = 0.0f;
				float buyvolumnRatio = 0.0f;
				float totalvolumn = 0.0f; //成交总量,手数
				float close = 0.0f;
				float amountPerChange = 0.0f;
				float cheatBuyVolumn = 0.0f;//欺骗性买卖超级大单,指对敲
				float cheatSellVolumn = 0.0f;
				String []datas = null;
				float upPrice = 0.0f;//上一条成交交易的价格
				if(bfr.ready())
				{
					String temp1 = bfr.readLine();
					String first [] =	temp1.split("[\\s]+");
				float firstPrice = Float.parseFloat(first[1]);
				upPrice = firstPrice;
				float bigchangevol = bigChangeLevel/firstPrice/100;	
				float totalChangeNumber = 0.0f; //总换手笔数
				float totalAmount = 0.0f;
				while(bfr.ready())
				{
					String temp = bfr.readLine();
					datas = temp.split("[\\s]+");
					float volum = Float.parseFloat(datas[2]);
					float changeNum = Float.parseFloat(datas[3]);
					float volumnPerChange = 0.0f;
					float nowPrice = Float.parseFloat(datas[1]);
					float upDown = nowPrice -upPrice;
					upPrice = nowPrice;
					totalvolumn = totalvolumn+volum;
					if (changeNum > 0)
					{	volumnPerChange = volum / changeNum;
						if (volumnPerChange >= bigchangevol) 
						{ // 平均每笔成交金额比较大,有对敲嫌疑
							if (datas.length > 4 && ("B".equals(datas[4]))) {
								cheatBuyVolumn = cheatBuyVolumn +volum;
							}
							else if (datas.length > 4 && ("S".equals(datas[4])))
							{
								cheatSellVolumn = cheatSellVolumn +volum;
								
							}
						}else{
							totalChangeNumber = totalChangeNumber+changeNum ;// 不计算被认为是对敲的数量
							totalAmount = totalAmount + volum*nowPrice;
						    if(upDown>0.009)// 正股的最小变动单位为0.01元
						    {
							buyvolumn = buyvolumn + volum;
						    }
						    else if(upDown<-0.009)
						    {
							sellvolumn = sellvolumn + volum;;
						    }
							}
					}
				
				close = Float.parseFloat(datas[1]);
				amountPerChange = totalAmount/totalChangeNumber;
				 netbuyvolumnpercent = (buyvolumn-sellvolumn)/commuteVolumeMap.get(stockCode);
				 buyvolumnRatio = (buyvolumn-sellvolumn)/totalvolumn;  //主动买与主动卖与总成交量的差值
				}
				}
				oneStock.add( buyvolumn);
				oneStock.add( sellvolumn);
				oneStock.add( netbuyvolumnpercent);
				oneStock.add( buyvolumnRatio);
				oneStock.add( totalvolumn); //成交总量,手数
				oneStock.add( close);
				oneStock.add( amountPerChange);
				oneStock.add( cheatBuyVolumn);//欺骗性买卖超级大单,指对敲
				oneStock.add( cheatSellVolumn); 

				
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
			}
	
	
