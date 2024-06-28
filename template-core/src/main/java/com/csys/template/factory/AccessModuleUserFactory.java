package com.csys.template.factory;

import com.csys.template.domain.AccessModuleUser;
import com.csys.template.dto.AccessModuleUserDTO;
import java.util.ArrayList;
import java.util.List;

public class AccessModuleUserFactory {

    public static AccessModuleUserDTO accessmoduleuserToAccessModuleUserDTO(AccessModuleUser accessmoduleuser) {
        AccessModuleUserDTO accessmoduleuserDTO = new AccessModuleUserDTO();
        accessmoduleuserDTO.setIdAccessModuleUser(accessmoduleuser.getIdAccessModuleUser());
        accessmoduleuserDTO.setIdModule(accessmoduleuser.getIdModule());
        accessmoduleuserDTO.setUserId(accessmoduleuser.getUserId());
        return accessmoduleuserDTO;
    }

    public static AccessModuleUser accessmoduleuserDTOToAccessModuleUser(AccessModuleUserDTO accessmoduleuserDTO) {

        AccessModuleUser accessmoduleuser = new AccessModuleUser();
        accessmoduleuser.setIdAccessModuleUser(accessmoduleuserDTO.getIdAccessModuleUser());
        accessmoduleuser.setIdModule(accessmoduleuserDTO.getIdModule());
        accessmoduleuser.setUserId(accessmoduleuserDTO.getUserId());
        return accessmoduleuser;
    }

    public static List<AccessModuleUserDTO> accessmoduleuserToAccessModuleUserDTOs(List<AccessModuleUser> accessmoduleusers) {
        List<AccessModuleUserDTO> accessmoduleusersDTO = new ArrayList<>();
        accessmoduleusers.forEach(x -> {
            accessmoduleusersDTO.add(accessmoduleuserToAccessModuleUserDTO(x));
        });
        return accessmoduleusersDTO;
    }
}
