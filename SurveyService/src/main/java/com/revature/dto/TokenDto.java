package com.revature.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TokenDto {
	@JsonProperty("surveyId")
	private int surveyId;
	@JsonProperty("batchId")
	private String batchId;
	@JsonProperty("surveySubId")
	private int surveySubId;

	public TokenDto() {
	}

	public TokenDto(int surveyId, String batchId, int surveySubId) {
		super();
		this.surveyId = surveyId;
		this.batchId = batchId;
		this.surveySubId = surveySubId;
	}
	
	@JsonProperty("surveyId")
	public int getSurveyId() {
		return surveyId;
	}
	
	@JsonProperty("surveyId") 
	void setSurveyId(int surveyId) {
		this.surveyId = surveyId;
	}
	@JsonProperty("batchId")
	public String getBatchId() {
		return batchId;
	}
	@JsonProperty("batchId")
	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}
	@JsonProperty("surveySubId")
	public int getSurveySubId() {
		return surveySubId;
	}
	@JsonProperty("surveySubId")
	public void setSurveySubId(int surveySubId) {
		this.surveySubId = surveySubId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((batchId == null) ? 0 : batchId.hashCode());
		result = prime * result + surveyId;
		result = prime * result + surveySubId;
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
		TokenDto other = (TokenDto) obj;
		if (batchId == null) {
			if (other.batchId != null)
				return false;
		} else if (!batchId.equals(other.batchId))
			return false;
		if (surveyId != other.surveyId)
			return false;
		if (surveySubId != other.surveySubId)
			return false;
		return true;
	}
	
	

}
