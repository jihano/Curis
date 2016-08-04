package com.curisLoc.service.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.curisLoc.Util.GenererPasswordAleatoire;
import com.curisLoc.exceptions.LoginRepeterException;
import com.curisLoc.model.User;
import com.curisLoc.model.UserMedecin;
import com.curisLoc.service.UserMedecinService;
import com.curisLoc.service.UserService;
import com.curisLoc.service.envoiEmailService;

@Service
@Transactional
public class UserMedecinServiceImpl implements UserMedecinService {

	@PersistenceContext
	private EntityManager entityManager;
	

	@Autowired
	private UserService userService;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private envoiEmailService ees;
	

	@Override
	public void save(UserMedecin um) {
		
		
		um.setLogin(um.getEmail());
		User userLogged= userService.obterUsuarioMedLogin(um.getLogin());
		if(userLogged != null && !userLogged.getId().equals(um.getId())){
			throw new LoginRepeterException("email existe déja");
		}
		
		
		if(um.getId() == null){
			String  passwordGenerer = GenererPasswordAleatoire.genererPasswordAleatoire(6);
			//TODO - criptation dial mot de passe
			System.out.println(passwordGenerer);
			String passwordCriptationgraf = passwordEncoder.encodePassword(passwordGenerer, null);
			um.setSenha(passwordCriptationgraf);
			//TODO - nsayfto mot de passe n email 
			ees.envoiEmailRegisterUserMedecin(um, passwordGenerer);
		}
		entityManager.merge(um);
	}



	@SuppressWarnings("unchecked")
	@Override
	public List<UserMedecin> listeTotal() {
		return entityManager.createNamedQuery(UserMedecin.LIST_TOTAL).getResultList();
	}



	@SuppressWarnings("unchecked")
	@Override
	public List<UserMedecin> listeTotalParCabinet(Integer idClinique) {
		return entityManager.createNamedQuery(UserMedecin.LIST_TOTAL_PAR_CABINET).
				setParameter("idClinique", idClinique).getResultList();
	}
	
	

}
