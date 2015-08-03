/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.security.Principal;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author Damian
 */
public class JsfUtils {
    
    /**
     * Metoda służy do pobrania instancji ExternalContext
     *
     * @return zwraca instancję ExternalContext dla instancji FacesContext
     */
    public static ExternalContext getContext() {
        return FacesContext.getCurrentInstance().getExternalContext();
    }

    /**
     * Metoda służy do wyświetlenia wiadomości o wyjątku
     *
     * @param ex wyjątek
     * @param defaultMsg wiadomość do wyświetlenia
     * @param componentId id komponentu, w którym należy wyświetlić wiadomość
     */
    public static void addErrorMessage(Exception ex, String defaultMsg, String componentId) {
        String msg = ex.getLocalizedMessage();
        if (msg != null && msg.length() > 0) {
            addErrorMessage(ResourceBundleUtils.getResourceBundleLanguageProperty("exception"), ResourceBundleUtils.getResourceBundleLanguageProperty(msg), componentId);
        } else {
            addErrorMessage(ResourceBundleUtils.getResourceBundleLanguageProperty("error"), defaultMsg, componentId);
        }
    }

    /**
     * Metoda służy do wyświetlenia wiadomości informującej o błędzie w
     * interfejsie użytkownika
     *
     * @param msg nagłówek wiadomości
     * @param details treść wiadomości
     * @param componentId id komponentu, w którym należy wyświetlić wiadomość
     */
    public static void addErrorMessage(String msg, String details, String componentId) {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, details);
        FacesContext.getCurrentInstance().addMessage(componentId, facesMsg);
        FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
    }

    /**
     * Metoda służy do wyświetlenia wiadomości o charakterze informacyjnym w
     * interfejsie użytkownika
     *
     * @param msg nagłówek wiadomości
     * @param details treść wiadomości
     * @param componentId id komponentu, w którym należy wyświetlić wiadomość
     */
    public static void addSuccessMessage(String msg, String details, String componentId) {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, details);
        FacesContext.getCurrentInstance().addMessage(componentId, facesMsg);
        FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
    }

    /**
     * Metoda kończy sesję użytkownika
     *
     */
    public static void invalidateSession() {
        getContext().invalidateSession();
    }

    /**
     * Metoda zwracająca login aktualnie uwierzytelnionego użytkownika
     *
     * @return login uwierzytelnionego użytkownika
     */
    public static String getLoggedUserLogin() {
        String login;
        Principal principal = getContext().getUserPrincipal();

        if (principal != null) {
            login = principal.getName();
        } else {
            login = "";
        }

        return login;
    }
    
}
