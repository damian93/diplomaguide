/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dgm.managers;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

/**
 *
 * @author Damian
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class ExamManager implements ExamManagerLocal {

    
}
