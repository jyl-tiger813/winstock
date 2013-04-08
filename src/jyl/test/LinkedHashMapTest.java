package jyl.test;

import java.util.LinkedHashMap;
import java.util.Map.Entry;

/**
 * 类描述：   
 * 创建人：jinyongliang
 * 创建时间：2011-10-16 下午09:05:58   
 * 修改人：jinyongliang   
 * 修改时间：2011-10-16 下午09:05:58   
 * 修改备注：   
 * @version 
 */
public class LinkedHashMapTest extends LinkedHashMap{
	private int rowNow  = 0;

	/**
	 * @param i
	 * @param d
	 * @param b
	 */
	public LinkedHashMapTest(int i, float d, boolean b) {
		super(i,d,b);
	}

	@Override
	protected boolean removeEldestEntry(Entry eldest) {
		// TODO Auto-generated method stub
		System.out.println("eldest:"+eldest.getKey());
		if((rowNow-(Integer)eldest.getKey())>140)
			//条件中可以加上时间条件，hash中最早数据的时间比当前读取记录的时间早某一常量时，触发删除动作
			//进行匹配操作，如果符合条件，持久化到数据库中 
		{
			System.out.println("removeKey:"+(Integer)eldest.getKey());
			return true;
		}
		else 
			return false;
		//return super.removeEldestEntry(eldest);
	}

	public static void main(String args[])
	{
		//false,从Map中get和put数据不影响判断为是否是eldest数据,true则相反 
		//false:按第一次放置数据判断是否为eldest,true按最后放置的时间判断是否为eldest
		//apache日志匹配用true方式
	
		LinkedHashMapTest lu = new LinkedHashMapTest(10,0.75f,true);
	//	lu.put(1, 1+"str");
		lu.put(1, 1+"str");
		lu.put(2, 2+"str");
		lu.put(2, 2+"str");
		//lu.put(1, 1+"str");
		String str = (String)lu.get(2);
	//	System.out.println("str:"+str);
		for(int i = 1;i<90;i++)
		{
			lu.rowNow = i;
			lu.put(i, i+"str");
			System.out.println("size:"+lu.size());
		}
		lu.put(1, 1+"str");
		for(int i = 90;i<290;i++)
		{
			lu.rowNow = i;
			lu.put(i, i+"str1");
			System.out.println("size1:"+lu.size());
		}
	}
}
