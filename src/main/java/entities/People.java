/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Damian
 */
@Entity
@Table(name = "people")
@NamedQueries({
    @NamedQuery(name = "People.findAll", query = "SELECT p FROM People p"),
    @NamedQuery(name = "People.findByUserId", query = "SELECT p FROM People p WHERE p.userId = :userId"),
    @NamedQuery(name = "People.findByName", query = "SELECT p FROM People p WHERE p.name = :name"),
    @NamedQuery(name = "People.findBySurname", query = "SELECT p FROM People p WHERE p.surname = :surname"),
    @NamedQuery(name = "People.findByDateoOfBirth", query = "SELECT p FROM People p WHERE p.dateoOfBirth = :dateoOfBirth")})
public class People implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "UserId")
    private Long userId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "Surname")
    private String surname;
    @Column(name = "DateoOfBirth")
    @Temporal(TemporalType.DATE)
    private Date dateoOfBirth;
    @JoinColumn(name = "UserId", referencedColumnName = "Id", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Users users;

    public People() {
    }

    public People(Long userId) {
        this.userId = userId;
    }

    public People(Long userId, String name, String surname) {
        this.userId = userId;
        this.name = name;
        this.surname = surname;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getDateoOfBirth() {
        return dateoOfBirth;
    }

    public void setDateoOfBirth(Date dateoOfBirth) {
        this.dateoOfBirth = dateoOfBirth;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof People)) {
            return false;
        }
        People other = (People) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.People[ userId=" + userId + " ]";
    }
    
}
