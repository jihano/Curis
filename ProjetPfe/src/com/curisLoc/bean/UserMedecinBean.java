package com.curisLoc.bean;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.curisLoc.Util.Mensagem;
import com.curisLoc.authentification.AuthenticationContext;
import com.curisLoc.dao.GenericDAO;
import com.curisLoc.dao.userMedDAO;
import com.curisLoc.exceptions.LoginRepeterException;
import com.curisLoc.model.Medecin;
import com.curisLoc.model.Specialite;
import com.curisLoc.model.UserClinique;
import com.curisLoc.model.UserMedecin;
import com.curisLoc.service.UserMedecinService;
@Controller("userMedecinBean")
@Scope("session")
@ManagedBean
public class UserMedecinBean implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6333938430672379861L;
	/**
	 * 
	 */
	
	@Autowired
	private UserMedecinService ums;
	public Integer idcu= AuthenticationContext.getUsuarioLogado().getId();
	
	private UserMedecin userMedecin;
	private List<UserMedecin> usersMedecin;
	private List<Specialite>  specialite;
	
	public void iniciarBean(){
		
		//userMedecin= new UserMedecin();
		//usersMedecin= ums.listeTotal();
		usersMedecin= new userMedDAO().listTotal(idcu);
		specialite= new GenericDAO<Specialite>(Specialite.class).listTotal();
	}
	
	public void newUserMedecin(){
		userMedecin= new UserMedecin();
	}
	
	
	public void save(){
		try {
			ums.save(userMedecin);
			//new userMedDAO().save(userMedecin);
			userMedecin=null;
			usersMedecin= new userMedDAO().listTotal(idcu);
			Mensagem.mensagemInformacao("Médecin Ajouter!");
		} catch (LoginRepeterException e) {
			Mensagem.mensagemErro(e.getMessage());
		}
	}
	public void actualiser(){
		usersMedecin= ums.listeTotal();
	}
	public void edit(UserMedecin um){
		this.userMedecin= um;
		
	}
	
	public void cancel(){
		this.userMedecin= null;
	}

	public StreamedContent getImageMedecin(){
		Map<String, String> mapaParametros = FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap();
		String idAluno = mapaParametros.get("id_Medecin");
		if(idAluno != null){
			UserMedecin alunoBanco = new GenericDAO<UserMedecin>(UserMedecin.class).selectParId(new Integer(idAluno));
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
			userMedecin.setPhoto(foto);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	public UserMedecin getUserMedecin() {
		return userMedecin;
	}

	public void setUserMedecin(UserMedecin userMedecin) {
		this.userMedecin = userMedecin;
	}

	public List<UserMedecin> getUsersMedecin() {
		return usersMedecin;
	}

	public void setUsersMedecin(List<UserMedecin> usersMedecin) {
		this.usersMedecin = usersMedecin;
	}

	public List<Specialite> getSpecialite() {
		return specialite;
	}

	public void setSpecialite(List<Specialite> specialite) {
		this.specialite = specialite;
	}
	
	
}
