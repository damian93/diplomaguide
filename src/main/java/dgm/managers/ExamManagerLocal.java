/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dgm.managers;

import common.CommisionTeachersUtils;
import entities.Exam;
import entities.Students;
import entities.Teachers;
import exceptions.BusinessException;
import java.util.List;
import java.util.Set;
import javax.ejb.Local;

/**
 *
 * @author Damian
 */
@Local
public interface ExamManagerLocal {

    void createExam(Exam exam) throws BusinessException;

    List<Exam> getExamsByTeacher(Teachers loggedTeacher);

    List<Exam> getMyExamsByStudent(Students loggedStudent);

    Exam getExamToEdit(Exam e);

    void editExamByStudent(Exam examToEditState,Exam edit) throws BusinessException;

    void ediExamByTeacher(Exam examToEditState, Exam examToEdit) throws BusinessException;

    Exam getExamToAddCommision(Exam e);

    void addCommision(Exam examToAddCommision, Exam exam, Set<Teachers> commisionTeachers) throws BusinessException;

    void acceptCommision(Teachers teacher, int rowIndex) throws BusinessException;

    void rejectCommision(Teachers teacher, int rowIndex) throws BusinessException;

    void editCommision(Exam examToEditCommision, Exam exam, Set<Teachers> commisionTeachers) throws BusinessException;

    Exam getExamToEditCommision(Exam e);

    CommisionTeachersUtils setMembersInCommision(Exam exam, Teachers loggedTeacher, List<Teachers> list);

    void setGrade(Exam examToSetGradeState, Exam examToEdit) throws BusinessException;

    Exam getExamToSetGrade(Exam e);

    void confirmGrade(Exam examToEditState, Exam examToEdit) throws BusinessException;
    
}
