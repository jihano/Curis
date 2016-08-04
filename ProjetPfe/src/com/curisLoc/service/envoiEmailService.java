package com.curisLoc.service;

import java.io.File;
import java.util.List;

import com.curisLoc.model.UserClinique;
import com.curisLoc.model.UserMedecin;

public interface envoiEmailService {
	
public void envoiEmail(String sujet, String texte,List<File> attachement,
		String... recipients);
public void envoiEmailRegisterUserClinique(UserClinique uc,
		String password);

public void envoiEmailRegisterUserMedecin(UserMedecin um,
		String password);

}
