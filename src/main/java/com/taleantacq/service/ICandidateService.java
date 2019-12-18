package com.taleantacq.service;

import java.io.File;
import java.sql.SQLException;
import java.util.List;

import com.taleantacq.domain.CallDetailRequest;
import com.taleantacq.domain.CallStaticsResponse;
import com.taleantacq.domain.CandidateResponse;
import com.taleantacq.domain.ProfileResponse;
import com.taleantacq.jpa.CandidateDetails;


public interface ICandidateService {

	
	public CandidateResponse getCandidateDetails(Integer taID, Integer pageNumber) throws SQLException ;

	public CallStaticsResponse getCallStatistics(Integer taID) ;

	public ProfileResponse saveCallDetails(CallDetailRequest callDetailRequest) ;

	public CandidateResponse readExcel(File file, Integer taid) ;

		
	}
