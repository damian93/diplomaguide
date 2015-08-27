/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dgm.endpoints;

import dgm.facades.ExamFacadeLocal;
import dgm.facades.StudentsFacadeLocal;
import dgm.facades.TeachersFacadeLocal;
import dgm.facades.ThesisFacadeLocal;
import dgm.facades.ThesistypeFacadeLocal;
import dgm.facades.UsersFacadeLocal;
import entities.Exam;
import entities.Students;
import entities.Teachers;
import entities.Thesis;
import entities.Thesistype;
import entities.Users;
import exceptions.BusinessException;
import exceptions.CanNotEditThesisWhichHasConfirmedExam;
import exceptions.DateFromPastException;
import exceptions.NullThesisStateException;
import exceptions.ThesisAlreadyAcceptedException;
import exceptions.ThesisIsNotAcceptedException;
import exceptions.ThesisStateMismatchException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.Stateful;

/**
 *
 * @author Damian
 */
@Stateful
public class DiplomaGuideEndpoint implements DiplomaGuideEndpointLocal {

    @EJB
    private ThesisFacadeLocal thesisFacadeLocal;

    @EJB
    private UsersFacadeLocal usersFacadeLocal;

    @EJB
    private TeachersFacadeLocal teachersFacadeLocal;

    @EJB
    private StudentsFacadeLocal studentsFacadeLocal;

    @EJB
    private ThesistypeFacadeLocal thesistypeFacadeLocal;

    @EJB
    private ExamFacadeLocal examFacadeLocal;

    private Thesis thesisToEditByTeacherState;
    private Thesis thesisToEditByStudentState;

    @Override
    public void createThesis(Thesis thesis) throws BusinessException {
        thesis.setDate(new Date());
        thesis.setAccepted(false);
        thesisFacadeLocal.create(thesis);
    }

    @Override
    public List<Users> getStudentList() {
        return usersFacadeLocal.findActiveStudents();
    }

    @Override
    public Teachers getLoggedTeacher(String login) {
        return teachersFacadeLocal.findByLogin(login);
    }

    @Override
    public Map<Teachers, Integer> getTeachersMap() {
        List<Teachers> allTeachers = teachersFacadeLocal.findAll();
        Map<Teachers, Integer> mapToReturn = new HashMap<>();
        allTeachers.stream().filter((t) -> (t.isIsActive())).forEach((t) -> {
            mapToReturn.put(t, t.getThesisList().size());
        });
        return mapToReturn;
    }

    @Override
    public List<Teachers> getTeachers() {
        List<Teachers> findAll = teachersFacadeLocal.findAll();
        List<Teachers> listToReturn = new ArrayList();
        findAll.stream().filter((t) -> (t.isIsActive())).forEach((t) -> {
            listToReturn.add(t);
        });
        return listToReturn;
    }

    @Override
    public Students getLoggedStudent(String loggedUserLogin) {
        return studentsFacadeLocal.findByLogin(loggedUserLogin);
    }

    @Override
    public List<Thesistype> getThesisTypeList() {
        return thesistypeFacadeLocal.findAll();
    }

    @Override
    public List<Thesis> getAllThesisList() {
        return thesisFacadeLocal.findAll();
    }

    @Override
    public List<Thesis> getThesisWithPhrase(String phrase) {
        return thesisFacadeLocal.findWithPhrase(phrase);
    }

    @Override
    public Thesis getThesisToEditByTeacher(Thesis row) {
        thesisToEditByTeacherState = thesisFacadeLocal.find(row.getThesisId());
        return thesisToEditByTeacherState;
    }

    @Override
    public void acceptation(Thesis thesisToEdit) throws BusinessException {

        if (thesisToEditByTeacherState == null) {
            throw new NullThesisStateException();
        }

        if (!thesisToEditByTeacherState.equals(thesisToEdit)) {
            throw new ThesisStateMismatchException();
        }
        if (thesisToEditByTeacherState.getExam() != null) {

            if (thesisToEditByTeacherState.getExam().getAccepted()) {
                throw new CanNotEditThesisWhichHasConfirmedExam();
            }
        }

        thesisToEditByTeacherState.setAccepted(thesisToEdit.getAccepted());

        thesisFacadeLocal.edit(thesisToEditByTeacherState);
    }

    @Override
    public List<Thesis> getMyThesis(Students loggedStudent) {
        return thesisFacadeLocal.findMyThesis(loggedStudent.getAccessLevelId());
    }

    @Override
    public Thesis getThesisToEditByStudent(Thesis thesis) {
        thesisToEditByStudentState = thesisFacadeLocal.find(thesis.getThesisId());
        return thesisToEditByStudentState;
    }

    @Override
    public void editThesisByStudent(Thesis thesis) throws BusinessException {

        if (thesisToEditByStudentState == null) {
            throw new NullThesisStateException();
        }

        if (!thesisToEditByStudentState.equals(thesis)) {
            throw new ThesisStateMismatchException();
        }

        if (thesisToEditByStudentState.getAccepted()) {
            throw new ThesisAlreadyAcceptedException();
        }
        thesisToEditByStudentState.setName(thesis.getName());
        thesisToEditByStudentState.setTeacher(thesis.getTeacher());

        thesisFacadeLocal.edit(thesisToEditByStudentState);
    }

    @Override
    public void createExam(Exam exam) throws BusinessException {

        if (exam.getDate().before(new Date())) {
            throw new DateFromPastException();
        }

        if (!exam.getThesis().getAccepted()) {
            throw new ThesisIsNotAcceptedException();
        }

        examFacadeLocal.create(exam);
    }

    @Override
    public List<Exam> getExamsByTeacher(Teachers loggedTeacher) {
        return examFacadeLocal.findByTeacher(loggedTeacher.getAccessLevelId());
    }

    @Override
    public List<Thesis> getMyThesisByTeacher(Teachers loggedTeacher) {
        return thesisFacadeLocal.findMyThesisByTeacher(loggedTeacher.getAccessLevelId());

    }

}
