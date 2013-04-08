package jyl.datacollect.sse.crawl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import jyl.datacollect.tdxdata.dao.TdxDataJdbcImp;
import jyl.util.DatabaseHelper;
/**
 * 类描述：   
 * 创建人：jinyongliang
 * 创建时间：2012-7-2 下午04:09:50   
 * 修改人：jinyongliang   
 * 修改时间：2012-7-2 下午04:09:50   
 * 修改备注：   
 * @version 
 */
public class ReprotItemCrawler extends Thread {
	

	String urlStr = "http://disclosure.szse.cn/m/search0425.jsp?leftid=1&lmid=drgg&pageNo=1&stockCode=000001&keyword=&noticeType=&startTime=2012-01-01&endTime=2012-12-02&imageField.x=25&imageField.y=12&tzy=";
	String blockPosPrefixStr = "<tbody>";
	String blockPosSuffixStr ="</tbody>";
	String itemBlockPosPrefixStr = "<tr>";
	String itemBlockPosSuffixStr = "</tr>";
	String hrefItemPrefixStr = "<td align='left' class='td2'><a href='";
	String hrefItemSuffixStr = "'";
	String itemAbstractPreStr = "target=\"new\">";
	String itemAbstractSuffixStr = "</a>";
	String pagePosPrefixStr = "共 <span>";
	String pagePosSuffixStr = "</span> 页";
	String messageTimePosPrefixStr = "class='link1'>["; //]</span>
	String messageTimePosSuffixStr = "]</span>"; 
	Connection con = null;
	ArrayList<String> shseCode = null;
	static String [][] itemParam = new String [3][];
	{
		String [] hrefArr = {"<td align='left' class='td2'><a href='","'","url"};
		String [] itemAbstractArr = {"target=\"new\">","</a>","cn_abstract_comment"};
		String [] messageTimeArr = {"class='link1'>[","]</span>","publish_time"};
		//String [] stockcode = {"","","stockcode"};
		itemParam [0] = hrefArr;
		itemParam [1] = itemAbstractArr;
		itemParam [2] = messageTimeArr;
	}
	
