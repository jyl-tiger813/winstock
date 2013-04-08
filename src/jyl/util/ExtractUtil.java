package jyl.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import org.apache.log4j.Logger;

/**
 * 类描述：  表数据抽取工具类
 * 创建人：jinyongliang
 * 创建时间：2012-5-28 上午10:25:08   
 * 修改人：jinyongliang   
 * 修改时间：2012-5-28 上午10:25:08   
 * 修改备注：   
 * @version 
 */
public class ExtractUtil {

	/**
	 * @param args
	 */
	private static final Logger logger = Logger.getLogger(ExtractUtil.class);
	private static HashSet<String> subtleColumns = new HashSet<String>();
	public static HashSet<String> getSubtleColumns() {
		return subtleColumns;
	}



	public static void setSubtleColumns(HashSet<String> subtleColumns) {
		ExtractUtil.subtleColumns = subtleColumns;
	}



	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<String[]> src = new ArrayList<String[]>();
	    String [] t1 = {"a","e","b"};
	    String [] t2 = {"c","f","d"};
	    src.add(t1);
	    src.add(t2);
	    
	    String [][]target = new String [src.size()][];
	    for(int i=0;i<src.size() ;i++)
	    {
	    	String temp = src.get(i)[2];
	    	System.out.println(temp);
	    }
	    for(int i =0;i<target.length;i++)
	    {
	    	String [] ttt = target[i];
	    	 for(int j =0;j<ttt.length;j++)
	    	 {
	    		 System.out.println(ttt[j]);
	    	 }
	    }
	}



	/**
	 * 
	 * 根据目标表的结构更新抽取一天的数据
	 * @param srcCon  源数据库连接
	 * @param tarCon  目标数据库连接
	 * @param srcTableName  源表名
	 * @param targetTableName 目标表名
	 * @param queryUniqueRowName 用于更新操作中删除的唯一列名
	 * @param updateTimeRowName  用于更新操作的时间字段名
	 * @param beginTimeStr  增量抽取的开始时间
	 * @param endTimeStr    增量抽取的结束时间
	 */
	public static boolean extTUpdateTarStruc(Connection srcCon, Connection tarCon,
			String srcTableName, String targetTableName,
			String queryUniqueRowName, String updateTimeRowName,
			String beginTimeStr, String endTimeStr) {
		HashSet<String> uniqueSet = null;
		if("ad_action_record".equals(srcTableName))
		{
			uniqueSet = new  HashSet<String>(30000);
		}
		String table_meta_fetch_sql = "SELECT * FROM "+targetTableName+" LIMIT 0,1";
		String getIDForDelSql = "select " + queryUniqueRowName + " from "
		+ srcTableName + " where " + updateTimeRowName+" >= '"+beginTimeStr +"' and "+updateTimeRowName+" <= '"+endTimeStr+"'";
		String delSql = "delete from " + targetTableName + " where "+queryUniqueRowName+" = ?";
		String getDataSql = " from "+ srcTableName + " where " + updateTimeRowName+" >= '"+beginTimeStr +"' and "+updateTimeRowName+" <= '"+endTimeStr+"'";
		String insertSql = "insert into " + targetTableName ;
		logger.info("insertSql" + insertSql);
		try {
			tarCon.setAutoCommit(false);
			Statement statementMeta = tarCon.createStatement();
			ResultSet rs = statementMeta.executeQuery(table_meta_fetch_sql);
			ResultSetMetaData rmd = rs.getMetaData();
			int columnSize = rmd.getColumnCount();
			String targetColumns = "";
			String paramStr = "";
			for(int i = 1;i<=columnSize;i++)
			{
				String columnName = rmd.getColumnName(i);
				targetColumns = targetColumns + columnName;
				paramStr = paramStr + "?";
				if(i!=columnSize)
				{
					targetColumns = targetColumns + ",";
					paramStr = paramStr + ",";
				} 
			}
			getDataSql = "select " + targetColumns + getDataSql;
			insertSql = insertSql+" ("+targetColumns+") values (" +paramStr+")";
			Statement statement = srcCon.createStatement();
			// 先用简单sql删除数据
			String delSql1 = "delete from " + targetTableName+ " where "+
			updateTimeRowName+" >= '"+beginTimeStr +"' and "+updateTimeRowName+" <= '"+endTimeStr+"'";
			tarCon.createStatement().execute(delSql1);
			tarCon.commit();
			//
			long beforeDelete = Calendar.getInstance().getTimeInMillis();
			ResultSet result1 = statement.executeQuery(getIDForDelSql);
			PreparedStatement pre = tarCon.prepareStatement(delSql);
			int delNum = 0;
			if (!"ad_site_trace".equals(srcTableName))// 数据无更新动作
		      {// 有更新情况
				while (result1.next()) {
					Object obj = result1.getObject(1);
					delNum++;
					pre.setObject(1, obj);
					pre.addBatch();
					if (delNum % 200 == 0) {
						pre.executeBatch();
						tarCon.commit();
					}
				}
				pre.executeBatch();
				tarCon.commit();
			}
			long afterDelete = Calendar.getInstance().getTimeInMillis();
			long deleteTime = (afterDelete - beforeDelete) / 1000;
			logger.info("deleteDataSuccess:" + deleteTime + "s");
			// 插入数据
			Statement statement1 = srcCon.createStatement();
			ResultSet result = statement1.executeQuery(getDataSql);
			/*
			 * //得到元数据信息 TODO 敏感信息去除在此增加逻辑代码 ResultSetMetaData rs =
			 */
			int columnCount = result.getMetaData().getColumnCount();
			//StringBuffer insertSqlJoint = new StringBuffer();
			/*for (int i = 1; i <= columnCount; i++) {
				if (i != columnCount)
					insertSqlJoint = insertSqlJoint.append("?,");
				else
					insertSqlJoint = insertSqlJoint.append("?");
			}
			insertSql = insertSql + insertSqlJoint.toString() + ")";*/
			tarCon.setAutoCommit(false);
			pre = tarCon.prepareStatement(insertSql);
			int i = 0;
			ResultSetMetaData rsMetaData = result.getMetaData();// 敏感字段处理
			String tableName = rsMetaData.getTableName(1);
			while (result.next()) {
				if (i == 0) {
					long queryWaitTime = (Calendar.getInstance()
							.getTimeInMillis() - afterDelete) / 1000;
					logger.info("queryWaitTime:" + queryWaitTime + "s");
				}
				i++;
				boolean shouldClear = false;
				String ad_action_user_name = null;
				String ad_action_type = null;
				
				for (int j = 1; j <= columnCount; j++) {
					// 敏感字段处理
					String columnName = rsMetaData.getColumnName(j);
					Object obj = null;
					obj = result.getObject(j);
					if (obj != null) {
						String columnFullName = tableName + "." + columnName;
						// logger.info("columnFullName:"+columnFullName);
						
						//TODO 暂时注释，到正式环境需恢复
						if (isFatalColumn(columnFullName)) {
							if (columnFullName
									.equals("user_info.user_password")) {// 密码由于存在""和实际密码统在统计时还需要加以区别,""不能被覆盖掉
								String temp = (String) obj;
								if (!temp.equals("")) {
									obj = "replaced";
								}
							} else {
								obj = "replaced";
							}
						}
						
						
					}

					if ("ad_action_record".equals(tableName)) {
						// user_id,action_type
						if ("user_id".equals(columnName))
							ad_action_user_name = obj + "";
						if ("action_type".equals(columnName))
							ad_action_type = obj + "";
					}

					pre.setObject(j, obj); //
				}
				
				//TODO 正式环境需恢复
				if ("ad_action_record".equals(tableName)) {
					// user_id,action_type
					String uniqueStr = ad_action_user_name + "_"
							+ ad_action_type;
					if (uniqueSet.contains(uniqueStr))
						shouldClear = true;
					uniqueSet.add(uniqueStr);
				}

				pre.addBatch();
				if (shouldClear)
					pre.clearBatch();

				if ("ad_action_record".equals(tableName) && !shouldClear) {
					pre.executeBatch();
					tarCon.commit();
				} else if (i % 200 == 0) {
					// logger.info("200rowcommit");
					pre.executeBatch();
					tarCon.commit();
				}

			}
			pre.executeBatch();
			tarCon.commit();
			long insertDataTime = (Calendar.getInstance().getTimeInMillis() - afterDelete) / 1000;
			logger.info("insertDataTime:" + insertDataTime + "s");
			return true;
			//jobState = 1;
		} catch (Exception e) {
			//jobState = 2;
			e.printStackTrace();
			logger.error("extract " + srcTableName + " wrong", e);
			return false;
		} finally {
			try {
				if (tarCon != null)
					tarCon.close();
				if (srcCon != null)
					srcCon.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	/**
	 * @param columnName
	 * @return
	 */
	private static boolean isFatalColumn(String columnName) {
		// TODO 判断是否为敏感列
		if(subtleColumns!=null)
			return subtleColumns.contains(columnName);
		else 
		    return false;
	}




	

}
