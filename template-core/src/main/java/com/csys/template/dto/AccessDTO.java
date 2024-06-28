package com.csys.template.dto;

import java.lang.Integer;
import java.lang.String;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AccessDTO {
//    private String code; 
    private Integer id; 
    private Integer idParent;
    private String designation;
    private String logo;
//    private String codeParent;
    private String type;

    public Integer getId() {  
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdParent() {
        return idParent;
    }

//    public String getCode() {
//        return code;
//    }
//
//    public void setCode(String code) {
//        this.code = code;
//    }
    public void setIdParent(Integer idParent) {
        this.idParent = idParent;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }


//    public String getCodeParent() {
//        return codeParent;
//    }
//
//    public void setCodeParent(String codeParent) {
//        this.codeParent = codeParent;
//    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    
    
 
}
