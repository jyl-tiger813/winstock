package jyl.datacollect.sina.dailytrade.datafetcher.bean;

import java.sql.Timestamp;

public abstract class DailyTradeInfoBeanAbstract 



{
private  long Id;
private  String StockCode;
private  Timestamp TradeDate;
private  float ClosedYes;
private  float BeginToday;
private  float CloseToday;
private  double Amount;
private  long Volumn;
private  float Maxprice;
private  float Minprice;
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

public   void setClosedYes(float ClosedYes)
		{
				this.ClosedYes = ClosedYes;
		}

public  float getClosedYes()
		{
				return this.ClosedYes;
		}

public   void setBeginToday(float BeginToday)
		{
				this.BeginToday = BeginToday;
		}

public  float getBeginToday()
		{
				return this.BeginToday;
		}

public   void setCloseToday(float CloseToday)
		{
				this.CloseToday = CloseToday;
		}

public  float getCloseToday()
		{
				return this.CloseToday;
		}

public   void setAmount(double Amount)
		{
				this.Amount = Amount;
		}

public  double getAmount()
		{
				return this.Amount;
		}

public   void setVolumn(long Volumn)
		{
				this.Volumn = Volumn;
		}

public  long getVolumn()
		{
				return this.Volumn;
		}

public   void setMaxprice(float Maxprice)
		{
				this.Maxprice = Maxprice;
		}

public  float getMaxprice()
		{
				return this.Maxprice;
		}

public   void setMinprice(float Minprice)
		{
				this.Minprice = Minprice;
		}

public  float getMinprice()
		{
				return this.Minprice;
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

