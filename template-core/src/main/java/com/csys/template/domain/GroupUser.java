/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.csys.template.domain;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

/**
 *
 * @author Yosra
 */
@Entity
@Table(name = "group_user")
@Audited
@AuditTable("group_user_AUD")
public class GroupUser implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Basic(optional = false)
   
    @Column(name = "group_user")
    private Integer groupUser;
       
  @Column(name = "groupe") 
    private String groupe;

    @Column(name = "username")
    private String username;
    
    @JoinColumn(name = "groupe", referencedColumnName = "groupe", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Groupe groups;
    

    @JoinColumn(name = "username", referencedColumnName = "username", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Utilisateur utilisateurs;

    public GroupUser() {
    }

    public GroupUser(Integer groupUser) {
        this.groupUser = groupUser;
    }

    public Integer getGroupUser() {
        return groupUser;
    }

    public void setGroupUser(Integer groupUser) {
        this.groupUser = groupUser;
    }

    public String getGroupe() {
        return groupe;
    }

    public void setGroupe(String groupe) {
        this.groupe = groupe;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Groupe getGroups() {
        return groups;
    }

    public void setGroups(Groupe groups) {
        this.groups = groups;
    }

    public Utilisateur getUtilisateurs() {
        return utilisateurs;
    }

    public void setUtilisateurs(Utilisateur utilisateurs) {
        this.utilisateurs = utilisateurs;
    }



    @Override
    public int hashCode() {
        int hash = 0;
        hash += (groupUser != null ? groupUser.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GroupUser)) {
            return false;
        }
        GroupUser other = (GroupUser) object;
        if ((this.groupUser == null && other.groupUser != null) || (this.groupUser != null && !this.groupUser.equals(other.groupUser))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.csys.template.domain.GroupUser[ groupUser=" + groupUser + " ]";
    }
    
}
