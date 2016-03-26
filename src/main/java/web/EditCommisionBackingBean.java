/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import common.CommisionTeachersUtils;
import entities.Exam;
import entities.Teachers;
import exceptions.BusinessException;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;
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
@Named(value = "editCommision")
@ViewScoped
public class EditCommisionBackingBean implements Serializable {

    @Inject
    private DiplomaGuideSession dgs;

    private Exam exam;
    private CommisionTeachersUtils ctu;

    public EditCommisionBackingBean() {
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    public CommisionTeachersUtils getCtu() {
        return ctu;
    }

    public void setCtu(CommisionTeachersUtils ctu) {
        this.ctu = ctu;
    }

    @PostConstruct
    private void init() {
        this.exam = dgs.getExamToEditCommision();
        Teachers loggedTeacher = dgs.getLoggedTeacher();
        ctu = dgs.setMembersInCommision(exam, loggedTeacher);

    }

    public String editCommision() {
        try {
            Set<Teachers> commisionTeachers = new LinkedHashSet<>();
            commisionTeachers.add(ctu.getChairman());
            commisionTeachers.add(ctu.getTeacher1());
            commisionTeachers.add(ctu.getTeacher2());
            dgs.editCommision(exam, commisionTeachers);
            JsfUtils.addSuccessMessage(ResourceBundleUtils.getResourceBundleLanguageProperty("Info"), ResourceBundleUtils.getResourceBundleLanguageProperty("editSucceed"), ":msgs");
            return "/teacher/examlist.xhtml?faces-redirect=true";
        } catch (BusinessException ex) {
            JsfUtils.addErrorMessage(ex, ex.getMessage(), ":msgs");
            return "";
        }

    }

}
