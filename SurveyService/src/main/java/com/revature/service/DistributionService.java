package com.revature.service;

import java.io.File;
import java.util.List;
import java.util.Set;

import org.springframework.web.multipart.MultipartFile;

import com.revature.response.EmailResponse;

public interface DistributionService {

	public EmailResponse sendEmailsByBatchId(int batchId, int surveyId);
	
	public EmailResponse sendEmailsByCSV(int batchId, int surveyId, MultipartFile csv);
	
}
