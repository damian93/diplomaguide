/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Damian
 */
@Entity
@Table(name = "commission")
@NamedQueries({
    @NamedQuery(name = "Commission.findAll", query = "SELECT c FROM Commission c"),
    @NamedQuery(name = "Commission.findByIdentyfikator", query = "SELECT c FROM Commission c WHERE c.identyfikator = :identyfikator"),
    @NamedQuery(name = "Commission.findByTeacher", query = "SELECT c FROM Commission c WHERE c.teacher = :t"),
    @NamedQuery(name = "Commission.findByChairman", query = "SELECT c FROM Commission c WHERE c.chairman = :chairman")})
public class Commission implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Identyfikator")
    private Long identyfikator;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Chairman")
    private boolean chairman;
    @JoinColumn(name = "exam", referencedColumnName = "ExamId")
    @ManyToOne(optional = false)
    private Exam exam;
    @JoinColumn(name = "Teacher", referencedColumnName = "AccessLevelId")
    @ManyToOne(optional = false)
    private Teachers teacher;

    public Commission() {
    }

    public Commission(Long identyfikator) {
        this.identyfikator = identyfikator;
    }

    public Commission(Long identyfikator, boolean chairman) {
        this.identyfikator = identyfikator;
        this.chairman = chairman;
    }

    public Long getIdentyfikator() {
        return identyfikator;
    }

    public void setIdentyfikator(Long identyfikator) {
        this.identyfikator = identyfikator;
    }

    public boolean getChairman() {
        return chairman;
    }

    public void setChairman(boolean chairman) {
        this.chairman = chairman;
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    public Teachers getTeacher() {
        return teacher;
    }

    public void setTeacher(Teachers teacher) {
        this.teacher = teacher;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (identyfikator != null ? identyfikator.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Commission)) {
            return false;
        }
        Commission other = (Commission) object;
        if ((this.identyfikator == null && other.identyfikator != null) || (this.identyfikator != null && !this.identyfikator.equals(other.identyfikator))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Commission[ identyfikator=" + identyfikator + " ]";
    }

}
