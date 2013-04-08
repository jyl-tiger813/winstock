package jyl.util;

import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class ShutDownMac {

	/**
	 * @param args
	 */
	/*public static void main(String[] args) {
		// TODO Auto-generated method stub
//Runtime.getRuntime().exec("shutdown.exe -s -t 10");
		ShutDownMac sd = new ShutDownMac();
		sd.shutDownIn(1000);
	//	sd.shutAtTime("2010/08/17/15/16");
	}*/

	public static void shutAtTime(String string) {
		// TODO Auto-generated method stub
	//	Calendar cal = new GregorianCalendar(2010,);
		//cal.se
	}

	public static void shutDownIn(int i) {
		// TODO Auto-generated method stub
		long now = Calendar.getInstance().getTimeInMillis();
		while(true)
		{
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			long waitTime = Calendar.getInstance().getTimeInMillis()-now;
			System.out.println("waitTime : "+waitTime);
			if(waitTime>i)
			{
				try {
					Runtime.getRuntime().exec("shutdown.exe /f /s ");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			}
		}
	}

}
