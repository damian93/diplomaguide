/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dgm.facades;

import entities.Administrators;
import exceptions.BusinessException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Damian
 */
@Local
public interface AdministratorsFacadeLocal {

    void create(Administrators administrators) throws BusinessException;

    void edit(Administrators administrators) throws BusinessException;

    void remove(Administrators administrators);

    Administrators find(Object id);

    List<Administrators> findAll();

    List<Administrators> findRange(int[] range);

    int count();
    
}
