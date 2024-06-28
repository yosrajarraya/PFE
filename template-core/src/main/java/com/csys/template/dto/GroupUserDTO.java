package com.csys.template.dto;

import com.csys.template.domain.Groupe;
import com.csys.template.domain.Utilisateur;
import java.lang.Integer;
import javax.validation.constraints.NotNull;

public class GroupUserDTO {

    private Integer groupUser;

    private String groupe;

    private String username;

    public Integer getGroupUser() {
        return groupUser;
    }

    public void setGroupUser(Integer groupUser) {
        this.groupUser = groupUser;
    }

    public String getGroupe() {
        return groupe;
    }

    public void setGroupe(String groupe) {
        this.groupe = groupe;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


}
