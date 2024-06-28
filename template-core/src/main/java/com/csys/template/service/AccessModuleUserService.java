package com.csys.template.service;

import com.csys.template.domain.AccessModuleUser;
import com.csys.template.domain.QAccessModuleUser;
import com.csys.template.dto.AccessModuleUserDTO;
import com.csys.template.dto.ModuleDTO;
import com.csys.template.factory.AccessModuleUserFactory;
import com.csys.template.factory.ModuleFactory;
import com.csys.template.repository.AccessModuleUserRepository;
import com.csys.template.util.Helper;
import com.csys.template.util.WhereClauseBuilder;
import com.csys.template.util.Preconditions;

import java.lang.Integer;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AccessModuleUserService {

    private final Logger log = LoggerFactory.getLogger(AccessModuleUserService.class);

    private final AccessModuleUserRepository accessmoduleuserRepository;

    public AccessModuleUserService(AccessModuleUserRepository accessmoduleuserRepository) {
        this.accessmoduleuserRepository = accessmoduleuserRepository;
    }

    public String save(AccessModuleUserDTO accessmoduleuserDTO) {
        log.debug("Request to save AccessModuleUser: {}", accessmoduleuserDTO);
        AccessModuleUser accessmoduleuser = AccessModuleUserFactory.accessmoduleuserDTOToAccessModuleUser(accessmoduleuserDTO);
        accessmoduleuser = accessmoduleuserRepository.save(accessmoduleuser);
        return "True";
    }

    public String update(AccessModuleUserDTO accessmoduleuserDTO) {
        log.debug("Request to update AccessModuleUser: {}", accessmoduleuserDTO);
        AccessModuleUser inBase = findOne(accessmoduleuserDTO.getIdAccessModuleUser());
        Preconditions.checkBusinessLogique(inBase != null, "accessmoduleuser.NotFound");
        AccessModuleUser accessmoduleuser = AccessModuleUserFactory.accessmoduleuserDTOToAccessModuleUser(accessmoduleuserDTO);
        accessmoduleuser = accessmoduleuserRepository.save(accessmoduleuser);
        return "True";
    }

    @Transactional(
            readOnly = true
    )
    public AccessModuleUser findOne(Integer id) {
        log.debug("Request to get AccessModuleUser: {}", id);
        AccessModuleUser accessmoduleuser = accessmoduleuserRepository.findById(id).orElse(null);
        return accessmoduleuser;
    }

    @Transactional(
            readOnly = true
    )
    public AccessModuleUserDTO findAccessModuleUser(Integer id) {
        log.debug("Request to get AccessModuleUser: {}", id);
        AccessModuleUser accessmoduleuser = findOne(id);
        AccessModuleUserDTO dto = AccessModuleUserFactory.accessmoduleuserToAccessModuleUserDTO(accessmoduleuser);
        return dto;
    }

    @Transactional(
            readOnly = true
    )
    public List<AccessModuleUserDTO> findAll() {
        log.debug("Request to get All AccessModuleUsers");
        List<AccessModuleUser> result = accessmoduleuserRepository.findAll();
        return AccessModuleUserFactory.accessmoduleuserToAccessModuleUserDTOs(result);
    }  
    
    @Transactional(
            readOnly = true
    )
    public List<ModuleDTO> findAll(Integer idModule , String username) {
        log.debug("Request to get All AccessModuleUsers");
         QAccessModuleUser accessModuleUser = QAccessModuleUser.accessModuleUser;
        WhereClauseBuilder builder = new WhereClauseBuilder()
                .booleanAnd(username == null, () -> accessModuleUser.userId.eq(Helper.getUserAuthenticated()))
                .booleanAnd(username != null, () -> accessModuleUser.userId.eq(username))
                .optionalAnd(idModule, () -> accessModuleUser.idModule.eq(idModule));
        List<AccessModuleUser> result = (List<AccessModuleUser>) accessmoduleuserRepository.findAll(builder);
        
        return ModuleFactory.moduleToModuleDTOs(result.stream().map(x->x.getModule()).collect(Collectors.toList()));
    }

    public String delete(Integer id) {
        log.debug("Request to delete AccessModuleUser: {}", id);
        AccessModuleUser accessmoduleuser = findOne(id);
        Preconditions.checkBusinessLogique(accessmoduleuser != null, "l'acces module utilisateur est inexistant");
        accessmoduleuserRepository.delete(accessmoduleuser);
        return "True";
    }

 
}
