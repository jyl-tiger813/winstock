package jyl.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;

/**
 * 类描述：   
 * 创建人：jinyongliang
 * 创建时间：2011-12-27 上午11:07:34   
 * 修改人：jinyongliang   
 * 修改时间：2011-12-27 上午11:07:34   
 * 修改备注：   
 * @version 
 */
public class PartitionUtil {
	Connection con ;
	String sqlStr =   " SELECT PARTITION_NAME,PARTITION_DESCRIPTION FROM information_schema.PARTITIONS" +
			" WHERE  TABLE_SCHEMA = \"?1\"  AND TABLE_NAME = \"?2\" " +
			"AND PARTITION_DESCRIPTION <> 'MAXVALUE' AND PARTITION_DESCRIPTION = " +
			" ( SELECT  MAX(PARTITION_DESCRIPTION)" +
			" FROM information_schema.PARTITIONS" +
			"  WHERE TABLE_SCHEMA = \"?1\" " +
					"AND TABLE_NAME = \"?2\" " +
							"AND PARTITION_DESCRIPTION <> 'MAXVALUE'" +
							" )";
	
	public static void main(String args[])
	{
		PartitionUtil pu =new PartitionUtil();
		pu.setConnection();
	  String partitonSqlStr =  pu.getAlterPartitionStr("incrementbak.ad_site_trace",60); 
	//	String partitonSqlStr =  pu.getAlterPartitionStr("apacheloganalysis.accessRecord",60); 
	//	String partitonSqlStr =  pu.getAlterPartitionStr("incrementbak.ad_action_record",200); 
	//	String partitonSqlStr =  pu.getAlterPartitionStr("apacheloganalysis.analysis_search_condition_param",90); 
	//	String partitonSqlStr =  pu.getAlterPartitionStr("apacheloganalysis.analysis_search_condition_apply",90);
		pu.finalyze();
	}

	/**
	 * @param string
	 * @return
	 */
	private  String getAlterPartitionStr(String string,int interval) {
		String alterPartitionStr = "";
		String [] params = string.split("\\.");
		String schemaName =  params[0];
		String tableName = params[1];
		sqlStr = sqlStr.replaceAll("\\?1", schemaName);
		sqlStr = sqlStr.replaceAll("\\?2", tableName);
		try {
			HashMap <String,Object> maxPartitionInfo = DatabaseHelper.getOneRowValue(con,sqlStr);
			String maxPartitionName = (String)maxPartitionInfo.get("PARTITION_NAME");
			String maxPartitionValue = (String)maxPartitionInfo.get("PARTITION_DESCRIPTION");
			Integer nameNum = Integer.parseInt(maxPartitionName.substring(1,maxPartitionName.length()))+1;
			Integer valueNum = Integer.parseInt(maxPartitionValue);
			//暂时处理 734868 p1272      734898 p1302
			//nameNum = nameNum -30;
			//valueNum = valueNum -30;
			
			 alterPartitionStr = "ALTER TABLE "+string+" REORGANIZE PARTITION p"+nameNum+" INTO (\n";
			String backStr = "PARTITION p"+(nameNum+interval)+" VALUES LESS  THAN MAXVALUE);";
			for(int i=1;i<=interval;i++)
			{ 
				
				valueNum++;
				String pStr ="PARTITION p"+nameNum+" VALUES LESS THAN ("+valueNum+"),\n";
				alterPartitionStr =  alterPartitionStr + pStr;
				nameNum++;
			}
			alterPartitionStr = alterPartitionStr + backStr;
			System.out.println("alterPartitionStr:"+alterPartitionStr);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return alterPartitionStr;
	}

	/**
	 * 释放资源
	 */
	private void finalyze() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 获得数据库连接
	 */
	private void setConnection() {
	/* String user = "root";
	 String password = "manager";
	 String url = "jdbc:mysql://58.83.216.121:3306/incrementbak?useUnicode=true&characterEncoding=utf8";
*/
		/* String user = "bi_dev";
		 String password = "officebidev@*D!";
		 String url = "jdbc:mysql://58.68.231.37:3306/incrementbak?useUnicode=true&characterEncoding=utf8";
		*/
		//local connection
		 String user = "root";
		 String password = "root";
		 String url = "jdbc:mysql://localhost:3306/incrementbak?useUnicode=true&characterEncoding=utf8";
		
		// String url = "jdbc:mysql://localhost:3306/incrementbak?useUnicode=true&characterEncoding=utf8";
	// String url = "jdbc:mysql://58.83.216.121:3306/incrementbak?useUnicode=true&characterEncoding=utf8";
	 con = DatabaseHelper.getAdConnectionByParams(url,user,password);
	 
	}

}
