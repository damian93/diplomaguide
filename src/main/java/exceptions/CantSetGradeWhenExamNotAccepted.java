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
public class CantSetGradeWhenExamNotAccepted extends BusinessException {

    private final static String message = "CantSetGradeWhenExamNotAccepted";

    public CantSetGradeWhenExamNotAccepted() {
        super(message);
    }

}
