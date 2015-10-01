/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package us.managers;

import common.TrackerInterceptor;
import entities.Accesslevel;
import entities.Administrators;
import entities.Students;
import entities.Teachers;
import entities.Users;
import exceptions.BadAccessLevelsCombinationException;
import exceptions.BusinessException;
import exceptions.PasswordMismatchException;
import exceptions.PasswordTooShortException;
import exceptions.PasswordUsedInThePastException;
import exceptions.UserStateMismatchException;
import exceptions.UsersNullStateException;
import java.util.Collections;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;
import us.facades.UsersFacadeLocal;
import utils.ConvertUtils;
import utils.ResourceBundleUtils;

/**
 *
 * @author Damian
 */
@Stateless
@Interceptors({TrackerInterceptor.class})
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class AdminManager implements AdminManagerLocal {

    @EJB
    UsersFacadeLocal usersFacadeLocal;

    @Override
    @RolesAllowed("getUsersList")
    public List<Users> getUsersList() {

        List<Users> listToReturn = usersFacadeLocal.findAll();

        if (listToReturn == null || listToReturn.isEmpty()) {
            return Collections.EMPTY_LIST;
        } else {
            return listToReturn;
        }

    }

    @Override
    @RolesAllowed("getUser1")
    public Users getUser(Users rowData) {
        return usersFacadeLocal.find(rowData.getId());

    }

    private Users checkAndEditPasswordThenReturnUser(Users userState, String oldPassword, String newPassword) throws BusinessException {

        if (!oldPassword.isEmpty()) {
            int minPassLength = Integer.parseInt(ResourceBundleUtils.getResourceBundleBusinessProperty("minPassLength"));

            if (oldPassword.length() >= minPassLength) {

                if (!oldPassword.equals(newPassword)) {
                    throw new PasswordMismatchException();
                }

                String calculateSha512Hash = ConvertUtils.calculateSha512Hash(oldPassword);
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

    private Users setAndReturnAccessLevelCollection(Users userToEdit) throws BadAccessLevelsCombinationException {
        List<Accesslevel> accesslevelCollection = userToEdit.getAccesslevelCollection();
        boolean adminExists = false;
        boolean studentExists = false;
        boolean teacherExists = false;

        for (Accesslevel a : accesslevelCollection) {

            if (a instanceof Administrators && a.isIsActive()) {
                adminExists = true;
            } else if (a instanceof Students && a.isIsActive()) {
                studentExists = true;
            } else if (a instanceof Teachers && a.isIsActive()) {
                teacherExists = true;
            }
        }
        if ((teacherExists && adminExists) || (studentExists && adminExists) || (studentExists && teacherExists)) {
            throw new BadAccessLevelsCombinationException();
        }
        return userToEdit;
    }

    @Override
    @RolesAllowed("editUserByAdmin")
    public void editUserByAdmin(Users userState, Users userToEdit, String oldPassword, String newPassword) throws BusinessException {

        if (userState == null) {
            throw new UsersNullStateException();
        }

        if (!userState.equals(userToEdit)) {
            throw new UserStateMismatchException();
        }
        userState = checkAndEditPasswordThenReturnUser(userState, oldPassword, newPassword);

        userToEdit = setAndReturnAccessLevelCollection(userToEdit);
        userState.setAccesslevelCollection((userToEdit.getAccesslevelCollection()));

        userState.setIsActive(userToEdit.getIsActive());
        userState.setName(userToEdit.getName());
        userState.setSurname(userToEdit.getSurname());
        userState.setEmail(userToEdit.getEmail());

        usersFacadeLocal.edit(userState);

    }

    @Override
    @RolesAllowed("filter")
    public List<Users> filter(String matchLogin) {
        return usersFacadeLocal.filter(matchLogin);
    }

}
