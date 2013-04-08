package jyl.datacollect.tdxdata.concretedata.robust;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Robot;
import java.awt.Toolkit;
import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import javax.sql.DataSource;
import jyl.datacollect.sina.dailytrade.datafetcher.bean.StockNameBeanImp;
import jyl.datacollect.sina.dailytrade.datafetcher.dao.StockNameDaoImp;
import jyl.util.DatabaseHelper;

/**
 * 类描述：   
 * 创建人：jinyongliang
 * 创建时间：Apr 1, 2013 2:32:10 PM   
 * 修改人：jinyongliang   
 * 修改时间：Apr 1, 2013 2:32:10 PM   
 * 修改备注：   
 * @version 
 */
public class HistoryDataRobust extends RobustMain{
	Robot rb;
	float screenWidth;
	float screenHeight;
	private int before =2000;
	private int interval = 50; //调试时为了看清楚，可以把值设大些等待数据导出
	DataSource ds = DatabaseHelper.getDSByNameLocalConfig("sse");
//	String storeFile = "E:\\stock\\tdxdata\\concrete";
	
	String storeFile = "E:\\stock\\tdxdata\\historydata";
	Set<String> set = null;
	private int keyInterval = 50;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HistoryDataRobust hbr = new HistoryDataRobust();
		hbr.doRobust();
	}

	private void doRobust() {
		// TODO Auto-generated method stub
		try {
			rb= new Robot();
			
		} catch (AWTException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		screenWidth = screen.width;
		screenHeight = screen.height;
		valadiate();
	}

	private void valadiate() {
		// TODO Auto-generated method stub0001
		
		//String day = getDateNotWeekEnd();
		 set = getBlockCode();
		//String day = "20100810";
		File baseFile = new File(storeFile);
		String [] files = baseFile.list();
		System.out.println("size "+set.size());
		for(String file : files )
		{
			String tem = file.replaceAll(".TXT", "");
			set.remove(tem);
		}
		
		System.out.println("还需要采集 "+set.size());
		rb.delay(before);
		//if(3>2)return;
		for(String stockcode: set)
		{
		getOneStockConcrete(stockcode);
		}
		if(set.size()>0)  
		{
			valadiate();
			
		}
	}
	//TODO  需进行相关修改
	private void getOneStockConcrete(String stockcode) 
	{
		// TODO Auto-generated method stub
		pressKeyStringAllNumber(stockcode);
		pressKeyReturn();
		pressKeyOneNumber(46);//.
		pressKeyStringAllNumber("501");	
		pressKeyReturn();
		pressKeyOneNumber(112);
		pressKeyStringAllNumber("34");
		pressKeyReturn();
		pressKeyReturn();
		rb.delay(interval);
		waiforDataExport(); //等待数据导出
	//	rb.delay(interval);
	//00	System.out.println("interval:"+interval);
		pressKeyOneNumber(27);//esc
		
	}
	
	//TODO 需要较多修改
	private void waiforDataExport() {
		// TODO Auto-generated method stub
		//341,335  385,366
			int xPosition = 341;
		int yPosition = 335;
		int xScope = 40;
		int yScope = 60
		;
		Color [][] datas = new Color[xScope][yScope];
		/*rb.mouseMove(xPosition,yPosition);
		rb.delay(2000);
		rb.mouseMove(xPosition+xScope,yPosition+yScope);
		rb.delay(10000);*/
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
					}
					datas[i][j] = col;
				}
			}
		System.out.println("after");
			/*rb.mouseMove(xPosition+xScope,yPosition+yScope);
			rb.delay(1000);*/
		}	
	}

	
	private Set<String> getBlockCode() {
		// TODO Auto-generated method stub00006
		
		StockNameDaoImp snDao = new StockNameDaoImp();
		String querySql = "select * from sse.stocknames";
		Connection con = null;
		try {
			con = ds.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<StockNameBeanImp>  stockBeans = snDao.getBeans(querySql, con);
		HashSet<String> set = new HashSet<String>();
		for(StockNameBeanImp bean : stockBeans)
		{
			set.add(bean.getCodeId());
		}
		return set;
	}

}
