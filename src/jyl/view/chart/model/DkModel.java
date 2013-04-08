package jyl.view.chart.model;

import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import jyl.util.MyCalendar;
import jyl.view.chart.entity.DayK;
import jyl.view.chart.entity.DayPrices;
import jyl.view.dao.imp.PriceDaoJdbcImp;

import org.apache.log4j.Logger;

public class DkModel {

	/**
	 * @param args
	 */
	private DayPrices now;//以后优化时最好是加上cache机制
	
	private String stockCode;
	
	private int zoomdown = 10;
	
	static Logger logger = Logger.getLogger(DkModel.class);
	
	private int moveRatio=6;//一次移动当前图象的几分之一

	String dataSql = "SELECT * FROM `sse`.`record`,`tdxdata`.`999999`"+
 "where `record`.`changetime` = `999999`.`changeTime`"+
"order by `999999`.`changetime` desc limit 10";
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		DkModel dm = new DkModel();
		DayPrices  result = dm.getDefault("999999");
		List<DayK> dk = result.getContainer();
		for( DayK day :dk)
		{
			logger.info(MyCalendar.getString(day.getChangeTime())+"/t"+day.getHigh()+"/t"+day.getClose());
		}
		logger.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		result = dm.zoomDown();
		result = dm.zoomDown();
		 dk = result.getContainer();
		
	}
	public DayPrices getNow() {
		return now;
	}
	/*
	 * 第一次初始化时使用默认的
	 */
	public DayPrices getDefault(String stockCode)
	{
		this.stockCode = stockCode;
		PriceDaoJdbcImp pj = new PriceDaoJdbcImp();
		this.now = pj.getDayKDefault(stockCode);
		this.now.init();//设置最左和最右位置
		 return now;
	}
   /*
    * 缩小,k线数变多
    */
	public DayPrices zoomDown()
	{
		PriceDaoJdbcImp pj = new PriceDaoJdbcImp();
		List<DayK> container = now.getContainer();
		Long temp =container.get(container.size()-1).getChangeTime().getTime().getTime();
		 pj.getDayKLimit(stockCode,new java.sql.Date(temp),zoomdown,now);
		return now;
	}
	
	public List<DayK> zoomUp()
	{
		return null;
	}
	
	public List<DayK> moveLeft()
	{
		
		return null;
	}
		
	public boolean LeftForward() {
		// TODO Auto-generated method stub
		boolean hasMoreData = true;
	    int last = now.getLast();
	    int first = now.getFirst();
	    int totalSize =now.getContainer().size();
		int limit = (last-first)/moveRatio;//一次移动当前图象的六分之一
		if((last+limit)<totalSize)
		{
			now.setFirst(first+limit);
			now.setLast(last+limit);
		}else{		
		limit = limit - (totalSize - last-1);
		Calendar changeTime = now.getContainer().get(totalSize-1).getChangeTime();
		java.sql.Date date =new java.sql.Date( changeTime.getTime().getTime());
		PriceDaoJdbcImp pj = new PriceDaoJdbcImp();
		
		pj.getDayKLimit(stockCode, date, limit, now);
		int temp = last;
		last = now.getContainer().size()-1;
		first =first+( last - temp);
		now.setFirst(first);
		now.setLast(last);
		if(now.getContainer().size() == totalSize)
		{
			hasMoreData = false;
		}
		}
		logger.info("first: "+first+"last: "+last);
		float highest = 0;
		float lowest = 0;
		first = now.getFirst();
		last = now.getLast();
		List <DayK> list = now.getContainer();
		int highNum = 0;
		int lowNum = 0;
		for(int i=first;i<=last;i++)
		{
			float high = list.get(i).getHigh();
			float low = list.get(i).getLow();
			 if(high>highest)
			 {
				 highest = high;
				 highNum = i;
			 }
			 if(((low<lowest)&&(low>0))||(lowest==0))
			 {
				 lowest = low;
				 lowNum = i;
			 }
		}
		now.setHighest(highest);
		now.setLowest(lowest);
		now.setHighestNum(highNum);
		 now.setLowestNum(lowNum);
		logger.info("hasMoreData :"+hasMoreData);
		return hasMoreData;
	}
	public void rightForward() {
		// TODO Auto-generated method stub
	    int last = now.getLast();
	    int first = now.getFirst();
		int limit = (last-first)/moveRatio;//一次移动当前图象的六分之一
		if((first-limit)<0)
			limit = first;
		now.setFirst(first-limit);
		now.setLast(last-limit);
		float highest = 0;
		float lowest = 0;
		first = now.getFirst();
		last = now.getLast();
		List <DayK> list = now.getContainer();
		int highNum = 0;
		int lowNum = 0;
		for(int i=first;i<=last;i++)
		{
			float high = list.get(i).getHigh();
			float low = list.get(i).getLow();
			 if(high>highest)
			 {
				 highest = high;
				 highNum = i;
			 }
			 if(((low<lowest)&&(low>0))||(lowest==0))
			 {
				 lowest = low;
				 lowNum = i;
			 }
		}
		now.setHighest(highest);
		now.setLowest(lowest);
		now.setHighestNum(highNum);
		 now.setLowestNum(lowNum);
	}
	
	public boolean upWard() {
		// TODO Auto-generated method stub
		
		int currentNum = now.getCurrentNum();
		 int last = now.getLast();
		    int first = now.getFirst();
		    if((last - first)<20)
		    	return false;
			int limit = (last-first)/moveRatio;//一次移动当前图象的六分之一
			int rightLimit =limit;
			int leftLimit =limit;
			int rightMove = first+limit;
			if(rightMove>currentNum)
				rightLimit = currentNum-first;
			now.setFirst(first+rightLimit);
			int leftMove = last-limit; 
			if(leftMove<currentNum)
				leftLimit = last - currentNum;
			now.setLast(last-leftLimit);
			float highest = 0;
			float lowest = 0;
			first = now.getFirst();
			last = now.getLast();
			List <DayK> list = now.getContainer();
			int highNum = 0;
			int lowNum = 0;
			for(int i=first;i<=last;i++)
			{
				float high = list.get(i).getHigh();
				float low = list.get(i).getLow();
				 if(high>highest)
				 {
					 highest = high;
					 highNum = i;
				 }
				 if(((low<lowest)&&(low>0))||(lowest==0))
				 {
					 lowest = low;
					 lowNum = i;
				 }
			}
			now.setHighest(highest);
			now.setLowest(lowest);
			now.setHighestNum(highNum);
			 now.setLowestNum(lowNum);
			return true;
	}
	public boolean downWard() {
		// TODO Auto-generated method stub
		boolean hasMoreData = true;
		int currentNum = now.getCurrentNum();	 
	    int last = now.getLast();
	    int first = now.getFirst();
	    int totalSize =now.getContainer().size();
		int limit = (last-first)/moveRatio;//一次移动当前图象的六分之一
		int rightLimit =limit;
		if((last+limit)<totalSize)
		{
			if((first - limit)<0)
				rightLimit = first;
			now.setFirst(first-rightLimit);
			now.setLast(last+limit);
		}else{		
			if((first - limit)<0)
				rightLimit = first;
			now.setFirst(first-rightLimit);
		Calendar changeTime = now.getContainer().get(totalSize-1).getChangeTime();
		java.sql.Date date =new java.sql.Date( changeTime.getTime().getTime());
		PriceDaoJdbcImp pj = new PriceDaoJdbcImp();
		
		pj.getDayKLimit(stockCode, date, limit, now);
		last = now.getContainer().size()-1;
		now.setLast(last);
		if(now.getContainer().size() == totalSize)
		{
			hasMoreData = false;
		}
		}
		logger.info("first: "+first+"last: "+last);
		float highest = 0;
		
		float lowest = 0;
		first = now.getFirst();
		last = now.getLast();
		List <DayK> list = now.getContainer();
		int highNum = 0;
		int lowNum = 0;
		for(int i=first;i<=last;i++)
		{
			float high = list.get(i).getHigh();
			float low = list.get(i).getLow();
			 if(high>highest)
			 {
				 highest = high;
				 highNum = i;
			 }
			 if(((low<lowest)&&(low>0))||(lowest==0))
			 {
				 lowest = low;
				 lowNum = i;
			 }
		}
		now.setHighest(highest);
		now.setLowest(lowest);
		now.setHighestNum(highNum);
		 now.setLowestNum(lowNum);
		logger.info("hasMoreData :"+hasMoreData);
		return hasMoreData;
	}
	
	public void addWidowData(String []dataNames)
	{
		 int totalSize =now.getContainer().size();
		Calendar changeTime = now.getContainer().get(totalSize-1).getChangeTime();
		PriceDaoJdbcImp pj = new PriceDaoJdbcImp();		
		pj.getWidowData(stockCode, changeTime, dataNames, now);
	}
}
