package com.taleantacq.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.taleantacq.domain.BatchSessionAssignRequest;
import com.taleantacq.domain.SessionRequest;
import com.taleantacq.jpa.Batch;
import com.taleantacq.jpa.Session;

@Component
public interface BatchSessionDao {

	List<Batch> getAllBatches(int id);

	int deleteBatchesById(int id);

	void createSession(SessionRequest sessionRequest);

	List<Session> getAllSessions(int id);
	
	int deleteSessionById(int id);

	void allocateTA(SessionRequest sessionRequest);

	void deallocateTA(SessionRequest sessionRequest);

	void assignTA(BatchSessionAssignRequest request);

}
