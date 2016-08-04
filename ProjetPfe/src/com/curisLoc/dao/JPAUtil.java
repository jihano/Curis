package com.curisLoc.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {

	public static EntityManager geEntityManager(){
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("ProjetPfe");
		EntityManager em= factory.createEntityManager();
	return em;
	}
}
