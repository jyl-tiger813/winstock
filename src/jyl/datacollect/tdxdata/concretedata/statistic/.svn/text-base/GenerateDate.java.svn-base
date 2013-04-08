package jyl.datacollect.tdxdata.concretedata.statistic;

import jyl.util.JdbcUtil;

public class GenerateDate {

	/**
	 * 对分时成交明细的结果进行统计，主要是大单净买入与第二日价格的n日累计，借以生成参考数据
	 * 1,5,10,20,30,60,150,300
	 * 可以借鉴BuySellUpDownSetter类
	 */
	private int[] periods ={1,5,10,20,30,60,150,300};
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GenerateDate gnd = new GenerateDate();
		gnd.creatTableStatistic("tdxdataanalysis.concretedata_szag");
	}

	private void creatTableStatistic(String string) {
		String column1 = "netBigBuyPercent";
		String column2 = "upanddownnextday";
		String str1 = "CREATE TABLE " ;
		String str2 = " (changetime DATETIME, stockcode VARCHAR(10)";
		String str3 = " )";
		String varchartyepe1 = "  float ";
		String sql ="";
		StringBuffer strDynamic = new StringBuffer();
		/*
		 * 拼接sql字符串
		 */
		for(int i : periods)
		{
			strDynamic.append(",");
			strDynamic.append(column1+i);
			strDynamic.append(varchartyepe1);
			strDynamic.append(",");
			strDynamic.append(column2+i);
			strDynamic.append(varchartyepe1);
		}
		sql = str1+string+"_sta"+str2+strDynamic.toString()+str3;
		System.out.println("sql:"+sql);
		JdbcUtil.executeSql(sql);
	}

}
