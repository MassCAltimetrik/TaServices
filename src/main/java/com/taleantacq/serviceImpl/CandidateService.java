package com.taleantacq.serviceImpl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.mail.MessagingException;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.poiji.bind.Poiji;
import com.taleantacq.comon.TAErrorCodes;
import com.taleantacq.dao.ICandidateDao;
import com.taleantacq.domain.CallDetailRequest;
import com.taleantacq.domain.CallStaticsResponse;
import com.taleantacq.domain.CandidateResponse;
import com.taleantacq.domain.ProfileResponse;
import com.taleantacq.jpa.CallLogDetails;
import com.taleantacq.jpa.CandidateDetails;
import com.taleantacq.jpa.UserInfo;
import com.taleantacq.service.ICandidateService;
import com.taleantacq.util.EmailUtil;
import com.taleantacq.util.Excelutil;

@Service
public class CandidateService implements ICandidateService {

	@Autowired
	Logger logger;

	@Autowired
	ICandidateDao candidateDaoImpl;

	@Autowired
	Validator validator;

	@Autowired
	EmailUtil emailUtil;

	@Autowired
	Excelutil excelutil;

	public CandidateResponse getCandidateDetails(Integer taID, Integer pageNumber) throws SQLException {
		CandidateResponse response = new CandidateResponse();

		if (pageNumber == null || pageNumber == 0) {
			pageNumber = 0;
		}
		
		if (null == taID) {
			response.setResponseCode(TAErrorCodes.TA_ID_NULL_CODE);
			response.setResponseDescription(TAErrorCodes.TA_ID_NULL_DESCRIPTION);
			return response;
		}
		
		CandidateResponse cr=candidateDaoImpl.getCandidateDetails(taID, pageNumber);
		 
    	 List<CandidateDetails> finallist=new LinkedList<>();
    	 
    	 
    	 if(cr.getCandidateList() !=null && !cr.getCandidateList().isEmpty()) {
    		 
    		 for (CandidateDetails cd : cr.getCandidateList()) {
    				
    			 Boolean status=candidateDaoImpl.getcalllogByCandidate(cd.getCandidateId());
    			 
    			 System.out.println("Candidate id ==  "+cd.getCandidateId()+"  status == "+status);
    			  if(!status) {
    					 finallist.add(cd);
    			  }
    				
    			}
    			
    			cr.getCandidateList().clear();
    			cr.setCandidateList(finallist);
    			 
    	 }
		
    	
		return cr;

	}

	public CallStaticsResponse getCallStatistics(Integer taID) {
		return candidateDaoImpl.getcallStatistics(taID);
	}

	public ProfileResponse saveCallDetails(CallDetailRequest callDetailRequest) {
		ProfileResponse response;
		response = validateDataFromTheRequest(callDetailRequest);
		if (response.getResponseCode().equals(TAErrorCodes.SUCCESS)) {
			response = candidateDaoImpl.saveCallDetails(transforrequestTocCallJpa(callDetailRequest));
		} else {
			return response;
		}
		return response;
	}

	private CallLogDetails transforrequestTocCallJpa(CallDetailRequest callDetailRequest) {
		CandidateDetails cd = new CandidateDetails();
		cd.setCandidateId(Integer.parseInt(callDetailRequest.getCandidateID()));

		CallLogDetails callLogJpa = new CallLogDetails();
		callLogJpa.setCallDate(callDetailRequest.getCalledDate());
		String timeString = callDetailRequest.getCalledDuration();
		logger.info("time  duration recived is **************** " + timeString);
		String arrtime[] = timeString.split(":");
		Integer hours = new Integer(arrtime[0]);
		Integer mins = new Integer(arrtime[1]);
		Integer sec = new Integer(arrtime[2]);
		Time timeDuration = new Time(hours, mins, sec);
		logger.info("converted time is ################" + timeDuration);
		callLogJpa.setCallDuration(timeDuration);
		
		Timestamp ts=callDetailRequest.getCallStartTime();
		
		ts.setMinutes(ts.getMinutes()-timeDuration.getMinutes());
		ts.setMinutes(ts.getSeconds()-timeDuration.getSeconds());

		
		callLogJpa.setCallEndTime(callDetailRequest.getCallEndTime());
		callLogJpa.setCallStartTime(ts);
		callLogJpa.setCandidateId(cd);
		callLogJpa.setIsCallRecieved(callDetailRequest.getIsCallRecieved());
		callLogJpa.setTaId(Integer.parseInt(callDetailRequest.getTaID()));

		return callLogJpa;
	}

