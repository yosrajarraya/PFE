package com.csys.template.dto;

import com.csys.template.domain.Utilisateur;
import java.lang.Integer;
import java.lang.String;
import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CliniqueDTO {
  @NotNull
  private Integer idClinique;
  private String name;
  private Integer idUser;
  
  
  public Integer getIdClinique() {
    return idClinique;
  }

  public void setIdClinique(Integer idClinique) {
    this.idClinique = idClinique;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }


 
}

