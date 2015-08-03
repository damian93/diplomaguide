/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package us.facades;

import entities.Degrees;
import exceptions.BusinessException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Damian
 */
@Local
public interface DegreesFacadeLocal {

    void create(Degrees degrees) throws BusinessException;

    void edit(Degrees degrees) throws BusinessException;

    void remove(Degrees degrees);

    Degrees find(Object id);

    List<Degrees> findAll();

    List<Degrees> findRange(int[] range);

    int count();
    
}
