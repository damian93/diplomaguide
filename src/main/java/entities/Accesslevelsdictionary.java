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
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Damian
 */
@Entity
@Table(name = "accesslevelsdictionary")
@NamedQueries({
    @NamedQuery(name = "Accesslevelsdictionary.findAll", query = "SELECT a FROM Accesslevelsdictionary a"),
    @NamedQuery(name = "Accesslevelsdictionary.findByIdentyfikator", query = "SELECT a FROM Accesslevelsdictionary a WHERE a.identyfikator = :identyfikator"),
    @NamedQuery(name = "Accesslevelsdictionary.findByName", query = "SELECT a FROM Accesslevelsdictionary a WHERE a.name = :name")})
public class Accesslevelsdictionary implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "Identyfikator")
    private Integer identyfikator;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "Name")
    private String name;

    public Accesslevelsdictionary() {
    }

    public Accesslevelsdictionary(Integer identyfikator) {
        this.identyfikator = identyfikator;
    }

    public Accesslevelsdictionary(Integer identyfikator, String name) {
        this.identyfikator = identyfikator;
        this.name = name;
    }

    public Integer getIdentyfikator() {
        return identyfikator;
    }

    public void setIdentyfikator(Integer identyfikator) {
        this.identyfikator = identyfikator;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        if (!(object instanceof Accesslevelsdictionary)) {
            return false;
        }
        Accesslevelsdictionary other = (Accesslevelsdictionary) object;
        if ((this.identyfikator == null && other.identyfikator != null) || (this.identyfikator != null && !this.identyfikator.equals(other.identyfikator))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Accesslevelsdictionary[ identyfikator=" + identyfikator + " ]";
    }
    
}
