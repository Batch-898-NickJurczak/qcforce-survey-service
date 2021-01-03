package com.revature.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;
import java.io.FileInputStream;
import java.util.Set;

import javax.mail.MessagingException;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.google.common.collect.Sets;
import com.revature.response.EmailResponse;
import com.revature.service.DistributionService;
import com.revature.util.InvalidBatchIdException;
import com.revature.util.InvalidSurveyIdException;

/*
 * These tests the post methods of the Distribution controller. The methods need a valid BatchId and correctly formatted 
 * email addresses. If either condition is not met, an exception should be thrown or return a list of invalid emails.
 */
@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = { DistributionService.class, DistributionController.class })
class DistributionControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private DistributionService service;

	/**
	 * Checks the SendEmailByBatchIdAndCSV method with a valid path parameter and no
	 * incorrectly formatted emails in the database should return
	 */
//	@Test
//	void distributionControllerSendEmailsByBatchId_withoutError() throws Exception {
//
//		// input parameters
//		final int validBatchId = 2010;
//		final int surveyId = 100;
//
//		// return
//		final Set<String> malformedEmails = Sets.newHashSet();
//		final Set<String> tokenFailedEmails = Sets.newHashSet();
//		final Set<String> sendFailedEmails = Sets.newHashSet();
//		final String statusMessage = "";
//		EmailResponse response = new EmailResponse(malformedEmails, tokenFailedEmails, sendFailedEmails, statusMessage);
//
//		// dependency
//		Mockito.when(service.sendEmailsByBatchId(validBatchId, surveyId)).thenReturn(response);
//
//		// when
//		RequestBuilder request = MockMvcRequestBuilders.post("/distribute/" + surveyId + "/" + validBatchId);
//		MvcResult result = mockMvc.perform(request).andExpect(status().isOk()).andReturn();
//		verify(service).sendEmailsByBatchId(validBatchId, surveyId);
//
//		// then
//		assertEquals("{\"malformedEmails\":[],\"tokenFailedEmails\":[],\"sendFailedEmails\":[],\"statusMessage\":\"\"}",
//				result.getResponse().getContentAsString());
//
//	}
//
//	/**
//	 * Checks the SendEmailByBatchIdCSV method with an invalid batch Id. The
//	 * distribution service should throw an InvalidBatchIdException and the request
//	 * should return a 404 not found status code
//	 */
//	@Test
//	void distributionControllerSendEmailsByBatchId_invalidBatchId() throws Exception {
//
//		// given
//		int invalidBatchId = 3010; // does not exist
//		int surveyId = 100;
//
//		// dependency
//		Mockito.when(service.sendEmailsByBatchId(invalidBatchId, surveyId)).thenThrow(InvalidBatchIdException.class);
//
//		// when
//		RequestBuilder request = MockMvcRequestBuilders.post("/distribute/" + surveyId + "/" + invalidBatchId);
//		MvcResult result = mockMvc.perform(request).andExpect(status().isNotFound()).andReturn();
//		verify(service).sendEmailsByBatchId(invalidBatchId, surveyId);
//
//		// then
//		assertEquals("null", result.getResponse());
//	}
//
//	/**
//	 * Checks the SendEmailByBatchIdCSV method with an invalid survey Id. The
//	 * distribution service should throw an InvalidBatchIdException and the request
//	 * should return a 404 not found status code
//	 */
//	@Test
//	void distributionControllerSendEmailsByBatchId_invalidSurveyId() throws Exception {
//
//		// given
//		int validBatchId = 2010;
//		int invalidSurveyId = 404; // does not exist
//
//		// dependency
//		Mockito.when(service.sendEmailsByBatchId(validBatchId, invalidSurveyId))
//				.thenThrow(InvalidSurveyIdException.class);
//
//		// when
//		RequestBuilder request = MockMvcRequestBuilders.post("/distribute/" + invalidSurveyId + "/" + validBatchId);
//		MvcResult result = mockMvc.perform(request).andExpect(status().isNotFound()).andReturn();
//		verify(service).sendEmailsByBatchId(validBatchId, invalidSurveyId);
//
//		// then
//		assertEquals("null", result.getResponse());
//	}
//
//	/**
//	 * Checks the SendEmailByBatchIdCSV method with an invalid survey Id. The
//	 * distribution service should throw an InvalidBatchIdException and the request
//	 * should return a 404 not found status code
//	 */
//	@Test
//	void distributionControllerSendEmailsByBatchId_invalidSurveyIdAndBatchId() throws Exception {
//
//		// given
//		int invalidBatchId = 3010; // does not exist
//		int invalidSurveyId = 404; // does not exist
//
//		// dependency
//		Mockito.when(service.sendEmailsByBatchId(invalidBatchId, invalidSurveyId))
//				.thenThrow(InvalidBatchIdException.class);
//
//		// when
//		RequestBuilder request = MockMvcRequestBuilders.post("/distribute/" + invalidSurveyId + "/" + invalidBatchId);
//		MvcResult result = mockMvc.perform(request).andExpect(status().isNotFound()).andReturn();
//		verify(service).sendEmailsByBatchId(invalidBatchId, invalidSurveyId);
//
//		// then
//		assertEquals("null", result.getResponse());
//	}
//
//	/**
//	 * Checks the SendEmailByBatchId method with a valid path parameter,
//	 */
//	@Test
//	void distributionControllerSendEmailsByBatchId_withInvalidEmail() throws Exception {
//
//		// input parameters
//		final int validBatchId = 2010;
//		final int surveyId = 100;
//
//		// return
//		final Set<String> malformedEmails = Sets.newHashSet("acacia.hollidayrevature.net");
//		final Set<String> tokenFailedEmails = Sets.newHashSet();
//		final Set<String> sendFailedEmails = Sets.newHashSet();
//		final String statusMessage = "";
//		EmailResponse response = new EmailResponse(malformedEmails, tokenFailedEmails, sendFailedEmails, statusMessage);
//
//		// dependency
//		Mockito.when(service.sendEmailsByBatchId(validBatchId, surveyId)).thenReturn(response);
//
//		// when
//		RequestBuilder request = MockMvcRequestBuilders.post("/distribute/" + surveyId + "/" + validBatchId);
//		MvcResult result = mockMvc.perform(request).andExpect(status().isBadRequest()).andReturn();
//		verify(service).sendEmailsByBatchId(validBatchId, surveyId);
//
//		// then
//		assertEquals(response, result);
//		assertEquals(
//				"{\"malformedEmails\":[\"acacia.hollidayrevature.net\"],\"tokenFailedEmails\":[],\"sendFailedEmails\":[],\"statusMessage\":\"\"}",
//				result.getResponse().getContentAsString());
//
//	}
//
//	/**
//	 * Checks the SendEmailsByBatchId method with a valid batchId, but with multiple
//	 * invalid emails.
//	 */
//	@Test
//	void distributionControllerSendEmailsByBatchId_withInvalidMultipleEmails() throws Exception {
//
//		// input parameters
//		final int validBatchId = 2010;
//		final int surveyId = 100;
//
//		// return
//		final Set<String> malformedEmails = Sets.newHashSet("acacia.hollidayrevature.net",
//				"ksenia.milstein@revaturenet", "zach.leonardo@revature");
//		final Set<String> tokenFailedEmails = Sets.newHashSet();
//		final Set<String> sendFailedEmails = Sets.newHashSet();
//		final String statusMessage = "";
//		EmailResponse response = new EmailResponse(malformedEmails, tokenFailedEmails, sendFailedEmails, statusMessage);
//
//		// dependency
//		Mockito.when(service.sendEmailsByBatchId(validBatchId, surveyId)).thenReturn(response);
//
//		// when
//		RequestBuilder request = MockMvcRequestBuilders.post("/distribute/" + surveyId + "/" + validBatchId);
//		MvcResult result = mockMvc.perform(request).andExpect(status().isOk()).andReturn();
//		verify(service).sendEmailsByBatchId(validBatchId, surveyId);
//
//		// then
//		assertEquals(
//				"{\"malformedEmails\":[\"acacia.hollidayrevature.net\",\"ksenia.milstein@revaturenet\",\"zach.leonardo@revature\"],"
//						+ "\"tokenFailedEmails\":[],\"sendFailedEmails\":[],\"statusMessage\":\"\"}",
//				result.getResponse().getContentAsString());
//
//	}
//
//	/**
//	 * Checks the SendEmailByBatchId method one email that failed to send
//	 */
//	@Test
//	void distributionControllerSendEmailsByBatchId_withOneMessagingException() throws Exception {
//
//		// input parameters
//		final int validBatchId = 2010;
//		final int surveyId = 100;
//
//		// return
//		final Set<String> malformedEmails = Sets.newHashSet();
//		final Set<String> tokenFailedEmails = Sets.newHashSet();
//		final Set<String> sendFailedEmails = Sets.newHashSet("acacia.holliday@revature.net",
//				"ksenia.milstein@revature.net", "zach.leonardo@revature.net");
//		final String statusMessage = "";
//		EmailResponse response = new EmailResponse(malformedEmails, tokenFailedEmails, sendFailedEmails, statusMessage);
//
//		// dependency
//		Mockito.when(service.sendEmailsByBatchId(validBatchId, surveyId)).thenThrow(MessagingException.class)
//				.thenReturn(response);
//
//		// when
//		RequestBuilder request = MockMvcRequestBuilders.post("/distribute/" + surveyId + "/" + validBatchId);
//		MvcResult result = mockMvc.perform(request).andExpect(status().isOk()).andReturn();
//		verify(service).sendEmailsByBatchId(validBatchId, surveyId);
//
//		// then
//		assertEquals(
//				"{\"malformedEmails\":[],\"tokenFailedEmails\":[],\"sendFailedEmails\":[\"acacia.holliday@revature.net\", "
//						+ "\"ksenia.milstein@revature.net\", \"zach.leonardo@revature.net\"],\"statusMessage\":\"\"}",
//				result.getResponse().getContentAsString());
//
//	}
//
//	/**
//	 * Checks the SendEmailByBatchId method with multiple email that failed to send
//	 */
//	@Test
//	void distributionControllerSendEmailsByBatchId_withMultipleMessagingException() throws Exception {
//
//		// input parameters
//		final int validBatchId = 2010;
//		final int surveyId = 100;
//
//		// return
//		final Set<String> malformedEmails = Sets.newHashSet();
//		final Set<String> tokenFailedEmails = Sets.newHashSet();
//		final Set<String> sendFailedEmails = Sets.newHashSet("acacia.holliday@revature.net");
//		final String statusMessage = "";
//		EmailResponse response = new EmailResponse(malformedEmails, tokenFailedEmails, sendFailedEmails, statusMessage);
//
//		// dependency
//		Mockito.when(service.sendEmailsByBatchId(validBatchId, surveyId)).thenThrow(MessagingException.class)
//				.thenReturn(response);
//
//		// when
//		RequestBuilder request = MockMvcRequestBuilders.post("/distribute/" + surveyId + "/" + validBatchId);
//		MvcResult result = mockMvc.perform(request).andExpect(status().isOk()).andReturn();
//		verify(service).sendEmailsByBatchId(validBatchId, surveyId);
//
//		// then
//		assertEquals(
//				"{\"malformedEmails\":[],\"tokenFailedEmails\":[],\"sendFailedEmails\":[\"acacia.holliday@revature.net\"],\"statusMessage\":\"\"}",
//				result.getResponse().getContentAsString());
//	}

	/**
	 * Checks the SendEmailByBatchIdAndCSV method with a valid batchId, and a valid
	 * CSV.
	 */
	@Test
	void distributionControllerSendEmailsByCSV_withoutError() throws Exception {

		// input parameters
		final int validBatchId = 2010;
		final int surveyId = 100;
		MockMultipartFile emailFile = new MockMultipartFile("csv", "emails.csv", "text/csv",
				"acacia.holliday@revature.net, ksenia.milstein@revature.net, zach.leonardo@revature.net".getBytes());

		// return
		final Set<String> malformedEmails = Sets.newHashSet();
		final Set<String> tokenFailedEmails = Sets.newHashSet();
		final Set<String> sendFailedEmails = Sets.newHashSet();
		final String statusMessage = "";
		EmailResponse response = new EmailResponse(malformedEmails, tokenFailedEmails, sendFailedEmails, statusMessage);

		Mockito.when(service.sendEmailsByCSV(validBatchId, surveyId, emailFile)).thenReturn(response);

		// when
		RequestBuilder request = MockMvcRequestBuilders.multipart("/distribute/" + surveyId).file(emailFile)
				.param("batchId", Integer.toString(validBatchId));
		MvcResult result = mockMvc.perform(request).andExpect(status().isOk()).andReturn();
		verify(service).sendEmailsByCSV(validBatchId, surveyId, emailFile);

		// then
		assertEquals("{\"malformedEmails\":[],\"tokenFailedEmails\":[],\"sendFailedEmails\":[],\"statusMessage\":\"\"}",
				result.getResponse().getContentAsString());
	}

	/**
	 * Checks the SendEmailByBatchIdAndCSV method with an invalid batchId, and a
	 * valid CSV.
	 */
	@Test
	void distributionControllerSendEmailsByCsv_invalidBatchId() throws Exception {

		// input parameters
		final int invalidBatchId = 3010; // does not exist
		final int surveyId = 100;
		MockMultipartFile emailFile = new MockMultipartFile("csv", "emails.csv", "text/plain",
				"acacia.holliday@revature.net, ksenia.milstein@revature.net, zach.leonardo@revature.net".getBytes());

		// dependency
		Mockito.when(service.sendEmailsByCSV(invalidBatchId, surveyId, emailFile))
				.thenThrow(InvalidBatchIdException.class);

		// when
		RequestBuilder request = MockMvcRequestBuilders.multipart("/distribute/" + surveyId).file(emailFile)
				.param("batchId", Integer.toString(invalidBatchId));
		MvcResult result = mockMvc.perform(request).andExpect(status().isNotFound()).andReturn();
		verify(service).sendEmailsByCSV(invalidBatchId, surveyId, emailFile);

		// then
		assertEquals("null", result.getResponse().getContentAsString());

	}

	/**
	 * Checks the SendEmailByBatchIdAndCSV method with an invalid surveyId, and a
	 * valid CSV.
	 */
	@Test
	void distributionControllerSendEmailsByCsv_invalidSurveyId() throws Exception {

		// input parameters
		final int validBatchId = 2010;
		final int invalidSurveyId = 404; // does not exist
		MockMultipartFile emailFile = new MockMultipartFile("csv", "emails.csv", "text/plain",
				"acacia.holliday@revature.net, ksenia.milstein@revature.net, zach.leonardo@revature.net".getBytes());

		// dependency
		Mockito.when(service.sendEmailsByCSV(validBatchId, invalidSurveyId, emailFile))
				.thenThrow(InvalidSurveyIdException.class);

		// when
		RequestBuilder request = MockMvcRequestBuilders.multipart("/distribute/" + invalidSurveyId).file(emailFile)
				.param("batchId", Integer.toString(validBatchId));
		MvcResult result = mockMvc.perform(request).andExpect(status().isNotFound()).andReturn();
		verify(service).sendEmailsByCSV(validBatchId, invalidSurveyId, emailFile);

		// then
		assertEquals("null", result.getResponse().getContentAsString());

	}

	/**
	 * Checks the SendEmailByBatchIdAndCSV method with an invalid surveyId and
	 * invalid batchId, and a valid CSV.
	 */
	@Test
	void distributionControllerSendEmailsCSV_invalidSurveyIdAndBatchId() throws Exception {

		// input parameters
		final int invalidBatchId = 3010; // does not exist
		final int invalidSurveyId = 404; // does not exist
		MockMultipartFile emailFile = new MockMultipartFile("csv", "emails.csv", "text/plain",
				"acacia.holliday@revature.net, ksenia.milstein@revature.net, zach.leonardo@revature.net".getBytes());

		// dependency
		Mockito.when(service.sendEmailsByCSV(invalidBatchId, invalidSurveyId, emailFile))
				.thenThrow(InvalidBatchIdException.class);

		// when
		RequestBuilder request = MockMvcRequestBuilders.multipart("/distribute/" + invalidSurveyId).file(emailFile)
				.param("batchId", Integer.toString(invalidBatchId));
		MvcResult result = mockMvc.perform(request).andExpect(status().isNotFound()).andReturn();
		verify(service).sendEmailsByCSV(invalidBatchId, invalidSurveyId, emailFile);

		// then
		assertEquals("null", result.getResponse().getContentAsString());

	}

	/**
	 * Checks the SendEmailByBatchIdAndCSV method with a valid batchId, and a CSV
	 * containing email with an invalid format.
	 */
	@Test
	void distributionControllerSendEmailsByCSV_withInvalidEmail() throws Exception {

		// input parameters
		final int validBatchId = 3010;
		final int validSurveyId = 404;
		MockMultipartFile emailFile = new MockMultipartFile("csv", "emails.csv", "text/plain",
				"acacia.hollidayrevature.net, ksenia.milstein@revature.net, zach.leonardo@revature.net".getBytes());

		// return
		final Set<String> malformedEmails = Sets.newHashSet("acacia.hollidayrevature.net");
		final Set<String> tokenFailedEmails = Sets.newHashSet();
		final Set<String> sendFailedEmails = Sets.newHashSet();
		final String statusMessage = "";
		EmailResponse response = new EmailResponse(malformedEmails, tokenFailedEmails, sendFailedEmails, statusMessage);

		// dependency
		Mockito.when(service.sendEmailsByCSV(validBatchId, validSurveyId, emailFile)).thenReturn(response);

		// when
		RequestBuilder request = MockMvcRequestBuilders.multipart("/distribute/" + validSurveyId).file(emailFile)
				.param("batchId", Integer.toString(validBatchId));
		MvcResult result = mockMvc.perform(request).andExpect(status().isBadRequest()).andReturn();
		verify(service).sendEmailsByCSV(validBatchId, validSurveyId, emailFile);

		// then
		assertEquals(
				"{\"malformedEmails\":[\"acacia.hollidayrevature.net\"],\"tokenFailedEmails\":[],\"sendFailedEmails\":[],\"statusMessage\":\"\"}",
				result.getResponse().getContentAsString());

	}

	/**
	 * Checks the SendEmailByBatchIdAndCSV method with a valid batchId, and a CSV
	 * containing malformatted emails.
	 */
	@Test
	void distributionControllerSendEmailsByCSV_withMultipleInvalidEmails() throws Exception {

		// input parameters
		final int validBatchId = 3010;
		final int validSurveyId = 404;
		MockMultipartFile emailFile = new MockMultipartFile("csv", "emails.csv", "text/plain",
				"acacia.hollidayrevature.net, ksenia.milstein@revature.net, zach.leonardo@revature.net".getBytes());

		// return
		final Set<String> malformedEmails = Sets.newHashSet("acacia.hollidayrevature.net",
				"ksenia.milstein@revature.net", "zach.leonardo@revature.net");
		final Set<String> tokenFailedEmails = Sets.newHashSet();
		final Set<String> sendFailedEmails = Sets.newHashSet();
		final String statusMessage = "";
		EmailResponse response = new EmailResponse(malformedEmails, tokenFailedEmails, sendFailedEmails, statusMessage);

		// dependency
		Mockito.when(service.sendEmailsByCSV(validBatchId, validSurveyId, emailFile)).thenReturn(response);

		// when
		RequestBuilder request = MockMvcRequestBuilders.multipart("/distribute/" + validSurveyId).file(emailFile)
				.param("batchId", Integer.toString(validBatchId));
		MvcResult result = mockMvc.perform(request).andExpect(status().isBadRequest()).andReturn();
		verify(service).sendEmailsByCSV(validBatchId, validSurveyId, emailFile);

		// then
		assertEquals("{\"malformedEmails\":[\"acacia.hollidayrevature.net\",\"ksenia.milstein@revature.net\","
				+ "\"zach.leonardo@revature.net\"],\"tokenFailedEmails\":[],\"sendFailedEmails\":[],\"statusMessage\":\"\"}",
				result.getResponse().getContentAsString());
	}

	/**
	 * Checks the SendEmailByCSV method with a non existing CSV throws an Exception.
	 */
	@Test
	void distributionControllerSendEmailsByBatchId_withCSVNotFound() throws Exception {

		// input parameters
		final int validBatchId = 3010;
		final int validSurveyId = 404;
		MockMultipartFile emailFile = new MockMultipartFile("csv", "non-existing-file.csv", "text/plain",
				"".getBytes());

		// return
		final Set<String> malformedEmails = Sets.newHashSet();
		final Set<String> tokenFailedEmails = Sets.newHashSet();
		final Set<String> sendFailedEmails = Sets.newHashSet();
		final String statusMessage = "File not Found";
		EmailResponse response = new EmailResponse(malformedEmails, tokenFailedEmails, sendFailedEmails, statusMessage);

		Mockito.when(service.sendEmailsByCSV(validBatchId, validSurveyId, emailFile)).thenReturn(response);

		// when
		RequestBuilder request = MockMvcRequestBuilders.multipart("/distribute/" + validSurveyId).file(emailFile)
				.param("batchId", Integer.toString(validBatchId));
		MvcResult result = mockMvc.perform(request).andExpect(status().isInternalServerError()).andReturn();
		verify(service).sendEmailsByCSV(validBatchId, validSurveyId, emailFile);

		// then
		assertEquals(
				"{\"malformedEmails\":[],\"tokenFailedEmails\":[],\"sendFailedEmails\":[],\"statusMessage\":\"File not Found\"}",
				result.getResponse().getContentAsString());
	}

	/**
	 * Checks the SendEmailByCSV method with a CSV with no content throws an
	 * Exception.
	 */
	@Test
	void distributionControllerSendEmailsByBatchId_withEmptyCSV() throws Exception {

		// input parameters
		final int validBatchId = 3010;
		final int validSurveyId = 404;
		MockMultipartFile csvFile = new MockMultipartFile("csv", "email.csv", "text/plain", "".getBytes());

		Mockito.when(service.sendEmailsByCSV(validBatchId, validSurveyId, csvFile))
				.thenThrow(IllegalArgumentException.class);

		// when
		RequestBuilder request = MockMvcRequestBuilders.multipart("/distribute/" + validSurveyId).file(csvFile)
				.param("batchId", Integer.toString(validBatchId));
		MvcResult result = mockMvc.perform(request).andExpect(status().isNotFound()).andReturn();
		verify(service).sendEmailsByCSV(validBatchId, validSurveyId, csvFile);

		// then
		assertEquals("null", result.getResponse().getContentAsString());
	}

	/**
	 * Checks the SendEmailByBatchId method one email that failed to send
	 * 
	 * @throws Exception
	 * @throws MessagingException
	 * 
	 */
	@Test
	void distributionControllerSendEmailsByCsv_withOneMessagingException() throws Exception {

		// input parameters
		final int validBatchId = 2010;
		final int surveyId = 100;
		MockMultipartFile csvFile = new MockMultipartFile("csv", "emails.csv", "text/plain",
				"acacia.holliday@revature.net, ksenia.milstein@revature.net, zach.leonardo@revature.net".getBytes());

		// return
		final Set<String> malformedEmails = Sets.newHashSet();
		final Set<String> tokenFailedEmails = Sets.newHashSet();
		final Set<String> sendFailedEmails = Sets.newHashSet("acacia.holliday@revature.net",
				"ksenia.milstein@revature.net", "zach.leonardo@revature.net");
		final String statusMessage = "";
		EmailResponse response = new EmailResponse(malformedEmails, tokenFailedEmails, sendFailedEmails, statusMessage);

		// dependency
		Mockito.when(service.sendEmailsByCSV(validBatchId, surveyId, csvFile)).thenReturn(response);

		// when
		RequestBuilder request = MockMvcRequestBuilders.multipart("/distribute/" + surveyId).file(csvFile)
				.param("batchId", Integer.toString(validBatchId));
		MvcResult result = mockMvc.perform(request).andExpect(status().isOk()).andReturn();
		verify(service).sendEmailsByCSV(validBatchId, surveyId, csvFile);

		// then
		assertEquals(
				"{\"malformedEmails\":[],\"tokenFailedEmails\":[],\"sendFailedEmails\":[\"acacia.holliday@revature.net\","
						+ "\"ksenia.milstein@revature.net\",\"zach.leonardo@revature.net\"],\"statusMessage\":\"\"}",
				result.getResponse().getContentAsString());

	}

	/**
	 * Checks the SendEmailByBatchId method with multiple email that failed to send
	 */
	@Test
	void distributionControllerSendEmailsByCSV_withMultipleMessagingException() throws Exception {

		// input parameters
		final int validBatchId = 2010;
		final int surveyId = 100;
		MockMultipartFile csvFile = new MockMultipartFile("csv", "emails.csv", "text/plain",
				"acacia.holliday@revature.net, ksenia.milstein@revature.net, zach.leonardo@revature.net".getBytes());

		// return
		final Set<String> malformedEmails = Sets.newHashSet();
		final Set<String> tokenFailedEmails = Sets.newHashSet();
		final Set<String> sendFailedEmails = Sets.newHashSet("acacia.holliday@revature.net");
		final String statusMessage = "";
		EmailResponse response = new EmailResponse(malformedEmails, tokenFailedEmails, sendFailedEmails, statusMessage);

		// dependency
		Mockito.when(service.sendEmailsByCSV(validBatchId, surveyId, csvFile)).thenReturn(response);

		// when
		RequestBuilder request = MockMvcRequestBuilders.multipart("/distribute/" + surveyId).file(csvFile)
				.param("batchId", Integer.toString(validBatchId));
		MvcResult result = mockMvc.perform(request).andExpect(status().isOk()).andReturn();
		verify(service).sendEmailsByCSV(validBatchId, surveyId, csvFile);

		// then
		assertEquals(
				"{\"malformedEmails\":[],\"tokenFailedEmails\":[],\"sendFailedEmails\":[\"acacia.holliday@revature.net\"],\"statusMessage\":\"\"}",
				result.getResponse().getContentAsString());

	}
}
