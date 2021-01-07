package com.revature.service;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

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
	public void setEmailConfig(EmailConfig emailConfig) {
		this.emailConfig = emailConfig;
	}

	@Override
	public boolean isValidEmailAddress(String email) {

		String emailRegex = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)"
				+ "*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]"
				+ "|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]"
				+ "*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]"
				+ "|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]"
				+ "?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]"
				+ "|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";

		Pattern pat = Pattern.compile(emailRegex);

		if (email == null)
			return false;

		return pat.matcher(email.toLowerCase()).matches();
	}

	@Override
	public void validateEmail(String destination) throws AddressException {
		InternetAddress.parse(destination);
	}

	@Override
	public Set<String> sendEmails(String msg, Set<String> destinations) {
		Set<String> failedDestinations = new HashSet<>();
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

		// Send Mail
		mailSender.send(mailMessage);
	}
}