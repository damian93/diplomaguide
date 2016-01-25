/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import entities.Teachers;
import interfaces.ITeacherConverter;
import java.io.Serializable;
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Damian
 */
@Named(value = "teacherConverter")
@ViewScoped
public class TeacherConverter implements Converter, Serializable {

    @Inject
    private ITeacherConverter teacherConverterBackingBean;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        List<Teachers> teachersList = teacherConverterBackingBean.getTeachers();
        for (Teachers t : teachersList) {
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
