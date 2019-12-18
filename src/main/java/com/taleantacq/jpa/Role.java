package com.taleantacq.jpa;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "role")
public class Role {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "role_id")
    private int id;
	
	@JsonIgnoreProperties("userlist")
    @Column(name = "role")
    private String role;
    
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	
	@OneToMany(mappedBy="role")
	private Set<UserInfo> userlist;

	public Set<UserInfo> getUserlist() {
		return userlist;
	}
	public void setUserlist(Set<UserInfo> userlist) {
		this.userlist = userlist;
	}
    
}
