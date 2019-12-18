package com.taleantacq.domain;

import java.util.HashSet;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SessionRequest {
	
	@JsonProperty(value = "sessionName")
	public String sessionName;
	
	@JsonProperty(value = "taId")
	public Integer taId;
	
	@JsonProperty(value = "candidates")
	public HashSet<Integer> candidates;

	public String getSessionName() {
		return sessionName;
	}

	public void setSessionName(String sessionName) {
		this.sessionName = sessionName;
	}

	public Integer getTaId() {
		return taId;
	}

	public void setTaId(Integer taId) {
		this.taId = taId;
	}

	public HashSet<Integer> getCandidates() {
		return candidates;
	}

	public void setCandidates(HashSet<Integer> candidates) {
		this.candidates = candidates;
	}
	
}
