/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import entities.Thesistype;
import interfaces.IThesisTypeConverter;
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
@Named(value = "thesisTypeConverter")
@ViewScoped
public class ThesisTypeConverter implements Converter, Serializable{
    
    @Inject
    private IThesisTypeConverter thesisTypeConverterBackingBean;


    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {

        List<Thesistype> thesisTypeList = thesisTypeConverterBackingBean.getThesisType();
        for (Thesistype t : thesisTypeList) {
            if (t.getSha256Hash().equals(value)) {
                return t;
            }
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if(value instanceof String){
            String t = (String) value;
            return t;
        }
        else{
            return "";
        }
    }

    
    
}
