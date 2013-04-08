package jyl.datacollect.tdxdata.concretedata.entity;

import java.util.Calendar;
import java.util.HashMap;

/*
 * 代表某支股票的所有明细值
 */

public class DayConcreteEntity {
	public HashMap<String,Float> datas = new HashMap<String,Float>();
	public HashMap<Calendar,Float> openPrice = new HashMap<Calendar,Float>();
	public HashMap<Calendar,Float> closePrice = new HashMap<Calendar,Float>();
	/*
	 *  oneStock.add( totalVolumn);
			 oneStock.add(  totalAmount);
			 oneStock.add(  totalChangeNumber);
			 oneStock.add(  perChangeAmount);
			 oneStock.add(  totalBigBuy);
			 oneStock.add(  totalBigSell);
			 oneStock.add( 	totalOherBuy);
			 oneStock.add(  totalOherSell);
			 oneStock.add(  totalSmallBuy);
			 oneStock.add(  totalSmallSell);
			 oneStock.add(  totalBigBuyRatio);
			 oneStock.add(  totalBigSellRatio);
			 oneStock.add(  totalOherBuyRatio);
			 oneStock.add(  totalOherSellRatio);
			 oneStock.add(  totalSmallBuyRatio);
			 oneStock.add(  totalSmallSellRatio);
			 oneStock.add(  totalBuyRatio);
			 oneStock.add(  totalSellRatio);
			 oneStock.add(  open);
			 oneStock.add(  close);
	 */
	String stockCode;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public HashMap<String, Float> getDatas() {
		return datas;
	}

	public void setDatas(HashMap<String, Float> datas) {
		this.datas = datas;
	}

	public String getStockCode() {
		return stockCode;
	}

	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}

	public HashMap<Calendar, Float> getOpenPrice() {
		return openPrice;
	}

	public void setOpenPrice(HashMap<Calendar, Float> openPrice) {
		this.openPrice = openPrice;
	}

	public HashMap<Calendar, Float> getClosePrice() {
		return closePrice;
	}

	public void setClosePrice(HashMap<Calendar, Float> closePrice) {
		this.closePrice = closePrice;
	}

}
