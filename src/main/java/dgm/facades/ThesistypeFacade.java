/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dgm.facades;

import entities.Thesistype;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Damian
 */
@Stateless
public class ThesistypeFacade extends AbstractFacade<Thesistype> implements ThesistypeFacadeLocal {
    @PersistenceContext(unitName = "dgPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ThesistypeFacade() {
        super(Thesistype.class);
    }
    
}
