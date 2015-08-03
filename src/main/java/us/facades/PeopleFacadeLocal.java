/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package us.facades;

import entities.People;
import exceptions.BusinessException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Damian
 */
@Local
public interface PeopleFacadeLocal {

    void create(People people) throws BusinessException;

    void edit(People people) throws BusinessException;

    void remove(People people);

    People find(Object id);

    List<People> findAll();

    List<People> findRange(int[] range);

    int count();
    
}
