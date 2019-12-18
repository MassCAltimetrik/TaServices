package com.taleantacq.dao.impl;

import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.taleantacq.dao.BatchSessionDao;
import com.taleantacq.domain.BatchSessionAssignRequest;
import com.taleantacq.domain.SessionRequest;
import com.taleantacq.jpa.Batch;
import com.taleantacq.jpa.CandidateDetails;
import com.taleantacq.jpa.Session;

@Component
public class BatchSessionDaoImpl implements BatchSessionDao{

	private static final Logger logger = LoggerFactory.getLogger(BatchSessionDaoImpl.class);
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Batch> getAllBatches(int id) {
		List<Batch> list;
		try {
			Query query=entityManager.createQuery("select b FROM Batch b where b.assigned_to is NULL and b.uploadedBy="+id,Batch.class);

			list=query.getResultList();

			if(null!=list && !list.isEmpty()) 
				return list;
		}
		catch(Exception e) {
		}
		return null;
	}

	@Transactional
	public int deleteBatchesById(int id) {
		Query query=entityManager.createNativeQuery("delete cd,b from candidate_details cd inner join batch  b on cd.batch_id = b.batch_id where cd.session_id IS NULL and b.batch_id = "+id);
		return query.executeUpdate();
	}

	@Transactional
	public void createSession(SessionRequest sessionRequest) {
		Session s = new Session();
		s.setsessionName(sessionRequest.getSessionName());
		s.setUploadedBy(sessionRequest.getTaId());
		s.setDate(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
		entityManager.persist(s);
		
		for(Integer id:sessionRequest.getCandidates()) {
			CandidateDetails emp = entityManager.find(CandidateDetails.class,id);
			emp.setSession(s);
		}
	}

	@Transactional	
	public List<Session> getAllSessions(int id) {
		List<Session> list;
		try {
			Query query=entityManager.createQuery("select s FROM Session s where s.uploadedBy="+id,Session.class);
			list=query.getResultList();
			if(null!=list && !list.isEmpty()) 
				return list;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Transactional
	public int deleteSessionById(int id) {
		Query query=entityManager.createNativeQuery("update candidate_details cd set cd.session_id = NULL where cd.session_id = "+id);
		query.executeUpdate();
		
		query=entityManager.createNativeQuery("delete from session where session_id = "+id);
		return query.executeUpdate();

	}

	@Transactional
	public void allocateTA(SessionRequest sessionRequest) {
		Query query=entityManager.createNativeQuery("update ta_info set admin =:adminId where ta_id=:taId");
		query.setParameter("adminId",sessionRequest.getTaId());
		for(Integer id:sessionRequest.getCandidates()) {
			query.setParameter("taId",id);
			query.executeUpdate();
		}
		
	}
	
	@Transactional
	public void deallocateTA(SessionRequest sessionRequest) {
		Query query=entityManager.createNativeQuery("update ta_info set admin =NULL where ta_id=:taId");
		for(Integer id:sessionRequest.getCandidates()) {
			query.setParameter("taId",id);
			query.executeUpdate();
		}
	}

	@Transactional
	public void assignTA(BatchSessionAssignRequest request) {
		Query query;
		if(request.getType().equals("Batch")) 
			query=entityManager.createNativeQuery("update batch set assigned_to=:taId where batch_id=:id");
		
		else
			query=entityManager.createNativeQuery("update session set assigned_to=:taId where session_id=:id");
		
		query.setParameter("taId",request.getSelectedTaId());
		query.setParameter("id",request.getId());
		query.executeUpdate();
		
	}
}
