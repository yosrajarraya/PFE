package com.csys.template.dto;

import java.lang.Boolean;
import java.lang.Integer;
import java.lang.String;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.validation.constraints.NotNull;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.AssertTrue;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UtilisateurDTO {

    @NotNull
    @ApiModelProperty(position = 0)
    private String username;
    private String designation;
    @ApiModelProperty(position = 1)
    private String password;
    private String confirmPassword;
    private boolean active;
    private String userCreation;
    private LocalDateTime dateCreation;
    private Boolean expireCompte;
    private Boolean expirePassword;
    private Integer nbJourExpiration;
    private Integer nbExpirationPassword;
    private LocalDate dateExpiration;
    private List<GroupUserDTO> groupUsers;
    private List<AccessModuleUserDTO> accessModuleUsers;
    private List<AccessMenuUserDTO> accessMenuUsers;
    private List<AccessButtonUserDTO> accessButtonUsers;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Boolean getExpireCompte() {
        return expireCompte;
    }

    public void setExpireCompte(Boolean expireCompte) {
        this.expireCompte = expireCompte;
    }

    public Boolean getExpirePassword() {
        return expirePassword;
    }

    public void setExpirePassword(Boolean expirePassword) {
        this.expirePassword = expirePassword;
    }

    public Integer getNbJourExpiration() {
        return nbJourExpiration;
    }

    public void setNbJourExpiration(Integer nbJourExpiration) {
        this.nbJourExpiration = nbJourExpiration;
    }

    public Integer getNbExpirationPassword() {
        return nbExpirationPassword;
    }

    public void setNbExpirationPassword(Integer nbExpirationPassword) {
        this.nbExpirationPassword = nbExpirationPassword;
    }

    public LocalDate getDateExpiration() {
        return dateExpiration;
    }

    public void setDateExpiration(LocalDate dateExpiration) {
        this.dateExpiration = dateExpiration;
    }

    public List<AccessModuleUserDTO> getAccessModuleUsers() {
        return accessModuleUsers;
    }

    public void setAccessModuleUsers(List<AccessModuleUserDTO> accessModuleUsers) {
        this.accessModuleUsers = accessModuleUsers;
    }

    public List<AccessMenuUserDTO> getAccessMenuUsers() {
        return accessMenuUsers;
    }

    public void setAccessMenuUsers(List<AccessMenuUserDTO> accessMenuUsers) {
        this.accessMenuUsers = accessMenuUsers;
    }

    public List<AccessButtonUserDTO> getAccessButtonUsers() {
        return accessButtonUsers;
    }

    public void setAccessButtonUsers(List<AccessButtonUserDTO> accessButtonUsers) {
        this.accessButtonUsers = accessButtonUsers;
    }

    public List<GroupUserDTO> getGroupUsers() {
        return groupUsers;
    }

    public void setGroupUsers(List<GroupUserDTO> groupUsers) {
        this.groupUsers = groupUsers;
    }

}
