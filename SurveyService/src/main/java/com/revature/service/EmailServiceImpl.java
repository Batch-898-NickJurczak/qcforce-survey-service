package com.revature.service;

import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import com.revature.config.EmailConfig;

/**
 * Used for sending email's to the Revature training associates
 * 
 * @author Anastasia Miagkii
 * @author Andres Toledo
 * @author Jose Canela
 * @author Monica Datta
 * @author Wei Wu
 * @author Zachary Reagin
 */
@Service
public class EmailServiceImpl implements EmailService {

	private EmailConfig emailConfig;
	
	@Autowired
	public void setEmailConfig(EmailConfig emailConfig)  {
		this.emailConfig = emailConfig;
	}
	
	public void validateEmail(String destination) throws AddressException {
		InternetAddress.parse(destination);
	}

	@Override
	public List<String> sendEmails(String msg, List<String> destinations) {
		List<String> failedDestinations = new ArrayList<>();
		for (String destination : destinations) {
			try {
				sendEmail(msg, destination);
			} catch (Exception e) {
				e.printStackTrace();
				failedDestinations.add(destination);
			}
		}
		return failedDestinations;
	}

	@Override
	public void sendEmail(String msg, String destination) throws AddressException, MessagingException {
		
		// Create a mail sender 
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost(this.emailConfig.getHost());
		mailSender.setPort(this.emailConfig.getPort());
		mailSender.setUsername(this.emailConfig.getUsername());
		mailSender.setPassword(this.emailConfig.getPassword());
		
		// Create an email instance 
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setFrom("qcforce@revature.net");
		mailMessage.setTo(destination);
		mailMessage.setSubject("Please submit feedback");
		mailMessage.setText(msg);
		
		//Send Mail
		mailSender.send(mailMessage);
	}
}