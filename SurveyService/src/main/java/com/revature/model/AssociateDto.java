package com.revature.model;

public class AssociateDto {

	private int associateId;
	
	private String email;

	/**
	 * 
	 */
	public AssociateDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param associateId
	 * @param email
	 */
	public AssociateDto(int associateId, String email) {
		super();
		this.associateId = associateId;
		this.email = email;
	}

	/**
	 * @return the associateId
	 */
	public int getAssociateId() {
		return associateId;
	}

	/**
	 * @param associateId the associateId to set
	 */
	public void setAssociateId(int associateId) {
		this.associateId = associateId;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + associateId;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
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
		AssociateDto other = (AssociateDto) obj;
		if (associateId != other.associateId)
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AssociateDto [associateId=" + associateId + ", email=" + email + "]";
	}
	
}
