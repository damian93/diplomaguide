/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package us.managers;

import common.AccessLevelsFactory;
import entities.Accesslevel;
import entities.Accesslevelsdictionary;
import entities.Administrators;
import entities.Students;
import entities.Teachers;
import entities.Users;
import exceptions.BusinessException;
import exceptions.WrongDateException;
import exceptions.WrongUserTypeException;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import us.facades.AccesslevelsdictionaryFacadeLocal;
import us.facades.UsersFacadeLocal;
import utils.ConvertUtils;

/**
 *
 * @author Damian
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class UnauthenticatedUserManager implements UnauthenticatedUserManagerLocal {

    @EJB
    UsersFacadeLocal usersFacadeLocal;

    @EJB
    AccesslevelsdictionaryFacadeLocal accesslevelsdictionaryFacadeLocal;

    @Override
    public void createUser(Users user, String type) throws BusinessException {

        if (user.getDateoOfBirth() != null) {
            if (user.getDateoOfBirth().after(new Date())) {
                throw new WrongDateException();
            }
        }
        user = setProperAccessLevelAndReturnUser(user, type);

        user.setPassword(ConvertUtils.calculateSha512Hash(user.getPassword()));

        usersFacadeLocal.create(user);

    }

    private Users setProperAccessLevelAndReturnUser(Users user, String type) throws WrongUserTypeException {

        Accesslevel accesslevel = AccessLevelsFactory.getProperAccessLevel(type);

        if (accesslevel instanceof Students) {

            user.setIsActive(true);

            Students s = new Students();
            s.setIsActive(true);
            s.setUserId(user);
            s.setRegisterDate(new Date());
            user.getAccesslevelCollection().add(s);

            Administrators a = new Administrators();
            a.setUserId(user);
            a.setRegisterDate(new Date());
            user.getAccesslevelCollection().add(a);

            Teachers t = new Teachers();
            t.setUserId(user);
            user.getAccesslevelCollection().add(t);

        } else if (accesslevel instanceof Administrators) {

            user.setIsActive(false);

            Administrators a = new Administrators();
            a.setUserId(user);
            a.setIsActive(true);
            a.setRegisterDate(new Date());
            user.getAccesslevelCollection().add(a);

            Students s = new Students();
            s.setUserId(user);
            user.getAccesslevelCollection().add(s);

            Teachers t = new Teachers();
            t.setUserId(user);
            user.getAccesslevelCollection().add(t);

        } else if (accesslevel instanceof Teachers) {
            user.setIsActive(false);

            Teachers t = new Teachers();
            t.setIsActive(true);
            t.setUserId(user);
            user.getAccesslevelCollection().add(t);

            Students s = new Students();
            s.setUserId(user);
            user.getAccesslevelCollection().add(s);

            Administrators a = new Administrators();
            a.setUserId(user);
            a.setRegisterDate(new Date());
            user.getAccesslevelCollection().add(a);

        }
        return user;

    }

    @Override
    public List<Accesslevelsdictionary> getAllAccessLevels() {
        return accesslevelsdictionaryFacadeLocal.findAll();
    }

}
