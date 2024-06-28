package com.csys.template.service;

import com.csys.template.domain.DelegationAccess;
import com.csys.template.domain.QDelegationAccess;
import com.csys.template.dto.DelegationAccessDTO;
import com.csys.template.factory.DelegationAccessFactory;
import com.csys.template.repository.DelegationAccessRepository;
import com.csys.template.util.Helper;
import com.csys.template.util.WhereClauseBuilder;
import com.csys.template.util.Preconditions;
import java.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class DelegationAccessService {

    private final Logger log = LoggerFactory.getLogger(DelegationAccessService.class);

    private final DelegationAccessRepository delegationAccessRepository;
    private final AccessMenuUserService accessMenuUserService;
    private final AccessButtonUserService accessButtonUserService;

    public DelegationAccessService(DelegationAccessRepository delegationAccessRepository, AccessMenuUserService accessMenuUserService, AccessButtonUserService accessButtonUserService) {
        this.delegationAccessRepository = delegationAccessRepository;
        this.accessMenuUserService = accessMenuUserService;
        this.accessButtonUserService = accessButtonUserService;
    }

    public String save(DelegationAccessDTO delegationAccessDTO) {
        LocalDate today = LocalDate.now();
        log.debug("Request to save DelegationAccess: {}", delegationAccessDTO);
        Preconditions.checkBusinessLogique(delegationAccessDTO.getDateDebut().isBefore(delegationAccessDTO.getDateFin()), "La date de début doit être avant la date de fin.");
        Preconditions.checkBusinessLogique(!(delegationAccessDTO.getDeclenche() && delegationAccessDTO.getTermine()), "Une délégation ne peut pas être à la fois déclenchée et terminée.");
        Preconditions.checkBusinessLogique(!delegationAccessDTO.getTermine() || delegationAccessDTO.getDeclenche(), "Une délégation ne peut pas être terminée si elle n'est pas déclenchée.");
        Preconditions.checkBusinessLogique(!delegationAccessDTO.getDateDebut().isBefore(today), "La date de début ne peut pas être dans le passé.");
        Preconditions.checkBusinessLogique(!delegationAccessDTO.getDateFin().isBefore(today), "La date de fin ne peut pas être dans le passé.");
//        Preconditions.checkBusinessLogique(hasNoOverlappingDelegations(delegationAccessDTO), "Il y a des délégations qui se chevauchent pour cet utilisateur et ce module.");

        log.debug("DelegationAccessFactory: {}", delegationAccessDTO);
        DelegationAccess delegationAccess = DelegationAccessFactory.delegationaccessDTOToDelegationAccess(delegationAccessDTO, null, Helper.getUserAuthenticated());
       
        log.debug("delegationAccess before save: {}", delegationAccess);
        delegationAccessRepository.save(delegationAccess);
        log.debug("delegationAccess: {}", delegationAccess);
//        if (delegationAccessDTO.getDetailsdelegationAccessButton() != null) {
//            delegationAccessDTO.getDetailsdelegationAccessButton().forEach(detailsDTO -> {
//                Integer idAccessButtonUser = detailsDTO.getIdDelegationButton();
//                Integer numDelegation = detailsDTO.getNumDelegation();
//                accessButtonUserService.updateAccessButtonUser(idAccessButtonUser, numDelegation);
//            });
//        }
//        if (delegationAccessDTO.getDetailsdelegationAccessMenu() != null) {
//            delegationAccessDTO.getDetailsdelegationAccessMenu().forEach(detailsDTO -> {
//                Integer idAccessButtonUser = detailsDTO.getId_delegation_menu();
//                Integer numDelegation = detailsDTO.getId_delegation_menu();
//                accessMenuUserService.updateAccessMenuUser(idAccessButtonUser, numDelegation);;
//            });
//        }

        return "True";
    }

//    private boolean hasNoOverlappingDelegations(DelegationAccessDTO delegationAccessDTO) {
//        List<DelegationAccess> overlappingDelegations = delegationAccessRepository.findOverlappingDelegations(
//                delegationAccessDTO.getUserDelegant(),
////                delegationAccessDTO.getModule(),
//                delegationAccessDTO.getDateDebut(),
//                delegationAccessDTO.getDateFin()
//        );
//        return overlappingDelegations.isEmpty();
//    }

    public String update(DelegationAccessDTO delegationAccessDTO) {
        log.debug("Request to update DelegationAccess: {}", delegationAccessDTO);
        DelegationAccess inBase = findOne(delegationAccessDTO.getNumDelegation());
        Preconditions.checkBusinessLogique(inBase != null, "delegationAccess.NotFound");
        DelegationAccess delegationAccess = DelegationAccessFactory.delegationaccessDTOToDelegationAccess(delegationAccessDTO, inBase,  Helper.getUserAuthenticated());
        delegationAccessRepository.save(delegationAccess);

        return "True";
    }

    @Transactional(readOnly = true)
    public DelegationAccess findOne(Integer id) {
        log.debug("Request to get DelegationAccess: {}", id);
        return delegationAccessRepository.findById(id).orElse(null);
    }

    @Transactional(readOnly = true)
    public DelegationAccessDTO findDelegationAccess(Integer id) {
        log.debug("Request to get DelegationAccess: {}", id);
        DelegationAccess delegationAccess = findOne(id);
        return DelegationAccessFactory.delegationaccessToDelegationAccessDTO(delegationAccess, true);
    }

    @Transactional(readOnly = true)
    public List<DelegationAccessDTO> findAll(Boolean[] Termine) {
        log.debug("Request to get all DelegationAccesses");
        List<DelegationAccess> result = findAllByTermine(Termine);
        return DelegationAccessFactory.delegationaccessToDelegationAccessDTOs(result, false);
    }

    @Transactional(readOnly = true)
    public List<DelegationAccess> findAllByTermine(Boolean[] Termine) {
        log.debug("Request to get all DelegationAccesses by actifs");
        QDelegationAccess qDelegationAccess = QDelegationAccess.delegationAccess;
        WhereClauseBuilder builder = new WhereClauseBuilder()
                .optionalAnd(Termine, () -> qDelegationAccess.termine.in(Termine));
        return (List<DelegationAccess>) delegationAccessRepository.findAll(builder);
    }

    public String delete(Integer id) {
        log.debug("Request to delete DelegationAccess: {}", id);
        DelegationAccess delegationAccess = findOne(id);
        Preconditions.checkBusinessLogique(delegationAccess != null, "Le delegationAccess est inexistant");
        if (delegationAccess.getTermine()) {
            if (delegationAccess.getDetailsdelegationAccessMenu() != null) {
                delegationAccess.getDetailsdelegationAccessMenu().forEach(detailsDTO -> {
                    Integer idAccessButtonUser = detailsDTO.getIdDelegationMenu();
                    Integer numDelegation = detailsDTO.getIdDelegationMenu();
                    accessMenuUserService.deleteAccessMenuUser(idAccessButtonUser, numDelegation);;
                });
            }
        }
        if (delegationAccess.getTermine()) {
            if (delegationAccess.getDetailsdelegationAccessButton() != null) {
                delegationAccess.getDetailsdelegationAccessButton().forEach(detailsDTO -> {
                    Integer idAccessButtonUser = detailsDTO.getIdDelegationButton();
                    Integer numDelegation = detailsDTO.getNumDelegation();
                    accessButtonUserService.deleteAccessButtonUser(idAccessButtonUser, numDelegation);
                });

            }
        }
        delegationAccessRepository.delete(delegationAccess);
        return "True";
    }

    @Transactional(readOnly = true)
    public Boolean findOverlappingDelegations(String userDelegant, int module, LocalDate dateDebut, LocalDate dateFin) {
        log.debug("Request to check if DelegationAccess exists for groupe: {}", userDelegant, module, dateDebut, dateFin);
        QDelegationAccess qDelegationAccess = QDelegationAccess.delegationAccess;
        WhereClauseBuilder builder = new WhereClauseBuilder()
                .and(qDelegationAccess.userDelegant.eq(userDelegant))
                .and(qDelegationAccess.module.eq(module))
                .and(qDelegationAccess.dateDebut.between(dateDebut, dateFin));
        return delegationAccessRepository.exists(builder);
    }

}
