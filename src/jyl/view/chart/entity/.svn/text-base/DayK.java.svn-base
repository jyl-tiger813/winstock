package jyl.view.chart.entity;

import java.util.Calendar;
import java.util.HashMap;

public class DayK 


{
	private Calendar changeTime;
	
	/*
	 * 用于储存除K线相关数据以外的数据
	 */
	private HashMap<String,Float> extendDatas = new HashMap<String,Float>();
	
	public Calendar getChangeTime() {
		return changeTime;
	}
	public void setChangeTime(Calendar changeTime) {
		this.changeTime = changeTime;
	}
	public float getOpen() {
		return open;
	}
	public void setOpen(float open) {
		this.open = open;
	}
	public float getHigh() {
		return high;
	}
	public void setHigh(float high) {
		this.high = high;
	}
	public float getLow() {
		return low;
	}
	public void setLow(float low) {
		this.low = low;
	}
	public long getVolumn() {
		return volumn;
	}
	public DayK(Calendar changeTime, float open,float close, float high, float low,
			long volumn) {
		super();
		this.close = close;
		this.changeTime = changeTime;
		this.open = open;
		this.high = high;
		this.low = low;
		this.volumn = volumn;
	}
	public void setVolumn(long volumn) {
		this.volumn = volumn;
	}
	public void setClose(float close) {
		this.close = close;
	}
	public float getClose() {
		return close;
	}
	private float open ;
	private float close;
	private float high ;
	private float low ;
	private long volumn ;

	public HashMap<String, Float> getExtendDatas() {
		return extendDatas;
	}
	public void setExtendDatas(HashMap<String, Float> extendDatas) {
		this.extendDatas = extendDatas;
	}
	
}
