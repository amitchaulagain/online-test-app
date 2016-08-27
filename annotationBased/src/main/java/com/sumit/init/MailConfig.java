package com.sumit.init;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class MailConfig {

	@Bean
	JavaMailSenderImpl setMailSenderProperties() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setPort(587);
		mailSender.setHost("smtp.gmail.com");
		return mailSender;
	}

}
