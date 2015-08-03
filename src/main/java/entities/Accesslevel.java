/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Damian
 */
@Entity
@Table(name = "accesslevel")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "Name")
@NamedQueries({
    @NamedQuery(name = "Accesslevel.findAll", query = "SELECT a FROM Accesslevel a"),
    @NamedQuery(name = "Accesslevel.findByAccessLevelId", query = "SELECT a FROM Accesslevel a WHERE a.accessLevelId = :accessLevelId"),
    @NamedQuery(name = "Accesslevel.findByName", query = "SELECT a FROM Accesslevel a WHERE a.name = :name"),
    @NamedQuery(name = "Accesslevel.findByVersion", query = "SELECT a FROM Accesslevel a WHERE a.version = :version")})
public class Accesslevel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "AccessLevelId")
    private Long accessLevelId;
    @Basic(optional = false)
//    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "Name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IsActive")
    private boolean isActive;
    @Basic(optional = false)
    @NotNull
    @Version
    @Column(name = "Version")
    private long version;
//    @OneToOne(cascade = CascadeType.ALL, mappedBy = "accesslevel")
//    private Teachers teachers;
    @JoinColumn(name = "UserId", referencedColumnName = "UserId")
    @ManyToOne(optional = false)
    private Users userId;
//    @OneToOne(cascade = CascadeType.ALL, mappedBy = "accesslevel")
//    private Students students;
//    @OneToOne(cascade = CascadeType.ALL, mappedBy = "accesslevel")
//    private Administrators administrators;

    public Accesslevel() {
    }

    public Accesslevel(Long accessLevelId) {
        this.accessLevelId = accessLevelId;
    }

    public Accesslevel(Long accessLevelId, String name, long version) {
        this.accessLevelId = accessLevelId;
        this.name = name;
        this.version = version;
    }

    public Long getAccessLevelId() {
        return accessLevelId;
    }

    public void setAccessLevelId(Long accessLevelId) {
        this.accessLevelId = accessLevelId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getVersion() {
        return version;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }
    
    

    public void setVersion(long version) {
        this.version = version;
    }

//    public Teachers getTeachers() {
//        return teachers;
//    }
//
//    public void setTeachers(Teachers teachers) {
//        this.teachers = teachers;
//    }
    public Users getUserId() {
        return userId;
    }

    public void setUserId(Users userId) {
        this.userId = userId;
    }

//    public Students getStudents() {
//        return students;
//    }
//
//    public void setStudents(Students students) {
//        this.students = students;
//    }
//
//    public Administrators getAdministrators() {
//        return administrators;
//    }
//    public void setAdministrators(Administrators administrators) {
//        this.administrators = administrators;
//    }
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (accessLevelId != null ? accessLevelId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Accesslevel)) {
            return false;
        }
        Accesslevel other = (Accesslevel) object;
        if ((this.accessLevelId == null && other.accessLevelId != null) || (this.accessLevelId != null && !this.accessLevelId.equals(other.accessLevelId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Accesslevel[ accessLevelId=" + accessLevelId + " ]";
    }

}
