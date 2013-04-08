package jyl.datacollect.sse.bo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import jyl.datacollect.sse.dao.RecordDaoJdbcImp;
import jyl.util.JdbcUtil;
import jyl.util.MyCalendar;

public class Validate {
	/**
	 * 得到缺少数据的股票交易日期
	 */
	private  Calendar beginDay;
	private   Calendar overDay;
	private  Calendar currentDay;
	private RecordDaoJdbcImp  rdj;
	public RecordDaoJdbcImp getRdj() {
		if(rdj==null)
			setRdj();
		return rdj;
	}
	public void setRdj() {
		rdj=new RecordDaoJdbcImp();
	}
	 
	private List<Calendar> getAllDays(){
		List<Calendar> allDays=new ArrayList<Calendar>();
		//必须是拷贝，否则会改变overDay的值
        currentDay=(Calendar)overDay.clone()  ;
		while(currentDay.after(beginDay)){
			//添加的必须是拷贝，否则指向同一个对象
			allDays.add(allDays.size(),(Calendar)currentDay.clone());
			currentDay=nextDay(currentDay);
			
		}
	//	allDays.add(allDays.size(),currentDay);
		return allDays;
		
	}
	
	private Calendar nextDay(Calendar cal){
	cal.add(Calendar.DAY_OF_YEAR,-1);
	while(cal.get(Calendar.DAY_OF_WEEK)==Calendar.SATURDAY||cal.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY)
		cal.add(Calendar.DAY_OF_MONTH,-1);		
			return cal;	
	}
	/*public void findAndSave(){
		currentDay=beginDay;
		while(currentDay.before(overDay)){
			currentDay=nextDay(currentDay);
			if(findRecord(currentDay)==null){
				System.out.println("找不到记录"+currentDay.toString());
			}
			else if(findCease(currentDay)==null){
				System.out.println("也不是停牌日"+currentDay.toString());
			Lack l1=new Lack();
			l1.setTime(currentDay);
			try{
				saveLack(l1);
			}catch(Exception e){
				e.printStackTrace();
			}
			}
		}
	}*/
	
	
	private  void saveLack(List<Calendar> lack){
		RecordDaoJdbcImp rdjTemp=getRdj();
		rdjTemp.saveLack(lack);
	}
	
	
	/*
	 * 所有有效日期去掉有记录的日期和停牌日期，得到没有记录的集合
	 * 如果该集合为空，表示截止该日期数据已经全部抓取完，可以修改配置文件中的日期
	 */
	private  List<Calendar> getLack(List<Calendar> allDays,List<Calendar> hasGetRecord,List<Calendar> ceaseDay ){
		/*for(Calendar cal : hasGetRecord )
			System.out.println(MyCalendar.getString(cal)+"\t"+cal.getTimeInMillis());
		for(Calendar cal : allDays )
			System.out.println(MyCalendar.getString(cal)+"\t"+cal.getTimeInMillis());*/
		allDays.removeAll(hasGetRecord);
		allDays.removeAll(ceaseDay);
		System.out.println("length"+allDays.size());
		return allDays;
	}
	
	/**
	 * 清空lack表
	 */
	
	private  void clearAll(){
		Connection con=JdbcUtil.getConnection();
			String sql="delete from sse.lack";
			try {
				PreparedStatement pr=con.prepareStatement(sql);
				pr.execute();
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	}
	
	public boolean  doValidate(){
		RecordDaoJdbcImp rdjTemp=getRdj();
	//	clearAll();
		List<Calendar> allDays =getAllDays();
		System.out.println("beginDay  "+beginDay.get(Calendar.DAY_OF_MONTH)+"overDay "+overDay.get(Calendar.DAY_OF_MONTH));
		List<Calendar> hasGetRecord =rdjTemp.getHasRecordDays(beginDay, overDay);
		List<Calendar> ceaseDay=rdjTemp.getCeaseDays(beginDay, overDay);
		List<Calendar> lack=getLack(allDays, hasGetRecord, ceaseDay );
		System.out.println("allDays"+allDays.size()+"\thasGetRecord"+hasGetRecord.size()+"\tceaseDay"+ceaseDay.size()+"\tlack"+lack.size());
		if(lack.size()<=0)
		{
			return true;
		}
		saveLack(lack);//jyl
		return false;
		
	}
	public  Calendar getBeginDay() {
		return beginDay;
	}
	public void setBeginDay(Calendar beginDay1) {
		beginDay = beginDay1;
	}
	public  Calendar getCurrentDay() {
		return currentDay;
	}
	public  void setCurrentDay(Calendar currentDay1) {
		currentDay = currentDay1;
	}
	public  Calendar getOverDay() {
		return overDay;
	}
	public  void setOverDay(Calendar overDay1) {
		overDay = overDay1;
	}
	public static void main(String args[]){
		Validate validate=new Validate();
		validate.setOverDay(new GregorianCalendar(2009,2,27));
		 validate.setBeginDay(new GregorianCalendar(2000,1,24));
		 validate.doValidate();
		/*Calendar cal1=new  GregorianCalendar(2008,5,14);
		Calendar cal2=new GregorianCalendar(2007,5,10);
		System.out.println(cal1.after(cal2));*/
		
	}
}