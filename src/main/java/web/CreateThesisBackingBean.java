/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import entities.Students;
import entities.Teachers;
import entities.Thesis;
import entities.Thesistype;
import exceptions.BusinessException;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.inject.Inject;
import utils.JsfUtils;
import utils.ResourceBundleUtils;

/**
 *
 * @author Damian
 */
@Named(value = "createThesisBackingBean")
@ViewScoped
public class CreateThesisBackingBean implements Serializable{

    @Inject
    private DiplomaGuideSession diplomaGuideSession;

    private Thesis thesis = new Thesis();

    private List<Teachers> teachers;
    private String studentLogin;
    private Teachers teacher;
    private Students student;
    private List<Thesistype> thesisType;

    public CreateThesisBackingBean() {
    }

    public Teachers getTeacher() {
        return teacher;
    }

    public void setTeacher(Teachers teacher) {
        this.teacher = teacher;
    }

    public List<Thesistype> getThesisType() {
        return thesisType;
    }

    public void setThesisType(List<Thesistype> thesisType) {
        this.thesisType = thesisType;
    }

    public Students getStudent() {
        return student;
    }

    public void setStudent(Students student) {
        this.student = student;
    }

    public String getStudentLogin() {
        return studentLogin;
    }

    public void setStudentLogin(String studentLogin) {
        this.studentLogin = studentLogin;
    }

    public Thesis getThesis() {
        return thesis;
    }

    public List<Teachers> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<Teachers> teachers) {
        this.teachers = teachers;
    }

    public void setThesis(Thesis thesis) {
        this.thesis = thesis;
    }

    @PostConstruct
    private void init() {
        student = diplomaGuideSession.getStudent();
        teachers = diplomaGuideSession.getTeachers();
        thesis.setStudent(student);
        thesisType = diplomaGuideSession.getThesisTypeList();
    }

    public void createThesis() {
        try {
            diplomaGuideSession.createThesis(thesis);
            JsfUtils.addSuccessMessage(ResourceBundleUtils.getResourceBundleLanguageProperty("Info"), ResourceBundleUtils.getResourceBundleLanguageProperty("successfulEnding"), ":msgs");
        } catch (BusinessException ex) {
            JsfUtils.addErrorMessage(ex, ex.getMessage(), ":msgs");
        }
    }

}
