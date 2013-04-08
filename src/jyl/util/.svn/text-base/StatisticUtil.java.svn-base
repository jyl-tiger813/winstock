package jyl.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.apache.log4j.Logger;
public class StatisticUtil {
	private static final Logger logger = Logger.getLogger(StatisticUtil.class);

	public static String gzipFile(String fileStr)
	{
	    String gzipStr = "gzip "+fileStr;
	    String targetStr = fileStr+".gz";
		try {
			Runtime.getRuntime().exec(gzipStr);
			try {
				Thread.sleep(1000);
				while(true)
				{
					Thread.sleep(100);
					File file =  new File(targetStr);
					if(file.exists())
						break;
				}
			} catch (InterruptedException e) {
				logger.error( e);
			}
		} catch (IOException e) {
			logger.error( e);
		}
		return targetStr;
		
	}

	public static void main(String args[]){
		/*String url = "http://www.baijob.com/alaAD/pages/top.html";
		String encodeingString = URLEncoder.encode(url);
		System.out.println(encodeingString);*/
		try {
				readLines("D:\\software\\opensource\\mondrian\\foodmart_mysql\\foodmart_mysql.sql",50);//("D:\\software\\opensource\\mondrian-3.2.1.13885\\mondrian-3.2.1.13885\\demo\\FoodMartCreateData.sql",20);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	/**
	 * @param string
	 * @param i
	 * @throws IOException 
	 */
	private static void readLines(String string, int i) throws IOException {
		File f = new File(string);
		FileReader reader = new FileReader(f);
		BufferedReader buf = new BufferedReader(reader);
		int r = 0;
		while(r<i)
		{
			r++;
			String line =  buf.readLine();
			System.out.println("line:"+line);
		}
	}
}
