package com.curisLoc.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.curisLoc.model.operation;
import com.curisLoc.dao.JPAUtil;



public class operationDAO {

	public void save(operation operation){
		EntityManager entityManager = JPAUtil.geEntityManager();
		
		entityManager.getTransaction().begin();
		entityManager.merge(operation);
		entityManager.getTransaction().commit();
		entityManager.close();
	}
	
	@SuppressWarnings("unchecked")
	public List<operation> listTotal(){
		EntityManager entityManager = JPAUtil.geEntityManager();
		
		Query query = entityManager.createQuery("SELECT o FROM operation o ");
		return query.getResultList();
	}
	
	public void delete(operation op){
		EntityManager entityManager = JPAUtil.geEntityManager();
				
				entityManager.getTransaction().begin();
				
				op = entityManager.merge(op);
				entityManager.remove(op);
				entityManager.getTransaction().commit();
				entityManager.close();
			}
	
}
