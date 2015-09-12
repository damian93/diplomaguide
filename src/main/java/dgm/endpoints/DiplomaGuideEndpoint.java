/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dgm.endpoints;

import common.CommisionTeachersUtils;
import dgm.facades.CommissionFacadeLocal;
import dgm.facades.ExamFacadeLocal;
import dgm.facades.StudentsFacadeLocal;
import dgm.facades.TeachersFacadeLocal;
import dgm.facades.ThesisFacadeLocal;
import dgm.facades.ThesistypeFacadeLocal;
import dgm.facades.UsersFacadeLocal;
import entities.Commission;
import entities.Exam;
import entities.Students;
import entities.Teachers;
import entities.Thesis;
import entities.Thesistype;
import entities.Users;
import exceptions.BusinessException;
import exceptions.CanNotEditThesisWhichHasConfirmedExam;
import exceptions.CantEditAcceptedExamException;
import exceptions.CantEditExamAfterExamDate;
import exceptions.CantSetGradeBeforeExamException;
import exceptions.CantSetGradeWhenExamNotAccepted;
import exceptions.CommissionMembersHasToBeUniqueException;
import exceptions.DateFromPastException;
import exceptions.ExamHasAlreadyCommisionException;
import exceptions.ExamStateMismatchException;
import exceptions.NullExamStateException;
import exceptions.NullThesisStateException;
import exceptions.ThesisAlreadyAcceptedException;
import exceptions.ThesisIsNotAcceptedException;
import exceptions.ThesisStateMismatchException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import utils.ResourceBundleUtils;

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

    @EJB
    private CommissionFacadeLocal commissionFacadeLocal;

    private Thesis thesisToEditByTeacherState;
    private Thesis thesisToEditByStudentState;
    private Exam examToEditState;
    private Exam examToAddCommision;
    private Exam examToEditCommision;
    private Exam examToSetGradeState;

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

        thesisToEditByTeacherState = null;
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

        thesisToEditByStudentState = null;
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

    @Override
    public List<Exam> getMyExamsByStudent(Students loggedStudent) {
        return examFacadeLocal.findByStudent(loggedStudent.getAccessLevelId());
    }

    @Override
    public Exam getExamToEdit(Exam e) {
        examToEditState = examFacadeLocal.find(e.getExamId());
        return examToEditState;
    }

    @Override
    public void editExamByStudent(Exam edit) throws BusinessException {

        if (examToEditState == null) {
            throw new NullExamStateException();
        }

        if (!examToEditState.equals(edit)) {
            throw new ExamStateMismatchException();
        }

        examToEditState.setAccepted(edit.getAccepted());

        examFacadeLocal.edit(examToEditState);

        examToEditState = null;
    }

    @Override
    public void editExamByTeacher(Exam examToEdit) throws BusinessException {

        if (examToEditState == null) {
            throw new NullExamStateException();
        }

        if (!examToEditState.equals(examToEdit)) {
            throw new ExamStateMismatchException();
        }
        if (new Date().after(examToEditState.getDate())) {
            throw new CantEditExamAfterExamDate();

        }
        if (examToEditState.getAccepted()) {
            throw new CantEditAcceptedExamException();
        }
        examToEditState.setDate(examToEdit.getDate());

        examFacadeLocal.edit(examToEditState);

        examToEditState = null;
    }

    @Override
    public Exam getExamToAddCommision(Exam e) {
        examToAddCommision = examFacadeLocal.find(e.getExamId());
        return examToAddCommision;

    }

    @Override
    public void addCommission(Exam exam, Set<Teachers> commisionTeachers) throws BusinessException {

        if (examToAddCommision == null) {
            throw new NullExamStateException();
        }

        if (!examToAddCommision.equals(exam)) {
            throw new ExamStateMismatchException();
        }

        if (examToAddCommision.getAccepted()) {
            throw new CantEditAcceptedExamException();
        }

        if (!examToAddCommision.getCommissionCollection().isEmpty()) {
            throw new ExamHasAlreadyCommisionException();
        }

        if (commisionTeachers.size() < Integer.parseInt(ResourceBundleUtils.
                getResourceBundleBusinessProperty("CommisionMember"))) {
            throw new CommissionMembersHasToBeUniqueException();
        }

        List<Commission> commissionCollection = this.examToAddCommision.getCommissionCollection();
        if (commissionCollection.isEmpty()) {
            commissionCollection = new ArrayList<>();

            for (Teachers t : commisionTeachers) {
                Commission commission = new Commission();
                commission.setExam(examToAddCommision);
                commission.setAccepted(false);
                commission.setChairman(false);
                commission.setTeacher(t);
                commissionCollection.add(commission);
            }
        }

        commissionCollection.get(0).setChairman(true);
        examToAddCommision.setCommissionCollection(commissionCollection);
        examFacadeLocal.edit(examToAddCommision);

        examToAddCommision = null;
    }

    @Override
    public void acceptCommision(Teachers teacher, int rowIndex) throws BusinessException {

        Commission c = teacher.getCommissionCollection().get(rowIndex);

        if (c.getExam().isAccepted()) {
            throw new CantEditAcceptedExamException();
        }
        c = commissionFacadeLocal.find(c.getIdentyfikator());
        c.setAccepted(true);
    }

    @Override
    public void rejectCommision(Teachers teacher, int rowIndex) throws BusinessException {

        Commission c = teacher.getCommissionCollection().get(rowIndex);

        if (c.getExam().isAccepted()) {
            throw new CantEditAcceptedExamException();
        }
        c = commissionFacadeLocal.find(c.getIdentyfikator());
        c.setAccepted(false);

    }

    @Override
    public void editCommission(Exam exam, Set<Teachers> commisionTeachers) throws BusinessException {

        if (examToEditCommision == null) {
            throw new NullExamStateException();
        }

        if (!examToEditCommision.equals(exam)) {
            throw new ExamStateMismatchException();
        }

        if (examToEditCommision.getAccepted()) {
            throw new CantEditAcceptedExamException();
        }

        if (commisionTeachers.size() < Integer.parseInt(ResourceBundleUtils.
                getResourceBundleBusinessProperty("CommisionMember"))) {
            throw new CommissionMembersHasToBeUniqueException();
        }

        List<Commission> commissionCollection = this.examToEditCommision.getCommissionCollection();

        List<Teachers> comList = new ArrayList<>(commisionTeachers);

        for (int i = 0; i < commissionCollection.size(); i++) {
            if (!commissionCollection.get(i).getTeacher().getAccessLevelId().
                    equals(comList.get(i).getAccessLevelId())) {
                commissionCollection.get(i).setAccepted(false);
            }
            commissionCollection.get(i).setTeacher(comList.get(i));

        }
        commissionCollection.get(0).setChairman(true);
        examToEditCommision.setCommissionCollection(commissionCollection);
        examFacadeLocal.edit(examToEditCommision);

        examToEditCommision = null;

    }

    @Override
    public Exam getExamToEditCommision(Exam e) {
        examToEditCommision = examFacadeLocal.find(e.getExamId());
        return examToEditCommision;

    }

    @Override
    public CommisionTeachersUtils setMembersInCommision(Exam exam, Teachers loggedTeacher) {
        CommisionTeachersUtils ctu = new CommisionTeachersUtils();
        if (exam.getCommissionCollection().size() == Integer.parseInt(ResourceBundleUtils.
                getResourceBundleBusinessProperty("CommisionMember"))) {
            for (Commission c : exam.getCommissionCollection()) {
                if (c.getChairman()) {
                    ctu.setChairman(c.getTeacher());
                } else {
                    if (ctu.getTeacher1() == null) {
                        ctu.setTeacher1(c.getTeacher());
                    } else if (ctu.getTeacher2() == null) {
                        ctu.setTeacher2(c.getTeacher());
                    }
                }
            }
        }
        ctu.setTeachersSet(new HashSet(getTeachers()));

        for (Iterator<Teachers> i = ctu.getTeachersSet().iterator(); i.hasNext();) {
            Teachers tmp = i.next();
            if (tmp.equals(loggedTeacher)) {
                i.remove();
            }
        }
        return ctu;

    }

    /**
     *
     * @param examToEdit
     * @throws BusinessException
     */
    @Override
    public void setGrade(Exam examToEdit) throws BusinessException {

        if (examToSetGradeState == null) {
            throw new NullExamStateException();
        }

        if (!examToSetGradeState.equals(examToEdit)) {
            throw new ExamStateMismatchException();
        }
        if (!examToSetGradeState.getAccepted()) {
            throw new CantSetGradeWhenExamNotAccepted();
        }
        if (!new Date().after(examToSetGradeState.getDate())) {
            throw new CantSetGradeBeforeExamException();

        }
        examToSetGradeState.setGrade(examToEdit.getGrade());

        examFacadeLocal.edit(examToSetGradeState);

        examToSetGradeState = null;

    }

    @Override
    public Exam getExamToSetGrade(Exam e) {
        examToSetGradeState = examFacadeLocal.find(e.getExamId());
        return examToSetGradeState;
    }

}
