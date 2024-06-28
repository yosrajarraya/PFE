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
@Table(name = "menu")

public class Menu implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "code_menu")
    private String codeMenu;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "designation")
    private String designation;
    @Basic(optional = false)
    @NotNull
    @Column(name = "orders")
    private String orders;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "logo")
    private String logo;
   

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_menu")
    private Integer idMenu;
    @OneToMany(mappedBy = "codeMenuPrincipal")
    private List<Menu> menuList;
    @JoinColumn(name = "code_menu_principal", referencedColumnName = "code_menu")
    @ManyToOne
    private Menu codeMenuPrincipal;
    @Column(name = "id_module")
    private Integer idModule;

    @NotAudited
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @JoinColumn(name = "id_module", referencedColumnName = "id_module", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Module module;
    
    @NotAudited
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "menu")
    private List<AccessMenuUser> accessMenuUsers;

   
    @NotAudited
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "menu")
    private List<AccessMenuGrp> accessMenuGrpList;

    @NotAudited
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
     @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true, mappedBy = "menu")
    private List<Button> buttonList;

    public Menu() {
    }

    public Menu(Integer idMenu) {
        this.idMenu = idMenu;
    }

    public Menu(Integer idMenu, String codeMenu, String designation, String orders, String logo) {
        this.idMenu = idMenu;
        this.codeMenu = codeMenu;
        this.designation = designation;
        this.orders = orders;
        this.logo = logo;
    }

    public Integer getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(Integer idMenu) {
        this.idMenu = idMenu;
    }
      public List<Button> getButtonList() {
        return buttonList;
    }

    public void setButtonList(List<Button> buttonList) {
        this.buttonList = buttonList;
    }
   

    public String getCodeMenu() {
        return codeMenu;
    }

    public void setCodeMenu(String codeMenu) {
        this.codeMenu = codeMenu;
    }


    public List<Menu> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<Menu> menuList) {
        this.menuList = menuList;
    }

    public Menu getCodeMenuPrincipal() {
        return codeMenuPrincipal;
    }

    public void setCodeMenuPrincipal(Menu codeMenuPrincipal) {
        this.codeMenuPrincipal = codeMenuPrincipal;
    }

    public Integer getIdModule() {
        return idModule;
    }

    public void setIdModule(Integer idModule) {
        this.idModule = idModule;
    }

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    public List<AccessMenuUser> getAccessMenuUsers() {
        return accessMenuUsers;
    }

    public void setAccessMenuUsers(List<AccessMenuUser> accessMenuUsers) {
        this.accessMenuUsers = accessMenuUsers;
    }


    public List<AccessMenuGrp> getAccessMenuGrpList() {
        return accessMenuGrpList;
    }

    public void setAccessMenuGrpList(List<AccessMenuGrp> accessMenuGrpList) {
        this.accessMenuGrpList = accessMenuGrpList;
    }

   

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMenu != null ? idMenu.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Menu)) {
            return false;
        }
        Menu other = (Menu) object;
        if ((this.idMenu == null && other.idMenu != null) || (this.idMenu != null && !this.idMenu.equals(other.idMenu))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.csys.template.domain.Menu[ idMenu=" + idMenu + " ]";
    }



    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getOrders() {
        return orders;
    }

    public void setOrders(String orders) {
        this.orders = orders;
    }


    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

 
}
