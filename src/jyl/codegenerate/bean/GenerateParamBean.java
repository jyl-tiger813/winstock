package jyl.codegenerate.bean;

/**
 * 类描述：   动态生称代码参数bean
 * 创建人：jinyongliang
 * 创建时间：2012-7-23 下午05:29:44   
 * 修改人：jinyongliang   
 * 修改时间：2012-7-23 下午05:29:44   
 * 修改备注：   
 * @version 
 */
public class GenerateParamBean {
	boolean isFisrtTimeGene = false;//默认为非第一次生成（避免错误操作覆盖）
	public boolean isFisrtTimeGene() {
		return isFisrtTimeGene;
	}
	public void setFisrtTimeGene(boolean isFisrtTimeGene) {
		this.isFisrtTimeGene = isFisrtTimeGene;
	}
	public String getPacakageName() {
		return pacakageName;
	}
	public String getSqlStr() {
		return sqlStr;
	}
	public String getDatasourceName() {
		return datasourceName;
	}
	public void setPacakageName(String pacakageName) {
		this.pacakageName = pacakageName;
	}
	public void setSqlStr(String sqlStr) {
		this.sqlStr = sqlStr;
	}
	public void setDatasourceName(String datasourceName) {
		this.datasourceName = datasourceName;
	}
	public String getBaseDircStr() {
		return baseDircStr;
	}
	public void setBaseDircStr(String baseDircStr) {
		this.baseDircStr = baseDircStr;
	}
	String baseDircStr ;
	String pacakageName ; //包名
	String sqlStr ;
	String datasourceName ;
	String baseClassName;
	String nameSufFix ;//後綴，如abstract,imp等,在子類中注明
	String tableName;
	String schemaName;
	public String getTableName() {
		return tableName;
	}
	public String getSchemaName() {
		return schemaName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public void setSchemaName(String schemaName) {
		this.schemaName = schemaName;
	}
	public String getNameSufFix() {
		return nameSufFix;
	}
	public void setNameSufFix(String nameSufFix) {
		this.nameSufFix = nameSufFix;
	}
	public String getBaseClassName() {
		return baseClassName;
	}
	public void setBaseClassName(String baseClassName) {
		this.baseClassName = baseClassName;
	}

}
