/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package us.facades;

import entities.Users;
import exceptions.BusinessException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Damian
 */
@Local
public interface UsersFacadeLocal {

    void create(Users users) throws BusinessException;

    void edit(Users users) throws BusinessException;

    Users find(Object id);

    List<Users> findAll();

    Users findByLogin(String name) throws BusinessException;

    List<Users> filter(String matcher);

}
