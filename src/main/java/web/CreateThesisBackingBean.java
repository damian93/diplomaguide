/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import entities.Students;
import entities.Teachers;
import entities.Thesis;
import exceptions.BusinessException;
import java.io.Serializable;
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

    private String studentLogin;
    private Teachers teacher;
    private Students student;

    public CreateThesisBackingBean() {
    }

    public Teachers getTeacher() {
        return teacher;
    }

    public void setTeacher(Teachers teacher) {
        this.teacher = teacher;
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

    public void setThesis(Thesis thesis) {
        this.thesis = thesis;
    }

    @PostConstruct
    private void init() {
        student = diplomaGuideSession.getStudent();
        thesis.setStudent(student);
    }

    public String createThesis() {
        try {
            diplomaGuideSession.createThesis(thesis);
            JsfUtils.addSuccessMessage(ResourceBundleUtils.getResourceBundleLanguageProperty("Info"), ResourceBundleUtils.getResourceBundleLanguageProperty("successfulEnding"), ":msgs");
            return "/index.xhtml?faces-redirect=true";
        } catch (BusinessException ex) {
            JsfUtils.addErrorMessage(ex, ex.getMessage(), ":msgs");
            return "";
        }
    }

}
