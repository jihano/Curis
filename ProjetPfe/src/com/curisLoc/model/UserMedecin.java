package com.curisLoc.model;

import java.io.ByteArrayInputStream;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@Entity

@NamedQueries({
	@NamedQuery(name = UserMedecin.LIST_TOTAL_PAR_CABINET, query = UserMedecin.LIST_TOTAL_PAR_CABINET),
	@NamedQuery(name = UserMedecin.LIST_TOTAL, query = UserMedecin.LIST_TOTAL )
})
public class UserMedecin extends User {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1042565664887967920L;

	public static final String LIST_TOTAL = "FROM UserMedecin";

	public static final String LIST_TOTAL_PAR_CABINET = "select medecins from UserClinique where id = :idClinique";

	private Date dateN;
	private String CIN;
	private String NumTel;
	@Lob
	private byte[] photo;
	@Transient
	private StreamedContent image;
	@ManyToOne
	@JoinColumn(name = "id_specialite")
	private Specialite specialite;
	@ManyToOne
	@JoinColumn(name ="id_cabinet")
	private UserClinique cabinet;
	
	
	
	public UserClinique getCabinet() {
		return cabinet;
	}
	public void setCabinet(UserClinique cabinet) {
		this.cabinet = cabinet;
	}
	@Temporal(TemporalType.DATE)
	public Date getDateN() {
		return dateN;
	}
	public void setDateN(Date dateN) {
		this.dateN = dateN;
	}
	public String getCIN() {
		return CIN;
	}
	public void setCIN(String cIN) {
		CIN = cIN;
	}
	
	public String getNumTel() {
		return NumTel;
	}
	public void setNumTel(String numTel) {
		NumTel = numTel;
	}
	
	
	public byte[] getPhoto() {
		return photo;
	}
	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}
	
	
	public StreamedContent getImage() {
		if(this.getPhoto() != null){
			return new DefaultStreamedContent(
					new ByteArrayInputStream(this.getPhoto()));
		}
		
		return new DefaultStreamedContent();
	}

		
		
	public void setImage(StreamedContent image) {
		this.image = image;
	}
	
	public Specialite getSpecialite() {
		return specialite;
	}
	public void setSpecialite(Specialite specialite) {
		this.specialite = specialite;
	}
	public UserMedecin(Date dateN, String cIN, String numTel, byte[] photo, StreamedContent image,
			Specialite specialite) {
		super();
		this.dateN = dateN;
		CIN = cIN;
		NumTel = numTel;
		this.photo = photo;
		this.image = image;
		this.specialite = specialite;
	}
	public UserMedecin() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
