package com.csys.template.dto;

import io.swagger.annotations.ApiModelProperty;
import java.lang.Integer;
import java.lang.String;
import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ModuleDTO {

    @NotNull
    private Integer idModule;
    private String codeModule;
    private String designation;
    private boolean active;
    private String versionDatabase;
    private String urlWeb;
    private List<AccessModuleUserDTO> accessModuleUsers;
    private List<AccessModuleGrpDTO> accessModuleGrps;
    private List<MenuDTO> menuList;
    private List<DelegationAccessDTO> delegationAccess;
//  private List accessButtonGrpList;

    public List<MenuDTO> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<MenuDTO> menuList) {
        this.menuList = menuList;
    }

    public List<DelegationAccessDTO> getDelegationAccess() {
        return delegationAccess;
    }

    public void setDelegationAccess(List<DelegationAccessDTO> delegationAccess) {
        this.delegationAccess = delegationAccess;
    }

    public Integer getIdModule() {
        return idModule;
    }

    public void setIdModule(Integer idModule) {
        this.idModule = idModule;
    }

    public String getCodeModule() {
        return codeModule;
    }

    public void setCodeModule(String codeModule) {
        this.codeModule = codeModule;
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

    public String getVersionDatabase() {
        return versionDatabase;
    }

    public void setVersionDatabase(String versionDatabase) {
        this.versionDatabase = versionDatabase;
    }

    public String getUrlWeb() {
        return urlWeb;
    }

    public void setUrlWeb(String urlWeb) {
        this.urlWeb = urlWeb;
    }

    public List<AccessModuleGrpDTO> getAccessModuleGrps() {
        return accessModuleGrps;
    }

    public void setAccessModuleGrps(List<AccessModuleGrpDTO> accessModuleGrps) {
        this.accessModuleGrps = accessModuleGrps;
    }

    public List<AccessModuleUserDTO> getAccessModuleUsers() {
        return accessModuleUsers;
    }

    public void setAccessModuleUsers(List<AccessModuleUserDTO> accessModuleUsers) {
        this.accessModuleUsers = accessModuleUsers;
    }
}
