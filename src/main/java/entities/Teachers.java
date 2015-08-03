/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
    @NamedQuery(name = "Teachers.findByAccessLevelId", query = "SELECT t FROM Teachers t WHERE t.accessLevelId = :accessLevelId")})
public class Teachers extends Accesslevel implements Serializable {
    private static final long serialVersionUID = 1L;
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Basic(optional = false)
//    @Column(name = "AccessLevelId")
//    private Long accessLevelId;
    @JoinColumn(name = "Degree", referencedColumnName = "Id")
    @ManyToOne(optional = false)
    private Degrees degree;
//    @JoinColumn(name = "AccessLevelId", referencedColumnName = "AccessLevelId", insertable = false, updatable = false)
//    @OneToOne(optional = false)
//    private Accesslevel accesslevel;

    public Teachers() {
        super.setName("T");
    }

//    public Teachers(Long accessLevelId) {
//        this.accessLevelId = accessLevelId;
//    }
//
//    public Long getAccessLevelId() {
//        return accessLevelId;
//    }
//
//    public void setAccessLevelId(Long accessLevelId) {
//        this.accessLevelId = accessLevelId;
//    }

    public Degrees getDegree() {
        return degree;
    }

    public void setDegree(Degrees degree) {
        this.degree = degree;
    }

//    public Accesslevel getAccesslevel() {
//        return accesslevel;
//    }
//
//    public void setAccesslevel(Accesslevel accesslevel) {
//        this.accesslevel = accesslevel;
//    }

//    @Override
//    public int hashCode() {
//        int hash = 0;
//        hash += (accessLevelId != null ? accessLevelId.hashCode() : 0);
//        return hash;
//    }
//
//    @Override
//    public boolean equals(Object object) {
//        // TODO: Warning - this method won't work in the case the id fields are not set
//        if (!(object instanceof Teachers)) {
//            return false;
//        }
//        Teachers other = (Teachers) object;
//        if ((this.accessLevelId == null && other.accessLevelId != null) || (this.accessLevelId != null && !this.accessLevelId.equals(other.accessLevelId))) {
//            return false;
//        }
//        return true;
//    }
//
//    @Override
//    public String toString() {
//        return "entities.Teachers[ accessLevelId=" + accessLevelId + " ]";
//    }
    
}
