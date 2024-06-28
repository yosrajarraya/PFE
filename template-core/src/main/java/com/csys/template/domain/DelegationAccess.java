/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.csys.template.domain;

import com.csys.template.enumeration.EnumMotif;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.hibernate.envers.RelationTargetAuditMode;

@Entity
@Table(name = "delegation_access")
@Audited
@AuditTable("delegation_access_AUD")
public class DelegationAccess implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "num_delegation")
    private Integer numDelegation;
    @Basic(optional = false)
    @Column(name = "module")
    private Integer module;
    @Basic(optional = false)
    @NotNull
    @Column(name = "user_delegant")
    private String userDelegant;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dateDebut")
    private LocalDate dateDebut;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dateFin")
    private LocalDate dateFin;
    @Basic(optional = false)
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "motif")
    private EnumMotif motif;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date_creation")

    private LocalDateTime dateCreation;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "user_creation")
    private String userCreation;
    @Basic(optional = false)
    @NotNull
    @Column(name = "declenche")
    private boolean declenche;
    @Basic(optional = false)
    @NotNull
    @Column(name = "termine")
    private boolean termine;
    @JoinColumn(name = "user_delegant", referencedColumnName = "username", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Utilisateur userName;
    
    @NotAudited
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @JoinColumn(name = "module", referencedColumnName = "id_module",  insertable = false, updatable = false)
    @ManyToOne( fetch = FetchType.LAZY)
    private Module idModule;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "delegationAccess")
    private List<DetailsDelegationAccessMenu> detailsdelegationAccessMenu;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "numDelegations")
    private List<DetailsDelegationAccessButton> detailsdelegationAccessButton;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "delegationAccess")
    private List<DetailsDelegationAccess> detailsdelegationAccess;

    public DelegationAccess() {
    }

    public DelegationAccess(Integer numDelegation) {
        this.numDelegation = numDelegation;
    }

    public DelegationAccess(Integer numDelegation, int module, String userDelegant, LocalDate dateDebut, LocalDate dateFin, LocalDateTime dateCreation, String userCreation, boolean declenche, boolean termine) {
        this.numDelegation = numDelegation;
        this.module = module;
        this.userDelegant = userDelegant;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.dateCreation = dateCreation;
        this.userCreation = userCreation;
        this.declenche = declenche;
        this.termine = termine;
    }

    public Integer getNumDelegation() {
        return numDelegation;
    }

    public void setNumDelegation(Integer numDelegation) {
        this.numDelegation = numDelegation;
    }

    public Integer getModule() {
        return module;
    }

    public void setModule(Integer module) {
        this.module = module;
    }

    public Module getIdModule() {
        return idModule;
    }

    public void setIdModule(Module idModule) {
        this.idModule = idModule;
    }

    public String getUserDelegant() {
        return userDelegant;
    }

    public void setUserDelegant(String userDelegant) {
        this.userDelegant = userDelegant;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public EnumMotif getMotif() {
        return motif;
    }

    public void setMotif(EnumMotif motif) {
        this.motif = motif;
    }

    public Utilisateur getUserName() {
        return userName;
    }

    public void setUserName(Utilisateur userName) {
        this.userName = userName;
    }

    public LocalDateTime getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDateTime dateCreation) {
        this.dateCreation = dateCreation;
    }

    public String getUserCreation() {
        return userCreation;
    }

    public void setUserCreation(String userCreation) {
        this.userCreation = userCreation;
    }

    public boolean getDeclenche() {
        return declenche;
    }

    public void setDeclenche(boolean declenche) {
        this.declenche = declenche;
    }

    public boolean getTermine() {
        return termine;
    }

    public void setTermine(boolean termine) {
        this.termine = termine;
    }

    public List<DetailsDelegationAccess> getDetailsdelegationAccess() {
        return detailsdelegationAccess;
    }

    public void setDetailsdelegationAccess(List<DetailsDelegationAccess> detailsdelegationAccess) {
        this.detailsdelegationAccess = detailsdelegationAccess;
    }

    public List<DetailsDelegationAccessMenu> getDetailsdelegationAccessMenu() {
        return detailsdelegationAccessMenu;
    }

    public void setDetailsdelegationAccessMenu(List<DetailsDelegationAccessMenu> detailsdelegationAccessMenu) {
        this.detailsdelegationAccessMenu = detailsdelegationAccessMenu;
    }

    public List<DetailsDelegationAccessButton> getDetailsdelegationAccessButton() {
        return detailsdelegationAccessButton;
    }

    public void setDetailsdelegationAccessButton(List<DetailsDelegationAccessButton> detailsdelegationAccessButton) {
        this.detailsdelegationAccessButton = detailsdelegationAccessButton;
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
        if (!(object instanceof DelegationAccess)) {
            return false;
        }
        DelegationAccess other = (DelegationAccess) object;
        if ((this.numDelegation == null && other.numDelegation != null) || (this.numDelegation != null && !this.numDelegation.equals(other.numDelegation))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.csys.template.domain.DelegationAccess[ numDelegation=" + numDelegation + " ]";
    }

}
