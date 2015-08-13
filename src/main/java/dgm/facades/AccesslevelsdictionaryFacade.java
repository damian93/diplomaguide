/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dgm.facades;

import entities.Accesslevelsdictionary;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Damian
 */
@Stateless(name = "dgAccessLevelDictionaryFacade")
public class AccesslevelsdictionaryFacade extends AbstractFacade<Accesslevelsdictionary> implements AccesslevelsdictionaryFacadeLocal {
    @PersistenceContext(unitName = "dgPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AccesslevelsdictionaryFacade() {
        super(Accesslevelsdictionary.class);
    }
    
}
