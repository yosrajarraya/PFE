package com.csys.template.factory;

import com.csys.template.domain.Menu;
import com.csys.template.domain.Module;
import com.csys.template.dto.AccessDTO;
import com.csys.template.dto.MenuDTO;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MenuFactory {

    public static MenuDTO menuToMenuDTO(Menu menu) {
        MenuDTO menuDTO = new MenuDTO();
        menuDTO.setIdMenu(menu.getIdMenu());
        menuDTO.setCodeMenu(menu.getCodeMenu());
        menuDTO.setCodeMenuPrincipal(menu.getCodeMenuPrincipal());
        menuDTO.setDesignation(menu.getDesignation());
        menuDTO.setOrders(menu.getOrders());
        menuDTO.setLogo(menu.getLogo());
        menuDTO.setIdModule(menu.getIdModule());
        if (menu.getButtonList() != null) {
            menuDTO.setButtonList(ButtonFactory.buttonToButtonDTOs(menu.getButtonList()));
        }
        if (menu.getMenuList()!= null) {
            menuDTO.setMenuList(MenuFactory.menuToMenuDTOs(menu.getMenuList()));
        }
        return menuDTO;
    }

    public static Menu menuDTOToMenu(MenuDTO menuDTO, Menu menu) {
        if (menu == null) {
            menu = new Menu();
            menu.setIdMenu(menuDTO.getIdMenu());
            menu.setCodeMenu(menuDTO.getCodeMenu());
        }

        menu.setCodeMenuPrincipal(menuDTO.getCodeMenuPrincipal());
        menu.setDesignation(menuDTO.getDesignation());
        menu.setOrders(menuDTO.getOrders());
        menu.setLogo(menuDTO.getLogo());
        menu.setIdModule(menuDTO.getIdModule());

        return menu;
    }

    public static List<MenuDTO> menuToMenuDTOs(List<Menu> menus) {
        List<MenuDTO> menusDTO = new ArrayList<>();
        menus.forEach(x -> {
            menusDTO.add(menuToMenuDTO(x));
        });
        return menusDTO;
    }
//
//    public static List<AccessDTO> menuToAccessDTOWithoutHirarchys(List<Menu> menus, AccessDTO accessModuleDTO) {
//        List<AccessDTO> accessDTOs = new ArrayList<>();
//        menus.forEach(x -> {
//            AccessDTO accessDTO = new AccessDTO();
//            accessDTO.setId(x.getIdMenu());
////            accessDTO.setCode(x.getCodeMenu());
//            accessDTO.setDesignation(x.getDesignation());
//            accessDTO.setIdParent(accessModuleDTO.getId());
////            accessDTO.setCodeParent(accessModuleDTO.getCode());
//            accessDTO.setType("menu");
//            accessDTOs.add(accessDTO);
//            if (x.getButtonList() != null) {
//                accessDTOs.addAll(ButtonFactory.buttonToAccessDTOWithoutHirarchys(x.getButtonList(),accessDTO));
//            }
//        });
//        return accessDTOs;
//    }

}
