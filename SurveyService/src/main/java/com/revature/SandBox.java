package com.revature;

import java.util.Set;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Sets;
import com.revature.response.EmailResponse;

public class SandBox {
	public static void main(String[] args) throws JsonProcessingException {
		final Set<String> malformedEmails = Sets.newHashSet();
		final Set<String> tokenFailedEmails = Sets.newHashSet();
		final Set<String> sendFailedEmails = Sets.newHashSet("acacia.holliday@revature.net");
		final String statusMessage = "";
		EmailResponse response = new EmailResponse(malformedEmails, tokenFailedEmails, sendFailedEmails, statusMessage);
		//EmailResponse response = null;
		
		ObjectMapper om = new ObjectMapper();
		
		String json = om.writeValueAsString(response);
		
		System.out.println(json);
	}
}
