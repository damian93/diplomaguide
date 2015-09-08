/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import entities.Commission;
import entities.Exam;
import entities.Teachers;
import exceptions.BusinessException;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import utils.JsfUtils;
import utils.ResourceBundleUtils;

/**
 *
 * @author Damian
 */
@Named(value = "createCommision")
@ViewScoped
public class CreateCommisionBackingBean implements Serializable {

    @Inject
    DiplomaGuideSession dgs;

    private Exam exam;
    private Teachers chairman;
    private Teachers teacher1;
    private Teachers teacher2;

    private Set<Teachers> teachersSet;

    public CreateCommisionBackingBean() {
    }

    public Set<Teachers> getTeachersSet() {
        return teachersSet;
    }

    public void setTeachersSet(Set<Teachers> teachersSet) {
        this.teachersSet = teachersSet;
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    public Teachers getChairman() {
        return chairman;
    }

    public void setChairman(Teachers chairman) {
        this.chairman = chairman;
    }

    public Teachers getTeacher1() {
        return teacher1;
    }

    public void setTeacher1(Teachers teacher1) {
        this.teacher1 = teacher1;
    }

    public Teachers getTeacher2() {
        return teacher2;
    }

    public void setTeacher2(Teachers teacher2) {
        this.teacher2 = teacher2;
    }


    @PostConstruct
    private void init() {
        this.exam = dgs.getExamToAddCommision();
        if (exam.getCommissionCollection().size() == 3) {
            for (Commission c : exam.getCommissionCollection()) {
                if (c.getChairman()) {
                    chairman = c.getTeacher();
                } else {
                    if (teacher1 == null) {
                        teacher1 = c.getTeacher();
                    } else if (teacher2 == null) {
                        teacher2 = c.getTeacher();
                    }
                }
            }
        }
            teachersSet = new HashSet(dgs.getTeachers());
            Teachers loggedTeacher = dgs.getLoggedTeacher();

            for (Iterator<Teachers> i = teachersSet.iterator(); i.hasNext();) {
                Teachers tmp = i.next();
                if (tmp.equals(loggedTeacher)) {
                    i.remove();
                }
            }

        }
    

    public String addCommision() {
        try {
            Set<Teachers> commisionTeachers = new LinkedHashSet<>();
            commisionTeachers.add(chairman);
            commisionTeachers.add(teacher1);
            commisionTeachers.add(teacher2);
            dgs.addCommision(exam, commisionTeachers);
            JsfUtils.addSuccessMessage(ResourceBundleUtils.getResourceBundleLanguageProperty("Info"), ResourceBundleUtils.getResourceBundleLanguageProperty("succeededCreation"), ":msgs");
            return "/teacher/examlist.xhtml?faces-redirect=true";
        } catch (BusinessException ex) {
            JsfUtils.addErrorMessage(ex, ex.getMessage(), ":msgs");
            return "";
        }

    }

}
