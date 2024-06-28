package com.csys.template.factory;

import com.csys.template.domain.Clinique;
import com.csys.template.domain.Utilisateur;
import com.csys.template.dto.CliniqueDTO;
import java.util.ArrayList;
import java.util.List;

public class CliniqueFactory {

    public static CliniqueDTO cliniqueToCliniqueDTO(Clinique clinique) {
        CliniqueDTO cliniqueDTO = new CliniqueDTO();
        cliniqueDTO.setIdClinique(clinique.getIdClinique());
        cliniqueDTO.setName(clinique.getName());

        return cliniqueDTO;
    }

    public static Clinique cliniqueDTOToClinique(CliniqueDTO cliniqueDTO) {

        Clinique clinique = new Clinique();
        clinique.setIdClinique(cliniqueDTO.getIdClinique());
        clinique.setName(cliniqueDTO.getName());

//List<Utilisateur> utilisateur = new ArrayList<>();
//    cliniqueDTO.getUtilisateurs().forEach(y->{
//    Utilisateur utilisateurs= UtilisateurFactory.utilisateurDTOToUtilisateur(y, null);
//    utilisateur.add(utilisateurs);
//   
//    });
//    if (clinique.getUtilisateurs()!=null){
//    clinique.getUtilisateurs().clear();
//    clinique.getUtilisateurs().addAll(utilisateur);
//    }
//    else{
//    clinique.setUtilisateurs(utilisateur);
//    }
            return clinique;
    }

    public static List<CliniqueDTO> cliniqueToCliniqueDTOs(List<Clinique> cliniques) {
        List<CliniqueDTO> cliniquesDTO = new ArrayList<>();
        cliniques.forEach(x -> {
            cliniquesDTO.add(cliniqueToCliniqueDTO(x));
        });
        return cliniquesDTO;
    }
}
