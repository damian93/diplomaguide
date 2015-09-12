/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package us.managers;

import entities.Accesslevelsdictionary;
import entities.Users;
import exceptions.BusinessException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Damian
 */
@Local
public interface UnauthenticatedUserManagerLocal {
    
    /**
     *
     * @param user
     * @param type
     * @throws exceptions.BusinessException
     */
    void createUser(Users user, String type) throws BusinessException;

    List<Accesslevelsdictionary> getAllAccessLevels();
    
}
