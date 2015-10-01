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
public class PasswordMismatchException extends BusinessException {

    private final static String message = "PasswordMismatchException";

    public PasswordMismatchException() {
        super(message);
    }

}
