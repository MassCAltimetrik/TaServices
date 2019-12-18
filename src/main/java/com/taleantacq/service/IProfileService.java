package com.taleantacq.service;

import java.util.List;

import com.taleantacq.domain.ProfileRequest;
import com.taleantacq.domain.ProfileResponse;
import com.taleantacq.jpa.UserInfo;


public interface IProfileService {


	public ProfileResponse loginAccount(ProfileRequest accountRequest) ;

	public ProfileResponse createAccount(ProfileRequest accountRequest) ;

	public List<UserInfo> getUnassingedTaData();

	public List<UserInfo> getTaData(Integer adminId);

}
