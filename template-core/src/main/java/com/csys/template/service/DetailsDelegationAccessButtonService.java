package com.csys.template.service;

import com.csys.template.domain.DetailsDelegationAccessButton;
import com.csys.template.dto.DetailsDelegationAccessButtonDTO;
import com.csys.template.factory.DetailsDelegationAccessButtonFactory;
import com.csys.template.repository.DetailsDelegationAccessButtonRepository;
import com.csys.template.util.Preconditions;
import java.lang.Integer;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class DetailsDelegationAccessButtonService {
  private final Logger log = LoggerFactory.getLogger(DetailsDelegationAccessButtonService.class);

  private final DetailsDelegationAccessButtonRepository detailsdelegationaccessbuttonRepository;

  public DetailsDelegationAccessButtonService(DetailsDelegationAccessButtonRepository detailsdelegationaccessbuttonRepository) {
    this.detailsdelegationaccessbuttonRepository=detailsdelegationaccessbuttonRepository;
  }

 
  public String save(DetailsDelegationAccessButtonDTO detailsdelegationaccessbuttonDTO) {
    log.debug("Request to save DetailsDelegationAccessButton: {}",detailsdelegationaccessbuttonDTO);
    DetailsDelegationAccessButton detailsdelegationaccessbutton = DetailsDelegationAccessButtonFactory.detailsdelegationaccessbuttonDTOToDetailsDelegationAccessButton(detailsdelegationaccessbuttonDTO,null);
    detailsdelegationaccessbuttonRepository.save(detailsdelegationaccessbutton);
    return "true";
  }


  public String update(DetailsDelegationAccessButtonDTO detailsdelegationaccessbuttonDTO) {
    log.debug("Request to update DetailsDelegationAccessButton: {}",detailsdelegationaccessbuttonDTO);
    DetailsDelegationAccessButton inBase= findOne(detailsdelegationaccessbuttonDTO.getNumDelegation());
    Preconditions.checkBusinessLogique(inBase != null, "detailsdelegationaccessbutton.NotFound");
    DetailsDelegationAccessButton detailsdelegationaccessbutton = DetailsDelegationAccessButtonFactory.detailsdelegationaccessbuttonDTOToDetailsDelegationAccessButton(detailsdelegationaccessbuttonDTO,inBase);
    detailsdelegationaccessbuttonRepository.save(detailsdelegationaccessbutton);
    return "true";
  }

 
  @Transactional(
      readOnly = true
  )
  public DetailsDelegationAccessButton findOne(Integer id) {
    log.debug("Request to get DetailsDelegationAccessButton: {}",id);
    DetailsDelegationAccessButton detailsdelegationaccessbutton= detailsdelegationaccessbuttonRepository.findById(id).orElse(null);
    return detailsdelegationaccessbutton;
  }


  @Transactional(
      readOnly = true
  )
  public DetailsDelegationAccessButtonDTO findDetailsDelegationAccessButton(Integer id) {
    log.debug("Request to get DetailsDelegationAccessButton: {}",id);
    DetailsDelegationAccessButton detailsdelegationaccessbutton= findOne(id);
    DetailsDelegationAccessButtonDTO dto = DetailsDelegationAccessButtonFactory.detailsdelegationaccessbuttonToDetailsDelegationAccessButtonDTO(detailsdelegationaccessbutton);
    return dto;
    }

  
  @Transactional(
      readOnly = true
  )
  public List<DetailsDelegationAccessButtonDTO> findAll() {
    log.debug("Request to get All DetailsDelegationAccessButtons");
    List<DetailsDelegationAccessButton> result= detailsdelegationaccessbuttonRepository.findAll();
    return DetailsDelegationAccessButtonFactory.detailsdelegationaccessbuttonToDetailsDelegationAccessButtonDTOs(result);
  }

  
  public String delete(Integer id) {
    log.debug("Request to delete DetailsDelegationAccessButton: {}",id);
    DetailsDelegationAccessButton detailsdelegationaccessbutton = findOne(id);
    Preconditions.checkBusinessLogique(detailsdelegationaccessbutton != null, "GroupUser est inexistant");   
    detailsdelegationaccessbuttonRepository.delete(detailsdelegationaccessbutton);
    return "true";
  }
}

