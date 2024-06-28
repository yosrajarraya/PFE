package com.csys.template.service;

import com.csys.template.domain.AccessButtonUser;
import com.csys.template.domain.QAccessButtonUser;
import com.csys.template.dto.AccessButtonUserDTO;
import com.csys.template.factory.AccessButtonUserFactory;
import com.csys.template.factory.ButtonFactory;
import com.csys.template.dto.ButtonDTO;
import com.csys.template.util.Helper;
import java.util.stream.Collectors;
import com.csys.template.repository.AccessButtonUserRepository;
import com.csys.template.util.Preconditions;
import com.csys.template.util.WhereClauseBuilder;

import java.lang.Integer;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing AccessButtonUser.
 */
@Service
@Transactional
public class AccessButtonUserService {

    private final Logger log = LoggerFactory.getLogger(AccessButtonUserService.class);

    private final AccessButtonUserRepository accessbuttonuserRepository;

    public AccessButtonUserService(AccessButtonUserRepository accessbuttonuserRepository) {
        this.accessbuttonuserRepository = accessbuttonuserRepository;
    }

    public String save(AccessButtonUserDTO accessbuttonuserDTO) {
        log.debug("Request to save AccessButtonUser: {}", accessbuttonuserDTO);
        AccessButtonUser accessbuttonuser = AccessButtonUserFactory.accessbuttonuserDTOToAccessButtonUser(accessbuttonuserDTO);
        accessbuttonuser = accessbuttonuserRepository.save(accessbuttonuser);
        return "True";
    }

    public String update(AccessButtonUserDTO accessbuttonuserDTO) {
        log.debug("Request to update AccessButtonUser: {}", accessbuttonuserDTO);
        AccessButtonUser inBase = findOne(accessbuttonuserDTO.getIdAccessButtonUser());
        Preconditions.checkBusinessLogique(inBase != null, "accessbuttonuser.NotFound");
        AccessButtonUser accessbuttonuser = AccessButtonUserFactory.accessbuttonuserDTOToAccessButtonUser(accessbuttonuserDTO);
        accessbuttonuserRepository.save(accessbuttonuser);
        return "True";
    }

    @Transactional(
            readOnly = true
    )
    public AccessButtonUser findOne(Integer id_AccessButtonUser) {
        log.debug("Request to get AccessButtonUser: {}", id_AccessButtonUser);
        AccessButtonUser accessbuttonuser = accessbuttonuserRepository.findById(id_AccessButtonUser).orElse(null);
        return accessbuttonuser;
    }

    @Transactional(
            readOnly = true
    )
    public AccessButtonUserDTO findAccessButtonUser(Integer id_AccessButtonUser) {
        log.debug("Request to get AccessButtonUser: {}", id_AccessButtonUser);
        AccessButtonUser accessbuttonuser = findOne(id_AccessButtonUser);
        AccessButtonUserDTO dto = AccessButtonUserFactory.accessbuttonuserToAccessButtonUserDTO(accessbuttonuser);

        return dto;
    }

    @Transactional(
            readOnly = true
    )
    public List<AccessButtonUserDTO> findAll() {
        log.debug("Request to get All AccessButtonUsers");
        List<AccessButtonUser> result = accessbuttonuserRepository.findAll();
        return AccessButtonUserFactory.accessbuttonuserToAccessButtonUserDTOs(result);
    }

    public String delete(Integer id_AccessButtonUser) {
        log.debug("Request to delete AccessButtonUser: {}", id_AccessButtonUser);
        AccessButtonUser accessbuttonuser = findOne(id_AccessButtonUser);
        Preconditions.checkBusinessLogique(accessbuttonuser != null, "");
        accessbuttonuserRepository.delete(accessbuttonuser);
        return "True";
    }

    @Transactional(
            readOnly = true
    )
    public List<ButtonDTO> findAll(Integer idModule, String username) {
        log.debug("Request to get All AccessButtonUsers");
        QAccessButtonUser accessButtonUser = QAccessButtonUser.accessButtonUser;
        WhereClauseBuilder builder = new WhereClauseBuilder()
                .booleanAnd(username == null, () -> accessButtonUser.userId.eq(Helper.getUserAuthenticated()))
                .booleanAnd(username != null, () -> accessButtonUser.userId.eq(username))
                .optionalAnd(idModule, () -> accessButtonUser.button().menu().idModule.eq(idModule));
        List<AccessButtonUser> result = (List<AccessButtonUser>) accessbuttonuserRepository.findAll(builder);

        return ButtonFactory.buttonToButtonDTOs(result.stream().map(x -> x.getButton()).collect(Collectors.toList()));
    }

    public void updateAccessButtonUser(Integer idAccessButtonUser, Integer numDelegation) {
        log.debug("Request to update AccessButtonUser with Delegation: {}", idAccessButtonUser);
        AccessButtonUser accessButtonUser = findOne(idAccessButtonUser);
        accessButtonUser.setVisible(true);
        accessButtonUser.setNumDelegate(numDelegation);
        accessbuttonuserRepository.save(accessButtonUser);
    }

    public void deleteAccessButtonUser(Integer idAccessButtonUser, Integer numDelegation) {
        log.debug("Request to update AccessButtonUser with Delegation: {}", idAccessButtonUser);
        AccessButtonUser accessButtonUser = findOne(idAccessButtonUser);
        accessButtonUser.setVisible(false);
        accessButtonUser.setNumDelegate(null);
        accessbuttonuserRepository.save(accessButtonUser);
    }
}
