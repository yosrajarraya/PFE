package com.csys.template.factory;

import com.csys.template.domain.Module;
import com.csys.template.dto.AccessDTO;
import com.csys.template.dto.ModuleDTO;
import java.util.ArrayList;
import java.util.List;

public class ModuleFactory {

    public static ModuleDTO moduleToModuleDTO(Module module) {
        ModuleDTO moduleDTO = new ModuleDTO();
        moduleDTO.setIdModule(module.getIdModule());
        moduleDTO.setCodeModule(module.getCodeModule());
        moduleDTO.setDesignation(module.getDesignation());
        moduleDTO.setActive(module.getActive());
        moduleDTO.setVersionDatabase(module.getVersionDatabase());
        moduleDTO.setUrlWeb(module.getUrlWeb());

        if (module.getMenus() != null) {
            moduleDTO.setMenuList(MenuFactory.menuToMenuDTOs(module.getMenus()));
        }
       
        return moduleDTO;
    }

    public static Module moduleDTOToModule(ModuleDTO moduleDTO, Module module) {
        if (module == null) {
            module = new Module();
            module.setIdModule(moduleDTO.getIdModule());
            module.setCodeModule(moduleDTO.getCodeModule());
        }

        module.setDesignation(moduleDTO.getDesignation());
        module.setActive(moduleDTO.getActive());
        module.setVersionDatabase(moduleDTO.getVersionDatabase());
        module.setUrlWeb(moduleDTO.getUrlWeb());
        // module.setAccessModuleGrpList(moduleDTO.getAccessModuleGrpList());
        // module.setAccessModuleUserList(moduleDTO.getAccessModuleUserList());
        // module.setMenuList(moduleDTO.getMenuList());
        // module.setAccessButtonGrpList(moduleDTO.getAccessButtonGrpList());
        return module;
    }

    public static List<ModuleDTO> moduleToModuleDTOs(List<Module> modules) {
        List<ModuleDTO> modulesDTO = new ArrayList<>();
        modules.forEach(x -> {
            modulesDTO.add(moduleToModuleDTO(x));
        });
        return modulesDTO;
    }

//    public static List<AccessDTO> moduleToAccessDTOWithoutHirarchys(List<Module> modules) {
//        List<AccessDTO> accessDTOs = new ArrayList<>();
//        modules.forEach(x -> {
//            AccessDTO accessDTO = new AccessDTO();
//            accessDTO.setId(x.getIdModule());
////            accessDTO.setCode(x.getCodeModule());
//            accessDTO.setDesignation(x.getDesignation());
//            accessDTO.setIdParent(0);
////              accessDTO.setCodeParent(null);
//            accessDTO.setType("module");
//            accessDTOs.add(accessDTO);
//            if (x.getMenus() != null) {
//                accessDTOs.addAll(MenuFactory.menuToAccessDTOWithoutHirarchys(x.getMenus(), accessDTO));
//            }
//        });
//        return accessDTOs;
//    }

}
