package jyl.util;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HbnUtil {
  private static SessionFactory sf;
  static{
	  try {
		sf = new Configuration()
		           .configure()
		           .buildSessionFactory();
	  } catch (Exception e) {
		e.printStackTrace();
	  }
  }
  public static Session getSession(){
	  Session s = null;
	  if(sf!=null) s = sf.openSession();
	  return s;
  }
  public void close(Session s,PreparedStatement pre,ResultSet r){
	  try {
		r.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  try {
		pre.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	try {
		r.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }
  
  public static void main(String args[]){
	  Session session=HbnUtil.getSession();
	  System.out.println(session);
  }
  
  
  
  
  
  
}
