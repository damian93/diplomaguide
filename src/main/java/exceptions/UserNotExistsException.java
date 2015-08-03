/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

/**
 *
 * @author Damian
 */
public class UserNotExistsException extends BusinessException{
    
    public final static String message = "USERS.EXCEPTION.USER_NOT_EXISTS";
    
    public UserNotExistsException(){
        super(message);
    }
    
}
