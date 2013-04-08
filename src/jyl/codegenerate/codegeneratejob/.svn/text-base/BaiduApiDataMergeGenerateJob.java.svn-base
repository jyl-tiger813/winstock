package jyl.codegenerate.codegeneratejob;

import jyl.codegenerate.CodeGenerateDispatcher;
import jyl.codegenerate.bean.GenerateParamBean;


/**
 * 类描述：   
 * 创建人：jinyongliang
 * 创建时间：2012-10-15 下午04:45:58   
 * 修改人：jinyongliang   
 * 修改时间：2012-10-15 下午04:45:58   
 * 修改备注：   
 * @version 
 */
public class BaiduApiDataMergeGenerateJob {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BaiduApiDataMergeGenerateJob job = new BaiduApiDataMergeGenerateJob();
		//job.doJob1();
		//job.doJob2();
		//job.doJob3();
		//job.doJob4();
		//job.doJob5();
		//job.generateTimeDimension();
		//job.generateSseStrockName();
		//job.generateDailyTradeInfo();
		job.generateIndex_Data();
	}

	/**
	 * 
	 */
	private void generateIndex_Data() {
		// TODO Auto-generated method stub
		GenerateParamBean param = new GenerateParamBean();
		param.setPacakageName("jyl.index.vrs");
		param.setBaseDircStr("D:/workspace/winStock/src/");
		param.setBaseClassName("VrsIndexData");
		param.setDatasourceName("sse");
		param.setSqlStr("SELECT * FROM  sse.index_vr_related_data");
		//param.setSqlStr(sqlStr);
		CodeGenerateDispatcher dispatcher = new CodeGenerateDispatcher();
		dispatcher.setParamBean(param);
		dispatcher.doGenerate();
	}

	/**
	 * 
	 */
	private void generateDailyTradeInfo() {
		// TODO Auto-generated method stub
		GenerateParamBean param = new GenerateParamBean();
		param.setPacakageName("jyl.datacollect.sina.dailytrade.datafetcher");
		param.setBaseDircStr("D:/workspace/winStock/src/");
		param.setBaseClassName("DailyTradeInfo");
		
		param.setDatasourceName("sse");
		param.setSqlStr("SELECT * FROM  sse.stock_trade_daily_detail");
		//param.setSqlStr(sqlStr);
		CodeGenerateDispatcher dispatcher = new CodeGenerateDispatcher();
		dispatcher.setParamBean(param);
		dispatcher.doGenerate();
	}

	/**
	 * 
	 */
	private void generateSseStrockName() {
		// TODO Auto-generated method stub
		GenerateParamBean param = new GenerateParamBean();
		param.setPacakageName("jyl.datacollect.sina.dailytrade.datafetcher");
		param.setBaseDircStr("D:/workspace/winStock/src/");
		param.setBaseClassName("StockName");
		
		param.setDatasourceName("sse");
		param.setSqlStr("SELECT * FROM  sse.stocknames");
		//param.setSqlStr(sqlStr);
		CodeGenerateDispatcher dispatcher = new CodeGenerateDispatcher();
		dispatcher.setParamBean(param);
		dispatcher.doGenerate();
	}

	/**
	 * 
	 */
	private void generateTimeDimension() {
		GenerateParamBean param = new GenerateParamBean();
		param.setPacakageName("com.baidu.rencai.util.mondrain");
		param.setBaseDircStr("D:/wk1/rc.bi.temp/src/main/java/");
		param.setBaseClassName("TimeDimGenerate");
		
		//param.setDatasourceName("sse");
		param.setSqlStr("SELECT * FROM  incrementbak.time_by_day");
		//param.setSqlStr(sqlStr);
		CodeGenerateDispatcher dispatcher = new CodeGenerateDispatcher();
		dispatcher.setParamBean(param);
		dispatcher.doGenerate();
		
	}

	/**
	 * 
	 */
	private void doJob6() {
		// TODO Auto-generated method stub
		
		GenerateParamBean param = new GenerateParamBean();
		param.setPacakageName("com.baidu.rencai.statistic.audit");
		param.setBaseDircStr("D:/wk1/rc.bi.temp/src/main/java/");
		param.setBaseClassName("AuditDailyValue");
		
		//param.setDatasourceName("sse");
		param.setSqlStr("SELECT * FROM  incrementbak.audit_abstract_value");
		//param.setSqlStr(sqlStr);
		CodeGenerateDispatcher dispatcher = new CodeGenerateDispatcher();
		dispatcher.setParamBean(param);
		dispatcher.doGenerate();
	}

	/**
	 * 
	 */
	private void doJob5() {
		// TODO Auto-generated method stub
		GenerateParamBean param = new GenerateParamBean();
		param.setPacakageName("com.baidu.rencai.statistic.audit");
		param.setBaseDircStr("D:/wk1/rc.bi.temp/src/main/java/");
		param.setBaseClassName("AuditReportValue");
		
		//param.setDatasourceName("sse");
		param.setSqlStr("SELECT * FROM  incrementbak.temp_audit_value");
		//param.setSqlStr(sqlStr);
		CodeGenerateDispatcher dispatcher = new CodeGenerateDispatcher();
		dispatcher.setParamBean(param);
		dispatcher.doGenerate();
	}

	/**
	 * 
	 */
	private void doJob4() {
		// TODO Auto-generated method stub
		GenerateParamBean param = new GenerateParamBean();
		param.setPacakageName("com.baidu.rencai.statistic.audit");
		param.setBaseDircStr("D:/wk1/rc.bi.temp/src/main/java/");
		param.setBaseClassName("IndexBaseInfo");
		
		//param.setDatasourceName("sse");
		param.setSqlStr("SELECT * FROM  incrementbak.abstract_audit_sum_statistic_index");
		//param.setSqlStr(sqlStr);
		CodeGenerateDispatcher dispatcher = new CodeGenerateDispatcher();
		dispatcher.setParamBean(param);
		dispatcher.doGenerate();
	}

	/**
	 * 
	 */
	private void doJob3() {
		// TODO Auto-generated method stub
		GenerateParamBean param = new GenerateParamBean();
		param.setPacakageName("com.linzhi.tree");
		param.setBaseDircStr("D:/wk1/rc.bi.temp/src/main/java/");
		param.setBaseClassName("TreeBaseInfo");
		
		//param.setDatasourceName("sse");
		param.setSqlStr("SELECT * FROM  linzhi.ul_tree_temp");
		//param.setSqlStr(sqlStr);
		CodeGenerateDispatcher dispatcher = new CodeGenerateDispatcher();
		dispatcher.setParamBean(param);
		dispatcher.doGenerate();
	}

	/**
	 * 
	 */
	private void doJob2() {
		GenerateParamBean param = new GenerateParamBean();
		param.setPacakageName("com.baidu.rencai.statistic.jsmonitor");
		param.setBaseDircStr("D:/wk1/rc.bi.temp/src/main/java/");
		param.setBaseClassName("JsAccessLogger");
		
		//param.setDatasourceName("sse");
		param.setSqlStr("SELECT * FROM  apache_js_log.jsaccesslog");
		//param.setSqlStr(sqlStr);
		CodeGenerateDispatcher dispatcher = new CodeGenerateDispatcher();
		dispatcher.setParamBean(param);
		dispatcher.doGenerate();
		
	}

	/**
	 * 
	 */
	private void doJob1() {
		// TODO Auto-generated method stub
		GenerateParamBean param = new GenerateParamBean();
		param.setPacakageName("com.baidu.rencai.statistic.baiduapi");
		param.setBaseDircStr("D:/wk1/rc.bi.temp/src/main/java/");
		param.setBaseClassName("BaiduApiRawData");
		
		//param.setDatasourceName("sse");
		param.setSqlStr("SELECT * FROM  incrementbak.baidu_api_raw_data");
		//param.setSqlStr(sqlStr);
		CodeGenerateDispatcher dispatcher = new CodeGenerateDispatcher();
		dispatcher.setParamBean(param);
		dispatcher.doGenerate();
	}

}
