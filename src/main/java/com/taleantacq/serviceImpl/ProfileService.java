package com.taleantacq.serviceImpl;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taleantacq.comon.TAErrorCodes;
import com.taleantacq.dao.IProfileDao;
import com.taleantacq.domain.ProfileRequest;
import com.taleantacq.domain.ProfileResponse;
import com.taleantacq.jpa.UserInfo;
import com.taleantacq.service.IProfileService;


@Service
public class ProfileService implements IProfileService	{

	@Autowired
	IProfileDao profileDao;

	public ProfileResponse loginAccount(ProfileRequest accountRequest) {
		ProfileResponse profileResponse;
		profileResponse=performLoginDataValidation(accountRequest);
		if(profileResponse.getResponseCode().equals(TAErrorCodes.SUCCESS)) {

			profileResponse=profileDao.loginTaAccount(transfromDomainToJpa(accountRequest));
		}else {
			return profileResponse;
		}
		return profileResponse;
	}


	private ProfileResponse performLoginDataValidation(ProfileRequest accountRequest) {
		ProfileResponse profileResponse=new ProfileResponse();
		if(null==accountRequest.getEmailID() || null==accountRequest.getEmailID().trim()) {
			profileResponse.setResponseCode(TAErrorCodes.EMAIL_IS_NULL_CODE);
			profileResponse.setResponseDescription(TAErrorCodes.EMAIL_IS_NULL_DESCRIPTION);
		}else {
			Boolean isemailValid=isEmailValid(accountRequest.getEmailID());
			if(!isemailValid) {
				profileResponse.setResponseCode(TAErrorCodes.EMAILFORMATINVALID_CODE);
				profileResponse.setResponseDescription(TAErrorCodes.EMAILFORMATINVALID_DESCRIPTION);
			}
		}
		if(null==accountRequest.getPassword() || null==accountRequest.getPassword().trim()) {
			profileResponse.setResponseCode(TAErrorCodes.PASSWORD_IS_NULL_CODE);
			profileResponse.setResponseDescription(TAErrorCodes.PASSWORD_IS_NULL_DESCRIPTION);
		}
		return profileResponse;
	}


	public ProfileResponse createAccount(ProfileRequest accountRequest) {
		ProfileResponse profileResponse;
		profileResponse=performDataValidation(accountRequest);
		if(profileResponse.getResponseCode().equals(TAErrorCodes.SUCCESS)) {
			profileResponse=checkIfemailAlreadyExist(accountRequest);
			if(profileResponse.getResponseCode().equals(TAErrorCodes.SUCCESS)) {
				profileResponse=profileDao.createTaAccount(transfromDomainToJpa(accountRequest));
			}
			}else {
			return profileResponse;
		}
		return profileResponse;
	}

	private ProfileResponse checkIfemailAlreadyExist(ProfileRequest accountRequest) {
		return profileDao.checkIfEmailAlreadyExist(accountRequest.getEmailID().trim());

	}

	private UserInfo transfromDomainToJpa(ProfileRequest accountRequest) {
		UserInfo taJpa=new UserInfo();
		taJpa.setActiveFlag(true);
		taJpa.setContactNumber(accountRequest.getMobileNumber());
		taJpa.setEmail(accountRequest.getEmailID());
		taJpa.setFirstName(accountRequest.getFirstName());
		taJpa.setLastName(accountRequest.getLastName());
		taJpa.setPassword(accountRequest.getPassword());
		taJpa.setSecurityQuestion(accountRequest.getSecurityQuestion());
		taJpa.setSecurityAnswer(accountRequest.getSecurityAnswer());
		taJpa.setIsAdmin(false);

		return taJpa;
	}

