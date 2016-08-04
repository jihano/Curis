package com.curisLoc.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.curisLoc.model.UserMedecin;

public class userMedDAO {
	
	public void save(UserMedecin um){
		EntityManager entityManager = JPAUtil.geEntityManager();
		
		entityManager.getTransaction().begin();
		entityManager.merge(um);
		entityManager.getTransaction().commit();
		entityManager.close();
	}
	
	@SuppressWarnings("unchecked")
	public List<UserMedecin> listTotal(Integer id){
		EntityManager entityManager = JPAUtil.geEntityManager();
		
		Query query = entityManager.createQuery("SELECT o FROM UserMedecin o where id_cabinet= :id ");
		return query.setParameter("id", id).getResultList();
	}
	

}
