package com.revature.service;

import org.springframework.stereotype.Service;

/**
 * @author Work From Home
 *
 */
@Service
public class SurveySubmissionServiceFinder implements SurveySubmissionService {
	
	/**
	 * Gets a survey submission Id based on the batch Id, survey Id, and associate Id. 
	 * If any invalid params are inputed, this function will return with a null value.
	 * 
	 * @param batchId
	 * @param surveyId
	 * @param associateId
	 * @return surveySubId : int
	 */
	@Override
	public int getSurveySubmissionByAssociateId(String batchId, int surveyId, int associateId) {
		// TODO Auto-generated method stub
		return 0;
	}

}
