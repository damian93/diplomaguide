/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package us.facades;

import entities.Teachers;
import exceptions.BusinessException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Damian
 */
@Local
public interface TeachersFacadeLocal {

    void create(Teachers teachers) throws BusinessException;

    void edit(Teachers teachers) throws BusinessException;

    void remove(Teachers teachers);

    Teachers find(Object id);

    List<Teachers> findAll();

    List<Teachers> findRange(int[] range);

    int count();
    
}
