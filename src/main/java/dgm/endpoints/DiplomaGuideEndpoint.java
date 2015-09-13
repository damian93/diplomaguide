/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dgm.endpoints;

import common.CommisionTeachersUtils;
import dgm.managers.ExamManagerLocal;
import dgm.managers.TeacherManagerLocal;
import dgm.managers.ThesisManagerLocal;
import entities.Exam;
import entities.Students;
import entities.Teachers;
import entities.Thesis;
import entities.Thesistype;
import exceptions.BusinessException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

/**
 *
 * @author Damian
 */
@Stateful
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class DiplomaGuideEndpoint implements DiplomaGuideEndpointLocal {

    @EJB
    private ExamManagerLocal examManagerLocal;

    @EJB
    private ThesisManagerLocal thesisManagerLocal;

    @EJB
    private TeacherManagerLocal teacherManagerLocal;

    private Thesis thesisToEditByTeacherState;
    private Thesis thesisToEditByStudentState;
    private Exam examToEditState;
    private Exam examToAddCommision;
    private Exam examToEditCommision;
    private Exam examToSetGradeState;

    @Override
    public void createThesis(Thesis thesis) throws BusinessException {
        thesisManagerLocal.createThesis(thesis);
    }

    @Override
    public Teachers getLoggedTeacher(String login) {
        return teacherManagerLocal.getLoggedTeacher(login);
    }

    @Override
    public Map<Teachers, Integer> getTeachersMap() {
        return thesisManagerLocal.getTeachersMap();
    }

    @Override
    public List<Teachers> getTeachers() {
        return thesisManagerLocal.getTeachers();
    }

    @Override
    public Students getLoggedStudent(String loggedUserLogin) {
        return thesisManagerLocal.getLoggedStudent(loggedUserLogin);

    }

    @Override
    public List<Thesistype> getThesisTypeList() {
        return thesisManagerLocal.getThesisTypeList();

    }

    @Override
    public List<Thesis> getAllThesisList() {
        return thesisManagerLocal.getAllThesisList();
    }

    @Override
    public List<Thesis> getThesisWithPhrase(String phrase) {
        return thesisManagerLocal.getThesisWithPhrase(phrase);
    }

    @Override
    public Thesis getThesisToEditByTeacher(Thesis row) {
        thesisToEditByTeacherState = thesisManagerLocal.getThesisToEditByTeacher(row);
        return thesisToEditByTeacherState;
    }

    @Override
    public void acceptation(Thesis thesisToEdit) throws BusinessException {
        thesisManagerLocal.acceptThesis(thesisToEditByTeacherState, thesisToEdit);
        thesisToEditByTeacherState = null;
    }

    @Override
    public List<Thesis> getMyThesis(Students loggedStudent) {
        return thesisManagerLocal.getMyThesis(loggedStudent);
    }

    @Override
    public Thesis getThesisToEditByStudent(Thesis thesis) {
        thesisToEditByStudentState = thesisManagerLocal.getThesisToEditByStudent(thesis);
        return thesisToEditByStudentState;
    }

    @Override
    public void editThesisByStudent(Thesis thesis) throws BusinessException {
        thesisManagerLocal.editThesisByStudent(thesisToEditByStudentState, thesis);
        thesisToEditByStudentState = null;
    }

    @Override
    public void createExam(Exam exam) throws BusinessException {
        examManagerLocal.createExam(exam);
    }

    @Override
    public List<Exam> getExamsByTeacher(Teachers loggedTeacher) {
        return examManagerLocal.getExamsByTeacher(loggedTeacher);
    }

    @Override
    public List<Thesis> getMyThesisByTeacher(Teachers loggedTeacher) {
        return thesisManagerLocal.getMyThesisByTeacher(loggedTeacher);

    }

    @Override
    public List<Exam> getMyExamsByStudent(Students loggedStudent) {
        return examManagerLocal.getMyExamsByStudent(loggedStudent);
    }

    @Override
    public Exam getExamToEdit(Exam e) {
        examToEditState = examManagerLocal.getExamToEdit(e);
        return examToEditState;
    }

    @Override
    public void editExamByStudent(Exam edit) throws BusinessException {
        examManagerLocal.editExamByStudent(examToEditState, edit);
        examToEditState = null;
    }

    @Override
    public void editExamByTeacher(Exam examToEdit) throws BusinessException {
        examManagerLocal.ediExamByTeacher(examToEditState, examToEdit);
        examToEditState = null;
    }

    @Override
    public Exam getExamToAddCommision(Exam e) {
        examToAddCommision = examManagerLocal.getExamToAddCommision(e);
        return examToAddCommision;

    }

    @Override
    public void addCommission(Exam exam, Set<Teachers> commisionTeachers) throws BusinessException {
        examManagerLocal.addCommision(examToAddCommision, exam, commisionTeachers);
        examToAddCommision = null;
    }

    @Override
    public void acceptCommision(Teachers teacher, int rowIndex) throws BusinessException {
        examManagerLocal.acceptCommision(teacher, rowIndex);
    }

    @Override
    public void rejectCommision(Teachers teacher, int rowIndex) throws BusinessException {
        examManagerLocal.rejectCommision(teacher, rowIndex);
    }

    @Override
    public void editCommission(Exam exam, Set<Teachers> commisionTeachers) throws BusinessException {
        examManagerLocal.editCommision(examToEditCommision, exam, commisionTeachers);
        examToEditCommision = null;

    }

    @Override
    public Exam getExamToEditCommision(Exam e) {
        examToEditCommision = examManagerLocal.getExamToEditCommision(e);
        return examToEditCommision;

    }

    @Override
    public CommisionTeachersUtils setMembersInCommision(Exam exam, Teachers loggedTeacher) {
        return examManagerLocal.setMembersInCommision(exam, loggedTeacher);
    }

    /**
     *
     * @param examToEdit
     * @throws BusinessException
     */
    @Override
    public void setGrade(Exam examToEdit) throws BusinessException {
        examManagerLocal.setGrade(examToSetGradeState, examToEdit);
        examToSetGradeState = null;
    }

    @Override
    public Exam getExamToSetGrade(Exam e) {
        examToSetGradeState = examManagerLocal.getExamToSetGrade(e);
        return examToSetGradeState;
    }
}
