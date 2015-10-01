/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dgm.facades;

import entities.Exam;
import exceptions.BusinessException;
import exceptions.EditExamWithOptimistickLockException;
import exceptions.ExamWithThesisAlreadyExists;
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
public class ExamFacade extends AbstractFacade<Exam> implements ExamFacadeLocal {

    @PersistenceContext(unitName = "dgPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ExamFacade() {
        super(Exam.class);
    }

    @Override
    @RolesAllowed({"editExamByStudent","editExamByTeacher","addCommission","editCommission","setGrade","confirmGrade"})
    public void edit(Exam entity) throws BusinessException {
        try{
        super.edit(entity);
        }
        catch(OptimisticLockException e){
            throw new EditExamWithOptimistickLockException();
        }
    }

    
    @Override
    @RolesAllowed("createExam")
    public void create(Exam entity) throws BusinessException {
        try {
            super.create(entity);
            em.flush();
        } catch (PersistenceException e) {
            if (e.getMessage().contains("Thesis_2")) {
                throw new ExamWithThesisAlreadyExists();
            } else {
                throw e;
            }
        }
    }

    @Override
    @RolesAllowed("getExamsByTeacher")
    public List<Exam> findByTeacher(Long accessLevelId) {
        Query q = em.createNamedQuery("Exam.findByTeacher");
        q.setParameter("s", accessLevelId);
        List<Exam> resultList = q.getResultList();
        return resultList;

    }

    @Override
    @RolesAllowed("getMyExamsByStudent")
    public List<Exam> findByStudent(Long accessLevelId) {
        Query q = em.createNamedQuery("Exam.findByStudent");
        q.setParameter("s", accessLevelId);
        List<Exam> resultList = q.getResultList();
        return resultList;
    }

    @Override
    @RolesAllowed({"getExamToEdit","getExamToAddCommision","getExamToEditCommision","getExamToSetGrade"})
    public Exam find(Object id) {
        return super.find(id); 
    }
    
    

}
