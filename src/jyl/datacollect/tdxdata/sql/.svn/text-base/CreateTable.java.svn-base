package jyl.datacollect.tdxdata.sql;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import jyl.util.JdbcUtil;

public class CreateTable {
	String before = "create table tdxdata.";
	String after = " (changeTime TIMESTAMP NOT NULL,open FLOAT,close FLOAT,high FLOAT,low FLOAT ,volumn NUMERIC ,PRIMARY KEY (`changeTime`) )";
	String insert1 = "";
	String insert2 ="";
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		CreateTable cta = new CreateTable();
		cta.createTableBuySellCompareHistory();
	//	String [] columns ={"",""};
	//	cta.createTable("999999");
	//	String url = "E:\\stock\\tdxdata\\999999.TXT";
	//	cta.insertdata( url);
	}

	private void createTableBuySellCompareHistory() {
		// TODO Auto-generated method stub
		String sqlStr = "create table tdxhistory.data_szag (stockcode varchar(7),changeTime DATETIME,";
		
		String [] columns ={
				"buyvolumn", "sellvolumn", "buyvolumnRatio",
				"totalvolumn", "close", "totalUp", "totalDown", "buyvolumn1",
				"sellvolumn1", "buyvolumn2", "sellvolumn2", "buyvolumn3",
				"sellvolumn3", "buyvolumn4", "sellvolumn4", "totalDown10",
				"totalDown20", "totalDown50", "totalDown100","totalDown250", "upDownToday",
				"upDownYest" };
		for(String str : columns )
		{
			sqlStr =sqlStr +str+" float,";
		}
		sqlStr = sqlStr.substring(0, sqlStr.length()-1)+")";
		System.out.println("sqlStr: "+sqlStr);
		JdbcUtil.executeSql(sqlStr);
		//	
	}

	private void createTableBuySellCompare() {
		// TODO Auto-generated method stub
		String sqlStr = "create table tdxbuysellcompare.concretedata_szag (stockcode varchar(7),changeTime TIMESTAMP,";
		String [] columns ={"buyvolumn","sellvolumn","netbuyvolumnpercent","buyvolumnRatio",
				"totalvolumn","close","amountPerChange","cheatBuyVolumn",
				"cheatSellVolumn","upDownToday","upDownYest"};//其他数据可以通过计算得到
		for(String str : columns )
		{
			sqlStr =sqlStr +str+" float,";
		}
		sqlStr = sqlStr.substring(0, sqlStr.length()-1)+")";
		System.out.println("sqlStr: "+sqlStr);
		JdbcUtil.executeSql(sqlStr);
		//	
	}

	private void insertdata(String url) {
		// TODO Auto-generated method stub
		File f = new File(url);
		String fileName = f.getName();
		try {
			InputStreamReader isr = new InputStreamReader(new FileInputStream(f));
			BufferedReader bfr = new BufferedReader(isr);
			bfr.readLine();
			bfr.readLine();
			bfr.readLine();//跳过前面三行
			while(bfr.ready())
			{
				
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void createTable(String string) {
		// TODO Auto-generated method stub
		JdbcUtil.executeSql(before+string+after);
		
	}

}
