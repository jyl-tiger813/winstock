package jyl.util.mondrian.bo;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;

import jyl.util.DatabaseHelper;
import jyl.util.DateTimeUtil;
import jyl.util.MonthMap;
import jyl.util.WeekMap;
import jyl.util.mondrian.bean.TimeDimGenerateBeanImp;
import jyl.util.mondrian.dao.TimeDimGenerateDaoImp;



/**
 * 类描述：   
 * 创建人：jinyongliang
 * 创建时间：Jan 14, 2013 10:20:09 AM   thdthfthftftgh
 * 修改人：jinyongliang   
 * 修改时间：Jan 14, 2013 10:20:09 AM   
 * 修改备注：   
 * @version 
 */
public class TimeDimGenerator {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TimeDimGenerator tgenerator  = new TimeDimGenerator();
		String beginStr = "2002-01-01";
		String endStr = "2014-01-01";
		Connection con = null;
		try {
			con = DatabaseHelper.getDSByNameLocalConfig("incrementbak").getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tgenerator.generateTimeDim(beginStr,endStr,con);
		
	}

	/**
	 * @param con 
	 * @param endStr 
	 * @param beginStr 
	 * 
	 */
	private void generateTimeDim(String beginStr, String endStr, Connection con) {
		// TODO Auto-generated method stub
		Calendar beginCal = DateTimeUtil.getCalendarByStr(beginStr);
		Calendar endCal = DateTimeUtil.getCalendarByStr(endStr);
		Calendar currentCal = beginCal;
		ArrayList<TimeDimGenerateBeanImp> beans = new ArrayList<TimeDimGenerateBeanImp>();
		while(currentCal.before(endCal))
		{
			TimeDimGenerateBeanImp tb = new TimeDimGenerateBeanImp();
			tb.setDayOfMonth(currentCal.get(Calendar.DAY_OF_MONTH)+"");
			String quarter = "Q"+(((currentCal.get(Calendar.MONTH))/3)+1);//季度逻辑特殊处理
			tb.setMonthOfYear(currentCal.get(Calendar.MONTH)+1);
			tb.setQuarter(quarter);
			tb.setTheDate(new Timestamp(currentCal.getTimeInMillis()));
			tb.setTheDay(WeekMap.getInstance().get(currentCal.get(Calendar.DAY_OF_WEEK)));
			tb.setTheMonth(MonthMap.getInstance().get((currentCal.get(Calendar.MONTH)+1)));
			tb.setTheYear(currentCal.get(Calendar.YEAR));
			tb.setWeekOfYear(currentCal.get(Calendar.WEEK_OF_YEAR));
			beans.add(tb);
			currentCal = DateTimeUtil.getNextDay(currentCal);
		}
		TimeDimGenerateDaoImp dimBeanImp = new TimeDimGenerateDaoImp();
		dimBeanImp.saveBeans(beans, con);
		
	}

}
