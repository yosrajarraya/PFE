package com.csys.template.factory;

import com.csys.template.domain.AccessMenuGrp;
import com.csys.template.dto.AccessMenuGrpDTO;
import java.util.ArrayList;
import java.util.List;

public class AccessMenuGrpFactory {

    public static AccessMenuGrpDTO accessmenugrpToAccessMenuGrpDTO(AccessMenuGrp accessmenugrp) {
        AccessMenuGrpDTO accessmenugrpDTO = new AccessMenuGrpDTO();
        accessmenugrpDTO.setId(accessmenugrp.getIdAccessMenuGroup());
        accessmenugrpDTO.setIdGroupUser(accessmenugrp.getGroupeId());
        accessmenugrpDTO.setIdMenu(accessmenugrp.getMenuId());
        return accessmenugrpDTO;
    }

    public static AccessMenuGrp accessmenugrpDTOToAccessMenuGrp(AccessMenuGrpDTO accessmenugrpDTO) {

        AccessMenuGrp accessmenugrp = new AccessMenuGrp();
        accessmenugrp.setIdAccessMenuGroup(accessmenugrpDTO.getId());
        accessmenugrp.setGroupeId(accessmenugrpDTO.getIdGroupUser());
        accessmenugrp.setMenuId(accessmenugrpDTO.getIdMenu());
        return accessmenugrp;
    }

    public static List<AccessMenuGrpDTO> accessmenugrpToAccessMenuGrpDTOs(List<AccessMenuGrp> accessmenugrps) {
        List<AccessMenuGrpDTO> accessmenugrpsDTO = new ArrayList<>();
        accessmenugrps.forEach(x -> {
            accessmenugrpsDTO.add(accessmenugrpToAccessMenuGrpDTO(x));
        });
        return accessmenugrpsDTO;
    }
}
