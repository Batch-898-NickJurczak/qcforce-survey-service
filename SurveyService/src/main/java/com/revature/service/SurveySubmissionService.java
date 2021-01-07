package com.revature.service;

public interface SurveySubmissionService {
	
	/**
	 * Gets a survey submission Id based on the batch Id, survey Id, and associate Id. 
	 * If any invalid params are inputed, this function will return with a null value.
	 * 
	 * @param batchId
	 * @param surveyId
	 * @param associateId
	 * @return surveySubId
	 */
	public int getSurveySubmissionByAssociateId(String batchId, int surveyId, int associateId);

}
