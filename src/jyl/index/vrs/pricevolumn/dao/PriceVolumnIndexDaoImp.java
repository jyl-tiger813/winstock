package jyl.index.vrs.pricevolumn.dao;

import java.sql.Connection;
import java.util.ArrayList;

import jyl.index.vrs.pricevolumn.bean.PriceVolumnIndexBeanImp;

public  class PriceVolumnIndexDaoImp extends PriceVolumnIndexDaoAbstract 



	{
	/**
	 * 大数据数组拆分成多个数组入库
	 * @param keyStr 
	 * @param countResult
	 * @param ds
	 */
	public void saveBeansDivided( ArrayList<PriceVolumnIndexBeanImp> countResult,
			Connection con) {
		// TODO Auto-generated method stub
		int i = 0;
		int lastPos = 0;
		int batchNum = 200;
		for(;i<countResult.size();i++)
		{
			if((i!=0)&&(i%batchNum==0))
			{
				saveBeans(countResult.subList(i-batchNum>0?i-batchNum:0, i+1),con);
				lastPos = i;
			}
		}
		if(lastPos< (countResult.size() -1))
		{
			saveBeans(countResult.subList(lastPos, countResult.size()), con);
		}
	}
	}
