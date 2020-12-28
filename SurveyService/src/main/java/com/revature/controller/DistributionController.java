package com.revature.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * This controller has three endpoints, one takes in a batch id, another takes
 * in a batch id with a set of emails, and the last takes in a batch id with a
 * .CSV file of emails. If there are no errors the emails are sent to
 * associates.
 * 
 * @author Acacia Holliday, Ksenia Milstein, Marc Roy, Zach Leonardo
 */
@RestController
public class DistributionController {
	/**
	 * The method will send an email to associates that are in the database for the
	 * batchId provided.
	 * 
	 * @param batchId represents a batch identifier
	 * @return List of incorrectly formatted emails in the database if any
	 */
	@PostMapping("/distribute/{batchId}")
	private ResponseEntity<List<String>> sendEmailsByBatchId(@PathVariable int batchId) {

		return null;
	}

	/**
	 * The method will send an email to associates that are provided in the CSV
	 * file.
	 * 
	 * @param batchId represents a batch identifier
	 * @return List of incorrectly formatted emails in the database if any
	 */
	@PostMapping("/distribute")
	private ResponseEntity<List<String>> sendEmailsByCSV(@RequestParam int batchId, @RequestParam MultipartFile csv) {
		return null;
	}
}
