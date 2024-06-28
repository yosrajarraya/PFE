package com.csys.template.dto;

import java.lang.Integer;
import java.lang.String;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AccessButtonUserDTO {

    private Integer idAccessButtonUser;
    private Integer idButton;
    private String idUser;
    private boolean visible;
    private Integer numDelegate;

    public Integer getIdAccessButtonUser() {
        return idAccessButtonUser;
    }

    public void setIdAccessButtonUser(Integer idAccessButtonUser) {
        this.idAccessButtonUser = idAccessButtonUser;
    }

    public Integer getIdButton() {
        return idButton;
    }

    public void setIdButton(Integer idButton) {
        this.idButton = idButton;
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
