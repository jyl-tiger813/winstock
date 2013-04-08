package jyl.datacollect.sse.bo;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;

import jyl.util.MyCalendar;
import jyl.util.XmlDealer;
/*
 * 需要添加的，如线程池参数，网络超时都应该设置成可配置的
 * 单个任务应该限定时间
 * sse从1999-01-03开始算起
 * 错误导致的原因可能是刚插入的数据查询不到？这个可以验证一下
 * ff
 */

public class SseControl 
{
	
	static HashMap  <String,String> result;
	private static  void getConfigs(){
		XmlDealer xd=new XmlDealer();
		String [] param={"starttime","endtime","firsttime","maxnum","begintime","corenum","queuesize","timeout","keepAliveTime"};
		result =xd.doParse(param,"sseconfig.xml");
		
	}
	
	public static HashMap <String,String> getConfig()
	{
		if(result==null)
			getConfigs();
		return result;
			
	}
	public void justDo()
	{
		getConfigs();//应该有个文件表明是否第一次抓
	//	boolean isFirst = false;
		String isFirstStr = result.get("firsttime");
		Calendar cal = null;
		Calendar stopCal = null;
		if("true".equals(isFirstStr.toLowerCase()))
		{
	//		isFirst = true;
			cal=MyCalendar.createCalendar(result.get("endtime"));
			stopCal=MyCalendar.createCalendar(result.get("starttime"));
			MainService ms = new MainService(cal,stopCal);
			ms.creatThread();
		}else
		{
			cal = Calendar.getInstance();//得到的时间不是零时零分零秒，需进一步处理
			cal =  new  GregorianCalendar(cal.get(Calendar.YEAR),
					cal.get(Calendar.MONTH),cal.get(Calendar.DATE));
		//	cal.add(Calendar.DAY_OF_YEAR,-1);
			stopCal=MyCalendar.createCalendar(result.get("begintime"));
		}
		
		
		Validate validate=new Validate();
		validate.setOverDay(cal);
		 validate.setBeginDay(stopCal);
		 boolean isComplete = validate.doValidate();
		 while(!isComplete)
		 {
			 AddLacks adds=new AddLacks();
				adds.savelack(stopCal, cal);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				isComplete = validate.doValidate();
		 }
		 XmlDealer xd=new XmlDealer();
			HashMap<String,String> hm = new HashMap<String,String>();
			hm.put("time.begintime", MyCalendar.getString(cal));
			
			xd.update(hm, "sseconfig.xml");
		//把截止日期保存到配置文件中	 
		 
	} 
	public static void main(String args[])
	{
		SseControl ssc = new SseControl();
		ssc.justDo();
	}
}
