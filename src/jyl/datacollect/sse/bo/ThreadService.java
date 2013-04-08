package jyl.datacollect.sse.bo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.concurrent.ThreadPoolExecutor;

import jyl.datacollect.sse.dao.RecordDao;
import jyl.datacollect.sse.dao.RecordDaoHbnImp;
import jyl.datacollect.sse.dao.RecordDaoIbatisImp;
import jyl.datacollect.sse.dao.RecordDaoJdbcImp;
import jyl.datacollect.sse.entity.Cease;
import jyl.datacollect.sse.entity.Record;
import jyl.util.CharSetDealing;
import jyl.util.MyCalendar;
public class ThreadService implements Runnable {
	private URL url1;

	private Calendar cal1;
	
	private Record re;
	//http://www.sse.com.cn/market/dealingdata/overview/stock/abshare?prodType=7&&searchDate=2013-01-03&tab_flag=1
	//http://www.sse.com.cn/sseportal/webapp/datapresent/SSEQueryTradingByProdTypeAct?prodType=7&searchDate2006-04-28=&tab_flag=1
	//private static String str1 = "http://www.sse.com.cn/sseportal/webapp/datapresent/SSEQueryTradingByProdTypeAct?prodType=7&searchDate=";
	private static String str1 = "http://www.sse.com.cn/market/dealingdata/overview/stock/abshare/?prodType=7&&searchDate=";
	private static String str2 = "&tab_flag=1";

	private String str = null;

	String dayStr = null;

	String monthStr = null;

	String yearStr = null;
	

