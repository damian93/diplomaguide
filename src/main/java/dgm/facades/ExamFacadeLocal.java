/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dgm.facades;

import entities.Exam;
import exceptions.BusinessException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Damian
 */
@Local
public interface ExamFacadeLocal {

    void create(Exam exam) throws BusinessException;

    void edit(Exam exam) throws BusinessException;

    void remove(Exam exam);

    Exam find(Object id);

    List<Exam> findAll();

    List<Exam> findRange(int[] range);

    int count();

    List<Exam> findByTeacher(Long accessLevelId);
    
}
