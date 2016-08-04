package com.curisLoc.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class rendezvous implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4799025354418457309L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private Date dateRd;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getDateRd() {
		return dateRd;
	}
	public void setDateRd(Date dateRd) {
		this.dateRd = dateRd;
	}

	
}