	private ProfileResponse validateDataFromTheRequest(CallDetailRequest callDetailRequest) {
		ProfileResponse response = new ProfileResponse();
		if (null != callDetailRequest.getCalledDate()) {
		} else {
			response.setResponseCode(TAErrorCodes.CALLED_DATE_NULL_CODE);
			response.setResponseDescription(TAErrorCodes.CALLED_DATE_NULL_DESCRIPTION);
		}

		if (null != callDetailRequest.getCalledDuration()) {
			if (callDetailRequest.getCalledDuration().equals("00:00:00")) {
				callDetailRequest.setIsCallRecieved(Boolean.FALSE);
			} else {
				callDetailRequest.setIsCallRecieved(Boolean.TRUE);
			}
		} else {
			response.setResponseCode(TAErrorCodes.CALL_DURATION_TIME_NULL_CODE);
			response.setResponseDescription(TAErrorCodes.CALL_DURATION_TIME_NULL_DESCRIPTION);
		}

		if (null != callDetailRequest.getCallStartTime()) {
		} else {
			response.setResponseCode(TAErrorCodes.CALL_START_TIME_NULL_CODE);
			response.setResponseDescription(TAErrorCodes.CALL_START_TIME_NULL_DESCRIPTION);
		}

		if (null != callDetailRequest.getCandidateID()) {
		} else {
			response.setResponseCode(TAErrorCodes.CANDIDATE_ID_NULL_CODE);
			response.setResponseDescription(TAErrorCodes.CANDIDATE_ID_NULL_DESCRIPTION);
		}

		if (null != callDetailRequest.getIsCallRecieved()) {
		} else {
			response.setResponseCode(TAErrorCodes.IS_CALL_RECIEVED_NULL_CODE);
			response.setResponseDescription(TAErrorCodes.IS_CALL_RECIEVED_NULL_DESCRIPTION);
		}

		return response;
	}

	public CandidateResponse readExcel(File file,Integer taid) {
		CandidateResponse response = new CandidateResponse();
		List<CandidateDetails> employees;
		List<CandidateDetails> errorCandidateList = new ArrayList<CandidateDetails>();
		try {
			employees = Poiji.fromExcel(file, CandidateDetails.class);
		} catch (Exception exception) {
			logger.error("Exception in converting excel data to objects", exception);
			response.setResponseCode(TAErrorCodes.ERROR_IN_CONVERTING_EXCEL_DATA_CODE);
			response.setResponseDescription(TAErrorCodes.ERROR_IN_CONVERTING_EXCEL_DATA_DESCRIPTION);
			return response;
		}

		try {
            
			String email=candidateDaoImpl.getTaMailid(taid);
			response = validateFileAndCreateErrorFile(employees, errorCandidateList,email);
		} catch (MessagingException e) {
			logger.error("Excetion in reading excel ", e);
			response.setResponseCode(TAErrorCodes.EXCEL_VALIDATION_FAILED_CODE);
			response.setResponseDescription(TAErrorCodes.EXCEL_VALIDATION_FAILED_DESCRIPTION);
			return response;
		}

		if (response.getResponseCode().contentEquals(TAErrorCodes.SUCCESS)) {
			for (CandidateDetails candidateDetails : employees) {			
				candidateDetails.setTaId(taid);			
			}
			
			response = candidateDaoImpl.saveCandidateDetails(employees);
		}
		return response;
	}

	/**
	 * @param employees
	 * @param errorCandidateList
	 * @throws MessagingException
	 */
	private CandidateResponse validateFileAndCreateErrorFile(List<CandidateDetails> employees,
			List<CandidateDetails> errorCandidateList,String email) throws MessagingException {

		CandidateResponse response = new CandidateResponse();
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Iterator<CandidateDetails> candidateIterator = employees.iterator();
		while (candidateIterator.hasNext()) {
			CandidateDetails candidateDetail = candidateIterator.next();
			Set<ConstraintViolation<CandidateDetails>> violations = validator.validate(candidateDetail);
			Boolean hasError = populateErrorsInFile(candidateDetail, violations, errorCandidateList);
			if (hasError) {
				candidateIterator.remove();
			}
		}

		
		if (!errorCandidateList.isEmpty()) {
			try {
				String validatedFile = excelutil.generateExcelForCandidateErrors(errorCandidateList);
				emailUtil.sendEmail(validatedFile,email);

			} catch (IOException exception) {
				response.setResponseCode(TAErrorCodes.ERROR_GENERATING_ERROR_FILE_CODE);
				response.setResponseCode(TAErrorCodes.ERROR_GENERATING_ERROR_FILE_DESCRIPTION);
				logger.error("Exception in validating and creating", exception);
			}
		}else {
			
			logger.info("Excel is uploaded No error found ******************************");
		}
		return response;
	}

	private Boolean populateErrorsInFile(CandidateDetails candidateDetail,
			Set<ConstraintViolation<CandidateDetails>> violations, List<CandidateDetails> errorCandidateList) {
		Boolean hasError = false;
		StringBuffer errorMessages = new StringBuffer(" ");

		for (ConstraintViolation<CandidateDetails> violation : violations) {
			hasError = true;
			errorMessages.append(" \n " + violation.getMessage() + "|");
		}
		if (hasError) {
			candidateDetail.setErrors(errorMessages);
			errorCandidateList.add(candidateDetail);
		}
		return hasError;
	}

	void sum(Object a) {
		System.out.println("in int");
	}

	void sum(Integer a) {
		System.out.println("in obj");
	}

}
