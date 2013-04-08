package jyl.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;

import com.baidu.rencai.cron.DatabaseHelper;
import com.baidu.rencai.util.RenCaiUtil;

/**
 * 类描述：   加载较多数据，不使用静态属性，避免perGen溢出
 * 创建人：jinyongliang
 * 创建时间：2012-2-7 下午06:32:28   
 * 修改人：jinyongliang   
 * 修改时间：2012-2-7 下午06:32:28   
 * 修改备注：   
 * @version 
 */
public class StatisticRencaiUtil extends RenCaiUtil{
	 String user = "root";
	 String password = "root";
	 String url = "jdbc:mysql://localhost:3306/incrementbak?useUnicode=true&characterEncoding=utf8";
	 String sqlStr = " SELECT search_con_value,search_num,view_num" +
	 		" FROM keyword_search_view ORDER BY search_num DESC ";
	 String  fileStr = "D:\\sss3.xls";
	 Connection con = DatabaseHelper.getAdConnectionByParams(url, user, password);

	//行业信息,分多级行业,分不同的列显示
  public HashMap<String,Object> industryMap = new HashMap<String,Object> () ;
  
  public HashMap<String,Object> addressMap = new HashMap<String,Object> ();
  
  public StatisticRencaiUtil(){
	  //初始化行业信息
	  String industrySql =  "SELECT node_id,industry_name FROM industry_type_dim";
	  initDimensionMap(industryMap,"incrementbak.industry_type_dim",industrySql);
	  
	  //初始化地址维度信息
	  String addressSql = "SELECT node_id,CONCAT(IFNULL(id_level0_name,'')," +
	  		"	IFNULL(id_level4_name,''),IFNULL(id_level3_name,'')," +
	  		"IFNULL(id_level2_name,''),IFNULL(id_level1_name,''))" +
	  		"	FROM rencai_address_dim";
	  initDimensionMap(addressMap,"incrementbak.rencai_address_dim",addressSql);
  }

/**
 * @param industryMap2
 * @param string
 * @param industrySql 
 */
private void initDimensionMap(HashMap<String, Object> industryMap2,
		String string, String industrySql) {
	try {
		  DatabaseHelper.getValueMap(con,industrySql,industryMap2);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}


}

