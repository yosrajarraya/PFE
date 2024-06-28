package com.csys.template.service;

import com.csys.template.domain.AccessMenuUser;
import com.csys.template.domain.QAccessMenuUser;
import com.csys.template.dto.AccessMenuUserDTO;
import com.csys.template.dto.MenuDTO;
import com.csys.template.factory.AccessMenuUserFactory;
import com.csys.template.factory.MenuFactory;
import com.csys.template.repository.AccessMenuUserRepository;
import com.csys.template.util.Helper;
import com.csys.template.util.Preconditions;
import com.csys.template.util.WhereClauseBuilder;
import java.lang.Integer;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AccessMenuUserService {

    private final Logger log = LoggerFactory.getLogger(AccessMenuUserService.class);

    private final AccessMenuUserRepository accessmenuuserRepository;

    public AccessMenuUserService(AccessMenuUserRepository accessmenuuserRepository) {
        this.accessmenuuserRepository = accessmenuuserRepository;
    }

    public String save(AccessMenuUserDTO accessmenuuserDTO) {
        log.debug("Request to save AccessMenuUser: {}", accessmenuuserDTO);
        Preconditions.checkBusinessLogique(!exists(accessmenuuserDTO.getIdAccessMenuUser()), "l'id est deja utilisé");
        AccessMenuUser accessmenuuser = AccessMenuUserFactory.accessmenuuserDTOToAccessMenuUser(accessmenuuserDTO);
        accessmenuuserRepository.save(accessmenuuser);
        return "True";
    }

    public String update(AccessMenuUserDTO accessmenuuserDTO) {
        log.debug("Request to update AccessMenuUser: {}", accessmenuuserDTO);
        AccessMenuUser inBase = findOne(accessmenuuserDTO.getIdAccessMenuUser());
        Preconditions.checkBusinessLogique(inBase != null, "accessmenuuser.NotFound");
        AccessMenuUser accessmenuuser = AccessMenuUserFactory.accessmenuuserDTOToAccessMenuUser(accessmenuuserDTO);
        accessmenuuserRepository.save(accessmenuuser);
        return "String";
    }

    @Transactional(
            readOnly = true
    )
    public AccessMenuUser findOne(Integer id_accessmenuuser) {
        log.debug("Request to get AccessMenuUser: {}", id_accessmenuuser);
        AccessMenuUser accessmenuuser = accessmenuuserRepository.findById(id_accessmenuuser).orElse(null);
        return accessmenuuser;
    }

    @Transactional(
            readOnly = true
    )
    public AccessMenuUserDTO findAccessMenuUser(Integer id_accessmenuuser) {
        log.debug("Request to get AccessMenuUser: {}", id_accessmenuuser);
        AccessMenuUser accessmenuuser = findOne(id_accessmenuuser);
        AccessMenuUserDTO accessMenuUserDTO = AccessMenuUserFactory.accessmenuuserToAccessMenuUserDTO(accessmenuuser);
        return accessMenuUserDTO;
    }

    @Transactional(
            readOnly = true
    )
    public List<AccessMenuUserDTO> findAll() {
        log.debug("Request to get All AccessMenuUsers");
        List<AccessMenuUser> result =accessmenuuserRepository.findAll();
        return AccessMenuUserFactory.accessmenuuserToAccessMenuUserDTOs(result);
    }


    @Transactional(
            readOnly = true
    )
    public List<MenuDTO> findAll(Integer idModule, String username ) {
        log.debug("Request to get All AccessMenuUsers");
         QAccessMenuUser accessMenuUser = QAccessMenuUser.accessMenuUser;
        WhereClauseBuilder builder = new WhereClauseBuilder()
                .booleanAnd(username == null, () -> accessMenuUser.userId.eq(Helper.getUserAuthenticated()))
                .booleanAnd(username != null, () -> accessMenuUser.userId.eq(username))
                .optionalAnd(idModule, () -> accessMenuUser.menu().idModule.eq(idModule));
        List<AccessMenuUser> result = (List<AccessMenuUser>) accessmenuuserRepository.findAll(builder);
        
        return MenuFactory.menuToMenuDTOs(result.stream().map(x->x.getMenu()).collect(Collectors.toList()));
    }

 
    public String delete(Integer id_accessmenuuser) {
        log.debug("Request to delete AccessMenuUser: {}", id_accessmenuuser);
        AccessMenuUser accessmenuuser = findOne(id_accessmenuuser);
        Preconditions.checkBusinessLogique(accessmenuuser != null, "AccessMenuUser est inexistant");
        accessmenuuserRepository.delete(accessmenuuser);
        return "True";
    }

    @Transactional(
            readOnly = true
    )
    public Boolean exists(Integer id) {
        QAccessMenuUser qAccessMenuUser = QAccessMenuUser.accessMenuUser;
        WhereClauseBuilder builder = new WhereClauseBuilder()
                .optionalAnd(id, () -> qAccessMenuUser.idAccessMenuUser.eq(id));
        Boolean result = accessmenuuserRepository.exists(builder);
        return result;
    }
 public void updateAccessMenuUser(Integer idAccessMenuUser, Integer numDelegation) {
        log.debug("Request to update AccessMenuUser with Delegation: {}", idAccessMenuUser);
        AccessMenuUser accessMenuUser = findOne(idAccessMenuUser);
        accessMenuUser.setVisible(true);
        accessMenuUser.setNumDelegate(numDelegation);
        accessmenuuserRepository.save(accessMenuUser);
    }
  public void deleteAccessMenuUser(Integer idAccessMenuUser, Integer numDelegation) {
        log.debug("Request to update AccessMenuUser with Delegation: {}", idAccessMenuUser);
        AccessMenuUser accessMenuUser = findOne(idAccessMenuUser);
        accessMenuUser.setVisible(false);
        accessMenuUser.setNumDelegate(null);
        accessmenuuserRepository.save(accessMenuUser);
    }
}
