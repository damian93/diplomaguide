/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import entities.Exam;
import exceptions.BusinessException;
import java.io.Serializable;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import utils.JsfUtils;
import utils.ResourceBundleUtils;

/**
 *
 * @author Damian
 */
@Named(value = "createExam")
@ViewScoped
public class CreateExamBackingBean implements Serializable{

    @Inject
    private DiplomaGuideSession diplomaGuideSession;

    private Exam exam = new Exam();

    public CreateExamBackingBean() {
    }


    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    public String create() {
        try {
            diplomaGuideSession.createExam(exam);
            JsfUtils.addSuccessMessage(ResourceBundleUtils.getResourceBundleLanguageProperty("Info"), ResourceBundleUtils.getResourceBundleLanguageProperty("succeededCreation"), ":msgs");
            return "/index.xhtml?faces-redirect=true";
        } catch (BusinessException e) {
            JsfUtils.addErrorMessage(e, e.getMessage(), ":msgs");
            return "";
        }
    }

}
