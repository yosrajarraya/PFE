import {
    GET_DELEGATION_BY_CODE,
    GET_MODULE,
    GET_LISTE_MENU,
    GET_ALL_FORM,
    ADD_NEW_DELEGATION,
    GET_ALL_DELEGATION,
    EDIT_DELEGATION,
    DELETE_DELEGATION,
    GET_ALL_UTILISATEUR

} from '../../Constants/Delegation/Delegation';

const initialState = {
  
    alldelegation: [],
    module: null,
    listMenus: null,
    allUtilisateur: [],
    forms: null,
    delegation: [],
    selectedDelegation: null,
    btnAddInstance: null,
    btnConsultInstance: null,
    btnEditInstance: null,
    btnDeleteInstance: null,
    btnEditionInstance: null,
    dateDebut: null,
    dateFin: null
};

const DelegationsReducer = (state = initialState, action) => {
    switch (action.type) {

        case GET_DELEGATION_BY_CODE:
            return {
                ...state,
                alldelegation: [...state.alldelegation, action.payload]
            };

        case EDIT_DELEGATION:
            return {
                ...state,
                alldelegation: [...state.allDelegation, action.payload]
            };
        case DELETE_DELEGATION:
            return {
                ...state,
                alldelegation: [...state.alldelegation, action.payload]
            };
        case GET_MODULE:
            return {
                ...state,
                module: action.payload
            };
        case GET_LISTE_MENU:
            return {
                ...state,
                listMenus: action.payload
            };
        case GET_ALL_FORM:
            return {
                ...state,
                forms: action.payload
            };

        case GET_ALL_DELEGATION:
            return {
                ...state,
                alldelegation: action.payload
            };
        case GET_ALL_UTILISATEUR:
            return {
                ...state,
                allUtilisateurs: action.payload
            };
        case ADD_NEW_DELEGATION:
            return {
                ...state,

                alldelegation: [...state.alldelegation, action.payload]
            };
        default:
            return state;
    }

}
export default DelegationsReducer;
