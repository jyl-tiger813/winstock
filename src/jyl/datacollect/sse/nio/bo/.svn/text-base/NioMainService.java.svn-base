package jyl.datacollect.sse.nio.bo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;

import jyl.util.MyCalendar;
import jyl.util.XmlDealer;
import jyl.util.nio.SeclectorHolder;

public class NioMainService {

	/**
	 * @param args
	 * 启动时就绑定一定数量的socket,等处理完一个时,继续绑定,使用单线程
	 * 需要考虑超时放弃任务
	 */
	int num = 10;//启动时就绑定的socket数目
	private  Calendar cal;
	private  Calendar stopCal;
	 private static String host = "www.sse.com.cn";
	 private static String str1 =  "GET http://www.sse.com.cn/sseportal/webapp/datapresent/SSEQueryTradingByProdTypeAct?prodType=7&searchDate=";
     private static String str2 = "&tab_flag=1 HTTP/1.0\r\nHost: www.sse.com.cn\r\n\r\n";
	 CharsetEncoder encoder = Charset.forName("GB2312").newEncoder();
	 CharsetDecoder decoder = Charset.forName("GB2312").newDecoder();
	 InetSocketAddress ip = null; // ip = new InetSocketAddress("localhost", 8888);
	private  void setCal() {
		getConfigs();
	}
	private  void getConfigs(){
		XmlDealer xd=new XmlDealer();
		String [] param={"starttime","endtime"};
		HashMap <String,String> result =xd.doParse(param,"sseconfig.xml");
		cal=MyCalendar.createCalendar(result.get("endtime"));
		stopCal=MyCalendar.createCalendar(result.get("starttime"));
	}
	private  Calendar getStopCal() {
		if(stopCal==null)
			setStopCal();
		return stopCal;
	}
	private  void setStopCal() {
		getConfigs();
	}
	private  Calendar nextDay(){
		if(cal==null)
		{
			setCal();
		return cal;
		}
		cal.add(Calendar.DAY_OF_YEAR,-1);
		while(cal.get(Calendar.DAY_OF_WEEK)==Calendar.SATURDAY||cal.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY)
			cal.add(Calendar.DAY_OF_MONTH,-1);		
				return cal;								
		}
	
	public void dispatch()
	{
		SocketChannel client;
	
			try {
				
				String middle = MyCalendar.getString(nextDay());
				
				client =  createSocketChannel(host);
			
				Selector selector = SeclectorHolder.getSeclector();
				//注册连接服务端socket动作
				
				/*Socket sk = client.socket();
				OutputStream os = sk.getOutputStream (); //向远程服务器写入请求
                os.write ("GET".getBytes ("GBK"));
                os.write (' ');
                os.write ((str1+middle+str2).getBytes ("GBK"));
                os.write (' ');*/
				String reques = str1+middle+str2;
				System.out.println(reques);
				ByteBuffer buf = ByteBuffer.allocate(1024*5);
				buf.put(reques.getBytes());
				buf.flip();
                client.write( buf);
                client.register(selector, client.validOps(),"sse");
            	
            	while (selector.isOpen()) {
            	try {
            		selector.select();
            	} catch (IOException e) {
            	e.printStackTrace();
            	}
            	Iterator it = selector.selectedKeys().iterator();
            	while (it.hasNext()) {
            	SelectionKey sk = (SelectionKey) it.next();
            	it.remove();
            	SocketChannel next = (SocketChannel) sk.channel();
            	buf.clear();
            	try {
            	// check whether finish
            	if (next.read(buf) == -1) {
            	sk.cancel();
            	next.close();

            	if (!it.hasNext()) {
            		selector.close();
            	}
            	}
            	} catch (IOException e) {
            	e.printStackTrace();
            	}
            	buf.flip();
            	if (buf.limit() == 0)
            	continue;
            	 CharBuffer cbf = decoder.decode(buf);
              	/*byte b[] = new byte[buf.limit()];
              	buf.get(b);
              	// System.out.println(next == sc[0]);
              	
              	System.out.print(new String(b));*/
              	 System.out.println(cbf.toString());
             	}
            	
            	}
            	
			}catch(Exception e){}
            	
              
			}
	
			//	client.connect(ip);
				/*while(selector.isOpen()){
				//	System.out.println("before");
				selector.select();
				//System.out.println("after");
				Iterator iter = selector.selectedKeys().iterator();
				while (iter.hasNext()) {
					SelectionKey key = (SelectionKey) iter.next();
					iter.remove();
					SocketChannel next = (SocketChannel) key.channel();
					delealing(next);
					if (key.isConnectable())
					{
						System.out.println("connectable");
					}
					if (key.isReadable())
					{
				//		System.out.println("readable");
					}
				}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	*/
		
	
	
	private  SocketChannel createSocketChannel(String host)
	throws IOException {
	SocketChannel sc = SocketChannel
	.open(new InetSocketAddress(host, 80));
	sc.configureBlocking(false);
	return sc;
	}
	
	private void delealing (SocketChannel sc)
	{
	//	System.out.println("dealing");
		/*ByteBuffer buf = ByteBuffer.allocate(1024*5);
		buf.flip();
		try {
			if (sc.read(buf) != -1) 
			{
			byte b[] = new byte[buf.limit()];
			buf.get(b);
			System.out.print(new String(b));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		// System.out.println(next == sc[0]);
//		System.out.print(new String(b));
		System.out.println("delealing");
		Socket sk = sc.socket() ;
		try {
			InputStreamReader isr = new InputStreamReader(sk.getInputStream(),"GBK");
			BufferedReader bfr = new BufferedReader(isr);
			String line = bfr.readLine();
			while(line!=null)
			{
				System.out.println(bfr.readLine());
				line = bfr.readLine();
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		NioMainService nms = new NioMainService();
		nms.dispatch();
	}

}
