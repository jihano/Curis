package com.curisLoc.service.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.curisLoc.model.User;
import com.curisLoc.model.UserClinique;
import com.curisLoc.service.UserCliniqueService;
import com.curisLoc.service.UserService;
import com.curisLoc.service.envoiEmailService;
import com.curisLoc.Util.GenererPasswordAleatoire;
import com.curisLoc.exceptions.LoginRepeterException;

@Service
@Transactional
public class UserCliniqueServiceImpl implements UserCliniqueService {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private UserService userService;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private envoiEmailService ees;
	
	@Override
	public void save(UserClinique uc) {
		
		uc.setLogin(uc.getEmail());
		User userLogged= userService.obterUsuarioPeloLogin(uc.getLogin());
		if(userLogged != null && !userLogged.getId().equals(uc.getId())){
			throw new LoginRepeterException("email existe déja");
		}
		
		if(uc.getId() == null){
			String  passwordGenerer = GenererPasswordAleatoire.genererPasswordAleatoire(6);
			//TODO - criptation dial mot de passe
			System.out.println(passwordGenerer);
			String passwordCriptationgraf = passwordEncoder.encodePassword(passwordGenerer, null);
			uc.setSenha(passwordCriptationgraf);
			//TODO - nsayfto mot de passe n email 
			ees.envoiEmailRegisterUserClinique(uc, passwordGenerer);
		}
		entityManager.merge(uc);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserClinique> listTotal() {
		// TODO Auto-generated method stub
			return entityManager.createNamedQuery(UserClinique.LIST_TOTAL).getResultList();
	}

}
