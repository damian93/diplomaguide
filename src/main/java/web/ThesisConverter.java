/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import entities.Thesis;
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
@Named(value = "thesisConverter")
@RequestScoped
public class ThesisConverter implements Converter {

    @Inject
    CreateExamBackingBean createExamBackingBean;

    
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        
        Long tId = Long.valueOf(value);
        List<Thesis> thesisList = createExamBackingBean.getThesisList();
        for (Thesis t : thesisList) {
            if (t.getThesisId().equals(tId)) {
                return t;
            }
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Thesis t = (Thesis) value;
        return String.valueOf(t.getThesisId());
    }
    
}
