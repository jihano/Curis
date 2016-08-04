package com.curisLoc.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class operation implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -574799229629009599L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String nom;
	private String description;
	private double duree;
	private Date dateOp;
	
	
	public Date getDateOp() {
		return dateOp;
	}
	public void setDateOp(Date dateOp) {
		this.dateOp = dateOp;
	}
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getDuree() {
		return duree;
	}
	public void setDuree(double duree) {
		this.duree = duree;
	}
	public operation() {
		super();
		// TODO Auto-generated constructor stub
	}
	public operation(String nom, String description, double duree) {
		super();
		this.nom = nom;
		this.description = description;
		this.duree = duree;
	}
	

}
