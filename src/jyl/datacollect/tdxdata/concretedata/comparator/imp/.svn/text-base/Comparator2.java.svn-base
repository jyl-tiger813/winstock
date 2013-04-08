package jyl.datacollect.tdxdata.concretedata.comparator.imp;

import java.util.HashMap;

import jyl.datacollect.tdxdata.concretedata.EffectiveAanalys;
import jyl.datacollect.tdxdata.concretedata.comparator.AnalyseComparator;
import jyl.datacollect.tdxdata.concretedata.entity.DayConcreteEntity;
/*
 * 加上总流通股本的因素
 */
public class Comparator2 extends AnalyseComparator{

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
		return ( o1.datas.get("netBigBuyPercent") -  o2.datas.get("netBigBuyPercent"))>=0? 1:-1;

		
	
	}

}
