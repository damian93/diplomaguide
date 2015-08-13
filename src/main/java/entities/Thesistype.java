/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Damian
 */
@Entity
@Table(name = "thesistype")
@NamedQueries({
    @NamedQuery(name = "Thesistype.findAll", query = "SELECT t FROM Thesistype t"),
    @NamedQuery(name = "Thesistype.findByThesisTypeId", query = "SELECT t FROM Thesistype t WHERE t.thesisTypeId = :thesisTypeId"),
    @NamedQuery(name = "Thesistype.findByName", query = "SELECT t FROM Thesistype t WHERE t.name = :name")})
public class Thesistype implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ThesisTypeId")
    private Long thesisTypeId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Name")
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "type")
    private Collection<Thesis> thesisCollection;

    public Thesistype() {
    }

    public Thesistype(Long thesisTypeId) {
        this.thesisTypeId = thesisTypeId;
    }

    public Thesistype(Long thesisTypeId, String name) {
        this.thesisTypeId = thesisTypeId;
        this.name = name;
    }

    public Long getThesisTypeId() {
        return thesisTypeId;
    }

    public void setThesisTypeId(Long thesisTypeId) {
        this.thesisTypeId = thesisTypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<Thesis> getThesisCollection() {
        return thesisCollection;
    }

    public void setThesisCollection(Collection<Thesis> thesisCollection) {
        this.thesisCollection = thesisCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (thesisTypeId != null ? thesisTypeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Thesistype)) {
            return false;
        }
        Thesistype other = (Thesistype) object;
        if ((this.thesisTypeId == null && other.thesisTypeId != null) || (this.thesisTypeId != null && !this.thesisTypeId.equals(other.thesisTypeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Thesistype[ thesisTypeId=" + thesisTypeId + " ]";
    }
    
}
