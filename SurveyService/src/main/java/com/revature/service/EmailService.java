package com.revature.service;

import java.util.List;
import java.util.Set;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

public interface EmailService {
	
	public void sendEmail(String msg,String destination) throws AddressException, MessagingException;

	public Set<String> sendEmails(String msg, Set<String> emails);
	
	public boolean isValidEmailAddress(String email);
	
	public void validateEmail(String destination) throws AddressException;
	
}
