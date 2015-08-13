/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Damian
 */
@Entity
@Table(name = "teachers")
@DiscriminatorValue("T")
@NamedQueries({
    @NamedQuery(name = "Teachers.findAll", query = "SELECT t FROM Teachers t"),
    @NamedQuery(name = "Teachers.findByAccessLevelId", query = "SELECT t FROM Teachers t WHERE t.accessLevelId = :accessLevelId"),
    @NamedQuery(name = "Teachers.findByLogin", query = "SELECT t FROM Teachers t WHERE t.userId=(SELECT u.id from Users u where u.login=:login)")})
public class Teachers extends Accesslevel implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "teacher")
    private Collection<Commission> commissionCollection;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "teacher")
    private List<Thesis> thesisList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "teacher")
    private Collection<Thesis> thesisCollection;
    @JoinColumn(name = "Degree", referencedColumnName = "Id")
    @ManyToOne(optional = false)
    private Degrees degree;

    public Teachers() {
        super.setName("T");
    }

    public Degrees getDegree() {
        return degree;
    }

    public void setDegree(Degrees degree) {
        this.degree = degree;
    }

    @Override
    public String toString() {
            return  super.getUserId().getName() + " " + super.getUserId().getSurname();
    }

    public List<Thesis> getThesisList() {
        return thesisList;
    }

    public void setThesisList(List<Thesis> thesisList) {
        this.thesisList = thesisList;
    }

    public Collection<Thesis> getThesisCollection() {
        return thesisCollection;
    }

    public void setThesisCollection(Collection<Thesis> thesisCollection) {
        this.thesisCollection = thesisCollection;
    }

    public Collection<Commission> getCommissionCollection() {
        return commissionCollection;
    }

    public void setCommissionCollection(Collection<Commission> commissionCollection) {
        this.commissionCollection = commissionCollection;
    }

}
