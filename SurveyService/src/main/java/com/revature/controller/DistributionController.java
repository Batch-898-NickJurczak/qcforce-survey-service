package com.revature.controller;

import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.response.EmailResponse;
import com.revature.service.DistributionService;
import com.revature.util.InvalidBatchIdException;
import com.revature.util.InvalidSurveyIdException;

/**
 * This controller has three endpoints, one takes in a batch id, another takes
 * in a batch id with a set of emails, and the last takes in a batch id with a
 * .CSV file of emails. If there are no errors the emails are sent to
 * associates.
 * 
 * @author Acacia Holliday, Ksenia Milstein, Marc Roy, Zach Leonardo
 */
@RestController
public class DistributionController {

	private DistributionService distributionService;

	@Autowired
	public void setDistributionService(DistributionService distributionService) {
		this.distributionService = distributionService;
	}

	/**
	 * The method will send an email to associates that are in the database for the
	 * batchId provided.
	 * 
	 * @param batchId represents a batch identifier
	 * @return List of incorrectly formatted emails in the database if any
	 * @throws JsonProcessingException 
	 */
	@PostMapping("/distribute/{surveyId}/{batchId}")
	private ResponseEntity<String> sendEmailsByBatchId(@PathVariable int surveyId, @PathVariable int batchId) throws JsonProcessingException {

		EmailResponse response;
		ObjectMapper om = new ObjectMapper();
		String json;

		try {
			response = distributionService.sendEmailsByBatchId(batchId, surveyId);
			json = om.writeValueAsString(response);
		} catch (InvalidBatchIdException | InvalidSurveyIdException | IllegalArgumentException e) {
			json = om.writeValueAsString(null);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(json);
		}

		if (!response.getMalformedEmails().isEmpty()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(json);
		} else if (!response.getStatusMessage().isEmpty()) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(json);
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(json);
		}
	}

	/**
	 * The method will send an email to associates that are provided in the CSV
	 * file.
	 * 
	 * @param batchId  represents a batch identifier
	 * @param surveyId represents a survey that will be filled out by associates
	 * @param csv      represents a csv file of emails
	 * @return List of incorrectly formatted emails in the database if any
	 * @throws JsonProcessingException 
	 * @throws MessagingException 
	 */
	@PostMapping("/distribute/{surveyId}")
	@ResponseBody
	private ResponseEntity<String> sendEmailsByCSV(@PathVariable int surveyId, @RequestParam int batchId,
			@RequestParam MultipartFile csv) throws JsonProcessingException {

		EmailResponse response;
		ObjectMapper om = new ObjectMapper();
		String json;

		try {
			response = distributionService.sendEmailsByCSV(batchId, surveyId, csv);
			json = om.writeValueAsString(response);
		} catch (InvalidBatchIdException | InvalidSurveyIdException | IllegalArgumentException e) {
			json = om.writeValueAsString(null);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(json);
		}

		if (!response.getMalformedEmails().isEmpty()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(json);
		} else if (!response.getStatusMessage().isEmpty()) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(json);
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(json);
		}
	}
}
