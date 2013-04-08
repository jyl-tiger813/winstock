package jyl.datacollect.sse.crawl;

import java.io.IOException;
import java.util.Calendar;
import jyl.datacollect.sse.crawl.bean.CrawlParamBean;
import jyl.datacollect.sse.crawl.bean.InfoParamBean;
import jyl.datacollect.sse.crawl.parser.ListPageParser;
import jyl.util.MyCalendar;


/**
 * 类描述：sse定期报告url提取(原始数据)
 * 创建人：jinyongliang
 * 创建时间：2012-7-19 下午04:13:26   
 * 修改人：jinyongliang   
 * 修改时间：2012-7-19 下午04:13:26   
 * 修改备注：   
 * @version 
 */
public  class PeriodFinalReportSseImp {

	/**
	 * @param args 没有找到符合查询条件的纪录！
	 * http://www.sse.com.cn/sseportal/webapp/datapresent/SSEQueryCompanyStatement?isAdvQuery=1&PRODUCTID=600019&REPORTTYPE2=DQBG&BEGINDATE=2011-01-01&ENDDATE=2012-01-01&REPORTTYPE=ALL 
	 */
	//http://www.sse.com.cn/assortment/stock/list/stockdetails/announcement/index.shtml?COMPANY_CODE=600019&startDate=?begintime&endDate=?endtime&productId=600019&startDate=2013-01-03&endDate=2013-04-03&reportType=ALL&reportType2=%E5%AE%9A%E6%9C%9F%E5%85%AC%E5%91%8A&reportType=ALL&moreConditions=true
	//static String baseUrl = "http://www.sse.com.cn/sseportal/webapp/datapresent/SSEQueryCompanyStatement?isAdvQuery=1&PRODUCTID=?code&REPORTTYPE2=DQBG&BEGINDATE=?begintime&ENDDATE=?endtime&REPORTTYPE=ALL";
	static String baseUrl = "http://www.sse.com.cn/assortment/stock/list/stockdetails/announcement/index.shtml?COMPANY_CODE=600019&startDate=?begintime&endDate=?endtime&productId=600019&startDate=2013-01-03&endDate=2013-04-03&reportType=ALL&reportType2=%E5%AE%9A%E6%9C%9F%E5%85%AC%E5%91%8A&reportType=ALL&moreConditions=true";
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PeriodFinalReportSseImp ps = new PeriodFinalReportSseImp();
		ps.getUrlOneStock("600019");
	}

	/**
	 * @param string
	 */
	private void getUrlOneStock(String string) {
		// TODO Auto-generated method stub
		Calendar now = Calendar.getInstance();
		String endDateStr = MyCalendar.getString(now);
		for(int i =0 ;i<30;i++)
		{
			now.set(now.get(Calendar.YEAR)-1, now.get(Calendar.MONTH), Calendar.DAY_OF_MONTH);
			String beginDateStr = MyCalendar.getString(now);
			try {
				if(!getUrlOneStockOneDay(string,beginDateStr,endDateStr))// 该查询区间无数据，退出
					break;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			endDateStr = beginDateStr;
			//now.set(now.get(Calendar.YEAR)-1, now.get(Calendar.MONTH), Calendar.DAY_OF_MONTH);
		}
	}

	/**
	 * @param string
	 * @param beginDateStr
	 * @param endDateStr
	 * @return
	 * @throws IOException 
	 */
	private boolean getUrlOneStockOneDay(String string, String beginDateStr,
			String endDateStr) throws IOException {
		// TODO Auto-generated method stub
		
		String url = baseUrl.replaceAll("\\?code", string).replaceAll("\\?begintime", beginDateStr).replaceAll("\\?endtime", endDateStr);
		CrawlParamBean paramBean = new CrawlParamBean();
		paramBean.setUrl(url);
		paramBean.setLabelBlockOuterBegin("定期公告</a>");
		paramBean.setLabelBlockOuterEnd("<div id=\"announcementDiv\"></div>");
		paramBean.setLabelBlockInnerBegin("<td><a target=\"_blank\" href=\"");
		paramBean.setLabelBlockInnerEnd("</tr>");
		paramBean.setNoInfoLabel("没有找到符合查询条件的纪录！");
		//<td class="table3" bgcolor="white" height="20">
		String [] begin1 = {"<td class=\"table3\" bgcolor=\"white\" height=\"20\"><a href=\"","<td class=\"table3\" bgcolor=\"#dbedf8\" height=\"20\"><a href=\""};
		String [] end1 = {"\""};
		
		String [] begin2 = {"target=\"_blank\">"};
		String [] end2 = {"</a></td>"};
		
		String [] begin3 = {"<td class=\"table3\" bgcolor=\"white\" height=\"20\">","<td class=\"table3\" bgcolor=\"#dbedf8\" height=\"20\">"};
		String [] end3 = {"</td>"};
		
		String [] begin4 = {"</td><td class=\"table3\" bgcolor=\"white\" height=\"20\">","</td><td class=\"table3\" bgcolor=\"#dbedf8\" height=\"20\">"};
		String [] end4 = {"</td>"};
		
		String [] begin5 = {"<td class=\"table3\" bgcolor=\"white\" height=\"20\">","<td class=\"table3\" bgcolor=\"#dbedf8\" height=\"20\">"};
		String [] end5 = {"</td>"};
		InfoParamBean infoBean1 = new InfoParamBean("pdfUrl",begin1,end1,"报告链接url");
		InfoParamBean infoBean2 = new InfoParamBean("reportName",begin2,end2,"报告名称");
		InfoParamBean infoBean3 = new InfoParamBean("stockCode",begin3,end3,"股票代码");
		InfoParamBean infoBean4 = new InfoParamBean("yearStr",begin4,end4,"年度");
		InfoParamBean infoBean5 = new InfoParamBean("publishTime",begin5,end5,"披露时间");
		paramBean.getInfoBeans().add(infoBean1);
		paramBean.getInfoBeans().add(infoBean2);
		paramBean.getInfoBeans().add(infoBean3);
		paramBean.getInfoBeans().add(infoBean4);
		paramBean.getInfoBeans().add(infoBean5);
	//	paramBean.addInfoBean("name","begin","end");
		ListPageParser parser = new ListPageParser();
		boolean isSuccess = parser.parse(paramBean);
		return isSuccess;
		
	}

}
