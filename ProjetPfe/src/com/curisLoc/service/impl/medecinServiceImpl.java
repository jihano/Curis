package com.curisLoc.service.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.curisLoc.model.Medecin;
import com.curisLoc.service.medecinService;

@Service(value="medecinService")
@Transactional
public class medecinServiceImpl implements medecinService {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void save(Medecin medecin) {
		entityManager.merge(medecin);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Medecin> listTotal() {
		return entityManager.createQuery("FROM Medecin").getResultList();
	}

}
