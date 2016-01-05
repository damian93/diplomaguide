/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package us.managers;

import entities.Accesslevel;
import entities.Administrators;
import entities.Students;
import entities.Teachers;
import entities.Users;
import exceptions.BadAccessLevelsCombinationException;
import exceptions.BusinessLogicException;
import exceptions.PasswordMismatchException;
import exceptions.PasswordTooShortException;
import exceptions.PasswordUsedInThePastException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Damian
 */
public class AdminManagerTest {

    private static Users user;

    @Before
    public void setUp() {
        user = new Users();
    }

    @After
    public void tearDown() {
        user = null;
    }

    @Test
    public void shouldCheckAndEditPasswordThenReturnUser() throws Exception {
        user.setPassword("Janusz");
        String newPass = "JanuszNEW";
        String repeatedPass = "JanuszNEW";
        AdminManager instance = new AdminManager();
        Users result = instance.checkAndEditPasswordThenReturnUser(user, newPass, repeatedPass);
        assertEquals(result.getPassword(), DigestUtils.sha512Hex(repeatedPass));
    }

    @Test(expected = PasswordMismatchException.class)
    public void shouldThrowMismatchException() throws Exception {
        String newPass = "JanuszNEW1";
        String repeatedPass = "JanuszNEW";
        AdminManager instance = new AdminManager();
        Users result = instance.checkAndEditPasswordThenReturnUser(user, newPass, repeatedPass);
    }

    @Test(expected = PasswordTooShortException.class)
    public void shouldThrowPasswordTooShortException() throws Exception {
        String newPass = "a";
        String repeatedPass = "a";
        AdminManager instance = new AdminManager();
        Users result = instance.checkAndEditPasswordThenReturnUser(user, newPass, repeatedPass);
    }

    @Test(expected = PasswordUsedInThePastException.class)
    public void shouldThrowPasswordUsedInThePastException() throws Exception {
        user.setPassword(DigestUtils.sha512Hex("Januszek"));
        String newPass = "Januszek";
        String repeatedPass = "Januszek";
        AdminManager instance = new AdminManager();
        Users result = instance.checkAndEditPasswordThenReturnUser(user, newPass, repeatedPass);
    }

    @Test(expected = BusinessLogicException.class)
    public void shouldThrowBusinessLogicExceptionWhenAllParametersAreNull() throws Exception {
        String newPass = null;
        String repeatedPass = null;
        user = null;
        AdminManager instance = new AdminManager();
        Users result = instance.checkAndEditPasswordThenReturnUser(user, newPass, repeatedPass);
    }

    @Test(expected = BusinessLogicException.class)
    public void shouldThrowBusinessLogicExceptionWhenSecondParameterIsNull() throws Exception {
        String newPass = null;
        String repeatedPass = "s";
        AdminManager instance = new AdminManager();
        Users result = instance.checkAndEditPasswordThenReturnUser(user, newPass, repeatedPass);
    }

    @Test(expected = BusinessLogicException.class)
    public void shouldThrowBusinessLogicExceptionWhenThirdParameterIsNull() throws Exception {
        String newPass = "s";
        String repeatedPass = null;
        AdminManager instance = new AdminManager();
        Users result = instance.checkAndEditPasswordThenReturnUser(user, newPass, repeatedPass);
    }

    @Test
    public void shouldPassAccessLevelCollectionCheck() throws Exception {
        List<Accesslevel> accessLevelList = new ArrayList();
        Students students = new Students();
        students.setIsActive(true);
        accessLevelList.add(students);
        Teachers teachers = new Teachers();
        accessLevelList.add(teachers);
        Administrators administrators = new Administrators();
        accessLevelList.add(administrators);

        user.setAccesslevelCollection(accessLevelList);
        AdminManager instance = new AdminManager();
        instance.checkAccessLevelCollection(user);
    }

    @Test(expected = BadAccessLevelsCombinationException.class)
    public void shouldNotPassAccessLevelCollectionCheck() throws Exception {
        List<Accesslevel> accessLevelList = new ArrayList();
        Students students = new Students();
        students.setIsActive(true);
        accessLevelList.add(students);
        Teachers teachers = new Teachers();
        teachers.setIsActive(true);
        accessLevelList.add(teachers);
        Administrators administrators = new Administrators();
        accessLevelList.add(administrators);

        user.setAccesslevelCollection(accessLevelList);
        AdminManager instance = new AdminManager();
        instance.checkAccessLevelCollection(user);
    }

}
