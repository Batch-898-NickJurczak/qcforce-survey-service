package com.revature.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * This service receives requests from the distribution controller, and then
 * validates if the emails are correctly formatted and the batch id exists. If
 * the emails are invalid then the invalid emails will be returned. If the batch
 * id does not exist then an exception is thrown.
 */

@Service
public class DistributionServiceImpl implements DistributionService {
	/**
	 * TODO: Document after creating tests
	 */
	@Override
	public List<String> sendEmailsByBatchId(int batchId) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * TODO: Document after creating tests
	 */
	@Override
	public List<String> sendEmailsByBatchIdAndCSV(int batchId, MultipartFile csv) {
		// TODO Auto-generated method stub
		return null;
	}

}
