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
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.hibernate.envers.RelationTargetAuditMode;

@Entity
@Table(name = "access_module_grp")
@Audited
@AuditTable("access_module_grp_AUD")
public class AccessModuleGrp implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_access_module_group")
    private Integer idAccessModuleGroup;
//    @Basic(optional = false)
//    @NotNull
//    @Size(min = 1, max = 20)
//    @Column(name = "code")
//    private String code;

    @Column(name = "groupe")
    private String idGroupUser;

    @Column(name = "id_module")
    private Integer idModule;

    @NotAudited
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @JoinColumn(name = "groupe", referencedColumnName = "groupe", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Groupe groups;

    @NotAudited
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @JoinColumn(name = "id_module", referencedColumnName = "id_module", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Module module;

    public AccessModuleGrp() {
    }

    public Integer getIdAccessModuleGroup() {
        return idAccessModuleGroup;
    }

    public void setIdAccessModuleGroup(Integer idAccessModuleGroup) {
        this.idAccessModuleGroup = idAccessModuleGroup;
    }

    public String getIdGroupUser() {
        return idGroupUser;
    }

    public void setIdGroupUser(String idGroupUser) {
        this.idGroupUser = idGroupUser;
    }

    public Integer getIdModule() {
        return idModule;
    }

    public void setIdModule(Integer idModule) {
        this.idModule = idModule;
    }

    public Groupe getGroups() {
        return groups;
    }

    public void setGroups(Groupe groups) {
        this.groups = groups;
    }

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAccessModuleGroup != null ? idAccessModuleGroup.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AccessModuleGrp)) {
            return false;
        }
        AccessModuleGrp other = (AccessModuleGrp) object;
        if ((this.idAccessModuleGroup == null && other.idAccessModuleGroup != null) || (this.idAccessModuleGroup != null && !this.idAccessModuleGroup.equals(other.idAccessModuleGroup))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.csys.template.domain.AccessModuleGrp[ idAccessModuleG=" + idAccessModuleGroup + " ]";
    }

}
