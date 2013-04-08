package jyl.datacollect.sse.bo;

import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import jyl.datacollect.sse.dao.RecordDaoJdbcImp;
import jyl.datacollect.sse.entity.Lack;
import jyl.util.concurrent.ThreadPoolManage;

/**
 * 
 * @author sq
 * 读取lack表中日期的数据，并重新抓取数据
 *
 */

public class AddLacks {
	private RecordDaoJdbcImp  rdj;
	private ThreadPoolExecutor tpe;

	/**
	 * @param args
	 */
	public  List<Calendar> getLacks(Calendar beginDay,Calendar overDay) {
		RecordDaoJdbcImp rdjTemp=getRdj();
		return rdjTemp.getLack(beginDay, overDay);
	}

	public  void savelack(Calendar beginDay,Calendar overDay) {
		List<Calendar> list=getLacks(beginDay, overDay);
		System.out.println("jyllist"+list.size());
		tpe = ThreadPoolManage.getExecuter();
		BlockingQueue bq = tpe.getQueue();
		String bqlength =  SseControl.getConfig().get("queuesize");
		int length = Integer.parseInt(bqlength);
		for(Calendar cal:list){
			ThreadService ts = new ThreadService(cal);
			tpe.submit(new ThreadService(cal));
				while(bq.size()>=length)
	        	{
	        		try {
						Thread.sleep(20);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	        	}
			
		}
		ThreadPoolManage.shutdownTpe(tpe);
		
			/*
			 * try {Thread.sleep(500);
			 *  } catch (InterruptedException e) { // TODO Auto-generated catch
			 * block e.printStackTrace(); }
			 */

	}

	public static void main(String[] args) {
		/*
		 * for(int i=0;i<20;i++){ backwardStr(); CommonUtil cu2=new
		 * CommonUtil(); int weekString =cu2.weekString(cal);
		 * System.out.println(weekString); System.out.println(str);
		 * 
		 */
		AddLacks adds=new AddLacks();
		adds.savelack(null, null);
		/*List <Lack>list=AddLacks.getLacks();
		for(Lack lack:list){
			Calendar cal=lack.getTime();
			System.out.println(cal.get(Calendar.YEAR)+":"
					+cal.get(Calendar.MONTH)+":"+cal.get(Calendar.DATE));
		}*/
	}

	public void setRdj() {
		rdj =new  RecordDaoJdbcImp();
	}

	public RecordDaoJdbcImp getRdj() {
		if(rdj==null)
			setRdj();
		return rdj;
	}
}
