/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import entities.Thesis;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;

/**
 *
 * @author Damian
 */
@Named(value = "studentThesisListBackingBean")
@RequestScoped
public class studentThesisListBackingBean {

    @Inject
    DiplomaGuideSession diplomaGuideSession;

    private List<Thesis> items;
    private DataModel<Thesis> thesisDataModel;

    public studentThesisListBackingBean() {
    }

    public DataModel<Thesis> getThesisDataModel() {
        return thesisDataModel;
    }

    public void setThesisDataModel(DataModel<Thesis> thesisDataModel) {
        this.thesisDataModel = thesisDataModel;
    }

    @PostConstruct
    private void init() {
        items = diplomaGuideSession.getMyThesis();
        thesisDataModel = new ListDataModel<>(items);
    }

    public String beforeEdit() {
        diplomaGuideSession.setThesisToEditByStudent(items.get(thesisDataModel.getRowIndex()));
        return "editthesis.xhtml?faces-redirect=true";
    }

}
