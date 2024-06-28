package com.csys.template.factory;

import com.csys.template.domain.AccessModuleGrp;
import com.csys.template.dto.AccessModuleGrpDTO;
import java.util.ArrayList;
import java.util.List;

public class AccessModuleGrpFactory {

    public static AccessModuleGrpDTO accessmodulegrpToAccessModuleGrpDTO(AccessModuleGrp accessmodulegrp) {
        AccessModuleGrpDTO accessmodulegrpDTO = new AccessModuleGrpDTO();
        accessmodulegrpDTO.setId(accessmodulegrp.getIdAccessModuleGroup());
        accessmodulegrpDTO.setIdGroupUser(accessmodulegrp.getIdGroupUser());
        accessmodulegrpDTO.setIdModule(accessmodulegrp.getIdModule());
        return accessmodulegrpDTO;
    }

    public static AccessModuleGrp accessmodulegrpDTOToAccessModuleGrp(AccessModuleGrpDTO accessmodulegrpDTO) {

        AccessModuleGrp accessmodulegrp = new AccessModuleGrp();
        accessmodulegrp.setIdAccessModuleGroup(accessmodulegrpDTO.getId());
        accessmodulegrp.setIdGroupUser(accessmodulegrpDTO.getIdGroupUser());
        accessmodulegrp.setIdModule(accessmodulegrpDTO.getIdModule());
        return accessmodulegrp;
    }

    public static List<AccessModuleGrpDTO> accessmodulegrpToAccessModuleGrpDTOs(List<AccessModuleGrp> accessmodulegrps) {
        List<AccessModuleGrpDTO> accessmodulegrpsDTO = new ArrayList<>();
        accessmodulegrps.forEach(x -> {
            accessmodulegrpsDTO.add(accessmodulegrpToAccessModuleGrpDTO(x));
        });
        return accessmodulegrpsDTO;
    }
}
