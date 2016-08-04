package com.curisLoc.model;

import java.io.ByteArrayInputStream;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Transient;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import com.curisLoc.model.UserClinique;

@Entity
@NamedQueries({
	
@NamedQuery(name = UserClinique.LIST_TOTAL, query = UserClinique.LIST_TOTAL )
})
public class UserClinique extends User {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1490078862811419816L;

	public static final String LIST_TOTAL = "FROM UserClinique";

	
	private String logo;
	@Lob
	public byte[] photo;
	@Transient
	public StreamedContent image;
	@OneToMany(mappedBy="cabinet", fetch=FetchType.EAGER)
	private List<UserMedecin> medecins;
	@OneToOne
    @PrimaryKeyJoinColumn
    private Location location;
	
	
	
	
	@OneToMany(mappedBy="cabinet")
	private List<Position> positions;
	

	public List<UserMedecin> getMedecins() {
		return medecins;
	}

	public void setMedecins(List<UserMedecin> medecins) {
		this.medecins = medecins;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	@Transient
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

	public UserClinique(String logo, List<UserMedecin> medecins, byte[] photo, StreamedContent image) {
		super();
		this.logo = logo;
		this.medecins = medecins;
		this.photo = photo;
		this.image = image;
	}

	public UserClinique(String logo) {
		super();
		this.logo = logo;
	}

	public UserClinique() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	
}
