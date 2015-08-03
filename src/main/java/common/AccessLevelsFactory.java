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
import exceptions.WrongUserTypeException;
import utils.ResourceBundleUtils;

/**
 *
 * @author Damian
 */
public class AccessLevelsFactory {

    public static Accesslevel getProperAccessLevel(String type) throws WrongUserTypeException {

        if (type.equals(ResourceBundleUtils.getResourceBundleBusinessProperty("Student"))) {
            return new Students();
        } else if (type.equals(ResourceBundleUtils.getResourceBundleBusinessProperty("Teacher"))) {
            return new Teachers();
        } else if (type.equals(ResourceBundleUtils.getResourceBundleBusinessProperty("Administrator"))) {
            return new Administrators();
        } else {
            throw new WrongUserTypeException();
        }

    }

}
