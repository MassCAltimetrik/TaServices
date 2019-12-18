package com.taleantacq.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.taleantacq.dao.BatchSessionDao;
import com.taleantacq.domain.BatchSessionAssignRequest;
import com.taleantacq.domain.SessionRequest;
import com.taleantacq.jpa.Batch;
import com.taleantacq.jpa.Session;

@RequestMapping("/batch")
@RestController
public class BatchController {
	private static final Logger logger = LoggerFactory.getLogger(BatchController.class);

	@Autowired
	BatchSessionDao batchSessionDaoImpl;

	@GetMapping(value = "/getBatchData/{id}")
	public List<Batch> getBatches(@PathVariable("id") int id) {
		return batchSessionDaoImpl.getAllBatches(id);
	}

	@DeleteMapping(value = "/deleteBatch/{id}")
	public ResponseEntity<Batch> deleteBatch(@PathVariable("id") int id) {
		logger.info("Deleting batch with id"+id);
		int rows = batchSessionDaoImpl.deleteBatchesById(id);
		return new ResponseEntity<Batch>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/createSesion", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ResponseEntity createAccount(@RequestBody SessionRequest sessionRequest) {
		System.out.println(sessionRequest);
			if (sessionRequest!=null) {
				batchSessionDaoImpl.createSession(sessionRequest);
				return new ResponseEntity<Session>(HttpStatus.CREATED);
			}
	
		return null;
	}
	
	@GetMapping(value = "/getSessionData/{id}")
	public List<Session> getSessions(@PathVariable("id") int id) {
		List<Session> list = batchSessionDaoImpl.getAllSessions(id); 
		
		if(list != null)
		logger.info("Returning Session list"+list.size());
		
		return list;
	}
	
	@DeleteMapping(value = "/deleteSession/{id}")
	public ResponseEntity<Session> deleteSession(@PathVariable("id") int id) {
		logger.info("Deleting Session with id"+id);
		int rows = batchSessionDaoImpl.deleteSessionById(id);
		return new ResponseEntity<Session>(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(value = "/allocateTA", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ResponseEntity allocateTA(@RequestBody SessionRequest sessionRequest) {
		System.out.println(sessionRequest);
			if (sessionRequest!=null) {
				batchSessionDaoImpl.allocateTA(sessionRequest);
				return new ResponseEntity<Session>(HttpStatus.CREATED);
			}
	
		return null;
	}
	
	@RequestMapping(value = "/assignBatchSessionTA", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ResponseEntity deallocateTA(@RequestBody BatchSessionAssignRequest request) {
		System.out.println(request);
			if (request!=null) {
				batchSessionDaoImpl.assignTA(request);
				return new ResponseEntity<Session>(HttpStatus.CREATED);
			}
	
		return null;
	}
}