package jyl.datacollect.tdxdata.concretedata;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import jyl.datacollect.tdxdata.dao.TdxDataJdbcImp;

/*
 * 用于设置10日20日,50日等累计一天累计下降幅度
 */
public class HistoryData2Dealer {

	/**
	 * @param args
	 */
	String stockCode = "";
	HashMap<Date,List<Float>> datas ;
	List<Date> dateList = new ArrayList<Date>();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HistoryData2Dealer hd = new HistoryData2Dealer();
		hd.dealAallDatesOneStrock("000002");
	}
	private void dealAallDatesOneStrock(String stockCode) {
		// TODO Auto-generated method stub
		datas = new HashMap<Date,List<Float>>();
		this.stockCode = stockCode;
		getAllDatas();
		setDatas();
		insertdatas();
		
	}

	private void insertdatas() {
		// TODO Auto-generated method stub
		TdxDataJdbcImp tjd = new TdxDataJdbcImp();
		tjd.setTotalDownDatasOneStock(stockCode,datas);
	}
	private void setDatas() {
		// TODO Auto-generated method stub
		Set<Date> days = datas.keySet();
		//Set<Entry<Date, List<Float>>> days = datas.entrySet();
		LinkedList<Float> [] list = new LinkedList[5];
		int [] params = {10,20,50,100,250};
		for(int i=0;i<list.length;i++)
		{
			list[i] =new LinkedList<Float>();
		}
		/*LinkedList<Float> list10 = new LinkedList<Float>();
		LinkedList<Float> list20 = new LinkedList<Float>();
		LinkedList<Float> list50 = new LinkedList<Float>();
		LinkedList<Float> list100 = new LinkedList<Float>();
		LinkedList<Float> list250 = new LinkedList<Float>();*/
		Float [] total1= {0.0f,0.0f,0.0f,0.0f,0.0f};
		
		for(Date date:dateList)
		{
			List<Float> da = datas.get(date);
			for(int i=0;i<list.length;i++)
			{
				LinkedList<Float> lf =	list[i] ;
				
				if(lf.size()<params[i])
				{
					 total1[i] =  total1[i] +da.get(0);
					 lf.add(da.get(0));
					 da.add(1.0f);//累计日期不够,1.0代表没有有效数据
					 
				}
				else
				{
					float first = lf.removeFirst();
					lf.add(da.get(0));
					total1[i] =  total1[i] +da.get(0)-first;
					da.add(total1[i]/params[i]);
				}
			}
		}
		//System.out.println("date :"+date);
	}

	private void getAllDatas() {
		// TODO Auto-generated method stub
		TdxDataJdbcImp tjd = new TdxDataJdbcImp();
		tjd.getTotalDownDatasOneStock(stockCode,datas,dateList);
	}

}
