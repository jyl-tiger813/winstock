package jyl.util;

import java.io.Serializable;
import java.util.HashMap;

import org.apache.log4j.Logger;

import com.baidu.rencai.statistic.extract.impl.MysqlExtractorWeb;


/**
 * 类描述：   
 * 创建人：jinyongliang
 * 创建时间：2011-12-29 下午04:31:12   
 * 修改人：jinyongliang   
 * 修改时间：2011-12-29 下午04:31:12   
 * 修改备注：   
 * @version 
 */
public class EfficientStringsFilter implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	boolean leftTORight = true; //默认从左到右
	IgnoreAccessFilterNode root  = new IgnoreAccessFilterNode();
	static String [] filterAccess ;//{".gif",".css", ".js",".png",".jpg",".swf",".ico"};
	private static final Logger logger = Logger.getLogger(EfficientStringsFilter.class);


	//};
	public static void main(String args[])
	{
		String [] filterAccess = {".gif",".css", ".js",".png",".jpg"};
		//纳秒
		long before = System.nanoTime();
		EfficientStringsFilter ig = new EfficientStringsFilter();
		ig.fillFilters(filterAccess,false);
		long interval = System.nanoTime() - before;
		System.out.println("interval:"+interval);
		//纳秒
		 before = System.nanoTime();
		boolean isContain = ig.containsFilterStr(".css");
		 interval = System.nanoTime() - before;
		System.out.println("interval:"+interval);
		System.out.println(isContain);
		long before1 = System.nanoTime();
		//测试
		//任何位置包含，非最前或最后位置条件符合(实际还不能满足需求)
		long interval1 = System.nanoTime() - before1;
		System.out.println("interval:"+interval1);
	}
	
	/**
	 * 是否包含目标字符串
	 * @param string
	 * @return
	 */
	public boolean containsFilterStr(String string) {
		// TODO Auto-generated method stub
		char [] ca = string.toCharArray();
		boolean isContain = true;
		IgnoreAccessFilterNode current = root;
		int beginNum = 0;
	
			for(int i =0;i<ca.length;i++)
			{
				if(!leftTORight)
				{
					beginNum =  ca.length -i -1;
				}
				else
					beginNum = i;
				Character ct = (Character)(ca[beginNum]);
				current = current.getChar(ct);
				if(current == null)
					return false;
				else if(current.isEndChar)
					return true;
			}
		return isContain;
		}
		

	/**
	 * @param filterAccess
	 * @param b
	 */
	public void fillFilters(String[] filterAccess, boolean b) {
		
		leftTORight = b;
		fillFilters(filterAccess);
		
	}
	/**
	 * @param filterAccess
	 */
	private void fillFilters(String[] filterAccess) {
		// TODO Auto-generated method stub
		for(String filterStr : filterAccess)
		{
			logger.info("filterStr:"+filterStr);
			fillOneFiltStr(filterStr.trim());
		}
	}
	/**
	 * @param filterStr
	 */
	private void fillOneFiltStr(String filterStr) {
		// TODO Auto-generated method stub
		char [] ca = filterStr.toCharArray();
		IgnoreAccessFilterNode current = root;
		int beginNum = 0;
	
			for(int i =0;i<ca.length;i++)
			{
				if(!leftTORight)
				{
					beginNum =  ca.length -i-1;
				}
				else
					beginNum = i;
				Character ct = (Character)(ca[beginNum]);
				current = current.addChar(ct);
				
			}
			//最后一个字符设置标志
			current.isEndChar = true;
		
		}
		
	}
	
	//内部类，节点
	 class IgnoreAccessFilterNode {
		HashMap<Character,IgnoreAccessFilterNode> subNode = new HashMap<Character,IgnoreAccessFilterNode> ();
		boolean isEndChar = false;

		/**
		 * @param ct
		 */
		public IgnoreAccessFilterNode addChar(Character ct) {
			// TODO Auto-generated method stub
			IgnoreAccessFilterNode ig = subNode.get(ct);
			if(ig==null)
			{
				ig = new IgnoreAccessFilterNode();
				subNode.put(ct, ig);
			}
			return ig;
		}

		/**
		 * @param ct
		 * @return
		 */
		public IgnoreAccessFilterNode getChar(Character ct) {
			// TODO Auto-generated method stub
			return subNode.get(ct);
		}


}
