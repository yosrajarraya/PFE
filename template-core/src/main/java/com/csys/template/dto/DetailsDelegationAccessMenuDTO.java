package com.csys.template.dto;

import java.lang.Integer;

public class DetailsDelegationAccessMenuDTO {

    private Integer idDelegationMenu;
    private Integer numDelegation;
    private Integer module;
    private Integer menus;
    private MenuDTO menu;
    private String userDelegant;

    public Integer getIdDelegationMenu() {
        return idDelegationMenu;
    }

    public void setIdDelegationMenu(Integer idDelegationMenu) {
        this.idDelegationMenu = idDelegationMenu;
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

    public Integer getMenus() {
        return menus;
    }

    public void setMenus(Integer menus) {
        this.menus = menus;
    }

    public String getUserDelegant() {
        return userDelegant;
    }

    public void setUserDelegant(String userDelegant) {
        this.userDelegant = userDelegant;
    }

    public MenuDTO getMenu() {
        return menu;
    }

    public void setMenu(MenuDTO menu) {
        this.menu = menu;
    }

}
