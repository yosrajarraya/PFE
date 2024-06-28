package com.csys.template.factory;

import com.csys.template.domain.DetailsDelegationAccess;
import com.csys.template.dto.DetailsDelegationAccessDTO;
import java.util.ArrayList;
import java.util.List;

public class DetailsDelegationAccessFactory {

    public static DetailsDelegationAccessDTO detailsdelegationaccessToDetailsDelegationAccessDTO(DetailsDelegationAccess detailsdelegationaccess) {
        if (detailsdelegationaccess == null) {
            return null;
        }

        DetailsDelegationAccessDTO dto = new DetailsDelegationAccessDTO();
        dto.setNumDelegation(detailsdelegationaccess.getNumDelegation());
        dto.setUserDelegataire(detailsdelegationaccess.getUserDelegataire());
        dto.setUserDelegant(detailsdelegationaccess.getUserDelegant());

        return dto;
    }

    public static DetailsDelegationAccess detailsdelegationaccessDTOToDetailsDelegationAccess(DetailsDelegationAccessDTO detailsdelegationaccessDTO) {
         if (detailsdelegationaccessDTO == null) {
            return null;
        }

        DetailsDelegationAccess detailsDelegationAccess = new DetailsDelegationAccess();
        detailsDelegationAccess.setUserDelegataire(detailsdelegationaccessDTO.getUserDelegataire());
        detailsDelegationAccess.setUserDelegant(detailsdelegationaccessDTO.getUserDelegant());
        
        return detailsDelegationAccess;
    
    }

    public static List<DetailsDelegationAccessDTO> detailsdelegationaccessToDetailsDelegationAccessDTOs(List<DetailsDelegationAccess> detailsdelegationaccesss) {
        List<DetailsDelegationAccessDTO> detailsdelegationaccesssDTO = new ArrayList<>();
        detailsdelegationaccesss.forEach(x -> {
            detailsdelegationaccesssDTO.add(detailsdelegationaccessToDetailsDelegationAccessDTO(x));
        });
        return detailsdelegationaccesssDTO;
    }
}
