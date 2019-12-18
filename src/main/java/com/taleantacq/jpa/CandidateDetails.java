/**
 * 
 */
package com.taleantacq.jpa;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.poiji.annotation.ExcelCellName;
import com.taleantacq.comon.TAErrorCodes;



/**
 * @author yadak
 *
 */
@Entity
@Table(name="candidate_details")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class CandidateDetails implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="candidate_id")
	private Integer candidateId;

	@Column(name="fileName")
	private String fileName;

	@Size(min=2,message=TAErrorCodes.VIOLATIONS_MIN_CHAR)
	@ExcelCellName("First Name")
	@Column
	private String firstName;

	@Size(min=2,message=TAErrorCodes.VIOLATIONS_MIN_CHAR)
	@ExcelCellName("Last Name")
	@Column
	private String lastName;

	@Size(min=8,max=11,message = TAErrorCodes.VIOLATIONS_MIN_NUMBER)
	@ExcelCellName("Primary Mobile Number")
	@Column(name="primary_mobile_number")
	private String primaryMobileNumber;

	@Column(name="secondary_mobile_number")
	private Long secondaryMobileNumber;

	//@Pattern(regexp=".+@.+\\.[a-z]+")
	@Email(message = TAErrorCodes.VIOLATIONS_EMAIL_FORMAT)
	@ExcelCellName("Email")
	@Column
	private String email;

	@Column(name = "ta_id")
	private  Integer taId;

	@Column
	private String location;

	@Column(name="no_of_calls")
	private int noOfCalls;

	@Column(name="active_flag")
	private boolean activeFlag;

	@Column
	private Timestamp createdOn;

	@Column
	private Timestamp updatedOn;

	@Column
	private Timestamp createdBy;

	@Column
	private Timestamp updatedBy;

	private StringBuffer errors;


	@JsonIgnoreProperties("candidates")
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="batch_id", nullable=false)
	private Batch batch;

	@JsonIgnoreProperties("candidates")
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="session_id")
	private Session session;
	
	public Batch getBatch() {
		return batch;
	}

	public void setBatch(Batch batch) {
		this.batch = batch;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}


	/**
	 * @return the errors
	 */
	public StringBuffer getErrors() {
		return errors;
	}

	/**
	 * @param errors the errors to set
	 */
	public void setErrors(StringBuffer errors) {
		this.errors = errors;
	}

	/**
	 * @return the candidateId
	 */
	public Integer getCandidateId() {
		return candidateId;
	}

	/**
	 * @param candidateId the candidateId to set
	 */
	public void setCandidateId(Integer candidateId) {
		this.candidateId = candidateId;
	}

	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the primaryMobileNumber
	 */
	public String getPrimaryMobileNumber() {
		return primaryMobileNumber;
	}

	/**
	 * @param primaryMobileNumber the primaryMobileNumber to set
	 */
	public void setPrimaryMobileNumber(String primaryMobileNumber) {
		this.primaryMobileNumber = primaryMobileNumber;
	}

	/**
	 * @return the secondaryMobileNumber
	 */
	public Long getSecondaryMobileNumber() {
		return secondaryMobileNumber;
	}

	/**
	 * @param secondaryMobileNumber the secondaryMobileNumber to set
	 */
	public void setSecondaryMobileNumber(Long secondaryMobileNumber) {
		this.secondaryMobileNumber = secondaryMobileNumber;
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
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * @return the noOfCalls
	 */
	public int getNoOfCalls() {
		return noOfCalls;
	}

	/**
	 * @param noOfCalls the noOfCalls to set
	 */
	public void setNoOfCalls(int noOfCalls) {
		this.noOfCalls = noOfCalls;
	}

	/**
	 * @return the activeFlag
	 */
	public boolean isActiveFlag() {
		return activeFlag;
	}

	/**
	 * @param activeFlag the activeFlag to set
	 */
	public void setActiveFlag(boolean activeFlag) {
		this.activeFlag = activeFlag;
	}

	/**
	 * @return the createdOn
	 */
	public Timestamp getCreatedOn() {
		return createdOn;
	}

	/**
	 * @param createdOn the createdOn to set
	 */
	public void setCreatedOn(Timestamp createdOn) {
		this.createdOn = createdOn;
	}

	/**
	 * @return the updatedOn
	 */
	public Timestamp getUpdatedOn() {
		return updatedOn;
	}

	/**
	 * @param updatedOn the updatedOn to set
	 */
	public void setUpdatedOn(Timestamp updatedOn) {
		this.updatedOn = updatedOn;
	}

	/**
	 * @return the createdBy
	 */
	public Timestamp getCreatedBy() {
		return createdBy;
	}

	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(Timestamp createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * @return the updatedBy
	 */
	public Timestamp getUpdatedBy() {
		return updatedBy;
	}

	/**
	 * @param updatedBy the updatedBy to set
	 */
	public void setUpdatedBy(Timestamp updatedBy) {
		this.updatedBy = updatedBy;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CandidateDetails [candidateId=");
		builder.append(candidateId);
		builder.append(", fileName=");
		builder.append(fileName);
		builder.append(", firstName=");
		builder.append(firstName);
		builder.append(", lastName=");
		builder.append(lastName);
		builder.append(", primaryMobileNumber=");
		builder.append(primaryMobileNumber);
		builder.append(", secondaryMobileNumber=");
		builder.append(secondaryMobileNumber);
		builder.append(", email=");
		builder.append(email);
		builder.append(", taId=");
		builder.append(taId);
		builder.append(", location=");
		builder.append(location);
		builder.append(", noOfCalls=");
		builder.append(noOfCalls);
		builder.append(", activeFlag=");
		builder.append(activeFlag);
		builder.append(", createdOn=");
		builder.append(createdOn);
		builder.append(", updatedOn=");
		builder.append(updatedOn);
		builder.append(", createdBy=");
		builder.append(createdBy);
		builder.append(", updatedBy=");
		builder.append(updatedBy);
		builder.append(", errors=");
		builder.append(errors);
		builder.append("]");
		return builder.toString();
	}


}
