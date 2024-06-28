package com.csys.template.service;

import com.csys.template.domain.AccessMenuGrp;
import com.csys.template.domain.QAccessMenuGrp;
import com.csys.template.dto.AccessMenuGrpDTO;
import com.csys.template.factory.AccessMenuGrpFactory;
import com.csys.template.repository.AccessMenuGrpRepository;
import com.csys.template.util.Preconditions;
import com.csys.template.util.WhereClauseBuilder;
import java.lang.Integer;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AccessMenuGrpService {

    private final Logger log = LoggerFactory.getLogger(AccessMenuGrpService.class);

    private final AccessMenuGrpRepository accessmenugrpRepository;

    public AccessMenuGrpService(AccessMenuGrpRepository accessmenugrpRepository) {
        this.accessmenugrpRepository = accessmenugrpRepository;
    }

    public String save(AccessMenuGrpDTO accessmenugrpDTO) {
        log.debug("Request to save AccessMenuGrp: {}", accessmenugrpDTO);
        AccessMenuGrp accessmenugrp = AccessMenuGrpFactory.accessmenugrpDTOToAccessMenuGrp(accessmenugrpDTO);
        accessmenugrpRepository.save(accessmenugrp);
        return "True";
    }

    public String update(AccessMenuGrpDTO accessmenugrpDTO) {
        log.debug("Request to update AccessMenuGrp: {}", accessmenugrpDTO);
        AccessMenuGrp inBase = findOne(accessmenugrpDTO.getId());
        Preconditions.checkBusinessLogique(inBase != null, "accessmenugrp.NotFound");
        AccessMenuGrp accessmenugrp = AccessMenuGrpFactory.accessmenugrpDTOToAccessMenuGrp(accessmenugrpDTO);
        accessmenugrpRepository.save(accessmenugrp);
        return "True";
    }

    @Transactional(
            readOnly = true
    )
    public AccessMenuGrp findOne(Integer id) {
        log.debug("Request to get AccessMenuGrp: {}", id);
        AccessMenuGrp accessmenugrp = accessmenugrpRepository.findById(id).orElse(null);
        return accessmenugrp;
    }

    @Transactional(
            readOnly = true
    )
    public AccessMenuGrpDTO findAccessMenuGrp(Integer id) {
        log.debug("Request to get AccessMenuGrp: {}", id);
        AccessMenuGrp accessmenugrp = findOne(id);
        AccessMenuGrpDTO dto = AccessMenuGrpFactory.accessmenugrpToAccessMenuGrpDTO(accessmenugrp);
        return dto;
    }

    @Transactional(
            readOnly = true
    )
    public List<AccessMenuGrpDTO> findAll() {
        log.debug("Request to get All AccessMenuGrps");
        List<AccessMenuGrp> result = accessmenugrpRepository.findAll();
        return AccessMenuGrpFactory.accessmenugrpToAccessMenuGrpDTOs(result);
    }

//    @Transactional(
//            readOnly = true
//    )
//    public List<AccessMenuGrp> findAllByVisible(Boolean[] visible) {
//        QAccessMenuGrp qAccessMenuGrp = QAccessMenuGrp.accessMenuGrp;
//        WhereClauseBuilder builder = new WhereClauseBuilder()
//                .optionalAnd(visible, () -> qAccessMenuGrp.visible.in(visible));
//        List<AccessMenuGrp> result = (List<AccessMenuGrp>) accessmenugrpRepository.findAll(builder);
//        return result;
//    }

    public String delete(Integer id) {
        log.debug("Request to delete AccessMenuGrp: {}", id);
        AccessMenuGrp accessmenugrp = findOne(id);
        Preconditions.checkBusinessLogique(accessmenugrp != null, "");
        accessmenugrpRepository.delete(accessmenugrp);
        return "True";
    }

    @Transactional(
            readOnly = true
    )
    public Boolean exists(Integer id) {
        QAccessMenuGrp qAccessMenuGrp = QAccessMenuGrp.accessMenuGrp;
        WhereClauseBuilder builder = new WhereClauseBuilder()
                .optionalAnd(id, () -> qAccessMenuGrp.idAccessMenuGroup.eq(id));
        Boolean result = accessmenugrpRepository.exists(builder);
        return result;
    }
}
