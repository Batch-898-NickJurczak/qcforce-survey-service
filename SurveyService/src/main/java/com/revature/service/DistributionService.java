package com.revature.service;

import java.util.List;
import java.util.Set;

import javax.mail.MessagingException;

import org.springframework.web.multipart.MultipartFile;

import com.revature.response.EmailResponse;

import org.springframework.web.multipart.MultipartFile;

public interface DistributionService {

	public EmailResponse sendEmailsByBatchId(String batchId, int surveyId);
	
	public EmailResponse sendEmailsByCSV(String batchId, int surveyId, MultipartFile csv);
	
}
