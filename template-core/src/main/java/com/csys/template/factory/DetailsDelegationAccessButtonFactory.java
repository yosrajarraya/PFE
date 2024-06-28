package com.csys.template.factory;

import com.csys.template.domain.DetailsDelegationAccessButton;
import com.csys.template.dto.DetailsDelegationAccessButtonDTO;
import java.util.ArrayList;
import java.util.List;

public class DetailsDelegationAccessButtonFactory {

    public static DetailsDelegationAccessButtonDTO detailsdelegationaccessbuttonToDetailsDelegationAccessButtonDTO(DetailsDelegationAccessButton detailsdelegationaccessbutton) {
        if (detailsdelegationaccessbutton == null) {
            return null;
        }
        DetailsDelegationAccessButtonDTO detailsdelegationaccessbuttonDTO = new DetailsDelegationAccessButtonDTO();
        detailsdelegationaccessbuttonDTO.setNumDelegation(detailsdelegationaccessbutton.getNumDelegation());
        detailsdelegationaccessbuttonDTO.setModule(detailsdelegationaccessbutton.getModule());
        detailsdelegationaccessbuttonDTO.setButton(detailsdelegationaccessbutton.getButton());
        detailsdelegationaccessbuttonDTO.setIdDelegationButton(detailsdelegationaccessbutton.getIdDelegationButton());
        detailsdelegationaccessbuttonDTO.setUserDelegant(detailsdelegationaccessbutton.getUserDelegant());
        detailsdelegationaccessbuttonDTO.setIdButton(ButtonFactory.buttonToButtonDTO(detailsdelegationaccessbutton.getIdButton()));

        return detailsdelegationaccessbuttonDTO;
    }

    public static DetailsDelegationAccessButton detailsdelegationaccessbuttonDTOToDetailsDelegationAccessButton(DetailsDelegationAccessButtonDTO detailsdelegationaccessbuttonDTO, DetailsDelegationAccessButton detailsdelegationaccessbutton) {
        if (detailsdelegationaccessbutton == null) {
            detailsdelegationaccessbutton = new DetailsDelegationAccessButton();
            detailsdelegationaccessbutton.setIdDelegationButton(detailsdelegationaccessbuttonDTO.getIdDelegationButton());
            detailsdelegationaccessbutton.setNumDelegation(detailsdelegationaccessbuttonDTO.getNumDelegation());
        }

        detailsdelegationaccessbutton.setModule(detailsdelegationaccessbuttonDTO.getModule());
        detailsdelegationaccessbutton.setButton(detailsdelegationaccessbuttonDTO.getButton());
        detailsdelegationaccessbutton.setUserDelegant(detailsdelegationaccessbuttonDTO.getUserDelegant());

        return detailsdelegationaccessbutton;
    }

    public static List<DetailsDelegationAccessButtonDTO> detailsdelegationaccessbuttonToDetailsDelegationAccessButtonDTOs(List<DetailsDelegationAccessButton> detailsdelegationaccessbuttons) {
        List<DetailsDelegationAccessButtonDTO> detailsdelegationaccessbuttonsDTO = new ArrayList<>();
        detailsdelegationaccessbuttons.forEach(x -> {
            detailsdelegationaccessbuttonsDTO.add(detailsdelegationaccessbuttonToDetailsDelegationAccessButtonDTO(x));
        });
        return detailsdelegationaccessbuttonsDTO;
    }
}
