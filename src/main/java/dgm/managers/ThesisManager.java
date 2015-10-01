/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dgm.managers;

import dgm.facades.StudentsFacadeLocal;
import dgm.facades.TeachersFacadeLocal;
import dgm.facades.ThesisFacadeLocal;
import dgm.facades.ThesistypeFacadeLocal;
import entities.Students;
import entities.Teachers;
import entities.Thesis;
import entities.Thesistype;
import exceptions.BusinessException;
import exceptions.CanNotEditThesisWhichHasConfirmedExam;
import exceptions.NullThesisStateException;
import exceptions.ThesisAlreadyAcceptedException;
import exceptions.ThesisStateMismatchException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

/**
 *
 * @author Damian
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class ThesisManager implements ThesisManagerLocal {

    @EJB
    private ThesisFacadeLocal thesisFacadeLocal;

    @EJB
    private TeachersFacadeLocal teachersFacadeLocal;

    @EJB
    private StudentsFacadeLocal studentsFacadeLocal;

    @EJB
    private ThesistypeFacadeLocal thesistypeFacadeLocal;

    @Override
    @RolesAllowed("createThesis")
    public void createThesis(Thesis thesis) throws BusinessException {
        thesis.setDate(new Date());
        thesis.setAccepted(false);
        thesisFacadeLocal.create(thesis);

    }

    private Date getProperDate() throws ParseException {

        int year = Calendar.getInstance().get(Calendar.YEAR);
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date date = df.parse("01-07-" + year);
        if (new Date().after(date)) {
            year = year + 1;
            date = df.parse("01-07-" + year);
        }
        return date;

    }

    private Map<Teachers, Integer> getThesisFromActualYear(List<Teachers> listToFilter) {

        try {
            Map<Teachers, Integer> mapToReturn = new HashMap<>();

            int size;
            Date date = getProperDate();

            for (Teachers t : listToFilter) {
                size = 0;
                if (t.isIsActive()) {
                    for (Thesis thesis : t.getThesisList()) {
                        if (thesis.getDate().before(date)) {
                            size++;
                        }
                    }
                    mapToReturn.put(t, size);
                }
            }
            return mapToReturn;
        } catch (ParseException ex) {
            Logger.getLogger(ThesisManager.class.getName()).log(Level.SEVERE, null, ex);

        }
        return Collections.EMPTY_MAP;

    }

    @Override
    @RolesAllowed("getTeachersMap")
    public Map<Teachers, Integer> getTeachersMap() {

        List<Teachers> allTeachers = teachersFacadeLocal.findAll();

        return getThesisFromActualYear(allTeachers);

    }

    private List<Teachers> getActiveTeachers(List<Teachers> allTeachers) {
        List<Teachers> listToReturn = new ArrayList();

        for (Teachers t : allTeachers) {
            if (t.isIsActive()) {
                listToReturn.add(t);
            }

        }
        return listToReturn;
    }

    @Override
    @RolesAllowed("getTeachers")
    public List<Teachers> getTeachers() {
        List<Teachers> findAll = teachersFacadeLocal.findAll();

        return getActiveTeachers(findAll);
    }

    @Override
    @RolesAllowed("getLoggedStudent")
    public Students getLoggedStudent(String loggedUserLogin) {
        return studentsFacadeLocal.findByLogin(loggedUserLogin);
    }

    @Override
    @RolesAllowed("getThesisTypeList")
    public List<Thesistype> getThesisTypeList() {
        return thesistypeFacadeLocal.findAll();
    }

    @Override
    @RolesAllowed("getAllThesisList")
    public List<Thesis> getAllThesisList() {
        return thesisFacadeLocal.findAll();
    }

    @Override
    @RolesAllowed("getThesisWithPhrase")
    public List<Thesis> getThesisWithPhrase(String phrase) {
        return thesisFacadeLocal.findWithPhrase(phrase);
    }

    @Override
    @RolesAllowed("getThesisToEditByTeacher")
    public Thesis getThesisToEditByTeacher(Thesis row) {
        return thesisFacadeLocal.find(row.getThesisId());
    }

    @Override
    @RolesAllowed("acceptation")
    public void acceptThesis(Thesis thesisToEditByTeacherState, Thesis thesisToEdit) throws BusinessException {
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
    @RolesAllowed("getMyThesis")
    public List<Thesis> getMyThesis(Students loggedStudent) {
        return thesisFacadeLocal.findMyThesis(loggedStudent.getAccessLevelId());
    }

    @Override
    @RolesAllowed("getThesisToEditByStudent")
    public Thesis getThesisToEditByStudent(Thesis thesis) {
        return thesisFacadeLocal.find(thesis.getThesisId());
    }

    @Override
    @RolesAllowed("editThesisByStudent")
    public void editThesisByStudent(Thesis thesisToEditByStudentState, Thesis thesis) throws BusinessException {
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
    @RolesAllowed("getMyThesisByTeacher")
    public List<Thesis> getMyThesisByTeacher(Teachers loggedTeacher) {
        return thesisFacadeLocal.findMyThesisByTeacher(loggedTeacher.getAccessLevelId());
    }

}
