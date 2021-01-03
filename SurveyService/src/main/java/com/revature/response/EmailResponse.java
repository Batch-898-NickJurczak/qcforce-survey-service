package com.revature.response;

import java.util.Set;

/**
 * 
 * @author Chris, Ksenia, Michael M, Yarashlee
 *
 */

public class EmailResponse {
	
	private Set<String> malformedEmails;
	private Set<String> tokenFailedEmails;
	private Set<String> sendFailedEmails;
	private String statusMessage;
	
	/**
	 * 
	 */
	public EmailResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @param malformedEmails
	 * @param tokenFailedEmails
	 * @param sendFailedEmails
	 */
	
	public EmailResponse(Set<String> malformedEmails, Set<String> tokenFailedEmails, Set<String> sendFailedEmails, String statusMessage) {
		super();
		this.malformedEmails = malformedEmails;
		this.tokenFailedEmails = tokenFailedEmails;
		this.sendFailedEmails = sendFailedEmails;
		this.statusMessage = statusMessage;
	}

	public Set<String> getMalformedEmails() {
		return malformedEmails;
	}

	public void setMalformedEmails(Set<String> malformedEmails) {
		this.malformedEmails = malformedEmails;
	}

	public Set<String> getTokenFailedEmails() {
		return tokenFailedEmails;
	}

	public void setTokenFailedEmails(Set<String> tokenFailedEmails) {
		this.tokenFailedEmails = tokenFailedEmails;
	}

	public Set<String> getSendFailedEmails() {
		return sendFailedEmails;
	}

	public void setSendFailedEmails(Set<String> sendFailedEmails) {
		this.sendFailedEmails = sendFailedEmails;
	}

	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((malformedEmails == null) ? 0 : malformedEmails.hashCode());
		result = prime * result + ((sendFailedEmails == null) ? 0 : sendFailedEmails.hashCode());
		result = prime * result + ((statusMessage == null) ? 0 : statusMessage.hashCode());
		result = prime * result + ((tokenFailedEmails == null) ? 0 : tokenFailedEmails.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmailResponse other = (EmailResponse) obj;
		if (malformedEmails == null) {
			if (other.malformedEmails != null)
				return false;
		} else if (!malformedEmails.equals(other.malformedEmails))
			return false;
		if (sendFailedEmails == null) {
			if (other.sendFailedEmails != null)
				return false;
		} else if (!sendFailedEmails.equals(other.sendFailedEmails))
			return false;
		if (statusMessage == null) {
			if (other.statusMessage != null)
				return false;
		} else if (!statusMessage.equals(other.statusMessage))
			return false;
		if (tokenFailedEmails == null) {
			if (other.tokenFailedEmails != null)
				return false;
		} else if (!tokenFailedEmails.equals(other.tokenFailedEmails))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "EmailResponse [malformedEmails=" + malformedEmails + ", tokenFailedEmails=" + tokenFailedEmails
				+ ", sendFailedEmails=" + sendFailedEmails + ", statusMessage=" + statusMessage + "]";
	}	
}
