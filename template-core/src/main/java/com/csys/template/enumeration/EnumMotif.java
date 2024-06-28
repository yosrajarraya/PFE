/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.csys.template.enumeration;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Administrateur
 */
public enum EnumMotif {
    V("VACATION"),
    SL("SICK LEAVE"),
    BT("BUSINESS TRIP"),
    O("OTHER");

    private String name;

    //Constructeur
    EnumMotif(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public static final Map<EnumMotif, EnumDTO> MOTIF = new HashMap<>();

    static {
        for (EnumMotif e : values()) {
            EnumDTO codeDesignationDTO = new EnumDTO();
            codeDesignationDTO.setCode(e);
            codeDesignationDTO.setDesignation(e.name);
            MOTIF.put(e, codeDesignationDTO);
        }
    }

    public static EnumDTO valueOfLabel(EnumMotif label) {
        return MOTIF.get(label);
    }

}
