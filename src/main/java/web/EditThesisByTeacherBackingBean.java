/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import entities.Thesis;
import exceptions.BusinessException;
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
@Named(value = "editThesisByTeacherBackingBean")
@RequestScoped
public class EditThesisByTeacherBackingBean {

    @Inject
    DiplomaGuideSession diplomaGuideSession;

    private Thesis thesisToEdit;

    public EditThesisByTeacherBackingBean() {

    }

    public Thesis getThesisToEdit() {
        return thesisToEdit;
    }

    public void setThesisToEdit(Thesis thesisToEdit) {
        this.thesisToEdit = thesisToEdit;
    }

    @PostConstruct
    private void init() {
        thesisToEdit = diplomaGuideSession.getThesisToEditByTeacher();
    }

    public String accept() {
        try {
            diplomaGuideSession.acceptation(thesisToEdit);
            JsfUtils.addSuccessMessage(ResourceBundleUtils.getResourceBundleLanguageProperty("Info"), ResourceBundleUtils.getResourceBundleLanguageProperty("editSucceed"), ":msgs");
            return "/index.xhtml?faces-redirect=true";

        } catch (BusinessException ex) {
            JsfUtils.addErrorMessage(ex, ex.getMessage(), ":msgs");
            return "";

        }
    }

}