	public ThreadService(Calendar cal) {
		/*int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH);
		int date = cal.get(Calendar.DATE);
		cal1 = new GregorianCalendar(year, month, date);
		yearStr = Integer.toString(cal1.get(Calendar.YEAR));
		monthStr = Integer.toString(cal1.get(Calendar.MONTH) + 1);
		dayStr = Integer.toString(cal1.get(Calendar.DATE));
		if (Integer.parseInt(monthStr) < 10)
			monthStr = "0" + monthStr;
		if (Integer.parseInt(dayStr) < 10)
			dayStr = "0" + dayStr;
		str = yearStr + "-" + monthStr + "-" + dayStr;*/
		try {
			url1 = new URL(str1 + MyCalendar.getString(cal) + str2);
			cal1 = cal;
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(Exception e)
		{
			e.printStackTrace();
		}

	};

	public void run() {
		try {
			
			getData(url1, cal1);
		} catch (Exception e) {
			e.printStackTrace();
			//System.out.println(str + "该日没数据");
		} finally {
			
		}
	}

	private void getData(URL url, Calendar cal1) {
		String row = null;// 得到每一行数据
		String maketTotal = null;// 市价总值
		String circulateTotal = null;// 流通市值
		String volumn = null;// 成交量
		String amount = null;// 成交金额
		String hundredShare = null;// 成交笔数
		String perGet = null;// 平均市盈率
		long now = System.currentTimeMillis();
		// Record re=null;
		try{
		try {
			InputStreamReader isr = null;
			URLConnection ucon = url.openConnection();
			String outtime =  SseControl.getConfig().get("timeout");
			ucon.setConnectTimeout(Integer.parseInt(outtime));
	//		System.out.println("timeout"+ucon.getReadTimeout());
			try {
				isr = new InputStreamReader(ucon.getInputStream(),"GBK");
			} catch (java.net.UnknownHostException e) {
				System.out.println(str + "不能识别目标主机");
		//		getData(url, cal1);
				return;

			}
			System.out.println(str + "还能输出吗？？");
			BufferedReader bfr = new BufferedReader(isr);
			for (int i = 0; i < 252; i++) {
				row = bfr.readLine();
			}
			// System.out.println(str+";3");//注释
			while (bfr.ready()) {
				// bfr.skip(50);
				
				row = bfr.readLine();
			//	byte [] temp1=row.getBytes("UTF-8");
				//row=new String(temp1,"GBK");
			//	System.out.println("rowString"+row);
				//System.out.println("rowString1"+new   String(row.getBytes("UTF-8")));   
				//System.out.println("rowString2"+new   String(row.getBytes("GBK")));   
				//row=CharSetDealing.gbToUtf8(row);

				// 读取市价总值
				if (row.matches(".*市价总值.*")) {// 使用正则表达式
					bfr.readLine();
					bfr.readLine();
					row = bfr.readLine();
					maketTotal = row.replace("<strong>", "");// 再使用正则表达式处理
					maketTotal = maketTotal.replace("</strong>", "");
					maketTotal = maketTotal.replace("亿元", "");
					
				}
				// 流通市值
				if (row.matches(".*流通市值.*")) {// 使用正则表达式
					bfr.readLine();
					bfr.readLine();
					row = bfr.readLine();
					circulateTotal = row.replace("<strong>", "");// 再使用正则表达式处理
					circulateTotal = circulateTotal.replace("</strong>", "");
					circulateTotal = circulateTotal.replace("亿元", "");
				}
				// 成交量
				if (row.matches(".*成交量.*")) {// 使用正则表达式
					bfr.readLine();
					bfr.readLine();
					row = bfr.readLine();
					volumn = row.replace("<B>", "");// 再使用正则表达式处理
					volumn = volumn.replace("</B>", "");
					volumn = volumn.replace("万股", "");
				}
				// 成交金额
				if (row.matches(".*成交金额.*")) {// 使用正则表达式
					bfr.readLine();
					bfr.readLine();
					row = bfr.readLine();
					amount = row.replace("<B>", "");// 再使用正则表达式处理
					amount = amount.replace("</B>", "");
					amount = amount.replace("亿元", "");
				}
				// 成交笔数
				if (row.matches(".*成交笔数.*")) {// 使用正则表达式
					bfr.readLine();
					bfr.readLine();
					row = bfr.readLine();
					hundredShare = row.replace("<B>", "");// 再使用正则表达式处理
					hundredShare = hundredShare.replace("</B>", "");
					hundredShare = hundredShare.replace("万笔", "");
				}
				// 平均市盈率
				if (row.matches(".*平均市盈率.*")) {// 使用正则表达式
				//	bfr.readLine();
				//	bfr.readLine();
					row = bfr.readLine();
				//	perGet = row.
					perGet = row.split("<B>")[1];// 再使用正则表达式处理
					perGet = perGet.replace("</B></td>", "");
					break;
				}

			}
			
			re = new Record(cal1, Float.parseFloat(maketTotal.trim()), Float
					.parseFloat(circulateTotal.trim()), Float.parseFloat(volumn
					.trim()), Float.parseFloat(amount.trim()), Float
					.parseFloat(hundredShare.trim()), Float.parseFloat(perGet
					.trim()));
			float amountNum = re.getAmount();
			float changeRatio = (amountNum != 0.0f?amountNum/Float
					.parseFloat(circulateTotal.trim()):0.0f);
			re.setChangeRatio(changeRatio);
			float hundredShareNum = re.getHundredShare();
			float amountPerChange = (hundredShareNum != 0.0f?amountNum/hundredShareNum:0.0f);
			re.setAmountPerChange(amountPerChange);
		}catch(java.net.SocketTimeoutException ste)
		{
			
			System.out.println(str + "超时 "+(System.currentTimeMillis()-now));
		//	getData(url, cal1);
		} 
		catch (java.net.SocketException f) {
			f.printStackTrace();
			System.out.println(str + "网络重置");
			System.out.println(str + "时间 "+(System.currentTimeMillis()-now));
		//	getData(url, cal1);
		}
		if(re!=null)
		{
		RecordDao rd=new RecordDaoJdbcImp();
		System.out.println(re.toString());//jyl 输出
		rd.saveRD(re);
		}
	}
		catch (IOException e) { 
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
			System.out.println(str + "该日没有数据");
			Cease c1=new Cease();
			c1.setTime(cal1);
			RecordDao rd=new RecordDaoJdbcImp();
			rd.saveCE(c1);
			// 该日期持久化到数据库的停市表

			// 调用插入日期函数，把休市日全部列出；以后查看！！
		}
		// 把字符串转换成float数值，作为构造参数创建一个Record对象；
		// System.out.println(maketTotal+";"+circulateTotal+";"+volumn+amount+hundredShare+perGet);

	}
	// 异常类型 java.net.ConnectException: Connection timed out: connect
}
