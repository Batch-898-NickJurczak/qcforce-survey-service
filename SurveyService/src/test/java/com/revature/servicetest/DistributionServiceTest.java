package com.revature.servicetest;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;

import com.revature.service.AuthService;
import com.revature.service.CSVParser;
import com.revature.service.DistributionService;
import com.revature.service.DistributionServiceImpl;
import com.revature.service.EmailService;

class DistributionServiceTest {

	@Autowired
	@InjectMocks
	DistributionServiceImpl distributionService;

	@MockBean
	AuthService authService;

	@MockBean
	CSVParser csvParser;

	@MockBean
	EmailService emailService;

	int batchId;

	int surveyId;

	String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdXJ2ZXlJZCI6IjEyMzQ1Njc4OTAiLCJiYXRjaElkIjoiMjAxMCIsImV4cCI6MjAsImlhdCI6MjB9.rpejfxJ1pM5bZm74bpuHh92vIdqfMkwDHATLGiY35qs";

	String url = "http://qcforce.com/survey?token=";

	List<String> emails;

	MockMultipartFile csv;

	
	@SuppressWarnings("serial")
	@BeforeEach
	void setUp() throws Exception {
		
		MockitoAnnotations.initMocks(this);
		batchId = 2010;
		surveyId = 1;
		emails = new ArrayList<String>() {
			{
				add("acacia.holliday@revature.net");
				add("ksenia.milstein@revature.net");
				add("zach.leonardo@revature.net");
			}
		};

		csv = new MockMultipartFile("data", "emails.csv", "text/plain",
				"acacia.holliday@revature.net,ksenia.milstein@revature.net,zach.leonardo@revature.net".getBytes());

		when(csvParser.parseFileForEmails(csv)).thenReturn(emails);
		when(authService.createToken(surveyId, batchId)).thenReturn(token);
		when(emailService.sendEmails(url + token, emails)).thenReturn(new ArrayList<String>());
	}

	@Test
	void sendEmailsByBatchIdAndCSV_withValidParameters() {

		try {

			List<String> failedEmails = distributionService.sendEmailsByBatchIdAndCSV(batchId, surveyId, csv);

			verify(csvParser).parseFileForEmails(csv);
			verify(authService).createToken(surveyId, batchId);
			verify(emailService).sendEmails(url + token, emails);

			assertTrue(failedEmails.size() == 0);

		} catch (Exception e) {
			fail("Exception was thrown " + e);
		}

		// parseFileForEmails(file)
		// createToken(surveyId, batchId)
		// sendEmails(msg, destinations)
	}

	// happy path
	// invalid batchId
	// invalid surveyID
	// invalid csv
}
