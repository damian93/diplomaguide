/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dgm.facades;

import entities.Thesis;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Damian
 */
@Stateless
public class ThesisFacade extends AbstractFacade<Thesis> implements ThesisFacadeLocal {

    @PersistenceContext(unitName = "dgPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ThesisFacade() {
        super(Thesis.class);
    }

    @Override
    public List<Thesis> findWithPhrase(String phrase) {
        Query q = em.createNamedQuery("Thesis.findThesisByPhrase");
        q.setParameter("par", '%' + phrase + '%');
        List<Thesis> resultList = q.getResultList();
        return resultList;
    }

}
