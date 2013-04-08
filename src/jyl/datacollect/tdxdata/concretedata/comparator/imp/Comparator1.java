package jyl.datacollect.tdxdata.concretedata.comparator.imp;

import jyl.datacollect.tdxdata.concretedata.EffectiveAanalys;
import jyl.datacollect.tdxdata.concretedata.comparator.AnalyseComparator;
import jyl.datacollect.tdxdata.concretedata.entity.DayConcreteEntity;
/*
 * 最原始的比较器,没有加上总流通股本的因素
 */
public class Comparator1 extends AnalyseComparator{

	private  String comparatorName = "InitialComparator";
	@Override
	public String getComparatorName() {
		// TODO Auto-generated method stub
		return comparatorName;
	}

	@Override
	public int compare(DayConcreteEntity o1, DayConcreteEntity o2) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		EffectiveAanalys.setCompareParm("totalBigBuyRatio");
		float bigbuy =o1.datas.get("totalBigBuyRatio") ;
		float bigSell = o1.datas.get("totalBigSellRatio");
		float debigbuy =o2.getDatas().get("totalBigBuyRatio") ;
		float debigSell = o2.getDatas().get("totalBigSellRatio");
		float thisless = bigbuy - bigSell;
		float deless = debigbuy - debigSell;
		//return ( bigbuy - debigbuy)>0? 1:-1;
		if(thisless>=0&&deless>=0)
		{
			return ( bigbuy - debigbuy)>0? 1:-1;

		}else if(thisless<0&&deless<0)
		{
			return ( bigSell - debigSell)>0? - 1:1;

		}else if(thisless>=0&&deless<0)
		{
			return 1;
		}else 
		{
			return -1;
		}
		
	
	}

}
