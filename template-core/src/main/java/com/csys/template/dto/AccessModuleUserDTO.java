package com.csys.template.dto;

import java.lang.Integer;
import java.lang.String;
import javax.validation.constraints.Size;

public class AccessModuleUserDTO {
  private Integer idAccessModuleUser;


  private Integer idModule;

  private String userId;

  public Integer getIdAccessModuleUser() {
    return idAccessModuleUser;
  }

  public void setIdAccessModuleUser(Integer idAccessModuleUser) {
    this.idAccessModuleUser = idAccessModuleUser;
  }



  public Integer getIdModule() {
    return idModule;
  }

  public void setIdModule(Integer idModule) {
    this.idModule = idModule;
  }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

 


}

