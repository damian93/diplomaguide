/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package us.facades;

import entities.Accesslevelsdictionary;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Damian
 */
@Local
public interface AccesslevelsdictionaryFacadeLocal {

    List<Accesslevelsdictionary> findAll();
}
