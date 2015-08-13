/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import entities.Teachers;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author Damian
 */
@Named(value = "listTeachersBackingBean")
@RequestScoped
public class ListTeachersBackingBean {

    @Inject
    private DiplomaGuideSession diplomaGuideSession;
    
    private Map<Teachers, Integer> teachersMap;
    
    
    public ListTeachersBackingBean() {
    }

    public Map<Teachers, Integer> getTeachersMap() {
        return teachersMap;
    }

    public void setTeachersMap(Map<Teachers, Integer> teachersMap) {
        this.teachersMap = teachersMap;
    }
    
    
    @PostConstruct
    private void init(){
        teachersMap=diplomaGuideSession.getTeachersMap();
    }
    
}
