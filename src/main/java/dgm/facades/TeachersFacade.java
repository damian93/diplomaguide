/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dgm.facades;

import entities.Teachers;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Damian
 */
@Stateless(name = "dgTeacherFacade")
public class TeachersFacade extends AbstractFacade<Teachers> implements TeachersFacadeLocal {

    @PersistenceContext(unitName = "dgPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TeachersFacade() {
        super(Teachers.class);
    }

    @Override
    public Teachers findByLogin(String login) {
        Teachers user;
        Query q = em.createNamedQuery("Teachers.findByLogin");
        q.setParameter("login", login);
        user = (Teachers) q.getSingleResult();
        return user;

    }

}