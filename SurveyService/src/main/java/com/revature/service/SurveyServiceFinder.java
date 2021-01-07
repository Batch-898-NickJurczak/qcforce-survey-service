/**
 * 
 */
package com.revature.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * This service is used to reach the '/survey' endpoint of the sync-service
 * microservice to retrieve survey objects.
 * 
 * @author Michael M, Chris B
 */
@Service
public class SurveyServiceFinder implements SurveyService {

	private static final String SURVEY_PATH = "/survey";
	private final String BASE_URL;

	private WebClient webClient;

	public SurveyServiceFinder(@Value("baseUrl") String baseUrl) {
		this.BASE_URL = baseUrl; 
	}

	/**
	 * Checks '/survey/{id}' endpoint in sync-service microservice and returns a
	 * boolean value if the resource exists or not. Method can catch a
	 * {@link NullPointerException} which will return a boolean value of false.
	 * 
	 * @param surveyId int - the ID of the requested survey object
	 * @return boolean if resource exists
	 */
	@Override
	public boolean isValidSurvey(int surveyId) {
		
		this.webClient = WebClient.create(BASE_URL);
		
		try {
			if (this.webClient.get().uri(SURVEY_PATH + "/{id}", surveyId).exchange()
					.block().statusCode().is2xxSuccessful()) {
				return true;
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
			return false;
		}

		return false;
	}

}