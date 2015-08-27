/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import entities.Thesis;
import entities.Users;
import exceptions.BusinessException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import dgm.endpoints.DiplomaGuideEndpointLocal;
import entities.Exam;
import entities.Students;
import entities.Teachers;
import entities.Thesistype;
import java.util.Map;
import utils.JsfUtils;

/**
 *
 * @author Damian
 */
@Named(value = "diplomaGuideSession")
@SessionScoped
public class DiplomaGuideSession implements Serializable {

    @EJB
    DiplomaGuideEndpointLocal diplomaGuideEndpointLocal;

    private Teachers loggedTeacher;
    private Students loggedStudent;

    private Thesis thesisToEditByTeacher;

    private Thesis thesisToEditByStudent;

    public DiplomaGuideSession() {
    }

    public Students getLoggedStudent() {
        if (loggedStudent != null) {
            return loggedStudent;
        } else {
            loggedStudent = getStudent();
            return loggedStudent;
        }
    }

    public void setThesisToEditByStudent(Thesis thesis) {
        thesisToEditByStudent = diplomaGuideEndpointLocal.getThesisToEditByStudent(thesis);
    }

    public Thesis getThesisToEditByStudent() {
        return thesisToEditByStudent;
    }

    public void setLoggedStudent(Students loggedStudent) {
        this.loggedStudent = loggedStudent;
    }

    public Thesis getThesisToEditByTeacher() {
        return thesisToEditByTeacher;
    }

    public void setThesisToEditByTeacher(Thesis thesisToEditByTeacher) {
        this.thesisToEditByTeacher = diplomaGuideEndpointLocal.getThesisToEditByTeacher(thesisToEditByTeacher);

    }

    public Teachers getLoggedTeacher() {
        if (loggedTeacher != null) {
            return loggedTeacher;
        } else {
            loggedTeacher = getTeacher();
            return loggedTeacher;
        }
    }

    public void setLoggedTeacher(Teachers loggedTeacher) {
        this.loggedTeacher = loggedTeacher;
    }

    public void createThesis(Thesis t) throws BusinessException {
        diplomaGuideEndpointLocal.createThesis(t);
    }

    public List<Users> getStudentList() {
        List<Users> studentList = diplomaGuideEndpointLocal.getStudentList();
        return studentList;
    }

    Teachers getTeacher() {
        return diplomaGuideEndpointLocal.getLoggedTeacher(JsfUtils.getLoggedUserLogin());
    }

    Map<Teachers, Integer> getTeachersMap() {
        return diplomaGuideEndpointLocal.getTeachersMap();
    }

    List<Teachers> getTeachers() {
        return diplomaGuideEndpointLocal.getTeachers();
    }

    Students getStudent() {
        return diplomaGuideEndpointLocal.getLoggedStudent(JsfUtils.getLoggedUserLogin());
    }

    List<Thesistype> getThesisTypeList() {
        return diplomaGuideEndpointLocal.getThesisTypeList();
    }

    List<Thesis> getAllThesisList() {
        return diplomaGuideEndpointLocal.getAllThesisList();
    }

    List<Thesis> getThesisWithPhrase(String phrase) {
        return diplomaGuideEndpointLocal.getThesisWithPhrase(phrase);
    }

    void acceptation(Thesis thesisToEdit) throws BusinessException {
        diplomaGuideEndpointLocal.acceptation(thesisToEdit);
    }

    List<Thesis> getMyThesis() {
        return diplomaGuideEndpointLocal.getMyThesis(getLoggedStudent());
    }
    List<Thesis> getMyThesisByTeacher() {
        return diplomaGuideEndpointLocal.getMyThesisByTeacher(getLoggedTeacher());
    }
    
    public void editThesisByStudent(Thesis thesis) throws BusinessException{
        diplomaGuideEndpointLocal.editThesisByStudent(thesis);
    }
    
    public void createExam(Exam exam) throws BusinessException{
        diplomaGuideEndpointLocal.createExam(exam);
    }

    List<Exam> getMyExamsByTeacher() {
        return diplomaGuideEndpointLocal.getExamsByTeacher(loggedTeacher);
    }


}
