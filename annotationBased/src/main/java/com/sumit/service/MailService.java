package com.sumit.service;

import javax.mail.MessagingException;

import com.sumit.model.EmailDTO;

public interface MailService {

	public void configureMailServer(String username, String password);
	
	public void sendMail(String from, String to, String subject, String content ) throws MessagingException;

	public void sendMail(EmailDTO emailDTO) throws MessagingException;
	
}
