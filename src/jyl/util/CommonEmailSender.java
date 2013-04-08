package jyl.util;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Date;

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

import com.baidu.rencai.util.RcBeanFactory;
import com.baidu.rencai.util.RequestFilter;
import com.baidu.rencai.util.mail.MailServiceImpl;

public class CommonEmailSender {
	private static final Logger logger = Logger.getLogger(CommonEmailSender.class);
	JavaMailSender mailSender;
	SimpleMailMessage msg;
	MailServiceImpl msi ;
	public CommonEmailSender()
	{
		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		HttpServletRequest request = (HttpServletRequest)RequestFilter.getTlCurrentRequest().get();
		if(request==null){
			mailSender  =(JavaMailSender)RcBeanFactory.getBean("mailSender");
			//mailSender  =(JavaMailSender)getApplicationContext(getRootPath()).getBean("mailSender");
		}
		else{
			mailSender = (JavaMailSender)RcBeanFactory.getBean(request,"mailSender");
		}	
		 msg = new SimpleMailMessage();
		 msi = new MailServiceImpl();
	}

	public void sendEmail(String emailTitle,String content,String appendixName,String filePath,String [] receiver,String[] ccReceiver)
	{
		if(receiver!=null)
		msg.setTo(receiver);
		if(ccReceiver!=null)
		msg.setCc(ccReceiver);
		String from = RmProjectHelper.getRcAdminDoc().valueOf("/rencai/email.props/emailuserNickname/text()");
		msg.setFrom( from);
		msg.setSubject(emailTitle);	
		MimeMessage mimeMsg = null;
		mimeMsg = mailSender.createMimeMessage();
		MimeMessageHelper helper;
		try {
			helper = new MimeMessageHelper(mimeMsg, true, "gbk");
			logger.info(msg.getFrom());
			helper.setFrom(msi.getInternetAddress(msg.getFrom()));
			helper.setTo(msg.getTo());
			helper.setCc(msg.getCc());
			helper.setSubject(msg.getSubject());
			mimeMsg.setSentDate(new Date());// 
			Multipart mp = helper.getMimeMultipart(); //new MimeMultipart("related");// related意味着可以发送html格式的邮件
			BodyPart bodyPart = new MimeBodyPart();// 正文 
			bodyPart.setDataHandler(new DataHandler(content, "text/html;charset=" + "gbk"));// 网页格式 
			mp.addBodyPart(bodyPart); 
			mimeMsg.setContent(mp);// 设置邮件内容对象 
			//添加附件
			 MimeBodyPart mbp2 = new MimeBodyPart();
		     // attach the file to the message
		   	 FileDataSource fds = new FileDataSource(new File (filePath) );
			 mbp2.setDataHandler(new DataHandler(fds));
			 mbp2.setFileName(MimeUtility.encodeText(appendixName));
			 mp.addBodyPart(mbp2);
			 mailSender.send(mimeMsg);
			 logger.info("receiver:"+receiver.toString());
		} catch (MessagingException e) {
			//
			logger.error(e);
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			logger.error(e);
		}
		
		
	}
	
}
