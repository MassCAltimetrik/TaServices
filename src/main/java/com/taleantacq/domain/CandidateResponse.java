/**
 * 
 */
package com.taleantacq.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.taleantacq.jpa.CandidateDetails;

/**
 * @author yadak
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CandidateResponse  {

	@JsonProperty(value = "response_code")
	private String responseCode="000";
	
	@JsonProperty(value = "response_description")
	private String responseDescription="Success";

	@JsonProperty("candidate_list")
	private List<CandidateDetails> candidateList;

	/**
	 * @return the responseCode
	 */
	public String getResponseCode() {
		return responseCode;
	}

	/**
	 * @param responseCode the responseCode to set
	 */
	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	/**
	 * @return the responseDescription
	 */
	public String getResponseDescription() {
		return responseDescription;
	}

	/**
	 * @param responseDescription the responseDescription to set
	 */
	public void setResponseDescription(String responseDescription) {
		this.responseDescription = responseDescription;
	}

	/**
	 * @return the candidateList
	 */
	public List<CandidateDetails> getCandidateList() {
		return candidateList;
	}

	/**
	 * @param candidateList the candidateList to set
	 */
	public void setCandidateList(List<CandidateDetails> candidateList) {
		this.candidateList = candidateList;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CandidateResponse [responseCode=");
		builder.append(responseCode);
		builder.append(", responseDescription=");
		builder.append(responseDescription);
		builder.append(", candidateList=");
		builder.append(candidateList);
		builder.append("]");
		return builder.toString();
	}		



}