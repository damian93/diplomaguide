/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package us.endpoints;

import entities.Accesslevelsdictionary;
import entities.Degrees;
import entities.Users;
import exceptions.BusinessException;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import us.managers.AdminManagerLocal;
import us.managers.AuthenticatedUserManagerLocal;
import us.managers.UnauthenticatedUserManagerLocal;

/**
 *
 * @author Damian
 */
@Stateful
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class UserServiceEndpoint implements UserServiceEndpointLocal {

    private Users userState;

    @EJB
    UnauthenticatedUserManagerLocal unauthenticatedUserManagerLocal;

    @EJB
    AuthenticatedUserManagerLocal authenticatedUserManagerLocal;

    @EJB
    AdminManagerLocal adminManagerLocal;

    @Override
    public void createUser(Users user, String type) throws BusinessException {
        unauthenticatedUserManagerLocal.createUser(user, type);

    }

    @Override
    public List<Accesslevelsdictionary> getAllAccessLevels() {
        return unauthenticatedUserManagerLocal.getAllAccessLevels();

    }

    @Override
    public Users getUserToEdit(String name) throws BusinessException {
        userState = authenticatedUserManagerLocal.getUserToEdit(name);
        return userState;
    }

    @Override
    public Users getUser(String name) throws BusinessException {
        return authenticatedUserManagerLocal.getUser(name);

    }

    @Override
    public List<Users> getUsersList() {
        return adminManagerLocal.getUsersList();

    }

    @Override
    public Users getUser(Users rowData) {
        userState = adminManagerLocal.getUser(rowData);
        return userState;
    }

    @Override
    public void editUserByAdmin(Users userToEdit, String oldPassword, String newPassword) throws BusinessException {
        adminManagerLocal.editUserByAdmin(userState, userToEdit, oldPassword, newPassword);
        userState = null;
    }

    @Override
    public List<Users> filter(String matchLogin) {
        return adminManagerLocal.filter(matchLogin);

    }

    @Override
    public void editUser(String userOldPassword, String userNewPassword, Users authorizedUser) throws BusinessException {
        authenticatedUserManagerLocal.editUser(userOldPassword, userNewPassword, authorizedUser, userState);
        userState = null;

    }

    @Override
    public List<Degrees> getDegreeList() {
        return authenticatedUserManagerLocal.getDegreesList();
    }

}
