/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dgm.managers;

import common.CommisionTeachersUtils;
import dgm.facades.CommissionFacadeLocal;
import dgm.facades.ExamFacadeLocal;
import entities.Commission;
import entities.Exam;
import entities.Students;
import entities.Teachers;
import exceptions.BusinessException;
import exceptions.CantAcceptExamWithoutCommision;
import exceptions.CantEditAcceptedExamException;
import exceptions.CantEditExamAfterExamDate;
import exceptions.CantRemoveMemberWhoAcceptCommision;
import exceptions.CantSetGradeBeforeExamException;
import exceptions.CantSetGradeWhenExamNotAccepted;
import exceptions.CommissionMembersHasToBeUniqueException;
import exceptions.DateFromPastException;
import exceptions.ExamHasAlreadyCommisionException;
import exceptions.ExamStateMismatchException;
import exceptions.NullExamStateException;
import exceptions.SomeMemberOfCommissionDidntAcceptMembershipException;
import exceptions.ThesisIsNotAcceptedException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import utils.ResourceBundleUtils;

/**
 *
 * @author Damian
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class ExamManager implements ExamManagerLocal {

    @EJB
    private ExamFacadeLocal examFacadeLocal;

    @EJB
    private CommissionFacadeLocal commissionFacadeLocal;

    @EJB
    private ThesisManagerLocal thesisManagerLocal;

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
    public List<Exam> getMyExamsByStudent(Students loggedStudent) {
        return examFacadeLocal.findByStudent(loggedStudent.getAccessLevelId());
    }

    @Override
    public Exam getExamToEdit(Exam e) {
        return examFacadeLocal.find(e.getExamId());
    }

    @Override
    public void editExamByStudent(Exam examToEditState, Exam edit) throws BusinessException {
        if (examToEditState == null) {
            throw new NullExamStateException();
        }

        if (!examToEditState.equals(edit)) {
            throw new ExamStateMismatchException();
        }
        if (examToEditState.getCommissionCollection().isEmpty()) {
            throw new CantAcceptExamWithoutCommision();
        }
        for(Commission c : examToEditState.getCommissionCollection()){
            if(!c.isAccepted()){
                throw new SomeMemberOfCommissionDidntAcceptMembershipException();
            }
        }

        examToEditState.setAccepted(edit.getAccepted());

        examFacadeLocal.edit(examToEditState);
    }

    @Override
    public void ediExamByTeacher(Exam examToEditState, Exam examToEdit) throws BusinessException {
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
    }

    @Override
    public Exam getExamToAddCommision(Exam e) {
        return examFacadeLocal.find(e.getExamId());
    }

    @Override
    public void addCommision(Exam examToAddCommision, Exam exam, Set<Teachers> commisionTeachers) throws BusinessException {
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

        List<Commission> commissionCollection = examToAddCommision.getCommissionCollection();
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
    public void editCommision(Exam examToEditCommision, Exam exam, Set<Teachers> commisionTeachers) throws BusinessException {
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

        List<Commission> commissionCollection = examToEditCommision.getCommissionCollection();

        List<Teachers> comList = new ArrayList<>(commisionTeachers);

        for (int i = 0; i < commissionCollection.size(); i++) {
            if (!commissionCollection.get(i).getTeacher().getAccessLevelId().
                    equals(comList.get(i).getAccessLevelId())) {
                if(commissionCollection.get(i).isAccepted()){
                    throw new CantRemoveMemberWhoAcceptCommision();
                }
                commissionCollection.get(i).setAccepted(false);
                commissionCollection.get(i).setTeacher(comList.get(i));
            }

        }
        commissionCollection.get(0).setChairman(true);
        examToEditCommision.setCommissionCollection(commissionCollection);
        examFacadeLocal.edit(examToEditCommision);
    }

    @Override
    public Exam getExamToEditCommision(Exam e) {
        return examFacadeLocal.find(e.getExamId());
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
        ctu.setTeachersSet(new HashSet(thesisManagerLocal.getTeachers()));

        for (Iterator<Teachers> i = ctu.getTeachersSet().iterator(); i.hasNext();) {
            Teachers tmp = i.next();
            if (tmp.equals(loggedTeacher)) {
                i.remove();
            }
        }
        return ctu;

    }

    @Override
    public void setGrade(Exam examToSetGradeState, Exam examToEdit) throws BusinessException {

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
    }

    @Override
    public Exam getExamToSetGrade(Exam e) {
        return examFacadeLocal.find(e.getExamId());
    }

}
