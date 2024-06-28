package com.csys.template.service;

import com.csys.template.domain.DetailsDelegationAccessMenu;
import com.csys.template.dto.DetailsDelegationAccessMenuDTO;
import com.csys.template.factory.DetailsDelegationAccessMenuFactory;
import com.csys.template.repository.DetailsDelegationAccessMenuRepository;
import com.csys.template.util.Preconditions;
import java.lang.Integer;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class DetailsDelegationAccessMenuService {
  private final Logger log = LoggerFactory.getLogger(DetailsDelegationAccessMenuService.class);

  private final DetailsDelegationAccessMenuRepository detailsdelegationaccessmenuRepository;

  public DetailsDelegationAccessMenuService(DetailsDelegationAccessMenuRepository detailsdelegationaccessmenuRepository) {
    this.detailsdelegationaccessmenuRepository=detailsdelegationaccessmenuRepository;
  }

 
  public String save(DetailsDelegationAccessMenuDTO detailsdelegationaccessmenuDTO) {
    log.debug("Request to save DetailsDelegationAccessMenu: {}",detailsdelegationaccessmenuDTO);
    DetailsDelegationAccessMenu detailsdelegationaccessmenu = DetailsDelegationAccessMenuFactory.detailsdelegationaccessmenuDTOToDetailsDelegationAccessMenu(detailsdelegationaccessmenuDTO,null);
    detailsdelegationaccessmenuRepository.save(detailsdelegationaccessmenu);
    return "true";
  }

  
  public String update(DetailsDelegationAccessMenuDTO detailsdelegationaccessmenuDTO) {
    log.debug("Request to update DetailsDelegationAccessMenu: {}",detailsdelegationaccessmenuDTO);
    DetailsDelegationAccessMenu inBase= findOne(detailsdelegationaccessmenuDTO.getNumDelegation());
    Preconditions.checkBusinessLogique(inBase != null, "detailsdelegationaccessmenu.NotFound");
    DetailsDelegationAccessMenu detailsdelegationaccessmenu = DetailsDelegationAccessMenuFactory.detailsdelegationaccessmenuDTOToDetailsDelegationAccessMenu(detailsdelegationaccessmenuDTO,inBase);
    detailsdelegationaccessmenuRepository.save(detailsdelegationaccessmenu);
    return "true";
  }

  @Transactional(
      readOnly = true
  )
  public DetailsDelegationAccessMenu findOne(Integer id) {
    log.debug("Request to get DetailsDelegationAccessMenu: {}",id);
    DetailsDelegationAccessMenu detailsdelegationaccessmenu= detailsdelegationaccessmenuRepository.findById(id).orElse(null);
    return detailsdelegationaccessmenu;
  }

 
  @Transactional(
      readOnly = true
  )
  public DetailsDelegationAccessMenuDTO findDetailsDelegationAccessMenu(Integer id) {
    log.debug("Request to get DetailsDelegationAccessMenu: {}",id);
    DetailsDelegationAccessMenu detailsdelegationaccessmenu=findOne(id);
    DetailsDelegationAccessMenuDTO dto = DetailsDelegationAccessMenuFactory.detailsdelegationaccessmenuToDetailsDelegationAccessMenuDTO(detailsdelegationaccessmenu);
    return dto;
    
  }


  @Transactional(
      readOnly = true
  )
  public List<DetailsDelegationAccessMenuDTO> findAll() {
    log.debug("Request to get All DetailsDelegationAccessMenus");
    List<DetailsDelegationAccessMenu> result= detailsdelegationaccessmenuRepository.findAll();
    return DetailsDelegationAccessMenuFactory.detailsdelegationaccessmenuToDetailsDelegationAccessMenuDTOs(result);
  }


  public String delete(Integer id) {
    log.debug("Request to delete DetailsDelegationAccessMenu: {}",id);
     DetailsDelegationAccessMenu detailsdelegationaccessmenu = findOne(id);
        Preconditions.checkBusinessLogique(detailsdelegationaccessmenu != null, "detailsdelegationaccessmenu est inexistant");
        
    detailsdelegationaccessmenuRepository.delete(detailsdelegationaccessmenu);
    return "true";
  }
}

