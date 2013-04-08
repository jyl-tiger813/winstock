package jyl.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

import net.sourceforge.jeval.EvaluationException;
import net.sourceforge.jeval.Evaluator;

/**
 * 类描述：四则运算,从右到左运算
 * 创建人：jinyongliang
 * 创建时间：2011-11-22 下午02:21:59   
 * 修改人：jinyongliang   
 * 修改时间：2011-11-22 下午02:21:59   
 * 修改备注：   
 * @version 
 */
public class ExpressionCount {
	
	 boolean ifLeftToRight = true; // 默认表达式为正常顺序
	 static Evaluator eval = new Evaluator(); 
	 static HashSet <Character> operationSign = new HashSet <Character> ();
	 static 
	 {
		 operationSign.add('*');
		 operationSign.add('-');
		 operationSign.add('+');
		 operationSign.add('/');
		 operationSign.add(')');
		 operationSign.add('(');
	 }
	 //("*","-")();
	 public static void main(String args[])
	 {
		 ExpressionCount epCount = new ExpressionCount();
		 String expression ="index1*(index2+index3)/index4-index6/2*50";// ;//"1-2/(3+2)*2";"((3+2)/2-1)*1.66
		 getParamIndexStr(expression);
		/* BigDecimal aB = new BigDecimal(1.5f);
		 BigDecimal bB = new BigDecimal(0.6f);*/
		 //System.out.println("result:"+result);
	 }

	 /**
	 * @param string
	 */
	public static String coutExpression(String string) {

		try {
			return eval.evaluate(string);
		} catch (EvaluationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}finally{
			System.out.println("return");
		}
	}

	public String suffix_expression(String expression)//中缀表达式转换后缀表达式（逆波兰式）
     {   
		 //TODO 超过两个字符的数字需要进行特殊处理 
	 if(ifLeftToRight)
	 {
		 expression = revertStrOrder(expression);
	 }
		/* char[] temp = expression.toCharArray();
		 char [] temp2 = new char [temp.length];
		 for(int i=0;i<temp.length;i++)
		 {
			char c = temp[temp.length-i-1];
			if(c=='(')
			{
				 temp2[i] = ')';
			}
			else if (c == ')')
			{
				temp2[i] = '(';
			}
			else 
			{
				temp2[i] = c;
			}
		 }
		 expression = new String (temp2);
	 }
		 System.out.println(expression);*/
   	  //Stack<Double> s2=new Stack<Double>();//存放数字栈
   	  Stack<Object> s3=new Stack<Object>();//存放结果栈
   	  Stack<Character> s4=new Stack<Character>();//存放操作符栈
   	  int len=expression.length();//
   	  
   	  char c1;
   	  double number;
   	  int m,n=-1;
   	  for(int i=0;i<len;i++)
   	  {
   		  c1=expression.charAt(i);
   		  if(isOprator(c1)||(i==len-1))//如果是运算符，将前面的数数字入s3栈，操作符入s4栈
   		  {  
   			 if(i==len-1&&(!isOprator(c1)))//当最后一位且不是操作符时，将前面的数压栈
   				m=i+1;
   			 else
   			    m=i;
   			 //操作数入栈,向前遍历到下一个运算符，将中间的字符串转化为double
   			 for(int j=i-1;j>=0;j--)
   			 {
   				 if(isOprator(expression.charAt(j)))
   				 {
   					 n=j;
   					 break;
   				 }
   				 n=j-1;
   			 }
   			 if(m!=n+1)//只有当这两个值不等时中间才会有操作数
   			 {
   				 number=Double.parseDouble(expression.substring(n+1,m));    			 
   			     s3.push(number);
   			 }
   			 //运算符入栈
   			 if(i==0&&(c1!='('))//当表达式第一个字符就为运算符且不是左括号时，返回表达式错误
   			 {
   				 return "表达式错误！";
   			 }
   			 else if(isOprator(c1))//且是操作符时
   			 {  
   				while(true)
   				{
   				if(s4.isEmpty()||s4.peek()=='('||c1=='(')//如果栈为空或者栈顶元素为（或者c1为（时，则直接将运算符压入栈内
   				{
   					s4.push(c1);
   					break;
   				}
   				else if(c1==')')//当c1为）时，依次弹出s4中的运算符并压入s3，直到（，舍弃这一对括号
   				{
   					while(s4.peek()!='(')
   					{
   						s3.push(s4.pop());
   						if(s4.isEmpty())//弹出所有不为左括号之后堆栈为空，则表达式不合法
       					{
       						return "缺少左括号";
       					}
   					}    					
   					s4.pop();//弹出（
   					break;
   				}
   				else
   				{
   					if(priorityCompare(c1,s4.peek())==1)//判断优先级，优先级高入栈，优先级低将栈顶运算符压入s3
   					{
   						s4.push(c1);
   						break;
   					}
   					else
   					{
   						s3.push(s4.pop());
   					}
   				}
   			}
   		   }
   		  }
   	      else
   	    	  continue;
   	  
          }
   	  while(!s4.isEmpty())//表达式结束后，依次将s4剩下的运算符压入s3
   	  {   
   		  if((char)s4.peek()=='(')
   			  return "缺少右括号";
   		  s3.push(s4.pop());
   	  }
   	  return count_result(s3);
     }
	 
