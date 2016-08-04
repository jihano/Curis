package com.curisLoc.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class User implements Serializable, UserDetails{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3698026701896552203L;
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	public Integer id;
	private String nom;
	private String email;
	private String login;
	private String senha;
	private boolean active;
	
	@OneToMany(mappedBy="user", fetch=FetchType.EAGER)
	private Collection<SecurityRole> roles;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	
	public Collection<SecurityRole> getRoles() {
		return roles;
	}
	public void setRoles(Collection<SecurityRole> roles) {
		this.roles = roles;
	}
	@SuppressWarnings("deprecation")
	@Override
	@Transient
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
		for(SecurityRole role: roles)
		{ 
			authorities.add(new GrantedAuthorityImpl(role.getRoleName()));
		}
		return authorities;
	}
	@Override
	@Transient
	public String getPassword() {
		// TODO Auto-generated method stub
		return senha;
	}
	@Override
	@Transient
	public String getUsername() {
		// TODO Auto-generated method stub
		return login;
	}
	@Override
	@Transient
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	@Transient
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	@Transient
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	@Transient
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	public User(String nom, String email, String login, String senha) {
		super();
		this.nom = nom;
		this.email = email;
		this.login = login;
		this.senha = senha;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

}
