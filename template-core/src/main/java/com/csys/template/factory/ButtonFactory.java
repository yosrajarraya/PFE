package com.csys.template.factory;

import com.csys.template.domain.Button;
import com.csys.template.dto.ButtonDTO;
import java.util.ArrayList;
import java.util.List;

public class ButtonFactory {
  public static ButtonDTO buttonToButtonDTO(Button button) {
    ButtonDTO buttonDTO=new ButtonDTO();
    buttonDTO.setIdButton(button.getIdButton());
    buttonDTO.setCodeButton(button.getCodeButton());
    buttonDTO.setCodeButtonPrincipal(button.getCodeButtonPrincipal());
    buttonDTO.setOrder(button.getOrder());
    buttonDTO.setDesignation(button.getDesignation());
    buttonDTO.setLogo(button.getLogo());
    buttonDTO.setIdMenu((button.getIdMenu()));
    return buttonDTO;
  }

  public static Button buttonDTOToButton(ButtonDTO buttonDTO ,Button button) {
    if( button == null){
        button=new Button();
    button.setIdButton(buttonDTO.getIdButton());
    button.setCodeButton(buttonDTO.getCodeButton());
    }
      
    button.setCodeButtonPrincipal(buttonDTO.getCodeButtonPrincipal());
    button.setOrder(buttonDTO.getOrder());
    button.setDesignation(buttonDTO.getDesignation());
    button.setLogo(buttonDTO.getLogo());
    button.setIdMenu(buttonDTO.getIdMenu());
    return button;
  }

  public static List<ButtonDTO> buttonToButtonDTOs(List<Button> buttons) {
    List<ButtonDTO> buttonsDTO=new ArrayList<>();
    buttons.forEach(x -> {
      buttonsDTO.add(buttonToButtonDTO(x));
    } );
    return buttonsDTO;
  }
  
  
//    public static List<AccessDTO> buttonToAccessDTOWithoutHirarchys(List<Button> buttons, AccessDTO accessModuleDTO) {
//        List<AccessDTO> accessDTOs = new ArrayList<>();
//        buttons.forEach(x -> {
//            AccessDTO accessDTO = new AccessDTO();
//            accessDTO.setId(x.getIdButton());
////            accessDTO.setCode(x.getCodeButton());
//            accessDTO.setDesignation(x.getDesignation());
//            accessDTO.setIdParent(accessModuleDTO.getId());
////            accessDTO.setCodeParent(accessModuleDTO.getCode());
//            accessDTO.setType("button");
//            accessDTOs.add(accessDTO);
//        });
//        return accessDTOs;
//    }
}

