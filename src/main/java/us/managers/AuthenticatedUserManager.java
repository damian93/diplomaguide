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
    public Users getUserToEdit(String name) throws BusinessException {
        return usersFacadeLocal.findByLogin(name);

    }

    @Override
    public Users getUser(String name) throws BusinessException {
        return usersFacadeLocal.findByLogin(name);
    }

    @Override
    public void editUser(String userOldPassword, String userNewPassword, Users authorizedUser, Users userState) throws BusinessException {
        int minPassLength = Integer.parseInt(ResourceBundleUtils.getResourceBundleBusinessProperty("minPassLength"));

        if (userState == null) {
            throw new UsersNullStateException();
        }

        if (!userState.equals(authorizedUser)) {
            throw new UserStateMismatchException();
        }

        if (!userNewPassword.isEmpty()) {

            String tmpPassHash = ConvertUtils.calculateSha512Hash(userOldPassword);

            if (!tmpPassHash.equals(userState.getPassword())) {
                throw new OldPasswordMismatchException();
            }

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

    }

    @Override
    public List<Degrees> getDegreesList() {
        return degreesFacadeLocal.findAll();
    }

}
