package jyl.index.vrs.mondel;

/**
 * 类描述：   
 * 创建人：jinyongliang
 * 创建时间：Mar 22, 2013 11:33:11 AM   
 * 修改人：jinyongliang   
 * 修改时间：Mar 22, 2013 11:33:11 AM   
 * 修改备注：   
 * @version 
 */
public class TestDoubleCompare {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 double exp = 10E-3;
         double a1 = 8.88;
         double a2 = 8.99;
         double d1 = 8.888;
         double d2 = 8.889;
         System.out.println((a2-a1)>exp);
         System.out.println((d2-d1)>exp);

	}

}
