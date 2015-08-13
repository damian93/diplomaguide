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
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Damian
 */
@Entity
@Table(name = "students")
@DiscriminatorValue("S")
@NamedQueries({
    @NamedQuery(name = "Students.findAll", query = "SELECT s FROM Students s"),
    //  @NamedQuery(name = "Students.findByAccessLevelId", query = "SELECT s FROM Students s WHERE s.accessLevelId = :accessLevelId"),
    @NamedQuery(name = "Students.findByRegisterDate", query = "SELECT s FROM Students s WHERE s.registerDate = :registerDate"),
    @NamedQuery(name = "Students.findByLogin", query = "SELECT t FROM Students t WHERE t.userId=(SELECT u.id from Users u where u.login=:login)")})
public class Students extends Accesslevel implements Serializable {
   

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "student")
    private Collection<Thesis> thesisCollection;
    @Basic(optional = false)
    @Column(name = "RegisterDate")
    @Temporal(TemporalType.DATE)
    private Date registerDate;

    public Students() {
        super.setName("S");
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date lastLoginDate) {
        this.registerDate = lastLoginDate;
    }

    public Collection<Thesis> getThesisCollection() {
        return thesisCollection;
    }

    public void setThesisCollection(Collection<Thesis> thesisCollection) {
        this.thesisCollection = thesisCollection;
    }


}
