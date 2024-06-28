package com.csys.template.service;

import com.csys.template.domain.Menu;
import com.csys.template.domain.QMenu;
import com.csys.template.dto.MenuDTO;
import com.csys.template.factory.MenuFactory;
import com.csys.template.repository.MenuRepository;
import com.csys.template.util.WhereClauseBuilder;
import com.csys.template.util.Preconditions;

import java.lang.Integer;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing Menu.
 */
@Service
@Transactional
public class MenuService {
  private final Logger log = LoggerFactory.getLogger(MenuService.class);

  private final MenuRepository menuRepository;

  public MenuService(MenuRepository menuRepository) {
    this.menuRepository=menuRepository;
  }

 
  

  @Transactional(
      readOnly = true
  )
  public Menu findOne(Integer id_menu) {
    log.debug("Request to get Menu: {}",id_menu);
    Menu menu= menuRepository.findById(id_menu).orElse(null);
   
    return menu;
  }


  @Transactional(
      readOnly = true
  )
  public MenuDTO findMenu(Integer id_menu) {
    log.debug("Request to get Menu: {}",id_menu);
    Menu menu= findOne(id_menu);
    MenuDTO dto = MenuFactory.menuToMenuDTO(menu);
    return dto;
  }

 
  @Transactional(
      readOnly = true
  )
  public List<MenuDTO> findAll() {
    log.debug("Request to get All Menus");
    List<Menu> result= menuRepository.findAll();
    return MenuFactory.menuToMenuDTOs(result);
  }


  
  
    @Transactional(
            readOnly = true
    )
    public Boolean exists(String code) {
        QMenu qMenu = QMenu.menu;
        WhereClauseBuilder builder = new WhereClauseBuilder()
                .optionalAnd(code, () -> qMenu.codeMenu.eq(code));
        Boolean result = menuRepository.exists(builder);
        return result;
    }
}

