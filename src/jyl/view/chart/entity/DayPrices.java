package jyl.view.chart.entity;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class DayPrices {
	private String stockNum;//证券代码
	private float highest; //某一时间段最高
	private float lowest; //某一时间段最低
	//List<DayK> cacheContainer;
	List<DayK> container;
	/*
	 * 和container中的数据保持着同样数据的引用,提供另一种访问方式
	 * 当增加出现数据种类(如增加窗口个数,并且新窗口需新内容)时辅助使用
	 */
	HashMap<Calendar,DayK> containerExtend;
	int first;//标志显示数据开始位置
	int last;//标志显示数据的结束位置
	int currentNum = 0; //十字线所指的位置，初始为最右侧
	int recoveryNum =-1;//需要去处十字线的区域
	private int highestNum;
	private int lowestNum;
	public void setLowestNum(int lowestNum) {
		this.lowestNum = lowestNum;
	}
	public int getLowestNum() {
		return lowestNum;
	}
	public void setHighestNum(int highestNum) {
		this.highestNum = highestNum;
	}
	public int getHighestNum() {
		return highestNum;
	}
	public int getCurrentNum() {
		return currentNum;
	}
	public void setCurrentNum(int currentNum) {
		this.recoveryNum = this.currentNum ;
		this.currentNum = currentNum;
	}
	
	/*public void add(DayPrices dp)
	{
		if(dp==null)
			return;
		container.addAll(dp.getContainer());
		float dphighest = dp.getHighest();
		float dplowest = dp.getLowest();
		if(highest<dphighest)
			highest = dphighest;
		if(lowest<dplowest)
			lowest = dplowest;
	}*/
	public void setContainer(List<DayK> container) {
		this.container = container;
	}
	public void setStockNum(String stockNum) {
		this.stockNum = stockNum;
	}
	public float getHighest() {
		return highest;
	}
	public void setHighest(float highest) {
		this.highest = highest;
	}
	public float getLowest() {
		return lowest;
	}
	public void setLowest(float lowest) {
		this.lowest = lowest;
	}
	public List<DayK> getContainer() {
		return container;
	}
	public String getStockNum() {
		return stockNum;
	}
	public int getRecoveryNum() {
		return recoveryNum;
	}
	public int getFirst() {
		return first;
	}
	public void setFirst(int first) {
		this.first = first;
	}
	public int getLast() {
		return last;
	}
	public void setLast(int last) {
		this.last = last;
	}
	public void init() {
		// TODO Auto-generated method stub
		this.first = 0;
		this.last = container.size()-1;
	}
	public HashMap<Calendar, DayK> getContainerExtend() {
		return containerExtend;
	}
	public void setContainerExtend(HashMap<Calendar, DayK> containerExtend) {
		this.containerExtend = containerExtend;
	}

}

