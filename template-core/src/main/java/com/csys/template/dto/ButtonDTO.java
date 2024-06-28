package com.csys.template.dto;

import com.csys.template.domain.Menu;
import java.lang.Integer;
import java.lang.String;
import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ButtonDTO {

    @NotNull
    private Integer idButton;
    private String codeButton;
    private String codeButtonPrincipal;
    private Integer order;
    private String designation;
    private String logo;
    private Integer idMenu;
    private List<AccessButtonUserDTO> AccessButtonUsers;
    private List<AccessButtonGrpDTO> accessButtonGrpList;

    public Integer getIdButton() {
        return idButton;
    }

    public void setIdButton(Integer idButton) {
        this.idButton = idButton;
    }



    public String getCodeButton() {
        return codeButton;
    }

    public void setCodeButton(String codeButton) {
        this.codeButton = codeButton;
    }

    public String getCodeButtonPrincipal() {
        return codeButtonPrincipal;
    }

    public void setCodeButtonPrincipal(String codeButtonPrincipal) {
        this.codeButtonPrincipal = codeButtonPrincipal;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Integer getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(Integer idMenu) {
        this.idMenu = idMenu;
    }

    public List<AccessButtonUserDTO> getAccessButtonUsers() {
        return AccessButtonUsers;
    }

    public void setAccessButtonUsers(List<AccessButtonUserDTO> AccessButtonUsers) {
        this.AccessButtonUsers = AccessButtonUsers;
    }

    public List<AccessButtonGrpDTO> getAccessButtonGrpList() {
        return accessButtonGrpList;
    }

    public void setAccessButtonGrpList(List<AccessButtonGrpDTO> accessButtonGrpList) {
        this.accessButtonGrpList = accessButtonGrpList;
    }

 
}
