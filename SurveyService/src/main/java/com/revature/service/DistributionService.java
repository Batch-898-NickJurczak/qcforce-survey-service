package com.revature.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface DistributionService {
	
	public List<String> sendEmailsByBatchId(int batchId);
	
	public List<String> sendEmailsByBatchIdAndCSV(int batchId, MultipartFile csv);

}
