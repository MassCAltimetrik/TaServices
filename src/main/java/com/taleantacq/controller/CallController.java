package com.taleantacq.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taleantacq.comon.TAErrorCodes;
import com.taleantacq.domain.CallDetailRequest;
import com.taleantacq.domain.CallStaticsResponse;
import com.taleantacq.domain.ProfileResponse;
import com.taleantacq.service.ICandidateService;

@RestController
@RequestMapping("/call/")
public class CallController {
	
	 private static final Logger logger = LoggerFactory.getLogger(CallController.class);
	
	@Autowired
	ICandidateService candidateServiceImpl;
	
	@GetMapping("/getCallStatistics/{taId}")
	public CallStaticsResponse getCallStatistics(@PathVariable("taId")Integer taID ) {
		return 	candidateServiceImpl.getCallStatistics(taID);
	}

	@PostMapping("/saveCallDetails")
	public ProfileResponse saveCallDetails(@RequestBody CallDetailRequest callDetailRequest) {
	logger.info("saveCallDetails"+callDetailRequest.toString());
		ProfileResponse callStasResponse;
		try {
			if (callDetailRequest!=null) {
				callStasResponse = candidateServiceImpl.saveCallDetails(callDetailRequest);
			}else{
				callStasResponse=new ProfileResponse();
				callStasResponse.setResponseCode(TAErrorCodes.REQUEST_IS_NULL_CODE);
				callStasResponse.setResponseDescription(TAErrorCodes.REQUEST_IS_NULL_DESCRIPTION);
			}
		} catch (Exception exception) {
			callStasResponse=new ProfileResponse();
			callStasResponse.setResponseCode(TAErrorCodes.SOMETHING_WENT_WRONG_CODE);
			callStasResponse.setResponseDescription(TAErrorCodes.SOMETHING_WENT_WRONG_DESCRIPTION);
		}
		logger.info("Response of saveCallDetails: "+callStasResponse);
		return callStasResponse; 
	}
}
