package jyl.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
/*
 * test gbk source readed by utf-8 enviroment
 */
public class CharSetDealing2 {

	private static String str1 = "http://www.sse.com.cn/sseportal/webapp/datapresent/SSEQueryTradingByProdTypeAct?prodType=9&searchDate=2009-03-24&tab_flag=1";
	//private static String str1="E:/221.html";
	private InputStream isr;
	private void init(){
		try{
		URL	url1 = new URL(str1);
		isr=url1.openStream();
		}catch(Exception e)
		{	System.out.println("urlWrong");		
		if(isr!=null){
			try {
				isr.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
		}

	private void closeSource(){
		if(isr!=null){
			try {
				isr.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void readOneTime() throws Exception{
		init();
	int souLength=isr.available();
	System.out.println("souLength"+souLength);
	byte [] byteArray=new byte[60000];
	int i=0;
	String temp="";
//System.out.println("length"+isr.available());

		
		isr.read(byteArray);
	temp=new String(byteArray,"GBK");
	closeSource();
	System.out.println("TEMP"+temp);

}
	public void readLine(){
		init();
		try {
			int before=0;
			byte [] byteArray=new byte[500];
			while(isr.available()>0){
			int tempInt=isr.read();
			System.out.print(tempInt+" "+(char)tempInt);
			
			before=tempInt;
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			closeSource();
			e.printStackTrace();
		}
	}
	public static void main(String []args) throws Exception{
		CharSetDealing2 cd=new CharSetDealing2();
		cd.readOneTime();
	}
}
