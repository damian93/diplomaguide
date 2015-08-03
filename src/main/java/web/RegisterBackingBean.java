/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import entities.Accesslevelsdictionary;
import entities.Users;
import exceptions.BusinessException;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
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
    private ArrayList<String> accessLevelList;
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

    public ArrayList<String> getAccessLevelList() {
        return accessLevelList;
    }

    @PostConstruct
    private void init() {
        accessLevelList = new ArrayList<>();
        for( Accesslevelsdictionary a : userSession.getAllAccessLevels()){
            accessLevelList.add(a.getName());
        }
        
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
