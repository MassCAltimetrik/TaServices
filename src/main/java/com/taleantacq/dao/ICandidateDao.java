package com.taleantacq.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import com.taleantacq.domain.CallStaticsResponse;
import com.taleantacq.domain.CandidateResponse;
import com.taleantacq.domain.ProfileResponse;
import com.taleantacq.jpa.CallLogDetails;
import com.taleantacq.jpa.CandidateDetails;

@Repository
public interface ICandidateDao {

	public CandidateResponse getCandidateDetails(int taID, int pageNumber) throws SQLException ;

	public CallStaticsResponse getcallStatistics(Integer taID) ;

	public ProfileResponse saveCallDetails(CallLogDetails callDetailsJpa) ;

	public CandidateResponse saveCandidateDetails(List<CandidateDetails> employees);

	public Boolean getcalllogByCandidate(Integer candidateId);

	public String getTaMailid(Integer taid);
}
