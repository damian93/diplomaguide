/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import entities.Accesslevel;
import entities.Administrators;
import entities.Students;
import entities.Teachers;
import entities.Users;
import interfaces.IUserInRole;

/**
 *
 * @author Damian
 */
public class CustomUser implements IUserInRole {

    private final Users user;

    public CustomUser(Users user) {
        this.user = user;
    }

    @Override
    public boolean isStudent() {
        for (Accesslevel a : user.getAccesslevelCollection()) {
            if (a instanceof Students) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isAdministrator() {
        for (Accesslevel a : user.getAccesslevelCollection()) {
            if (a instanceof Administrators) {
                return true;
            }
        }
        return false;

    }

    @Override
    public boolean isTeacher() {
        for (Accesslevel a : user.getAccesslevelCollection()) {
            if (a instanceof Teachers) {
                return true;
            }
        }
        return false;

    }
    
//        public static <T extends Accesslevel> T getSpecAccesslevelFromAccount(Users account, Class<T> type) throws BusinessException {
//        if (account == null) {
////            throw InternalException.createInternalWithNullAccountState(account);
//        }
//        T specAccesslevel = null;
//
//        for(Accesslevel al: account.getAccesslevelCollection()) {
//            if (type.isAssignableFrom(al.getClass())) {
//                specAccesslevel = type.cast(al);
//                break;
//            }
//        }
//        if (specAccesslevel == null) {
////            throw InternalException.createInternalConvertAccountToNotAssignedType(account);
//        }
//        return specAccesslevel;
//    }

}
