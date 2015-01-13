package com.sumit.service;

import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.sumit.model.EmailDTO;

@Service
public class MailServiceImpl implements MailService{

	@Autowired
	private JavaMailSenderImpl senderImpl;
		
	@Override
	public void configureMailServer(String username,String password) {
		senderImpl.setUsername(username);
		senderImpl.setPassword(password);
		Properties javaMailProperties = new Properties();
		javaMailProperties.put("mail.transport.protocol", "smtp");
		javaMailProperties.put("mail.smtp.auth", "true");
		javaMailProperties.put("mail.smtp.starttls.enable", "true");
		senderImpl.setJavaMailProperties(javaMailProperties);
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendMail(String from, String to, String subject,
			String content ) throws MessagingException {
		MimeMessage message =  senderImpl.createMimeMessage();
		
		// creates a simple e-mail object 
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		helper.setFrom(from);
		helper.setTo(to);
		helper.setSubject(subject);
		helper.setText(content);

		// let's attach the infamous windows Sample file (this time copied to c:/)
//		FileSystemResource file = new FileSystemResource(new File(filename));
//		helper.addAttachment("fileId", file);
		
		senderImpl.send(message);
		
	}

	@Override
	public void sendMail(EmailDTO emailDTO) throws MessagingException {
MimeMessage message =  senderImpl.createMimeMessage();
		
		// creates a simple e-mail object 
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		helper.setFrom(emailDTO.getFromEmailAddress());
		helper.setTo(emailDTO.getToEmailAddress());
		helper.setSubject(emailDTO.getSubject());
		helper.setText(emailDTO.getContent());

		// let's attach the infamous windows Sample file (this time copied to c:/)
//		FileSystemResource file = new FileSystemResource(new File(filename));
//		helper.addAttachment("fileId", file);
		
		senderImpl.send(message);
		
	}

}
