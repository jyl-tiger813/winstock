package jyl.util.concurrent;

import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import jyl.datacollect.sse.bo.SseControl;

public class ThreadPoolManage {
	
	static BlockingQueue queue ;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public static ThreadPoolExecutor getExecuter() {
		// TODO Auto-generated method stub
		Map<String,String> params = SseControl.getConfig();
		//"corenum","queuesize","timeout","keepAliveTime"
		int corenum = Integer.parseInt(params.get("corenum"));
		int maxnum = Integer.parseInt(params.get("maxnum"));
		int queuesize = Integer.parseInt(params.get("queuesize"));
		int timeout = Integer.parseInt(params.get("timeout"));
		int keepAliveTime = Integer.parseInt(params.get("keepAliveTime"));
		queue = new ArrayBlockingQueue(queuesize);
		ThreadPoolExecutor te = new ThreadPoolExecutor(corenum, maxnum, timeout, TimeUnit.MILLISECONDS, queue);
		return te;
	}

	public static void shutdownTpe(ThreadPoolExecutor tpe) {
		// TODO Auto-generated method stub
		if(tpe.getActiveCount()>0)
		{
			try {
				Thread.sleep(1000*5);
				System.out.println("still have alive thread");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			tpe.shutdown();
	}

}
