package com.curisLoc.bean;

import javax.faces.bean.ManagedBean;

import com.curisLoc.dao.operationDAO;
import com.curisLoc.model.operation;
import com.curisLoc.service.operationService;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller("operationBean")
@Scope("view")
public class operationBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6248557424119956854L;
	
	private operation operation;
	private List<operation> operations = new ArrayList<operation>();
	private operation operationSupp;
	private List<operation> operationFilter;
	
	@Autowired
	private operationService opService;
	
	public void iniciarBean(){
		operations= opService.listTotal();

	}
	
	public void nouvelleOperation(){
		operation = new operation();
	}
	
	public void edit(operation operation){
		this.operation=operation;
	}
	public void save(){
		opService.save(operation);
		operations= opService.listTotal();
		operation= null;
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("operation enregister"));
	
	}

	public void preparerSuppression(operation op){
		this.operationSupp=op;
	}
	
	public void delete(){
		opService.delete(operationSupp);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("operation supprimer"));
		operations = opService.listTotal();
		operationFilter = null;
		
	}
	
	public void cancel(){
		operation= null;
	}
	
	public operation getOperationSupp() {
		return operationSupp;
	}

	public void setOperationSupp(operation operationSupp) {
		this.operationSupp = operationSupp;
	}

	public String getDateActuel(){
		return new SimpleDateFormat("dd/MM/yyyy").format(new Date());
	}
	
	public List<operation> getOperations() {
		return operations;
	}


	public void setOperations(List<operation> operations) {
		this.operations = operations;
	}


	public operation getOperation() {
		return operation;
	}

	public void setOperation(operation operation) {
		this.operation = operation;
	}

	public List<operation> getOperationFilter() {
		return operationFilter;
	}

	public void setOperationFilter(List<operation> operationFilter) {
		this.operationFilter = operationFilter;
	}
	
	
	
}
