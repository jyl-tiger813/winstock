package jyl.datacollect.sina.historydailytrade;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import javax.sql.DataSource;
import jyl.datacollect.sina.dailytrade.datafetcher.bean.DailyTradeInfoBeanImp;
import jyl.datacollect.sina.dailytrade.datafetcher.dao.DailyTradeInfoDaoImp;
import jyl.util.DatabaseHelper;
import jyl.util.DateTimeUtil;

/**
 * 类描述：   
 * 创建人：jinyongliang
 * 创建时间：Mar 20, 2013 10:47:46 AM   
 * 修改人：jinyongliang   
 * 修改时间：Mar 20, 2013 10:47:46 AM   
 * 修改备注：   
 * @version 
 */
public class HistoryDailyDataLoader {
    String baseDirStr = "C:\\new_qlzq_v6\\T0002\\export";
	DataSource ds = DatabaseHelper.getDSByNameLocalConfig("sse");
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HistoryDailyDataLoader hdd = new HistoryDailyDataLoader();
		hdd.loadDatas();
		//File f = new File("C:\\daily_data\\600004.TXT");
		//hdd.getOneShareHistoryData(f);
	}

	/**
	 * 
	 */
	private void loadDatas() {
		// TODO Auto-generated method stub
		File baseFile = new File(baseDirStr);
		File[] files = baseFile.listFiles();
		int i =0;
		for(File f : files)
		{
			i++;
			System.out.println("i:"+i);
			if(f.getName().endsWith(".txt")||f.getName().endsWith(".TXT"))
			{
				getOneShareHistoryData1(f);
			}
		}
	}

	/**
	 * @param f
	 */
	private void getOneShareHistoryData1(File f) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		String stockcode = f.getName().replace(".txt", "").replace(".TXT","" );
		stockcode = stockcode.substring(2);
		DailyTradeInfoDaoImp dailyImpDao = new DailyTradeInfoDaoImp(); 
		BufferedReader bfr = null;
		try {
			bfr = new BufferedReader(new InputStreamReader(new FileInputStream(f),"GBK"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//跳过前三条数据
		try {
			/*bfr.readLine();
			bfr.readLine();
			bfr.readLine();
			bfr.readLine();*/
			ArrayList<DailyTradeInfoBeanImp> arrs = new ArrayList<DailyTradeInfoBeanImp>();
			while(bfr.ready())
			{
				String rowline = bfr.readLine();
				//System.out.println("rowline:"+rowline);
				if(!"".equals(rowline.trim()))
						{
				DailyTradeInfoBeanImp bean = compositeBean(rowline,stockcode);
				if(bean!=null)
					arrs.add(bean);
						}
			}
				//dailyImpDao.saveBeans(arrs, ds.getConnection());
				dailyImpDao.saveBeansDivided(arrs, ds);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * @param f 
	 * 
	 */
	private void getOneShareHistoryData(File f) {
		// TODO Auto-generated method stub
		String stockcode = f.getName().replace(".txt", "").replace(".TXT","" );
		DailyTradeInfoDaoImp dailyImpDao = new DailyTradeInfoDaoImp(); 
		BufferedReader bfr = null;
		try {
			bfr = new BufferedReader(new InputStreamReader(new FileInputStream(f),"GBK"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//跳过前三条数据
		try {
			bfr.readLine();
			bfr.readLine();
			bfr.readLine();
			bfr.readLine();
			ArrayList<DailyTradeInfoBeanImp> arrs = new ArrayList<DailyTradeInfoBeanImp>();
			while(bfr.ready())
			{
				String rowline = bfr.readLine();
				System.out.println("rowline:"+rowline);
				if(!"".equals(rowline.trim()))
						{
				DailyTradeInfoBeanImp bean = compositeBean(rowline,stockcode);
				if(bean!=null)
					arrs.add(bean);
						}
			}
				//dailyImpDao.saveBeans(arrs, ds.getConnection());
				dailyImpDao.saveBeansDivided(arrs, ds);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * @param rowline
	 * @param stockcode 
	 * @return
	 */
	private DailyTradeInfoBeanImp compositeBean(String rowline, String stockcode) {
		// TODO Auto-generated method stub
		      DailyTradeInfoBeanImp bean = new DailyTradeInfoBeanImp();
			  String [] prop = rowline.split("\t");
			  if(prop.length<7)
				  return null;
			  for(int i =0;i<prop.length;i++)
			  {
				  prop[i] = prop[i].trim();
			  }
			  String StockCode = stockcode;
			  String dateStr = prop[0];
			  Timestamp TradeDate = DateTimeUtil.getTimeStampMonBeforeFormat(dateStr) ;
			  float BeginToday = Float.parseFloat(prop[1]);
			  float Maxprice = Float.parseFloat(prop[2]);
		//	  float ClosedYes = Float.parseFloat(prop[0]);
			  float Minprice = Float.parseFloat(prop[3]);
			  float CloseToday = Float.parseFloat(prop[4]);
			  long Volumn = Long.parseLong(prop[5]);
			  double Amount = Double.parseDouble(prop[6]);
			  bean.setStockCode(StockCode);
			  bean.setTradeDate(TradeDate);
			  bean.setBeginToday(BeginToday);
			  bean.setMaxprice(Maxprice);
			  bean.setMinprice(Minprice);
			  bean.setCloseToday(CloseToday);
			  bean.setVolumn(Volumn);
			  bean.setAmount(Amount);
		return bean;
	}

}
