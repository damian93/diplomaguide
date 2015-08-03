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
public class LoginAlreadyExistsException extends BusinessException{
    
    private static final String message = "EXCEPTION.ACCOUNT.LOGIN_EXISTS";
    
    public LoginAlreadyExistsException(){
        super(message);
    }
    
    
    
}
