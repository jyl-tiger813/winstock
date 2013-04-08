package jyl.util;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Map.Entry;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
public class XmlDealer{
	private HashMap<String,String> hs=new HashMap<String,String>();
	private HashSet<String> hsini=new HashSet<String>();
	public HashMap<String,String> doParse(String [] paramini,String fileName){
		for(String str:paramini){
			hsini.add(str);
		}
		XmlDealerAim xl=new XmlDealerAim();	
		xl.getparams(fileName);
		return hs;
	}
	public static void main(String args[]){
		XmlDealer xd=new XmlDealer();
		Calendar cal = Calendar.getInstance();
		HashMap<String,String> hm = new HashMap<String,String>();
		hm.put("begintime", MyCalendar.getString(cal));
		
		xd.update(hm, "sseconfig.xml");
		/*String [] param={"starttime","endtime"};
		HashMap <String,String> result =xd.doParse(param,"sseconfig.xml");
		for(String str:result.keySet()){
			String temp=result.get(str);
			Calendar cal=MyCalendar.createCalendar(temp);
			System.out.println("kdk "+str+" "+MyCalendar.getString(cal));
		}*/
	}
	
 class XmlDealerAim {
	
	public   void getparams(String fileName){
	try {
	      XMLReader parser = 
	          org.xml.sax.helpers.XMLReaderFactory.createXMLReader();
	      // Create a new instance and register it with the parser
	      ContentHandler contentHandler = new MySaxtest();
	      parser.setContentHandler(contentHandler);
	      // Don't worry about this for now -- we'll get to it later
	      ClassLoader loader=XmlDealer.class.getClassLoader();
	      URL url=loader.getResource(fileName);
	      parser.parse(new InputSource(url.openStream() ) );
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	  }
	


	//try {
		
		/*ClassLoader loader=XmlDealer.class.getClassLoader();
	//	URL url=loader.getResource("sseconfig.xml");
		URL url=loader.getResource("sseconfig.xml");
		Document doc = builder.build(url);
		XMLOutputter outputter = new XMLOutputter();
        outputter.output(doc, System.out);*/

		//Object pro=	doc.getProperty("starttime");
		/* Element ele=doc.getRootElement();  
		 List list=ele.getAttributes();*/
		// String pro=ele.getAttributeValue("starttime");
	//	System.out.println("pro"+list.size());
	/*} catch (JDOMException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}*/

	
	
	
}
  class MySaxtest  implements ContentHandler {
		private String currentName;
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		// TODO Auto-generated method stub
		if(hsini.contains(currentName)){
		String line=new String(ch,start,length);
		System.out.println("lien" +line);
		hs.put(currentName, line);
		}
	}

	@Override
	public void endDocument() throws SAXException {
		// TODO Auto-generated method stub
	}

	@Override
	public void endElement(String uri, String localName, String name)
			throws SAXException {
		currentName=null;
		// TODO Auto-generated method stub
	//	System.out.println("test3 "+uri+" "+localName+" "+name);
	}

	@Override
	public void endPrefixMapping(String prefix) throws SAXException {
		// TODO Auto-generated method stub
	}

	@Override
	public void ignorableWhitespace(char[] ch, int start, int length)
			throws SAXException {
		// TODO Auto-generated method stub
	}

	@Override
	public void processingInstruction(String target, String data)
			throws SAXException {
		// TODO Auto-generated method stub
	}

	@Override
	public void setDocumentLocator(Locator locator) {
		// TODO Auto-generated method stub
	}

	@Override
	public void skippedEntity(String name) throws SAXException {
		// TODO Auto-generated method stub
	}

	@Override
	public void startDocument() throws SAXException {
		// TODO Auto-generated method stub
	}

	@Override
	public void startElement(String uri, String localName, String name,
			Attributes atts) throws SAXException {
		// TODO Auto-generated method stub
		//System.out.println("test10 "+uri+" "+localName+" "+name);
		currentName=name;
	}

	@Override
	public void startPrefixMapping(String prefix, String uri)
			throws SAXException {
		// TODO Auto-generated method stub
	}
	
}
public void update(HashMap<String, String> hm, String string) {
	// TODO Auto-generated method stub
	  ClassLoader loader=XmlDealer.class.getClassLoader();
	  System.out.println("string"+string);
      URL url=loader.getResource(string);
      System.out.println("url"+url);
	SAXBuilder sb =new SAXBuilder();
	try {
		 Document doc =	sb.build(url);
		 /*	 Element e =doc.getRootElement().getChild("time").getChild("begintime");
		 e.setText("ok");
		 doc.getRootElement().getChild("time").setContent(e);
		 System.out.println("Element "+e.getValue());
		e.setName("ok");
		e.setAttribute("begintime", "ok");*/
Set<Entry<String, String>> es =	hm.entrySet();
for(Entry<String, String> entry : es)
{
//	doc.setProperty();
	setSingle(entry.getKey(), entry.getValue(),doc);
	System.out.println("key"+entry.getKey()+entry.getValue());
}
System.out.println("doc"+doc);

		XMLOutputter    so = new XMLOutputter   ();
so.output(doc,new   FileWriter(string));
	} catch (JDOMException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}
private void setSingle(String key, String value, Document doc) {
	// TODO Auto-generated method stub
	String [] keyArray = key.split("\\.");
	Element e =doc.getRootElement();
	for(String keystr :keyArray)
	{
		e = e.getChild(keystr);
	}
	String value1 = e.getValue();
	System.out.println("key1:"+e.getName());
	System.out.println("value1:"+value1);
	 e.setText(value);
}
}
