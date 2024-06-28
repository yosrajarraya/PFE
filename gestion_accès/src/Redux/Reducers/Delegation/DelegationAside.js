import {
    CLOSE_ASIDE_DELEGATION,
    RESET_ASIDE_DELEGATION,
    SHOW_ASIDE_ADD_MODE_DELEGATION,
    SHOW_ASIDE_DELETE_MODE_DELEGATION,
    SHOW_ASIDE_EDIT_MODE_DELEGATION,
    GET_COMPTEUR_DELEGATION,
    SHOW_ASIDE_CONSULT_MODE_DELEGATION,
    SHOW_MODAL_CONFIRMATION_DELEGATION,
    CLOSE_MODAL_CONFIRMATION_DELEGATION,
    DATA_TREE_LIST,
    GET_GROUP,
    GET_MODULES_FOR_USER,
    GET_ALL_MOTIF,
    FETCH_MODULES_FOR_USER
} from "../../Constants/Delegation/DelegationAside";

const initialState = {
    isOpen: false,
    modeAside: '',
    compteurDelegation: '',
    selectedDelegation: null,
    dataTreeList: [],
    group: null,

    allMotif: [],
    filteredModules: []
};

const DelegationAsideReducer = (state = initialState, action) => {
    switch (action.type) {
        case SHOW_ASIDE_ADD_MODE_DELEGATION:
            return {
                ...state,
                modeAside: 'ADD',
                isOpen: true,
                selectedDelegation: null,
                successCallback: action.payload
            };
        case SHOW_ASIDE_DELETE_MODE_DELEGATION:
            return {
                ...state,
                modeAside: 'DELETE',
                isOpen: true,
                selectedDelegation: action.payload.selectedDelegation,
                successCallback: action.payload.successCallback
            };
        case SHOW_ASIDE_EDIT_MODE_DELEGATION:
            return {
                ...state,
                modeAside: 'EDIT',
                isOpen: true,
                selectedDelegation: action.payload.selectedDelegation,
                successCallback: action.payload.successCallback
            };
        case CLOSE_ASIDE_DELEGATION:
            return {
                ...state,
                isOpen: false,
                selectedDelegation: null,
            };
        case RESET_ASIDE_DELEGATION:
            return {
                ...state,
                form: {
                    codeSaisie: 'test'
                },
                selectedDelegation: null,
            };

        case GET_COMPTEUR_DELEGATION:
            return {
                ...state,
                compteurDelegation: action.payload
            };
        case SHOW_ASIDE_CONSULT_MODE_DELEGATION:
            return {
                ...state,
                modeAside: 'CONSULT',
                isOpen: true,
                selectedDelegation: action.payload
            };
        case SHOW_MODAL_CONFIRMATION_DELEGATION:
            return {
                ...state,
                isConfirmationOpen: true,
                messageToShow: action.messageToShow,
                actionBtnModalConfirmation: action.actionBtnModalConfirmation
            };
        case CLOSE_MODAL_CONFIRMATION_DELEGATION:
            return {
                ...state,
                isConfirmationOpen: false,
            };
        case DATA_TREE_LIST:
            return {
                ...state,
                dataTreeList: action.payload
            };
        case GET_GROUP: //GROUP
            return {
                ...state,
                group: action.payload
            };

        case GET_MODULES_FOR_USER:
            return {
                ...state,
                modulesForUser: action.payload
            };

        case GET_ALL_MOTIF:
            return {
                ...state,
                allMotif: action.payload
            };
        case FETCH_MODULES_FOR_USER:
            return {
                ...state,
                filteredModules: action.payload
            };[]
        default:
            return state;
    }
};

export default DelegationAsideReducer;