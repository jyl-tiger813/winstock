package jyl.codegenerate.bean;

import jyl.codegenerate.constant.CodeGenerateConstant;


/**
 * 类描述：   
 * 创建人：jinyongliang
 * 创建时间：2012-10-10 上午11:38:46   
 * 修改人：jinyongliang   
 * 修改时间：2012-10-10 上午11:38:46   
 * 修改备注：   
 * @version 
 */
public class AbstractBeanGenerator extends AbstractClassGenerator {

	private String generateGetSetMethodStrings ()
	{
		StringBuilder result = new StringBuilder();
		String propertyPrefix = "public  ";
		for(DbField field : dbField)
		{
			String fieldName = field.getFieldName();
			String fieldType = field.getFieldType();
			//set方法生成
			result.append(propertyPrefix+" void set"+covertBeginChar2Upper(fieldName)+"("+fieldType+" "+fieldName+")\n\t\t{");
			result.append("\n\t\t\t\tthis."+fieldName+" = "+fieldName+";\n\t\t}\n\n");
			//get方法生成
			result.append(propertyPrefix+fieldType+" get"+covertBeginChar2Upper(fieldName)+"()\n\t\t{");
			result.append("\n\t\t\t\treturn this."+fieldName+";\n\t\t}\n\n");
		}
		return result.toString();
	}

	private String generatePropetyStrings()
	{
		StringBuilder result = new StringBuilder();
		String propertyPrefix = "private ";
		for(DbField field : dbField)
		{
			String fieldName = field.getFieldName();
			String fieldType = field.getFieldType();
			result.append(propertyPrefix+" "+fieldType+" "+fieldName+";\n");
		}
		return result.toString();
	}

	@Override
	public String generateBodyStr() {
		// TODO Auto-generated method stub
		String propertyString = generatePropetyStrings();
		String methodBlockString = generateGetSetMethodStrings();
		return "\n{\n"+ propertyString+"\n"+methodBlockString+"\n}\n";
		
	}
	public static void main (String args[]){
		GenerateParamBean param = new GenerateParamBean();
		param.setPacakageName("jyl.datacollect.sse.crawl.genesr.bean");
		param.setBaseDircStr("D:/workspace/winStock/src/");
		param.setNameSufFix(CodeGenerateConstant.abatractBeanSuffix);
		param.setBaseClassName("RawPeriodReport");
		//param.setDatasourceName("sse");
		param.setSqlStr("SELECT * FROM  sse.dealedrecord");
		AbstractBeanGenerator ab = new AbstractBeanGenerator();
		ab.setParamBean(param);
		ab.generateJavaFile();
		
	}

	/* (non-Javadoc)
	 * @see jyl.codegenerate.bean.ClassGenerator#getSuperSuffix()
	 */
	@Override
	public String getSuperSuffix() {
		// TODO Auto-generated method stub
		return null;
	}
}
