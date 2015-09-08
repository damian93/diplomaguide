/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import entities.Commission;
import entities.Exam;
import entities.Teachers;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author Damian
 */
@Named(value = "showCommision")
@RequestScoped
public class ShowCommisionBackingBean {

    @Inject
    DiplomaGuideSession dgs;

    private Exam exam;
    private Teachers chairman;
    private Teachers teacher1;
    private Teachers teacher2;

    public ShowCommisionBackingBean() {
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
        exam = dgs.getExamToAddCommision();
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

    }

}
