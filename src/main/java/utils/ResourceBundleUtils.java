/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.ResourceBundle;
import javax.faces.context.FacesContext;

/**
 *
 * @author ssbd03
 */
public class ResourceBundleUtils {

    /**
     * Metoda pobiera właściwość z pliku, służy zapewnieniu internacjonalizacji
     *
     * @param propertyName parametr jest kluczem, który służy do pobrania
     * właściwości do wyświetlenia w interfejsie użytkownika
     * @return zwraca właściwość określoną kluczem z parametru metody
     */
    public static String getResourceBundleLanguageProperty(String propertyName) {
        ResourceBundle rb;
        String language = FacesContext.getCurrentInstance().getViewRoot().getLocale().toString();
        if(language.equals("en")){
            rb = ResourceBundle.getBundle("languages.locale_en");
        }
        else if(language.equals("pl")){
            rb = ResourceBundle.getBundle("languages.locale_pl");
        }
        else{
            rb = ResourceBundle.getBundle("languages.locale");
        }
        return rb.getString(propertyName); 
    }

    /**
     * Pobiera właściwości biznesowe
     *
     * @param propertyName
     * @return zwraca właściwość określoną kluczem z parametru metody
     */
    public static String getResourceBundleBusinessProperty(String propertyName) {

        ResourceBundle rb = ResourceBundle.getBundle("properties.Business");
        return rb.getString(propertyName);

    }

}
