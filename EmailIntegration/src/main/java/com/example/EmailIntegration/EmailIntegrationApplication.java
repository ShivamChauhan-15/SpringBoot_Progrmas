package com.example.EmailIntegration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.mail.MailException;

@SpringBootApplication
public class EmailIntegrationApplication {
	@Autowired
	private EmailService emailService;
	public static void main(String[] args) {
		SpringApplication.run(EmailIntegrationApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void mailSend()throws Exception{
		emailService.sendSimpleEmail("svrajput1502@gmail.com","Testing integration","This is email integration");
	}

}
