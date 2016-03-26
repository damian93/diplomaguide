/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import entities.Teachers;
import entities.Thesis;
import exceptions.BusinessException;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import utils.JsfUtils;
import utils.ResourceBundleUtils;

/**
 *
 * @author Damian
 */
@Named(value = "editThesisByStudentBackingBean")
@ViewScoped
public class EditThesisByStudentBackingBean implements Serializable{

    @Inject
    private DiplomaGuideSession diplomaGuideSession;

    private Thesis thesisToEdit;
    private Teachers teacher;

    public EditThesisByStudentBackingBean() {
    }

    public Teachers getTeacher() {
        return teacher;
    }

    public void setTeacher(Teachers teacher) {
        this.teacher = teacher;
    }

    public Thesis getThesisToEdit() {
        return thesisToEdit;
    }

    public void setThesisToEdit(Thesis thesisToEdit) {
        this.thesisToEdit = thesisToEdit;
    }

    @PostConstruct
    private void init() {
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
