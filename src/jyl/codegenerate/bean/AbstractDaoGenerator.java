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
public class AbstractDaoGenerator extends AbstractClassGenerator {

	@Override
	public String generateHeaderStr() {
		// TODO Auto-generated method stub
		//return super.generateHeaderStr();
		
		String className = covertBeginChar2Upper(paramBean.getBaseClassName());
		String packageStr = "package "+paramBean.getPacakageName()+";";
			String classHeader = "\npublic "+getClassType()+" class "+className+paramBean.getNameSufFix()+" extends "+CodeGenerateConstant.baseDaoName+" \n";
			return packageStr+classHeader;
		
	}

	@Override
	public String generateBodyStr() {
		// TODO Auto-generated method stub
		
		String result = "";
		String saveMethodString = getSaveMethodString();
		String batchSaveString = getBatchSaveMethodString();
		String queryBeansString = getQueryBeansString();
		result = result+"\n" +saveMethodString+"\n"+batchSaveString+"\n"+queryBeansString;
		//String methodBlockString = generateGetSetMethodStrings();
		return "\n{\n"+result+"\n}\n";
		
	}
	
	
	
	
	
	/**
	 * @return
	 */
	private String getQueryBeansString() {
		// TODO Auto-generated method stub
		String beanName = paramBean.getBaseClassName()+CodeGenerateConstant.impBeanSuffix;
		String methodName = "\npublic ArrayList<"+beanName+">  getBeans(String  querySql,Connection con)\n{";
	    String beforeLoopStr = "ArrayList<"+beanName+"> result = new ArrayList<"+beanName+">();\n\t" +
	    		"\n\t	Statement st = null;" +
	    		"\n\t	try {  " +
	    		"\n\t st = con.createStatement();" +
	    		"\n\t	ResultSet rs = st.executeQuery(querySql);" +
	    		"\n\t	while(rs.next())" +
	    		"\n\t		{\n\t" ;
		String loopBefore = beanName+" bean = new "+beanName+"();\n";
		String setProperties  ="";
		for(DbField fd :dbField)
		{
			String oneRowStr = "";
			String proName = fd.getFieldName();
			proName = covertBeginChar2Upper(proName);
		
			String fieldName = fd.getFieldName();
			String typeName = fd.getFieldType();
		//	oneRowStr = "\t\tbean.set"+proName+"("+"rs.get"+covertBeginChar2Upper(typeName)+"(\""+fieldName+"\"));";
			oneRowStr = "\t\tbean.set"+proName+"("+"rs.get"+covertBeginChar2Upper(typeName)+"(\""+	fd.dbColumName+"\"));";
			setProperties = setProperties+oneRowStr+"\n";
		}
		setProperties = setProperties +"\n\t\t"+"result.add(bean);\n";
		String loopAfter = "}\n	} catch (SQLException e) {\n\t e.printStackTrace(); 	}\n\t 	finally{\n\t " +
				"\n\t 		if(st!=null)" +
				"\n\t 		try {" +
				"\n\t 			st.close();" +
				"\n\t 		} catch (SQLException e) {" +
				"\n\t 		e.printStackTrace();" +
				"\n\t 	}" +
				"\n\t 	}" +
				"\n\t	if(result.size()>0)" +
				"\n\t		return result;" +
				"\n\t	else return null;" +
				"\n\t	}";
		return methodName+beforeLoopStr+loopBefore+setProperties+loopAfter;
	}

