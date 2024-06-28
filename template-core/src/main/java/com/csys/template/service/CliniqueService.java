package com.csys.template.service;

import com.csys.template.domain.Clinique;
import com.csys.template.domain.QClinique;
import com.csys.template.dto.CliniqueDTO;
import com.csys.template.factory.CliniqueFactory;
import com.csys.template.repository.CliniqueRepository;
import com.csys.template.util.Preconditions;
import com.csys.template.util.WhereClauseBuilder;
import java.lang.Integer;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CliniqueService {

    private final Logger log = LoggerFactory.getLogger(CliniqueService.class);

    private final CliniqueRepository cliniqueRepository;

    public CliniqueService(CliniqueRepository cliniqueRepository) {
        this.cliniqueRepository = cliniqueRepository;
    }

    public String save(CliniqueDTO cliniqueDTO) {
        log.debug("Request to save Clinique: {}", cliniqueDTO);
        Preconditions.checkBusinessLogique(!exists(cliniqueDTO.getName()), "Le name est déjà utilisé");
        Preconditions.checkBusinessLogique((cliniqueDTO.getName().length() <= 20 && cliniqueDTO.getName().length() > 0), "Le name est invalide");
        Clinique clinique = CliniqueFactory.cliniqueDTOToClinique(cliniqueDTO);
        cliniqueRepository.save(clinique);
        return "True";
    }

    public String update(CliniqueDTO cliniqueDTO) {
        log.debug("Request to update Clinique: {}", cliniqueDTO);
        Clinique inBase = findOne(cliniqueDTO.getIdClinique());
        Preconditions.checkBusinessLogique(inBase != null, "clinique.NotFound");
        Clinique clinique = CliniqueFactory.cliniqueDTOToClinique(cliniqueDTO);
        cliniqueRepository.save(clinique);
        return "True";
    }

    @Transactional(
            readOnly = true
    )
    public Clinique findOne(Integer id) {
        log.debug("Request to get Clinique: {}", id);
        Clinique clinique = cliniqueRepository.findById(id).orElse(null);
        return clinique ;
    }

    @Transactional(
            readOnly = true
    )
    public CliniqueDTO findClinique(Integer id) {
        log.debug("Request to get Clinique: {}", id);
        Clinique clinique = findOne(id);
        CliniqueDTO dto = CliniqueFactory.cliniqueToCliniqueDTO(clinique);
        return dto;
    }

    @Transactional(
            readOnly = true
    )
    public List<CliniqueDTO> findAll(String[] name) {
        log.debug("Request to get All Cliniques");
        List<Clinique> result = findAllByName(name);
        return CliniqueFactory.cliniqueToCliniqueDTOs(result);
    }

     public List<Clinique> findAllByName(String[] name) {
        QClinique qClinique = QClinique.clinique;
        WhereClauseBuilder builder = new WhereClauseBuilder()
                .optionalAnd(name, () -> qClinique.name.in(name));
         List<Clinique>  result = (List<Clinique>) cliniqueRepository.findAll(builder);
        return result;
    }
    
    public String delete(Integer id) {
        log.debug("Request to delete Clinique: {}", id);
        Clinique clinique = findOne(id);
        cliniqueRepository.delete(clinique);
        return "True";
    }
    
        public Boolean exists(String name) {
        QClinique qClinique = QClinique.clinique;
        WhereClauseBuilder builder = new WhereClauseBuilder()
                .optionalAnd(name, () -> qClinique.name.eq(name));
        Boolean result = cliniqueRepository.exists(builder);
        return result;
    }
}
