package com.curisLoc.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class SecurityRole {
	@Id 
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;
	private String roleName;
	
	@ManyToOne(fetch= FetchType.EAGER)
	private User user;

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public SecurityRole(String roleName, User user) {
		super();
		this.roleName = roleName;
		this.user = user;
	}

	public SecurityRole() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
}
