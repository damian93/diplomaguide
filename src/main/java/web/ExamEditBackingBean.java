/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import entities.Exam;
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
@Named(value = "examEdit")
@RequestScoped
public class ExamEditBackingBean {

    @Inject
    private DiplomaGuideSession dgs;

    private Exam examToEdit;

    public ExamEditBackingBean() {
    }

    public Exam getExamToEdit() {
        return examToEdit;
    }

    public void setExamToEdit(Exam examToEdit) {
        this.examToEdit = examToEdit;
    }

    @PostConstruct
    public void init() {
        examToEdit = dgs.getExamToEdit();
    }

    public String editExam() {
        try {
            dgs.editExamByStudent(this.examToEdit);
            JsfUtils.addSuccessMessage(ResourceBundleUtils.getResourceBundleLanguageProperty("Info"), ResourceBundleUtils.getResourceBundleLanguageProperty("editSucceed"), ":msgs");
            return "/student/myexams.xhtml?faces-redirect=true";
        } catch (BusinessException e) {
            JsfUtils.addErrorMessage(e, e.getMessage(), ":msgs");
            return "";
        }

    }

    public String confirmGrade() {
        try {
            dgs.confirmGrade(examToEdit);
            JsfUtils.addSuccessMessage(ResourceBundleUtils.getResourceBundleLanguageProperty("Info"), ResourceBundleUtils.getResourceBundleLanguageProperty("confirmed"), ":msgs");
            return "/student/myexams.xhtml?faces-redirect=true";
        } catch (BusinessException e) {
            JsfUtils.addErrorMessage(e, e.getMessage(), ":msgs");
            return "";
        }
    }

}
