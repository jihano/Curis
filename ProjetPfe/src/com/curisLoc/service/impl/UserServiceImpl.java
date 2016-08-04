package com.curisLoc.service.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.curisLoc.model.User;
import com.curisLoc.service.UserService;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

	@PersistenceContext
	EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	@Override
	public User obterUsuarioMedLogin(String login) {
List<User> usuarios = entityManager.createQuery("FROM UserMedecin WHERE login = :login").setParameter("login", login).getResultList();
		
		if(usuarios.isEmpty()){
			return null;
		}
		
		return usuarios.get(0);
	}


	@SuppressWarnings("unchecked")
	@Override
	public User obterUsuarioPeloLogin(String login) {
		List<User> usuarios = entityManager.createQuery("FROM UserClinique WHERE login = :login").setParameter("login", login).getResultList();
		
		if(usuarios.isEmpty()){
			return null;
		}
		
		return usuarios.get(0);
	}


	
}