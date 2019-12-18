package com.taleantacq.dao;

import java.util.HashSet;
import java.util.List;

import com.taleantacq.domain.ProfileResponse;
import com.taleantacq.jpa.UserInfo;



public interface IProfileDao{

	public ProfileResponse createTaAccount(UserInfo tajpa);

	public ProfileResponse loginTaAccount(UserInfo taJpa);

	public ProfileResponse checkIfEmailAlreadyExist(String emailID) ;	

	public List<UserInfo> getUnassingedTaData();

	public List<UserInfo> getTaData(Integer adminId);
}
