/**
 * 
 */
package com.revature.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @author micha
 *
 */
@SpringBootTest
class SurveyServiceFinderTest {

	private SurveyServiceFinder surveyService;
	
	@MockBean
	private WebClient webClient;
	
	@Autowired
	public void setSurveyService(SurveyServiceFinder surveyService) {
		this.surveyService = surveyService;
	}

	private int surveyId;
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		
		this.surveyId = 1;
		
		surveyService.setWebClient(webClient);
		
		when(webClient.get().uri("/survey/" + surveyId).exchange()
				.map(response -> response.statusCode())
				.block()).thenReturn(HttpStatus.OK);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void isVaildSurveyTest() {
		assertTrue(surveyService.isValidSurvey(surveyId), "Should return true if satus code is OK.");
	}

}
