/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package us.facades;

import entities.Degrees;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Damian
 */
@Stateless
public class DegreesFacade extends AbstractFacade<Degrees> implements DegreesFacadeLocal {
    @PersistenceContext(unitName = "usPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DegreesFacade() {
        super(Degrees.class);
    }
    
}
