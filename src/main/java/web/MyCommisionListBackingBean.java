/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import entities.Commission;
import entities.Teachers;
import exceptions.BusinessException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;
import utils.JsfUtils;
import utils.ResourceBundleUtils;

/**
 *
 * @author Damian
 */
@Named(value = "myCommision")
@RequestScoped
public class MyCommisionListBackingBean {

    @Inject
    DiplomaGuideSession dgs;

    private Teachers teacher;

    private DataModel<Commission> commisionDataModel;

    public MyCommisionListBackingBean() {
    }

    public DataModel<Commission> getCommisionDataModel() {
        return commisionDataModel;
    }

    public void setCommisionDataModel(DataModel<Commission> commisionDataModel) {
        this.commisionDataModel = commisionDataModel;
    }

    public Teachers getTeacher() {
        return teacher;
    }

    public void setTeacher(Teachers teacher) {
        this.teacher = teacher;
    }

    @PostConstruct
    private void init() {
        this.teacher = dgs.getLoggedTeacher();
        commisionDataModel = new ListDataModel<>(teacher.getCommissionCollection());
    }

    public void accept() {
        try {
            int rowIndex = commisionDataModel.getRowIndex();
            dgs.acceptCommision(teacher, rowIndex);
            JsfUtils.addSuccessMessage(ResourceBundleUtils.getResourceBundleLanguageProperty("Info"), ResourceBundleUtils.getResourceBundleLanguageProperty("editSucceed"), ":msgs");
            init();
        } catch (BusinessException ex) {
            JsfUtils.addErrorMessage(ex, ex.getMessage(), ":msgs");
        }

    }

    public void reject() {
        try {
            int rowIndex = commisionDataModel.getRowIndex();
            dgs.rejectCommision(teacher, rowIndex);
            JsfUtils.addSuccessMessage(ResourceBundleUtils.getResourceBundleLanguageProperty("Info"), ResourceBundleUtils.getResourceBundleLanguageProperty("editSucceed"), ":msgs");
            init();
        } catch (BusinessException ex) {
            JsfUtils.addErrorMessage(ex, ex.getMessage(), ":msgs");
        }
    }
}
