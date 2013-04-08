package jyl.datacollect.sina.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;

public class Dispatcher {
	public static LinkedList numArr=new LinkedList();
	public  static   int threadcount ;
	private  int pageNum=10;
	private String url = "http://vip.stock.finance.sina.com.cn/corp/go.php/vRPD_NewStockIssue/page/1.phtml";

	public static LinkedList getnumArr(){
		return numArr;
	}
	
	public static void setnumArr(LinkedList numArr1 ){
		numArr=numArr1;
	}
	public synchronized static void setThreadcount(int threadcount1) {
		threadcount = threadcount1;
	}

	public synchronized static  int getThreadcount() {
		return threadcount;
	}

	/*
	 * 得到页面数
	 */
	public  void  getPageNum() {
		threadcount=10;
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
		String temp = null;
		try {
			bfr.skip(62512);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		while (true) {
			try {
				temp = bfr.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (temp.matches(".*当前页面是.*")) {
				pageNum=Integer.parseInt(temp.split(";")[0].split(",")[1].trim().replace("共", "").replace("页", "").replace("&nbsp", "").trim());
				System.out.println("pageNum"+pageNum);
				break;
			}
		}
	}
	public void doall(){
		disDo();
		try {
			doAddUnSucc();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public  void disDo(){
		getPageNum();
		int i=1;	
		LinkedList numA = getnumArr();
		while(i<=pageNum){
			if(threadcount>0){
				try{
					numA.add(new Integer(i));
				Worker wk=new Worker(i);		
				wk.start();
				}catch (Exception e){
					
				}
				i++;
			}else{
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
		}
	}
	
	public void doAddUnSucc()throws Exception{
		LinkedList numA = getnumArr();
		System.out.println("size "+numA.size());
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~`");
		for(Object a:numA){
			System.out.println("~~~~~~~~ `"+a);
		}
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~`");
		for(int i=0;i<numA.size();i++){
			if(threadcount>0){
				Worker wk=new Worker(((int)(Integer)(numA.get(i))));
		//		i++;
				wk.start();
			}else{
				try {
					i--;//如果线程没启动,该次循环重新计算
					Thread.sleep(200);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
		}
	// numA = getnumArr();i
		System.out.println("size2 "+numA.size());
	 if(numA.size()>0){
		 doAddUnSucc();
	 }
	 System.out.println("size"+Worker.stock.size());
	}

	public static void main(String args[]) {
		Dispatcher dc=new Dispatcher();
		dc.doall();
		
	}
}
