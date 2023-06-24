package com.coder.sanam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import com.coder.sanam.services.MailServiceClient;

import jakarta.mail.MessagingException;

@SpringBootApplication
public class MailServiceClientApplication {
	
	@Autowired
	private MailServiceClient serviceClient;
	

	@Value("${sendMailClient.to}")
	private String toEmail;
	
	@Value("${sendMailClient.body}")
	private String mailBody;
	
	@Value("${sendMailClient.subject}")
	private String mailSubject;
	
	@Value("${sendMailClient.attachment.enabled}")
	private Boolean attachmentEnabled;
	
	@Value("${sendMailClient.attachment.path}")
	private String attachmentPath;

	public static void main(String[] args) {
		SpringApplication.run(MailServiceClientApplication.class, args);
	}
	
	@EventListener(ApplicationReadyEvent.class)
	public void sendMail(){
		try {
			if(!attachmentEnabled) {
			serviceClient.sendEmail(toEmail,mailBody,mailSubject);	
			}else {
			serviceClient.sendMailWithAttachment(toEmail, mailBody, mailSubject,
					attachmentPath);
			}
		} catch (MessagingException e) {
			System.out.println("Error while sending mail !!");
			e.printStackTrace();
		}
	}

}
