/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package us.managers;

import entities.Degrees;
import entities.Users;
import exceptions.BusinessException;
import exceptions.OldPasswordMismatchException;
import exceptions.PasswordTooShortException;
import exceptions.PasswordUsedInThePastException;
import exceptions.UserStateMismatchException;
import exceptions.UsersNullStateException;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import us.facades.DegreesFacadeLocal;
import us.facades.UsersFacadeLocal;
import utils.ConvertUtils;
import utils.ResourceBundleUtils;

/**
 *
 * @author Damian
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class AuthenticatedUserManager implements AuthenticatedUserManagerLocal {

    @EJB
    UsersFacadeLocal usersFacadeLocal;

    @EJB
    DegreesFacadeLocal degreesFacadeLocal;

    @Override
    @RolesAllowed("getUserToEdit")
    public Users getUserToEdit(String name) throws BusinessException {
        return usersFacadeLocal.findByLogin(name);

    }

    @Override
    @RolesAllowed("getUser")
    public Users getUser(String name) throws BusinessException {
        return usersFacadeLocal.findByLogin(name);
    }

    private Users checkAndEditPasswordsThenReturnUser(String userOldPassword, String userNewPassword, Users userState) throws BusinessException {
        if (!userNewPassword.isEmpty()) {

            String tmpPassHash = ConvertUtils.calculateSha512Hash(userOldPassword);

            if (!tmpPassHash.equals(userState.getPassword())) {
                throw new OldPasswordMismatchException();
            }
            int minPassLength = Integer.parseInt(ResourceBundleUtils.getResourceBundleBusinessProperty("minPassLength"));

            if (userNewPassword.length() >= minPassLength) {
                String calculateSha512Hash = ConvertUtils.calculateSha512Hash(userNewPassword);
                if (calculateSha512Hash.equals(userState.getPassword())) {
                    throw new PasswordUsedInThePastException();
                }
                userState.setPassword(calculateSha512Hash);
            } else {
                throw new PasswordTooShortException();
            }
        }
        return userState;
    }

    @Override
    @RolesAllowed("editUser")
    public void editUser(String userOldPassword, String userNewPassword, Users authorizedUser, Users userState) throws BusinessException {

        if (userState == null) {
            throw new UsersNullStateException();
        }

        if (!userState.equals(authorizedUser)) {
            throw new UserStateMismatchException();
        }
        userState = checkAndEditPasswordsThenReturnUser(userOldPassword, userNewPassword, userState);

        userState.setName(authorizedUser.getName());
        userState.setSurname(authorizedUser.getSurname());
        userState.setEmail(authorizedUser.getEmail());

        usersFacadeLocal.edit(userState);

    }

    @Override
    @RolesAllowed("getDegreeList")
    public List<Degrees> getDegreesList() {
        return degreesFacadeLocal.findAll();
    }

}
