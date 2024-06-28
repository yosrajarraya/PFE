import { combineReducers } from 'redux';
import DemandesReducer from './Demande/Demande';
import DemandeAsideReducer from './Demande/DemandeAside'
import MenuTabsReducer from './MenuTabs/MenuTabs';
import MenuReducer from './Menu/Menu';
import HeaderReducer from './Header/Header';
import { intlReducer } from 'react-intl-redux';
import gestionnairesReducer from './Gestionnaire/Gestionnaire';
import gestionnaireAsideReducer from './Gestionnaire/GestionnaireAside';
import GroupeAsideReducer from './Groupe/GroupeAside'
import GroupeAccessAsideReducer from './Groupe/GroupeAccessAside'
import UtilisateurAsideReducer from './Utilisateur/UtilisateurAside'
import UtilisateurAccessAsideReducer from './Utilisateur/UtilisateurAccessAside'
import UtilisateursReducer from './Utilisateur/Utilisateur';
import GroupesReducer from './Groupe/Groupe';
import ModalReducerImpression from './ComponentTable/ModalImpression';
import LoginReducer from './Login/Login';
import DelegationAsideReducer from './Delegation/DelegationAside';
import DelegationsReducer from './Delegation/Delegation';
import ModuleReducer from './Module/Module';
export default combineReducers({
    MenuTabsReducer, MenuReducer, HeaderReducer, intl: intlReducer
    , ModalReducerImpression,
    DemandesReducer, DemandeAsideReducer,
    gestionnairesReducer, gestionnaireAsideReducer, GroupeAsideReducer, GroupesReducer,GroupeAccessAsideReducer,
     UtilisateurAsideReducer, UtilisateursReducer,UtilisateurAccessAsideReducer,ModuleReducer,DelegationAsideReducer,DelegationsReducer,
    LoginReducer
});