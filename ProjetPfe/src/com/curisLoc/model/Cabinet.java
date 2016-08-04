package com.curisLoc.model;

import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@Entity
public class Cabinet implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6490647057142092052L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private String nom;
	private String email;
	private double lat;
	private double lng;
	private String NumTel;
	@Lob
	private byte[] photo;
	@Transient
	private StreamedContent image;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	
	
	@Email(message="entree un email valide")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public double getLng() {
		return lng;
	}
	public void setLng(double lng) {
		this.lng = lng;
	}
	public Cabinet() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Cabinet(String nom, String email, double lat, double lng, String numTel, byte[] photo,
			StreamedContent image) {
		super();
		this.nom = nom;
		this.email = email;
		this.lat = lat;
		this.lng = lng;
		NumTel = numTel;
		this.photo = photo;
		this.image = image;
	}
	
	
	
	
}
