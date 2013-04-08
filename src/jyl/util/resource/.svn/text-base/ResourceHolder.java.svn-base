package jyl.util.resource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class ResourceHolder {

	/**
	 * 应该写成一个单例
	 */
	Properties pro ;
	private ResourceHolder()
	{
		loadResource();
		
	}
	private void loadResource() {
		// TODO Auto-generated method stub
		pro = new Properties();
		 InputStream in = ResourceHolder.class.getResourceAsStream("/jyl/conf/stockconfig.properties"); 
			try {
				pro.load(in);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	private static ResourceHolder  instance= new ResourceHolder();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//  InputStream in = ReadFile.class.getResourceAsStream("/com/lavasoft/res/a.txt"); 
		System.out.println(" isFirst:");
		ResourceHolder rh = ResourceHolder.getInstance();
		String isFirst = rh.getConfigValue("jyl.datacollect.csrc.fund.crawl.isfirst");
		System.out.println(" isFirst:"+isFirst);
	}
	public String getConfigValue(String string) {
		// TODO Auto-generated method stub
		return pro.getProperty(string);
	}
	public static ResourceHolder getInstance() {
		return instance;
	}

}
