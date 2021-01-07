package com.revature.service;

import org.springframework.web.multipart.MultipartFile;

import com.revature.response.EmailResponse;

public interface DistributionService {

	public EmailResponse sendEmailsByBatchId(String batchId, int surveyId);
	
	public EmailResponse sendEmailsByCSV(String batchId, int surveyId, MultipartFile csv);
	
}
