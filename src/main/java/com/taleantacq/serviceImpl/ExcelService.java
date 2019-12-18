package com.taleantacq.serviceImpl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.mail.MessagingException;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poiji.bind.Poiji;
import com.taleantacq.comon.TAErrorCodes;
import com.taleantacq.dao.ICandidateDao;
import com.taleantacq.domain.CandidateResponse;
import com.taleantacq.domain.ExcelFileRequest;
import com.taleantacq.jpa.Batch;
import com.taleantacq.jpa.CandidateDetails;
import com.taleantacq.service.IExcelService;
import com.taleantacq.util.EmailUtil;
import com.taleantacq.util.Excelutil;

@Service
public class ExcelService implements IExcelService {
	

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

	public CandidateResponse uploadExcel(File file,ExcelFileRequest details) {
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
			String email=candidateDaoImpl.getTaMailid(details.getId());
			response = validateFileAndCreateErrorFile(employees, errorCandidateList,email);
		} catch (MessagingException e) {
			logger.error("Excetion in reading excel ", e);
			response.setResponseCode(TAErrorCodes.EXCEL_VALIDATION_FAILED_CODE);
			response.setResponseDescription(TAErrorCodes.EXCEL_VALIDATION_FAILED_DESCRIPTION);
			return response;
		}

		if (response.getResponseCode().contentEquals(TAErrorCodes.SUCCESS)) {
			Batch batch = new Batch();
			batch.setBatchName(details.getBatchName());
			batch.setUploadedBy(details.getId());
			
			for (CandidateDetails candidateDetails : employees) {			
				candidateDetails.setBatch(batch);			
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
