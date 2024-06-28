package com.csys.template.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

/**
 *
 * @author Yosra
 */
@Entity
@Table(name = "utilisateur")

@Audited
@AuditTable("utilisateur_AUD")
public class Utilisateur implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "username")
    private String username;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "designation")
    private String designation;
    @Basic(optional = false)
    @NotNull()
    @Size(min = 1, max = 10)
    @Column(name = "password")
    private String password;
    @Basic(optional = false)
    @NotNull
    @Column(name = "active")
    private boolean active;
    @Basic(optional = false)
    @NotNull()
    @Size(min = 1, max = 20)
    @Column(name = "user_creation")
    private String userCreation;
    @Column(name = "date_creation")
    private LocalDateTime dateCreation;
    @Column(name = "date_expiration")
    private LocalDate dateExpiration;

    @Column(name = "expire_compte")
    private Boolean expireCompte;
    @Column(name = "expire_password")
    private Boolean expirePassword;
    @Column(name = "nb_jour_expiration")
    private Integer nbJourExpiration;

    @Column(name = "nb_expiration_password")
    private Integer nbExpirationPassword;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "utilisateurs")
    private List<GroupUser> groupUsers;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "utilisateurs")
    private List<AccessModuleUser> accessModuleUsers;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "utilisateurs")
    private List<AccessMenuUser> accessMenuUsers;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "utilisateurs")
    private List<AccessButtonUser> accessButtonUserList;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "utilisateur")
    private List<DetailsDelegationAccess> detailsDelegationAccessList;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "userName")
    private List<DelegationAccess> delegationAccessList;
    public Utilisateur() {
    }

    public Utilisateur(String username, String designation, String password, boolean active, String userCreation, LocalDateTime dateCreation, LocalDate dateExpiration, List<DelegationAccess> delegationAccessList, List<DetailsDelegationAccessButton> detailsDelegationAccessButtonList, List<DetailsDelegationAccessMenu> detailsDelegationAccessMenuList, Boolean expireCompte, Boolean expirePassword, Integer nbJourExpiration, Integer nbExpirationPassword, List<GroupUser> groupUsers, List<AccessModuleUser> accessModuleUsers, List<AccessMenuUser> accessMenuUsers, List<AccessButtonUser> accessButtonUserList) {
        this.username = username;
        this.designation = designation;
        this.password = password;
        this.active = active;
        this.userCreation = userCreation;
        this.dateCreation = dateCreation;
        this.dateExpiration = dateExpiration;
        this.expireCompte = expireCompte;
        this.expirePassword = expirePassword;
        this.nbJourExpiration = nbJourExpiration;
        this.nbExpirationPassword = nbExpirationPassword;
        this.groupUsers = groupUsers;
        this.accessModuleUsers = accessModuleUsers;
        this.accessMenuUsers = accessMenuUsers;
        this.accessButtonUserList = accessButtonUserList;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserCreation() {
        return userCreation;
    }

    public void setUserCreation(String userCreation) {
        this.userCreation = userCreation;
    }

    public LocalDateTime getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDateTime dateCreation) {
        this.dateCreation = dateCreation;
    }

    public LocalDate getDateExpiration() {
        return dateExpiration;
    }

    public void setDateExpiration(LocalDate dateExpiration) {
        this.dateExpiration = dateExpiration;
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

    public List<GroupUser> getGroupUsers() {
        return groupUsers;
    }

    public void setGroupUsers(List<GroupUser> groupUsers) {
        this.groupUsers = groupUsers;
    }

    public List<AccessModuleUser> getAccessModuleUsers() {
        return accessModuleUsers;
    }

    public void setAccessModuleUsers(List<AccessModuleUser> accessModuleUsers) {
        this.accessModuleUsers = accessModuleUsers;
    }

    public List<AccessMenuUser> getAccessMenuUsers() {
        return accessMenuUsers;
    }

    public void setAccessMenuUsers(List<AccessMenuUser> accessMenuUsers) {
        this.accessMenuUsers = accessMenuUsers;
    }

    public List<AccessButtonUser> getAccessButtonUserList() {
        return accessButtonUserList;
    }

    public void setAccessButtonUserList(List<AccessButtonUser> accessButtonUserList) {
        this.accessButtonUserList = accessButtonUserList;
    }

    public List<DelegationAccess> getDelegationAccessList() {
        return delegationAccessList;
    }

    public void setDelegationAccessList(List<DelegationAccess> delegationAccessList) {
        this.delegationAccessList = delegationAccessList;
    }

    public List<DetailsDelegationAccess> getDetailsDelegationAccessList() {
        return detailsDelegationAccessList;
    }

    public void setDetailsDelegationAccessList(List<DetailsDelegationAccess> detailsDelegationAccessList) {
        this.detailsDelegationAccessList = detailsDelegationAccessList;
    }

   

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

}
