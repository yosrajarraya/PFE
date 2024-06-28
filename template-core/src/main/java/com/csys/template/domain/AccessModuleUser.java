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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.hibernate.envers.RelationTargetAuditMode;

/**
 *
 * @author C
 */
@Entity
@Table(name = "access_module_user")
@Audited
@AuditTable("access_module_user_AUD")
public class AccessModuleUser implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_access_module_user")
    private Integer idAccessModuleUser;
    @Basic(optional = false)
       
    @Column(name = "id_user")
    private String userId;
    
    @Column(name = "id_module")
    private Integer idModule;
    
    @NotAudited
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @JoinColumn(name = "id_module", referencedColumnName = "id_module", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Module module;
    @NotAudited
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @JoinColumn(name = "id_user", referencedColumnName = "username", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Utilisateur utilisateurs;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    public Module getModule() {
        return module;
    }

 
    

    public Utilisateur getUtilisateurs() {
        return utilisateurs;
    }

    public void setUtilisateurs(Utilisateur utilisateurs) {
        this.utilisateurs = utilisateurs;
    }
  
    public AccessModuleUser() {
    }

    public AccessModuleUser(Integer idAccessModuleUser) {
        this.idAccessModuleUser = idAccessModuleUser;
    }

 

    public Integer getIdAccessModuleUser() {
        return idAccessModuleUser;
    }

    public void setIdAccessModuleUser(Integer idAccessModuleUser) {
        this.idAccessModuleUser = idAccessModuleUser;
    }

    public Integer getIdModule() {
        return idModule;
    }

    public void setIdModule(Integer idModule) {
        this.idModule = idModule;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAccessModuleUser != null ? idAccessModuleUser.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AccessModuleUser)) {
            return false;
        }
        AccessModuleUser other = (AccessModuleUser) object;
        if ((this.idAccessModuleUser == null && other.idAccessModuleUser != null) || (this.idAccessModuleUser != null && !this.idAccessModuleUser.equals(other.idAccessModuleUser))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.csys.template.domain.AccessModuleUser[ idAccessModuleU=" + idAccessModuleUser + " ]";
    }

}
