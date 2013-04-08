package jyl.datacollect.sse.bo;

import jyl.util.JdbcUtil;

/**
 * 
 * @author sq
 * 初使化数据的时候使用
 *
 */
public class DealDataBoFirstTime {
	//在已有数据的情况下，使用insert,还得加上日期条件，避免重复查询和输入
	private static String createInitData="create table sse.dealedRecord as select time ," +
			"amount/circulateTotal changeRatio ,volumn/hundredShare volumnPerShare," +
			"amount*10000/hundredShare amountPerShare from sse.record ";
	private static String modifyDealedRecord ="alter table sse.dealedRecord add" +
			"(seqnum  integer,volumnchange1 double(10,6))";
	//,seqnum ,volumnchange1
	private static String getVolumnSub="update  sse.dealedrecord  d1,sse.dealedrecord  d2 "+
"set d1.volumnchange=(d1.volumnPerShare-d2.volumnPerShare)/d1.volumnPerShare "+
			" where d2.seqnum=d1.seqnum-1 and d2.volumnPerShare>0 and d1.volumnPerShare>0" ;
	private static String callseqnumpro="{call sse.seqnumPro()}";
	private static int[] params={5,10,30,60,90,120,300};
	/*
	 * 用于更新数据
	 */
    public static void commonForUpdate(String colunmname){
    	addColumn( colunmname);
		addNewColumnData( colunmname);
    	
    }
    private static void addColumn(String colunmname){
//增加字段
    	
    	String baseString1="alter table sse.dealedRecord1 add( "; 
		String baseString2=" double(10,6),";
		for(int temp:params){
			baseString1=baseString1+colunmname+temp+" "+baseString2;
		}
		baseString1=baseString1.substring(0,baseString1.length()-1);
		baseString1=baseString1+")";
		System.out.println("baseString1 "+baseString1);
		JdbcUtil.executeSql(baseString1);//改表结构
    }
    /**
     * update某个字段的值
     * @param args
     */
    private static void addNewColumnData(String colunmname){
    	//更新数据
    	for(int temp:params){
    		dealUpdateOneColumn(colunmname,temp);
		}
    }
    private static void dealUpdateOneColumn(String columnname,int interval){
    	String sqlTemp ="update sse.dealedrecord1 d1 set d1."+columnname+interval+"=(select sum(d2."+columnname+")  from sse.dealedrecord d2 where "
    			+"d2.seqnum<=d1.seqnum and d2.seqnum>d1.seqnum-"+interval+"  and d1.seqnum>"+interval+" )" ;
    	System.out.println("sqlString "+sqlTemp);
    	JdbcUtil.executeSql(sqlTemp);
    //	String sqlTemp="update sse.dealedRecord set "+
    //	columnname+interval+"=select"
    }
	public static void main(String args[]){
		/*JdbcUtil.executeSql(createInitData);//建表
		JdbcUtil.executeSql(modifyDealedRecord);//改表结构
		JdbcUtil.executeProcedure(callseqnumpro);//调用存储过程
		JdbcUtil.executeSql(getVolumnSub);//改表的数据
		
*/		
		//commonForUpdate("changeRatio");
		//addNewColumnData("volumnchange");
		JdbcUtil.executeSql(createInitData);//建表
	}
}

