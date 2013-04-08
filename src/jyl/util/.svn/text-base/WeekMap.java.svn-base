package jyl.util;

import java.util.TreeMap;

/**
 * 类描述：   
 * 创建人：jinyongliang
 * 创建时间：Jan 14, 2013 11:26:36 AM   
 * 修改人：jinyongliang   
 * 修改时间：Jan 14, 2013 11:26:36 AM   
 * 修改备注：   
 * @version 
 */
public class WeekMap    {
	private static TreeMap<Integer,String> map = new TreeMap<Integer,String>();
	private static WeekMap instance;

	Integer weekInteger;
	String weekStr;
	private WeekMap() {
		map.put(2,"Monday");
		map.put(3,"Tuesday");
		map.put(4,"Wednesday");
		map.put(5,"Thursday");
		map.put(6,"Friday");
		map.put(7,"Saturday");
		map.put(1,"Sunday");
		;
	}
	public static  WeekMap  getInstance()
	{
		if(instance==null)
			instance = new WeekMap();
		return instance;
	}
	
	public String get(int weekInt)
	{
		return map.get(weekInt);
	}
	

}
