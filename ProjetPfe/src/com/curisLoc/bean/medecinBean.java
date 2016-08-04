package com.curisLoc.bean;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.curisLoc.dao.GenericDAO;
import com.curisLoc.model.Medecin;
import com.curisLoc.service.medecinService;


@Controller("medecinBean")
@Scope("session")
public class medecinBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2346035274973321545L;
	
	private Medecin medecin;
	private List<Medecin> medecins;
	
	@Autowired
	private medecinService medService;

	public void iniciarBean(){
		medecins =medService.listTotal();
		
	}

	public void nouveauMedecin(){
		medecin = new Medecin();
	}
	
	public void save(){
		medService.save(medecin);
	
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("sauvgardage avec succee"));
		medecins = medService.listTotal();
		medecin = null;
	}
	
	
	public void voltar(){
		this.medecin= null;
	}
	//edit
	public void update(Medecin med){
		
		this.medecin= med;
		
	}

	public StreamedContent getImageMedecin(){
		Map<String, String> mapaParametros = FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap();
		String idAluno = mapaParametros.get("id_Medecin");
		if(idAluno != null){
			Medecin alunoBanco = new GenericDAO<Medecin>(Medecin.class).selectParId(new Integer(idAluno));
			return alunoBanco.getImage();
		}
		
		return new DefaultStreamedContent();
	}
	public String dateActual(){
		return new SimpleDateFormat("dd/MM/yyyy").format(new Date());
	}
	
	public void enviarFoto(FileUploadEvent event){
		try {
			byte[] foto = IOUtils.toByteArray(
					event.getFile().getInputstream());
			medecin.setPhoto(foto);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public Medecin getMedecin() {
		return medecin;
	}

	public void setMedecin(Medecin medecin) {
		this.medecin = medecin;
	}

	public List<Medecin> getMedecins() {
		return medecins;
	}

	public void setMedecins(List<Medecin> medecins) {
		this.medecins = medecins;
	}
	

}
