/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package us.facades;

import entities.Students;
import exceptions.BusinessException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Damian
 */
@Local
public interface StudentsFacadeLocal {

    void create(Students students) throws BusinessException;

    void edit(Students students) throws BusinessException;

    void remove(Students students);

    Students find(Object id);

    List<Students> findAll();

    List<Students> findRange(int[] range);

    int count();
    
}
