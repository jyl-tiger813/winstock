package jyl.datacollect.sse.dao;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import sseGet.util.HbnUtil;
import sseGet.aim.Validate;
import sseGet.biz.entity.Cease;
import sseGet.biz.entity.Record;

public class RecordDaoHbnImp implements RecordDao{

	public List<Record> queryList(Calendar cal1, Calendar cal2) {
		Session session=HbnUtil.getSession() ;
		Transaction tran=session.beginTransaction();
		String hql2="from Record order by time";
	String hql="from Record as r where r.cotime between :cal1 and :cal2 order by r.time";
	Query query=session.createQuery(hql);
	query.setParameter("cal1", cal1);
	query.setParameter("cal2", cal2);
	List<Record> list=query.list();
		tran.commit();
		session.close();
		return list;
	}

	public void saveRD(Record record) {
		Session session=HbnUtil.getSession() ;
		Transaction tran=session.beginTransaction();
		session.save(record);
		tran.commit();
		session.close();
	}
	
	public void saveCE(Cease cease) {
		Session session=HbnUtil.getSession() ;
		Transaction tran=session.beginTransaction();
		session.save(cease);
		tran.commit();
		session.close();
	}
	
	public void cease(Cease c){
		
	}
	public static void main(String args[]){
	/*Calendar cal1=new GregorianCalendar(2008,6,25);
	Calendar cal3=new GregorianCalendar(2003,5,14);
	Calendar cal2=new GregorianCalendar(2000,5,10);
	RecordDaoHbnImp rhd=new RecordDaoHbnImp();
	List <Record> list=rhd.queryList(cal2, cal3);
	System.out.println(list.size());
	for(Record r: list){
		System.out.println(r.toString());
	}*/
		Session session=HbnUtil.getSession() ;
		Transaction tran=session.beginTransaction();
		String hql="from Record";
		Query qu=session.createQuery(hql);
		qu.setFirstResult(10);
		qu.setMaxResults(20);
		List<Record> l1=qu.list();
		tran.commit();
		session.close();
		/*
		Hibernate: select * from ( select row_.*, rownum rownum_ from ( 
		select record0_.oid as oid0_,  record0_.t_perGet as t8_0_ from t_Record record0_ )
		row_ where rownum <= ?) where rownum_ > ?
*/
	}
}
