package jyl.datacollect.tdxdata.concretedata;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import jyl.datacollect.tdxdata.dao.TdxDataJdbcImp;
import jyl.util.Log4jLoader;

import org.apache.log4j.Logger;

/*
 * 从通达信文件中读取板块数据,包括代码,名字
 */
public class GetBlockCode 
{
	static Logger logger = Logger.getLogger(GetBlockCode.class);
	Set <HashMap<String,String>> stocks =new HashSet <HashMap<String,String>>();
	public static void main(String args[])
	{
		Log4jLoader.loaddefault();
		GetBlockCode gc = new GetBlockCode();
		gc.getSzczZxbz();
	}
/*
 * 得到深证成指\中小板指板块数据
 */
	private void getSzczZxbz() {
		// TODO Auto-generated method stub
		//String f1= "E:\\stock\\tdxdata\\concrete\\深证Ａ股.TXT";
		String f1= "E:\\stock\\tdxdata\\concrete\\创业板指.TXT";
		//String f2= "E:\\stock\\tdxdata\\concrete\\中小板指.TXT";
		getDataFromFile( f1);
	//	getDataFromFile( f2);
		TdxDataJdbcImp tdi = new TdxDataJdbcImp();
		tdi.insertblockdata(stocks,"tdxdataanalysis.cybz");
	//	logger.info("stock : "+stocks);
	}
	private void getDataFromFile(String f1) {
	
			// TODO Auto-generated method stub
			try {
				FileInputStream fis = new FileInputStream(f1);
				InputStreamReader fr = new InputStreamReader(fis,"GBK");
				BufferedReader bfr = new BufferedReader(fr);
				bfr.readLine();
				
				while(bfr.ready())
				{
					String comVolumn = null;
					String temp = bfr.readLine();
					String [] temp1 = temp.split("[\\s]+");
					if(temp1[1].length()<2)
					{
						temp1[1] = temp1[1]+temp1[2];
						if(temp1[1].length()<3){
							temp1[1] = temp1[1]+temp1[3];
							//comVolumn = temp1[20];
						}/*else{
						comVolumn = temp1[19];
						}*/
					}
					comVolumn = temp1[temp1.length-1];
					/*
					 * 需要进行仔细处理
					 */
					if(!temp1[1].contains("ST"))
					{
					HashMap oneRowData = new HashMap<String,String>();
					oneRowData.put("stockCode",temp1[0]);
					oneRowData.put("stockName",new String(temp1[1].getBytes(),"utf-8"));
					oneRowData.put("commuteVolumn",new String(comVolumn.getBytes(),"utf-8"));
					stocks.add(oneRowData);
					}
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
}
