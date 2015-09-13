/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dgm.managers;

import entities.Teachers;
import javax.ejb.Local;

/**
 *
 * @author Damian
 */
@Local
public interface TeacherManagerLocal {

    Teachers getLoggedTeacher(String login);
    
}
