package com.csys.template.domain;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "access_menu_grp")
@Audited
@AuditTable("access_menu_grp_AUD")
public class AccessMenuGrp implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_access_menu_group")
    private Integer idAccessMenuGroup;
//    @Basic(optional = false)
//    @NotNull
//    @Column(name = "visible")
//    private boolean visible;

    @Column(name = "groupe")
    private String groupeId;

    @Column(name = "id_menu")
    private Integer menuId;
    @NotAudited
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @JoinColumn(name = "groupe", referencedColumnName = "groupe", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Groupe groups;
    @NotAudited
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @JoinColumn(name = "id_menu", referencedColumnName = "id_menu", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Menu menu;

    public AccessMenuGrp() {
    }

    public AccessMenuGrp(Integer idAccessMenuGroup) {
        this.idAccessMenuGroup = idAccessMenuGroup;
    }

    public Integer getIdAccessMenuGroup() {
        return idAccessMenuGroup;
    }

    public void setIdAccessMenuGroup(Integer idAccessMenuGroup) {
        this.idAccessMenuGroup = idAccessMenuGroup;
    }

    public Groupe getGroups() {
        return groups;
    }

    public void setGroups(Groupe groups) {
        this.groups = groups;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public String getGroupeId() {
        return groupeId;
    }

    public void setGroupeId(String groupeId) {
        this.groupeId = groupeId;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.idAccessMenuGroup);
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
        final AccessMenuGrp other = (AccessMenuGrp) obj;
        if (!Objects.equals(this.idAccessMenuGroup, other.idAccessMenuGroup)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "AccessMenuGrp{" + "idAccessMenuGroup=" + idAccessMenuGroup + '}';
    }

}
