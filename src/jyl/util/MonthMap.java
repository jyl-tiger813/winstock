package jyl.util;

import java.util.TreeMap;

public class MonthMap    {
	private static TreeMap<Integer,String> map = new TreeMap<Integer,String>();
	private static MonthMap instance;

	Integer weekInteger;
	String weekStr;
	private MonthMap() {
	
		map.put(1,"January");
		map.put(2,"February");
		map.put(3,"March");
		map.put(4,"April");
		map.put(5,"May");
		map.put(6,"June");
		map.put(7,"July");
		map.put(8,"August");
		map.put(9,"September");
		map.put(10,"October");
		map.put(11,"November");
		map.put(12,"December");
		;
	}
	public static MonthMap  getInstance()
	{
		if(instance==null)
			instance = new MonthMap();
		return instance;
	}
	
	public String get(int weekInt)
	{
		return map.get(weekInt);
	}
	

}