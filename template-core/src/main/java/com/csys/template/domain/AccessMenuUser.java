/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.csys.template.domain;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "access_menu_user")
@Audited
@AuditTable("access_menu_user_AUD")
public class AccessMenuUser implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_access_menu_user")
    private Integer idAccessMenuUser;

    @Column(name = "id_user")
    private String userId;

    @Column(name = "id_menu")
    private Integer idMenu;
 @Basic(optional = false)
    @NotNull
    @Column(name = "visible")
    private boolean visible;
    @Column(name = "num_delegate")
    private Integer numDelegate;
    @NotAudited
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @JoinColumn(name = "id_menu", referencedColumnName = "id_menu", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Menu menu;

    @NotAudited
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @JoinColumn(name = "id_user", referencedColumnName = "username", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Utilisateur utilisateurs;

    public AccessMenuUser() {
    }

    public AccessMenuUser(Integer idAccessMenuUser) {
        this.idAccessMenuUser = idAccessMenuUser;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public Integer getNumDelegate() {
        return numDelegate;
    }

    public void setNumDelegate(Integer numDelegate) {
        this.numDelegate = numDelegate;
    }

    public Integer getIdAccessMenuUser() {
        return idAccessMenuUser;
    }

    public void setIdAccessMenuUser(Integer idAccessMenuUser) {
        this.idAccessMenuUser = idAccessMenuUser;
    }

  

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

  

    public Integer getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(Integer idMenu) {
        this.idMenu = idMenu;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
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
        hash += (idAccessMenuUser != null ? idAccessMenuUser.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AccessMenuUser)) {
            return false;
        }
        AccessMenuUser other = (AccessMenuUser) object;
        if ((this.idAccessMenuUser == null && other.idAccessMenuUser != null) || (this.idAccessMenuUser != null && !this.idAccessMenuUser.equals(other.idAccessMenuUser))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.csys.template.domain.AccessMenuUser[ idAccessMenuU=" + idAccessMenuUser + " ]";
    }

}
