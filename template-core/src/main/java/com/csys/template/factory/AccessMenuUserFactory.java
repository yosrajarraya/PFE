package com.csys.template.factory;

import com.csys.template.domain.AccessMenuUser;
import com.csys.template.dto.AccessMenuUserDTO;
import java.util.ArrayList;
import java.util.List;

public class AccessMenuUserFactory {

    public static AccessMenuUserDTO accessmenuuserToAccessMenuUserDTO(AccessMenuUser accessmenuuser) {
        AccessMenuUserDTO accessmenuuserDTO = new AccessMenuUserDTO();
        accessmenuuserDTO.setIdAccessMenuUser(accessmenuuser.getIdAccessMenuUser());
        accessmenuuserDTO.setIdMenu(accessmenuuser.getIdMenu());
        accessmenuuserDTO.setNumDelegate(accessmenuuser.getNumDelegate());
        accessmenuuserDTO.setVisible(accessmenuuser.isVisible());
        accessmenuuserDTO.setIdUser(accessmenuuser.getUserId());
        return accessmenuuserDTO;
    }

    public static AccessMenuUser accessmenuuserDTOToAccessMenuUser(AccessMenuUserDTO accessmenuuserDTO) {

        AccessMenuUser accessmenuuser = new AccessMenuUser();
        accessmenuuser.setIdAccessMenuUser(accessmenuuserDTO.getIdAccessMenuUser());
        accessmenuuser.setIdMenu(accessmenuuserDTO.getIdMenu());
        accessmenuuser.setUserId(accessmenuuserDTO.getIdUser());
        accessmenuuser.setNumDelegate(accessmenuuserDTO.getNumDelegate());
        accessmenuuser.setVisible(accessmenuuserDTO.isVisible());
        return accessmenuuser;
    }

    public static List<AccessMenuUserDTO> accessmenuuserToAccessMenuUserDTOs(List<AccessMenuUser> accessmenuusers) {
        List<AccessMenuUserDTO> accessmenuusersDTO = new ArrayList<>();
        accessmenuusers.forEach(x -> {
            accessmenuusersDTO.add(accessmenuuserToAccessMenuUserDTO(x));
        });
        return accessmenuusersDTO;
    }
}
