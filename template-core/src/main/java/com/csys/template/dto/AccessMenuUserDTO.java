package com.csys.template.dto;

import java.lang.Integer;
import javax.validation.constraints.NotNull;

public class AccessMenuUserDTO {

    private Integer idAccessMenuUser;
    private boolean visible;
    private Integer numDelegate;

    private Integer idMenu;

    private String idUser;

    public Integer getIdAccessMenuUser() {
        return idAccessMenuUser;
    }

    public void setIdAccessMenuUser(Integer idAccessMenuUser) {
        this.idAccessMenuUser = idAccessMenuUser;
    }

    public Integer getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(Integer idMenu) {
        this.idMenu = idMenu;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
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

}
