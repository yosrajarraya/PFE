package com.csys.template.service;

import com.csys.template.domain.Button;
import com.csys.template.domain.Menu;
import com.csys.template.domain.Module;
import com.csys.template.domain.QModule;
import com.csys.template.dto.AccessDTO;
import com.csys.template.dto.ButtonDTO;
import com.csys.template.dto.MenuDTO;
import com.csys.template.dto.ModuleDTO;
import com.csys.template.factory.ButtonFactory;
import com.csys.template.factory.MenuFactory;
import com.csys.template.factory.ModuleFactory;
import com.csys.template.repository.ModuleRepository;
import com.csys.template.util.WhereClauseBuilder;

import java.lang.Integer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ModuleService {

    private final Logger log = LoggerFactory.getLogger(ModuleService.class);

    private final ModuleRepository moduleRepository;

    public ModuleService(ModuleRepository moduleRepository) {
        this.moduleRepository = moduleRepository;
    }

    @Transactional(
            readOnly = true
    )
    public Module findOne(Integer id_Module) {
        log.debug("Request to get Module: {}", id_Module);
        Module module = moduleRepository.findById(id_Module).orElse(null);
        return module;
    }

    @Transactional(
            readOnly = true
    )
    public ModuleDTO findModule(Integer id_Module) {
        log.debug("Request to get Module: {}", id_Module);
        Module module = findOne(id_Module);
        ModuleDTO dto = ModuleFactory.moduleToModuleDTO(module);
        return dto;
    }

    @Transactional(
            readOnly = true
    )
    public Collection<ModuleDTO> findAll(Boolean[] actifs) {
        log.debug("Request to get All Modules");
        List<Module> result = findAllByActifs(actifs);
        return ModuleFactory.moduleToModuleDTOs(result);
    }

//    @Transactional(
//            readOnly = true
//    )
//    public List<AccessDTO> findAllWithoutHirarchy(Boolean[] actifs) {
//        log.debug("Request to get All Modules");
//        List<Module> result = findAllByActifs(actifs);
//        return ModuleFactory.moduleToAccessDTOWithoutHirarchys(result);
//    }

    public List<Module> findAllByActifs(Boolean[] actifs) {
        log.debug("Request to get All Module");
        QModule qModule = QModule.module;
        WhereClauseBuilder builder = new WhereClauseBuilder()
                .optionalAnd(actifs, () -> qModule.active.in(actifs));
        List<Module> result = (List<Module>) moduleRepository.findAll(builder);
        return result;
    }

    @Transactional(
            readOnly = true
    )
    public Boolean exists(String code) {
        QModule qModule = QModule.module;
        WhereClauseBuilder builder = new WhereClauseBuilder()
                .optionalAnd(code, () -> qModule.codeModule.eq(code));
        Boolean result = moduleRepository.exists(builder);
        return result;
    }


}
