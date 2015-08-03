/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package us.facades;

import entities.Accesslevel;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Damian
 */
@Stateless
public class AccesslevelFacade extends AbstractFacade<Accesslevel> implements AccesslevelFacadeLocal {
    @PersistenceContext(unitName = "pl_DiplomaGuide_war_0.1-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AccesslevelFacade() {
        super(Accesslevel.class);
    }
    
}
