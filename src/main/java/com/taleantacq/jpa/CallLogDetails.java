/**
 * 
 */
package com.taleantacq.jpa;

import java.sql.Time;
import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author yadak
 *
 */
@Entity
@Table(name="call_log")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CallLogDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="call_id")
	@JsonIgnoreProperties
	private Integer callId;

	@Column(name = "ta_id")
	@JsonIgnoreProperties
	private Integer taId;

	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.PERSIST)
	@JoinColumn(name = "candidate_id" ,columnDefinition="INTEGER")
	private  CandidateDetails candidateId;

 

	@Column(name="is_call_recieved")
	private Boolean isCallRecieved;

	@Column(name="call_duration")
	private Time callDuration;

	@Column(name="call_date")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss") 
	private Timestamp callDate;

	@Column(name="call_start_time")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss") 
	private Timestamp callStartTime;

	@Column(name="call_end_time")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss") 
	private Timestamp callEndTime;

	/**
	 * @return the callId
	 */
	public Integer getCallId() {
		return callId;
	}

	/**
	 * @param callId the callId to set
	 */
	public void setCallId(Integer callId) {
		this.callId = callId;
	}

	/**
	 * @return the taId
	 */
	public Integer getTaId() {
		return taId;
	}

	/**
	 * @param taId the taId to set
	 */
	public void setTaId(Integer taId) {
		this.taId = taId;
	}

	/**
	 * @return the candidateId
	 */
	public CandidateDetails getCandidateId() {
		return candidateId;
	}

	/**
	 * @param candidateId the candidateId to set
	 */
	public void setCandidateId(CandidateDetails candidateId) {
		this.candidateId = candidateId;
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

	/**
	 * @return the callDuration
	 */
	public Time getCallDuration() {
		return callDuration;
	}

	/**
	 * @param time the callDuration to set
	 */
	public void setCallDuration(Time time) {
		this.callDuration = time;
	}

	/**
	 * @return the callDate
	 */
	public Timestamp getCallDate() {
		return callDate;
	}

	/**
	 * @param callDate the callDate to set
	 */
	public void setCallDate(Timestamp callDate) {
		this.callDate = callDate;
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CallLogDetails [callId=");
		builder.append(callId);
		builder.append(", taId=");
		builder.append(taId);
		builder.append(", candidateId=");
		builder.append(candidateId); 
		builder.append(", isCallRecieved=");
		builder.append(isCallRecieved);
		builder.append(", callDuration=");
		builder.append(callDuration);
		builder.append(", callDate=");
		builder.append(callDate);
		builder.append(", callStartTime=");
		builder.append(callStartTime);
		builder.append(", callEndTime=");
		builder.append(callEndTime);
		builder.append("]");
		return builder.toString();
	}
}



