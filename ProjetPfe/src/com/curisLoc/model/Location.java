package com.curisLoc.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import org.hibernate.annotations.Parameter;

@Entity
public class Location {
	
	@Id @GeneratedValue(generator = "customForeignGenerator")
    @org.hibernate.annotations.GenericGenerator(
        name = "customForeignGenerator",
        strategy = "foreign",
        parameters = @Parameter(name = "property", value = "userClinique")
    )
    private Integer id;

    @OneToOne(mappedBy="location")
    @PrimaryKeyJoinColumn
    public UserClinique userClinique;
 
	private String title;
    
    private double lat;
      
    private double lng;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public UserClinique getUserClinique() {
		return userClinique;
	}

	public void setUserClinique(UserClinique userClinique) {
		this.userClinique = userClinique;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public Location() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Location(UserClinique userClinique, String title, double lat, double lng) {
		super();
		this.userClinique = userClinique;
		this.title = title;
		this.lat = lat;
		this.lng = lng;
	}

    

}