	ArrayList<String> blockStr = new ArrayList<String> ();
	private AtomicInteger activeThreads = new AtomicInteger(0) ;
	private Set<String> codes;
	private String stockCode;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Set<String> codes = new TdxDataJdbcImp().getBlockCode();
		Connection con  = null; 
		/*while(codes.size()>0)
		{*/
			System.out.println("codes.size()"+codes.size());
		for(Object str :codes.toArray())
		{
			try {
				Thread.sleep(100);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			ReprotItemCrawler rl = new ReprotItemCrawler();
			rl.setCodes(codes);
			if(con == null)
				con = rl.setConnection();
			else 
				rl.setConnection(con);
		String temp = 
			"http://disclosure.szse.cn/m/search0425.jsp?leftid=1&lmid=drgg&pageNo=1&stockCode="
			+((String)str)+"&keyword=&noticeType=&startTime=2012-01-01&endTime=2012-07-02&imageField.x=25&imageField.y=12&tzy=";
		 rl.urlStr = temp;
		 rl.setStockCode((String)str);
		rl.start();
		while(rl.activeThreads.intValue()>10)
		{
			try {
				rl.sleep(1000);
				System.out.println("seleep");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		}
		}
	//}

	/**
	 * @param str
	 */
	private void setStockCode(String stockCode) {
		// TODO Auto-generated method stub
		this.stockCode = stockCode;
	}

	/**
	 * @param codes
	 */
	private void setCodes(Set<String> codes) {

		this.codes = codes;
		
	}

	/**
	 * @param con2
	 */
	private void setConnection(Connection con2) {
		// TODO Auto-generated method stub
	this.con = con2;	
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		try {
			doOnePageParse();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException 
	 * 
	 */
	private void fetchAllShzjReports() throws IOException {
		// TODO Auto-generated method stub
		Set<String> codes = new TdxDataJdbcImp().getBlockCode();
		for(String str :codes)
		{
		String temp = 
			"http://disclosure.szse.cn/m/search0425.jsp?leftid=1&lmid=drgg&pageNo=1&stockCode="
			+str+"&keyword=&noticeType=&startTime=2012-01-01&endTime=2012-07-02&imageField.x=25&imageField.y=12&tzy=";
		urlStr = temp;
		try{
		doOnePageParse();
		}catch (java.net.SocketTimeoutException e){
			
		}
		}
	}

	/**
	 * @throws IOException 
	 * 
	 */
	private void doOnePageParse() throws IOException {
		// TODO Auto-generated method stub
		try{
		activeThreads.incrementAndGet();
		getBlocks();
		getInfsFromBlock();
		activeThreads.decrementAndGet();
		}catch (java.net.SocketTimeoutException e){
			doOnePageParse();
		}
	}

	/**
	 * 
	 */
	private void getInfsFromBlock() {
		// TODO Auto-generated method stub
		for(String str : blockStr)
		{
			HashMap<String,String> itemValues = new HashMap<String,String>();
			for(String [] itemArr:itemParam)
			{
				String prefixStr = itemArr[0];
				String suffixStr = itemArr[1];
				int prefixPos = str.indexOf(prefixStr);
				if (prefixPos>0){
					String tempStr = str.substring(prefixPos+prefixStr.length());
					int suffixPos = tempStr.indexOf(suffixStr);
					String contentStr = tempStr.substring(0, suffixPos);
					itemValues.put(itemArr[2], contentStr);
				//	System.out.println("contentStr:"+contentStr);
				}
			}
			if(itemValues.size()>0)
			{
				itemValues.put("stock_code", stockCode);
			flushValue2DB(itemValues);
			}
		}
	}

	/**
	 * @param itemValues
	 */
	private void flushValue2DB(HashMap<String, String> itemValues) {
		// TODO Auto-generated method stub
		String keyStr = "";
		String valueStr = "";
		int i = 0;
		for(Entry<String, String> entry : itemValues.entrySet())
		{
			
			String key = entry.getKey();
			String value = entry.getValue();
			//System.out.println("key:"+key);
		//	System.out.println("value:"+value);
			keyStr = keyStr+key;
			valueStr = valueStr+"'"+value+"'";
			if(i < itemValues.size()-1)
			{
				keyStr = keyStr +",";
				valueStr = valueStr +",";
			}
			i++;
			
		}
		String sqlStr = "insert into sse.shse_report_url_info  ("+keyStr+") values ("+valueStr+")";
		if(con==null)
		{
			
			con = setConnection(); 
		}
		try {
			DatabaseHelper.executeSql(con, sqlStr);
		} catch (SQLException e) {
			System.out.println("sqlStr:"+sqlStr);
			e.printStackTrace();
		}
		codes.remove(itemValues.get("code")); //移除任务队列
		//System.out.println("sqlStr"+sqlStr);
		//System.out.println("sqlStr:");n
	}

	/**
	 * @return
	 */
	private Connection setConnection() {
		// TODO Auto-generated method stub
		 String user = "root";
		 String password = "root";
		 String url = "jdbc:mysql://127.0.0.1:3306/sse?useUnicode=true&characterEncoding=utf8";
		 return DatabaseHelper.getAdConnectionByParams(url, user, password);
	}

	/**
	 * 
	 */
	private void getBlocks() throws IOException  {
		URL url = new URL(urlStr);
		InputStreamReader isr = null;
		URLConnection ucon = url.openConnection();
		//String outtime =  SseControl.getConfig().get("timeout");
		ucon.setConnectTimeout(50);
//		System.out.println("timeout"+ucon.getReadTimeout());
		try {
			isr = new InputStreamReader(ucon.getInputStream(),"GBK");
		} catch (java.net.UnknownHostException e) {
		//	System.out.println(urlStr + "不能识别目标主机");
	//		getData(url, cal1);
			return;

		}
		System.out.println(urlStr + "还能输出吗？？");
		BufferedReader bfr = new BufferedReader(isr);
		// System.out.println(str+";3");//注释
		boolean isEnterOuterBlock = false;
		boolean isEnterInnerBlock = false;
		StringBuilder sbd = null;
		try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while (bfr.ready()) {
			String line = bfr.readLine();
			if(!isEnterOuterBlock)
			{
				if(line.contains(blockPosPrefixStr))
				{
					isEnterOuterBlock = true;
				}
			}
			else{
		//	System.out.println(bfr.readLine());
			
				if(!isEnterInnerBlock)
				{
					if(line.contains(itemBlockPosPrefixStr))
					{
						isEnterInnerBlock = true;
						sbd = new StringBuilder();
					}
				}else
				{//拼接数据
					if(line.contains(itemBlockPosSuffixStr))
					{
						isEnterInnerBlock = false;
						blockStr.add(sbd.toString());
				//		System.out.println("blockStr:"+sbd.toString());
					}else
					{
						sbd.append(line);
					}
				}
			
			if(line.contains(blockPosSuffixStr))
				break;
			
			}
		}
		
	}
		
	}

