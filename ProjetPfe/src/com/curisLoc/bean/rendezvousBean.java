package com.curisLoc.bean;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.curisLoc.dao.GenericDAO;
import com.curisLoc.model.EmploiTp;
import com.curisLoc.model.rendezvous;
@Controller("rendezvousBean")
@Scope("view")
@ManagedBean
public class rendezvousBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7759364884278870467L;
	private rendezvous rv;
	private rendezvous rvSelectione;
	private List<rendezvous> listerv;
	private rendezvous rvSupp;
	private List<rendezvous> rvFiltrer;
	
	public void iniciarBean(){
		listerv= new GenericDAO<rendezvous>(rendezvous.class).listTotal();
		
	}
	
	public void preparerSuppression(rendezvous rv){
		this.rvSupp=rv;
		
	}
	public void delete(){
		new GenericDAO<rendezvous>(rendezvous.class).delete(rvSupp);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Rendez vous Annuler avec succee!"));
		listerv = new GenericDAO<rendezvous>(rendezvous.class).listTotal();
		rvFiltrer= null;
		
	}
	
	public String getDateActual(){
		return new SimpleDateFormat("dd/MM/yyyy").format(new Date());
	}
	
	public int count(){
		int count = new GenericDAO<rendezvous>(rendezvous.class).count();
		return count;
	}

	public rendezvous getRv() {
		return rv;
	}

	public void setRv(rendezvous rv) {
		this.rv = rv;
	}

	public List<rendezvous> getListerv() {
		return listerv;
	}

	public void setListerv(List<rendezvous> listerv) {
		this.listerv = listerv;
	}

	public rendezvous getRvSupp() {
		return rvSupp;
	}

	public void setRvSupp(rendezvous rvSupp) {
		this.rvSupp = rvSupp;
	}

	public List<rendezvous> getRvFiltrer() {
		return rvFiltrer;
	}

	public void setRvFiltrer(List<rendezvous> rvFiltrer) {
		this.rvFiltrer = rvFiltrer;
	}

	public rendezvous getRvSelectione() {
		return rvSelectione;
	}

	public void setRvSelectione(rendezvous rvSelectione) {
		this.rvSelectione = rvSelectione;
	}

	
}
