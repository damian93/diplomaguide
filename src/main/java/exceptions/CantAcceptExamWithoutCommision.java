/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

/**
 *
 * @author Comarch
 */
public class CantAcceptExamWithoutCommision extends BusinessException {

    private final static String message = "CantAcceptExamWithoutCommision";

    public CantAcceptExamWithoutCommision() {
        super(message);
    }

}
