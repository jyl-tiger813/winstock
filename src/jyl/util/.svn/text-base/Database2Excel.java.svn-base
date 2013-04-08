package jyl.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.baidu.rencai.cron.DatabaseHelper;


public class Database2Excel {

	/**
	 * @param args
	 */
	
	private static final Logger logger = Logger.getLogger(Database2Excel.class); 
	private int currentRows = 0;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*user: bispider
		password: Yzd#F9P58
		ip ：211.151.137.89*/
		/* String user = "root";
		 String password = "root";
		 String url = "jdbc:mysql://localhost:3306/sr_spider?useUnicode=true&characterEncoding=utf8";
		 String sqlStr = " SELECT sr.name,sr.type,sr.size,sr.industry,sr.address,sr.description  " +
		 		"FROM sr_company_spider_unique sr ,spider_company_num sn WHERE sr.name = sn.name limit 300000,100000 ";
	*/	
		/*String user = "root";
		 String password = "root";
		 String url = "jdbc:mysql://localhost:3306/incrementbak?useUnicode=true&characterEncoding=utf8";
		 String sqlStr = " SELECT * from company15000 ";
	
		String  fileStr = "D:\\company15000.xls";
		 Connection con = null;
		 con = DatabaseHelper.getAdConnectionByParams(url, user, password);
		 Database2Excel.exportDataPages(con, sqlStr, fileStr, null);*/
	//	Database2Excel.exportData(con, sqlStr, fileStr, null);
		Database2Excel d2 = new  Database2Excel();
		d2.exportSearchKeyWords();
	
		
	}

	/**
	 * 
	 */
	private void exportSearchKeyWords() {
		String sqlStr = "SELECT * FROM search_key_word where ORDER BY search_num DESC  ";
		String user = "root";
		 String password = "root";
		 String url = "jdbc:mysql://localhost:3306/apacheloganalysis?useUnicode=true&characterEncoding=utf8";
		String  fileStr = "D:\\key_word.xls";
		 Connection con = null;
		 con = DatabaseHelper.getAdConnectionByParams(url, user, password);
		 this.exportDataPages(con, sqlStr, fileStr, null);
		
	}

	/*
	 * 使用jxl生成xls文档
	 */
	public static void exportData(Connection con, String sqlStr, String tarGetFile,String [] columnNames) {
		// 如果columnNames为空,则没有列名行
		try {
			// Workbook bookModel=Workbook.getWorkbook(new
			// File(StatisticConstant.unAuditCompanyFile));
			WritableWorkbook book = Workbook
					.createWorkbook(new File(tarGetFile));
			WritableSheet sheet = book.createSheet("sheet1", 0);
			fillValues(sheet,sqlStr,con,columnNames);
			//写入列名
			book.write();
			book.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
			logger.error("exportInitFile",e);
		} finally {
			if (con != null)
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
	
	
	/*
	 * 使用jxl生成xls文档(多页)
	 */
	public static void exportDataPages(Connection con, String sqlStr, String tarGetFile,String [] columnNames) {
		// 如果columnNames为空,则没有列名行
		try {
			// Workbook bookModel=Workbook.getWorkbook(new
			// File(StatisticConstant.unAuditCompanyFile));
			WritableWorkbook book = Workbook
					.createWorkbook(new File(tarGetFile));
			fillValuesPages(book,sqlStr,con,columnNames);
			//写入列名
			book.write();
			book.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
			logger.error("exportInitFile",e);
		} finally {
			if (con != null)
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
	
	/**
	 * @param book
	 * @param sqlStr
	 * @param con
	 * @param columnNames
	 * @throws WriteException 
	 * @throws RowsExceededException 
	 * @throws SQLException 
	 */
	private static void fillValuesPages(WritableWorkbook book, String sqlStr,
			Connection con, String[] colNames) throws RowsExceededException, WriteException, SQLException {
		int sheetNum = 0;
		WritableSheet sheet = book.createSheet("sheet0", sheetNum);
		sheet.setColumnView(0, 20);
		sheet.setColumnView(0, 20);
		int j1 = 0;
		if(colNames!=null)
		{
		for(String colName : colNames)
		{
			Label label3 = new Label(j1 , 0, colName);
			sheet.addCell(label3);
			j1++;
		}
		}
		Statement statement = con.createStatement();
		ResultSet result1 = statement.executeQuery(sqlStr);
		int columnCount = result1.getMetaData().getColumnCount();
		int i = 1;
		while (result1.next()) {
			for (int j=1; j <= columnCount; j++) {
				Object obj = result1.getObject(j);
				String temp = "";
				if (obj != null)
					temp = obj.toString();
				Label label2 = new Label(j - 1, i, temp);
				sheet.addCell(label2);
			}
			if(i%50000==0)
			{//创建一个新的sheet
				sheetNum++;
				sheet =  book.createSheet("sheet"+sheetNum, sheetNum);
				i=1;
			}
			System.out.println("rowNum:"+i);
			i++;
		}
		
	}

	/*
	 * 使用poi生成xlsx文档
	 */
	public static void exportDataXLSX(Connection con, String sqlStr, String tarGetFile,String [] columnNames) {
		// 如果columnNames为空,则没有列名行
		try {
			// Workbook bookModel=Workbook.getWorkbook(new
			// File(StatisticConstant.unAuditCompanyFile));
			/*WritableWorkbook book = Workbook
					.createWorkbook(new File(tarGetFile));
			WritableSheet sheet = book.createSheet("sheet1", 0);
			fillValues(sheet,sqlStr,con,columnNames);
			//写入列名
			book.write();
			book.close();*/
			


			//输出流
			OutputStream os = new FileOutputStream(tarGetFile); 
			//工作区
			XSSFWorkbook wb = new XSSFWorkbook();
			//创建第一个sheet
			XSSFSheet sheet= wb.createSheet("sheet1");
			//生成第一行
			fillValuesXLSX(sheet,sqlStr,con,columnNames,os);
			/*XSSFRow row = sheet.createRow(0);
			//给这一行的第一列赋值
			row.createCell(0).setCellValue("column1");
			//给这一行的第一列赋值
			row.createCell(1).setCellValue("column2");*/
			//写文件
			wb.write(os);
			//关闭输出流
			os.close();
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
			logger.error("exportInitFile",e);
		} finally {
			if (con != null)
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}

	/**
	 * @param os 
	 * @param sheet
	 * @param sqlStr
	 * @param con
	 * @param columnNames
	 * @throws  
	 */
	private static void fillValuesXLSX(XSSFSheet sheet, String sqlStr,
			Connection con, String[] colNames, OutputStream os)  {
		// TODO Auto-generated method stub
		/*sheet.setColumnView(0, 20);
		sheet.setColumnView(0, 20);
		int j1 = 0;
		if(colNames!=null)
		{
		for(String colName : colNames)
		{
			Label label3 = new Label(j1 , 0, colName);
			sheet.addCell(label3);
			j1++;
		}
		}
		Statement statement = con.createStatement();
		ResultSet result1 = statement.executeQuery(newwestActivityAdAcceptApplyYestSqlStr);
		int columnCount = result1.getMetaData().getColumnCount();
		int i = 1;
		while (result1.next()) {
			for (int j=1; j <= columnCount; j++) {
				Object obj = result1.getObject(j);
				String temp = "";
				if (obj != null)
					temp = obj.toString();
				Label label2 = new Label(j - 1, i, temp);
				sheet.addCell(label2);
			}
			i++;
		}
		*/
		sheet.setDefaultColumnWidth(8); 
		int j1 = 0;
		if(colNames!=null)
		{
			XSSFRow row = sheet.createRow(0);
		for(String colName : colNames)
		{
			/*Label label3 = new Label(j1 , 0, colName);
			sheet.addCell(label3);*/
			
			row.createCell(j1).setCellValue(colName);
			j1++;
		}
		}
		Statement statement;
		try {
			statement = con.createStatement();
			ResultSet result1 = statement.executeQuery(sqlStr);
			int columnCount = result1.getMetaData().getColumnCount();
			int i = 1;
			while (result1.next()) {
				XSSFRow rowValue = sheet.createRow(i);
				for (int j=1; j <= columnCount; j++) {
					Object obj = result1.getObject(j);
					String temp = "";
					if (obj != null)
						temp = obj.toString();
					rowValue.createCell(j-1).setCellValue(temp);
					
				}
				System.out.println("rowNum:"+i);
				i++;
				/*if(i%50000==0)
				{
					sheet.getWorkbook().write(os);
					os.flush();
				}*/
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
	}

	/**
	 * @param con
	 * @param targetExcelPathStr2 
	 * @param sheetName2 
	 * @param sheet1相关的sql
	 * @param sheet2相关的sql
	 * @param 文件名
	 * @param sheet1的列名
	 * @param sheet2的列名
	 */
	public static void exportData(Connection con,
			String newwestActivityAdAcceptApplyYestSqlStr,
			String newwestActivityAdFilterApplyYestSqlStr,
			String sheetName1, String sheetName2, String targetExcelPathStr2, String[] colNames, String[] colNames1) {
		try {
			// Workbook bookModel=Workbook.getWorkbook(new
			// File(StatisticConstant.unAuditCompanyFile));
			logger.info("filePath:"+targetExcelPathStr2);
			WritableWorkbook book = Workbook
					.createWorkbook(new File(targetExcelPathStr2));
			WritableSheet sheet = book.createSheet(sheetName1, 0);
			WritableSheet sheet2 = book.createSheet(sheetName2, 1);
			fillValues(sheet,newwestActivityAdAcceptApplyYestSqlStr,con,colNames);
			fillValues(sheet2,newwestActivityAdFilterApplyYestSqlStr,con,colNames);
			//写入列名
			book.write();
			book.close();
		} catch (Exception e) {
			logger.error("exportInitFile",e);
		} finally {
			if (con != null)
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
	}

	/**
	 * @param sheet
	 * @param newwestActivityAdAcceptApplyYestSqlStr
	 * @param con
	 * @param colNames
	 * @throws WriteException 
	 * @throws RowsExceededException 
	 * @throws SQLException 
	 */
	private static void fillValues(WritableSheet sheet,
			String newwestActivityAdAcceptApplyYestSqlStr, Connection con,
			String[] colNames) throws RowsExceededException, WriteException, SQLException {
		logger.info("sheetName:"+sheet.getName());
		logger.info("sqlStr:"+newwestActivityAdAcceptApplyYestSqlStr);
		sheet.setColumnView(0, 20);
		sheet.setColumnView(0, 20);
		int j1 = 0;
		if(colNames!=null)
		{
		for(String colName : colNames)
		{
			Label label3 = new Label(j1 , 0, colName);
			sheet.addCell(label3);
			j1++;
		}
		}
		Statement statement = con.createStatement();
		ResultSet result1 = statement.executeQuery(newwestActivityAdAcceptApplyYestSqlStr);
		int columnCount = result1.getMetaData().getColumnCount();
		int i = 1;
		while (result1.next()) {
			for (int j=1; j <= columnCount; j++) {
				Object obj = result1.getObject(j);
				String temp = "";
				if (obj != null)
					temp = obj.toString();
				Label label2 = new Label(j - 1, i, temp);
				sheet.addCell(label2);
			}
			i++;
		}
		
	}

	/**
	 * @param con
	 * @param newwestActivityAdAcceptApplyYestSqlStr
	 * @param newwestActivityAdFilterApplyYestSqlStr
	 * @param sheetName1
	 * @param sheetName2
	 * @param targetExcelPathStr
	 * @param colNames
	 * @param colNames1
	 */
	public static void exportDataFromSrc(Connection con,
			String newwestActivityAdAcceptApplyYestSqlStr,
			String newwestActivityAdFilterApplyYestSqlStr, String sheetName1,
			String sheetName2, String targetExcelPathStr,String modelStr, String[] colNames,
			String[] colNames1) {
		// TODO Auto-generated method stub
		//	
		File model = new File(modelStr);
		File reportTargetFile = new File(targetExcelPathStr);
		try {
			FileUtils.copyFile(model,reportTargetFile);
			Workbook wb = Workbook.getWorkbook(reportTargetFile);
			WritableWorkbook book = Workbook.createWorkbook(reportTargetFile,
					wb);
WritableSheet sheet = book.getSheet( 0);
WritableSheet sheet2 = book.getSheet( 1);
fillValues(sheet,newwestActivityAdAcceptApplyYestSqlStr,con);
fillValues(sheet2,newwestActivityAdFilterApplyYestSqlStr,con);
//写入列名
book.write();
book.close();
		} catch (RowsExceededException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}

	/**
	 * @param sheet
	 * @param newwestActivityAdAcceptApplyYestSqlStr
	 * @param con
	 * @throws SQLException 
	 * @throws WriteException 
	 * @throws RowsExceededException 
	 */
	private static void fillValues(WritableSheet sheet,
			String newwestActivityAdAcceptApplyYestSqlStr, Connection con) throws SQLException, RowsExceededException, WriteException {
		sheet.setColumnView(0, 20);
		sheet.setColumnView(0, 20);
		Statement statement = con.createStatement();
		ResultSet result1 = statement.executeQuery(newwestActivityAdAcceptApplyYestSqlStr);
		int columnCount = result1.getMetaData().getColumnCount();
		int i = 1;
		while (result1.next()) {
			for (int j=1; j <= columnCount; j++) {
				Object obj = result1.getObject(j);
				String temp = "";
				if (obj != null)
					temp = obj.toString();
				Label label2 = new Label(j - 1, i, temp);
				sheet.addCell(label2);
			}
			i++;
		}
		
	}
	
	

}
