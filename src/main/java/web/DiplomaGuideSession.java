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

    public DiplomaGuideSession() {
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
    List<Thesis> getAllThesisList(){
        return diplomaGuideEndpointLocal.getAllThesisList();
    }
}

