package com.revature.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.client.WebClientException;

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
	
	private AssociateService associateService;
	
	private SurveyService surveyService;
	
	private SurveySubmissionService surveySubmissionService;
	
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
	public void setAuthService(AuthServiceFinder authService) {
		this.authService = authService;
	}
	

	/**
	 * @param associateService the associateService to set
	 */
	@Autowired
	public void setAssociateService(AssociateService associateService) {
		this.associateService = associateService;
	}

	/**
	 * @param surveyService the surveyService to set
	 */
	@Autowired
	public void setSurveyService(SurveyService surveyService) {
		this.surveyService = surveyService;
	}
	
	

	/**
	 * @param surveySubmissionService the surveySubmissionService to set
	 */
	@Autowired
	public void setSurveySubmissionService(SurveySubmissionService surveySubmissionService) {
		this.surveySubmissionService = surveySubmissionService;
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
		
		// call getAssociatesByBatchId
		// if this returns nothing, it was a bad batch Id so throw an InvalidBatchIdException
		// We'll have a hashmap of all emails with associate Ids as keys
		HashMap<String, Integer> associateEmailIdMap = associateService.getAssociatesByBatchId(batchId);
		if(associateEmailIdMap.isEmpty()) {
			throw new InvalidBatchIdException("Invalid BatchId: " + batchId);
		}
		
		// validate that surveyId is valid
		if(!surveyService.isValidSurvey(surveyId)) {
			throw new InvalidSurveyIdException("Invalid SurveyId: " + surveyId);
		}

		// parse list of emails out of csv file
		Set<String> emails = null;
		try {
			emails = csvParser.parseFileForEmails(csv);
		} catch (IOException e) {
			throw new IllegalArgumentException("Invalid csv: Invalid Content");
		}
		
		// return sendEmail method call
		return sendEmailHelper(batchId, surveyId, emails, associateEmailIdMap);
	}
	
	/**
	 * Distributes survey links to specified emails within the given email Set.
	 * 
	 * @param batchId
	 * @param surveyId
	 * @param emails
	 * @return
	 */
	private EmailResponse sendEmailHelper(String batchId, int surveyId, Set<String> emails, HashMap<String, Integer> associateEmailIdMap) {
				
		EmailResponse emailResponse = new EmailResponse(); 
		
		// validate list of emails. Add to emailResponse if one is malformatted but still check all others.
		for(String email : emails) {
			if(!emailService.isValidEmailAddress(email)) {
				emailResponse.addMalformedEmail(email);
			}
		}
		
		// if any emails were invalid, return with the list of malformatted emails
		if(!emailResponse.getMalformedEmails().isEmpty()) {
			return emailResponse;
		}
	
		//Start loop for each email
		for(String email : emails) {
		
			// check if emails are in hashmap and pull out key
			Integer associateId = associateEmailIdMap.get(email);
			// if there is no email in batch that matches, add to token fail and continue
			if(associateId == null) {
				emailResponse.addTokenFailedEmail(email);
				continue;
			}
			
			// make post api call to syncService "/surveysub" with surveyId and associateId and get surveySubmission back
			Integer surveySubId = surveySubmissionService.getSurveySubmissionByAssociateId(batchId, surveyId, associateId);
			
			// generate token using batchId, surveyId, and serveySubId; add failed emails into response in tokenFailed
			String authToken = null;
			try {
				authToken = authService.createToken(surveyId, batchId, surveySubId);
			} catch (WebClientException e) {
				e.printStackTrace();
				emailResponse.addTokenFailedEmail(email);
				continue;
			}
			
			// Create url for each email and send; add failed sending emails to response in sendFailed
			String surveyUrl = baseURL + "/survey?token=" + authToken;
			try {
				emailService.sendEmail(surveyUrl, email);
			} catch (Exception e) {
				emailResponse.addsendFailedEmail(email);
				e.printStackTrace();
			}
		}
    
		// return email response
		return emailResponse;

	}

}
