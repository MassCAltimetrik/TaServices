package com.taleantacq.domain;

import java.sql.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.taleantacq.jpa.CandidateDetails;

@Entity
@Table(name="batch")
public class Batch {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="batch_id")
	@JsonIgnoreProperties
	private Integer batchId;

	@Column(name = "batch_name")
	private String batchName; 

	@Column(name="uploaded_by")
	private Integer uploadedBy;
	
	@Column(name="assigned_to")
	private Integer assignedTo;
	
	@Column(name="date")
	private Date date;

	@Column(name="Candidates")
	@OneToMany(mappedBy="batch",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<CandidateDetails> candidates;
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getBatchId() {
		return batchId;
	}

	public void setBatchId(Integer batchId) {
		this.batchId = batchId;
	}

	public String getBatchName() {
		return batchName;
	}

	public void setBatchName(String batchName) {
		this.batchName = batchName;
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
