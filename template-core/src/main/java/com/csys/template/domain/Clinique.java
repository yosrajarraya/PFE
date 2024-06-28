/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.csys.template.domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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

/**
 *
 * @author C
 */
@Entity
@Table(name = "clinique")
@Audited
@AuditTable("clinique_AUD")
public class Clinique implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_clinique")
    private Integer idClinique;
    @Size(max = 10)
    @Column(name = "name")
    private String name;

 
    public Clinique() {
    }

    public Clinique(Integer idClinique) {
        this.idClinique = idClinique;
    }

    public Integer getIdClinique() {
        return idClinique;
    }

    public void setIdClinique(Integer idClinique) {
        this.idClinique = idClinique;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idClinique != null ? idClinique.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Clinique)) {
            return false;
        }
        Clinique other = (Clinique) object;
        if ((this.idClinique == null && other.idClinique != null) || (this.idClinique != null && !this.idClinique.equals(other.idClinique))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.csys.template.domain.Clinique[ idClinique=" + idClinique + " ]";
    }

}
