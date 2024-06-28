package com.csys.template.factory;

import com.csys.template.domain.DelegationAccess;
import com.csys.template.domain.DetailsDelegationAccess;
import com.csys.template.domain.DetailsDelegationAccessButton;
import com.csys.template.domain.DetailsDelegationAccessMenu;
import com.csys.template.dto.DelegationAccessDTO;
import com.csys.template.dto.DetailsDelegationAccessButtonDTO;
import com.csys.template.dto.DetailsDelegationAccessDTO;
import com.csys.template.dto.DetailsDelegationAccessMenuDTO;
import com.csys.template.enumeration.EnumMotif;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DelegationAccessFactory {

    public static DelegationAccessDTO delegationaccessToDelegationAccessDTO(DelegationAccess delegationaccess, boolean withDetails) {
        DelegationAccessDTO delegationaccessDTO = new DelegationAccessDTO();
        delegationaccessDTO.setNumDelegation(delegationaccess.getNumDelegation());
        delegationaccessDTO.setUserDelegant(delegationaccess.getUserDelegant());
        delegationaccessDTO.setMotif(delegationaccess.getMotif());
        delegationaccessDTO.setMotifDTO(EnumMotif.MOTIF.get(delegationaccess.getMotif()));
        delegationaccessDTO.setUserCreation(delegationaccess.getUserCreation());
        delegationaccessDTO.setUtilisateurDTO(UtilisateurFactory.utilisateurToUtilisateurDTO(delegationaccess.getUserName()));
        delegationaccessDTO.setIdModule(ModuleFactory.moduleToModuleDTO(delegationaccess.getIdModule()));
//        delegationaccessDTO.setModule(delegationaccess.getModule());
        delegationaccessDTO.setDateDebut(delegationaccess.getDateDebut());
        delegationaccessDTO.setDateFin(delegationaccess.getDateFin());
        delegationaccessDTO.setDateCreation(delegationaccess.getDateCreation());
        delegationaccessDTO.setDeclenche(delegationaccess.getDeclenche());
        delegationaccessDTO.setTermine(delegationaccess.getTermine());

        if (withDetails) {
            if (delegationaccess.getDetailsdelegationAccess() != null) {
                delegationaccessDTO.setDetailsdelegationAccess(DetailsDelegationAccessFactory.detailsdelegationaccessToDetailsDelegationAccessDTOs(delegationaccess.getDetailsdelegationAccess()));
            }
            if (delegationaccess.getDetailsdelegationAccessButton() != null) {
                delegationaccessDTO.setDetailsdelegationAccessButton(DetailsDelegationAccessButtonFactory.detailsdelegationaccessbuttonToDetailsDelegationAccessButtonDTOs(delegationaccess.getDetailsdelegationAccessButton()));
            }
            if (delegationaccess.getDetailsdelegationAccessMenu() != null) {
                delegationaccessDTO.setDetailsdelegationAccessMenu(DetailsDelegationAccessMenuFactory.detailsdelegationaccessmenuToDetailsDelegationAccessMenuDTOs(delegationaccess.getDetailsdelegationAccessMenu()));
            }
        }

        return delegationaccessDTO;
    }

    public static DelegationAccess delegationaccessDTOToDelegationAccess(DelegationAccessDTO delegationaccessDTO, DelegationAccess delegationaccess, String user) {
        if (delegationaccess == null) {
            delegationaccess = new DelegationAccess();
            delegationaccess.setNumDelegation(delegationaccessDTO.getNumDelegation());
            delegationaccess.setDateCreation(LocalDateTime.now());
            delegationaccess.setUserCreation(user);
        }
        delegationaccess.setUserDelegant(delegationaccessDTO.getUserDelegant());
        delegationaccess.setMotif(delegationaccessDTO.getMotif());
//        delegationaccess.setModule(delegationaccessDTO.getModule());
        delegationaccess.setDateDebut(delegationaccessDTO.getDateDebut());
        delegationaccess.setDateFin(delegationaccessDTO.getDateFin());

        if (delegationaccessDTO.getDateDebut().isEqual(LocalDate.now())) {
            delegationaccess.setDeclenche(true);
        }
        if (delegationaccessDTO.getDateFin().isEqual(LocalDate.now())) {
            delegationaccess.setTermine(true);
        }
        if (delegationaccessDTO.getTermine()) {
            delegationaccess.setDeclenche(false);
        }

        List<DetailsDelegationAccess> detailsDelegationAcces = new ArrayList<>();
        if (delegationaccessDTO.getDetailsdelegationAccess() != null) {
            for (DetailsDelegationAccessDTO x : delegationaccessDTO.getDetailsdelegationAccess()) {

                DetailsDelegationAccess detailsDelegationAccess = DetailsDelegationAccessFactory.detailsdelegationaccessDTOToDetailsDelegationAccess(x);
                detailsDelegationAccess.setDelegationAccess(delegationaccess);
                detailsDelegationAcces.add(detailsDelegationAccess);
            }
        }
        if (delegationaccess.getDetailsdelegationAccess() != null) {
            delegationaccess.getDetailsdelegationAccess().clear();
            delegationaccess.getDetailsdelegationAccess().addAll(detailsDelegationAcces);
        } else {
            delegationaccess.setDetailsdelegationAccess(detailsDelegationAcces);
        }
        List<DetailsDelegationAccessMenu> detailsDelegationAccessMenu = new ArrayList<>();
        if (delegationaccessDTO.getDetailsdelegationAccessMenu() != null) {
            for (DetailsDelegationAccessMenuDTO x : delegationaccessDTO.getDetailsdelegationAccessMenu()) {

                DetailsDelegationAccessMenu detailsDelegationAccessMenus = DetailsDelegationAccessMenuFactory.detailsdelegationaccessmenuDTOToDetailsDelegationAccessMenu(x, null);
                detailsDelegationAccessMenus.setDelegationAccess(delegationaccess);
                detailsDelegationAccessMenu.add(detailsDelegationAccessMenus);
            }
        }
        if (delegationaccess.getDetailsdelegationAccessMenu() != null) {
            delegationaccess.getDetailsdelegationAccessMenu().clear();
            delegationaccess.getDetailsdelegationAccessMenu().addAll(detailsDelegationAccessMenu);
        } else {
            delegationaccess.setDetailsdelegationAccessMenu(detailsDelegationAccessMenu);
        }
        List<DetailsDelegationAccessButton> detailsDelegationAccessButton = new ArrayList<>();
        if (delegationaccessDTO.getDetailsdelegationAccessButton() != null) {
            for (DetailsDelegationAccessButtonDTO x : delegationaccessDTO.getDetailsdelegationAccessButton()) {

                DetailsDelegationAccessButton detailsDelegationAccessButtons = DetailsDelegationAccessButtonFactory.detailsdelegationaccessbuttonDTOToDetailsDelegationAccessButton(x, null);
                detailsDelegationAccessButtons.setNumDelegations(delegationaccess);
                detailsDelegationAccessButton.add(detailsDelegationAccessButtons);

            }
        }
        if (delegationaccess.getDetailsdelegationAccessButton() != null) {
            delegationaccess.getDetailsdelegationAccessButton().clear();
            delegationaccess.getDetailsdelegationAccessButton().addAll(detailsDelegationAccessButton);
        } else {
            delegationaccess.setDetailsdelegationAccessButton(detailsDelegationAccessButton);
        }

        return delegationaccess;
    }

    public static List<DelegationAccessDTO> delegationaccessToDelegationAccessDTOs(List<DelegationAccess> delegationaccesss, boolean withdetails) {
        List<DelegationAccessDTO> delegationaccesssDTO = new ArrayList<>();
        delegationaccesss.forEach(x -> {
            delegationaccesssDTO.add(delegationaccessToDelegationAccessDTO(x, withdetails));
        });
        return delegationaccesssDTO;
    }
}
