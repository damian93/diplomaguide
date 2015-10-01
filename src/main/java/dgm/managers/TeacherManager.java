/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dgm.managers;

import common.TrackerInterceptor;
import dgm.facades.TeachersFacadeLocal;
import entities.Teachers;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;

/**
 *
 * @author Damian
 */
@Stateless
@Interceptors({TrackerInterceptor.class})
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class TeacherManager implements TeacherManagerLocal {

    @EJB
    private TeachersFacadeLocal teachersFacadeLocal;

    @Override
    @RolesAllowed("getLoggedTeacher")
    public Teachers getLoggedTeacher(String login) {
        return teachersFacadeLocal.findByLogin(login);
    }

}
