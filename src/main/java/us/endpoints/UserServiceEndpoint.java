/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package us.endpoints;

import common.AbstractEndpoint;
import common.TrackerInterceptor;
import entities.Accesslevelsdictionary;
import entities.Degrees;
import entities.Users;
import exceptions.BusinessException;
import java.util.List;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.SessionSynchronization;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;
import us.managers.AdminManagerLocal;
import us.managers.AuthenticatedUserManagerLocal;
import us.managers.UnauthenticatedUserManagerLocal;

/**
 *
 * @author Damian
 */
@Stateful
@Interceptors({TrackerInterceptor.class})
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class UserServiceEndpoint extends AbstractEndpoint implements UserServiceEndpointLocal, SessionSynchronization {

    private Users userState;

    @EJB
    private UnauthenticatedUserManagerLocal unauthenticatedUserManagerLocal;

    @EJB
    private AuthenticatedUserManagerLocal authenticatedUserManagerLocal;

    @EJB
    private AdminManagerLocal adminManagerLocal;

    /**
     * Deleguje utworzenie(rejestracje) użytkownika do
     * UnauthenticadedUserManagera
     *
     * @param user obiekt użytkownika do rejestracji
     * @param type nazwa roli użytkownika
     * @throws BusinessException wyjątek aplikacyjny
     */
    @Override
    @PermitAll
    public void createUser(Users user, String type) throws BusinessException {
        unauthenticatedUserManagerLocal.createUser(user, type);

    }

    /**
     * Deleguje pobranie listy wszystkich poziomów dostępów(ról), a następnie ją
     * zwraca
     *
     * @return lista poziomów dostępów(ról)
     */
    @Override
    @PermitAll
    public List<Accesslevelsdictionary> getAllAccessLevels() {
        return unauthenticatedUserManagerLocal.getAllAccessLevels();

    }

    /**
     * Deleguje pobranie użytkownika do edycji do AuthenticatedUserManagera.
     * Zapamiętuje stan pobranego obiektu oraz zwraca ten obiekt
     *
     * @param name login użytkownika do edycji
     * @return pobrany obiekt użytkownika do edycji
     * @throws BusinessException wyjątek aplikacyjny
     */
    @Override
    @RolesAllowed("getUserToEdit")
    public Users getUserToEdit(String name) throws BusinessException {
        userState = authenticatedUserManagerLocal.getUserToEdit(name);
        return userState;
    }

    /**
     * Deleguje pobranie użytkownika o danym loginie do
     * AuthenticatedUserManagera, a następnie zwraca pobrany obiekt
     *
     * @param name login użytkownika
     * @return obiekt użytkownika o danym loginie
     * @throws BusinessException wyjątek aplikacyjny
     */
    @Override
    @RolesAllowed("getUser")
    public Users getUser(String name) throws BusinessException {
        return authenticatedUserManagerLocal.getUser(name);

    }

    /**
     * Deleguje pobranie listy wszystkich uzytkowników systemu do AdminManagera,
     * a następnie zwraca pobraną listę
     *
     * @return lista wszystkim użytkowników systemu
     */
    @Override
    @RolesAllowed("getUsersList")
    public List<Users> getUsersList() {
        return adminManagerLocal.getUsersList();

    }

    /**
     * Deleguje pobranie użytkownika do edycji przez administratora do
     * AdminManagera. Zapamiętuje jego stan oraz zwraca pobrany obiekt
     *
     * @param rowData obiekt użytkownika pobieranego do edycji przez
     * administratora
     * @return obiekt użytkownika do edycji
     */
    @Override
    @RolesAllowed("getUser1")
    public Users getUser(Users rowData) {
        userState = adminManagerLocal.getUser(rowData);
        return userState;
    }

    /**
     * Deleguje edycję użytkownika przez administratora do metody AdminManagera
     *
     * @param userToEdit użytkownik do edycji przez administratora
     * @param oldPassword stare hasło
     * @param newPassword nowe hasło
     * @throws BusinessException wyjątek aplikacyjny
     */
    @Override
    @RolesAllowed("editUserByAdmin")
    public void editUserByAdmin(Users userToEdit, String oldPassword, String newPassword) throws BusinessException {
        adminManagerLocal.editUserByAdmin(userState, userToEdit, oldPassword, newPassword);
        userState = null;
    }

    /**
     * Deleguje pobranie listy użytkowników zawierających szukaną frazę w
     * loginie do metody AdminManagera a następnie zwraca pobraną listę
     *
     * @param matchLogin fraza szukana w loginie
     * @return lista użytkowników zawierąca w loginie szukaną frazę
     */
    @Override
    @RolesAllowed("filter")
    public List<Users> filter(String matchLogin) {
        return adminManagerLocal.filter(matchLogin);

    }

    /**
     * Deleguje edycję swoich danych przez użytkownika do dedykowanej metody
     * AuthenticatedManagera
     *
     * @param userOldPassword stare hasło
     * @param userNewPassword nowe hasło
     * @param authorizedUser edytowany obiekt użytkownika
     * @throws BusinessException wyjątek aplikacyjny
     */
    @Override
    @RolesAllowed("editUser")
    public void editUser(String userOldPassword, String userNewPassword, Users authorizedUser) throws BusinessException {
        authenticatedUserManagerLocal.editUser(userOldPassword, userNewPassword, authorizedUser, userState);
        userState = null;

    }

    /**
     * Deleguje pobranie listy wszystkich dostępnych tytułów(stopni naukowych)
     * do AuthenticatedManagera, a następnie zwraca pobraną listę
     *
     * @return lista wszystkich dostępnych tytułów(stopni naukowych)
     */
    @Override
    @RolesAllowed("getDegreeList")
    public List<Degrees> getDegreeList() {
        return authenticatedUserManagerLocal.getDegreesList();
    }

}
