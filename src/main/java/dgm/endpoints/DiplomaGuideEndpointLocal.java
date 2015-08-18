/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dgm.endpoints;

import entities.Students;
import entities.Teachers;
import entities.Thesis;
import entities.Thesistype;
import entities.Users;
import exceptions.BusinessException;
import java.util.List;
import java.util.Map;
import javax.ejb.Local;

/**
 *
 * @author Damian
 */
@Local
public interface DiplomaGuideEndpointLocal {
    
    List<Users> getStudentList();
    void createThesis(Thesis t)  throws BusinessException;
    Teachers getLoggedTeacher(String login);
    Map<Teachers, Integer> getTeachersMap();
    List<Teachers> getTeachers();
    Students getLoggedStudent(String loggedUserLogin);
    List<Thesistype> getThesisTypeList();
    List<Thesis> getAllThesisList();
    List<Thesis> getThesisWithPhrase(String phrase);
    Thesis getThesisToEditByTeacher(Thesis row);
    void acceptation(Thesis thesisToEdit) throws BusinessException;
    List<Thesis> getMyThesis(Students loggedStudent);
    Thesis getThesisToEditByStudent(Thesis thesis);
    void editThesisByStudent(Thesis thesis) throws BusinessException;
    
}
