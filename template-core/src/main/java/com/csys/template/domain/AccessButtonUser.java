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

@Entity
@Table(name = "access_button_user")
@Audited
@AuditTable("access_button_user_AUD")
public class AccessButtonUser implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_access_button_user")
    private Integer idAccessButtonUser;

    @Column(name = "id_button")
    private Integer idButton;

    @Column(name = "id_user")
    private String userId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "visible")
    private boolean visible;
    @Column(name = "num_delegate")
    private Integer numDelegate;
    @NotAudited
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @JoinColumn(name = "id_button", referencedColumnName = "id_button", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Button button;
    @NotAudited
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @JoinColumn(name = "id_user", referencedColumnName = "username", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Utilisateur utilisateurs;

    public AccessButtonUser() {
    }

    public AccessButtonUser(Integer idAccessBU) {
        this.idAccessButtonUser = idAccessBU;
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

    public Integer getIdAccessButtonUser() {
        return idAccessButtonUser;
    }

    public void setIdAccessButtonUser(Integer idAccessBU) {
        this.idAccessButtonUser = idAccessBU;
    }

    public Integer getIdButton() {
        return idButton;
    }

    public void setIdButton(Integer idButton) {
        this.idButton = idButton;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
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
        hash += (idAccessButtonUser != null ? idAccessButtonUser.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AccessButtonUser)) {
            return false;
        }
        AccessButtonUser other = (AccessButtonUser) object;
        if ((this.idAccessButtonUser == null && other.idAccessButtonUser != null) || (this.idAccessButtonUser != null && !this.idAccessButtonUser.equals(other.idAccessButtonUser))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.csys.template.domain.AccessButtonUser[ idAccessBU=" + idAccessButtonUser + " ]";
    }

}
