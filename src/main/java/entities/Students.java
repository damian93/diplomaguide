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
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
    @NamedQuery(name = "Students.findByRegisterDate", query = "SELECT s FROM Students s WHERE s.registerDate = :registerDate")})
public class Students extends Accesslevel implements Serializable {
    private static final long serialVersionUID = 1L;
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Basic(optional = false)
//    @Column(name = "AccessLevelId")
//    private Long accessLevelId;
    @Basic(optional = false)
    @Column(name = "RegisterDate")
    @Temporal(TemporalType.DATE)
    private Date registerDate;
//    @JoinColumn(name = "AccessLevelId", referencedColumnName = "AccessLevelId", insertable = false, updatable = false)
//    @OneToOne(optional = false)
//    private Accesslevel accesslevel;

    public Students() {
        super.setName("S");
    }

//    public Students(Long accessLevelId) {
//        this.accessLevelId = accessLevelId;
//    }
//
//    public Students(Long accessLevelId, Date lastLoginDate) {
//        this.accessLevelId = accessLevelId;
//        this.lastLoginDate = lastLoginDate;
//    }
//
//    public Long getAccessLevelId() {
//        return accessLevelId;
//    }
//
//    public void setAccessLevelId(Long accessLevelId) {
//        this.accessLevelId = accessLevelId;
//    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date lastLoginDate) {
        this.registerDate = lastLoginDate;
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
//        if (!(object instanceof Students)) {
//            return false;
//        }
//        Students other = (Students) object;
//        if ((this.accessLevelId == null && other.accessLevelId != null) || (this.accessLevelId != null && !this.accessLevelId.equals(other.accessLevelId))) {
//            return false;
//        }
//        return true;
//    }
//
//    @Override
//    public String toString() {
//        return "entities.Students[ accessLevelId=" + accessLevelId + " ]";
//    }
    
}
