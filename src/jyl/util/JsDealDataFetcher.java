package jyl.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

/**
 * 类描述：   
 * 创建人：jinyongliang
 * 创建时间：Apr 3, 2013 6:12:16 PM   
 * 修改人：jinyongliang   
 * 修改时间：Apr 3, 2013 6:12:16 PM   
 * 修改备注：   
 * @version 
 */
public class JsDealDataFetcher {

	public static void main(String args[])
	{
		JsDealDataFetcher fetcher = new JsDealDataFetcher();
		//String content = fetcher.getDealedData("http://www.163.com");
		String content = fetcher.getDealedData("http://www.sse.com.cn/assortment/stock/list/stockdetails/announcement/index.shtml?COMPANY_CODE=600004&startDate=2013-01-03&endDate=2013-04-03&productId=600004&startDate=2013-01-03&endDate=2013-04-03&reportType=ALL&reportType2=%E5%85%A8%E9%83%A8&reportType=ALL&moreConditions=true");
		System.out.println("content:"+content);
	}

	/**
	 * 
	 */
	public static String getDealedData(String url) {
		// TODO Auto-generated method stub
		    WebClient webClient = new WebClient(BrowserVersion.FIREFOX_17);
		    StringBuffer sbf = new StringBuffer();  
	        HtmlPage htmlPage = null;
			try {
				webClient.setThrowExceptionOnScriptError( false ) ;
				htmlPage = webClient.getPage(url);
				
			} catch (FailingHttpStatusCodeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			/*try {
				
				InputStream is = htmlPage.getWebResponse().getContentAsStream();
				InputStreamReader	isr = new InputStreamReader(is,"utf-8");
				BufferedReader bfr = new BufferedReader(isr);
				while(bfr.ready())
				{
					sbf.append(bfr.readLine());
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			//htmlPage.getBody().asXml();
			//htmlPage.getBody();
			 return htmlPage.asXml();
	      // return htmlPage.getTextContent();
		//return sbf.toString();
	}
}
