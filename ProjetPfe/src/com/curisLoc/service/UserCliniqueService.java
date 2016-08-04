package com.curisLoc.service;

import java.util.List;

import com.curisLoc.model.UserClinique;

public interface UserCliniqueService {

	
	public void save(UserClinique uc);
	public List<UserClinique> listTotal();
}
