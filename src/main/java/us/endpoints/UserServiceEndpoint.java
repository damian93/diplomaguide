/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package us.endpoints;

import common.AccessLevelsFactory;
import entities.Accesslevel;
import entities.Accesslevelsdictionary;
import entities.Administrators;
import entities.Students;
import entities.Teachers;
import entities.Users;
import exceptions.BadAccessLevelsCombinationException;
import exceptions.BusinessException;
import exceptions.OldPasswordMismatchException;
import exceptions.PasswordTooShortException;
import exceptions.PasswordUsedInThePastException;
import exceptions.UserStateMismatchException;
import exceptions.UsersNullStateException;
import exceptions.WrongDateException;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import us.facades.AccesslevelFacadeLocal;
import us.facades.AccesslevelsdictionaryFacadeLocal;
import us.facades.UsersFacadeLocal;
import utils.ConvertUtils;
import utils.ResourceBundleUtils;

/**
 *
 * @author Damian
 */
@Stateful
public class UserServiceEndpoint implements UserServiceEndpointLocal {

    @EJB
    UsersFacadeLocal usersFacadeLocal;

    @EJB
    AccesslevelFacadeLocal accesslevelFacadeLocal;

    @EJB
    AccesslevelsdictionaryFacadeLocal accesslevelsdictionaryFacadeLocal;

    private Users userState;

    @Override
    public void createUser(Users user, String type) throws BusinessException {

        if (user.getDateoOfBirth() != null) {
            if (user.getDateoOfBirth().after(new Date())) {
                throw new WrongDateException();
            }
        }
        Accesslevel accesslevel = AccessLevelsFactory.getProperAccessLevel(type);

        if (accesslevel instanceof Students) {
            user.setIsActive(true);
            Students s = new Students();
            s.setUserId(user);
            s.setRegisterDate(new Date());
            user.getAccesslevelCollection().add(s);
        } else if (accesslevel instanceof Administrators) {
            user.setIsActive(false);
            Administrators a = new Administrators();
            a.setUserId(user);
            a.setRegisterDate(new Date());
            user.getAccesslevelCollection().add(a);

        } else if (accesslevel instanceof Teachers) {
            user.setIsActive(false);
            Teachers t = new Teachers();
            t.setUserId(user);
            user.getAccesslevelCollection().add(t);
        }

        user.setPassword(ConvertUtils.calculateSha512Hash(user.getPassword()));

        usersFacadeLocal.create(user);

    }

    @Override
    public List<Accesslevelsdictionary> getAllAccessLevels() {
        return accesslevelsdictionaryFacadeLocal.findAll();
    }

    @Override
    public Users getUserToEdit(String name) throws BusinessException {
        userState = usersFacadeLocal.findByLogin(name);
        return userState;
    }

    @Override
    public Users getUser(String name) throws BusinessException {
        return usersFacadeLocal.findByLogin(name);
    }

    @Override
    public List<Users> getUsersList() {

        List<Users> listToReturn = usersFacadeLocal.findAll();

        if (listToReturn == null || listToReturn.isEmpty()) {
            return Collections.EMPTY_LIST;
        } else {
            return listToReturn;
        }

    }

    @Override
    public Users getUser(Users rowData) {
        userState = usersFacadeLocal.find(rowData.getId());
        return userState;
    }

    @Override
    public void editUserByAdmin(Users userToEdit, String oldPassword, String newPassword) throws BusinessException {

        int minPassLength = Integer.parseInt(ResourceBundleUtils.getResourceBundleBusinessProperty("minPassLength"));

        if (userState == null) {
            throw new UsersNullStateException();
        }

        if (!userState.equals(userToEdit)) {
            throw new UserStateMismatchException();
        }

        if (!oldPassword.isEmpty()) {

            if (oldPassword.length() >= minPassLength) {
                String calculateSha512Hash = ConvertUtils.calculateSha512Hash(oldPassword);
                if (calculateSha512Hash.equals(userState.getPassword())) {
                    throw new PasswordUsedInThePastException();
                }
                userState.setPassword(calculateSha512Hash);
            } else {
                throw new PasswordTooShortException();
            }
        }

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
        if ((teacherExists && adminExists) || (studentExists && adminExists)) {
            throw new BadAccessLevelsCombinationException();
        }

        userState.setAccesslevelCollection((userToEdit.getAccesslevelCollection()));

        userState.setIsActive(userToEdit.getIsActive());
        userState.setName(userToEdit.getName());
        userState.setSurname(userToEdit.getSurname());
        userState.setEmail(userToEdit.getEmail());

        usersFacadeLocal.edit(userState);

        userState = null;
    }

    @Override
    public List<Users> filter(String matchLogin) {
        return usersFacadeLocal.filter(matchLogin);
    }

    @Override
    public void editUser(String userOldPassword, String userNewPassword, Users authorizedUser) throws BusinessException {

        int minPassLength = Integer.parseInt(ResourceBundleUtils.getResourceBundleBusinessProperty("minPassLength"));

        if (userState == null) {
            throw new UsersNullStateException();
        }

        if (!userState.equals(authorizedUser)) {
            throw new UserStateMismatchException();
        }

        String tmpPassHash = ConvertUtils.calculateSha512Hash(userOldPassword);

        if (!tmpPassHash.equals(userState.getPassword())) {
            throw new OldPasswordMismatchException();
        }

        if (!userNewPassword.isEmpty()) {

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

        userState.setName(authorizedUser.getName());
        userState.setSurname(authorizedUser.getSurname());
        userState.setEmail(authorizedUser.getEmail());

        usersFacadeLocal.edit(userState);

        userState = null;

    }

}
