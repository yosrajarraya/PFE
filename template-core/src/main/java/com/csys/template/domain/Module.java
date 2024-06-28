package com.csys.template.domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "module")

public class Module implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "code_module")
    private String codeModule;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "designation")
    private String designation;
    @Basic(optional = false)
    @NotNull
    @Column(name = "active")
    private boolean active;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "version_database")
    private String versionDatabase;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "urlWeb")
    private String urlWeb;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "module")
    private List<DetailsDelegationAccessButton> detailsDelegationAccessButtonList;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_module")
    private Integer idModule;
    
 

   @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true,mappedBy = "module")
    private List<DelegationAccess> delegationAccessList;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "module")
    private List<AccessModuleGrp> accessModuleGrps;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "module")
    private List<AccessModuleUser> accessModuleUsers;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "module")
    private List<Menu> menus;
//    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "modules")
//    private List<DelegationAccess> delegationAccess;

    public Module() {
    }

    public Module(Integer idModule) {
        this.idModule = idModule;
    }

    public Module(Integer idModule, String codeModule, String designation, boolean active, String versionDatabase, String urlWeb) {
        this.idModule = idModule;
        this.codeModule = codeModule;
        this.designation = designation;
        this.active = active;
        this.versionDatabase = versionDatabase;
        this.urlWeb = urlWeb;
    }

    public String getCodeModule() {
        return codeModule;
    }

    public void setCodeModule(String codeModule) {
        this.codeModule = codeModule;
    }

    public String getVersionDatabase() {
        return versionDatabase;
    }

    public void setVersionDatabase(String versionDatabase) {
        this.versionDatabase = versionDatabase;
    }

    public Integer getIdModule() {
        return idModule;
    }

    public void setIdModule(Integer idModule) {
        this.idModule = idModule;
    }

    public List<AccessModuleGrp> getAccessModuleGrps() {
        return accessModuleGrps;
    }

    public void setAccessModuleGrps(List<AccessModuleGrp> accessModuleGrps) {
        this.accessModuleGrps = accessModuleGrps;
    }

    public List<AccessModuleUser> getAccessModuleUsers() {
        return accessModuleUsers;
    }

    public void setAccessModuleUsers(List<AccessModuleUser> accessModuleUsers) {
        this.accessModuleUsers = accessModuleUsers;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idModule != null ? idModule.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Module)) {
            return false;
        }
        Module other = (Module) object;
        if ((this.idModule == null && other.idModule != null) || (this.idModule != null && !this.idModule.equals(other.idModule))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.csys.template.domain.Module[ idModule=" + idModule + " ]";
    }




    public List<DelegationAccess> getDelegationAccessList() {
        return delegationAccessList;
    }

    public void setDelegationAccessList(List<DelegationAccess> delegationAccessList) {
        this.delegationAccessList = delegationAccessList;
    }


    public List<DetailsDelegationAccessButton> getDetailsDelegationAccessButtonList() {
        return detailsDelegationAccessButtonList;
    }

    public void setDetailsDelegationAccessButtonList(List<DetailsDelegationAccessButton> detailsDelegationAccessButtonList) {
        this.detailsDelegationAccessButtonList = detailsDelegationAccessButtonList;
    }

  

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }



    public String getUrlWeb() {
        return urlWeb;
    }

    public void setUrlWeb(String urlWeb) {
        this.urlWeb = urlWeb;
    }

}
