/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import entities.Teachers;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

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
    
    
    public MyCommisionListBackingBean() {
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
    }
    public String beforeEdit(){
        return "";
    }
    
}
