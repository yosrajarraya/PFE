/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.csys.template.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

/**
 *
 * @author Yosra
 */
@Entity
@Table(name = "groupe")
@Audited
@AuditTable("groupe_AUD")
public class Groupe implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "groupe")
    private String groupe;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "designation")
    private String designation;
    @Basic(optional = false)
    @NotNull
    @Column(name = "active")
    private boolean active;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20, message = "la taille doit être comprise entre 1 et 20")
    @Column(name = "user_creation")
    private String userCreation;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date_creation")
    private LocalDateTime dateCreation;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "groups")
    private List<GroupUser> groupUsers;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "groups")
    private List<AccessModuleGrp> accessModuleGrps;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "groups")
    private List<AccessButtonGrp> accessButtonGrpList;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "groups")
    private List<AccessMenuGrp> accessMenuGrpList;


    public Groupe() {
    }

    public Groupe(String groupe) {
        this.groupe = groupe; 
    }

    public LocalDateTime getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDateTime dateCreation) {
        this.dateCreation = dateCreation;
    }

    public List<GroupUser> getGroupUsers() {
        return groupUsers;
    }

    public void setGroupUsers(List<GroupUser> groupUsers) {
        this.groupUsers = groupUsers;
    }

  
    public List<AccessModuleGrp> getAccessModuleGrps() {
        return accessModuleGrps;
    }

    public void setAccessModuleGrps(List<AccessModuleGrp> accessModuleGrps) {
        this.accessModuleGrps = accessModuleGrps;
    }

    public List<AccessButtonGrp> getAccessButtonGrpList() {
        return accessButtonGrpList;
    }

    public void setAccessButtonGrpList(List<AccessButtonGrp> accessButtonGrpList) {
        this.accessButtonGrpList = accessButtonGrpList;
    }

    public List<AccessMenuGrp> getAccessMenuGrpList() {
        return accessMenuGrpList;
    }

    public void setAccessMenuGrpList(List<AccessMenuGrp> accessMenuGrpList) {
        this.accessMenuGrpList = accessMenuGrpList;
    }

    public Groupe(String groupe, String designation, boolean active, String userCreation, LocalDateTime dateCreation) {
        this.groupe = groupe;
        this.designation = designation;
        this.active = active;
        this.userCreation = userCreation;
        this.dateCreation = dateCreation;
    }

    public String getGroupe() {
        return groupe;
    }

    public void setGroupe(String groupe) {
        this.groupe = groupe;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getUserCreation() {
        return userCreation;
    }

    public void setUserCreation(String userCreation) {
        this.userCreation = userCreation;
    }



    @Override
    public int hashCode() {
        int hash = 0;
        hash += (groupe != null ? groupe.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Groupe)) {
            return false;
        }
        Groupe other = (Groupe) object;
        if ((this.groupe == null && other.groupe != null) || (this.groupe != null && !this.groupe.equals(other.groupe))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.csys.template.domain.Groupe[ groupe=" + groupe + " ]";
    }
    
}
