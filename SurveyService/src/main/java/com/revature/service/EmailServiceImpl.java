package com.revature.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.revature.logger.AppLogger;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * Used for sending email's to the Revature training associates
 *  @author Anastasia Miagkii
 *  @author Andres Toledo
 *  @author Jose Canela
 *  @author Monica Datta
 *  @author Wei Wu 
 *  @author Zachary Reagin
 */
@Service
public class EmailServiceImpl implements EmailService {
	
	/**An instance of a Session for sending out email's
	 * 
	 */
	private Session session;

	/**
	 * The username of the email account
	 */
	@Value("${survey_service.email_service.username}")
	private String emailServiceUsername;

	/**
	 * The password of the email account
	 */
	@Value("${survey_service.email_service.password}")
	private String emailServicePassword;
	
	/**
	 * Sets up properties of EmailService to achieve connection
	 */
	public EmailServiceImpl() {
		super();
		Properties prop = new Properties();
		prop.put("mail.smtp.auth", true);
		prop.put("mail.smtp.starttls.enable", "true");
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", "25");
		prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		
		this.session = Session.getInstance(prop, new Authenticator() {
		    @Override
		    protected PasswordAuthentication getPasswordAuthentication() {
		        return new PasswordAuthentication(emailServiceUsername,
						emailServicePassword);
		    }
		});  
	}

	/**Sends an email to a particular email address
	 * @param msg contents of the email 
	 * @param destination email address
	 * @throws AddressException exception thrown when a wrongly formatted address is encountered.
	 * @throws MessagingException base class for all exceptions thrown by the Messaging classes
	 */
	public void sendEmail(String msg,String destination) throws AddressException, MessagingException{
		
		AppLogger.log.info("SendEmails() was called");
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(emailServiceUsername));
		message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destination));
		message.setSubject("Please Fill Out The QC Survey");
		 
		MimeBodyPart mimeBodyPart = new MimeBodyPart();
		mimeBodyPart.setContent(msg, "text/html");
		 
		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(mimeBodyPart);
		 
		message.setContent(multipart);
		 
		Transport.send(message);
		
	}
}