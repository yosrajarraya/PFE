package com.csys.template.dto;

import com.csys.template.enumeration.EnumDTO;
import com.csys.template.enumeration.EnumMotif;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.lang.Boolean;
import java.lang.Integer;
import java.lang.String;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.validation.constraints.NotNull;

public class DelegationAccessDTO {

    private Integer numDelegation;
    private String userDelegant;
    private EnumMotif motif;
    private EnumDTO motifDTO;
    private String userCreation;
    private UtilisateurDTO utilisateurDTO;
    private Integer module;
    private LocalDate dateDebut;
    private ModuleDTO idModule;
    private LocalDate dateFin;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private LocalDateTime dateCreation;
    private Boolean declenche;
    private Boolean termine;
    private List<DetailsDelegationAccessDTO> detailsdelegationAccess;
    private List<DetailsDelegationAccessMenuDTO> detailsdelegationAccessMenu;
    private List<DetailsDelegationAccessButtonDTO> detailsdelegationAccessButton;

    public Integer getNumDelegation() {
        return numDelegation;
    }

    public void setNumDelegation(Integer numDelegation) {
        this.numDelegation = numDelegation;
    }

    public String getUserDelegant() {
        return userDelegant;
    }

    public void setUserDelegant(String userDelegant) {
        this.userDelegant = userDelegant;
    }

    public EnumMotif getMotif() {
        return motif;
    }

    public void setMotif(EnumMotif motif) {
        this.motif = motif;
    }

    public EnumDTO getMotifDTO() {
        return motifDTO;
    }

    public void setMotifDTO(EnumDTO motifDTO) {
        this.motifDTO = motifDTO;
    }

    public String getUserCreation() {
        return userCreation;
    }

    public void setUserCreation(String userCreation) {
        this.userCreation = userCreation;
    }

    public Integer getModule() {
        return module;
    }

    public void setModule(Integer module) {
        this.module = module;
    }

    public ModuleDTO getIdModule() {
        return idModule;
    }

    public void setIdModule(ModuleDTO idModule) {
        this.idModule = idModule;
    }

    public UtilisateurDTO getUtilisateurDTO() {
        return utilisateurDTO;
    }

    public void setUtilisateurDTO(UtilisateurDTO utilisateurDTO) {
        this.utilisateurDTO = utilisateurDTO;
    }

 

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public String getDateCreation() {
        return DateTimeFormatter.ofPattern("dd/MMMM/yyyy HH:mm").format(dateCreation);
    }

    public void setDateCreation(LocalDateTime dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Boolean getDeclenche() {
        return declenche;
    }

    public void setDeclenche(Boolean declenche) {
        this.declenche = declenche;
    }

    public Boolean getTermine() {
        return termine;
    }

    public void setTermine(Boolean termine) {
        this.termine = termine;
    }

    public List<DetailsDelegationAccessDTO> getDetailsdelegationAccess() {
        return detailsdelegationAccess;
    }

    public void setDetailsdelegationAccess(List<DetailsDelegationAccessDTO> detailsdelegationAccess) {
        this.detailsdelegationAccess = detailsdelegationAccess;
    }

    public List<DetailsDelegationAccessMenuDTO> getDetailsdelegationAccessMenu() {
        return detailsdelegationAccessMenu;
    }

    public void setDetailsdelegationAccessMenu(List<DetailsDelegationAccessMenuDTO> detailsdelegationAccessMenu) {
        this.detailsdelegationAccessMenu = detailsdelegationAccessMenu;
    }

    public List<DetailsDelegationAccessButtonDTO> getDetailsdelegationAccessButton() {
        return detailsdelegationAccessButton;
    }

    public void setDetailsdelegationAccessButton(List<DetailsDelegationAccessButtonDTO> detailsdelegationAccessButton) {
        this.detailsdelegationAccessButton = detailsdelegationAccessButton;
    }

}
