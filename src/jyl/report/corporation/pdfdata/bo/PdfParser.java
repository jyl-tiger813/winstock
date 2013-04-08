package jyl.report.corporation.pdfdata.bo;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.cos.COSObject;
import org.apache.pdfbox.pdfparser.PdfParserAim;



/*
 * 解析pdf文件
 */
public class PdfParser {

	/**
	 * @param args
	 */
	
	public void dealPdf(String url)
		{
		
		FileInputStream is = null;
		InputStreamReader isr = null;
		BufferedReader bgr = null;
	
            try {
				is = new FileInputStream(url);
				isr = new InputStreamReader(is,"utf-8");
				bgr = new BufferedReader(isr);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            try {
            	PdfParserAim parser = new PdfParserAim(is);
            	parser.parse();
            	COSDocument cos = parser.getDocument();
            	 List<COSObject> coses = cos.	getObjects() ;
            	 for(COSObject cos1 :coses)
            	 {
            		 COSBase base = 	cos1.getObject() ;
            		System.out.println("base:"+base);
            	 }
				while(true)//!parser.isEOL())//改成true
				{
			//	String contenline = isr.readLine();
					String contenline = bgr.readLine();
				System.out.println("contenlin:"+contenline);//new String (contenline.getBytes(),"utf-8"));
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String rootPath ="e:\\book\\book\\book\\ThingkingInJava3rdEdition"; //"f:\\pdf\\600460_2007_1";
		//pdf文件路径
		String pdffile = rootPath + ".pdf";
		PdfParser pp = new PdfParser();
		System.out.println("contenlin11:");
		pp.dealPdf(pdffile);
}
}
