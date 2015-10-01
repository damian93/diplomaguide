/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dgm.facades;

import entities.Thesis;
import exceptions.BusinessException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Damian
 */
@Local
public interface ThesisFacadeLocal {

    void create(Thesis thesis) throws BusinessException;

    void edit(Thesis thesis) throws BusinessException;

    Thesis find(Object id);

    List<Thesis> findAll();

    List<Thesis> findWithPhrase(String phrase);

    List<Thesis> findMyThesis(Long accessLevelId);

    List<Thesis> findMyThesisByTeacher(Long accessLevelId);

}
