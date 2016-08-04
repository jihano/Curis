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
import com.curisLoc.model.Cabinet;
import com.curisLoc.model.Medecin;


@ManagedBean
@Scope("view")
public class CabinetBean implements Serializable {

	private static final long serialVersionUID = 2346035274973321545L;
	
	private Cabinet cabinet;
	private List<Cabinet> cabinets;

	public void iniciarBean(){
		cabinets =new GenericDAO<Cabinet>(Cabinet.class).listTotal();
		
	}

	public void nouveauCabinet(){
		cabinet = new Cabinet();
	}
	
	public void save(){
		new GenericDAO<Cabinet>(Cabinet.class).save(cabinet);
	
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("sauvgardage avec succee"));
		cabinets = new GenericDAO<Cabinet>(Cabinet.class).listTotal();
		cabinet = null;
	}
	
	
	public void voltar(){
		this.cabinet= null;
	}
	//edit
	public void update(Cabinet cab){
		
		this.cabinet= cab;
		
	}

	public StreamedContent getImageCabinet(){
		Map<String, String> mapaParametros = FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap();
		String idAluno = mapaParametros.get("id_Medecin");
		if(idAluno != null){
			Cabinet alunoBanco = new GenericDAO<Cabinet>(Cabinet.class).selectParId(new Integer(idAluno));
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
			cabinet.setPhoto(foto);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Cabinet getCabinet() {
		return cabinet;
	}

	public void setCabinet(Cabinet cabinet) {
		this.cabinet = cabinet;
	}

	public List<Cabinet> getCabinets() {
		return cabinets;
	}

	public void setCabinets(List<Cabinet> cabinets) {
		this.cabinets = cabinets;
	}
	
	
	
}
