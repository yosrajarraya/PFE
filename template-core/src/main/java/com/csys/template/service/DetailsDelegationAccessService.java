package com.csys.template.service;

import com.csys.template.domain.DetailsDelegationAccess;
import com.csys.template.dto.DetailsDelegationAccessDTO;
import com.csys.template.factory.DetailsDelegationAccessFactory;
import com.csys.template.repository.DetailsDelegationAccessRepository;
import com.google.common.base.Preconditions;
import java.lang.Integer;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DetailsDelegationAccessService {

    private final Logger log = LoggerFactory.getLogger(DetailsDelegationAccessService.class);

    private final DetailsDelegationAccessRepository detailsdelegationaccessRepository;

    public DetailsDelegationAccessService(DetailsDelegationAccessRepository detailsdelegationaccessRepository) {
        this.detailsdelegationaccessRepository = detailsdelegationaccessRepository;
    }

    public String save(DetailsDelegationAccessDTO detailsdelegationaccessDTO) {
        log.debug("Request to save DetailsDelegationAccess: {}", detailsdelegationaccessDTO);
        DetailsDelegationAccess detailsdelegationaccess = DetailsDelegationAccessFactory.detailsdelegationaccessDTOToDetailsDelegationAccess(detailsdelegationaccessDTO);
        detailsdelegationaccessRepository.save(detailsdelegationaccess);
        return "true";
    }

    public String update(DetailsDelegationAccessDTO detailsdelegationaccessDTO) {
        log.debug("Request to update DetailsDelegationAccess: {}", detailsdelegationaccessDTO);
        DetailsDelegationAccess inBase = findOne(detailsdelegationaccessDTO.getNumDelegation());
        Preconditions.checkArgument(inBase != null, "detailsdelegationaccess.NotFound");
        DetailsDelegationAccess detailsdelegationaccess = DetailsDelegationAccessFactory.detailsdelegationaccessDTOToDetailsDelegationAccess(detailsdelegationaccessDTO);
        detailsdelegationaccessRepository.save(detailsdelegationaccess);
        return "true";
    }

    @Transactional(
            readOnly = true
    )
    public DetailsDelegationAccess findOne(Integer id) {
        log.debug("Request to get DetailsDelegationAccess: {}", id);
        DetailsDelegationAccess detailsdelegationaccess = detailsdelegationaccessRepository.findById(id).orElse(null);
        return detailsdelegationaccess;
    }

    @Transactional(
            readOnly = true
    )
    public DetailsDelegationAccessDTO findDetailsDelegationAccess(Integer id) {
        log.debug("Request to get DetailsDelegationAccess: {}", id);
        DetailsDelegationAccess detailsdelegationaccess = findOne(id);
        DetailsDelegationAccessDTO dto = DetailsDelegationAccessFactory.detailsdelegationaccessToDetailsDelegationAccessDTO(detailsdelegationaccess);
        return dto;
    }

    @Transactional(
            readOnly = true
    )
    public List<DetailsDelegationAccessDTO> findAll() {
        log.debug("Request to get All DetailsDelegationAccesss");
        List<DetailsDelegationAccess> result = detailsdelegationaccessRepository.findAll();
        return DetailsDelegationAccessFactory.detailsdelegationaccessToDetailsDelegationAccessDTOs(result);
    }

    public String delete(Integer id) {
        log.debug("Request to delete DetailsDelegationAccess: {}", id);
        DetailsDelegationAccess detailsdelegationaccess = findOne(id);
        detailsdelegationaccessRepository.delete(detailsdelegationaccess);
        return "true";
    }
}
