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
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

/**
 *
 * @author Damian
 */
@Named(value = "thesisListBackingBean")
@Dependent
public class ThesisListBackingBean {

    @Inject
    DiplomaGuideSession diplomaGuideSession;

    private List<Thesis> allThesisList;

    public ThesisListBackingBean() {
    }

    public List<Thesis> getAllThesisList() {
        return allThesisList;
    }

    public void setAllThesisList(List<Thesis> allThesisList) {
        this.allThesisList = allThesisList;
    }

    @PostConstruct
    private void init() {
        allThesisList=diplomaGuideSession.getAllThesisList();
    }

}
