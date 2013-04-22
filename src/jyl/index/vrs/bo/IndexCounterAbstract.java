package jyl.index.vrs.bo;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.sql.DataSource;

import jyl.codegenerate.srbaseclass.AbstractBaseDAO;
import jyl.datacollect.sina.dailytrade.datafetcher.MainDispatcher;
import jyl.datacollect.sina.dailytrade.datafetcher.bean.StockNameBeanImp;
import jyl.datacollect.sina.dailytrade.datafetcher.dao.DailyTradeInfoDaoImp;
import jyl.datacollect.sina.dailytrade.datafetcher.dao.StockNameDaoImp;
import jyl.util.DatabaseHelper;

/**
 * 类描述：   
 * 创建人：jinyongliang
 * 创建时间：Apr 19, 2013 5:51:30 PM   
 * 修改人：jinyongliang   
 * 修改时间：Apr 19, 2013 5:51:30 PM   
 * 修改备注：   
 * @version 
 */
public abstract class IndexCounterAbstract {

	protected     DataSource ds = null;// DatabaseHelper.getDSByNameLocalConfig("sse");
	protected     Connection con = null;
	protected AbstractBaseDAO vrsDao = null;
	protected AbstractBaseDAO dailyTradeDao =null;
	//protected String baseQuerySql = "SELECT * FROM (SELECT * FROM stock_trade_daily_detail where stock_code = '";
	protected String baseQuerySql = "SELECT * FROM stock_trade_daily_detail where stock_code = '";
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MainDispatcher disp = new MainDispatcher();
		disp.fetchDatas();
	//计算全量
	//	vr.createTables();
	//	vr.countFullPeriodData();
	} 
	
	public abstract void initParams();

	/**
	 * 
	 */
	public void countDataIncrement() {
		// TODO Auto-generated method stub
		StockNameDaoImp snDao = new StockNameDaoImp();
		String querySql = "select * from sse.stocknames ";
		Connection con = null;
		//	con = ds.getConnection();
			 String user = "root";
			String password = "manager";
			 String url = "jdbc:mysql://localhost:3306/sse?useUnicode=true&amp;characterEncoding=UTF-8";
					
			 try {
				if(con==null||con.isClosed())			
						con = DatabaseHelper.getAdConnectionByParams(url,user,password);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		ArrayList<StockNameBeanImp>  stockBeans = snDao.getBeans(querySql, con);
		int i =0 ;
		for(StockNameBeanImp bean : stockBeans)
		{
		i++;
		System.out.println("i:"+i);
		countOneStock(bean.getCodeId());
		modifyStatus(bean);
		}
	}

	/**
	 * 
	 */
	public void createTables() {
		int [] indexid = {1,2,3,4};
		int [] countDays = {5,10,21};
		String dropSql = "drop TABLE `index_vr_related_data?1`";
		String baseSqlStr = "CREATE TABLE `index_vr_related_data?1` ( "+
  " `index_id` INT(11) DEFAULT NULL, "+
  " `stock_code` VARCHAR(20) DEFAULT NULL, "+
  " `change_ratio` double DEFAULT NULL COMMENT '今天昨天收盘变化率', "+
  " `ave_change_ratio` double DEFAULT NULL COMMENT '今天昨天均价变化率' , "+
  " `change_ratio_close_begin` double DEFAULT NULL COMMENT '今天收盘 开盘', "+
  " `change_ratio_avg_close` double DEFAULT NULL COMMENT '今天均价收盘', "+
		" `stock_code_int` INT(10) DEFAULT NULL, "+
		" `count_days` INT(11) DEFAULT NULL, "+
		" `trade_date` DATETIME DEFAULT NULL, "+
		" `index_value` DOUBLE DEFAULT NULL, "+
		" `ts` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, "+
		" UNIQUE KEY `index_id_count_days_stock_code_trade_date` (`index_id`,`stock_code`,`count_days`,`trade_date`),"+
		" KEY `NewIndex1` (`index_id`),"+
		" KEY `NewIndex2` (`stock_code`),"+
		" KEY `NewIndex3` (`trade_date`),"+
		" KEY `NewIndex4` (`index_id`,`count_days`)"+
		" ) ENGINE=MYISAM DEFAULT CHARSET=utf8 DELAY_KEY_WRITE=1";
		for(int i=0;i<indexid.length;i++)
		{
			for(int j=0;j<countDays.length;j++)
			{
				String subfix = indexid[i]+"_"+countDays[j];
				String exeSql = baseSqlStr.replace("?1", subfix);
				String dropSqlStr = dropSql.replace("?1", subfix);
				con = setCon ();
				
				Statement st = null;
			 try {
				st = con.createStatement();
				st.execute(dropSqlStr);
				st.execute(exeSql);
				System.out.println("exeSql:"+exeSql);
			} catch (Exception e) {
				// TODO: handle exception
			}
			}

		}
	}

	/**
	 * 
	 */
	public void countFullPeriodData() {
		// TODO Auto-generated method stub
		StockNameDaoImp snDao = new StockNameDaoImp();
		String querySql = "select * from sse.stocknames where isdealed <>1";
		//String querySql = "select * from sse.stocknames where  code_id = '600559' ";
		Connection con = null;
		//	con = ds.getConnection();
			 String user = "root";
			String password = "manager";
			 String url = "jdbc:mysql://localhost:3306/sse?useUnicode=true&amp;characterEncoding=UTF-8";
					
			 try {
				if(con==null||con.isClosed())			
						con = DatabaseHelper.getAdConnectionByParams(url,user,password);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		ArrayList<StockNameBeanImp>  stockBeans = snDao.getBeans(querySql, con);
		int i =0 ;
		for(StockNameBeanImp bean : stockBeans)
		{
		i++;
		System.out.println("i:"+i);
		countOneStock(bean.getCodeId());
		modifyStatus(bean);
		}
	}

	/**
	 * @param bean
	 */
	public void modifyStatus(StockNameBeanImp bean) {
		// TODO Auto-generated method stub
		String sql = " UPDATE stocknames SET isdealed =1 WHERE code_id = '?1'";
		String exeSql = sql.replace("?1", bean.getCodeId());
			con = setCon ();
			
			Statement st = null;
		 try {
			st = con.createStatement();
			st.execute(exeSql);
			System.out.println("exeSql:"+exeSql);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * @param string
	 */
	public abstract void countOneStock(String string); /*{
		// TODO Auto-generated method stub
		VrsModel vm = new VrsModel();
		String querySql = baseQuerySql+string+"' and amount >0 ORDER BY trade_date DESC  LIMIT 25)t ORDER BY trade_date ";
		//String querySql = baseQuerySql+string+"' and amount >0 ORDER BY trade_date  "; 
		String user = "root";
			String password = "manager";
			 String url = "jdbc:mysql://localhost:3306/sse?useUnicode=true&amp;characterEncoding=UTF-8";
		try {
			if(con==null||con.isClosed())			
				con = DatabaseHelper.getAdConnectionByParams(url,user,password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		long t1 = System.currentTimeMillis();
		ArrayList<DailyTradeInfoBeanImp> arrs = dailyTradeDao.getBeans( querySql,con);
		long t2 = System.currentTimeMillis();
		System.out.println("查询消耗时间:"+(t2-t1));
		if(arrs!=null)
		System.out.print("arrsLength:"+arrs.size());
		else return;
		HashMap<String,ArrayList<VrsIndexDataBeanImp>> resultMap = vm.countDatasVrs(arrs);
		long t3 = System.currentTimeMillis();
		System.out.println("计算消耗时间:"+(t3-t2));
		for(String keyStr:resultMap.keySet())
		{
		vrsDao.saveBeansDivided(keyStr,resultMap.get(keyStr),con);
		}
		long t4 = System.currentTimeMillis();
		System.out.println("保存数据消耗时间:"+(t4-t3));
	}*/
	
	public Connection setCon (){
		 String user = "root";
			String password = "manager";
			 String url = "jdbc:mysql://localhost:3306/sse?useUnicode=true&amp;characterEncoding=UTF-8";
				try {
					if(con==null||con.isClosed())			
						con = DatabaseHelper.getAdConnectionByParams(url,user,password);
					return con;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return null;
				}
	}

}

