package com.curisLoc.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.curisLoc.model.Specialite;

@FacesConverter("speciliteConverter")
public class speciliteConverter implements Converter {


	@Override
	public Object getAsObject(FacesContext context, 
			UIComponent component, String value) {
		if(value.equals("") || !value.contains("#")){
			return null;
		}
				Specialite sp = new Specialite();
		
		String[] properties = value.split("#");
		if(!properties[0].isEmpty()){
			sp.setId(new Integer(properties[0]));
		}
		if(!properties[1].isEmpty()){
			sp.setNom(properties[1]);
		}
		
		return sp;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object obj) {
		if(obj == null || !(obj instanceof Specialite)){
			return "";
		}
		
		Specialite sp = (Specialite) obj;
		
		String id = sp.getId() == null ? "" : sp.getId().toString();
		String nom = sp.getNom() == null ? "" : sp.getNom();
		
		return id + "#" + nom;
	}
	
	
}



