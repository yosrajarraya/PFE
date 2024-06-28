import {
    CLOSE_ASIDE_ACCESS_GROUPE,
    RESET_ASIDE_ACCESS_GROUPE,
    SHOW_ASIDE_ACCESS_MODE_GROUPE,
    SHOW_MODAL_CONFIRMATION_GROUPE,
    CLOSE_MODAL_CONFIRMATION_GROUPE,
    GET_ACCESS_MODULE_GRP
} from "../../Constants/Groupe/GroupeAccessAside";


const initialState = {
    isOpenAccess: false,
    modeAsideAccess: '',
    selectedGroupe: null,
    accessmodule: [],
};



const GroupeAccessAsideReducer = (state = initialState, action) => {
    switch (action.type) {

        case SHOW_ASIDE_ACCESS_MODE_GROUPE:
            return {
                ...state,
                modeAsideAccess: 'ACCESS',
                isOpenAccess: true,
                selectedGroupe: action.payload.selectedGroupe,
                successCallback: action.payload.successCallback
            };

        case CLOSE_ASIDE_ACCESS_GROUPE:
            return {
                ...state,
                isOpenAccess: false,
                selectedGroupe: null,
            };
        case RESET_ASIDE_ACCESS_GROUPE:
            return {
                ...state,
                button: {
                    codeSaisie: 'test'
                },
                selectedGroupe: null,
            };

        case SHOW_MODAL_CONFIRMATION_GROUPE:
            return {
                ...state,
                isConfirmationOpen: true,
                messageToShow: action.messageToShow,
                actionBtnModalConfirmation: action.actionBtnModalConfirmation
            };
        case CLOSE_MODAL_CONFIRMATION_GROUPE:
            return {
                ...state,
                isConfirmationOpen: false,
            };
        case GET_ACCESS_MODULE_GRP:
            return {
                ...state,
                accessmodule: action.payload
            };

        default:
            return state;
    }
};

export default GroupeAccessAsideReducer;