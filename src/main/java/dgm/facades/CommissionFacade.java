/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dgm.facades;

import entities.Commission;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Damian
 */
@Stateless
public class CommissionFacade extends AbstractFacade<Commission> implements CommissionFacadeLocal {
    @PersistenceContext(unitName = "usPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CommissionFacade() {
        super(Commission.class);
    }

    @Override
    @RolesAllowed({"acceptCommision","rejectCommision"})
    public Commission find(Object id) {
        return super.find(id);
    }
    
    
}
