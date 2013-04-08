package jyl.datacollect.sse.bo;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import jyl.util.MyCalendar;
import jyl.util.XmlDealer;
import jyl.util.concurrent.ThreadPoolManage;
public class MainService {
	/*private static int year=2009;
	private static int month=4;
	private static int day=8;
	private static int stopYear=2009;
	private static int stopMonth=3;
	private static int stopDay=27;
	private static int ThreadCount=100;
	private static Calendar cal=new GregorianCalendar(year,month-1,day);
	private static Calendar stopCal=new GregorianCalendar(stopYear,stopMonth-1,stopDay);*/
	private ThreadPoolExecutor tpe;
	private  Calendar cal;
	private  Calendar stopCal;
	/*
	 * 构造方法
	 * 进行初试化的内容
	 */
	
	public MainService(Calendar beginDay,Calendar overDay)
	{
		cal = beginDay;
		stopCal = overDay;
	}
	private Calendar nextDay(){
		cal.add(Calendar.DAY_OF_YEAR,-1);
		while(cal.get(Calendar.DAY_OF_WEEK)==Calendar.SATURDAY||cal.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY)
			cal.add(Calendar.DAY_OF_YEAR,-1);		
				return cal;								
		}
	
	public void creatThread(){	
		
		tpe = ThreadPoolManage.getExecuter();
		BlockingQueue bq = tpe.getQueue();
		String bqlength =  SseControl.getConfig().get("queuesize");
		int length = Integer.parseInt(bqlength);
		while(!cal.before(stopCal)){
			/*if(cal.before(stopCal))
				break;*/
			/*try {Thread.sleep(5);
			
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/	
		//	System.out.println("threadcount111"+ThreadCount);
			while(bq.size()>=length)
        	{
        		try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}
			 //深拷贝，防止多线程时出现问题
			Calendar cal1= new GregorianCalendar(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE));
			
          tpe.submit(new ThreadService(cal1));
				 cal=nextDay();
				
				
			//System.out.println("this is main");
			}
		stopAllTaskInside();
		}
	    
		public void stopAllTasks()
		{
			tpe.shutdown();
		}
	    private void stopAllTaskInside()
	    {
	    	ThreadPoolManage.shutdownTpe(tpe);
	    }
	    
	
		public static void main(String[] args) throws UnsupportedEncodingException {	
			/*
			for(int i=0;i<20;i++){
			backwardStr();
			CommonUtil cu2=new CommonUtil();
			int weekString =cu2.weekString(cal);
			System.out.println(weekString);
			System.out.println(str);
			
			*/			
			
		//	System.out.println("rowString2"+new   String("涓浗".getBytes("utf-8")));   
			
		}
	}
	



