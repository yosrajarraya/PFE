/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.csys.template.domain;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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

/**
 *
 * @author C
 */
@Entity
@Table(name = "details_delegation_access")
@Audited
@AuditTable("details_delegation_access_AUD")
public class DetailsDelegationAccess implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_details_delegation")
    private Integer idDetailsDelegation;
    @Basic(optional = false)
    @Column(name = "num_delegation", insertable = false, updatable = false)
    private Integer numDelegation;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "user_delegataire")
    private String userDelegataire;
    @Basic(optional = false)
    @Size(min = 1, max = 10)
    @Column(name = "user_delegant")
    private String userDelegant;
    @JoinColumn(name = "num_delegation", referencedColumnName = "num_delegation")
    @ManyToOne(optional = false)
    private DelegationAccess delegationAccess;
    @JoinColumn(name = "user_delegataire", referencedColumnName = "username", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Utilisateur utilisateur;

    public DetailsDelegationAccess() {
    }

    public Integer getIdDetailsDelegation() {
        return idDetailsDelegation;
    }

    public void setIdDetailsDelegation(Integer idDetailsDelegation) {
        this.idDetailsDelegation = idDetailsDelegation;
    }

    public Integer getNumDelegation() {
        return numDelegation;
    }

    public void setNumDelegation(Integer numDelegation) {
        this.numDelegation = numDelegation;
    }

    public String getUserDelegataire() {
        return userDelegataire;
    }

    public void setUserDelegataire(String userDelegataire) {
        this.userDelegataire = userDelegataire;
    }

   

    public String getUserDelegant() {
        return userDelegant;
    }

    public void setUserDelegant(String userDelegant) {
        this.userDelegant = userDelegant;
    }

    public DelegationAccess getDelegationAccess() {
        return delegationAccess;
    }

    public void setDelegationAccess(DelegationAccess delegationAccess) {
        this.delegationAccess = delegationAccess;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + Objects.hashCode(this.idDetailsDelegation);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DetailsDelegationAccess other = (DetailsDelegationAccess) obj;
        if (!Objects.equals(this.idDetailsDelegation, other.idDetailsDelegation)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DetailsDelegationAccess{" + "idDetailsDelegation=" + idDetailsDelegation + '}';
    }

    



}
