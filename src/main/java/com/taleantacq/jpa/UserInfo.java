/**
 * 
 */
package com.taleantacq.jpa;

import java.sql.Timestamp;
import java.util.HashSet;
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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="ta_info")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class UserInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ta_id")
	private int taId;

	@Column
	private String firstName;

	@Column
	private String lastName;

	@Column(name="contact_number")
	private String contactNumber;

	@Column
	private String email;

	@Column
	private String username;

	@Column
	private String password;

	@Column(name="active_flag" ,nullable = false)	
	private Boolean activeFlag;

	@Column(name="is_admin", nullable=false)
	private Boolean isAdmin;

	@Column(name="security_question", nullable=false)
	private String securityQuestion;

	@Column(name="security_answer", nullable=false)
	private String securityAnswer;

	@Column
	private Timestamp createdOn;

	@Column
	private Timestamp updatedOn;

	@Column
	private Timestamp createdBy;

	@Column
	private Timestamp updatedBy;

	@JsonIgnoreProperties("userlist")
	@ManyToOne
	@JoinColumn(name="role_id")
	Role role;

	@ManyToOne(cascade={CascadeType.ALL}, fetch=FetchType.LAZY)
	@JoinColumn(name="admin")
	@JsonIgnoreProperties("subordinates")
	private UserInfo admin;

	public UserInfo getAdmin() {
		return admin;
	}

	public void setAdmin(UserInfo admin) {
		this.admin = admin;
	}

	@OneToMany(mappedBy="admin")
	private Set<UserInfo> subordinates = new HashSet<UserInfo>();

	public Set<UserInfo> getSubordinates() {
		return subordinates;
	}

	public void setSubordinates(Set<UserInfo> subordinates) {
		this.subordinates = subordinates;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
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
	public void setTaId(int taId) {
		this.taId = taId;
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
	 * @return the contactNumber
	 */
	public String getContactNumber() {
		return contactNumber;
	}

	/**
	 * @param contactNumber the contactNumber to set
	 */
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
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
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the active_flag
	 */
	public Boolean isActiveFlag() {
		return activeFlag;
	}

	/**
	 * @param active_flag the active_flag to set
	 */
	public void setActiveFlag(Boolean activeFlag) {
		this.activeFlag = activeFlag;
	}

	/**
	 * @return the is_admin
	 */
	public Boolean isAdmin() {
		return isAdmin;
	}

	/**
	 * @param is_admin the is_admin to set
	 */
	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
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

	/**
	 * @return the securityQuestion
	 */
	public String getSecurityQuestion() {
		return securityQuestion;
	}

	/**
	 * @param securityQuestion the securityQuestion to set
	 */
	public void setSecurityQuestion(String securityQuestion) {
		this.securityQuestion = securityQuestion;
	}

	/**
	 * @return the securityAnswer
	 */
	public String getSecurityAnswer() {
		return securityAnswer;
	}

	/**
	 * @param securityAnswer the securityAnswer to set
	 */
	public void setSecurityAnswer(String securityAnswer) {
		this.securityAnswer = securityAnswer;
	}

	/**
	 * @param isAdmin the isAdmin to set
	 */
	public void setAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	/**
	 * @return the activeFlag
	 */
	public Boolean getActiveFlag() {
		return activeFlag;
	}

	/**
	 * @return the isAdmin
	 */
	public Boolean getIsAdmin() {
		return isAdmin;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TaInfo [taId=");
		builder.append(taId);
		builder.append(", firstName=");
		builder.append(firstName);
		builder.append(", lastName=");
		builder.append(lastName);
		builder.append(", contactNumber=");
		builder.append(contactNumber);
		builder.append(", email=");
		builder.append(email);
		builder.append(", username=");
		builder.append(username);
		builder.append(", password=");
		builder.append(password);
		builder.append(", activeFlag=");
		builder.append(activeFlag);
		builder.append(", isAdmin=");
		builder.append(isAdmin);
		builder.append(", securityQuestion=");
		builder.append(securityQuestion);
		builder.append(", securityAnswer=");
		builder.append(securityAnswer);
		builder.append(", createdOn=");
		builder.append(createdOn);
		builder.append(", updatedOn=");
		builder.append(updatedOn);
		builder.append(", createdBy=");
		builder.append(createdBy);
		builder.append(", updatedBy=");
		builder.append(updatedBy);
		builder.append("]");
		return builder.toString();
	}



}
