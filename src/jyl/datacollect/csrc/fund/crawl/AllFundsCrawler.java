package jyl.datacollect.csrc.fund.crawl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import jyl.datacollect.sse.bo.SseControl;
import jyl.util.Log4jLoader;
import jyl.view.chart.model.DkModel;

import org.apache.log4j.Logger;

public class AllFundsCrawler implements Runnable {

	/**
	 * @param args
	 */
	private static String url = "http://fund.csrc.gov.cn/web/fund_compay_affiche.fund_affiche";

	static Logger logger = Logger.getLogger(AllFundsCrawler.class);

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 Log4jLoader.loaddefault();
		AllFundsCrawler ac = new AllFundsCrawler();
		Thread ac1 = new Thread(ac);
		ac1.start();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		getData();
	}

	private void getData() {
		// TODO Auto-generated method stub
		URL url1 = null;
		try {
			url1 = new URL(url);
			logger.info("url:"+url);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		InputStreamReader isr = null;
		URLConnection ucon;
		try {
			ucon = url1.openConnection();
			isr = new InputStreamReader(ucon.getInputStream(),"utf-8");
			BufferedReader bfr = new BufferedReader(isr);
			String row = null;
			bfr.readLine();
			while (bfr.ready()) {
				row = bfr.readLine();
				logger.info("row:"+row);
			}
		} 
		catch (java.net.UnknownHostException e) {
			logger.info("不能识别目标主机"+e);
		}catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
