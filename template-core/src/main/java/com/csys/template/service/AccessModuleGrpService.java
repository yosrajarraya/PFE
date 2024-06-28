package com.csys.template.service;

import com.csys.template.domain.AccessModuleGrp;
import com.csys.template.domain.QAccessModuleGrp;
import com.csys.template.dto.AccessModuleGrpDTO;
import com.csys.template.factory.AccessModuleGrpFactory;
import com.csys.template.repository.AccessModuleGrpRepository;
import com.csys.template.util.WhereClauseBuilder;
import com.csys.template.util.Preconditions;

import java.lang.Integer;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AccessModuleGrpService {

    private final Logger log = LoggerFactory.getLogger(AccessModuleGrpService.class);

    private final AccessModuleGrpRepository accessmodulegrpRepository;

    public AccessModuleGrpService(AccessModuleGrpRepository accessmodulegrpRepository) {
        this.accessmodulegrpRepository = accessmodulegrpRepository;
    }


    public String save(AccessModuleGrpDTO accessmodulegrpDTO) {
        log.debug("Request to save AccessModuleGrp: {}", accessmodulegrpDTO);
        AccessModuleGrp accessmodulegrp = AccessModuleGrpFactory.accessmodulegrpDTOToAccessModuleGrp(accessmodulegrpDTO);
        accessmodulegrp = accessmodulegrpRepository.save(accessmodulegrp);
        return "True";
    }

    public String update(AccessModuleGrpDTO accessmodulegrpDTO) {
        log.debug("Request to update AccessModuleGrp: {}", accessmodulegrpDTO);
        AccessModuleGrp inBase = findOne(accessmodulegrpDTO.getId());
        Preconditions.checkBusinessLogique(inBase != null, "accessmodulegrp.NotFound");
        AccessModuleGrp accessmodulegrp = AccessModuleGrpFactory.accessmodulegrpDTOToAccessModuleGrp(accessmodulegrpDTO);
        accessmodulegrp = accessmodulegrpRepository.save(accessmodulegrp);
        return "True";
    }

    @Transactional(readOnly = true)
    public AccessModuleGrp findOne(Integer id) {
        log.debug("Request to get AccessModuleGrp: {}", id);
        AccessModuleGrp accessmodulegrp = accessmodulegrpRepository.findById(id).orElse(null);
        return accessmodulegrp;
    }

    @Transactional(readOnly = true)

    public AccessModuleGrpDTO findAccessModuleGrp(Integer id) {
        log.debug("Request to get AccessModuleGrp: {}", id);
        AccessModuleGrp accessmodulegrp = findOne(id);
        AccessModuleGrpDTO dto = AccessModuleGrpFactory.accessmodulegrpToAccessModuleGrpDTO(accessmodulegrp);
        return dto;
    }

    @Transactional(
            readOnly = true
    )
    public List<AccessModuleGrpDTO> findAll() {
        log.debug("Request to get All AccessModuleGrps");
        List<AccessModuleGrp> result = accessmodulegrpRepository.findAll();
        return AccessModuleGrpFactory.accessmodulegrpToAccessModuleGrpDTOs(result);
    }

    public String delete(Integer id_accessModuleGrp) {
        log.debug("Request to delete AccessModuleGrp: {}", id_accessModuleGrp);
        AccessModuleGrp accessModuleGrp = findOne(id_accessModuleGrp);
        accessmodulegrpRepository.delete(accessModuleGrp);
        return "True";
    }

//    @Transactional(
//            readOnly = true
//    )
//    public Boolean exists(String code) {
//        QAccessModuleGrp qAccessModuleGrp = QAccessModuleGrp.accessModuleGrp;
//        WhereClauseBuilder builder = new WhereClauseBuilder()
//                .optionalAnd(code, () -> qAccessModuleGrp.code.eq(code));
//        Boolean result = accessmodulegrpRepository.exists(builder);
//        return result;
//    }
}
