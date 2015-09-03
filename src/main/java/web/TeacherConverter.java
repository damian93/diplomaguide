/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import entities.Teachers;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.RequestScoped;
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
    CreateThesisBackingBean createThesisBackingBean;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {

        Long tId = Long.valueOf(value);
        List<Teachers> teachersList = createThesisBackingBean.getTeachers();
        for (Teachers t : teachersList) {
            if (t.getAccessLevelId().equals(tId)) {
                return t;
            }
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Teachers t = (Teachers) value;
        return String.valueOf(t.getAccessLevelId());

    }

}
