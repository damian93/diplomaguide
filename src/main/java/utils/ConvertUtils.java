/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author Damian
 */
public class ConvertUtils {
    
    public static String calculateSha512Hash(String pass){     
        return DigestUtils.sha512Hex(pass);
    }
    
    public static String calculateSha256Hash(String string){
        return DigestUtils.sha256Hex(string);
    }
    
}
