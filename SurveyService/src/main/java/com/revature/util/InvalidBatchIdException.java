package com.revature.util;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidBatchIdException extends RuntimeException {
	
	public InvalidBatchIdException(String message) {
		super(message);
	}
}
