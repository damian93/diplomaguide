/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package us.facades;

import entities.Accesslevel;
import exceptions.BusinessException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Damian
 */
@Local
public interface AccesslevelFacadeLocal {

    void create(Accesslevel accesslevel) throws BusinessException;

    void edit(Accesslevel accesslevel) throws BusinessException;

    void remove(Accesslevel accesslevel);

    Accesslevel find(Object id);

    List<Accesslevel> findAll();

    List<Accesslevel> findRange(int[] range);

    int count();
    
}
