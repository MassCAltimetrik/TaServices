/**
 * 
 */
package com.taleantacq.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.taleantacq.jpa.UserInfo;

/**
 * @author yadak
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProfileResponse  {

	@JsonProperty(value = "response_code")
	private String responseCode="000";
	
	@JsonProperty(value = "response_description")
	private String responseDescription="Success";

	@JsonProperty("ta_id")
	private String taID;
	
	@JsonProperty("ta_name")
	private String taName;
	
	@JsonProperty("role")
	private String role;
	
	@JsonProperty("admin")
	private UserInfo admin;

	public UserInfo getAdmin() {
		return admin;
	}

	public void setAdmin(UserInfo admin) {
		this.admin = admin;
	}

	@JsonProperty("subordinates")
	private Set<UserInfo> subordinates = new HashSet<UserInfo>();

	public Set<UserInfo> getSubordinates() {
		return subordinates;
	}

	public void setSubordinates(Set<UserInfo> subordinates) {
		this.subordinates = subordinates;
	}

	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}


	/**
	 * @return the taName
	 */
	public String getTaName() {
		return taName;
	}

	/**
	 * @param taName the taName to set
	 */
	public void setTaName(String taName) {
		this.taName = taName;
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ProfileResponse [responseCode=");
		builder.append(responseCode);
		builder.append(", responseDescription=");
		builder.append(responseDescription);
		builder.append(", taID=");
		builder.append(taID);
		builder.append(", taName=");
		builder.append(taName);
		builder.append("]");
		return builder.toString();
	}
	
	
}