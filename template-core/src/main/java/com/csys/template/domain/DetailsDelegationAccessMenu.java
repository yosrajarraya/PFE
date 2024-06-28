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
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.hibernate.envers.RelationTargetAuditMode;

/**
 *
 * @author C
 */
@Entity
@Table(name = "details_delegation_access_menu")
@Audited
@AuditTable("details_delegation_access_menu_AUD")
public class DetailsDelegationAccessMenu implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_delegation_menu")
    private Integer idDelegationMenu;
    @Column(name = "num_delegation", insertable = false, updatable = false)
    private Integer numDelegation;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Module")
    private Integer modules;

    @Column(name = "menu")
    private Integer menus;
    @Column(name = "user_delegant")
    private String userDelegant;
 
    @JoinColumn(name = "num_delegation", referencedColumnName = "num_delegation", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private DelegationAccess delegationAccess;
    @NotAudited
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @JoinColumn(name = "menu", referencedColumnName = "id_menu", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Menu menu;
//    @JoinColumn(name = "Username", referencedColumnName = "username", nullable = false, insertable = false, updatable = false)
//    @ManyToOne(optional = false, fetch = FetchType.LAZY)
//    private Utilisateur username;

    public DetailsDelegationAccessMenu() {
    }

    public DetailsDelegationAccessMenu(Integer numDelegation) {
        this.numDelegation = numDelegation;
    }

    public DetailsDelegationAccessMenu(Integer numDelegation, int module) {
        this.numDelegation = numDelegation;
        this.modules = module;
    }

    public DelegationAccess getDelegationAccess() {
        return delegationAccess;
    }

    public void setDelegationAccess(DelegationAccess delegationAccess) {
        this.delegationAccess = delegationAccess;
    }

    public Integer getNumDelegation() {
        return numDelegation;
    }

    public void setNumDelegation(Integer numDelegation) {
        this.numDelegation = numDelegation;
    }

    public int getModule() {
        return modules;
    }

    public void setModule(int module) {
        this.modules = module;
    }

    public Integer getIdDelegationMenu() {
        return idDelegationMenu;
    }

    public void setIdDelegationMenu(Integer idDelegationMenu) {
        this.idDelegationMenu = idDelegationMenu;
    }

    public Integer getModules() {
        return modules;
    }

    public void setModules(Integer modules) {
        this.modules = modules;
    }

    public Integer getMenus() {
        return menus;
    }

    public void setMenus(Integer menus) {
        this.menus = menus;
    }

    public String getUserDelegant() {
        return userDelegant;
    }

    public void setUserDelegant(String userDelegant) {
        this.userDelegant = userDelegant;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numDelegation != null ? numDelegation.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetailsDelegationAccessMenu)) {
            return false;
        }
        DetailsDelegationAccessMenu other = (DetailsDelegationAccessMenu) object;
        if ((this.numDelegation == null && other.numDelegation != null) || (this.numDelegation != null && !this.numDelegation.equals(other.numDelegation))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.csys.template.domain.DetailsDelegationAccessMenu[ numDelegation=" + numDelegation + " ]";
    }

}
