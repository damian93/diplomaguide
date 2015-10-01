/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dgm.facades;

import entities.Thesis;
import exceptions.BusinessException;
import exceptions.EditThesisWithOptimistickLockException;
import exceptions.StudentHasAlreadyThisTypeThesis;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
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
    @RolesAllowed("getThesisWithPhrase")
    public List<Thesis> findWithPhrase(String phrase) {
        Query q = em.createNamedQuery("Thesis.findThesisByPhrase");
        q.setParameter("par", '%' + phrase + '%');
        List<Thesis> resultList = q.getResultList();
        return resultList;
    }

    @Override
    @RolesAllowed({"acceptation", "editThesisByStudent"})
    public void edit(Thesis entity) throws BusinessException {
        try {
            super.edit(entity);
        } catch (OptimisticLockException e) {
            throw new EditThesisWithOptimistickLockException();
        }
    }

    @Override
    @RolesAllowed("getMyThesis")
    public List<Thesis> findMyThesis(Long accessLevelId) {
        Query q = em.createNamedQuery("Thesis.findMyThesis");
        q.setParameter("s", accessLevelId);
        List<Thesis> resultList = q.getResultList();
        return resultList;

    }

    @Override
    @RolesAllowed("getMyThesisByTeacher")
    public List<Thesis> findMyThesisByTeacher(Long accessLevelId) {
        Query q = em.createNamedQuery("Thesis.findMyThesisByTeacher");
        q.setParameter("s", accessLevelId);
        List<Thesis> resultList = q.getResultList();
        return resultList;

    }

    @Override
    @RolesAllowed("createThesis")
    public void create(Thesis entity) throws BusinessException {
        try {
            super.create(entity);
            em.flush();
        } catch (PersistenceException e) {
            if (e.getMessage().contains("unique_thesis")) {
                throw new StudentHasAlreadyThisTypeThesis();
            } else {
                throw e;
            }
        }
    }

    @Override
    @RolesAllowed("getAllThesisList")
    public List<Thesis> findAll() {
        return super.findAll();
    }

    @Override
    @RolesAllowed({"getThesisToEditByTeacher", "getThesisToEditByStudent"})
    public Thesis find(Object id) {
        return super.find(id);
    }

}
