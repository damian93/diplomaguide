/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dgm.facades;

import entities.Thesistype;
import exceptions.BusinessException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Damian
 */
@Local
public interface ThesistypeFacadeLocal {

    void create(Thesistype thesistype) throws BusinessException;

    void edit(Thesistype thesistype) throws BusinessException;

    void remove(Thesistype thesistype);

    Thesistype find(Object id);

    List<Thesistype> findAll();

    List<Thesistype> findRange(int[] range);

    int count();
    
}
