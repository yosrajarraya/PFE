package com.csys.template.service;

import com.csys.template.domain.Button;
import com.csys.template.domain.QButton;
import com.csys.template.dto.ButtonDTO;
import com.csys.template.factory.ButtonFactory;
import com.csys.template.repository.ButtonRepository;
import com.csys.template.util.Preconditions;
import com.csys.template.util.Preconditions;
import com.csys.template.util.WhereClauseBuilder;
import java.lang.Integer;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing Button.
 */
@Service
@Transactional
public class ButtonService {
  private final Logger log = LoggerFactory.getLogger(ButtonService.class);

  private final ButtonRepository buttonRepository;

  public ButtonService(ButtonRepository buttonRepository) {
    this.buttonRepository=buttonRepository;
  }

 

 
  @Transactional(
      readOnly = true
  )
  public Button findOne(Integer id) {
    log.debug("Request to get Button: {}",id);
    Button button= buttonRepository.findById(id).orElse(null);
    return button;
  }

 
  @Transactional( readOnly = true
  )
  public ButtonDTO findButton(Integer id) {
    log.debug("Request to get Button: {}",id);
    Button button= findOne(id);
    ButtonDTO dto =ButtonFactory.buttonToButtonDTO(button);
    return dto;
  }

 
  @Transactional(
      readOnly = true
  )
  public List<ButtonDTO> findAll() {
    log.debug("Request to get All Buttons");
    List<Button> result= buttonRepository.findAll();
    return ButtonFactory.buttonToButtonDTOs(result);
  }

    @Transactional(
            readOnly = true
    )
    public Boolean exists(String code) {
        QButton qButton = QButton.button;
        WhereClauseBuilder builder = new WhereClauseBuilder()
                .optionalAnd(code, () -> qButton.codeButton.eq(code));
        Boolean result = buttonRepository.exists(builder);
        return result;
    }
}

