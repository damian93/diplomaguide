/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import entities.Degrees;
import entities.Users;
import exceptions.BusinessException;
import java.util.List;
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
@Named(value = "editBackingBean")
@RequestScoped
public class EditBackingBean {

    @Inject
    private UserSession userSession;

    private Users userToEdit;
    private Users authorizedUser;
    private String oldPassword = "";
    private String newPassword = "";
    private String userOldPassword = "";
    private String userNewPassword = "";
    private String userNewConfirmedPassword = "";
    private String degree;
    private List<Degrees> degreeList;

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public List<Degrees> getDegreeList() {
        return degreeList;
    }

    public void setDegreeList(List<Degrees> degreeList) {
        this.degreeList = degreeList;
    }

    public EditBackingBean() {

    }

    public String getUserOldPassword() {
        return userOldPassword;
    }

    public void setUserOldPassword(String userOldPassword) {
        this.userOldPassword = userOldPassword;
    }

    public String getUserNewPassword() {
        return userNewPassword;
    }

    public void setUserNewPassword(String userNewPassword) {
        this.userNewPassword = userNewPassword;
    }

    public String getUserNewConfirmedPassword() {
        return userNewConfirmedPassword;
    }

    public void setUserNewConfirmedPassword(String userNewConfirmedPassword) {
        this.userNewConfirmedPassword = userNewConfirmedPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public Users getAuthorizedUser() {
        return authorizedUser;
    }

    public void setAuthorizedUser(Users authorizedUser) {
        this.authorizedUser = authorizedUser;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public Users getUserToEdit() {
        return userToEdit;
    }

    public void setUserToEdit(Users userToEdit) {
        this.userToEdit = userToEdit;
    }

    @PostConstruct
    private void init() {

        userToEdit = userSession.getSelectedUser();
        authorizedUser = userSession.getAuthorizedUser();

    }

    public String edit() {
        try {
            userSession.editUserByAdmin(userToEdit, oldPassword, newPassword);
            JsfUtils.addSuccessMessage(ResourceBundleUtils.getResourceBundleLanguageProperty("Info"), ResourceBundleUtils.getResourceBundleLanguageProperty("editSucceed"), ":msgs");
            return "list.xhtml?faces-redirect=true";
        } catch (BusinessException ex) {
            JsfUtils.addErrorMessage(ex, ex.getMessage(), ":msgs");
            return "";
        }
    }

    public String editByUser() {
        try {
            userSession.editUser(userOldPassword, userNewPassword, authorizedUser);
            JsfUtils.addSuccessMessage(ResourceBundleUtils.getResourceBundleLanguageProperty("Info"), ResourceBundleUtils.getResourceBundleLanguageProperty("editSucceed"), ":msgs");
            return "showUserData.xhtml?faces-redirect=true";

        } catch (BusinessException ex) {
            JsfUtils.addErrorMessage(ex, ex.getMessage(), ":msgs");
            return "";

        }

    }

}
