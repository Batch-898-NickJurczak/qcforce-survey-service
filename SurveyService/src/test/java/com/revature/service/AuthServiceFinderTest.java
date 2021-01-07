/**
 * 
 */
package com.revature.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;

import com.revature.dto.TokenDto;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import reactor.core.publisher.Mono;

/**
 * @author Work From Home
 *
 */
class AuthServiceFinderTest {
	
	private AuthService authService;
	
	public static MockWebServer mockBackEnd;
	
	private String token;

	private String batchId;
	
	private int surveyId;
	
	private int surveySubId;
	
	/**
	 * @param authService the authService to set
	 */
	public void setAuthService(AuthServiceFinder authService) {
		this.authService = authService;
	}

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
		this.surveySubId = 2;
		this.batchId = "2010";
		
		this.token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdXJ2ZXlJZCI6IjEyMzQ1Njc4OTAiLCJiYXRjaElkIjoiMjAxMCIsImV4cCI6MjAsImlhdCI6MjB9.rpejfxJ1pM5bZm74bpuHh92vIdqfMkwDHATLGiY35qs";
		
		String baseUrl = String.format("http://localhost:%s", mockBackEnd.getPort());
		
		authService = new AuthServiceFinder(baseUrl);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
	}

	/**
	 * Checks if createToken() returns expected token when given valid 
	 * surveyId, surveySubId, and batchId.
	 */
	@Test
	void createTokenTest_withValidParams() {
		mockBackEnd.enqueue(new MockResponse().setBody(token));
		String returned = authService.createToken(surveyId, batchId, surveySubId);
		assertEquals(token, returned, "Token " + token + " did not line up with returned " + returned);
	}
}
