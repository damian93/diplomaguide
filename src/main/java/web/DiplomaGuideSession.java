/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import common.CommisionTeachersUtils;
import entities.Thesis;
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
import java.util.Set;
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
    private Exam examToEdit;
    private Exam examToAddCommision;
    private Exam examToEditCommision;
    private Exam examToSetGrade;

    public DiplomaGuideSession() {
    }

    public Exam getExamToSetGrade() {
        return examToSetGrade;
    }

    public void setExamToSetGrade(Exam examToSetGrade) {
        this.examToSetGrade = examToSetGrade;
    }

    public Exam getExamToEditCommision() {
        return examToEditCommision;
    }

    public void setExamToEditCommision(Exam examToEditCommision) {
        this.examToEditCommision = examToEditCommision;
    }

    public Exam getExamToEdit() {
        return examToEdit;
    }

    public void setExamToEdit(Exam examToEdit) {
        this.examToEdit = examToEdit;
    }

    public Exam getExamToAddCommision() {
        return examToAddCommision;
    }

    public void setExamToAddCommision(Exam examToAddCommision) {
        this.examToAddCommision = examToAddCommision;
    }

    public Students getLoggedStudent() {
        loggedStudent = getStudent();
        return loggedStudent;

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
        loggedTeacher = getTeacher();
        return loggedTeacher;
    }

    public void setLoggedTeacher(Teachers loggedTeacher) {
        this.loggedTeacher = loggedTeacher;
    }

    public void createThesis(Thesis t) throws BusinessException {
        diplomaGuideEndpointLocal.createThesis(t);
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

    public void editThesisByStudent(Thesis thesis) throws BusinessException {
        diplomaGuideEndpointLocal.editThesisByStudent(thesis);
    }

    public void createExam(Exam exam) throws BusinessException {
        diplomaGuideEndpointLocal.createExam(exam);
    }

    List<Exam> getMyExamsByTeacher() {
        return diplomaGuideEndpointLocal.getExamsByTeacher(getLoggedTeacher());
    }

    List<Exam> getMyExamsByStudent() {
        return diplomaGuideEndpointLocal.getMyExamsByStudent(getLoggedStudent());
    }

    void getExamToEdit(Exam e) {
        this.examToEdit = diplomaGuideEndpointLocal.getExamToEdit(e);
    }

    void editExamByStudent(Exam examToEdit) throws BusinessException {
        diplomaGuideEndpointLocal.editExamByStudent(examToEdit);

    }

    void editExamByTeacher(Exam examToEdit) throws BusinessException {
        diplomaGuideEndpointLocal.editExamByTeacher(examToEdit);
    }

    void getExamToAddCommision(Exam e) {
        this.examToAddCommision = diplomaGuideEndpointLocal.getExamToAddCommision(e);
    }

    void addCommision(Exam exam, Set<Teachers> commisionTeachers) throws BusinessException {
        diplomaGuideEndpointLocal.addCommission(exam, commisionTeachers);
    }

    void acceptCommision(Teachers teacher, int rowIndex) throws BusinessException {
        diplomaGuideEndpointLocal.acceptCommision(teacher, rowIndex);
    }

    void rejectCommision(Teachers teacher, int rowIndex) throws BusinessException {
        diplomaGuideEndpointLocal.rejectCommision(teacher, rowIndex);
    }

    void editCommision(Exam exam, Set<Teachers> commisionTeachers) throws BusinessException {
        diplomaGuideEndpointLocal.editCommission(exam, commisionTeachers);
    }

    void getExamToEditCommision(Exam e) {
        this.examToEditCommision = diplomaGuideEndpointLocal.getExamToEditCommision(e);

    }

    CommisionTeachersUtils setMembersInCommision(Exam exam, Teachers lt) {
        return diplomaGuideEndpointLocal.setMembersInCommision(exam, lt);
    }

    void setGrade(Exam examToEdit) throws BusinessException {
        diplomaGuideEndpointLocal.setGrade(examToEdit);
    }

    void getExamToSetGrade(Exam e) {
        this.examToSetGrade = diplomaGuideEndpointLocal.getExamToSetGrade(e);
    }

    void confirmGrade(Exam examToEdit) throws BusinessException {
        diplomaGuideEndpointLocal.confirmGrade(examToEdit);
    }

}
