package com.curisLoc.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class GenericDAO<T> {

	private final Class<T> classe;
	
	public GenericDAO(Class<T> classe){
		this.classe = classe;
	} 
	
	public void save(T t){
		EntityManager em = JPAUtil.geEntityManager();
		em.getTransaction().begin();
		em.merge(t);
		em.getTransaction().commit();
		em.close();
	}

	public void delete(T t){
		EntityManager em = JPAUtil.geEntityManager();
		em.getTransaction().begin();
		em.remove(t);
		em.getTransaction().commit();
		em.close();
		
		
	}
	
	public List<T> listTotal(){
		EntityManager em= JPAUtil.geEntityManager();
		
		List<T> resultados = em.createQuery("SELECT c FROM " + classe.getName() + " c ").getResultList();
		
		 em.close();
		
		return resultados;
		 
	}
	public int count(){
		EntityManager em= JPAUtil.geEntityManager();
		
		int resultados = (Integer) em.createQuery("SELECT count(*) c FROM " + classe.getName() + " c ").getSingleResult();
		
		 em.close();
		
		return resultados;
		 
	}
	 public T selectParId(Integer id){
			EntityManager em= JPAUtil.geEntityManager();
			
			Query query = em.createQuery("FROM "+ classe.getName() + " E WHERE E.id = :cod", classe);
			query.setParameter("cod", id);
			T t = (T) query.getSingleResult();
			em.close();
			
			return t;
	 
	 }
	
}
