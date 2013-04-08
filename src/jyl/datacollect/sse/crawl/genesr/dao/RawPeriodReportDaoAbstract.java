package jyl.datacollect.sse.crawl.genesr.dao;
public abstract class RawPeriodReportDaoAbstract extends AbstractBaseDAO 



{


public void save(RawPeriodReportBeanImp bean,Connection con)
{String sqlStr = "insert into sse.dealedrecord()values()";
ArrayList<Object> values = new ArrayList<Object> ();

try {
	excutePreparedParams( sqlStr,  values, con);
} catch (SQLException e) {
e.printStackTrace();
}finally{
if(con!=null)
try {
	con.close();
} catch (SQLException e) {
e.printStackTrace();
}
}}



public void saveBeans(ArrayList<RawPeriodReportBeanImp> beans,Connection con)
{String sqlStrs = "";
for(RawPeriodReportBeanImp bean:beans)
{
if("".equals(sqlStrs))
sqlStrs = "insert into sse.dealedrecord()values()";
else

sqlStrs = sqlStrs+",()";}
try {
excuteSqlStrs( sqlStrs, con);
} catch (SQLException e) {
e.printStackTrace();
}finally{
if(con!=null)
try {
	con.close();
} catch (SQLException e) {
e.printStackTrace();
}
}}



public ArrayList<RawPeriodReportBeanImp>  getBeans(String  querySql,Connection con)
{ArrayList<RawPeriodReportBeanImp> result = new ArrayList<RawPeriodReportBeanImp>();
	
		Statement st = null;
		try {  
	 st = con.createStatement();
		ResultSet rs = st.executeQuery(querySql);
		while(rs.next())
			{
	RawPeriodReportBeanImp bean = new RawPeriodReportBeanImp();

		result.add(bean);
}
	} catch (SQLException e) {
	 e.printStackTrace(); 	}
	 	finally{
	 
	 		if(st!=null)
	 		try {
	 			st.close();
	 		} catch (SQLException e) {
	 		e.printStackTrace();
	 	}
	 	}
		if(result.size()>0)
			return result;
		else return null;
		}
}

