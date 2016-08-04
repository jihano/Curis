package com.curisLoc.bean;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.curisLoc.Util.Mensagem;
import com.curisLoc.dao.GenericDAO;
import com.curisLoc.exceptions.LoginRepeterException;
import com.curisLoc.model.EmploiTp;
import com.curisLoc.model.Medecin;
import com.curisLoc.model.jour;





@Controller("emploiTempBean")
@Scope("view")
@ManagedBean
public class EmploiTempBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8724581278116527259L;
	
	private EmploiTp ET;
	private List<jour> jours = Arrays.asList(jour.values());
	private List<EmploiTp> listET;
	private EmploiTp emSupp;
	private List<EmploiTp> emFiltrer;
	
	public void iniciarBean(){
		listET= new GenericDAO<EmploiTp>(EmploiTp.class).listTotal();
		
	}
	public void nouveauET(){
		ET = new EmploiTp();
	}

	public void save(){
		try {
			new GenericDAO<EmploiTp>(EmploiTp.class).save(ET);
			listET = new GenericDAO<EmploiTp>(EmploiTp.class).listTotal();
			ET= null;
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("sauvgardage avec succee"));
			Mensagem.mensagemInformacao("user enregister!");
		} catch (LoginRepeterException e) {
			Mensagem.mensagemErro(e.getMessage());
		}

	
	}
	
			public void update(EmploiTp emtp){
						
						this.ET= emtp;
						
				}
			
			public void preparerSuppression(EmploiTp emtp){
				this.emSupp=emtp;
				
			}
			public void delete(){
				new GenericDAO<EmploiTp>(EmploiTp.class).delete(emSupp);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("supprier avec succée !"));
				listET = new GenericDAO<EmploiTp>(EmploiTp.class).listTotal();
				emFiltrer= null;
				
			}
			public String getDateActual(){
				return new SimpleDateFormat("dd/MM/yyyy").format(new Date());
			}
			
			public void cancel(){
				ET= null;
			}
			public EmploiTp getET() {
				return ET;
			}
			public void setET(EmploiTp eT) {
				ET = eT;
			}
			public List<jour> getJours() {
				return jours;
			}
			public void setJours(List<jour> jours) {
				this.jours = jours;
			}
			public List<EmploiTp> getListET() {
				return listET;
			}
			public void setListET(List<EmploiTp> listET) {
				this.listET = listET;
			}
			public EmploiTp getEmSupp() {
				return emSupp;
			}
			public void setEmSupp(EmploiTp emSupp) {
				this.emSupp = emSupp;
			}
			public List<EmploiTp> getEmFiltrer() {
				return emFiltrer;
			}
			public void setEmFiltrer(List<EmploiTp> emFiltrer) {
				this.emFiltrer = emFiltrer;
			}
			
	
}
