package com.revature.service;

import org.springframework.web.multipart.MultipartFile;

import com.google.common.collect.Sets;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Set;

import org.springframework.stereotype.Service;

/**
 * This service is responsible for parsing a given CSV as a MultipartFile, and
 * returning a List<String> containing email addresses within the file.
 */
@Service
public class CSVParserImpl implements CSVParser {

	/**
	 * This method takes in a MultipartFile containing emails separated by either a
	 * comma and/or a space. It then returns a set containing each email as a
	 * string.
	 * 
	 * @param file the CSV to parse as a MultipartFile
	 * @throws IOException
	 * @throws FileNotFoundException
	 * @throws IllegalArgumentException
	 */
	@Override
	public Set<String> parseFileForEmails(MultipartFile file)
			throws IOException, FileNotFoundException, IllegalArgumentException {

		if (file.isEmpty()) {
			throw new IllegalArgumentException("Cannot parse empty file");
		}
		return Sets.newHashSet(new String(file.getBytes()).replaceAll("[\" ]", "").split("[,\\ ]"));
	}

}
