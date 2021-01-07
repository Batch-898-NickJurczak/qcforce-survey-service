package com.revature.service;

public interface AuthService {
	
	public String createToken(int surveyId, int batchId, int surveySubId);
		
}
