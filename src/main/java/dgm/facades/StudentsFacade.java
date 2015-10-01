/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dgm.facades;

import entities.Students;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Damian
 */
@Stateless(name = "dgStudentFacade")
public class StudentsFacade extends AbstractFacade<Students> implements StudentsFacadeLocal {

    @PersistenceContext(unitName = "dgPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public StudentsFacade() {
        super(Students.class);
    }

    @Override
    @RolesAllowed("getLoggedStudent")
    public Students findByLogin(String studentLogin) {
        Students user;
        Query q = em.createNamedQuery("Students.findByLogin");
        q.setParameter("login", studentLogin);
        user = (Students) q.getSingleResult();
        return user;

    }

}
