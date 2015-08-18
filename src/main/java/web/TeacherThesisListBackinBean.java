/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import entities.Teachers;
import entities.Thesis;
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
@Named(value = "teacherThesisListBackinBean")
@RequestScoped
public class TeacherThesisListBackinBean {

    @Inject
    DiplomaGuideSession diplomaGuideSession;

    private DataModel<Thesis> myThesisList;

    private Teachers teacher;

    private List<Thesis> list;

    public TeacherThesisListBackinBean() {
    }

    public List<Thesis> getList() {
        return list;
    }

    public void setList(List<Thesis> list) {
        this.list = list;
    }

    public Teachers getTeacher() {
        return teacher;
    }

    public void setTeacher(Teachers teacher) {
        this.teacher = teacher;
    }

    public DataModel<Thesis> getMyThesisList() {
        return myThesisList;
    }

    public void setMyThesisList(DataModel<Thesis> allThesisList) {
        this.myThesisList = allThesisList;
    }

    @PostConstruct
    private void init() {
        teacher = diplomaGuideSession.getLoggedTeacher();
        list = teacher.getThesisList();
        myThesisList = new ListDataModel<>(list);
    }

    public String beforeEdit() {
        diplomaGuideSession.setThesisToEditByTeacher(list.get(myThesisList.getRowIndex()));
        return "editthesis.xhtml?faces-redirect=true";
    }

}
