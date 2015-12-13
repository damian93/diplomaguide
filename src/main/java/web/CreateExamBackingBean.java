/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import entities.Exam;
import entities.Thesis;
import exceptions.BusinessException;
import java.io.Serializable;
import java.util.List;
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
@Named(value = "createExam")
@ViewScoped
public class CreateExamBackingBean implements Serializable{

    @Inject
    private DiplomaGuideSession diplomaGuideSession;

    private Exam exam = new Exam();
    private List<Thesis> thesisList;

    public CreateExamBackingBean() {
    }

    public List<Thesis> getThesisList() {
        return thesisList;
    }

    public void setThesisList(List<Thesis> thesisList) {
        this.thesisList = thesisList;
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    @PostConstruct
    public void init() {
        thesisList = diplomaGuideSession.getMyThesisByTeacher();

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
