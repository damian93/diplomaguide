/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import entities.Users;
import exceptions.BusinessException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import utils.JsfUtils;

/**
 *
 * @author Damian
 */
@Named(value = "showUserDataBackingBean")
@RequestScoped
public class ShowUserDataBackingBean {

    @Inject
    UserSession userSession;

    Users loggedUser;

    public Users getLoggedUser() {
        return loggedUser;
    }

    public ShowUserDataBackingBean() {
    }

    @PostConstruct
    private void init() {

        try {
            loggedUser = userSession.getUser(JsfUtils.getLoggedUserLogin());
        } catch (BusinessException ex) {
            Logger.getLogger(ShowUserDataBackingBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void beforeEdit() {
        try {
            userSession.getUserToEdit(JsfUtils.getLoggedUserLogin());
        } catch (BusinessException ex) {
            Logger.getLogger(ShowUserDataBackingBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
