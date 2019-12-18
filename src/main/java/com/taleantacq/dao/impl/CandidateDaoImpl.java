package com.taleantacq.dao.impl;

import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.apache.coyote.http11.Http11AprProtocol;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.taleantacq.comon.TAConstants;
import com.taleantacq.comon.TAErrorCodes;
import com.taleantacq.dao.ICandidateDao;
import com.taleantacq.domain.CallStaticsResponse;
import com.taleantacq.domain.CandidateResponse;
import com.taleantacq.domain.ProfileResponse;
import com.taleantacq.jpa.CallLogDetails;
import com.taleantacq.jpa.CandidateDetails;
import com.taleantacq.jpa.UserInfo;


@Transactional
@Component
public class CandidateDaoImpl implements ICandidateDao {

	private static final Logger logger = LoggerFactory.getLogger(CandidateDaoImpl.class);

	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Transactional
	public CandidateResponse getCandidateDetails(int taID,int pageNumber) throws SQLException {
		CandidateResponse response=new CandidateResponse();
		List<CandidateDetails> list;
		try {
			Query query=entityManager.createQuery("select c FROM CandidateDetails c where c.taId="+taID,CandidateDetails.class);

			if(pageNumber != 0) {
				int startIndex=(pageNumber-1)*TAConstants.FETCH_MAX_RESULTS;
				query.setFirstResult(startIndex);
				query.setMaxResults(TAConstants.FETCH_MAX_RESULTS);
			}
			logger.info("cadidate list query is"+query);
			list=query.getResultList();

			if(null!=list && !list.isEmpty()) {
				response.setCandidateList(list);
			}else {
				response.setResponseCode(TAErrorCodes.CANDIDATE_LIST_EMPTY_CODE);
				response.setResponseDescription(TAErrorCodes.CANDIDATE_LIST_EMPTY_DESCRIPTION);
			}
			logger.info("cadidate list is"+list);
		}catch(Exception exception) {
			logger.error("Exception in fetching cadidate details ",exception);
			response.setResponseCode(TAErrorCodes.SOMETHING_WENT_WRONG_CODE);
			response.setResponseDescription(TAErrorCodes.SOMETHING_WENT_WRONG_DESCRIPTION);
		}
		return response;

	}

	public CallStaticsResponse getcallStatistics(Integer taID) {
		List <CallLogDetails>callStatsList;
		CallStaticsResponse response = new CallStaticsResponse();
		try {
			Query query=entityManager.createQuery("select calllog from CallLogDetails calllog where calllog.taId="+taID+" order by calllog.callDate desc",CallLogDetails.class);
			query.setMaxResults(1);
			callStatsList=query.getResultList();
			
			 StringBuffer candidateName=new StringBuffer();
			callStatsList.forEach(callDetails -> {
				response.setLastCalldate(callDetails.getCallDate());
				response.setLastCallduration(callDetails.getCallDuration());
				
				
				if(callDetails.getCandidateId().getFirstName() != null) {
					candidateName.append(callDetails.getCandidateId().getFirstName());
					
				}
				
				if(callDetails.getCandidateId().getLastName() != null) {
					candidateName.append(callDetails.getCandidateId().getLastName());
					
				}
				response.setLastCalledcandidateName(candidateName.toString());
				response.setLastCallNumber(callDetails.getCandidateId().getPrimaryMobileNumber());
			});

			query=entityManager.createQuery("select count(candidateId) from CallLogDetails where cast(callDate as date) = CURDATE()",Long.class);
			Long todaysNumberOfCalls=(Long) query.getSingleResult();
			logger.info("total number of calls made today: "+todaysNumberOfCalls);
			response.setTodaysNumberOfCalls(todaysNumberOfCalls.toString());

			query=entityManager.createQuery("select count(candidateId) from CandidateDetails where candidateId NOT IN (select candidateId from CallLogDetails where taId="+taID+") and taId="+taID,Long.class);
			Long remainingCalls=(Long) query.getResultList().get(0);
			logger.info("total number of calls pending : "+remainingCalls);
			response.setRemainingCallToMake(remainingCalls.toString());

			query=entityManager.createQuery("select count(cd.taId) from CallLogDetails cd where cd.taId="+taID,Long.class);
			Long totalCallsMade=(Long) query.getSingleResult();
			logger.info("total number of calls made : "+totalCallsMade);
			response.setTotalNumberOfCalls(totalCallsMade.toString());

		}catch(NoResultException exception) {
			logger.error("Record not found "+exception.getMessage());
		}
		catch(Exception  exception) {
			logger.error("Exception in getting statistics",exception);
		}
		return response;
	}

