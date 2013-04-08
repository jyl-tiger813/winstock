package jyl.datacollect.sina.dao;

import java.util.Calendar;
import java.util.List;

import jyl.datacollect.sina.bo.StockFirstDayInfo;



public interface RecordDao {

	/**
	 * @param args
	 */
	public void saveRDs(List<StockFirstDayInfo>records);
}
