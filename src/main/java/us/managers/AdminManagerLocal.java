/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package us.managers;

import entities.Users;
import exceptions.BusinessException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Damian
 */
@Local
public interface AdminManagerLocal {

    List<Users> getUsersList();

    Users getUser(Users rowData);

    void editUserByAdmin(Users userState,Users userToEdit, String oldPassword) throws BusinessException;

    List<Users> filter(String matchLogin);
    
}
