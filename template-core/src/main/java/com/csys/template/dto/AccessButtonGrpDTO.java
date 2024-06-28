package com.csys.template.dto;

import java.lang.Integer;
import java.lang.String;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AccessButtonGrpDTO {

    private Integer id;

    private Integer idButton;

    private String idGroup;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public Integer getIdButton() {
        return idButton;
    }

    public void setIdButton(Integer idButton) {
        this.idButton = idButton;
    }

    public String getIdGroup() {
        return idGroup;
    }

    public void setIdGroup(String idGroup) {
        this.idGroup = idGroup;
    }

}
