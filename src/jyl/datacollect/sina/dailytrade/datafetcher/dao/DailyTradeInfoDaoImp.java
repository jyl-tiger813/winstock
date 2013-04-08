package jyl.datacollect.sina.dailytrade.datafetcher.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.DataSource;
import jyl.datacollect.sina.dailytrade.datafetcher.bean.DailyTradeInfoBeanImp;
public  class DailyTradeInfoDaoImp extends DailyTradeInfoDaoAbstract 

	{
	
	/**
	 * 大数据数组拆分成多个数组入库
	 * @param countResult
	 * @param ds
	 */
	public void saveBeansDivided(ArrayList<DailyTradeInfoBeanImp> countResult,
			DataSource ds) {
		// TODO Auto-generated method stub
		Connection con = null;
		
		int i = 0;
		int lastPos = 0;
		for(;i<countResult.size();i++)
		{
			if((i!=0)&&(i%100==0))
			{
				try {
					con = ds.getConnection();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				saveBeans(countResult.subList(i-100, i),con);
				lastPos = i;
			}
		}
		if(countResult.size()>0&&(lastPos!= (countResult.size() -1)))
		{
			try {
				con = ds.getConnection();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			saveBeans(countResult.subList(lastPos, countResult.size() ), con);
		}
	}
	}
