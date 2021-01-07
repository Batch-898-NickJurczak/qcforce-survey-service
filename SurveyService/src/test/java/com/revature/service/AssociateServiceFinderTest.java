/**
 * 
 */
package com.revature.service;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Work From Home
 *
 */
class AssociateServiceFinderTest {
	
	private AssociateService associateService;

	/**
	 * @param associateService the associateService to set
	 */
	public void setAssociateService(AssociateServiceFinder associateService) {
		this.associateService = associateService;
	}

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
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void associateServiceTest() {
		associateService.getAssociatesByBatchId("2010");
	}

}
