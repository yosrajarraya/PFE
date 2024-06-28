package com.csys.template.factory;

import com.csys.template.domain.AccessButtonUser;
import com.csys.template.dto.AccessButtonUserDTO;
import java.util.ArrayList;
import java.util.List;

public class AccessButtonUserFactory {

    public static AccessButtonUserDTO accessbuttonuserToAccessButtonUserDTO(AccessButtonUser accessbuttonuser) {
        AccessButtonUserDTO accessbuttonuserDTO = new AccessButtonUserDTO();
        accessbuttonuserDTO.setIdAccessButtonUser(accessbuttonuser.getIdAccessButtonUser());
        accessbuttonuserDTO.setNumDelegate(accessbuttonuser.getNumDelegate());
        accessbuttonuserDTO.setVisible(accessbuttonuser.isVisible());
        accessbuttonuserDTO.setIdButton(accessbuttonuser.getIdButton());
        accessbuttonuserDTO.setIdUser(accessbuttonuser.getUserId());
        return accessbuttonuserDTO;
    }

    public static AccessButtonUser accessbuttonuserDTOToAccessButtonUser(AccessButtonUserDTO accessbuttonuserDTO) {

        AccessButtonUser accessbuttonuser = new AccessButtonUser();
        accessbuttonuser.setIdAccessButtonUser(accessbuttonuserDTO.getIdAccessButtonUser());
    accessbuttonuser.setNumDelegate(accessbuttonuserDTO.getNumDelegate());
        accessbuttonuser.setVisible(accessbuttonuserDTO.isVisible());
        accessbuttonuser.setIdButton(accessbuttonuserDTO.getIdButton());
        accessbuttonuser.setUserId(accessbuttonuserDTO.getIdUser());
        return accessbuttonuser;
    }

    public static List<AccessButtonUserDTO> accessbuttonuserToAccessButtonUserDTOs(List<AccessButtonUser> accessbuttonusers) {
        List<AccessButtonUserDTO> accessbuttonusersDTO = new ArrayList<>();
        accessbuttonusers.forEach(x -> {
            accessbuttonusersDTO.add(accessbuttonuserToAccessButtonUserDTO(x));
        });
        return accessbuttonusersDTO;
    }
}
