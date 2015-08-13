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
import javax.persistence.Version;
import javax.validation.constraints.Size;

/**
 *
 * @author Damian
 */
@Entity
@Table(name = "thesis")
@NamedQueries({
    @NamedQuery(name = "Thesis.findAll", query = "SELECT t FROM Thesis t"),
    @NamedQuery(name = "Thesis.findByThesisId", query = "SELECT t FROM Thesis t WHERE t.thesisId = :thesisId"),
    @NamedQuery(name = "Thesis.findByName", query = "SELECT t FROM Thesis t WHERE t.name = :name"),
    @NamedQuery(name = "Thesis.findThesisByPhrase", query = "SELECT t FROM Thesis t WHERE t.name LIKE :par"),
    @NamedQuery(name = "Thesis.findByDate", query = "SELECT t FROM Thesis t WHERE t.date = :date")
})
public class Thesis implements Serializable {

    @Basic(optional = false)
    //@NotNull
    @Column(name = "Accepted")
    private boolean accepted;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "thesis")
    private Collection<Exam> examCollection;
    @JoinColumn(name = "Type", referencedColumnName = "ThesisTypeId")
    @ManyToOne(optional = false)
    private Thesistype type;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    //@NotNull
    @Column(name = "ThesisId")
    private Long thesisId;
    @Basic(optional = false)
    @Size(min = 1, max = 255)
    @Column(name = "Name")
    private String name;
    @Column(name = "Date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @Basic(optional = false)
    //@NotNull
    @Version
    @Column(name = "Version")
    private long version;
    @JoinColumn(name = "Student", referencedColumnName = "AccessLevelId")
    @ManyToOne(optional = false)
    private Students student;
    @JoinColumn(name = "Teacher", referencedColumnName = "AccessLevelId")
    @ManyToOne(optional = false)
    private Teachers teacher;

    public Thesis() {
    }

    public Thesis(Long thesisId) {
        this.thesisId = thesisId;
    }

    public Long getThesisId() {
        return thesisId;
    }

    public void setThesisId(Long thesisId) {
        this.thesisId = thesisId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public Students getStudent() {
        return student;
    }

    public void setStudent(Students student) {
        this.student = student;
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
        hash += (thesisId != null ? thesisId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Thesis)) {
            return false;
        }
        Thesis other = (Thesis) object;
        if ((this.thesisId == null && other.thesisId != null) || (this.thesisId != null && !this.thesisId.equals(other.thesisId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Thesis[ thesisId=" + thesisId + " ]";
    }

    public boolean getAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    public Collection<Exam> getExamCollection() {
        return examCollection;
    }

    public void setExamCollection(Collection<Exam> examCollection) {
        this.examCollection = examCollection;
    }

    public Thesistype getType() {
        return type;
    }

    public void setType(Thesistype type) {
        this.type = type;
    }

}
