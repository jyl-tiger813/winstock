package jyl.datacollect.tdxdata.concretedata.comparator.imp;

import java.util.HashMap;

import jyl.datacollect.tdxdata.concretedata.EffectiveAanalys;
import jyl.datacollect.tdxdata.concretedata.comparator.AnalyseComparator;
import jyl.datacollect.tdxdata.concretedata.entity.DayConcreteEntity;
/*
 * 加上总流通股本的因素
 */
public class Comparator3 extends AnalyseComparator{

	private  String comparatorName = "Comparator2";
	@Override
	public String getComparatorName() {
		// TODO Auto-generated method stub
		return comparatorName;
	}

	@Override
	public int compare(DayConcreteEntity o1, DayConcreteEntity o2) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		EffectiveAanalys.setCompareParm("netBigBuyPercent");
		float BigBuy1 = o1.datas.get("totalBigBuy") ;
		float BigBuy2 = o2.datas.get("totalBigBuy") ;
		float BigSell1 = o1.datas.get("totalBigSell") ;
		float BigSell2 = o2.datas.get("totalBigSell") ;
		HashMap<String,Float>  commuteVolumeMap = EffectiveAanalys.getCommuteVolumeMap();
		float netBigBuyPercent1 =(BigBuy1 - BigSell1)/commuteVolumeMap.get(o1.getStockCode());
		float netBigBuyPercent2 =(BigBuy2 - BigSell2)/commuteVolumeMap.get(o2.getStockCode());
		o1.datas.put("netBigBuyPercent",netBigBuyPercent1);
		o2.datas.put("netBigBuyPercent",netBigBuyPercent2);
		
		return ( netBigBuyPercent1 - netBigBuyPercent2)>=0? 1:-1;

		
	
	}

}
