package com.revature.servicetest;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.IllegalFormatException;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.revature.service.CSVParser;

@SpringBootTest
class CSVParserTest {
	@Autowired
	CSVParser csvParser;

	/**
	 * Tests CSVParser service, creates a valid CSV and inputs it as parameter,
	 * asserts that it does not throw an exception
	 */
	@Test
	void testCSVParser_withValidCSVFormattedFile() {
		List<String> testEmails = new ArrayList<>(Arrays.asList("acacia.holliday@revature.net",
				"ksenia.milstein@revature.net", "zach.leonardo@revature.net"));
		MockMultipartFile emailFile = new MockMultipartFile("data", "nonexistingfile.csv", "text/plain",
				"acacia.holliday@revature.net,ksenia.milstein@revature.net,zach.leonardo@revature.net".getBytes());
		List<String> actualEmails;
		try {
			actualEmails = csvParser.parseFileForEmails(emailFile);
			assertEquals(testEmails, actualEmails);
		} catch (IOException e) {
			fail("IOException thrown " + e);
		}

	}

	/**
	 * Tests CSVParser service with invalid CSV file, should throw a
	 * FileNotFoundException.
	 * 
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	@Test
	void testCVSParser_withInvalidCSV() throws FileNotFoundException, IOException {
		assertThrows(FileNotFoundException.class,
				() -> csvParser.parseFileForEmails((MultipartFile) new FileInputStream(new File("emails.csv"))));
	}

	/**
	 * Tests that CVSParser service throws an IllegalFormatException when receiving
	 * a file without any data
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 * 
	 */
	@Test
	void testCVSParser_withEmptyFile() throws FileNotFoundException, IOException {
		MockMultipartFile cvsFile = new MockMultipartFile("data", "email.csv", "text/plain", "".getBytes());
		assertThrows(IllegalArgumentException.class, () -> csvParser.parseFileForEmails(cvsFile));
	}

}
