package jyl.index.vrs.pricevolumn.bean;

import java.sql.Timestamp;

public abstract class PriceVolumnIndexBeanAbstract 



{
private  long Id;
private  String StockCode;
private  Timestamp TradeDate;
private  Double Avg2closeRatioBef1;
private  Double Avg2closeRatioBef3;
private  Double Avg2closeRatioBef5;
private  Double Avg2closeRatioBef10;
private  Double Avg2closeRatioBef20;
private  Double Avg2closeRatioBef60;
private  Double Avg2closeRatioBef120;
private  Double AvgCostBef1TodayRatio;
private  Double AvgCostBef3TodayRatio;
private  Double AvgCostBef5TodayRatio;
private  Double AvgCostBef10TodayRatio;
private  Double AvgCostBef20TodayRatio;
private  Double AvgCostBef60TodayRatio;
private  Double AvgCostBef120TodayRatio;
private  Double AvgCostAfter1TodayRatio;
private  Double AvgCostAfter3TodayRatio;
private  Double AvgCostAfter5TodayRatio;
private  Double AvgCostAfter10TodayRatio;
private  Double AvgCostAfter20TodayRatio;
private  Double AvgCostAfter60TodayRatio;
private  Double AvgCostAfter120TodayRatio;
private  Double ViewRealAvgRatioBefore1;
private  Double ViewRealAvgRatioBefore3;
private  Double ViewRealAvgRatioBefore5;
private  Double ViewRealAvgRatioBefore10;
private  Double ViewRealAvgRatioBefore20;
private  Double ViewRealAvgRatioBefore60;
private  Double ViewRealAvgRatioBefore120;
private  Timestamp Ts;

public   void setId(long Id)
		{
				this.Id = Id;
		}

public  long getId()
		{
				return this.Id;
		}

public   void setStockCode(String StockCode)
		{
				this.StockCode = StockCode;
		}

public  String getStockCode()
		{
				return this.StockCode;
		}

public   void setTradeDate(Timestamp TradeDate)
		{
				this.TradeDate = TradeDate;
		}

public  Timestamp getTradeDate()
		{
				return this.TradeDate;
		}

public   void setAvg2closeRatioBef1(Double Avg2closeRatioBef1)
		{
				this.Avg2closeRatioBef1 = Avg2closeRatioBef1;
		}

public  Double getAvg2closeRatioBef1()
		{
				return this.Avg2closeRatioBef1;
		}

public   void setAvg2closeRatioBef3(Double Avg2closeRatioBef3)
		{
				this.Avg2closeRatioBef3 = Avg2closeRatioBef3;
		}

public  Double getAvg2closeRatioBef3()
		{
				return this.Avg2closeRatioBef3;
		}

public   void setAvg2closeRatioBef5(Double Avg2closeRatioBef5)
		{
				this.Avg2closeRatioBef5 = Avg2closeRatioBef5;
		}

public  Double getAvg2closeRatioBef5()
		{
				return this.Avg2closeRatioBef5;
		}

public   void setAvg2closeRatioBef10(Double Avg2closeRatioBef10)
		{
				this.Avg2closeRatioBef10 = Avg2closeRatioBef10;
		}

public  Double getAvg2closeRatioBef10()
		{
				return this.Avg2closeRatioBef10;
		}

public   void setAvg2closeRatioBef20(Double Avg2closeRatioBef20)
		{
				this.Avg2closeRatioBef20 = Avg2closeRatioBef20;
		}

public  Double getAvg2closeRatioBef20()
		{
				return this.Avg2closeRatioBef20;
		}

public   void setAvg2closeRatioBef60(Double Avg2closeRatioBef60)
		{
				this.Avg2closeRatioBef60 = Avg2closeRatioBef60;
		}

public  Double getAvg2closeRatioBef60()
		{
				return this.Avg2closeRatioBef60;
		}

public   void setAvg2closeRatioBef120(Double Avg2closeRatioBef120)
		{
				this.Avg2closeRatioBef120 = Avg2closeRatioBef120;
		}

public  Double getAvg2closeRatioBef120()
		{
				return this.Avg2closeRatioBef120;
		}

public   void setAvgCostBef1TodayRatio(Double AvgCostBef1TodayRatio)
		{
				this.AvgCostBef1TodayRatio = AvgCostBef1TodayRatio;
		}

public  Double getAvgCostBef1TodayRatio()
		{
				return this.AvgCostBef1TodayRatio;
		}

public   void setAvgCostBef3TodayRatio(Double AvgCostBef3TodayRatio)
		{
				this.AvgCostBef3TodayRatio = AvgCostBef3TodayRatio;
		}

public  Double getAvgCostBef3TodayRatio()
		{
				return this.AvgCostBef3TodayRatio;
		}

public   void setAvgCostBef5TodayRatio(Double AvgCostBef5TodayRatio)
		{
				this.AvgCostBef5TodayRatio = AvgCostBef5TodayRatio;
		}

public  Double getAvgCostBef5TodayRatio()
		{
				return this.AvgCostBef5TodayRatio;
		}

public   void setAvgCostBef10TodayRatio(Double AvgCostBef10TodayRatio)
		{
				this.AvgCostBef10TodayRatio = AvgCostBef10TodayRatio;
		}

public  Double getAvgCostBef10TodayRatio()
		{
				return this.AvgCostBef10TodayRatio;
		}

public   void setAvgCostBef20TodayRatio(Double AvgCostBef20TodayRatio)
		{
				this.AvgCostBef20TodayRatio = AvgCostBef20TodayRatio;
		}

public  Double getAvgCostBef20TodayRatio()
		{
				return this.AvgCostBef20TodayRatio;
		}

public   void setAvgCostBef60TodayRatio(Double AvgCostBef60TodayRatio)
		{
				this.AvgCostBef60TodayRatio = AvgCostBef60TodayRatio;
		}

public  Double getAvgCostBef60TodayRatio()
		{
				return this.AvgCostBef60TodayRatio;
		}

public   void setAvgCostBef120TodayRatio(Double AvgCostBef120TodayRatio)
		{
				this.AvgCostBef120TodayRatio = AvgCostBef120TodayRatio;
		}

public  Double getAvgCostBef120TodayRatio()
		{
				return this.AvgCostBef120TodayRatio;
		}

public   void setAvgCostAfter1TodayRatio(Double AvgCostAfter1TodayRatio)
		{
				this.AvgCostAfter1TodayRatio = AvgCostAfter1TodayRatio;
		}

public  Double getAvgCostAfter1TodayRatio()
		{
				return this.AvgCostAfter1TodayRatio;
		}

public   void setAvgCostAfter3TodayRatio(Double AvgCostAfter3TodayRatio)
		{
				this.AvgCostAfter3TodayRatio = AvgCostAfter3TodayRatio;
		}

public  Double getAvgCostAfter3TodayRatio()
		{
				return this.AvgCostAfter3TodayRatio;
		}

public   void setAvgCostAfter5TodayRatio(Double AvgCostAfter5TodayRatio)
		{
				this.AvgCostAfter5TodayRatio = AvgCostAfter5TodayRatio;
		}

public  Double getAvgCostAfter5TodayRatio()
		{
				return this.AvgCostAfter5TodayRatio;
		}

public   void setAvgCostAfter10TodayRatio(Double AvgCostAfter10TodayRatio)
		{
				this.AvgCostAfter10TodayRatio = AvgCostAfter10TodayRatio;
		}

public  Double getAvgCostAfter10TodayRatio()
		{
				return this.AvgCostAfter10TodayRatio;
		}

public   void setAvgCostAfter20TodayRatio(Double AvgCostAfter20TodayRatio)
		{
				this.AvgCostAfter20TodayRatio = AvgCostAfter20TodayRatio;
		}

public  Double getAvgCostAfter20TodayRatio()
		{
				return this.AvgCostAfter20TodayRatio;
		}

public   void setAvgCostAfter60TodayRatio(Double AvgCostAfter60TodayRatio)
		{
				this.AvgCostAfter60TodayRatio = AvgCostAfter60TodayRatio;
		}

public  Double getAvgCostAfter60TodayRatio()
		{
				return this.AvgCostAfter60TodayRatio;
		}

public   void setAvgCostAfter120TodayRatio(Double AvgCostAfter120TodayRatio)
		{
				this.AvgCostAfter120TodayRatio = AvgCostAfter120TodayRatio;
		}

public  Double getAvgCostAfter120TodayRatio()
		{
				return this.AvgCostAfter120TodayRatio;
		}

public   void setViewRealAvgRatioBefore1(Double ViewRealAvgRatioBefore1)
		{
				this.ViewRealAvgRatioBefore1 = ViewRealAvgRatioBefore1;
		}

public  Double getViewRealAvgRatioBefore1()
		{
				return this.ViewRealAvgRatioBefore1;
		}

public   void setViewRealAvgRatioBefore3(Double ViewRealAvgRatioBefore3)
		{
				this.ViewRealAvgRatioBefore3 = ViewRealAvgRatioBefore3;
		}

public  Double getViewRealAvgRatioBefore3()
		{
				return this.ViewRealAvgRatioBefore3;
		}

public   void setViewRealAvgRatioBefore5(Double ViewRealAvgRatioBefore5)
		{
				this.ViewRealAvgRatioBefore5 = ViewRealAvgRatioBefore5;
		}

public  Double getViewRealAvgRatioBefore5()
		{
				return this.ViewRealAvgRatioBefore5;
		}

public   void setViewRealAvgRatioBefore10(Double ViewRealAvgRatioBefore10)
		{
				this.ViewRealAvgRatioBefore10 = ViewRealAvgRatioBefore10;
		}

public  Double getViewRealAvgRatioBefore10()
		{
				return this.ViewRealAvgRatioBefore10;
		}

public   void setViewRealAvgRatioBefore20(Double ViewRealAvgRatioBefore20)
		{
				this.ViewRealAvgRatioBefore20 = ViewRealAvgRatioBefore20;
		}

public  Double getViewRealAvgRatioBefore20()
		{
				return this.ViewRealAvgRatioBefore20;
		}

public   void setViewRealAvgRatioBefore60(Double ViewRealAvgRatioBefore60)
		{
				this.ViewRealAvgRatioBefore60 = ViewRealAvgRatioBefore60;
		}

public  Double getViewRealAvgRatioBefore60()
		{
				return this.ViewRealAvgRatioBefore60;
		}

public   void setViewRealAvgRatioBefore120(Double ViewRealAvgRatioBefore120)
		{
				this.ViewRealAvgRatioBefore120 = ViewRealAvgRatioBefore120;
		}

public  Double getViewRealAvgRatioBefore120()
		{
				return this.ViewRealAvgRatioBefore120;
		}

public   void setTs(Timestamp Ts)
		{
				this.Ts = Ts;
		}

public  Timestamp getTs()
		{
				return this.Ts;
		}


}

