/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import entities.Thesis;
import interfaces.IThesisConverter;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author Damian
 */
@Named(value = "thesisConverterBackingBean")
@ViewScoped
public class ThesisConverterBackingBean implements Serializable, IThesisConverter{

    @Inject
    private DiplomaGuideSession diplomaGuideSession;

    private List<Thesis> thesisList;

    public ThesisConverterBackingBean() {
    }

    @Override
    public List<Thesis> getThesisList() {
        return thesisList;
    }

    @PostConstruct
    public void init() {
        thesisList = diplomaGuideSession.getMyThesisByTeacher();

    }
}
