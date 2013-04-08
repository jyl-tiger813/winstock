package jyl.datacollect.sse.crawl.parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;

import jyl.datacollect.sse.crawl.bean.CrawlParamBean;
import jyl.datacollect.sse.crawl.bean.InfoParamBean;

/**
 * 类描述：   
 * 创建人：jinyongliang
 * 创建时间：2012-7-20 下午05:47:35   
 * 修改人：jinyongliang   
 * 修改时间：2012-7-20 下午05:47:35   
 * 修改备注：   
 * @version 
 */
public class ListPageParser {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**
	 * @param paramBean
	 * @return 
	 * @throws IOException 
	 */
	public boolean parse(CrawlParamBean paramBean) throws IOException {
		// TODO Auto-generated method stub
		ArrayList<String> blockStr = new ArrayList<String> ();
		boolean result = true;
		
		//解析得到list页面数据
		URL url = new URL(paramBean.getUrl());
		InputStreamReader isr = null;
		URLConnection ucon = url.openConnection();
		//String outtime =  SseControl.getConfig().get("timeout");
		ucon.setConnectTimeout(500);
//		System.out.println("timeout"+ucon.getReadTimeout());
			isr = new InputStreamReader(ucon.getInputStream(),"utf-8");
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
		int i =0;
		while (bfr.ready()) {
			i++;
			if(i%100==0)
				System.out.println("i:"+i);	;
			System.out.println("i:"+i);
			String line = bfr.readLine();
			if(line.contains(paramBean.getNoInfoLabel()))
				return false;
			if(!isEnterOuterBlock)
			{
				if(line.contains(paramBean.getLabelBlockOuterBegin()))
				{
					isEnterOuterBlock = true;
				}
			}
			else{
		//	System.out.println(bfr.readLine());
			
				if(!isEnterInnerBlock)
				{
					if(line.contains(paramBean.getLabelBlockInnerBegin()))
					{
						isEnterInnerBlock = true;
						sbd = new StringBuilder();
					}
				}else
				{//拼接数据
					if(line.contains(paramBean.getLabelBlockInnerEnd()))
					{
						isEnterInnerBlock = false;
						blockStr.add(sbd.toString()); //
				//		System.out.println("blockStr:"+sbd.toString());
					}else
					{
						sbd.append(line);
					}
				}
			
			/*if(line.contains(paramBean.getLabelBlockOuterEnd()))
				break;*/
			
			}
		}
		
		//解析得到item数据
		
		for(String str : blockStr)
		{
			HashMap<String,String> itemValues = new HashMap<String,String>();
			paramBean.getValues().add(itemValues);
			for(InfoParamBean bean : paramBean.getInfoBeans())
			{
				String []prefixStrs = bean.getBlockBegin();
				String[] suffixStrs = bean.getBlockEnd();
				String prefixStr = "";
				String suffixStr = "";
				int prefixPos = -1;
				for(String temp : prefixStrs){
					
					prefixPos = str.indexOf(temp);
					if (prefixPos>=0)
					{
						prefixStr = temp;
						break;
					}
				}
				if (prefixPos>=0){
					String tempStr = str.substring(prefixPos+prefixStr.length());
					int suffixPos = -1;
					for(String temp : suffixStrs){
						suffixPos = tempStr.indexOf(temp);
						if(suffixPos>=0)
							suffixStr = temp;
					}
					String contentStr = tempStr.substring(0, suffixPos);
					str = tempStr.substring(suffixPos+suffixStr.length(), tempStr.length());
					//bean.setValue(contentStr);
					itemValues.put(bean.getItemName(), contentStr);
					System.out.println("contentStr:"+bean.getItemName()+"，"+contentStr);
				}
			}
		}
		
		return result;
		
	}

}
