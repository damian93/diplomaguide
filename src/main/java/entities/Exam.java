/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Damian
 */
@Entity
@Table(name = "exam")
@NamedQueries({
    @NamedQuery(name = "Exam.findAll", query = "SELECT e FROM Exam e"),
    @NamedQuery(name = "Exam.findByExamId", query = "SELECT e FROM Exam e WHERE e.examId = :examId"),
    @NamedQuery(name = "Exam.findByDate", query = "SELECT e FROM Exam e WHERE e.date = :date"),
    @NamedQuery(name = "Exam.findByGrade", query = "SELECT e FROM Exam e WHERE e.grade = :grade"),
    @NamedQuery(name = "Exam.findByAccepted", query = "SELECT e FROM Exam e WHERE e.accepted = :accepted"),
    @NamedQuery(name = "Exam.findByVersion", query = "SELECT e FROM Exam e WHERE e.version = :version")})
public class Exam implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ExamId")
    private Long examId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Date")
    @Temporal(TemporalType.DATE)
    private Date date;
    @Column(name = "Grade")
    private Integer grade;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Accepted")
    private boolean accepted;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Version")
    private long version;
    @JoinColumn(name = "Thesis", referencedColumnName = "ThesisId")
    @ManyToOne(optional = false)
    private Thesis thesis;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "exam")
    private Collection<Commission> commissionCollection;

    public Exam() {
    }

    public Exam(Long examId) {
        this.examId = examId;
    }

    public Exam(Long examId, Date date, boolean accepted, long version) {
        this.examId = examId;
        this.date = date;
        this.accepted = accepted;
        this.version = version;
    }

    public Long getExamId() {
        return examId;
    }

    public void setExamId(Long examId) {
        this.examId = examId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public boolean getAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public Thesis getThesis() {
        return thesis;
    }

    public void setThesis(Thesis thesis) {
        this.thesis = thesis;
    }

    public Collection<Commission> getCommissionCollection() {
        return commissionCollection;
    }

    public void setCommissionCollection(Collection<Commission> commissionCollection) {
        this.commissionCollection = commissionCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (examId != null ? examId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Exam)) {
            return false;
        }
        Exam other = (Exam) object;
        if ((this.examId == null && other.examId != null) || (this.examId != null && !this.examId.equals(other.examId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Exam[ examId=" + examId + " ]";
    }
    
}