/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

import javax.ejb.ApplicationException;

/**
 *
 * @author Damian
 */
@ApplicationException(rollback = true)
public class BusinessException extends Exception{
    
    public BusinessException(String message){
        super(message);
        
    }
    
}
