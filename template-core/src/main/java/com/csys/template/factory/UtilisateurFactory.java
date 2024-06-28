package com.csys.template.factory;

import com.csys.template.domain.AccessButtonUser;
import com.csys.template.domain.AccessMenuUser;
import com.csys.template.domain.AccessModuleUser;
import com.csys.template.domain.DetailsDelegationAccess;
import com.csys.template.domain.DetailsDelegationAccessButton;
import com.csys.template.domain.DetailsDelegationAccessMenu;
import com.csys.template.domain.GroupUser;
import com.csys.template.domain.Utilisateur;
import com.csys.template.dto.UtilisateurDTO;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UtilisateurFactory {
// private final static Logger log = LoggerFactory.getLogger(GroupeFactory.class);
    public static UtilisateurDTO utilisateurToUtilisateurDTO(Utilisateur utilisateur) {
        UtilisateurDTO utilisateurDTO = new UtilisateurDTO();
        utilisateurDTO.setUsername(utilisateur.getUsername());
        utilisateurDTO.setDesignation(utilisateur.getDesignation());
        utilisateurDTO.setPassword(utilisateur.getPassword());
        utilisateurDTO.setActive(utilisateur.getActive());
        utilisateurDTO.setUserCreation(utilisateur.getUserCreation());
        utilisateurDTO.setDateCreation(utilisateur.getDateCreation());
        utilisateurDTO.setExpireCompte(utilisateur.getExpireCompte());
        utilisateurDTO.setExpirePassword(utilisateur.getExpirePassword());
        utilisateurDTO.setNbJourExpiration(utilisateur.getNbJourExpiration());
        utilisateurDTO.setNbExpirationPassword(utilisateur.getNbExpirationPassword());
        utilisateurDTO.setDateExpiration(utilisateur.getDateExpiration());
          if (utilisateur.getGroupUsers()!= null) {
            utilisateurDTO.setGroupUsers(GroupUserFactory.groupuserToGroupUserDTOs(utilisateur.getGroupUsers()));
        }
            if(utilisateur.getAccessModuleUsers()!=null){
         utilisateurDTO.setAccessModuleUsers(AccessModuleUserFactory.accessmoduleuserToAccessModuleUserDTOs(utilisateur.getAccessModuleUsers()));
        
        }
        if(utilisateur.getAccessButtonUserList()!= null){
         utilisateurDTO.setAccessButtonUsers(AccessButtonUserFactory.accessbuttonuserToAccessButtonUserDTOs(utilisateur.getAccessButtonUserList()));
        
        }
        if(utilisateur.getAccessMenuUsers()!=null){
                 utilisateurDTO.setAccessMenuUsers(AccessMenuUserFactory.accessmenuuserToAccessMenuUserDTOs(utilisateur.getAccessMenuUsers()));
        }
//           if (utilisateur.getDetailsDelegationAccessList()!= null) {
//            utilisateurDTO.setDetailsDelegationAccessList(DetailsDelegationAccessFactory.detailsdelegationaccessToDetailsDelegationAccessDTOs(utilisateur.getDetailsDelegationAccessList()));
//        }
//        if (utilisateur.getDetailsDelegationAccessButtonList()!= null) {
//            utilisateurDTO.setDetailsDelegationAccessButtonList(DetailsDelegationAccessButtonFactory.detailsdelegationaccessbuttonToDetailsDelegationAccessButtonDTOs(utilisateur.getDetailsDelegationAccessButtonList()));
//        }
//        if (utilisateur.getDetailsDelegationAccessMenuList()!= null) {
//            utilisateurDTO.setDetailsDelegationAccessMenuList(DetailsDelegationAccessMenuFactory.detailsdelegationaccessmenuToDetailsDelegationAccessMenuDTOs(utilisateur.getDetailsDelegationAccessMenuList()));
//        }
        return utilisateurDTO;
    }
    


    public static Utilisateur utilisateurDTOToUtilisateur(UtilisateurDTO utilisateurDTO, Utilisateur utilisateur,String user) {
        if (utilisateur == null) {
            utilisateur = new Utilisateur();
            utilisateur.setUsername(utilisateurDTO.getUsername());
            utilisateur.setDesignation(utilisateurDTO.getDesignation());

            utilisateur.setUserCreation(user);
            utilisateur.setDateCreation(LocalDateTime.now());
        }
        utilisateur.setPassword(utilisateurDTO.getPassword());
        utilisateur.setActive(utilisateurDTO.isActive());
        utilisateur.setExpireCompte(utilisateurDTO.getExpireCompte());
        utilisateur.setExpirePassword(utilisateurDTO.getExpirePassword());
        utilisateur.setNbJourExpiration(utilisateurDTO.getNbJourExpiration());
        utilisateur.setNbExpirationPassword(utilisateurDTO.getNbExpirationPassword());
        utilisateur.setDateExpiration(utilisateurDTO.getDateExpiration());
        List<GroupUser> groupuser = new ArrayList<>();
        if (utilisateurDTO.getGroupUsers() != null) {
            utilisateurDTO.getGroupUsers().forEach(x -> {

                GroupUser groupusere = GroupUserFactory.groupuserDTOToGroupUser(x);
                groupuser.add(groupusere);
            });
        }
        if (utilisateur.getGroupUsers() != null) {
            utilisateur.getGroupUsers().clear();
            utilisateur.getGroupUsers().addAll(groupuser);
        } else {
            utilisateur.setGroupUsers(groupuser);
        }

        List<AccessModuleUser> accessModuleUser = new ArrayList<>();
        if (utilisateurDTO.getAccessModuleUsers() != null) {
            utilisateurDTO.getAccessModuleUsers().forEach(x -> {

                AccessModuleUser accessModuleUsers = AccessModuleUserFactory.accessmoduleuserDTOToAccessModuleUser(x);
                accessModuleUser.add(accessModuleUsers);
            });
        }
        if (utilisateur.getAccessModuleUsers() != null) {
            utilisateur.getAccessModuleUsers().clear();
            utilisateur.getAccessModuleUsers().addAll(accessModuleUser);
        } else {
            utilisateur.setAccessModuleUsers(accessModuleUser);
        }

        List<AccessMenuUser> accessMenuUser = new ArrayList<>();
        if (utilisateurDTO.getAccessMenuUsers() != null) {
            utilisateurDTO.getAccessMenuUsers().forEach(x -> {
                AccessMenuUser accessMenuUsers = AccessMenuUserFactory.accessmenuuserDTOToAccessMenuUser(x);
                accessMenuUser.add(accessMenuUsers);
            });
        }
        if (utilisateur.getAccessMenuUsers() != null) {
            utilisateur.getAccessMenuUsers().clear();
            utilisateur.getAccessMenuUsers().addAll(accessMenuUser);
        } else {
            utilisateur.setAccessMenuUsers(accessMenuUser);
        }

        List<AccessButtonUser> accessButtonUser = new ArrayList<>();
        if (utilisateurDTO.getAccessButtonUsers() != null) {
        utilisateurDTO.getAccessButtonUsers().forEach(x -> {
            AccessButtonUser accessButtonUsers = AccessButtonUserFactory.accessbuttonuserDTOToAccessButtonUser(x);
            accessButtonUser.add(accessButtonUsers);

        });}
        if (utilisateur.getAccessButtonUserList() != null) {
            utilisateur.getAccessButtonUserList().clear();
            utilisateur.getAccessButtonUserList().addAll(accessButtonUser);

        } else {
            utilisateur.setAccessButtonUserList(accessButtonUser);
        }
        
//          List<DetailsDelegationAccess> detailsDelegationAcces = new ArrayList<>();
//        if (utilisateurDTO.getDetailsDelegationAccessList() != null) {
//            utilisateurDTO.getDetailsDelegationAccessList().forEach(x -> {
//
//                DetailsDelegationAccess detailsDelegationAccess = DetailsDelegationAccessFactory.detailsdelegationaccessDTOToDetailsDelegationAccess(x);
//                detailsDelegationAcces.add(detailsDelegationAccess);
//            });
//        }
//        if (utilisateur.getDetailsDelegationAccessList() != null) {
//            utilisateur.getDetailsDelegationAccessList().clear();
//            utilisateur.getDetailsDelegationAccessList().addAll(detailsDelegationAcces);
//        } else {
//            utilisateur.setDetailsDelegationAccessList(detailsDelegationAcces);
//        }
//        List<DetailsDelegationAccessMenu> detailsDelegationAccessMenu = new ArrayList<>();
//        if (utilisateurDTO.getDetailsDelegationAccessMenuList()!= null) {
//            utilisateurDTO.getDetailsDelegationAccessMenuList().forEach(x -> {
//
//                DetailsDelegationAccessMenu detailsDelegationAccessMenus = DetailsDelegationAccessMenuFactory.detailsdelegationaccessmenuDTOToDetailsDelegationAccessMenu(x);
//                detailsDelegationAccessMenu.add(detailsDelegationAccessMenus);
//            });
//        }
//        if (utilisateur.getDetailsDelegationAccessMenuList() != null) {
//            utilisateur.getDetailsDelegationAccessMenuList().clear();
//            utilisateur.getDetailsDelegationAccessMenuList().addAll(detailsDelegationAccessMenu);
//        } else {
//            utilisateur.setDetailsDelegationAccessMenuList(detailsDelegationAccessMenu);
//        }
//        List<DetailsDelegationAccessButton> detailsDelegationAccessButton = new ArrayList<>();
//        if (utilisateurDTO.getDetailsDelegationAccessButtonList()!= null) {
//            utilisateurDTO.getDetailsDelegationAccessButtonList().forEach(x -> {
//
//                DetailsDelegationAccessButton detailsDelegationAccessButtons = DetailsDelegationAccessButtonFactory.detailsdelegationaccessbuttonDTOToDetailsDelegationAccessButton(x);
//                detailsDelegationAccessButton.add(detailsDelegationAccessButtons);
//            });
//        }
//        if (utilisateur.getDetailsDelegationAccessButtonList() != null) {
//            utilisateur.getDetailsDelegationAccessButtonList().clear();
//            utilisateur.getDetailsDelegationAccessButtonList().addAll(detailsDelegationAccessButton);
//        } else {
//            utilisateur.setDetailsDelegationAccessButtonList(detailsDelegationAccessButton);
//        }
        
        return utilisateur;
    }

    public static List<UtilisateurDTO> utilisateurToUtilisateurDTOs(List<Utilisateur> utilisateurs) {
        List<UtilisateurDTO> utilisateursDTO = new ArrayList<>();
        utilisateurs.forEach(x -> {
            utilisateursDTO.add(utilisateurToUtilisateurDTO(x));
        });
        return utilisateursDTO;
    }
    
}
