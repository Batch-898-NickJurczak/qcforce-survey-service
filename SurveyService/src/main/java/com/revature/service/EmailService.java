package com.revature.service;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

public interface EmailService {
	
	public void sendEmail(String msg,String destination) throws AddressException, MessagingException;

}
