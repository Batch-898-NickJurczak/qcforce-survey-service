package com.revature.servicetest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.revature.service.EmailService;

/*
 * This class tests that the behavior of the sendEmail method in EmailServiceImpl class with 
 * different parameters. The method should execute is if both parameters are not null, neither string is empty and the email
 * address is valid. If any of these conditions are not met, an exception should be thrown.
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {EmailService.class})
class EmailParameterValidationTest {
	
	@Autowired
	private EmailService service;
	

	/*
	 * This tests if the sendEmails() method does not throw an exception when there are valid parameters 
	 * (happy path)
	 */
	@Test
	void testSendEmailsWithNoException() throws AddressException, MessagingException{
		final String msg = "Please fill out this survey";
		final String destination = "john.smith@revature.net";
		service.sendEmail(msg, destination);
		assertDoesNotThrow(() -> service.sendEmail(msg, destination));
	}

	/*
	 * This tests if the sendEmails() method throws an exception when the email address is not formatted
	 * correctly
	 */
	@Test
	void testSendEmailsWithInvalidEmailFormat() {
		final String msg = "Please fill out this survey";
		final String destination = "john.smithrevature.net";
		assertThrows(AddressException.class, () -> service.sendEmail(msg, destination));
	}
	
	/*
	 * This tests if the sendEmails() method throws an exception if the email address parameter is 
	 * an empty string
	 */
	@Test
	void testSendEmailsWithEmptyStringForEmail() {
		final String msg = "Please fill out this survey";
		final String destination = "";
		assertThrows(AddressException.class, () -> service.sendEmail(msg, destination));
	}
	
	
	/*
	 * This tests if the sendEmails() method throws an exception if the message parameter is an empty string
	 */
	@Test
	void testSendEmailsWithEmptyStringForMsg() {
		final String msg = "";
		final String destination = "john.smith@revature.net";
		fail("Custom exception for invalid message parameter needs to be written");
		//TODO - write custom exception
	}
	
	/*
	 * This tests if the sendEmails() method throws an exception if the message parameter is null
	 */
	@Test
	void testSendEmailsWithNullValueForMsg() throws AddressException, MessagingException {
		final String msg = null;
		final String destination = "john.smith@revature.net";
		service.sendEmail(msg, destination);
		//TODO Test case - we need to create a Custom Exception to test this
	}
	
	/*
	 * This tests if the sendEmails() method throws an exception if the email parameter is null
	 */
	@Test
	void testSendEmailsWithNullValueForEmail() throws AddressException, MessagingException {
		final String msg = "Please fill out this survey";
		final String destination = null;
		service.sendEmail(msg, destination);
		assertThrows(AddressException.class, () -> service.sendEmail(msg, destination));
	}
	
	/*
	 * This tests if the sendEmails() method throws an exception if the both parameters are null
	 */
	@Test
	void testSendEmailsWithNullValueForBothParameters() throws AddressException, MessagingException {
		final String msg = null;
		final String destination = null;
		service.sendEmail(msg, destination);
		assertThrows(AddressException.class, () -> service.sendEmail(msg, destination));
	}
	

}