	private ProfileResponse performDataValidation(ProfileRequest accountRequest) {
		ProfileResponse profileResponse=new ProfileResponse();
		if(null==accountRequest.getFirstName() || null==accountRequest.getFirstName().trim()) {
			profileResponse.setResponseCode(TAErrorCodes.FIRSTNAME_IS_NULL_CODE);
			profileResponse.setResponseDescription(TAErrorCodes.FIRSTNAME_IS_NULL_DESCRIPTION);
		}
		if(null==accountRequest.getLastName() || null==accountRequest.getLastName().trim()) {
			profileResponse.setResponseCode(TAErrorCodes.LASTNAME_IS_NULL_CODE);
			profileResponse.setResponseDescription(TAErrorCodes.LASTNAME_IS_NULL_DESCRIPTION);
		}
		if(null==accountRequest.getEmailID() || null==accountRequest.getEmailID().trim()) {
			profileResponse.setResponseCode(TAErrorCodes.EMAIL_IS_NULL_CODE);
			profileResponse.setResponseDescription(TAErrorCodes.EMAIL_IS_NULL_DESCRIPTION);
		}else {
			Boolean isemailValid=isEmailValid(accountRequest.getEmailID());
			if(!isemailValid) {
				profileResponse.setResponseCode(TAErrorCodes.EMAILFORMATINVALID_CODE);
				profileResponse.setResponseDescription(TAErrorCodes.EMAILFORMATINVALID_DESCRIPTION);
			}
		}
		if(null==accountRequest.getMobileNumber() || null==accountRequest.getMobileNumber().trim()) {
			profileResponse.setResponseCode(TAErrorCodes.MOBILE_IS_NULL_DESCRIPTION);
			profileResponse.setResponseDescription(TAErrorCodes.MOBILENUMBER_IS_NULL_CODE);
		}else {
			Boolean isMobileCorrect=isMobileNumberValid(accountRequest.getMobileNumber());
			if(!isMobileCorrect) {
				profileResponse.setResponseCode(TAErrorCodes.MOBILENUMBERFORMATINVALID_CODE);
				profileResponse.setResponseDescription(TAErrorCodes.MOBILENUMBERFORMATINVALID_DESCRIPTION);
			}
		}
		if(null==accountRequest.getPassword() || null==accountRequest.getPassword().trim()) {
			profileResponse.setResponseCode(TAErrorCodes.PASSWORD_IS_NULL_CODE);
			profileResponse.setResponseDescription(TAErrorCodes.PASSWORD_IS_NULL_DESCRIPTION);
		}
		if(null==accountRequest.getSecurityAnswer() || null==accountRequest.getSecurityAnswer().trim()) {
			profileResponse.setResponseCode(TAErrorCodes.SECURITY_ANSWER_IS_NULL_CODE);
			profileResponse.setResponseDescription(TAErrorCodes.SECURITY_ANSWER_IS_NULL_DESCRIPTION);
		}
		if(null==accountRequest.getSecurityQuestion() || null==accountRequest.getSecurityQuestion().trim()) {
			profileResponse.setResponseCode(TAErrorCodes.SECURITY_QUESTION_IS_NULL_CODE);
			profileResponse.setResponseDescription(TAErrorCodes.SECURITY_QUESTION_IS_NULL_DESCRIPTION);
		}

		//validate valid email as well
		return profileResponse;

	}

	public Boolean isEmailValid(String emailId) {
		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
				+ "A-Z]{2,7}$";

		Pattern pat = Pattern.compile(emailRegex);
		if (emailId == null)
			return false;
		return pat.matcher(emailId).matches();
	}


	public Boolean isMobileNumberValid(String mobileNumber) {
		boolean isNumberValid=true;
		Pattern p = Pattern.compile("[0-9]{10}");

		// Pattern class contains matcher() method
		// to find matching between given number
		// and regular expression
		Matcher m = p.matcher(mobileNumber);
		if(!(m.find() && m.group().equals(mobileNumber))){
			isNumberValid =false;
		}
		return isNumberValid;
	}


	public List<UserInfo> getUnassingedTaData() {
		return profileDao.getUnassingedTaData();
	}


	public List<UserInfo> getTaData(Integer adminId) {
		return profileDao.getTaData(adminId);
	}

}
