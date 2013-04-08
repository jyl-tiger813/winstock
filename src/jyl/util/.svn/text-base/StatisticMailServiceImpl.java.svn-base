package jyl.util;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.quickbundle.project.RmProjectHelper;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.baidu.rencai.cron.StaticReport;
import com.baidu.rencai.statistic.StatisticConstant;
import com.baidu.rencai.util.RcBeanFactory;
import com.baidu.rencai.util.RequestFilter;
import com.baidu.rencai.util.mail.MailServiceImpl;

public class StatisticMailServiceImpl extends MailServiceImpl{
	
	private static final Logger logger = Logger.getLogger(StatisticMailServiceImpl.class);

	/**
	 * @
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public void send(SimpleMailMessage msg, String templateName, Map model,
			String encoding,File file) {
		// TODO 带附件邮件的发送
		// 生成html邮件内容
		//String content = generateEmailContent(templateName, model);
		MimeMessage mimeMsg = null;
		HttpServletRequest request = (HttpServletRequest)RequestFilter.getTlCurrentRequest().get();
		JavaMailSender mailSender = null;
		if(request==null){
			mailSender  =(JavaMailSender)RcBeanFactory.getBean("mailSender");
			//mailSender  =(JavaMailSender)getApplicationContext(getRootPath()).getBean("mailSender");
		}
		else{
			mailSender = (JavaMailSender)RcBeanFactory.getBean(request,"mailSender");
		}		
		try
		{
			 String content = "详情见附件";
			mimeMsg = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMsg, true, encoding);
			logger.info(msg.getFrom());
			helper.setFrom(getInternetAddress(msg.getFrom()));
			helper.setTo(msg.getTo());
			helper.setCc(msg.getCc());
			helper.setSubject(msg.getSubject());
			mimeMsg.setSentDate(new Date());// 发送日期
			Multipart mp = helper.getMimeMultipart(); //new MimeMultipart("related");// related意味着可以发送html格式的邮件
			BodyPart bodyPart = new MimeBodyPart();// 正文 
			bodyPart.setDataHandler(new DataHandler(content, "text/html;charset=" + encoding));// 网页格式 
			mp.addBodyPart(bodyPart); 
			mimeMsg.setContent(mp);// 设置邮件内容对象 
			//添加附件
			 MimeBodyPart mbp2 = new MimeBodyPart();
		     // attach the file to the message
		   	 FileDataSource fds = new FileDataSource(file);
			   mbp2.setDataHandler(new DataHandler(fds));
			 //  mbp2.setFileName(MimeUtility.encodeText(fds.getName()));
			   Calendar cal = Calendar.getInstance();
			 	long result =  cal.getTimeInMillis()-24*3600*1000;
			 	cal.setTimeInMillis(result);
			 	 String dateStr =  cal.get(Calendar.YEAR)+"_"+(cal.get(Calendar.MONTH)+1)+"_"+ cal.get(Calendar.DAY_OF_MONTH);
				String fileStr = dateStr+"_"+"运营日报表.xls";
				
			   mbp2.setFileName(MimeUtility.encodeText(fileStr));
			    // create the Multipart and its parts to it
			    mp.addBodyPart(mbp2);
			
			  
		} catch (MessagingException ex)
		{
			logger.error(ex.getMessage(), ex);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}
		
		//msg.setText(content);
		mailSender.send(mimeMsg);
	}
	
	

}
