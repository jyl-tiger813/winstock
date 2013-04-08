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
		job.doJob1();
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
