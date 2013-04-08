package jyl.agent;
import java.io.IOException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.impl.client.DefaultHttpClient;


/**
 * 类描述：   
 * 创建人：jinyongliang
 * 创建时间：2012-2-22 下午10:02:04   
 * 修改人：jinyongliang   
 * 修改时间：2012-2-22 下午10:02:04   
 * 修改备注：   
 * @version 
 */
public class AgentIPGet {

	
	/**
	* HttpClient使用GET方式通过代理服务器读取页面的例子。
	* 
	* @author JAVA世纪网(java2000.net, laozizhu.com)
	*/
	private int  maxThread = 2;
	
	//152.26.53.4 80,遍历256网段
	public static void main(String[] args) throws Exception {
	
		AgentIPGet getter = new AgentIPGet();
		getter.searchUsableAgentIps();
	}
	
	/**
	 * 
	 */
	private void searchUsableAgentIps() {
		// TODO Auto-generated method stub
	/*	String beginIpStr = "152.26.53.4";
		String [] ipComposit = beginIpStr.split(".");
		int part1 = Integer.parseInt(ipComposit[0]);
		int part2 = Integer.parseInt(ipComposit[1]);
		int part3 = Integer.parseInt(ipComposit[2]);
		int part4 = Integer.parseInt(ipComposit[3]);*/
		
		for(int i1 =0;i1<255;i1++)
		{
			
			for(int i2 =0;i2<255;i2++)
			{
				for(int i3 =0;i3<255;i3++)
				{
					String ipStr = "152."+i1+"."+i2+"."+i3;
					while(maxThread<0)
					{
						//最多只能有10个线程同时跑，如果超过这个数，等待线程释放，非严格限制
						synchronized (this)
						{
						this.notifyAll();
						try {
							this.wait();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						}
					}
					SearchThread thread = new SearchThread();
					thread.setIp("210.245.85.219");//(ipStr);
					thread.setPort(8080);
					thread.setGetter(this);
					thread.start();
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}
			
		}
	}

	public void setMaxThread(int maxThread) {
		this.maxThread = maxThread;
	}

	public int getMaxThread() {
		return maxThread;
	}


	/**
	 * 
	 */
	public synchronized void plusThreadNumSymbol() {
		// TODO Auto-generated method stub
		++maxThread;
	}

	/**
	 * 
	 */
	public  void minusThreadNumSymbol() {
		// TODO Auto-generated method stub
		--maxThread;
	}
	class SearchThread extends Thread
	{
		private AgentIPGet getter ;
		String ip ;
		int port ;
		public int getPort() {
			return port;
		}
		public void setPort(int port) {
			this.port = port;
		}
		public String getIp() {
			return ip;
		}
		public void setIp(String ip) {
			this.ip = ip;
		}
		@Override
		public void run() {
			// TODO Auto-generated method stub
			super.run();
			try {
				testAgentUsable();
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		/**
		 * @throws IOException 
		 * @throws ClientProtocolException 
		 * 
		 */
		private void testAgentUsable() throws ClientProtocolException, IOException {
			// TODO Auto-generated method stub
			/*
			 * HttpClient httpClient = new HttpClient();
			//设置代理服务器的ip地址和端口
			httpClient.getHostConfiguration().setProxy("192.168.101.1", 5608);
		//使用抢先认证
			httpClient.getParams().setAuthenticationPreemptive(true);


			 */
			getter.plusThreadNumSymbol();
			try{
			DefaultHttpClient httpclient = new DefaultHttpClient();
			// 访问的目标站点，端口和协议
			HttpHost targetHost = new HttpHost("www.sina.com.cn");
			// 代理的设置
			HttpHost proxy = new HttpHost(ip, port);
			httpclient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);
			// 目标地址
			HttpGet httpget = new HttpGet("/");
		//	System.out.println("目标: " + targetHost);
		//	System.out.println("请求: " + httpget.getRequestLine());
			// 执行
			HttpResponse response = httpclient.execute(targetHost, httpget);
			HttpEntity entity = response.getEntity();
			 System.out.println("testIp : "+ip+":"+port);
		//	System.out.println("----------------------------------------");
			System.out.println(response.getStatusLine());
			if (entity != null) {
			    System.out.println("usable agent : "+ip+":"+port);
			    persist2DB(ip,port);
				//System.out.println("Response content length: " + entity.getContentLength());
			}
			// 显示结果
			/*BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent(), "UTF-8"));
			String line = null;
			while ((line = reader.readLine()) != null) {
			System.out.println(line);
			}
			if (entity != null) {
			entity.consumeContent();
			}*/
			}catch (Exception e)
			{
				e.printStackTrace();
			}
			finally{
			synchronized (getter)
			{
			getter.minusThreadNumSymbol();
			}
			}
		}
		/**
		 * @param ip2
		 * @param port2
		 */
		private void persist2DB(String ip2, int port2) {
			// TODO Auto-generated method stub
			
		}
		public void setGetter(AgentIPGet getter) {
			this.getter = getter;
		}
		public AgentIPGet getGetter() {
			return getter;
		}
		
	}

	}
	 


