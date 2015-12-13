/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import common.AccessLevelsFactory;
import entities.Users;
import exceptions.BusinessException;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIOutput;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;
import utils.JsfUtils;
import utils.ResourceBundleUtils;

/**
 *
 * @author Damian
 */
@Named(value = "registerBackingBean")
@RequestScoped
public class RegisterBackingBean {

    @Inject
    UserSession userSession;

    private final Users user = new Users();
    private List<String> accessLevelList;
    private String type;

    public RegisterBackingBean() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    
    public Users getUser() {
        return user;
    }

    public List<String> getAccessLevelList() {
        return accessLevelList;
    }

    @PostConstruct
    private void init() {
        accessLevelList = AccessLevelsFactory.getAllAccessLevels();
        
    }

    public String registerUser() {

        try {
            userSession.registerUser(user, type);
            JsfUtils.addSuccessMessage(ResourceBundleUtils.getResourceBundleLanguageProperty("regSuccess"), "", ":registerForm");
            return "index?faces-redirect=true";
        } catch (BusinessException ex) {
            JsfUtils.addErrorMessage(ResourceBundleUtils.getResourceBundleLanguageProperty(ex.getMessage()), "", "registerForm");
            return "";
        }

    }
    
}
