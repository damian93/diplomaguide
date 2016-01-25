/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import interfaces.IThesisTypeConverter;
import entities.Thesistype;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.inject.Inject;

/**
 *
 * @author Damian
 */
@Named(value = "thesisTypeConverterBackingBean")
@ViewScoped
public class ThesisTypeConverterBackingBean implements Serializable, IThesisTypeConverter{

    @Inject
    private DiplomaGuideSession diplomaGuideSession;

    private List<Thesistype> thesisType;

    public ThesisTypeConverterBackingBean() {
    }
    @Override
    public List<Thesistype> getThesisType() {
        return thesisType;
    }

    @PostConstruct
    private void init() {
        thesisType = diplomaGuideSession.getThesisTypeList();
    }
}
