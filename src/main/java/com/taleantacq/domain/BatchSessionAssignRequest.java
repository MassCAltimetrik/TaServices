package com.taleantacq.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BatchSessionAssignRequest {

	@JsonProperty(value = "type")
	public String type;
	
	@JsonProperty(value = "Id")
	public Integer id;
	
	@JsonProperty(value = "adminId")
	public Integer adminId;
	
	@JsonProperty(value = "selectedTaId")
	public Integer selectedTaId;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAdminId() {
		return adminId;
	}

	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}

	public Integer getSelectedTaId() {
		return selectedTaId;
	}

	public void setSelectedTaId(Integer selectedTaId) {
		this.selectedTaId = selectedTaId;
	}
	
}
