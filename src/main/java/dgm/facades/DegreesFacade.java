/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dgm.facades;

import entities.Degrees;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Damian
 */
@Stateless(name = "dgDegressFacade")
public class DegreesFacade extends AbstractFacade<Degrees> implements DegreesFacadeLocal {
    @PersistenceContext(unitName = "dgPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DegreesFacade() {
        super(Degrees.class);
    }
    
}
