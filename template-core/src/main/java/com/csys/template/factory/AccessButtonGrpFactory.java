package com.csys.template.factory;

import com.csys.template.domain.AccessButtonGrp;
import com.csys.template.dto.AccessButtonGrpDTO;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AccessButtonGrpFactory {

    public static AccessButtonGrpDTO accessbuttongrpToAccessButtonGrpDTO(AccessButtonGrp accessbuttongrp) {
        AccessButtonGrpDTO accessbuttongrpDTO = new AccessButtonGrpDTO();
        accessbuttongrpDTO.setId(accessbuttongrp.getIdAccessButtonGroup());
        accessbuttongrpDTO.setIdButton(accessbuttongrp.getButtonId());
        accessbuttongrpDTO.setIdGroup(accessbuttongrp.getGroupeId());

        return accessbuttongrpDTO;
    }

    public static AccessButtonGrp accessbuttongrpDTOToAccessButtonGrp(AccessButtonGrpDTO accessbuttongrpDTO) {

        AccessButtonGrp accessbuttongrp = new AccessButtonGrp();
        accessbuttongrp.setIdAccessButtonGroup(accessbuttongrpDTO.getId());
        accessbuttongrp.setButtonId(accessbuttongrpDTO.getIdButton());
        accessbuttongrp.setGroupeId(accessbuttongrpDTO.getIdGroup());

        return accessbuttongrp;
    }

    public static List<AccessButtonGrpDTO> accessbuttongrpToAccessButtonGrpDTOs(List<AccessButtonGrp> accessbuttongrps) {
        List<AccessButtonGrpDTO> accessbuttongrpsDTO = new ArrayList<>();
        accessbuttongrps.forEach(x -> {
            accessbuttongrpsDTO.add(accessbuttongrpToAccessButtonGrpDTO(x));
        });
        return accessbuttongrpsDTO;
    }
}
