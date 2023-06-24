package com.coder.sanam.services;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class MailServiceClient {
	@Autowired
	private JavaMailSender sender;

	public void sendEmail(String toString, String body, String subject) {
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo(toString);
		msg.setText(body);
		msg.setSubject(subject);
		msg.setFrom("sanam.coder.com");

		sender.send(msg);

		System.out.println("Mail Sent...");
	}
	
	public void sendMailWithAttachment(String toString, String body, String subject, String attachment) throws MessagingException {
		MimeMessage mimeMessage = sender.createMimeMessage();
		
		MimeMessageHelper mimeMsg = new MimeMessageHelper(mimeMessage, true);
		
		mimeMsg.setTo(toString);
		mimeMsg.setText(body);
		mimeMsg.setSubject(subject);
		
		FileSystemResource fileSystem = new FileSystemResource(new File(attachment));
		
		mimeMsg.addAttachment(fileSystem.getFilename(), fileSystem);
		
		sender.send(mimeMessage);
		
		System.out.println("Mail Sent with attchment...");
		
		
	}

}
