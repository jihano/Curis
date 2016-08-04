package com.curisLoc.model;

public enum jour {

	lundi("Lundi"),
	Mardi("Mardi"),
	mercredi("Mercredi"),
	jeudi("Jeudi"),
	samedi("Samedi"),
	vendredi("Vendredi"),
	dimanche("Dimanche");
	private String label;
	private jour(String label){
		this.label=label;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	
	
}
