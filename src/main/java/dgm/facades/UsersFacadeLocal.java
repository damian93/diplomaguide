
package dgm.facades;

import entities.Users;
import exceptions.BusinessException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Damian
 */
@Local
public interface UsersFacadeLocal {

    void create(Users users) throws BusinessException;

    void edit(Users users) throws BusinessException;

    void remove(Users users);

    Users find(Object id);

    List<Users> findAll();

    List<Users> findRange(int[] range);

    int count();

    Users findByLogin(String name) throws BusinessException;
    
    List<Users> filter(String matcher);

    List<Users> findActiveStudents();
    List<Users> findActiveTeachers();
    
}
