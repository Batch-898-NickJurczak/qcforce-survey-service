/**
 * 
 */
package com.revature.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

/**
 * @author Michael M, Chris B
 *
 */
@SpringBootTest
class SurveyServiceFinderTest {

	private SurveyService surveyService;
	
	public static MockWebServer mockBackEnd;
	
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
		mockBackEnd = new MockWebServer();
        mockBackEnd.start();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterAll
	static void tearDownAfterClass() throws Exception {
		mockBackEnd.shutdown();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		
		this.surveyId = 1;
		
		String baseUrl = String.format("http://localhost:%s", mockBackEnd.getPort());
		
		surveyService = new SurveyServiceFinder(baseUrl);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void isVaildSurveyTest_vaildPath() {
		mockBackEnd.enqueue(new MockResponse().setResponseCode(HttpStatus.OK.value()));
		assertTrue(surveyService.isValidSurvey(surveyId), "Should return true if status code is OK.");
	}
	
	@Test
	void isVaildSurveyTest_invaildPath() {
		mockBackEnd.enqueue(new MockResponse().setResponseCode(HttpStatus.NOT_FOUND.value()));
		assertFalse(surveyService.isValidSurvey(surveyId), "Should return false if status code is NOT FOUND.");
	}
	
}
