package jyl.datacollect.sse.bo;

import java.util.Calendar;
import java.util.HashMap;

import jyl.util.JdbcUtil;
import jyl.util.MyCalendar;

public class DealDataAddBo {
	private static int[] params={5,10,30,60,90,120,300};
public static final String  dateType="date";

/*
 * 更新Dealedrecord表
 */
public void addToDealedrecord(){
	Calendar startCal=MainService.getStopCal();
	Calendar stopCal=MainService.getCal();
    String sqlString="insert into  sse.dealedRecord (time,changeRatio,volumnPerShare,amountPerShare)select time ," +
		"amount/circulateTotal  ,volumn/hundredShare ," +
		"amount*10000/hundredShare  from sse.record where time>="+MyCalendar.getString(startCal)+" and time<= "+MyCalendar.getString(stopCal);
    JdbcUtil.executeSql(sqlString);
}

/*
 * 更新Dealedrecord1表
 */
public void addToDealedrecord1(){
	Calendar startCal=MainService.getStopCal();
	Calendar stopCal=MainService.getCal();
	//(time,changeRatio,volumnPerShare,amountPerShare)
    String sqlString="replace into  sse.dealedRecord1 (time,seqnum,volumnchange) select time,seqnum,volumnchange" +
		"  from sse.dealedRecord where time>='"+MyCalendar.getString(startCal)+"' and time<= '"+MyCalendar.getString(stopCal)+"'";
    System.out.println("sqlstring "+sqlString);
    JdbcUtil.executeSql(sqlString);
}

/*
 * 产生新加行的volumnchange数据
 */
public void generateVolumnchange(){
	Calendar startCal=MainService.getStopCal();
	Calendar stopCal=MainService.getCal();
 String getVolumnSub="update  sse.dealedrecord  d1,sse.dealedrecord  d2 "+
	"set d1.volumnchange=(d1.volumnPerShare-d2.volumnPerShare)/d1.volumnPerShare "+
				" where d2.seqnum=d1.seqnum-1 and d1.time>='"+MyCalendar.getString(startCal)+"' and d1.time<= '"+MyCalendar.getString(stopCal)+"' and d2.volumnPerShare>0 and d1.volumnPerShare>0" ;
 	System.out.println("sqlString "+getVolumnSub);
}
/*
 *产生新加数据的行号
 */
 private void seqnumadd (){
	 Calendar startCal=MainService.getStopCal();
		Calendar stopCal=MainService.getCal();
	 String callseqnumpro="{call sse.seqnumAdd(?,?)}";
	 HashMap<String,HashMap <String,Object>> params=new HashMap<String,HashMap <String,Object>>();
	 HashMap<String,Object> dateMap=new HashMap<String,Object>();
	 dateMap.put("starttime", new java.sql.Date(startCal.getTime().getTime()));
	 dateMap.put("stoptime", new java.sql.Date(stopCal.getTime().getTime()));
	 params.put(this.dateType, dateMap);
	 JdbcUtil.executeProcedureWithParam( params,callseqnumpro);
 }
 
 private  void modifyColumnData(String colunmname ){
	 Calendar startCal=MainService.getStopCal();
		Calendar stopCal=MainService.getCal();
 	//更新数据
 	for(int temp:params){
 		addDataOneColumn(colunmname,temp,MyCalendar.getString(startCal),MyCalendar.getString(stopCal));
		}
 }
 private static void addDataOneColumn (String columnname,int interval,String starttime,String stoptime){
 	String sqlTemp ="update sse.dealedrecord1 d1 set d1."+columnname+interval+"=(select sum(d2."+columnname+")  from sse.dealedrecord d2 where "
 			+"d2.seqnum<=d1.seqnum and d2.seqnum>d1.seqnum-"+interval+"  and d1.seqnum>"+interval+" )/"+interval+" where time>='"+starttime+"' and time<='"+stoptime+"'" ;
 	System.out.println("sqlString "+sqlTemp);
 	
 	JdbcUtil.executeSql(sqlTemp);
 //	String sqlTemp="update sse.dealedRecord set "+
 //	columnname+interval+"=select"
 }
 public static void main(String args[]){
	 DealDataAddBo da=new DealDataAddBo();
	// da.seqnumadd();增加更新后数据的行号
	// da.addToDealedrecord1();
	// da.generateVolumnchange();
	 da.modifyColumnData("volumnchange");
	 da.modifyColumnData("changeRatio");
 }
}
