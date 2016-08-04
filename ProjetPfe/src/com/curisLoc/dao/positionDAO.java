package com.curisLoc.dao;

import javax.persistence.EntityManager;
import javax.persistence.Query;


public class positionDAO {

	public void save(Integer id){
		EntityManager entityManager = JPAUtil.geEntityManager();
		
		entityManager.getTransaction().begin();
		Query query = entityManager.createNativeQuery("INSERT INTO Position(lat, lng, title,id_cabinet) values(?,?,?,"+id+")");
		
		entityManager.getTransaction().commit();
		entityManager.close();
	}
	
	
	
}
