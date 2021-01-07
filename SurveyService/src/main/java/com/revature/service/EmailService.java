package com.revature.service;

import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

public interface EmailService {
	
	public void sendEmail(String msg,String destination) throws AddressException, MessagingException;

	public List<String> sendEmails(String msg, List<String> emails);
	
	public void validateEmail(String destination) throws AddressException;
	
}
