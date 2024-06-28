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
@Table(name = "access_button_grp")
@Audited
@AuditTable("access_button_grp_AUD")
public class AccessButtonGrp implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_access_button_group")
    private Integer idAccessButtonGroup;

    @Column(name = "id_group")
    private String groupeId;
    @Column(name = "id_button")
    private Integer buttonId;

    @NotAudited
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @JoinColumn(name = "id_group", referencedColumnName = "groupe", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Groupe groups;
    @NotAudited
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @JoinColumn(name = "id_button", referencedColumnName = "id_button", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Button button;

    public AccessButtonGrp() {
    }

    public AccessButtonGrp(Integer idAccessBG) {
        this.idAccessButtonGroup = idAccessBG;
    }

    public Integer getIdAccessButtonGroup() {
        return idAccessButtonGroup;
    }

    public void setIdAccessButtonGroup(Integer idAccessBG) {
        this.idAccessButtonGroup = idAccessBG;
    }

    public String getGroupeId() {
        return groupeId;
    }

    public void setGroupeId(String groupeId) {
        this.groupeId = groupeId;
    }

    public Integer getButtonId() {
        return buttonId;
    }

    public void setButtonId(Integer buttonId) {
        this.buttonId = buttonId;
    }

    public Groupe getGroups() {
        return groups;
    }

    public void setGroups(Groupe groups) {
        this.groups = groups;
    }

    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAccessButtonGroup != null ? idAccessButtonGroup.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AccessButtonGrp)) {
            return false;
        }
        AccessButtonGrp other = (AccessButtonGrp) object;
        if ((this.idAccessButtonGroup == null && other.idAccessButtonGroup != null) || (this.idAccessButtonGroup != null && !this.idAccessButtonGroup.equals(other.idAccessButtonGroup))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.csys.template.domain.AccessButtonGrp[ idAccessBG=" + idAccessButtonGroup + " ]";
    }

}
//    @Basic(optional = false)
//    @NotNull
//    @Size(min = 1, max = 10)
//    @Column(name = "name")
//    private String name;
//    @Basic(optional = false)
//    @NotNull
//    @Size(min = 1, max = 10)
//    @Column(name = "code")
//    private String code;
//    @Basic(optional = false)
//    @NotNull
//    @Column(name = "active")
//    private boolean active;
//    @Basic(optional = false)
//    @NotNull
//    @Size(min = 1, max = 2147483647)
//    @Column(name = "designation")
//    private String designation;
//    @Basic(optional = false)
//    @NotNull
//    @Column(name = "visible")
//    private boolean visible;
//    @Basic(optional = false)
//    @NotNull
//    @Size(min = 1, max = 20)
//    @Column(name = "control")
//    private String control;
