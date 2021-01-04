package com.revature.service;

public interface SurveyService {
	
	/**
	 * Evaluates if survey Id sent in is a valid survey Id by calling the 
	 * sync service and checking if it returns content for a survey.
	 * 
	 * If that endpoints returns null we know that survey Id is not valid 
	 * as it does not relate to a real survey.
	 * 
	 * @param surveyId
	 * @return boolean
	 */
	public boolean isValidSurvey(int surveyId);

}
