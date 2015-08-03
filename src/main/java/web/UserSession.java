/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import entities.Accesslevelsdictionary;
import entities.Users;
import exceptions.BusinessException;
import interfaces.IUserInRole;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import us.endpoints.UserServiceEndpointLocal;
import utils.JsfUtils;
import utils.ResourceBundleUtils;

/**
 *
 * @author Damian
 */
@Named(value = "userSession")
@SessionScoped
public class UserSession implements Serializable, IUserInRole {

    @EJB
    UserServiceEndpointLocal userServiceEndpointLocal;

    private Users selectedUser;
    private Users authorizedUser;

    public UserSession() {
    }

    public Users getAuthorizedUser() {
        return authorizedUser;
    }

    public void setAuthorizedUser(Users authorizedUser) {
        this.authorizedUser = authorizedUser;
    }

    public Users getSelectedUser() {
        return selectedUser;
    }

    void setSelectedUser(Users selected) {
        this.selectedUser = selected;
    }

    public void registerUser(Users user, String type) throws BusinessException {
        userServiceEndpointLocal.createUser(user, type);
    }

    public List<Accesslevelsdictionary> getAllAccessLevels() {
        return userServiceEndpointLocal.getAllAccessLevels();

    }

    public String logout() {
        JsfUtils.invalidateSession();
        return "/index.xhtml?faces-redirect=true";
    }

    public Users getUser(String name) throws BusinessException {
        return userServiceEndpointLocal.getUser(name);
    }

    private boolean customIsUserInRole(String role) {
        return JsfUtils.getContext()
                .isUserInRole(ResourceBundleUtils.getResourceBundleBusinessProperty(role));
    }

    @Override
    public boolean isAdministrator() {
        if (!JsfUtils.getLoggedUserLogin().isEmpty()) {
            return customIsUserInRole("admin_role_name");
        }
        return false;
    }

    @Override
    public boolean isTeacher() {
        if (!JsfUtils.getLoggedUserLogin().isEmpty()) {
            return customIsUserInRole("teacher_role_name");
        }
        return false;
    }

    @Override
    public boolean isStudent() {
        if (!JsfUtils.getLoggedUserLogin().isEmpty()) {
            return customIsUserInRole("student_role_name");
        }
        return false;
    }

    List<Users> prepareUsersList() {
        return userServiceEndpointLocal.getUsersList();
    }

    void getUserToEdit(Users rowData) {
        selectedUser = userServiceEndpointLocal.getUser(rowData);
    }
    void getUserToEdit(String name) throws BusinessException  {
        authorizedUser = userServiceEndpointLocal.getUserToEdit(name);
    }


    void editUserByAdmin(Users userToEdit, String oldPassword, String newPassword) throws BusinessException {
        userServiceEndpointLocal.editUserByAdmin(userToEdit, oldPassword, newPassword);
    }

    List<Users> filter(String matchLogin) {
        return userServiceEndpointLocal.filter(matchLogin);
    }

    void editUser(String userOldPassword, String userNewPassword, Users authorizedUser) throws BusinessException {
        userServiceEndpointLocal.editUser(userOldPassword,userNewPassword,authorizedUser);
    }

}
