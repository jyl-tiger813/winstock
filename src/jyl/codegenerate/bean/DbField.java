package jyl.codegenerate.bean;

/**
 * 类描述：   
 * 创建人：jinyongliang
 * 创建时间：2012-7-23 下午05:53:49   
 * 修改人：jinyongliang   
 * 修改时间：2012-7-23 下午05:53:49   
 * 修改备注：   
 * @version 
 */
public class DbField {

	String fieldName;
	String fieldType; //数据库类型映射对应的java类型
	String tableName;
	String dbColumName;
	public String getDbColumName() {
		return dbColumName;
	}
	public void setDbColumName(String dbColumName) {
		this.dbColumName = dbColumName;
	}
	public String getFieldName() {
		return fieldName;
	}
	public String getFieldType() {
		return fieldType;
	}
	public String getTableName() {
		return tableName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	
}
