package com.csys.template.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.lang.String;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class GroupeDTO {

    private String groupe;

    private String designation;

    private boolean active;

    private String userCreation;
    private LocalDateTime dateCreation;
    private List<GroupUserDTO> groupUsers;

    private List<AccessModuleGrpDTO> accessModuleGrps;

    private List<AccessButtonGrpDTO> accessButtonGrpList;
    private List<AccessMenuGrpDTO> accessMenuGrpList;

    public String getGroupe() {
        return groupe;
    }

    public void setGroupe(String groupe) {
        this.groupe = groupe;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getUserCreation() {
        return userCreation;
    }

    public void setUserCreation(String userCreation) {
        this.userCreation = userCreation;
    }

    public String getDateCreation() {
        return DateTimeFormatter.ofPattern("dd/MMMM/yyyy HH:mm").format(dateCreation);

    }

    public void setDateCreation(LocalDateTime dateCreation) {
        this.dateCreation = dateCreation;
    }

    public List<AccessModuleGrpDTO> getAccessModuleGrps() {
        return accessModuleGrps;
    }

    public void setAccessModuleGrps(List<AccessModuleGrpDTO> accessModuleGrps) {
        this.accessModuleGrps = accessModuleGrps;
    }

    public List<AccessButtonGrpDTO> getAccessButtonGrpList() {
        return accessButtonGrpList;
    }

    public void setAccessButtonGrpList(List<AccessButtonGrpDTO> accessButtonGrpList) {
        this.accessButtonGrpList = accessButtonGrpList;
    }

    public List<AccessMenuGrpDTO> getAccessMenuGrpList() {
        return accessMenuGrpList;
    }

    public void setAccessMenuGrpList(List<AccessMenuGrpDTO> accessMenuGrpList) {
        this.accessMenuGrpList = accessMenuGrpList;
    }

    public List<GroupUserDTO> getGroupUsers() {
        return groupUsers;
    }

    public void setGroupUsers(List<GroupUserDTO> GroupUsers) {
        this.groupUsers = GroupUsers;
    }

}
