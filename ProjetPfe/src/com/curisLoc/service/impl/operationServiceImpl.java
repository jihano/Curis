package com.curisLoc.service.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.curisLoc.model.operation;
import com.curisLoc.service.operationService;

@Service(value="operationService")
@Transactional
public class operationServiceImpl implements operationService {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void save(operation operation) {
			entityManager.merge(operation);		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<operation> listTotal() {
		// TODO Auto-generated method stub
		return entityManager.createQuery("FROM operation").getResultList();
	}

	@Override
	public void delete(operation op) {
		op= entityManager.merge(op);
		entityManager.remove(op);
	}

}
