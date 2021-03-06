package com.sqe.shop.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.SimpleMailMessage;

public class SendMail {

	private static Properties props;
	private static Authenticator auth;
	public static String[] mailTo;
	static {
		props = new Properties();
//		try {
//			InputStream in = SendMail.class.getResourceAsStream("/common-config.properties");
//			props.load(in);
//			auth = new MailAuthenticator(props.getProperty("mail.account"), props.getProperty("mail.passowrd"));
//			mailTo = props.getProperty("mail.to").split(",");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}

	private static class MailAuthenticator extends Authenticator {
		private String userName;
		private String password;

		public MailAuthenticator(String userName, String password) {
			super();
			this.userName = userName;
			this.password = password;
		}

		@Override
		protected PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(userName, password);
		}
	}

	public static void sendHtmlMail(String emailTitle, String emailContent, String to) {
		JavaMailSenderImpl senderImpl = new JavaMailSenderImpl();
		// 设定mail server
		senderImpl.setHost("smtp.exmail.qq.com");

		// 建立邮件消息,发送简单邮件和html邮件的区别  
        MimeMessage mailMessage = senderImpl.createMimeMessage();  
        // 为防止乱码，添加编码集设置  
        MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage,  
                "UTF-8");  
       
        try {
        	 messageHelper.setTo(to);
			messageHelper.setFrom("1832806774@qq.com");
			messageHelper.setSubject(emailTitle);
		
			 messageHelper.setText(emailContent, true);  
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		 senderImpl.setUsername("@qq.com"); //
		// 根据自己的情况,设置username
		senderImpl.setPassword("edrsseogjhwgfaii"); // 根据自己的情况, 设置password

		Properties prop = new Properties();
		prop.put(" mail.smtp.auth ", " true "); // 将这个参数设为true，让服务器进行认证,认证用户名和密码是否正确
		prop.put(" mail.smtp.timeout ", " 25000 ");
		senderImpl.setJavaMailProperties(prop);
		// 发送邮件
		senderImpl.send(mailMessage);
		
		System.out.println("mail send");
	}


	public static void main(String args[]) {
		JavaMailSenderImpl senderImpl = new JavaMailSenderImpl();
		// 设定mail server
		senderImpl.setHost("smtp.qq.com");

		// 建立邮件消息
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		// 设置收件人，寄件人 用数组发送多个邮件
		// String[] array = new String[] {"sun111@163.com","sun222@sohu.com"};
		// mailMessage.setTo(array);
		mailMessage.setTo("@qq.com");
		mailMessage.setFrom("@qq.com");
		mailMessage.setSubject("测试简单文本邮件发送！ ");
		mailMessage.setText(" 测试我的简单邮件发送机制！！ ");

		senderImpl.setUsername("@qq.com"); // 根据自己的情况,设置username
		senderImpl.setPassword("edrsseogjhwgfaii"); // 根据自己的情况, 设置password

		Properties prop = new Properties();
		prop.put(" mail.smtp.auth ", " true "); // 将这个参数设为true，让服务器进行认证,认证用户名和密码是否正确
		prop.put(" mail.smtp.timeout ", " 25000 ");
        prop.setProperty("mail.host", "smtp.qq.com");
        prop.setProperty("mail.transport.protocol", "smtp");
        prop.setProperty("mail.smtp.auth", "true");
        prop.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        prop.setProperty("mail.smtp.port", "465");
        prop.setProperty("mail.smtp.socketFactory.port", "465");

		senderImpl.setJavaMailProperties(prop);
		// 发送邮件
		senderImpl.send(mailMessage);

		// logger.info(" 邮件发送成功.. ");
	}

}
