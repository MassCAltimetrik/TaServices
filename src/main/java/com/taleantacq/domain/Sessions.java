package com.taleantacq.domain;

import java.sql.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.taleantacq.jpa.CandidateDetails;

@Entity
@Table(name="session")
public class Sessions {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="session_id")
	@JsonIgnoreProperties
	private Integer sessionId;

	@Column(name = "session_name")
	private String sessionName; 

	@Column(name="uploaded_by")
	private Integer uploadedBy;
	
	@Column(name="assigned_to")
	private Integer assignedTo;
	
	@Column(name="date")
	private Date date;
	
	@OneToMany(mappedBy="session",cascade = CascadeType.ALL)
	private Set<CandidateDetails> candidates;
	
	public Set<CandidateDetails> getCandidates() {
		return candidates;
	}

	public void setCandidates(Set<CandidateDetails> candidates) {
		this.candidates = candidates;
	}
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getsessionId() {
		return sessionId;
	}

	public void setsessionId(Integer sessionId) {
		this.sessionId = sessionId;
	}

	public String getsessionName() {
		return sessionName;
	}

	public void setsessionName(String sessionName) {
		this.sessionName = sessionName;
	}

	public Integer getUploadedBy() {
		return uploadedBy;
	}

	public void setUploadedBy(Integer uploadedBy) {
		this.uploadedBy = uploadedBy;
	}

	public Integer getAssignedTo() {
		return assignedTo;
	}

	public void setAssignedTo(Integer assignedTo) {
		this.assignedTo = assignedTo;
	}
	

}
