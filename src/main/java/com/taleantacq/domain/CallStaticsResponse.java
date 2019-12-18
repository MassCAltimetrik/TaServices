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
public class CallStaticsResponse {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	
	@JsonProperty(value = "last_call_date")
	private Timestamp lastCalldate;

	@JsonProperty(value = "last_call_number")
	private String lastCallNumber;

	@JsonProperty(value = "last_called_candidate_name")
	private String lastCalledcandidateName;

	@JsonProperty(value = "last_call_duration")
	private Time lastCallduration;

	@JsonProperty(value = "total_calls_made")
	private String totalNumberOfCalls;
	
	@JsonProperty(value = "total_calls_made_today")
	private String todaysNumberOfCalls;

	@JsonProperty(value = "remaining_calls_to_be_made")
	private String remainingCallToMake;

	/**
	 * @return the lastCalldate
	 */
	public Timestamp getLastCalldate() {
		return lastCalldate;
	}

	/**
	 * @param lastCalldate the lastCalldate to set
	 */
	public void setLastCalldate(Timestamp lastCalldate) {
		this.lastCalldate = lastCalldate;
	}

	/**
	 * @return the lastCallNumber
	 */
	public String getLastCallNumber() {
		return lastCallNumber;
	}

	/**
	 * @param lastCallNumber the lastCallNumber to set
	 */
	public void setLastCallNumber(String lastCallNumber) {
		this.lastCallNumber = lastCallNumber;
	}

	/**
	 * @return the lastCalledcandidateName
	 */
	public String getLastCalledcandidateName() {
		return lastCalledcandidateName;
	}

	/**
	 * @param lastCalledcandidateName the lastCalledcandidateName to set
	 */
	public void setLastCalledcandidateName(String lastCalledcandidateName) {
		this.lastCalledcandidateName = lastCalledcandidateName;
	}

	/**
	 * @return the lastCallduration
	 */
	public Time getLastCallduration() {
		return lastCallduration;
	}

	/**
	 * @param lastCallduration the lastCallduration to set
	 */
	public void setLastCallduration(Time lastCallduration) {
		this.lastCallduration = lastCallduration;
	}

	/**
	 * @return the totalNumberOfCalls
	 */
	public String getTotalNumberOfCalls() {
		return totalNumberOfCalls;
	}

	/**
	 * @param totalNumberOfCalls the totalNumberOfCalls to set
	 */
	public void setTotalNumberOfCalls(String totalNumberOfCalls) {
		this.totalNumberOfCalls = totalNumberOfCalls;
	}

	/**
	 * @return the todaysNumberOfCalls
	 */
	public String getTodaysNumberOfCalls() {
		return todaysNumberOfCalls;
	}

	/**
	 * @param todaysNumberOfCalls the todaysNumberOfCalls to set
	 */
	public void setTodaysNumberOfCalls(String todaysNumberOfCalls) {
		this.todaysNumberOfCalls = todaysNumberOfCalls;
	}

	/**
	 * @return the remainingCallToMake
	 */
	public String getRemainingCallToMake() {
		return remainingCallToMake;
	}

	/**
	 * @param remainingCallToMake the remainingCallToMake to set
	 */
	public void setRemainingCallToMake(String remainingCallToMake) {
		this.remainingCallToMake = remainingCallToMake;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CallStaticsResponse [lastCalldate=");
		builder.append(lastCalldate);
		builder.append(", lastCallNumber=");
		builder.append(lastCallNumber);
		builder.append(", lastCalledcandidateName=");
		builder.append(lastCalledcandidateName);
		builder.append(", lastCallduration=");
		builder.append(lastCallduration);
		builder.append(", totalNumberOfCalls=");
		builder.append(totalNumberOfCalls);
		builder.append(", todaysNumberOfCalls=");
		builder.append(todaysNumberOfCalls);
		builder.append(", remainingCallToMake=");
		builder.append(remainingCallToMake);
		builder.append("]");
		return builder.toString();
	}

	
}
