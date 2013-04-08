package jyl.datacollect.sse.dao.query;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jyl.datacollect.sse.bo.MainService;
import jyl.datacollect.sse.util.MyCalendar;

import com.nhncorp.lucy.common.util.DataMap;
import com.nhncorp.lucy.db.dao.AbstractSqlMapDAO;

public class ChangeRatioDaoIbitisImp extends AbstractSqlMapDAO implements ChangeRatioDao {
	
	static final String NAMESPACE = "com.nhncorp.sse6.";

	@Override
	/*
	 * 得到dealedrecord1所有需要展现列的数据，到dao或者bo里再进行分类处理
	 */
	public List<DataMap> getAllDataByTime(Calendar cal1, Calendar cal2) throws SQLException {
		// TODO Auto-generated method stub
		HashMap<String,Calendar> map=new HashMap<String,Calendar>();
		map.put("starttime", cal1);
		map.put("stoptime", cal2);
		return queryForList(NAMESPACE + "getdatabytime",map);
	}

	@Override
	public String getDbKey() {
		// TODO Auto-generated method stub
		return "sse6";
	}

	
	/*
	 * 得到某一列的数据
	 */
	@Override
	public List<DataMap> getOneColumnData(Calendar cal1, Calendar cal2,String columnName)
			throws SQLException {
		Map map=new HashMap();
		map.put("starttime", MyCalendar.getString(cal1));
		map.put("stoptime", MyCalendar.getString(cal2));
		map.put("columnName", columnName);
		return queryForList(NAMESPACE + "getdatabytime",map);
	}
	
	public static void main(String args[]) throws SQLException{
		ChangeRatioDaoIbitisImp ci=new ChangeRatioDaoIbitisImp();
		String columnName="volumnchange";
		Calendar startCal=MainService.getStopCal();
		Calendar stopCal=MainService.getCal();
	//	ci.getOneColumnData(MyCalendar.getString(startCal), MyCalendar.getString(stopCal), columnName);
	}

}
