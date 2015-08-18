/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import entities.Teachers;
import entities.Thesis;
import exceptions.BusinessException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import utils.JsfUtils;
import utils.ResourceBundleUtils;

/**
 *
 * @author Damian
 */
@Named(value = "editThesisByStudentBackingBean")
@RequestScoped
public class EditThesisByStudentBackingBean {

    @Inject
    DiplomaGuideSession diplomaGuideSession;

    private Thesis thesisToEdit;
    private List<Teachers> teachersList;
    private Teachers teacher;

    public EditThesisByStudentBackingBean() {
    }

    public Teachers getTeacher() {
        return teacher;
    }

    public void setTeacher(Teachers teacher) {
        this.teacher = teacher;
    }

    public List<Teachers> getTeachersList() {
        return teachersList;
    }

    public void setTeachersList(List<Teachers> teachersList) {
        this.teachersList = teachersList;
    }

    public Thesis getThesisToEdit() {
        return thesisToEdit;
    }

    public void setThesisToEdit(Thesis thesisToEdit) {
        this.thesisToEdit = thesisToEdit;
    }

    @PostConstruct
    private void init() {
        teachersList = diplomaGuideSession.getTeachers();
        thesisToEdit = diplomaGuideSession.getThesisToEditByStudent();
        teacher = thesisToEdit.getTeacher();
    }

    public String edit() {
        try {
            diplomaGuideSession.editThesisByStudent(thesisToEdit);
            JsfUtils.addSuccessMessage(ResourceBundleUtils.getResourceBundleLanguageProperty("Info"), ResourceBundleUtils.getResourceBundleLanguageProperty("editSucceed"), ":msgs");
            return "/index.xhtml?faces-redirect=true";
        } catch (BusinessException ex) {
            JsfUtils.addErrorMessage(ex, ex.getMessage(), ":msgs");
            return "";

        }
    }
}
