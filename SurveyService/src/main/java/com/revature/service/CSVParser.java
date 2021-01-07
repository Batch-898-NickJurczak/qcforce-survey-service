package com.revature.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.springframework.web.multipart.MultipartFile;

public interface CSVParser {

	public Set<String> parseFileForEmails(MultipartFile file) throws IOException;

}
