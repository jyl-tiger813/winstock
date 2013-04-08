package jyl.datacollect.sse.dao;

import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;

import com.nhncorp.lucy.db.dao.AbstractSqlMapDAO;


import jyl.datacollect.sse.entity.Cease;
import jyl.datacollect.sse.entity.Record;

public class RecordDaoIbatisImp  extends AbstractSqlMapDAO implements RecordDao{

		Logger log = Logger.getLogger(RecordDaoIbatisImp.class);
		private static final String NAMESPACE = "com.nhncorp.sse6.";
	@Override
	public List<Record> queryList(Calendar cal1, Calendar cal2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveCE(Cease cease) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveRD(Record record) {
		// TODO Auto-generated method stub
		insert(NAMESPACE + "insertRecord", record);
		
	}

	@Override
	public String getDbKey() {
		// TODO Auto-generated method stub
		return "qaresult";
	}

}
