package jyl.datacollect.tdxdata.concretedata.robust;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import jyl.datacollect.tdxdata.concretedata.buysellcompare.HistoryDataDealer;
import jyl.datacollect.tdxdata.dao.TdxDataJdbcImp;
import jyl.util.MyCalendar;

public class TdxHistoryConcreteGetter {

	public class DealDataAndFiles extends Thread{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			while(true)
			{
			synchronized (this)
			{
				try {
				this.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
				String stockCode = dealStocks.poll();
				while(stockCode!=null)
				{
					
					hdd.dealHs300(stockCode);
					stockCode = dealStocks.poll();
				}
				
			if(isOver==true)
				break;
			}
		}

	}

	/**
	 * @param args
	 * : 33 pageup
￿ : 33
￿ : 40 downarrow
￿ : 40
	 */
	Robot rb ;
	float screenWidth;
	float screenHeight;
	private int before =5000;
	private int interval = 1000; //调试时为了看清楚，可以把值设大些等待数据导出
	private int keyInterval = 200;
	private int days = 50;
	DealDataAndFiles dda;
	String tempFile = "E:\\stock\\tdxdata\\history\\temp";
	int fileCount = 0;
	HistoryDataDealer hdd;
	LinkedList<String> dealStocks = new LinkedList<String>();
	boolean isOver =false;
	private int problemContinieTime;
	private int waittimebase = 100;
	private int waittime;
//	 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TdxHistoryConcreteGetter thc = new TdxHistoryConcreteGetter();
	//	thc.doRobot();
		
		thc.getAllBlock( "hs300");
		
