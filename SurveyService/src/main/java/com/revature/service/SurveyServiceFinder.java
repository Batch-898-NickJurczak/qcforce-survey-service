/**
 * 
 */
package com.revature.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @author Michael M, Chris B
 *
 */
@Service
public class SurveyServiceFinder implements SurveyService {

	private static final String SURVEY_PATH = "/survey/";
	
	private WebClient webClient;
	
	@Autowired
	public void setWebClient(WebClient webClient) {
		this.webClient = webClient;
	}

	@Override
	public boolean isValidSurvey(int surveyId) {

		if(this.webClient.get().uri(SURVEY_PATH + surveyId).exchange()
				.map(response -> response.statusCode())
				.block() == HttpStatus.OK) {
			return true;
		}
		return false;
	}

}
