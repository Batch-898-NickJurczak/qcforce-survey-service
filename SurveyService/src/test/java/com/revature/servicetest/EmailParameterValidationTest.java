package com.revature.servicetest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.revature.config.EmailConfig;
import com.revature.service.EmailService;

/**
 * This class tests that the behavior of the sendEmail method in
 * EmailServiceImpl class with different parameters. The method should execute
 * is if both parameters are not null, neither string is empty and the email
 * address is valid. If any of these conditions are not met, an exception should
 * be thrown.
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { EmailService.class, EmailConfig.class })
@SpringBootTest
public class EmailParameterValidationTest {

	@Autowired
	private EmailService service;
	
	@Autowired
	private EmailConfig emailConfig;

	/**
	 * This tests if the sendEmail() method does not throw an exception when there
	 * are valid parameters (happy path)
	 */
	@Test
	void testSendEmailWithNoException() throws AddressException, MessagingException {
		final String msg = "Please fill out this survey";
		final String destination = "john.smith@revature.net";
		service.sendEmail(msg, destination);
		assertDoesNotThrow(() -> service.sendEmail(msg, destination));
	}

	/**
	 * This tests if the sendEmail() method throws an exception when the email
	 * address is not formatted correctly
	 */
	@Test
	void testSendEmailWithInvalidEmailFormat() {
		final String msg = "Please fill out this survey";
		final String destination = "john.smithrevature.net";
		assertThrows(AddressException.class, () -> service.sendEmail(msg, destination));
	}

	/**
	 * This tests if the sendEmail() method throws an exception if the email address
	 * parameter is an empty string
	 */
	@Test
	void testSendEmailWithEmptyStringForEmail() {
		final String msg = "Please fill out this survey";
		final String destination = "";
		assertThrows(AddressException.class, () -> service.sendEmail(msg, destination));
	}

	/**
	 * This tests if the sendEmail() method throws an exception if the message
	 * parameter is an empty string
	 */
	@Test
	void testSendEmailWithEmptyStringForMsg() {
		final String msg = "";
		final String destination = "john.smith@revature.net";
		fail("Custom exception for invalid message parameter needs to be written");
		// TODO - write custom exception
	}

	/**
	 * This tests if the sendEmail() method throws an exception if the message
	 * parameter is null
	 */
	@Test
	void testSendEmailWithNullValueForMsg() throws AddressException, MessagingException {
		final String msg = null;
		final String destination = "john.smith@revature.net";
		service.sendEmail(msg, destination);
		// TODO Test case - we need to create a Custom Exception to test this
	}

	/**
	 * This tests if the sendEmail() method throws an exception if the email
	 * parameter is null
	 */
	@Test
	void testSendEmailWithNullValueForEmail() throws AddressException, MessagingException {
		final String msg = "Please fill out this survey";
		final String destination = null;
		service.sendEmail(msg, destination);
		assertThrows(AddressException.class, () -> service.sendEmail(msg, destination));
	}

	/**
	 * This tests if the sendEmail() method throws an exception if the both
	 * parameters are null
	 */
	@Test
	void testSendEmailWithNullValueForBothParameters() throws AddressException, MessagingException {
		final String msg = null;
		final String destination = null;
		service.sendEmail(msg, destination);
		assertThrows(AddressException.class, () -> service.sendEmail(msg, destination));
	}

	/**
	 * Tests EmailService's sendEmails with all valid emails, should return an empty
	 * list
	 */
	@Test
	void testSendEmailsWithAllValidEmails() {
		final Set<String> validEmails = new HashSet<>(Arrays.asList("acacia.holliday@revature.net",
				"ksenia.milstein@revature.net", "zach.leonardo@revature.net"));
		assertEquals(0, service.sendEmails("Please fill out this survey", validEmails).size());
	}

	/**
	 * Tests EmailService's sendEmails with some invalid emails, should return a
	 * subset of inputted emails that are invalid
	 */
	@Test
	void testSendEmailsWithSomeInvalidEmails() {
		final Set<String> emails = new HashSet<>(
				Arrays.asList("acacia.holliday@revature.net", "ksenia.milstein@revaturenet", "zach.leonardo@revature"));
		List<String> expectedInvalidEmails = Arrays.asList("ksenia.milstein@revaturenet", "zach.leonardo@revature");
		assert (expectedInvalidEmails.equals(service.sendEmails("Please fill out this survey", emails)));
	}

	/**
	 * Tests EmailService's sendEmails with all invalid emails, should return the
	 * same list of emails as inputted as they are all invalid
	 */
	@Test
	void testSendEmailsWithAllInvalidEmails() {
		final Set<String> emails = new HashSet<>(
				Arrays.asList("acacia.hollidayrevature.net", "ksenia.milstein@revaturenet", "zach.leonardo@revature"));
		assert (emails.equals(service.sendEmails("Please fill out this survey", emails)));
	}

	/**
	 * Tests EmailService's sendEmails with an empty list of emails, should throw an
	 * IllegalArgumentException
	 * 
	 * @throws IllegalArgumentException
	 */
	@Test
	void testSendEmailsWithEmptyList() throws IllegalStateException {
		assertThrows(IllegalArgumentException.class,
				() -> service.sendEmails("Please fill out this survey", new HashSet<String>()));
	}

	/**
	 * Tests EmailService's sendEmails with empty string for message parameter should throw an
	 * IllegalStateExecption
	 * 
	 * @throws IllegalArgumentException
	 */
	@Test
	void testSendEmailsWithEmptyMessage() throws IllegalArgumentException {
		final List<String> validEmails = new ArrayList<>(
				Arrays.asList("acacia.hollidayrevature.net", "ksenia.milstein@revaturenet", "zach.leonardo@revature"));
		assertThrows(IllegalArgumentException.class, () -> service.sendEmails("", new HashSet<String>()));

	}

}
