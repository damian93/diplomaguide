/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package us.managers;

import entities.Degrees;
import entities.Users;
import exceptions.BusinessException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Damian
 */
@Local
public interface AuthenticatedUserManagerLocal {

    Users getUserToEdit(String name) throws BusinessException;

    Users getUser(String name) throws BusinessException;

    void editUser(String userOldPassword, String userNewPassword, Users authorizedUser, Users userState) throws BusinessException;

    List<Degrees> getDegreesList();
    
}
