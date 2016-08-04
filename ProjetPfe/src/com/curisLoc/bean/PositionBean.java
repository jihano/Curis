package com.curisLoc.bean;

import java.io.Serializable;
import javax.annotation.PostConstruct; 
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
  
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.curisLoc.authentification.AuthenticationContext;
import com.curisLoc.dao.GenericDAO;
import com.curisLoc.dao.positionDAO;
import com.curisLoc.model.Location;
import com.curisLoc.model.Position;
@ManagedBean
@Controller("positionBean")
@Scope("view")
public class PositionBean implements Serializable{
	
	private static final long serialVersionUID = 693728881118093924L;
	private Position position= new Position();
	private Location location;
	private MapModel emptyModel;
	public Integer idcu= AuthenticationContext.getUsuarioLogado().getId();
	    public void iniciarBean() {
	        emptyModel = new DefaultMapModel();
	    }

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public MapModel getEmptyModel() {
		return emptyModel;
	}

	public void setEmptyModel(MapModel emptyModel) {
		this.emptyModel = emptyModel;
	}
	
	
	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}
	public Integer test=4;
	public void addMarker() {
		//new positionDAO().save(test);
		new GenericDAO<Position>(Position.class).save(position);
		
	 Marker marker = new Marker(new LatLng(position.getLat(), position.getLng()), position.getTitle());
	       emptyModel.addOverlay(marker);
          
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Marker Ajouté!", "Lat:" + position.getLat() + ", Lng:" + position.getLng()));
    }
	

}
