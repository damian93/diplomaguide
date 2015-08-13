/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dgm.endpoints;

import dgm.facades.StudentsFacadeLocal;
import dgm.facades.TeachersFacadeLocal;
import dgm.facades.ThesisFacadeLocal;
import dgm.facades.ThesistypeFacadeLocal;
import dgm.facades.UsersFacadeLocal;
import entities.Students;
import entities.Teachers;
import entities.Thesis;
import entities.Thesistype;
import entities.Users;
import exceptions.BusinessException;
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

}
