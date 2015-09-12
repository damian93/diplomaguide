/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import entities.Teachers;
import java.util.Set;

/**
 *
 * @author Damian
 */
public class CommisionTeachersUtils {

    private Teachers chairman;
    private Teachers teacher1;
    private Teachers teacher2;

    private Set<Teachers> teachersSet;

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

    public Set<Teachers> getTeachersSet() {
        return teachersSet;
    }

    public void setTeachersSet(Set<Teachers> teachersSet) {
        this.teachersSet = teachersSet;
    }

}
