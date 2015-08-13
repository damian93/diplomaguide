/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import entities.Thesistype;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;

/**
 *
 * @author Damian
 */
@Named(value = "thesisTypeConverter")
@RequestScoped
public class ThesisTypeConverter implements Converter{
    
    @Inject
    CreateThesisBackingBean createThesisBackingBean;


    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        
        Long tId = Long.valueOf(value);
        List<Thesistype> thesisTypeList = createThesisBackingBean.getThesisType();
        for (Thesistype t : thesisTypeList) {
            if (t.getThesisTypeId().equals(tId)) {
                return t;
            }
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Thesistype t = (Thesistype) value;
        return String.valueOf(t.getThesisTypeId());

    }

    
    
}