package jyl.index.vrs.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import jyl.index.vrs.bean.VrsIndexDataBeanImp;
import jyl.util.DatabaseHelper;

public  class VrsIndexDataDaoImp extends VrsIndexDataDaoAbstract 



	{

	/**
	 * 大数据数组拆分成多个数组入库
	 * @param keyStr 
	 * @param countResult
	 * @param ds
	 */
	public void saveBeansDivided(String keyStr, ArrayList<VrsIndexDataBeanImp> countResult,
			Connection con) {
		// TODO Auto-generated method stub
		 String user = "root";
			String password = "manager";
			 String url = "jdbc:mysql://localhost:3306/sse?useUnicode=true&amp;characterEncoding=UTF-8";
		int i = 0;
		int lastPos = 0;
		int batchNum = 200;
		for(;i<countResult.size();i++)
		{
			if((i!=0)&&(i%batchNum==0))
			{
			//	con = DatabaseHelper.getAdConnectionByParams(url,user,password);
				/*
			
				try {
					con = ds.getConnection();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
				saveBeans(keyStr,countResult.subList(i-batchNum>0?i-batchNum:0, i),con);
				lastPos = i;
			}
		}
		if(lastPos!= (countResult.size() -1))
		{
		//	con = DatabaseHelper.getAdConnectionByParams(url,user,password);
			/*
			try {
				con = ds.getConnection();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			saveBeans(keyStr,countResult.subList(lastPos, countResult.size() -1), con);
		}
	}
	}
