package com.curisLoc.bean;

import java.io.Serializable;
import java.security.Principal;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.chart.PieChartModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.security.core.Authentication;
import com.curisLoc.Util.Mensagem;
import com.curisLoc.dao.GenericDAO;
import com.curisLoc.exceptions.LoginRepeterException;
import com.curisLoc.model.Medecin;
import com.curisLoc.model.UserClinique;
import com.curisLoc.service.UserCliniqueService;

@Controller("userCliniqueBean")
@Scope("session")
@ManagedBean
public class UserCliniqueBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7846598653303831857L;
	
	private PieChartModel pieModel;
    
	
	private UserClinique userClinique;
	private List<UserClinique> usersClinique;
	
	@Autowired
	private UserCliniqueService ucs;
	
	public void iniciarBean(){
		
		actualiserListUsers();
		createPieModels();
	}
	private void createPieModels() {
		 GenericDAO<UserClinique> dao;
		 List<UserClinique> usersClinique;

	        try {
	            dao = new GenericDAO<UserClinique>(UserClinique.class);
	            usersClinique = dao.listTotal();
	            graficar(usersClinique);
	        } catch (Exception e) {
	            //Logica de excepcion y/o mensajes de error
	        } finally {
	            //Logica de cierre
	        }
     
    }
	
	private void graficar(List<UserClinique> usersClinique) {
		pieModel = new PieChartModel();

        for (UserClinique pro : usersClinique) {
            pieModel.set(pro.getNom(), pro.getId());
        }
        pieModel.setTitle("");
        pieModel.setLegendPosition("e");
        pieModel.setFill(false);
        pieModel.setShowDataLabels(true);
        pieModel.setDiameter(150);
	}
	
	public void nouvelleClinique(){
		userClinique = new UserClinique();
	
	}
	
	public void save(){
		try {
			ucs.save(userClinique);
			actualiserListUsers();
			userClinique = null;
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("sauvgardage avec succee"));
			Mensagem.mensagemInformacao("user enregister!");
		} catch (LoginRepeterException e) {
			Mensagem.mensagemErro(e.getMessage());
		}
	}
	
	 @RequestMapping(value = "/username", method = RequestMethod.GET)
	    @ResponseBody
	    public String currentUserName(Authentication authentication) {
	        return authentication.getName();
	    }
	
	public void edit(UserClinique uc){
		this.userClinique= uc;
		
	}
	
	public void cancel(){
		this.userClinique= null;
	}
	
	private void actualiserListUsers(){
		usersClinique = ucs.listTotal();
	}
	public StreamedContent getImageCabinet(){
		Map<String, String> mapaParametros = FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap();
		String idAluno = mapaParametros.get("id_C");
		if(idAluno != null){
			UserClinique alunoBanco = new GenericDAO<UserClinique>(UserClinique.class).selectParId(new Integer(idAluno));
			return alunoBanco.getImage();
		}
		
		return new DefaultStreamedContent();
	}
	public void enviarFoto(FileUploadEvent event){
		try {
			byte[] foto = IOUtils.toByteArray(
					event.getFile().getInputstream());
			userClinique.setPhoto(foto);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public UserClinique getUserClinique() {
		return userClinique;
	}

	public void setUserClinique(UserClinique userClinique) {
		this.userClinique = userClinique;
	}

	public List<UserClinique> getUsersClinique() {
		return usersClinique;
	}

	public void setUsersClinique(List<UserClinique> usersClinique) {
		this.usersClinique = usersClinique;
	}

	public UserCliniqueService getUcs() {
		return ucs;
	}

	public void setUcs(UserCliniqueService ucs) {
		this.ucs = ucs;
	}
	public PieChartModel getPieModel() {
		return pieModel;
	}
	public void setPieModel(PieChartModel pieModel) {
		this.pieModel = pieModel;
	}
	
	
	
}
