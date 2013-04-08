package jyl.util.mondrian.bean;

import java.sql.Timestamp;

public abstract class TimeDimGenerateBeanAbstract 



{
private  int TimeId;
private  Timestamp TheDate;
private  String TheDay;
private  String TheMonth;
private  int TheYear;
private  String DayOfMonth;
private  int WeekOfYear;
private  int MonthOfYear;
private  String Quarter;

public   void setTimeId(int TimeId)
		{
				this.TimeId = TimeId;
		}

public  int getTimeId()
		{
				return this.TimeId;
		}

public   void setTheDate(Timestamp TheDate)
		{
				this.TheDate = TheDate;
		}

public  Timestamp getTheDate()
		{
				return this.TheDate;
		}

public   void setTheDay(String TheDay)
		{
				this.TheDay = TheDay;
		}

public  String getTheDay()
		{
				return this.TheDay;
		}

public   void setTheMonth(String TheMonth)
		{
				this.TheMonth = TheMonth;
		}

public  String getTheMonth()
		{
				return this.TheMonth;
		}

public   void setTheYear(int TheYear)
		{
				this.TheYear = TheYear;
		}

public  int getTheYear()
		{
				return this.TheYear;
		}

public   void setDayOfMonth(String DayOfMonth)
		{
				this.DayOfMonth = DayOfMonth;
		}

public  String getDayOfMonth()
		{
				return this.DayOfMonth;
		}

public   void setWeekOfYear(int WeekOfYear)
		{
				this.WeekOfYear = WeekOfYear;
		}

public  int getWeekOfYear()
		{
				return this.WeekOfYear;
		}

public   void setMonthOfYear(int MonthOfYear)
		{
				this.MonthOfYear = MonthOfYear;
		}

public  int getMonthOfYear()
		{
				return this.MonthOfYear;
		}

public   void setQuarter(String Quarter)
		{
				this.Quarter = Quarter;
		}

public  String getQuarter()
		{
				return this.Quarter;
		}


}

