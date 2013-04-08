package jyl.codegenerate.bean;

import jyl.codegenerate.constant.CodeGenerateConstant;



/**
 * 类描述：   
 * 创建人：jinyongliang
 * 创建时间：2012-10-10 下午01:01:56   
 * 修改人：jinyongliang   
 * 修改时间：2012-10-10 下午01:01:56   
 * 修改备注：   
 * @version 
 */
public class ImplementBeanGenerator extends ImplementClassGenerator {

	/**
	 * @param args
	 */
	public static void main (String args[]){
		GenerateParamBean param = new GenerateParamBean();
		param.setPacakageName("jyl.datacollect.sse.crawl.genesr.bean");
		param.setBaseDircStr("D:/workspace/winStock/src/");
	//	String nameSuffix 
		param.setNameSufFix(CodeGenerateConstant.impBeanSuffix);
		param.setBaseClassName("RawPeriodReport");
		//param.setDatasourceName("sse");
		param.setSqlStr("SELECT * FROM  sse.dealedrecord");
		ImplementBeanGenerator ab = new ImplementBeanGenerator();
		ab.setParamBean(param);
		ab.generateJavaFile();
		
	}

	/* (non-Javadoc)
	 * @see jyl.codegenerate.bean.ClassGenerator#getSuperSuffix()
	 */
	@Override
	public String getSuperSuffix() {
		// TODO Auto-generated method stub
		return CodeGenerateConstant.abatractBeanSuffix;
	}

}
