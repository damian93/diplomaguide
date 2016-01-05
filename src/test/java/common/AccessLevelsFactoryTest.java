/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import entities.Accesslevel;
import exceptions.WrongUserTypeException;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Damian
 */
public class AccessLevelsFactoryTest {
    
    private final static String STUDENT = "Student";
    private final static String STUDENT_LEVEL = "S";
    private final static String TEACHER = "Teacher";
    private final static String TEACHER_LEVEL = "T";
    private final static String ADMINISTRATOR = "Administrator";
    private final static String ADMINISTRATOR_LEVEL = "A";
    private final static String WRONG_TYPE = "pleple";
    
    
    
    @Test
    public void shouldGetStudentAccessLevel() throws Exception {
        String type = STUDENT;
        String expResult = STUDENT_LEVEL ;
        Accesslevel result = AccessLevelsFactory.getProperAccessLevel(type);
        assertEquals(expResult, result.getName());
    }


    @Test
    public void shouldGetAdminAccessLevel() throws Exception {
        String type = ADMINISTRATOR;
        String expResult = ADMINISTRATOR_LEVEL ;
        Accesslevel result = AccessLevelsFactory.getProperAccessLevel(type);
        assertEquals(expResult, result.getName());

    }
    
    @Test
    public void shouldGetTeacherAccessLevel() throws Exception {
        String type = TEACHER;
        String expResult = TEACHER_LEVEL ;
        Accesslevel result = AccessLevelsFactory.getProperAccessLevel(type);
        assertEquals(expResult, result.getName());
    }
    
    @Test(expected = WrongUserTypeException.class)
    public void shouldGetWrongUsetTypeException() throws WrongUserTypeException {
        String type = WRONG_TYPE; 
        Accesslevel result = AccessLevelsFactory.getProperAccessLevel(type);
       
    }

    @Test(expected = WrongUserTypeException.class)
    public void shouldGetExceptionWhenNULL() throws WrongUserTypeException {
        String type = null; 
        Accesslevel result = AccessLevelsFactory.getProperAccessLevel(type);
       
    }
    
}