	/**
	 * @return
	 */
	private String getSaveMethodString() {
		/*
		 * 
		 * excutePreparedParams(String sqlStr, ArrayList<Object> values,Connection con)
		 * 
		 */
		// TODO Auto-generated method stub
		String beanName = paramBean.getBaseClassName()+CodeGenerateConstant.impBeanSuffix;
		String methodName = "\npublic void save("+beanName+" bean,Connection con)\n";
		String sqlPart1 = "";
		String sqlPart2 = "";
		int i =1;
		for(DbField fd :dbField)
		{
			String columnName = fd.getDbColumName();
			sqlPart1 = sqlPart1 + columnName;
			sqlPart2 = sqlPart2 + "?";
			if(i!=dbField.size())
			{
				sqlPart1 = sqlPart1 +",";
				sqlPart2 = sqlPart2 +",";
			}
			i++;
		}
		String sqlStr = "String sqlStr = \"insert into "+paramBean.getSchemaName()+"."+paramBean.getTableName()+"("+sqlPart1+")values("+sqlPart2+")\";\n";
	    
		String supplyValues = "ArrayList<Object> values = new ArrayList<Object> ();\n" ;
		for(DbField fd :dbField)
		{
			String proName = fd.getFieldName();
			proName = covertBeginChar2Upper(proName);
			String oneValue = "Object obj"+proName+" = bean.get"+proName+"();\n ";
			supplyValues = supplyValues+oneValue;
			supplyValues = supplyValues+" values.add(obj"+proName+");\n";
		}
		/*
		 * try {
	excutePreparedParams( sqlStr,  values, con);
} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}finally{
	if(con!=null)
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}
		 */
		String callMethodString = "\ntry {\n\texcutePreparedParams( sqlStr,  values, con);\n} catch (SQLException e) {\n" +
				"e.printStackTrace();\n" +
				"}finally{\n" +
				"if(con!=null)\n" +
				"try {\n" +
				"	con.close();\n" +
				"} catch (SQLException e) {\n" +
				"e.printStackTrace();\n" +
				"}\n}";
		String methodBody = "{" +sqlStr+supplyValues+callMethodString+
	    		"}\n";
	    return methodName+methodBody;
	}


	/**
	 * 执行多条拼接的sql语句
	 * @return
	 */
	private String getBatchSaveMethodString() {
		/*
		 * 
		 * excutePreparedParams(String sqlStr, ArrayList<Object> values,Connection con)
		 * 
		 */
		// TODO Auto-generated method stub
		String beanName = paramBean.getBaseClassName()+CodeGenerateConstant.impBeanSuffix;
		String methodName = "\npublic void saveBeans(ArrayList<"+beanName+"> beans,Connection con)\n{";
	    String beforeLoopStr = "String sqlStrs = \"\";\n" ;
		String loopBefore = "for("+beanName+" bean:beans)\n{\n";
		methodName = methodName +beforeLoopStr+ loopBefore;
		String sqlPart1 = "";
		String sqlPart2 = "";
		int i =1;
		String columnValue = "";
		for(DbField fd :dbField)
		{
			String columnName = fd.getDbColumName();
			String proName = fd.getFieldName();
			proName = covertBeginChar2Upper(proName);
			String oneValue = "Object obj"+proName+" = bean.get"+proName+"();\n" +
					"String "+proName+"valueStr = convertObj2string(obj"+proName+"); ";
			columnValue =columnValue+oneValue+"\n";
			sqlPart1 = sqlPart1 + columnName;
			sqlPart2 = sqlPart2 +"\"+"+proName+ "valueStr+\"";
			if(i!=dbField.size())
			{
				sqlPart1 = sqlPart1 +",";
				sqlPart2 = sqlPart2 +",";
			}
			i++;
		}
		String sqlStr = "if(\"\".equals(sqlStrs))\n" +
				"sqlStrs = \"insert into "+paramBean.getSchemaName()+"."+paramBean.getTableName()+"("+sqlPart1+")values("+sqlPart2+")\";\n" +
				"else\n" +
				"\nsqlStrs = sqlStrs+"+"\",("+sqlPart2+")\";";
		/*
		 * try {
	excutePreparedParams( sqlStr,  values, con);
} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}finally{
	if(con!=null)
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}
		 */
		String callMethodString = "}\ntry {\nexcuteSqlStrs( sqlStrs, con);\n} catch (SQLException e) {\n" +
				"e.printStackTrace();\n" +
				"}finally{\n" +
				"if(con!=null)\n" +
				"try {\n" +
				"	con.close();\n" +
				"} catch (SQLException e) {\n" +
				"e.printStackTrace();\n" +
				"}\n}";
		String methodBody = columnValue +sqlStr+callMethodString+
	    		"}\n";
	    return  "\n"+methodName+methodBody+"\n";
	}

	
	
	public static void main (String args[]){
		GenerateParamBean param = new GenerateParamBean();
		param.setPacakageName("jyl.datacollect.sse.crawl.genesr.dao");
		param.setBaseDircStr("D:/workspace/winStock/src/");
		param.setNameSufFix(CodeGenerateConstant.abatractDaoSuffix);
		param.setBaseClassName("RawPeriodReport");
		//param.setDatasourceName("sse");
		param.setSqlStr("SELECT * FROM  sse.dealedrecord");
		AbstractDaoGenerator ab = new AbstractDaoGenerator();
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
