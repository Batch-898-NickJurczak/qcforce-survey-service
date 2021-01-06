package com.revature.scheduler;

import java.util.List;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.revature.logger.AppLogger;
import com.revature.service.EmailService;
import reactor.core.publisher.Mono;

/**
 * Used for scheduling email's to the Revature training associates
 *  @author Anastasia Miagkii
 *  @author Andres Toledo
 *  @author Jose Canela
 *  @author Monica Datta
 *  @author Wei Wu 
 *  @author Zachary Reagin
 */
@Component
public class EmailScheduler {
	
	/**Instance of an EmailService
	 * 
	 */
	private EmailService emailService;
	@Autowired
	public void setEmailService(EmailService emailService) {
		this.emailService = emailService;
	}

	/**
	 * The base url of the service for getting email list
	 */
	@Value("${survey_service.receiveEmailList_baseUrl}")
	private String receiveEmailList_baseUrl;

	/**
	 * The endpoint of the service for getting email list
	 */
	@Value("${survey_service.receiveEmailList_endpoint}")
	private String receiveEmailList_endpoint;

	/**
	 * Sends out email's to active training associates given a particular schedule.
	 */
	@Scheduled(cron = "${survey_service.scheduler_cron_pattern}")
	public void sendMailScheduler() {
		AppLogger.log.info("Scheduler ran");
		/*Calls the sendMail method that to sends email's to list of email addresses
		 *received from the recieveEmailList method. Currently, email's are being sent 
		 *every Monday at 10AM.
		*/

		recieveEmailList().subscribe(x -> {
			sendMail(x);
		});	
	}

	/**Retrieves the list of email's for all active training associates
	 * @return {@link Mono}{@link List}{@link String} of email's
	 */
	public Mono<List<String>> recieveEmailList() {
		WebClient emails = WebClient.create(receiveEmailList_baseUrl);
	return emails.get().uri(receiveEmailList_endpoint).retrieve().bodyToMono(new ParameterizedTypeReference<List<String>>() {});

	}
	/**Sends out an email containing the link to the QC Survey to all the active training associates
	 * @param data email addresses of all active associates
	 */
	public void sendMail(List<String> data) {
		/*
		try {
			//Sleeping the current thread for 0.5 seconds in order to not overwhelm gmail session 
			//Thread.sleep(500);
		} catch (InterruptedException e1) {
			AppLogger.log.error("sendMail: "+ e1.getMessage());
		}
		*/
		AppLogger.log.info("emailList: "+ data);
		//For every active training associate's email  
		for (String email : data) {
			AppLogger.log.info("sendMail: Individual Email - "+ email);
			try {
				//Send an email notification to an associate for them to fill out a QC form  
				emailService.sendEmail("Please complete the following QC form: <br> <a href=\"https://docs.google.com/forms/d/e/1FAIpQLSctgsH-__acrraIWMPDsV3XSFmTAujJNIxK9zKEhATsYsKHSw/viewform?usp=send_form\">Survey Link</a>", email);
			} catch (AddressException e) {
				AppLogger.log.error("sendMail: "+e.getMessage());
			} catch (MessagingException e) {
				AppLogger.log.error("sendMail: "+e.getMessage());
			}
		}
	}
	
}
