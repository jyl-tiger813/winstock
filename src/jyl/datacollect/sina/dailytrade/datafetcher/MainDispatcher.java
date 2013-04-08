package jyl.datacollect.sina.dailytrade.datafetcher;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.sql.DataSource;

import jyl.datacollect.sina.dailytrade.datafetcher.bean.DailyTradeInfoBeanImp;
import jyl.datacollect.sina.dailytrade.datafetcher.bean.StockNameBeanImp;
import jyl.datacollect.sina.dailytrade.datafetcher.dao.DailyTradeInfoDaoImp;
import jyl.datacollect.sina.dailytrade.datafetcher.dao.StockNameDaoImp;
import jyl.datacollect.sse.bo.SseControl;
import jyl.util.DatabaseHelper;
import jyl.util.DateTimeUtil;


/**
 * 类描述：   
 * 创建人：jinyongliang
 * 创建时间：Mar 18, 2013 11:10:46 AM   
 * 修改人：jinyongliang   
 * 修改时间：Mar 18, 2013 11:10:46 AM   
 * 修改备注：   
 * @version 
 */
public class MainDispatcher {
	DataSource ds = DatabaseHelper.getDSByNameLocalConfig("sse");
	public static void main( String args[]
			){
		MainDispatcher disp = new MainDispatcher();
		disp.fetchDatas();
	}

	/**
	 * 
	 */
	public void fetchDatas() {
		// TODO Auto-generated method stub
		StockNameDaoImp snDao = new StockNameDaoImp();
		String querySql = "select * from sse.stocknames";
		Connection con = null;
		try {
			con = ds.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<StockNameBeanImp>  stockBeans = snDao.getBeans(querySql, con);
		ArrayList<String> queryUrl = getQuerySql(stockBeans);
		getAndSaveDatas(queryUrl);
		System.out.println("queryUrl+\n"+queryUrl);
	}

	/**
	 * @param queryUrl
	 */
	private void getAndSaveDatas(ArrayList<String> queryUrl) {
		// TODO Auto-generated method stub
		long beginTime = System.currentTimeMillis();
		
		for(int i=0;i<queryUrl.size();i++)
		{
			String url = queryUrl.get(i);
			try{
			getAndSaveDataOneUrl(url);
			}catch(Exception e)
			{
				e.printStackTrace();
				i--;//如果获取数据失败，重新获取 
			}
		}
	}

	/**
	 * @param url
	 * @throws IOException 
	 */
	private void getAndSaveDataOneUrl(String urlStr) throws IOException {
		// TODO Auto-generated method stub
		URL url = new URL(urlStr); 
		InputStreamReader isr = null;
		URLConnection ucon = url.openConnection();
		String outtime =  SseControl.getConfig().get("timeout");
		ucon.setConnectTimeout(Integer.parseInt(outtime));
//		System.out.println("timeout"+ucon.getReadTimeout());
			isr = new InputStreamReader(ucon.getInputStream(),"GBK");
		StringBuffer contentBuffer = new StringBuffer();
		BufferedReader bfr = new BufferedReader(isr);
		while(bfr.ready())
		{
			contentBuffer.append(bfr.readLine());
		}
		System.out.println("urlStr:"+urlStr);
		//System.out.println("contentStr:"+contentBuffer.toString());
		parseAndSaveDatas(contentBuffer.toString());
	/*	while (bfr.ready()) {
			row = bfr.readLine();
		}*/
		
	}

	/**
	 * @param string
	 */
	private void parseAndSaveDatas(String string) {
	/*
	 * private  Timestamp TradeDate;
private  float ClosedYes;
private  float BeginToday;
private  float CloseToday;
private  float Amount;
private  float Volumn;
private  float Maxprice;
private  float Minprice;
	 */
		ArrayList<DailyTradeInfoBeanImp> beans = new ArrayList<DailyTradeInfoBeanImp>();
		DailyTradeInfoDaoImp dailyImpDao = new DailyTradeInfoDaoImp(); 
		String [] trades = string.split(";");
		for(String str : trades)
		{
			DailyTradeInfoBeanImp bean = new DailyTradeInfoBeanImp();
			String []strs = str.split("=");
			String StockCode = strs[0].substring(strs[0].length()-6, strs[0].length());
			System.out.println("StockCode:"+StockCode);
			String [] tradePro = strs[1].split(",");
			
			  float BeginToday= Float.parseFloat(tradePro[1]);
			  float ClosedYes = Float.parseFloat(tradePro[2]);
			  float CloseToday= Float.parseFloat(tradePro[3]);
			  float Maxprice= Float.parseFloat(tradePro[4]);
			  float Minprice= Float.parseFloat(tradePro[5]);
			  Long Volumn= Long.parseLong(tradePro[8]);
			  Double Amount= Double.parseDouble(tradePro[9]);
			  String timeStr = tradePro[tradePro.length-3];
			  Timestamp TradeDate = new Timestamp(DateTimeUtil.getCalendarByStr(timeStr).getTimeInMillis());
			  bean.setTradeDate(TradeDate);
			  bean.setBeginToday(BeginToday);
			  bean.setClosedYes(ClosedYes);
			  bean.setCloseToday(CloseToday);
			  bean.setMaxprice(Maxprice);
			  bean.setMinprice(Minprice);
			  bean.setAmount(Amount);
			  bean.setVolumn(Volumn);
			  bean.setStockCode(StockCode);
			  beans.add(bean);
		}
		Connection con = null;
		try {
			con = ds.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException();
		}
		dailyImpDao.saveBeans(beans, con);
		
	}


	/**
	 * @param stockBeans
	 * @return
	 */
	private ArrayList<String> getQuerySql(ArrayList<StockNameBeanImp> stockBeans) {
		// TODO Auto-generated method stub
	//	StringBuffer basePrefixStr = new StringBuffer("http://hq.sinajs.cn/list=");
		ArrayList<String> result = new ArrayList<String>();
		StringBuffer prefixStr = new StringBuffer("http://hq.sinajs.cn/list=");
	//	int i =0 ;
	//	for(StockNameBeanImp bean : stockBeans)
		ArrayList<String> arrs = new ArrayList<String> ();
		for(int i=0;i<stockBeans.size();i++)
		{
		//	i++;
		//	if(i>100)
		//		break;
			StockNameBeanImp bean = stockBeans.get(i);
			String stockPrefix = "sz";
			String codeId = bean.getCodeId();
			String beginStr = codeId.substring(0, 1);
			if("6".equals(beginStr))
			{
				stockPrefix = "sh";
			}
			prefixStr.append(stockPrefix+bean.getCodeId()+",");
			if((i+1)%100==0)
			{
				prefixStr.deleteCharAt(prefixStr.length()-1);
				result.add(prefixStr.toString());
				prefixStr = new StringBuffer("http://hq.sinajs.cn/list=");
			}
		}
		return result; 
	}
}
