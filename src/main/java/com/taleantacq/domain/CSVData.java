/**
 * 
 */
package com.taleantacq.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author yadak
 *
 */

public class CSVData {

	@JsonProperty("srno")
	String srNo;
	
	@JsonProperty("name")
	String name;
	
	@JsonProperty("mobile_number")
	String mobileNumber;

	public String getSrNo() {
		return srNo;
	}

	public void setSrNo(String srNo) {
		this.srNo = srNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CSVData [srNo=");
		builder.append(srNo);
		builder.append(", name=");
		builder.append(name);
		builder.append(", mobileNumber=");
		builder.append(mobileNumber);
		builder.append("]");
		return builder.toString();
	}
	
	
}
