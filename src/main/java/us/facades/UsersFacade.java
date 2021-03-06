/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package us.facades;

import entities.Users;
import exceptions.BusinessException;
import exceptions.EmailAlreadyExistsException;
import exceptions.LoginAlreadyExistsException;
import exceptions.UserEditWithOptimistichLockException;
import exceptions.UserNotExistsException;
import java.util.List;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

/**
 *
 * @author Damian
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class UsersFacade extends AbstractFacade<Users> implements UsersFacadeLocal {

    @PersistenceContext(unitName = "usPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsersFacade() {
        super(Users.class);
    }

    @Override
    @PermitAll
    public void create(Users entity) throws BusinessException {
        try {
            super.create(entity);
            em.flush();
        } catch (PersistenceException e) {

            if (e.getMessage().contains("for key 'Login'")) {
                throw new LoginAlreadyExistsException();
            } else if (e.getMessage().contains("for key 'Email'")) {
                throw new EmailAlreadyExistsException();
            }
            throw e;

        }
    }

    /**
     *
     * @param name
     * @return
     * @throws BusinessException
     */
    @Override
    @RolesAllowed({"getUserToEdit", "getUser"})
    public Users findByLogin(String name) throws BusinessException {
        Users user = null;
        try {
            Query q = em.createNamedQuery("Users.findByLogin");
            q.setParameter("login", name);
            user = (Users) q.getSingleResult();
        } catch (NoResultException ex) {
            throw new UserNotExistsException();
        }
        return user;
    }

    @Override
    @RolesAllowed({"editUserByAdmin", "editUser"})
    public void edit(Users entity) throws BusinessException {
        try {
            super.edit(entity);
            em.flush();
        } catch (OptimisticLockException e) {
            throw new UserEditWithOptimistichLockException();
        } catch (PersistenceException e) {
            if (e.getMessage().contains("for key 'Email'")) {
                throw new EmailAlreadyExistsException();
            } else {
                throw new BusinessException("");
            }
        }
    }

    @Override
    @RolesAllowed("filter")
    public List<Users> filter(String matcher) {
        Query q = em.createNamedQuery("Users.findByFewLetters");
        q.setParameter("par", '%' + matcher + '%');
        List<Users> resultList = q.getResultList();
        return resultList;
    }

    @Override
    @RolesAllowed({"getUsersList"})
    public List<Users> findAll() {
        return super.findAll();
    }

    @Override
    @RolesAllowed("getUser1")
    public Users find(Object id) {
        return super.find(id);
    }

}
