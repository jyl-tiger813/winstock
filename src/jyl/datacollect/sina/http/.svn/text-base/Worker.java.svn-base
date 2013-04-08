package jyl.datacollect.sina.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class Worker extends Thread {
	private String iniUrl1 = "http://vip.stock.finance.sina.com.cn/corp/go.php/vRPD_NewStockIssue/page/";
	private String iniUrl2 = ".phtml";
	private String url;
	private int num;
	private List record = new ArrayList();
	public  static HashMap stock=new HashMap();//测试用

	public Worker(int param) {
		num=param;
		url = iniUrl1 + param + iniUrl2;
		if(param==2){
			System.out.println("exception will throwed");
		//	Dispatcher.getnumArr().add(new Integer(2));
		//	throw new RuntimeException();
			
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		Dispatcher.setThreadcount(Dispatcher.getThreadcount()-1);
		getData(url);
		Dispatcher.setThreadcount(Dispatcher.getThreadcount()+1);
	//	Dispatcher.numArr.remove(new Integer(num));
	}

	private void getData(String url) {
		System.out.println("url111" + url);
		URL urlnow = null;
		try {
			urlnow = new URL(url);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			System.out.println("url wrong");
			e.printStackTrace();
		}
		InputStreamReader isr = null;
		try {
			isr = new InputStreamReader(urlnow.openStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BufferedReader bfr = new BufferedReader(isr);
		String row = null;
		try {
			boolean is = true;
			int i=0;
			bfr.skip(17046);
			while (bfr.ready()&&is) {
				// bfr.skip(50);
               i++;
				row = bfr.readLine();
				System.out.println("row" + row);
				if (row
						.matches(".*<td ><div align=\"center\"><strong>详细</strong></div></td>.*"))
				{		
					bfr.readLine();
				while (is) {
					
					row = bfr.readLine();
					System.out.println("rowtest"+row);
					if (!(row.matches(".*<tr class=\"tr_2\">.*")||row.matches(".*<tr>.*"))) {
						is = false;
						break;
					}
					readOneRecord(bfr);

				}
				}

			}
			Dispatcher.numArr.remove(new Integer(num));
			System.out.println("remove "+num);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/*
	 * 读取一支股票的数据,一行数据区块处理
	 */
	public void readOneRecord(BufferedReader bfr) {
		String rowStr;
		String stockNum;// 证券代码
		String stockName;// 证券简称
		String issueTime;// 上网发行日期
		String toMarketTime;// 上市日期
		String issueAmount;// 发行数量(万股)
		String netIssueAmount;// 上网发行数量(万股)
		String issuePrice;// 发行价格(元)
		String ratio;// 市盈率
		String integrySym;// 信息是否完整
		try {
			rowStr = bfr.readLine();
			stockNum = rowStr.replace("<td><div align=\"center\">", "");
			stockNum = stockNum.replace("</div></td>", "");
			stockNum = stockNum.trim();// 得到发行代码
			bfr.readLine();
			bfr.readLine();
			bfr.readLine();
			rowStr = bfr.readLine();
			stockName = rowStr.replace("</a></div>", "").trim();
			bfr.readLine();
			rowStr = bfr.readLine();
			issueTime = rowStr.replace("<td><div align=\"center\">", "")
					.replace("</div></td>", "").trim();
			rowStr = bfr.readLine();
			toMarketTime = rowStr.replace("<td><div align=\"center\">", "")
					.replace("</div></td>", "").trim();
			rowStr = bfr.readLine();
			issueAmount = rowStr.replace("<td><div align=\"center\">", "")
					.replace("</div></td>", "").trim();
			rowStr = bfr.readLine();
			netIssueAmount = rowStr.replace("<td><div align=\"center\">", "")
					.replace("</div></td>", "").trim();
			rowStr = bfr.readLine();
			issuePrice = rowStr.replace("<td><div align=\"center\">", "")
					.replace("</div></td>", "").trim();
			rowStr = bfr.readLine();
			ratio = rowStr.replace("<td><div align=\"center\">", "").replace(
					"</div></td>", "").trim();
			rowStr = bfr.readLine();
			while(!(rowStr.matches(".*</tr>.*"))){
				rowStr=bfr.readLine();
			}
			System.out.println("record   :"+num+ " "+ stockNum +"  "+ stockName +"  "+ issueTime
					+ "  "+toMarketTime + "  "+issueAmount + "  "+netIssueAmount + "  "+issuePrice
					+ "  "+ratio);
			stock.put(stockNum, stockName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String args[]) {
		Worker wk = new Worker(1);
		wk.getData(wk.url);
	}
}
