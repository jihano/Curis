package com.curisLoc.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class EmploiTp {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private int HeurDebut;
	private int MinDebut;
	private int HeurFin;
	private int MinFin;
	private boolean Active;
	private jour jour;
	
	
	public jour getJour() {
		return jour;
	}
	public void setJour(jour jour) {
		this.jour = jour;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getHeurDebut() {
		return HeurDebut;
	}
	public void setHeurDebut(int heurDebut) {
		HeurDebut = heurDebut;
	}
	public int getMinDebut() {
		return MinDebut;
	}
	public void setMinDebut(int minDebut) {
		MinDebut = minDebut;
	}
	public int getHeurFin() {
		return HeurFin;
	}
	public void setHeurFin(int heurFin) {
		HeurFin = heurFin;
	}
	public int getMinFin() {
		return MinFin;
	}
	public void setMinFin(int minFin) {
		MinFin = minFin;
	}
	public boolean isActive() {
		return Active;
	}
	public void setActive(boolean active) {
		Active = active;
	}
	
}
