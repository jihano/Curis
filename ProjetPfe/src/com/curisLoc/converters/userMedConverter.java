package com.curisLoc.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.curisLoc.model.UserClinique;
import com.curisLoc.model.UserMedecin;;

@FacesConverter("userMedConverter")
public class userMedConverter implements Converter {


	@Override
	public Object getAsObject(FacesContext context, 
			UIComponent component, String value) {
		if(value.equals("") || !value.contains("#")){
			return null;
		}
				UserClinique uc = new UserClinique();
		
		String[] properties = value.split("#");
		if(!properties[0].isEmpty()){
			uc.setId(new Integer(properties[0]));
		}
		if(!properties[1].isEmpty()){
			uc.setNom(properties[1]);
		}
		
		return uc;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object obj) {
		if(obj == null || !(obj instanceof UserClinique)){
			return "";
		}
		
		UserClinique sp = (UserClinique) obj;
		
		String id = sp.getId() == null ? "" : sp.getId().toString();
		String nom = sp.getNom() == null ? "" : sp.getNom();
		
		return id + "#" + nom;
	}
	
	
}

