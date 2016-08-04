package com.curisLoc.service;

import java.util.List;

import com.curisLoc.model.UserMedecin;

public interface UserMedecinService {
	
	public void save(UserMedecin um);
	public List<UserMedecin> listeTotal();
	public List<UserMedecin> listeTotalParCabinet(Integer idClinique);

}
