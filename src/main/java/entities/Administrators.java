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

/**
 *
 * @author Damian
 */
@Entity
@Table(name = "administrators")
@DiscriminatorValue("A")
@NamedQueries({
    @NamedQuery(name = "Administrators.findAll", query = "SELECT a FROM Administrators a"),
    @NamedQuery(name = "Administrators.findByAccessLevelId", query = "SELECT a FROM Administrators a WHERE a.accessLevelId = :accessLevelId"),
    @NamedQuery(name = "Administrators.findByRegisterDate", query = "SELECT a FROM Administrators a WHERE a.registerDate = :registerDate")})
public class Administrators extends Accesslevel implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "RegisterDate")
    @Temporal(TemporalType.DATE)
    private Date registerDate;

    public Administrators() {
        super.setName("A");
    }


    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    
}
