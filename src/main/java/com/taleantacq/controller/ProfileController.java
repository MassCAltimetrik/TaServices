package com.taleantacq.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.taleantacq.comon.TAErrorCodes;
import com.taleantacq.domain.ProfileRequest;
import com.taleantacq.domain.ProfileResponse;
import com.taleantacq.jpa.UserInfo;
import com.taleantacq.service.IProfileService;


@RequestMapping("/profile/")
@RestController
public class ProfileController {

	 private static final Logger logger = LoggerFactory.getLogger(ProfileController.class);
	 
	@Autowired
	IProfileService profileServiceImpl;

	
	 @RequestMapping(value = "/createAccount", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	      public ProfileResponse createAccount(@RequestBody ProfileRequest accountRequest) {
		 ProfileResponse response = null;

	      try {
	        if (accountRequest!=null) {
	          response = profileServiceImpl.createAccount(accountRequest);
	        }else{
	        	logger.info("request is null");
	        	response=new ProfileResponse();
	        	response.setResponseCode(TAErrorCodes.REQUEST_IS_NULL_CODE);
	          response.setResponseDescription(TAErrorCodes.REQUEST_IS_NULL_DESCRIPTION);
	        }
	        
	        } catch (Exception exception) {
	        	logger.error("Exception in creating account",exception);
	        	response=new ProfileResponse();
		      response.setResponseCode(TAErrorCodes.SOMETHING_WENT_WRONG_CODE);
		      response.setResponseDescription(TAErrorCodes.SOMETHING_WENT_WRONG_DESCRIPTION);
		    } 
			
			return response;
		}

	 
	 @RequestMapping(value = "/loginAccount", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
     public ProfileResponse loginAccount(@RequestBody ProfileRequest accountRequest) {
	 ProfileResponse response = null;

     try {
       if (accountRequest!=null) {
         response = profileServiceImpl.loginAccount(accountRequest);
       }else{
    	   logger.info("Request is null");
    	   response=new ProfileResponse();
       	response.setResponseCode(TAErrorCodes.REQUEST_IS_NULL_CODE);
         response.setResponseDescription(TAErrorCodes.REQUEST_IS_NULL_DESCRIPTION);
       }
       
       } catch (Exception exception) {
    	   logger.error("Exception in login account",exception);
	      response=new ProfileResponse();
	      response.setResponseCode(TAErrorCodes.SOMETHING_WENT_WRONG_CODE);
	      response.setResponseDescription(TAErrorCodes.SOMETHING_WENT_WRONG_DESCRIPTION);
	    } 
		
		return response;
	}
	 
	 @GetMapping(value = "/unassignedTAData")
     public  List<UserInfo> unassignedtaDetails() {
		/* List<UserInfo> list = profileServiceImpl.getUnassingedTaData();
		 HttpHeaders httpHeaders = new HttpHeaders();
		 return new ResponseEntity<UserInfo>((UserInfo) list,httpHeaders, HttpStatus.OK);*/
		 return profileServiceImpl.getUnassingedTaData();
		 
	}
	 
	 @GetMapping(value = "/assignedTAData/{id}")
     public  List<UserInfo> getAssignedtaDetails(@PathVariable("id") int id) {
		 return profileServiceImpl.getTaData(id);
	}

 
}

