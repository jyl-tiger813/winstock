package jyl.util;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class JdbcUtil {
	
		static{
		  String driverClassName =
			  "com.mysql.jdbc.Driver";
			 // "oracle.jdbc.driver.OracleDriver";
		  try{
		    Class.forName(driverClassName);
		  }catch(Exception ex){
		    ex.printStackTrace();
		  }
		}
	    public static Connection getConnection(){
		  String url = 
			  "jdbc:mysql://localhost/sse?user=root&password=root";
		//"jdbc:oracle:thin:@127.0.0.1:1521:orcl";
		  String username = "root";
			  //"jyl";
		  String pwd = "manager";
			  //"jyl7123421";
		  Connection con = null;
		  try{
		    con =  DriverManager.getConnection(
				url,username,pwd);
		  }catch(Exception ex){
		    ex.printStackTrace();
		  }
		  return con;
		} 
		public static void printRs(ResultSet rs){
		   try{
		     StringBuffer sb = new StringBuffer();
			 ResultSetMetaData md = rs.getMetaData();
			 int cols = md.getColumnCount();
			 while(rs.next()){
			   for(int i=1;i<=cols;i++){
			     sb.append(md.getColumnName(i));
				 sb.append("="+rs.getString(i)+"  ");
			   }
			   sb.append("\n");
			 }
		     System.out.println(sb.toString());
		   }catch(Exception ex){
		     ex.printStackTrace();
		   }
		}
		public static void release( ResultSet rs,Statement stmt,Connection con){
		   try{
		     if(rs!=null) rs.close();
		   }catch(Exception ex){
		     ex.printStackTrace();
		   }
		   try{
		     if(stmt!=null) stmt.close();
		   }catch(Exception ex){
		     ex.printStackTrace();
		   }
		   try{
		     if(con!=null) con.close();
		   }catch(Exception ex){
		     ex.printStackTrace();
		   }
		}
		public static void executeSql(String sql){
			Connection con1=getConnection();
			PreparedStatement pre=null;
				try {
					pre=con1.prepareStatement(sql);
					pre.execute();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally{
					try {
						if(pre!=null)
						pre.close();
						release(null,null,con1);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
		}
		public static void executeProcedure(String procedure){
			Connection con1=getConnection();
			 CallableStatement pre=null;
				try {
					pre=con1.prepareCall(procedure);
					pre.execute();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally{
					try {
						if(pre!=null)
						pre.close();
						release(null,null,con1);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
		}
		
}
