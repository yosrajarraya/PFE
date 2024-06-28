package com.csys.template.factory;

import com.csys.template.domain.DetailsDelegationAccessMenu;
import com.csys.template.dto.DetailsDelegationAccessMenuDTO;
import java.util.ArrayList;
import java.util.List;

public class DetailsDelegationAccessMenuFactory {

    public static DetailsDelegationAccessMenuDTO detailsdelegationaccessmenuToDetailsDelegationAccessMenuDTO(DetailsDelegationAccessMenu detailsdelegationaccessmenu) {
        DetailsDelegationAccessMenuDTO detailsdelegationaccessmenuDTO = new DetailsDelegationAccessMenuDTO();
        detailsdelegationaccessmenuDTO.setNumDelegation(detailsdelegationaccessmenu.getNumDelegation());
        detailsdelegationaccessmenuDTO.setModule(detailsdelegationaccessmenu.getModule());
        detailsdelegationaccessmenuDTO.setIdDelegationMenu(detailsdelegationaccessmenu.getIdDelegationMenu());
        detailsdelegationaccessmenuDTO.setUserDelegant(detailsdelegationaccessmenu.getUserDelegant());
        detailsdelegationaccessmenuDTO.setMenus(detailsdelegationaccessmenu.getMenus());
        detailsdelegationaccessmenuDTO.setMenu(MenuFactory.menuToMenuDTO(detailsdelegationaccessmenu.getMenu()));

        return detailsdelegationaccessmenuDTO;
    }

    public static DetailsDelegationAccessMenu detailsdelegationaccessmenuDTOToDetailsDelegationAccessMenu(DetailsDelegationAccessMenuDTO detailsdelegationaccessmenuDTO, DetailsDelegationAccessMenu detailsdelegationaccessmenu) {
        if (detailsdelegationaccessmenu == null) {
            detailsdelegationaccessmenu = new DetailsDelegationAccessMenu();
            detailsdelegationaccessmenu.setNumDelegation(detailsdelegationaccessmenuDTO.getNumDelegation());
        }

        detailsdelegationaccessmenu.setMenus(detailsdelegationaccessmenuDTO.getMenus());
        detailsdelegationaccessmenu.setUserDelegant(detailsdelegationaccessmenuDTO.getUserDelegant());
        detailsdelegationaccessmenu.setModule(detailsdelegationaccessmenuDTO.getModule());

        return detailsdelegationaccessmenu;
    }

    public static List<DetailsDelegationAccessMenuDTO> detailsdelegationaccessmenuToDetailsDelegationAccessMenuDTOs(List<DetailsDelegationAccessMenu> detailsdelegationaccessmenus) {
        List<DetailsDelegationAccessMenuDTO> detailsdelegationaccessmenusDTO = new ArrayList<>();
        detailsdelegationaccessmenus.forEach(x -> {
            detailsdelegationaccessmenusDTO.add(detailsdelegationaccessmenuToDetailsDelegationAccessMenuDTO(x));
        });
        return detailsdelegationaccessmenusDTO;
    }
}
