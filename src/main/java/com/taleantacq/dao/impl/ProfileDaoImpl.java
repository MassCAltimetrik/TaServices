package com.taleantacq.dao.impl;

import java.util.HashSet;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.taleantacq.comon.TAErrorCodes;
import com.taleantacq.dao.IProfileDao;
import com.taleantacq.domain.ProfileResponse;
import com.taleantacq.jpa.UserInfo;


@Transactional
@Component
public class ProfileDaoImpl  implements IProfileDao{
	
	 private static final Logger logger = LoggerFactory.getLogger(ProfileDaoImpl.class);
	  
	@PersistenceContext
	private EntityManager entityManager;
	
	@Transactional
	public ProfileResponse createTaAccount(UserInfo tajpa){
		ProfileResponse profileResponse=new ProfileResponse();
		try {
		
		entityManager.persist(tajpa);
		profileResponse.setResponseCode(TAErrorCodes.SUCCESS);
		}catch(Exception exception) {
			logger.error("Exception in persisting  ta registration data", exception);
			profileResponse.setResponseCode(TAErrorCodes.TA_REGISTRATION_FAILED_CODE);
			profileResponse.setResponseDescription(TAErrorCodes.TA_REGISTRATION_FAILED_DESCRIPTION);
		}
		return profileResponse;
				
	}

	@Transactional
	public ProfileResponse loginTaAccount(UserInfo taJpa) {
		logger.debug("login TaAccount");
		ProfileResponse profileResponse=new ProfileResponse();
		try {
			List<UserInfo> list;
			Query query=entityManager.createQuery("select userInfo from UserInfo userInfo where userInfo.email=:email and userInfo.password=:password",UserInfo.class);
			query.setParameter("email", taJpa.getEmail());
			query.setParameter("password", taJpa.getPassword());
			list=query.getResultList();
			logger.info("TA account list fetced for login "+list);
			if(list==null ) {
				profileResponse.setResponseCode(TAErrorCodes.INVALID_LOGIN_CREDENTAILS_CODE);
				profileResponse.setResponseDescription(TAErrorCodes.INVALID_LOGIN_CREDENTAILS_DESCRIPTION);
			}
			profileResponse.setTaID(list.get(0).getTaId().toString());
			profileResponse.setTaName(list.get(0).getFirstName()+" "+list.get(0).getLastName());
			profileResponse.setRole(list.get(0).getRole().getRole());
		}catch(Exception exception) {
			logger.error("Exception in login ta ",exception);
			profileResponse.setResponseCode(TAErrorCodes.SOMETHING_WENT_WRONG_CODE);
			profileResponse.setResponseDescription(TAErrorCodes.SOMETHING_WENT_WRONG_DESCRIPTION);
		}
		return profileResponse;
	}

	public ProfileResponse checkIfEmailAlreadyExist(String emailID) {
		ProfileResponse profileResponse=new ProfileResponse();
		Query query=entityManager.createQuery("select userInfo from UserInfo userInfo where userInfo.email=:email ",UserInfo.class);
		query.setParameter("email", emailID);
		List<UserInfo>talist=query.getResultList();
		if(null!=talist && !talist.isEmpty()) {
			profileResponse.setResponseCode(TAErrorCodes.ACCOUNT_ALREADY_EXIST_CODE);
			profileResponse.setResponseDescription(TAErrorCodes.ACCOUNT_ALREADY_EXIST_DESCRIPTION);
		}
		return profileResponse;
	}

	public List<UserInfo> getUnassingedTaData() {
		Query query=entityManager.createQuery("select userInfo from UserInfo userInfo where userInfo.admin is NULL and userInfo.role = 2",UserInfo.class);
		List<UserInfo>talist=query.getResultList();
		
		return talist;
	}

	public List<UserInfo> getTaData(Integer adminId) {
		Query query=entityManager.createQuery("select userInfo from UserInfo userInfo where userInfo.admin.taId =:admin",UserInfo.class);
		query.setParameter("admin", adminId);
		List<UserInfo>talist=query.getResultList();
		return talist;
	}
}
