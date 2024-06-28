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
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.hibernate.envers.RelationTargetAuditMode;

/**
 *
 * @author C
 */
@Entity
@Table(name = "button")

public class Button implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "code_button")
    private String codeButton;
    @Size(max = 10)
    @Column(name = "code_button_principal")
    private String codeButtonPrincipal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "orders")
    private int order;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "designation")
    private String designation;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "logo")
    private String logo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "button")
    private List<DetailsDelegationAccessButton> detailsDelegationAccessButtonList;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_button")
    private Integer idButton;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idButton")
    private List<AccessButtonUser> accessButtonUsers;

    @Column(name = "id_menu")
    private Integer idMenu;

    @NotAudited
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_menu", referencedColumnName = "id_menu", nullable = false, insertable = false, updatable = false)
    private Menu menu;

    @NotAudited
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "button")
    private List<AccessButtonGrp> accessButtonGrpList;

    public Button() {
    }

    public Button(Integer idButton) {
        this.idButton = idButton;
    }

    public Button(Integer idButton, String codeButton, String codeButtonPrincipal, int order, String designation, String logo) {
        this.idButton = idButton;

        this.codeButton = codeButton;
        this.codeButtonPrincipal = codeButtonPrincipal;
        this.order = order;
        this.designation = designation;
        this.logo = logo;
    }

    public String getCodeButton() {
        return codeButton;
    }

    public void setCodeButton(String codeButton) {
        this.codeButton = codeButton;
    }

    public String getCodeButtonPrincipal() {
        return codeButtonPrincipal;
    }

    public void setCodeButtonPrincipal(String codeButtonPrincipal) {
        this.codeButtonPrincipal = codeButtonPrincipal;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public Integer getIdButton() {
        return idButton;
    }

    public void setIdButton(Integer idButton) {
        this.idButton = idButton;
    }

    public List<AccessButtonUser> getAccessButtonUsers() {
        return accessButtonUsers;
    }

    public void setAccessButtonUsers(List<AccessButtonUser> accessButtonUsers) {
        this.accessButtonUsers = accessButtonUsers;
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

    public void setMenu(Menu Menu) {
        this.menu = Menu;
    }

    public List<AccessButtonGrp> getAccessButtonGrpList() {
        return accessButtonGrpList;
    }

    public void setAccessButtonGrpList(List<AccessButtonGrp> accessButtonGrpList) {
        this.accessButtonGrpList = accessButtonGrpList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idButton != null ? idButton.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Button)) {
            return false;
        }
        Button other = (Button) object;
        if ((this.idButton == null && other.idButton != null) || (this.idButton != null && !this.idButton.equals(other.idButton))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.csys.template.domain.Button[ idButton=" + idButton + " ]";
    }

   


    public List<DetailsDelegationAccessButton> getDetailsDelegationAccessButtonList() {
        return detailsDelegationAccessButtonList;
    }

    public void setDetailsDelegationAccessButtonList(List<DetailsDelegationAccessButton> detailsDelegationAccessButtonList) {
        this.detailsDelegationAccessButtonList = detailsDelegationAccessButtonList;
    }


    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

  
}
