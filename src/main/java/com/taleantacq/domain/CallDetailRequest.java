/**
 * 
 */
package com.taleantacq.domain;

import java.sql.Time;
import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author yadak
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CallDetailRequest  {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	
	@JsonProperty(value = "candidate_id")
	public String candidateID;
	
	@JsonProperty(value = "ta_id")
	public String taID;
	
	@JsonProperty(value = "called_date")
	public Timestamp calledDate;
	
	@JsonProperty(value = "call_start_time")
	public Timestamp callStartTime;
	
	@JsonProperty(value = "call_end_time")
	public Timestamp callEndTime;
	
	@JsonProperty(value = "called_duration")
	public String calledDuration;
	
	@JsonProperty(value = "is_call_recieved")
	public Boolean isCallRecieved;
	
	



	/**
	 * @return the candidateID
	 */
	public String getCandidateID() {
		return candidateID;
	}





	/**
	 * @param candidateID the candidateID to set
	 */
	public void setCandidateID(String candidateID) {
		this.candidateID = candidateID;
	}





	/**
	 * @return the taID
	 */
	public String getTaID() {
		return taID;
	}





	/**
	 * @param taID the taID to set
	 */
	public void setTaID(String taID) {
		this.taID = taID;
	}





	/**
	 * @return the calledDate
	 */
	public Timestamp getCalledDate() {
		return calledDate;
	}





	/**
	 * @param calledDate the calledDate to set
	 */
	public void setCalledDate(Timestamp calledDate) {
		this.calledDate = calledDate;
	}





	/**
	 * @return the callStartTime
	 */
	public Timestamp getCallStartTime() {
		return callStartTime;
	}





	/**
	 * @param callStartTime the callStartTime to set
	 */
	public void setCallStartTime(Timestamp callStartTime) {
		this.callStartTime = callStartTime;
	}





	/**
	 * @return the callEndTime
	 */
	public Timestamp getCallEndTime() {
		return callEndTime;
	}





	/**
	 * @param callEndTime the callEndTime to set
	 */
	public void setCallEndTime(Timestamp callEndTime) {
		this.callEndTime = callEndTime;
	}





	/**
	 * @return the calledDuration
	 */
	public String getCalledDuration() {
		return calledDuration;
	}





	/**
	 * @param calledDuration the calledDuration to set
	 */
	public void setCalledDuration(String calledDuration) {
		this.calledDuration = calledDuration;
	}





	/**
	 * @return the isCallRecieved
	 */
	public Boolean getIsCallRecieved() {
		return isCallRecieved;
	}





	/**
	 * @param isCallRecieved the isCallRecieved to set
	 */
	public void setIsCallRecieved(Boolean isCallRecieved) {
		this.isCallRecieved = isCallRecieved;
	}





	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CallDetailRequest [candidateID=");
		builder.append(candidateID);
		builder.append(", taID=");
		builder.append(taID);
		builder.append(", calledDate=");
		builder.append(calledDate);
		builder.append(", callStartTime=");
		builder.append(callStartTime);
		builder.append(", callEndTime=");
		builder.append(callEndTime);
		builder.append(", calledDuration=");
		builder.append(calledDuration);
		builder.append(", isCallRecieved=");
		builder.append(isCallRecieved);
		builder.append("]");
		return builder.toString();
	}
	}