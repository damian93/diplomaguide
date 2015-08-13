/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dgm.facades;

import entities.Accesslevelsdictionary;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Damian
 */
@Local
public interface AccesslevelsdictionaryFacadeLocal {


    Accesslevelsdictionary find(Object id);

    List<Accesslevelsdictionary> findAll();

    List<Accesslevelsdictionary> findRange(int[] range);

    int count();
    
}
