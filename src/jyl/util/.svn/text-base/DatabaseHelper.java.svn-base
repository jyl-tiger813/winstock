package jyl.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.HashSet;
import javax.sql.DataSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DatabaseHelper {
	
	private  Connection defaultCon;
	private String defaultConSrt = "apacheloganalysis";
	
	/*
	 * 带参数购造方法
	 */
	public DatabaseHelper(String dbName)
	{
		this.defaultConSrt = dbName;
	}
	
	/**
	 * 
	 */
	public DatabaseHelper() {
	}

	public static DatabaseHelper getInstance ()
	{
		return new DatabaseHelper();
	}
	
	protected static Connection getRcConnection(){
		 String user = "rencai_test";
		 String password = "rencai_test";
		 String url = "jdbc:mysql://211.151.137.113:3306/baijob_test";
		 String driver = "com.mysql.jdbc.Driver";
		 
		 Connection con = null;
		 try{
			 Class.forName(driver);
			 con = DriverManager.getConnection(url, user, password);
		 }catch(ClassNotFoundException e){
			 System.out.println("数据库驱动不存在！");
			 System.out.println(e.toString());
		 }catch(SQLException e2)
		 {
			 System.out.println("数据库存在异常！");
			 System.out.println(e2.toString());
		 }
		 
		 return con;
	}
	public static Connection getAdConnection(){
		 String user = "rencai_dev";
		 String password = "rencai_dev";
		 String url = "jdbc:mysql://211.151.137.113:3306/rencai_ad_dev?useUnicode=true&characterEncoding=utf8";
		 String driver = "com.mysql.jdbc.Driver";
		 
		 Connection con = null;
		 try{
			 Class.forName(driver);
			 con = DriverManager.getConnection(url, user, password);
		 }catch(ClassNotFoundException e){
			 System.out.println("数据库驱动不存在！");
			 System.out.println(e.toString());
		 }catch(SQLException e2)
		 {
			 System.out.println("数据库存在异常！");
			 System.out.println(e2.toString());
		 }
		 
		 return con;
	}
	
	public static Connection getAdConnectionByParams(String url,String user,String password){
	//	 String user = "rencai_dev";
	//	 String password = "rencai_dev";
	//	 String url = "jdbc:mysql://211.151.137.113:3306/rencai_ad_dev?useUnicode=true&characterEncoding=utf8";
		 String driver = "com.mysql.jdbc.Driver";
		 
		 Connection con = null;
		 try{
			 Class.forName(driver);
			 con = DriverManager.getConnection(url, user, password);
		 }catch(ClassNotFoundException e){
			 System.out.println("数据库驱动不存在！");
			 System.out.println(e.toString());
		 }catch(SQLException e2)
		 {
			 System.out.println("数据库存在异常！");
			 System.out.println(e2.toString());
			 e2.printStackTrace();
		 }
		 
		 return con;
	}
	
	
	
	
	

	
	public static DataSource getDSByNameLocalConfig(String name )
	{
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.ds_local.xml");
		DataSource ds =(DataSource)(context.getBean(name));
		return ds;
		
	}
	public static void main(String [] args)
	{
		DatabaseHelper dbh = new DatabaseHelper();
		DataSource ds = dbh.getDSByNameLocalConfig("analysis");
		try {
			Connection con = ds.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*DataSource ds =	dbh.getDSByName("incrementbak");
	//	DataSource ds1 =	dbh.getDSByName("incrementbassk");
		System.out.println("ds:"+ds);*/
		/*String columnsStr = "daged,";
		columnsStr = columnsStr.substring(0, columnsStr.length()-1);
		System.out.println("columnsStr:"+columnsStr);*/
	/*	Connection con  = getRcConnection();	
		String filterColumnStr = "user_name,user_truename,user_password,phone,email";
		String resultColumnStr = dbh.generatResultColumnStr(con,"user_info",filterColumnStr);
	    System.out.println(resultColumnStr);*/
	}

	/**
	 * @param con
	 * @param string
	 * @param filterColumnStr
	 * @return
	 */
	private String generatResultColumnStr(Connection con, String tableName,
			String filterColumnStr) {
		String sqlStr = "select * from "+tableName +" limit 0,10";
		HashSet <String> columnNameHash = new 	HashSet <String>() ;
		String [] columns = filterColumnStr.split(",");
		for(String str : columns)
		{
			columnNameHash.add(str);
		}
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sqlStr);
			ResultSetMetaData  meta = rs.getMetaData();
			StringBuffer sbf = new StringBuffer();
			for(int i = 1; i<=meta.getColumnCount();i++)
			{
				String columnName = meta.getColumnName(i);
				if(!columnNameHash.contains(columnName))
				sbf.append(columnName+",");
			}
			return sbf.toString();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		finally{
			try {
				if(con!=null)
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * 插入一条记录,如果该记录存在,返回主键值,使用默认的connection
	 * @param tableName
	 * @param updateColumnName
	 * @param columnNames
	 * @param values
	 * @return 该条记录的主键
	 * @throws SQLException 
	 */
	public  int saveOrgetPk(String tableName,
			String updateColumnName, String[] columnNames, Object[] values) throws SQLException {
		//取得下标位置
		int columnPos = getCorrespondStr(updateColumnName,columnNames);
		Object updateConValue = values[columnPos];
		//先根据updateColumnName和updateConValue取相应的pk值
		Integer pk =  getPkByUniqueColumn(tableName,updateColumnName,updateConValue);
		if(pk==null)
			pk = saveWithoutPk(tableName,updateColumnName,updateConValue,columnNames,values);
		return pk;
	}

	

	

	

	/**
	 * @param tableName
	 * @param updateColumnName
	 * @param updateConValue
	 * @param columnNames
	 * @param values
	 * @return
	 */
	private Integer saveWithoutPk(String tableName, String updateColumnName,
			Object updateConValue, String[] columnNames, Object[] values) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @param tableName
	 * @param updateColumnName
	 * @param updateConValue
	 * @return
	 * @throws SQLException 
	 */
	private  Integer getPkByUniqueColumn(String tableName,
			String updateColumnName, Object updateConValue) throws SQLException {
		//  根据表名，更新字段和更新字段值返回主键值
		String sqlStr = "select id from "+tableName+" where "+updateColumnName+" = ? ";
		Object [] values = new Object [1];
		values[0] = updateConValue;
		Object obj = prepareQuery(sqlStr,values);
		return (Integer)(obj);
	}

	/**
	 * @param sqlStr
	 * @param values
	 * @return
	 * @throws SQLException 
	 */
	private  Object prepareQuery(String sqlStr, Object[] values) throws SQLException {
		//  带参数的预处理查询 
		Connection con =  getDefaultConnection();
		PreparedStatement preS = con.prepareStatement(sqlStr);
		int i = 1;
		for(Object obj : values)
		{
			preS.setObject(i, obj);
			i++;
		}
		ResultSet rs = preS.executeQuery();
		if(rs.next())
		return rs.getObject(1);
		return null;
	}

	

	
	
	/**
	 * @return
	 */
	private Connection getDefaultConnection() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @return
	 */
	public static Connection getDefaultConnectionSingle() {
//		 String user = "rencai_dev";
		//	 String password = "rencai_dev";
		//	 String url = "jdbc:mysql://211.151.137.113:3306/rencai_ad_dev?useUnicode=true&characterEncoding=utf8";
			 String driver = "com.mysql.jdbc.Driver";
			 String url = "jdbc:mysql://58.83.216.121:3306/incrementbak?useUnicode=true&characterEncoding=utf8";
			 String user = "root";
			 String password = "manager";
			 Connection con = null;
			 try{
				 Class.forName(driver);
				 con = DriverManager.getConnection(url, user, password);
			 }catch(ClassNotFoundException e){
				 System.out.println("数据库驱动不存在！");
				 System.out.println(e.toString());
			 }catch(SQLException e2)
			 {
				 System.out.println("数据库存在异常！");
				 System.out.println(e2.toString());
			 }
			 
			 return con;
	}
	
	/**
	 * @param updateColumnName
	 * @param columnNames
	 * @return
	 */
	private static int getCorrespondStr(String updateColumnName, String[] columnNames) {
		int i = 0 ;
		for(;i<columnNames.length;i++)
		{
			if(updateColumnName.endsWith(columnNames[i]))
			break;
		}
		return i;
	}

	/**
	 * @param con 
	 * @param createTableSql
	 * @throws SQLException 
	 */
	public static void executeSql(Connection con, String createTableSql) throws SQLException {
		Statement statement = con.createStatement();
		statement.execute(createTableSql);
	}

	

	/**
	 * @param tableName
	 * @param updateColumnName
	 * @param columnNames
	 * @param values
	 * @throws SQLException 
	 */
	public  void saveOrUpate(String tableName, String updateColumnName,
			String[] columnNames, Object[] values) throws SQLException {
		// TODO  保存或更新数据
		
		int columnPos = getCorrespondStr(updateColumnName,columnNames);
		Object updateConValue = values[columnPos];
		//先根据updateColumnName和updateConValue取相应的pk值
		Integer pk =  getPkByUniqueColumn(tableName,updateColumnName,updateConValue);
		if(pk==null)
			saveWithoutPk(tableName,columnNames,values);
		else
		{
			updateWithOutPk(tableName,updateColumnName,updateConValue,columnNames,values);
		}
	}

	/**
	 * @param tableName
	 * @param columnNames
	 * @param values
	 */
	private void saveWithoutPk(String tableName, String[] columnNames,
			Object[] values) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @param tableName
	 * @param updateColumnName
	 * @param updateConValue
	 * @param columnNames
	 * @param values
	 * @throws SQLException 
	 */
	private  void updateWithOutPk(String tableName,
			String updateColumnName, Object updateConValue,
			String[] columnNames, Object[] values) throws SQLException {
		String updateSqlStr = "UPDATE "+tableName+" SET ";
		for(String column : columnNames )
		{
			updateSqlStr = updateSqlStr+column+" = ?,";
		}
		updateSqlStr = updateSqlStr.substring(0,updateSqlStr.length()-1);//去除最后一个逗号
		updateSqlStr = updateSqlStr + " where "+updateColumnName +" =  ?" ;
		Object[] value1  = new Object [values.length+1];
		for(int i = 0;i< values.length;i++)
		{
			Object obj = values[i];
			value1[i] = obj;
		}
		value1[values.length] = updateConValue;
		excutePreparedParams(updateSqlStr,value1);
	}

	/**
	 * @param updateSqlStr
	 * @param value1
	 */
	private void excutePreparedParams(String updateSqlStr, Object[] value1) {
		// TODO Auto-generated method stub
		
	}

	public void setDefaultConSrt(String defaultConSrt) {
		this.defaultConSrt = defaultConSrt;
	}

	public String getDefaultConSrt() {
		return defaultConSrt;
	}

	/**
	 * 关闭默认的数据库联结
	 */
	public void closeCon() {

		if(defaultCon!=null)
			try {
				defaultCon.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}

	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		if(defaultCon!=null)
			try {
				defaultCon.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		super.finalize();
	}

	/**
	 * @param con
	 * @param sqlStr
	 * @throws SQLException 
	 */
	public static HashMap<String,Object> getOneRowValue(Connection con, String sqlStr) throws SQLException {
		//  返回一行记录
		Statement statement = con.createStatement();
		ResultSet rs = statement.executeQuery(sqlStr);
		ResultSetMetaData metaData = rs.getMetaData();
		HashMap<String,Object> result = new HashMap<String,Object>();
		int columnCount = metaData.getColumnCount();
		rs.next();
		for(int i = 0;i<columnCount;i++)
		{
			String columnName = metaData.getColumnName(i+1);
			Object value = rs.getObject(i+1);
			result.put(columnName, value);
		}
		return result;
	}

	public static void getValueMap(Connection con, String sqlStr, HashMap<String, Object> result) throws SQLException {
		//  返回一行记录
		Statement statement = con.createStatement();
		ResultSet rs = statement.executeQuery(sqlStr);
		while(rs.next())
		{
		String key = rs.getObject(1)+"";
		Object value = rs.getObject(2);
		result.put(key, value);
		
		}
	}

	/**
	 * @param con
	 * @param querySql
	 */
	public static HashMap<String,String> getMapDatas(Connection con, String querySql) {
	//  返回一行记录
		HashMap<String,String> result = new HashMap<String,String>();
		
		try{
		Statement statement = con.createStatement();
		ResultSet rs = statement.executeQuery(querySql);
		while(rs.next())
		{
		String key = rs.getObject(1)+"";
		String value = rs.getString(2);
		result.put(key, value);
		}
		return result;
		}catch(Exception e)
		{
			return null;
		}
		finally
		{
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	/**
	 * @param maxIdSql
	 * @param con
	 * @return 
	 */
	public static Object getOneResult(String maxIdSql, Connection con) {
			Statement statement;
			try {
				statement = con.createStatement();
				ResultSet rs = statement.executeQuery(maxIdSql);
				if(rs.next())
				{
				Object obj = rs.getObject(1);
				return obj;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return null;
	}

	/**
	 * @param metaColumns
	 * @param string
	 * @param con 
	 */
	public static void saveMapDatas(HashMap<String, Integer> metaColumns,
			String tableName, Connection con,int minNum) {
		// TODO 保存数据
		
		//INSERT INTO ext_resume_meta (m_key,m_value)VALUES('test',1)
   
	String sqlStrPart1 = "INSERT INTO "+tableName+" (m_key,m_value)VALUES(?,?)"; 
	System.out.println("sqlStrPart1:"+sqlStrPart1+"dataN:"+metaColumns.size());
	try {
		 con.setAutoCommit(false);
		PreparedStatement pre = con.prepareStatement(sqlStrPart1);
		int i =0 ;
		for(String keyStr : metaColumns.keySet())
		{
			int emgNum = metaColumns.get(keyStr);
			if(emgNum>minNum)
			{
			i++;
		 pre.setObject(1, keyStr);
		 pre.setObject(2,emgNum );
		 pre.addBatch();
		 if(i%100==0)
			 pre.executeBatch();
		 	con.commit();
			}
		}
		 pre.executeBatch();
		 con.commit();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally
	{
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	}

	/**
	 * @param con
	 */
	public void setDefaultConnection(Connection con) {
		this.defaultCon = con;
		
	}
}