	@Transactional
	public ProfileResponse saveCallDetails(CallLogDetails callDetailsJpa) {
		ProfileResponse profileResponse = new ProfileResponse();
		try {
			CandidateDetails cd=entityManager.find(CandidateDetails.class, callDetailsJpa.getCandidateId().getCandidateId());
			
			if(callDetailsJpa.getCallDuration() !=null) {				
				System.out.println("setting no of calls ***************************");
				 Time t=callDetailsJpa.getCallDuration();
				 
				 System.out.println("hours =="+t.getHours());
				 System.out.println("min =="+t.getMinutes());
				 System.out.println("seconds =="+t.getSeconds());

					System.out.println("Time object ***************************"+t.toString());

				if(t.getHours()!=0 || t.getMinutes() !=0 || t.getSeconds() !=0) {					
					System.out.println("DB no of call == "+cd.getNoOfCalls());				
					cd.setNoOfCalls(cd.getNoOfCalls()+1);
				}
				
			}
			
			callDetailsJpa.setCandidateId(cd);

			logger.info("saving call details: "+callDetailsJpa);
			entityManager.persist(callDetailsJpa);

			profileResponse.setResponseCode(HttpStatus.CREATED.toString());
			profileResponse.setResponseDescription("Call Details Saved Successfully");
		}catch(Exception exception) {
			logger.error("Exception in persisting  call log data", exception);
			profileResponse.setResponseCode(TAErrorCodes.ERROR_SAVING_CALL_DETAILS_DESCRIPTION_CODE);
			profileResponse.setResponseDescription(TAErrorCodes.ERROR_SAVING_CALL_DETAILS_DESCRIPTION);
		}
		return profileResponse;
	}

	@Transactional
	public CandidateResponse saveCandidateDetails(List<CandidateDetails> employees) {
		CandidateResponse response=new CandidateResponse();
		try {
			//entityManager.merge(employees.get(0).getBatch());
			
			
			
			for(CandidateDetails candidate:employees) {	
				System.out.println(candidate.getBatch()+"   "+candidate);
				entityManager.persist(candidate);
			}
		}
		catch(Exception exception) {
			logger.error("Exceptin in saving candidate details",exception);
			response.setResponseCode(TAErrorCodes.ERROR_SAVING_CADIDATE_LIST_CODE);
			response.setResponseDescription(TAErrorCodes.ERROR_SAVING_CADIDATE_LIST_DESCRIPTION);

		}
		
		return response;
	}
	
	@Transactional
	@Override
	public Boolean getcalllogByCandidate(Integer candidateId) {
		CandidateResponse response=new CandidateResponse();
		List<CallLogDetails> list;
		Boolean dailed=false;
		try {
			Query query=entityManager.createQuery("select c FROM CallLogDetails c where c.candidateId="+candidateId,CallLogDetails.class);
		 
 			list=query.getResultList();

			if(null!=list && !list.isEmpty()) {
				 
				for (CallLogDetails callLogDetails : list) {
					if(callLogDetails.getIsCallRecieved() !=null) {
											
						if(callLogDetails.getIsCallRecieved()) {
							dailed=true;
						}else {
							dailed=false;
						}
					}
					
				}
			} 
		}catch(Exception exception) {
			logger.error("Exception in fetching cadidate details ",exception);
			response.setResponseCode(TAErrorCodes.SOMETHING_WENT_WRONG_CODE);
			response.setResponseDescription(TAErrorCodes.SOMETHING_WENT_WRONG_DESCRIPTION);
		}
		return dailed;

	}

	@Override
	public String getTaMailid(Integer taId) {
		 
		System.out.println("Taid =================== "+taId);
		
		try {
			Query query=entityManager.createQuery("select email from TaInfo tainfo where tainfo.taId=:taId",String.class);
			query.setParameter("taId", taId);

			String taobj=(String) query.getSingleResult();
			if(null!=taobj) {
				
				return taobj;
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

 
}
