package com.curisLoc.service;

import java.util.List;

import com.curisLoc.model.operation;

public interface operationService {
	public void save(operation operation);
	public List<operation> listTotal();
	public void delete(operation op);

}
