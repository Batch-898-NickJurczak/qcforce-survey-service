package com.revature.service;

public interface AuthService {
  
	public String createToken(int surveyId, String batchId, int surveySubId);
	
}
