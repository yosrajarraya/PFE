package com.csys.template.dto;

import com.csys.template.domain.Groupe;
import com.csys.template.domain.Menu;
import java.lang.Integer;
import javax.validation.constraints.NotNull;

public class AccessMenuGrpDTO {

    private Integer id;

    private String idGroupUser;

    private Integer idMenu;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    

    public String getIdGroupUser() {
        return idGroupUser;
    }

    public void setIdGroupUser(String idGroupUser) {
        this.idGroupUser = idGroupUser;
    }

    public Integer getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(Integer idMenu) {
        this.idMenu = idMenu;
    }

}
