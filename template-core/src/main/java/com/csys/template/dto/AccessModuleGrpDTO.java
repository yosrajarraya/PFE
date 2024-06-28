package com.csys.template.dto;

import com.csys.template.domain.Groupe;
import com.csys.template.domain.Module;
import java.lang.Integer;
import java.lang.String;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AccessModuleGrpDTO {

    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    private String idGroupUser;

    private Integer idModule;

   

    public String getIdGroupUser() {
        return idGroupUser;
    }

    public void setIdGroupUser(String idGroupUser) {
        this.idGroupUser = idGroupUser;
    }

   
    public Integer getIdModule() {
        return idModule;
    }

    public void setIdModule(Integer idModule) {
        this.idModule = idModule;
    }

}
