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
import javax.ejb.Local;

/**
 *
 * @author Damian
 */
@Local
public interface UserServiceEndpointLocal {

    /**
     * Deleguje utworzenie(rejestracje) użytkownika do
     * UnauthenticadedUserManagera
     *
     * @param user obiekt użytkownika do rejestracji
     * @param type nazwa roli użytkownika
     * @throws BusinessException wyjątek aplikacyjny
     */
    void createUser(Users user, String type) throws BusinessException;

    /**
     * Deleguje pobranie listy wszystkich poziomów dostępów(ról), a następnie ją
     * zwraca
     *
     * @return lista poziomów dostępów(ról)
     */
    List<Accesslevelsdictionary> getAllAccessLevels();

    /**
     * Deleguje pobranie użytkownika o danym loginie do
     * AuthenticatedUserManagera, a następnie zwraca pobrany obiekt
     *
     * @param name login użytkownika
     * @return obiekt użytkownika o danym loginie
     * @throws BusinessException wyjątek aplikacyjny
     */
    Users getUser(String name) throws BusinessException;

    /**
     * Deleguje pobranie listy wszystkich uzytkowników systemu do AdminManagera,
     * a następnie zwraca pobraną listę
     *
     * @return lista wszystkim użytkowników systemu
     */
    List<Users> getUsersList();

    /**
     * Deleguje pobranie użytkownika do edycji przez administratora do
     * AdminManagera. Zapamiętuje jego stan oraz zwraca pobrany obiekt
     *
     * @param rowData obiekt użytkownika pobieranego do edycji przez
     * administratora
     * @return obiekt użytkownika do edycji
     */
    Users getUser(Users rowData);

    /**
     * Deleguje edycję użytkownika przez administratora do metody AdminManagera
     *
     * @param userToEdit użytkownik do edycji przez administratora
     * @param oldPassword stare hasło
     * @param newPassword nowe hasło
     * @throws BusinessException wyjątek aplikacyjny
     */
    void editUserByAdmin(Users userToEdit, String oldPassword) throws BusinessException;

    /**
     * Deleguje pobranie listy użytkowników zawierających szukaną frazę w
     * loginie do metody AdminManagera a następnie zwraca pobraną listę
     *
     * @param matchLogin fraza szukana w loginie
     * @return lista użytkowników zawierąca w loginie szukaną frazę
     */
    List<Users> filter(String matchLogin);

    /**
     * Deleguje edycję swoich danych przez użytkownika do dedykowanej metody
     * AuthenticatedManagera
     *
     * @param userOldPassword stare hasło
     * @param userNewPassword nowe hasło
     * @param authorizedUser edytowany obiekt użytkownika
     * @throws BusinessException wyjątek aplikacyjny
     */
    void editUser(String userOldPassword, String userNewPassword, Users authorizedUser) throws BusinessException;

    /**
     * Deleguje pobranie użytkownika do edycji do AuthenticatedUserManagera.
     * Zapamiętuje stan pobranego obiektu oraz zwraca ten obiekt
     *
     * @param name login użytkownika do edycji
     * @return pobrany obiekt użytkownika do edycji
     * @throws BusinessException wyjątek aplikacyjny
     */
    Users getUserToEdit(String name) throws BusinessException;

    /**
     * Deleguje pobranie listy wszystkich dostępnych tytułów(stopni naukowych)
     * do AuthenticatedManagera, a następnie zwraca pobraną listę
     *
     * @return lista wszystkich dostępnych tytułów(stopni naukowych)
     */
    List<Degrees> getDegreeList();

}
