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
@Named(value = "examList")
@RequestScoped
public class ExamListBackingBean {

    @Inject
    DiplomaGuideSession diplomaGuideSession;

    private List<Exam> examList;
    private DataModel<Exam> examDataModel;

    public ExamListBackingBean() {
    }

    public DataModel<Exam> getExamDataModel() {
        return examDataModel;
    }

    public void setExamDataModel(DataModel<Exam> examDataModel) {
        this.examDataModel = examDataModel;
    }

    @PostConstruct
    public void init() {
        examList = diplomaGuideSession.getMyExamsByTeacher();
        examDataModel = new ListDataModel<>(examList);

    }
    
    public String beforeEdit(){
        int rowIndex = examDataModel.getRowIndex();
        diplomaGuideSession.getExamToEdit(examList.get(rowIndex));
        return "/teacher/examedit.xhtml?faces-redirect=true";
        
    }
    
    public String beforeShowCommision(){
        int rowIndex = examDataModel.getRowIndex();
        diplomaGuideSession.getExamToAddCommision(examList.get(rowIndex));
        return "/teacher/addCommision.xhtml?faces-redirecr=true";
    }
    public String beforeEditCommision(){
        int rowIndex = examDataModel.getRowIndex();
        diplomaGuideSession.getExamToEditCommision(examList.get(rowIndex));
        return "/teacher/editCommision.xhtml?faces-redirecr=true";
    }
    
    public String beforeSetGrade(){
        int rowIndex = examDataModel.getRowIndex();
        diplomaGuideSession.getExamToSetGrade(examList.get(rowIndex));
        return "/teacher/setGrade.xhtml?faces-redirecr=true";
    }
    
    
}
