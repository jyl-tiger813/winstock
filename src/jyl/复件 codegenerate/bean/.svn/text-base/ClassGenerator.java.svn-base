package jyl.codegenerate.bean;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.sql.DataSource;

import jyl.util.DatabaseHelper;

/**
 * 类描述： 類生成器（基礎抽象類）
 * 创建人：jinyongliang
 * 创建时间：2012-10-9 下午05:52:38   
 * 修改人：jinyongliang   
 * 修改时间：2012-10-9 下午05:52:38   
 * 修改备注：   
 * @version 
 */
public abstract class ClassGenerator {
	GenerateParamBean paramBean ;
	ArrayList<DbField>  dbField = new ArrayList<DbField>(); //数据库参数
	
	//主方法
	public void generateJavaFile(){
		setWholeContent();
		flush2File();
	};
	
	/**
	 * 
	 */
	private void flush2File() {
		String className = covertBeginChar2Upper(paramBean.getBaseClassName());
		String packageRelativePath = paramBean.getPacakageName().replace('.', '/');
		String fileDirecPathStr = paramBean.getBaseDircStr()+packageRelativePath+"/"+className+paramBean.getNameSufFix()+".java";
		System.out.println("fileDirecPathStr:"+fileDirecPathStr);
		File f = new File(fileDirecPathStr);
		try {
			if(!f.exists())
			{
				if(!f.getParentFile().exists())
				{
					f.getParentFile().mkdirs();
				}
			f.createNewFile();
			}
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		
		try {
			BufferedWriter buf = new BufferedWriter(new FileWriter(f));
			buf.write(wholeContent);
			buf.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @param baseClassName
	 * @return
	 */
	public String covertBeginChar2Upper(String baseClassName) {
		// TODO Auto-generated method stub
		char [] chars = baseClassName.toCharArray();
		 if(chars[0]>= 'a' && chars[0]<= 'z')
		   {
			   chars[0] = (char)(((int)chars[0])-32);
		   }
		return new String(chars);
	}

	
	public GenerateParamBean getParamBean() {
		return paramBean;
	}

	public void setParamBean(GenerateParamBean paramBean) {
		this.paramBean = paramBean;
	}

	boolean ifFirstGenerate = true;//是否為初次生成代碼，如果非初次，實現類不會重新生成
	public boolean isIfFirstGenerate() {
		return ifFirstGenerate;
	}

	public void setIfFirstGenerate(boolean ifFirstGenerate) {
		this.ifFirstGenerate = ifFirstGenerate;
	}

	static HashMap<Integer,String> typeMapping = new HashMap<Integer,String>();
	static {
		typeMapping.put(java.sql.Types.BIGINT, "long");
		typeMapping.put(java.sql.Types.FLOAT, "float");
		typeMapping.put(java.sql.Types.INTEGER, "int");
		typeMapping.put(java.sql.Types.VARCHAR, "String");
		typeMapping.put(java.sql.Types.BOOLEAN, "int");
		typeMapping.put(java.sql.Types.DECIMAL, "int");
		typeMapping.put(java.sql.Types.DOUBLE, "double");
		typeMapping.put(java.sql.Types.FLOAT, "float");
		typeMapping.put(java.sql.Types.REAL, "float");
		typeMapping.put(java.sql.Types.DATE, "Timestamp");
		typeMapping.put(java.sql.Types.TIMESTAMP, "Timestamp");
		typeMapping.put(java.sql.Types.TIME, "Timestamp");
		typeMapping.put(java.sql.Types.TIMESTAMP, "Timestamp");
		typeMapping.put(java.sql.Types.TINYINT, "int");	
	}
	
	String header;
	
	String boby;
	
	String wholeContent;

	public String generateHeaderStr() {
		// TODO Auto-generated method stub
		//return super.generateHeaderStr();
		
		String className = covertBeginChar2Upper(paramBean.getBaseClassName());
		String packageStr = "package "+paramBean.getPacakageName()+";";
		String classType = getClassType();
		System.out.println("classType:"+classType);
		if(!"abstract".equals(classType))
		{
			String classHeader = "\npublic "+getClassType()+" class "+className+paramBean.getNameSufFix()+" extends "+className+getSuperSuffix()+" \n";
			return packageStr+classHeader;
		}
		else
		{
			String classHeader = "\npublic "+getClassType()+" class "+className+paramBean.getNameSufFix()+" \n";
			return packageStr+classHeader;
		}
		
	}
	
	/**
	 * @return
	 */
	public abstract String getSuperSuffix();

	/**
	 * 返回類的類型，抽取類或實體類，默認為實體類
	 * @return
	 */
	public abstract String getClassType();

	public String generateBodyStr()
	{
		return "\n\t{\n\t}";
	};
	
	public void setWholeContent()
	{
		fetchDbFields();
		String headStr = generateHeaderStr();
		String bodyStr = generateBodyStr();
		wholeContent = headStr+"\n\n"+bodyStr+"\n";
		
	};
	
	/**
	 * 
	 */
	private void fetchDbFields() {
		// TODO 连接数据库表，获取相关的属性
		 HashMap<String,String> tableParams = getDataSourceName(paramBean.getSqlStr());
		 String dataSourceName = tableParams.get("schemaName");
		 DataSource  ds = DatabaseHelper.getDSByNameLocalConfig(dataSourceName);
		 try {
			Connection con = ds.getConnection();
			ResultSet rs = con.createStatement().executeQuery(paramBean.getSqlStr());
			ResultSetMetaData rsMD = rs.getMetaData();
			for(int i = 0;i<rsMD.getColumnCount();i++)
			{
				String columnName = rsMD.getColumnName(i+1);
				String sqlType = rsMD.getColumnTypeName(i+1);
				int columnType = rsMD.getColumnType(i+1);
			
				DbField df = new DbField();
				df.setDbColumName(columnName);
				df.setFieldName(covertDB2JField(columnName));
				String javaType = typeMapping.get(columnType);
				if(javaType!=null)
				{
				df.setFieldType(typeMapping.get(columnType));
				}
				else
					System.out.println(columnName+":"+sqlType+" don't have corresponding type");
				//dbField
				dbField.add(df);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
}

	/**
	 * @param sqlStr
	 * @return
	 */
	private HashMap<String,String> getDataSourceName(String sqlStr) {
		// TODO Auto-generated method stub
		String tempSqlStr = sqlStr.toLowerCase();
		int pos = sqlStr.toLowerCase().indexOf("from");
		
		String temp1 = sqlStr.substring(pos+4,tempSqlStr.length()).trim();
		String [] strArr = temp1.split("/s");
		String wholeTableName = strArr[0].trim();
		String [] temp = wholeTableName.split("\\.");
		HashMap<String,String> result = new HashMap<String,String> ();
		result.put("schemaName", temp[0]);
		result.put("tableName", temp[1]);
		paramBean.setSchemaName(temp[0]);
		paramBean.setTableName(temp[1]);
		return result;
	}
	
	

	/**
	 * 从数据库格式转化到java格式
	 * @param columnName
	 * @return
	 */
	private String covertDB2JField(String columnName) {
		// TODO Auto-generated method stub
	   String [] str = columnName.split("_");
	   StringBuilder sbd = new StringBuilder();
	   for(int i =0;i<str.length;i++)
	   {
		   String temp = str[i];
		   char[] chars = temp.toCharArray();
		   if(chars[0]>= 'a' && chars[0]<= 'z')
		   {
			   chars[0] = (char)(((int)chars[0])-32);
		   }
		   str[i] = new String(chars);
		   sbd.append(str[i]);
	   }
	   
	   return sbd.toString();
	}

}