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

    /**
     * Deleguje utworzenie(rejestracje) użytkownika do
     * UnauthenticadedUserManagera
     *
     * @param user obiekt użytkownika do rejestracji
     * @param type nazwa roli użytkownika
     * @throws BusinessException wyjątek aplikacyjny
     */
    @Override
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
    public void editUserByAdmin(Users userToEdit, String oldPassword) throws BusinessException {
        adminManagerLocal.editUserByAdmin(userState, userToEdit, oldPassword);
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
    public List<Degrees> getDegreeList() {
        return authenticatedUserManagerLocal.getDegreesList();
    }

}
