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
@Table(name = "details_delegation_access_button")
@Audited
@AuditTable("details_delegation_access_button_AUD")
public class DetailsDelegationAccessButton implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_delegation_button")
    private Integer idDelegationButton;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Module")
    private Integer module;
    @Basic(optional = false)
    @NotNull
    @Column(name = "user_delegant")
    private String userDelegant;
    @Basic(optional = false)
    @NotNull
    @Column(name = "button")
    private Integer button;
    @Column(name = "num_delegation", insertable = false, updatable = false)
    private Integer numDelegation;
    @JoinColumn(name = "num_delegation", referencedColumnName = "num_delegation", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private DelegationAccess numDelegations;
    @NotAudited
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @JoinColumn(name = "button", referencedColumnName = "id_button", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Button idButton;

    public DetailsDelegationAccessButton() {
    }

    public DetailsDelegationAccessButton(Integer idDelegationButton) {
        this.idDelegationButton = idDelegationButton;
    }

    public DetailsDelegationAccessButton(Integer idDelegationButton, int module, String numDelegant, int button) {
        this.idDelegationButton = idDelegationButton;
        this.module = module;
        this.userDelegant = numDelegant;
        this.button = button;
    }

    public Integer getIdDelegationButton() {
        return idDelegationButton;
    }

    public void setIdDelegationButton(Integer idDelegationButton) {
        this.idDelegationButton = idDelegationButton;
    }

    public Integer getModule() {
        return module;
    }

    public void setModule(Integer module) {
        this.module = module;
    }

    public String getUserDelegant() {
        return userDelegant;
    }

    public void setUserDelegant(String userDelegant) {
        this.userDelegant = userDelegant;
    }

    public Integer getButton() {
        return button;
    }

    public void setButton(Integer button) {
        this.button = button;
    }

    public Integer getNumDelegation() {
        return numDelegation;
    }

    public void setNumDelegation(Integer numDelegation) {
        this.numDelegation = numDelegation;
    }

 
    public DelegationAccess getNumDelegations() {
        return numDelegations;
    }

    public void setNumDelegations(DelegationAccess numDelegations) {
        this.numDelegations = numDelegations;
    }

    public Button getIdButton() {
        return idButton;
    }

    public void setIdButton(Button idButton) {
        this.idButton = idButton;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDelegationButton != null ? idDelegationButton.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetailsDelegationAccessButton)) {
            return false;
        }
        DetailsDelegationAccessButton other = (DetailsDelegationAccessButton) object;
        if ((this.idDelegationButton == null && other.idDelegationButton != null) || (this.idDelegationButton != null && !this.idDelegationButton.equals(other.idDelegationButton))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.csys.template.domain.DetailsDelegationAccessButton[ idDelegationButton=" + idDelegationButton + " ]";
    }

}