	//	hdd.dealStock("000651");
	//	BuySellUpDownSetter bus = new BuySellUpDownSetter();
		//bus.setUpDown("tdxhistory.data_szag");
	//	ShutDownMac.shutDownIn(100);
	}
	private void getAllBlock(String string) {
	// TODO Auto-generated method stub
		
		try {
			rb = new Robot();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		rb.delay(5000);
		 hdd = new HistoryDataDealer();
		TdxDataJdbcImp tdj = new TdxDataJdbcImp();
			  dda = new DealDataAndFiles();
			  DealDataAndFiles da =new DealDataAndFiles();
			  da.start();
		 List <String> stocks = tdj.getBlockCode(string);
		 for(String stock : stocks)
		 {
			 //选中某支股票
			 pressKeyOneNumber(27);//esc
			 pressKeyStringAllNumber(stock);
			 pressKeyOneNumber(10);//enter
			 rb.delay(500);
			 pressKeyOneNumber(39);//右箭
			 rb.delay(500);
			 pressKeyOneNumber(10);//enter
			
			 pressKeyOneNumber(33);//pageup
			 waitForDatas();
			if( doRobot())
			{
			
				continue;
			}
		//	validateDataComplete(stock);
			 dealStocks.push(stock);
			// TODO 唤醒数据处理进程
			 synchronized(da)
			 {
				 da.notifyAll();
			 }
		 }
		 isOver =true;
		 synchronized(da)
		 {
			 da.notifyAll();
		 }
}
	private void validateDataComplete(final String stockCode) {
		// TODO Auto-generated method stub
		File stockFiles = new File(tempFile);
		ArrayList <Calendar> dates = new ArrayList <Calendar> ();
		File [] files =stockFiles.listFiles(new FilenameFilter(){
			
			@Override
			public boolean accept(File dir, String name) {
				// TODO Auto-generated method stub
				if(name.contains(stockCode+".TXT"))
				return true;
				else 
				return false;
			}

			});
		if(files.length<days)
		{
			//采集未采集的数据
			for(File f : files)
			{
			dates.add(MyCalendar.getCalendarFromEightStr(f.getName().split("-")[0]));
			}
			Collections.sort(dates);
			}
	}
	
	private void waitForDatas() {
		// TODO Auto-generated method stub
		boolean isContin =true;
		Color baseColor = new Color(192,192,192);
		long timebegin = Calendar.getInstance().getTimeInMillis();
		
			
		while(isContin){
		for(int i=240;i<270;i++)
		 {
			 Color cor = rb.getPixelColor(793, i);
				System.out.println("cor:"+cor);
				if(cor.equals(baseColor))
				{
					isContin=false;
					break;
				}
		 }
		long afterTime = Calendar.getInstance().getTimeInMillis() - timebegin;
		if(afterTime > 2000)
		{
			break;
		}
		}
	}
	private boolean doRobot() {
		// TODO Auto-generated method stub
		waittime = waittimebase;
		File stockFiles = new File(tempFile);
		fileCount = stockFiles.listFiles().length;
		problemContinieTime = 0;
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		screenWidth = screen.width;
		screenHeight = screen.height;
		System.out.println("screenWidth : "+screenWidth+"\n"+"screenHeight:"+screenHeight);
		
		//if(3>2)return;
		boolean hasProblem= false;
		for(int i = 0;i<days;i++)
		{
			if(problemContinieTime>3){
				hasProblem= true;
				break;
			}
				
			long timebegin = Calendar.getInstance().getTimeInMillis();
		rb.mouseMove(760, 600);
		mouseClick();
		pressKeyOneNumber(40);//向下
		pressKeyOneNumber(40);
		pressKeyOneNumber(40);
		pressKeyOneNumber(40);
		pressKeyOneNumber(10);//回车
		if(waitForDialog())
		{
			i--;
			continue;//等待对话框出现
		}
		//TODO
		rb.delay(waittime);
		pressKeyOneNumber(10);
		if(waitForDialog())
		{
			i--;
			continue;//等待对话框出现
		}
		waitForDataExportBegin();
			//continue;
		waitForDataExport();
		pressKeyOneNumber(27);//esc
	if(	validateOutputFail())
		{
		pressKeyOneNumber(34);//pagedown
		i--;
		}
		pressKeyOneNumber(33);//pageup
		long afterTime = Calendar.getInstance().getTimeInMillis() - timebegin;
		System.out.println("afterTime:"+afterTime);
		if(afterTime > 10000)
		{
			hasProblem = true	;
			break;
		}
		}
		return hasProblem;
	}

	private boolean validateOutputFail() {
		// TODO Auto-generated method stub
		File stockFiles = new File(tempFile);
		int temp =stockFiles.listFiles().length;
		boolean isfail =true;
		if(temp>fileCount)
			isfail = false;
		else 
			waittime=waittime*2;
		fileCount = temp;
		return isfail;
	}
	private boolean waitForDataExportBegin() {
		// TODO Auto-generated method stub
		int xPosition = 925;
		int yPosition = 364;
		int xScope = 40;
		int yScope = 30;
		Color [][] datas = new Color[xScope][yScope];
	//	rb.mouseMove(xPosition,yPosition);
	//	rb.delay(2000);
	//	rb.mouseMove(xPosition+xScope,yPosition+yScope);
		boolean hasProblem =false;
		boolean isStop = true;
		//System.out.println("outofwhile");
		long timebegin = Calendar.getInstance().getTimeInMillis();
		while (isStop) {
			//System.out.println("begin");
			rb.delay(500);
			isStop = true;
			for (int i = 0; i < xScope; i++) {
				for (int j = 0; j < yScope; j++) {
					Color col = rb.getPixelColor(xPosition + i, yPosition + j);
					if (!col.equals(datas[i][j])) {
						isStop = false;
						datas[i][j] = col;
					}
				}
			}
		}
		 isStop = true;
		//System.out.println("outofwhile");
		while (isStop) {
			//System.out.println("begin");
			rb.delay(500);
			isStop = true;
			for (int i = 0; i < xScope; i++) {
				for (int j = 0; j < yScope; j++) {
					Color col = rb.getPixelColor(xPosition + i, yPosition + j);
					if (!col.equals(datas[i][j])) {
						isStop = false;
						datas[i][j] = col;
					}
				}
			}
			long afterTime = Calendar.getInstance().getTimeInMillis() - timebegin;
			if(afterTime > 2000)
			{
				hasProblem = true
				;
				break;
			}
		}
		return hasProblem;
	}
	private boolean waitForDialog() {
		// TODO Auto-generated method stub
		//612,403 -1250856
		boolean hasProblem= false;
		long timebegin = Calendar.getInstance().getTimeInMillis();
	while(true){
		rb.delay(60);
		Color cor = rb.getPixelColor(612, 403);
		int corl = cor.getRGB();
		if(corl==-1250856)
			break;
		long afterTime = Calendar.getInstance().getTimeInMillis() - timebegin;
		System.out.println("afterTime"+afterTime);
		if(afterTime > 5000)
		{
			hasProblem = true;
			
			break;
		}
		System.out.println("cor1"+corl);
	}
	if(hasProblem)
	{
		problemContinieTime++;
	}else
	{
		problemContinieTime = 0;
	}
		return hasProblem;
	}
	private void waitForDataExport() {
		// TODO Auto-generated method stub
		//925,364  965,394
		int xPosition = 925;
		int yPosition = 364;
		int xScope = 40;
		int yScope = 30;
		Color [][] datas = new Color[xScope][yScope];
		/*rb.mouseMove(xPosition,yPosition);
		rb.delay(1000);*/
		boolean isStop = false;
		//System.out.println("outofwhile");
		while (!isStop) {
			//System.out.println("begin");
			rb.delay(500);
			isStop = true;
			for (int i = 0; i < xScope; i++) {
				for (int j = 0; j < yScope; j++) {
					Color col = rb.getPixelColor(xPosition + i, yPosition + j);
					if (!col.equals(datas[i][j])) {
						isStop = false;
						datas[i][j] = col;
					}
				}
			}
		}
	}
	
	private void pressKeyStringAllNumber(String string) {
		// TODO Auto-generated method stub,如果是数就加上48
		char [] numbers = string.toCharArray();
		for(char cn : numbers )
		{ 
			int num = (int)cn+48;
			pressKeyOneNumber(num);
		}
		
	}
	
	/*
	 * 按下放开某个键动作一次
	 */
	private void pressKeyOneNumber(int i) {
		// TODO Auto-generated method stub
		rb.keyPress(i);
		rb.delay(keyInterval );
		rb.keyRelease(i);
	}
	
	private void mouseClick() {
		// TODO Auto-generated method stub
		rb.mousePress(InputEvent.BUTTON1_MASK);
		rb.delay(keyInterval );
		rb.keyRelease(InputEvent.BUTTON1_MASK);
	}
}
