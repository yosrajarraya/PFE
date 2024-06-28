package com.csys.template.service;

import com.csys.template.domain.Groupe;
import com.csys.template.domain.QGroupe;
import com.csys.template.dto.GroupeDTO;
import com.csys.template.factory.GroupeFactory;
import com.csys.template.util.Preconditions;
import com.csys.template.util.WhereClauseBuilder;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.csys.template.repository.GroupeRepository;
import org.springframework.data.domain.Sort;

@Service
@Transactional
public class GroupeService {

    private final Logger log = LoggerFactory.getLogger(GroupeService.class);

    private final GroupeRepository groupuserRepository;

    public GroupeService(GroupeRepository groupuserRepository) {
        this.groupuserRepository = groupuserRepository;
    }

    public String save(GroupeDTO groupuserDTO) {
        log.debug("Request to save GroupUser: {}", groupuserDTO);
        Preconditions.checkBusinessLogique(!exists(groupuserDTO.getGroupe()), "Le code est déjà utilisé");
        Preconditions.checkBusinessLogique((groupuserDTO.getGroupe().length() <= 20 && groupuserDTO.getGroupe().length() > 0), "Le code est invalide");
        Groupe groupuser = GroupeFactory.groupDTOToGroup(groupuserDTO, null, SecurityContextHolder.getContext().getAuthentication().getName());
        groupuserRepository.save(groupuser);

        return "True";
    }

    public String update(GroupeDTO groupuserDTO) {
        log.debug("Request to update GroupUser: {}", groupuserDTO);
        Groupe inBase = findOne(groupuserDTO.getGroupe());
        Preconditions.checkBusinessLogique(inBase != null, "Le groupe est inexistant");
        Groupe groupuser = GroupeFactory.groupDTOToGroup(groupuserDTO, inBase, SecurityContextHolder.getContext().getAuthentication().getName());
        groupuserRepository.save(groupuser);
        return "True";
    }

    @Transactional(
            readOnly = true
    )
    public Groupe findOne(String getGroupe) {
        log.debug("Request to get GroupUser: {}", getGroupe);
        Groupe groupuser = groupuserRepository.findById(getGroupe).orElse(null);

        return groupuser;
    }

    @Transactional(
            readOnly = true
    )
    public GroupeDTO findGroupUser(String id_groupe) {
        log.debug("Request to get GroupUser: {}", id_groupe);
        Groupe groupuser = findOne(id_groupe);
        GroupeDTO dto = GroupeFactory.groupToGroupDTO(groupuser);
        return dto;
    }

    @Transactional(
            readOnly = true
    )
    public List<GroupeDTO> findAll(Boolean[] actifs) {
        log.debug("Request to get All GroupUsers");
        List<Groupe> result = findAllByActifs(actifs);
        return GroupeFactory.groupToGroupDTOs(result);
    }

    public List<Groupe> findAllByActifs(Boolean[] actifs) {
        log.debug("Request to get All GroupUsers");
        QGroupe qGroup = QGroupe.groupe1;
        WhereClauseBuilder builder = new WhereClauseBuilder()
                .optionalAnd(actifs, () -> qGroup.active.in(actifs));
        List<Groupe> result = (List<Groupe>) groupuserRepository.findAll(builder);
        return result;
    }

    public String delete(String id_groupe) {
        log.debug("Request to delete GroupUser: {}", id_groupe);
        Groupe groupUser = findOne(id_groupe);
        Preconditions.checkBusinessLogique(groupUser != null, "Le groupe est inexistant");
        groupuserRepository.delete(groupUser);
        return "True";
    }

    @Transactional(
            readOnly = true
    )
    public Boolean exists(String groupe) {
        QGroupe qGroup = QGroupe.groupe1;
        WhereClauseBuilder builder = new WhereClauseBuilder()
                .optionalAnd(groupe, () -> qGroup.groupe.eq(groupe));
        Boolean result = groupuserRepository.exists(builder);
        return result;
    }
     @Transactional(readOnly = true)
//methode pour l'imprission
    public List<Groupe> listAll() {
        List<Groupe> groupe = groupuserRepository.findAll(Sort.by("groupe").ascending());
        return groupe;
    }
}
