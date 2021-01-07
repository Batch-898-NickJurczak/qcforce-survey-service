package com.revature.service;

import java.util.Set;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.revature.response.EmailResponse;
import com.revature.util.InvalidBatchIdException;
import com.revature.util.InvalidSurveyIdException;

/**
 * This service receives requests from the distribution controller, and then
 * validates if the emails are correctly formatted and the batch id exists. If
 * the emails are invalid then the invalid emails will be returned. If the batch
 * id does not exist then an exception is thrown.
 */
@Service
public class DistributionServiceImpl implements DistributionService {

	private EmailService emailService;

	private CSVParser csvParser;

	private AuthService authService;
	
	public final String baseURL = "http://qcforce.com";

	/**
	 * @param emailService the emailService to set
	 */
	@Autowired
	public void setEmailService(EmailService emailService) {
		this.emailService = emailService;
	}

	/**
	 * @param csvParser the csvParser to set
	 */
	@Autowired
	public void setCsvParser(CSVParser csvParser) {
		this.csvParser = csvParser;
	}

	/**
	 * @param authService the authService to set
	 */
	@Autowired
	public void setAuthService(AuthService authService) {
		this.authService = authService;
	}

	/**
	 * Sends emails by batch Id. Takes survey id calls email service with batch Id emails 
	 * received from messaging queue
	 * and calls auth service with surveyId.
	 * 
	 * @param batchId - identifies the batch to recieve emails from
	 * @param surveyId - identifies the survey to send to batch emails
	 * @return 
	 * 
	 */
	@Override
	public EmailResponse sendEmailsByBatchId(String batchId, int surveyId) {
		
		// get list of emails
		
		return null;
	}
	
	/**
	 * Distributes survey links to specified emails within the given csv file.
	 * 
	 * @param batchId  the identifier for the desired batch.
	 * @param surveyId the identifier for the survey to distribute.
	 * @param csv      the file containing associate emails.
	 * @return
	 * @throws MessagingException 
	 * 
	 */
	@Override
	public EmailResponse sendEmailsByCSV(String batchId, int surveyId, MultipartFile csv) throws 
	InvalidSurveyIdException, InvalidBatchIdException, IllegalArgumentException{
		
		// parse list of emails out of csv file
		
		// validate that batchId is valid
		// validate that surveyId is valid
		
		// return sendEmail method call
		return null;
	}
	
	/**
	 * Distributes survey links to specified emails within the given email Set.
	 * 
	 * @param batchId
	 * @param surveyId
	 * @param emails
	 * @return
	 */
	private EmailResponse sendEmailHelper(String batchId, int surveyId, Set<String> emails) {
		
		// call associate finder(Our service) to get id for that associate from this endpoint: /batch-id/{batchId}
		// We'll have a hashmap of all emails with associate Ids as keys
		// if this returns nothing, it was a bad batch Id so return with 404 more or less
		
		// validate list of emails. Flag if one is malformatted but still check all.
	
		//Start loop for each email
		
			// check if emails are in hashmap and pull out key
			
			// make post api call to syncService "/surveysub" with surveyId and associateId and get surveySubmission back
			// if returns nothing, the surveyId was bad, return with 404 more or less
		
			// generate token using batchId, surveyId, and serveySubId; add failed emails into response in tokenFailed
		
			// Create url for each email and send; add failed sending emails to response in sendFailed
		
		// return email response

		return null;
	}

}
