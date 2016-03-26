/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import entities.Exam;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;

/**
 *
 * @author Damian
 */
@Named(value = "myExams")
@RequestScoped
public class MyExamsBackingBean {

    @Inject
    private DiplomaGuideSession dgs;

    private List<Exam> examList;

    private DataModel<Exam> examDataModel;

    public MyExamsBackingBean() {
    }

    public DataModel<Exam> getExamDataModel() {
        return examDataModel;
    }

    public void setExamDataModel(DataModel<Exam> examDataModel) {
        this.examDataModel = examDataModel;
    }

    @PostConstruct
    private void init() {
        examList = dgs.getMyExamsByStudent();
        examDataModel = new ListDataModel<>(examList);
    }

    public String beforeEdit() {
        int rowIndex = examDataModel.getRowIndex();
        dgs.getExamToEdit(examList.get(rowIndex));
        return "/student/examedit.xhtml?faces-redirect=true";
    }

    public String beforeShowCommision() {
        int rowIndex = examDataModel.getRowIndex();
        dgs.getExamToAddCommision(examList.get(rowIndex));
        return "/student/showcommision.xhtml?faces-redirect=true";
    }

}
