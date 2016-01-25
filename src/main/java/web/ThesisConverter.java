/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import entities.Thesis;
import interfaces.IThesisConverter;
import java.io.Serializable;
import java.util.List;
import javax.inject.Named;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author Damian
 */
@Named(value = "thesisConverter")
@ViewScoped
public class ThesisConverter implements Converter, Serializable {

    @Inject
    private IThesisConverter thesisConverterBackingBean;
  
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        
        List<Thesis> thesisList = thesisConverterBackingBean.getThesisList();
        for (Thesis t : thesisList) {
            if (t.getSha256Hash().equals(value)) {
                return t;
            }
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value)  {
        
        if(value instanceof String){
            String t = (String) value;
            return String.valueOf(t);
        }
        else {
            return "";
        }
    }
}
