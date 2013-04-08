package jyl.datacollect.sse.dao;

import java.util.Calendar;
import java.util.List;

import jyl.datacollect.sse.entity.Cease;
import jyl.datacollect.sse.entity.Record;

public interface RecordDao {

	/**
	 * @param args
	 */
	public void saveRD(Record record);
	public List<Record> queryList(Calendar cal1,Calendar cal2);
	public void saveCE(Cease cease);
}