     /**
	 * @param expression
	 * @return
	 */
	private static String revertStrOrder(String expression) {
		char[] temp = expression.toCharArray();
		 char [] temp2 = new char [temp.length];
		 ArrayList<Character> als = new ArrayList<Character> ();
		 boolean isAfterSybol = false;
		 int numB = 0;
		 for(int i=0;i<temp.length;i++)
		 {
			char c = temp[temp.length-i-1];
			if(c=='(')
			{
				 temp2[i] = ')';
				 isAfterSybol = true;
			}
			else if (c == ')')
			{
				temp2[i] = '(';
				isAfterSybol = true;
			}
			else if(operationSign.contains(c))
			{//* *
				
				temp2[i] = c;
				isAfterSybol = true;
			}
			else
			{
				//如果 是数字不逆转
				if(isAfterSybol)
				{
					conbimeData(temp2,als,numB);
				}
				
				als.add(c);
				isAfterSybol = false;
				numB = i;
			}
		 }
		 conbimeData(temp2,als,numB);
		 expression = new String (temp2);
		 System.out.println(expression);
		return expression;
	}
	

	/**
	 * @param temp2
	 * @param als
	 * @param numB 
	 */
	private static void conbimeData(char[] temp2, ArrayList<Character> als, int numB) {
		int fistNum = numB-als.size()+1;
		for(int i =0;i<als.size();i++  )
		{
			temp2[fistNum] = als.get(als.size()- i -1);
			fistNum++;
		}
		als.clear();
	}

	private int priorityCompare(char c1,char c2)
     { 
   	switch(c1)
   	{
   	 case '+':
   	 case '-':    		 
                return (c2 == '*' || c2 == '/' ? -1 : 0);  
        case '*': 
        case '/':  
                return (c2 == '+' || c2 == '-' ? 1 : 0);  
   	}
   	return 1;
	  }
     //判断字符是否为运算符，是为真，不是为假
     private boolean isOprator(Object c) {
		// TODO Auto-generated method stub
       try
       {
       	char c1=(Character)c;
       	if(c1=='+'||c1=='-'||c1=='*'||c1=='/'||c1=='('||c1==')')
       		return true;
       	
       }
       catch (Exception e) {
			// TODO: handle exception
       	return false;
		}    	
		return false;
	}
	private String count_result(Stack<Object> ob) {
   	  // TODO Auto-generated method stub	    
	     Stack<Object> s1=new Stack<Object>();//后缀表达式栈
	     Stack<Double> s2=new Stack<Double>();//操作数栈
	     //char c1;
	 //    Stack<Character> s3=new Stack<Character>();//操作符栈
	     
	     while(!ob.isEmpty())//将传入的栈逆序压入
	     {
	    	 s1.push(ob.pop());
	     }
	     while(!s1.isEmpty())
	     {   
	    	 if(!isOprator(s1.peek()))//遇到非操作符，压入s2栈
	    	 {
	    		 s2.push((Double)s1.pop());
	    	 }
	    	 else
	    	 {
	    		 s2.push(cout(s2.pop(),s2.pop(),(Character)s1.pop()));
	    	 }
	     }  
	     return Double.toString(s2.peek());
		
	}
	
	private Double cout(double s1,double s2,char s3)
	{   
		double result=0;
		switch(s3)
		{
		   case '+':
			   result=s1+s2;
			   break;
		   case '-':
			   result=s1-s2;
			   break;
		   case '*':
			   result=s1*s2;
			   break;
		   case '/':			   
			   result=s1/s2;
			   break;
	 	}
		return result;
	}

	/**
	 * TODO now
	 * @param formularStr
	 * @param forCountMap
	 * @param channelName 
	 * @return
	 */
	public static String countExpression(String formularStr,
			HashMap<String, Integer> forCountMap, String channelName) {
		HashMap<String,Integer> paramStrs = getParamIndexStr(formularStr);
		Set<String> keys = paramStrs.keySet();
		for(String key : keys)
		{
			int indexId = paramStrs.get(key);
			Integer value = forCountMap.get(indexId+"~_"+channelName);
			if(value==null)
				value = 0;
			formularStr = formularStr.replaceAll(key, value+"");
		}
		String result = coutExpression(formularStr);
		if(result == null)
		{
			System.out.println("formularStr:"+formularStr);
		}
		return result ;
	}
	

	/**
	 * @param formularStr
	 * @return
	 */
	public static HashMap<String,Integer>  getParamIndexStr(String formularStr) {
		char[] formular = formularStr.toCharArray();
		HashMap<String,Integer> result = new HashMap<String,Integer> ();
		boolean enterIndex = false;
		StringBuffer sbf = null;
		StringBuffer indexNum = null;
		for (int i = 0; i < formular.length; i++) {
			char c = formular[i];
			if (c == 'i') {
				enterIndex = true;
				sbf = new StringBuffer();
				indexNum = new StringBuffer();
				sbf.append(c);
				
				
			} else if (operationSign.contains(c)) {
				if (enterIndex == true) {
					Integer num = Integer.parseInt(indexNum.toString());
					result.put(sbf.toString(),num);
					enterIndex = false;
				}
			} else {
				if (enterIndex == true) {
					sbf.append(c);
					if(c>='0'&&c<='9')
						indexNum.append(c);
				}
			}
		}
		if(indexNum.length()>0)
		{
		Integer num = Integer.parseInt(indexNum.toString());
		result.put(sbf.toString(),num);
		}
		return result;
	}
	
}
