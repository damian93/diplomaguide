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
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.inject.Inject;

/**
 *
 * @author Damian
 */
@Named(value = "teacherConverterBackingBean")
@ViewScoped
public class TeacherConverterBackingBean implements Serializable, ITeacherConverter {

    @Inject
    private DiplomaGuideSession diplomaGuideSession;

    private List<Teachers> teachers;

    public TeacherConverterBackingBean() {
    }

    @Override
    public List<Teachers> getTeachers() {
        return teachers;
    }

    @PostConstruct
    private void init() {
        teachers = diplomaGuideSession.getTeachers();
    }

}
