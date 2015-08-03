/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package us.endpoints;

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
public interface UserServiceEndpointLocal {

    void createUser(Users user, String type) throws BusinessException;

    List<Accesslevelsdictionary> getAllAccessLevels();

    Users getUser(String name) throws BusinessException;

    List<Users> getUsersList();

    Users getUser(Users rowData);

    void editUserByAdmin(Users userToEdit, String oldPassword, String newPassword) throws BusinessException;

    List<Users> filter(String matchLogin);

    void editUser(String userOldPassword, String userNewPassword, Users authorizedUser) throws BusinessException;
    
    Users getUserToEdit(String name) throws BusinessException;

}
