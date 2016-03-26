/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import entities.Thesis;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author Damian
 */
@Named(value = "thesisListBackingBean")
@ViewScoped
public class ThesisListBackingBean implements Serializable{

    @Inject
    private DiplomaGuideSession diplomaGuideSession;

    private List<Thesis> allThesisList;
    
    private String phrase;

    public ThesisListBackingBean() {
    }

    public String getPhrase() {
        return phrase;
    }

    public void setPhrase(String phrase) {
        this.phrase = phrase;
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
    
    public void filter(){
        allThesisList= new ArrayList(diplomaGuideSession.getThesisWithPhrase(phrase));
    }

}
