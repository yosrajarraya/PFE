package com.csys.template.dto;

import com.csys.template.domain.Menu;
import io.swagger.annotations.ApiModelProperty;
import java.lang.Integer;
import java.lang.String;
import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class MenuDTO {

    @NotNull
    private Integer idMenu;
    private String codeMenu;
    private Menu codeMenuPrincipal;
    private String designation;
    private String orders;
    private String logo;
    private Integer idModule;
    private List <ButtonDTO>buttonList;
    private List<MenuDTO> menuList;

    public List<MenuDTO> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<MenuDTO> menuList) {
        this.menuList = menuList;
    }
  

    
    
    public Integer getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(Integer idMenu) {
        this.idMenu = idMenu;
    }

    public String getCodeMenu() {
        return codeMenu;
    }

    public void setCodeMenu(String codeMenu) {
        this.codeMenu = codeMenu;
    }


    public Menu getCodeMenuPrincipal() {
        return codeMenuPrincipal;
    }

    public void setCodeMenuPrincipal(Menu codeMenuPrincipal) {
        this.codeMenuPrincipal = codeMenuPrincipal;
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

    public Integer getIdModule() {
        return idModule;
    }

    public void setIdModule(Integer idModule) {
        this.idModule = idModule;
    }
    public List<ButtonDTO> getButtonList() {
        return buttonList;
    }

    public void setButtonList(List<ButtonDTO> buttonList) {
        this.buttonList = buttonList;
    }

    public String getOrders() {
        return orders;
    }

    public void setOrders(String orders) {
        this.orders = orders;
    }
   
    
}
 