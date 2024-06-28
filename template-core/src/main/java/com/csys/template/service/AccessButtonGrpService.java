package com.csys.template.service;

import com.csys.template.domain.AccessButtonGrp;
import com.csys.template.domain.QAccessButtonGrp;
import com.csys.template.dto.AccessButtonGrpDTO;
import com.csys.template.factory.AccessButtonGrpFactory;
import com.csys.template.repository.AccessButtonGrpRepository;
import com.csys.template.util.Preconditions;
import com.csys.template.util.WhereClauseBuilder;
import java.lang.Integer;
import java.util.Collection;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AccessButtonGrpService {

    private final Logger log = LoggerFactory.getLogger(AccessButtonGrpService.class);

    private final AccessButtonGrpRepository accessbuttongrpRepository;

    public AccessButtonGrpService(AccessButtonGrpRepository accessbuttongrpRepository) {
        this.accessbuttongrpRepository = accessbuttongrpRepository;
    }

    public String save(AccessButtonGrpDTO accessbuttongrpDTO) {
        log.debug("Request to save AccessButtonGrp: {}", accessbuttongrpDTO);
        AccessButtonGrp accessbuttongrp = AccessButtonGrpFactory.accessbuttongrpDTOToAccessButtonGrp(accessbuttongrpDTO);
        accessbuttongrp = accessbuttongrpRepository.save(accessbuttongrp);
        return "True";
    }

    public String update(AccessButtonGrpDTO accessbuttongrpDTO) {
        log.debug("Request to update AccessButtonGrp: {}", accessbuttongrpDTO);
        AccessButtonGrp inBase = findOne(accessbuttongrpDTO.getId());
        Preconditions.checkBusinessLogique(inBase != null, "accessbuttongrp.NotFound");
        AccessButtonGrp accessbuttongrp = AccessButtonGrpFactory.accessbuttongrpDTOToAccessButtonGrp(accessbuttongrpDTO);
        accessbuttongrpRepository.save(accessbuttongrp);

        return "True";
    }

    @Transactional(
            readOnly = true
    )
    public AccessButtonGrp findOne(Integer id_AccessButtonGrp) {
        log.debug("Request to get AccessButtonGrp: {}", id_AccessButtonGrp);
        AccessButtonGrp accessbuttongrp = accessbuttongrpRepository.findById(id_AccessButtonGrp).orElse(null);
        return accessbuttongrp;
    }

    @Transactional(
            readOnly = true
    )
    public AccessButtonGrpDTO findAccessButtonGrp(Integer id_AccessButtonGrp) {
        log.debug("Request to get AccessButtonGrp: {}", id_AccessButtonGrp);
        AccessButtonGrp accessbuttongrp = findOne(id_AccessButtonGrp);
        AccessButtonGrpDTO dto = AccessButtonGrpFactory.accessbuttongrpToAccessButtonGrpDTO(accessbuttongrp);
        return dto;
    }

    @Transactional(
            readOnly = true
    )
    public List<AccessButtonGrpDTO> findAll() {
        log.debug("Request to get All AccessButtonGrps");
        List<AccessButtonGrp> result = accessbuttongrpRepository.findAll();
        return AccessButtonGrpFactory.accessbuttongrpToAccessButtonGrpDTOs(result);
    }

//    public List<AccessButtonGrp> findAllByActifs(Boolean[] actifs, Boolean[] visible) {
//        QAccessButtonGrp qAccessButtonGrp = QAccessButtonGrp.accessButtonGrp;
//        WhereClauseBuilder builder = new WhereClauseBuilder()
//                .optionalAnd(actifs, () -> qAccessButtonGrp.active.in(actifs))
//                .optionalAnd(visible, () -> qAccessButtonGrp.visible.in(visible));
//        List<AccessButtonGrp> result = (List<AccessButtonGrp>) accessbuttongrpRepository.findAll(builder);
//        return result;
//    }

    public String delete(Integer id_AccessButtonGrp) {
        log.debug("Request to delete AccessButtonGrp: {}", id_AccessButtonGrp);
        AccessButtonGrp accessbuttongrp = findOne(id_AccessButtonGrp);
        accessbuttongrpRepository.delete(accessbuttongrp);
        return "True";
    }

//    @Transactional(
//            readOnly = true
//    )
//    public Boolean exists(String code) {
//        QAccessButtonGrp qAccessButtonGrp = QAccessButtonGrp.accessButtonGrp;
//        WhereClauseBuilder builder = new WhereClauseBuilder()
//                .optionalAnd(code, () -> qAccessButtonGrp.code.eq(code));
//        Boolean result = accessbuttongrpRepository.exists(builder);
//        return result;
//    }
}
