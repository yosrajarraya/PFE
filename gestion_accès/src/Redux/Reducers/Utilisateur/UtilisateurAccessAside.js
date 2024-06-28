import {
    CLOSE_ASIDE_ACCESS_USER,
    RESET_ASIDE_ACCESS_USER,
    SHOW_ASIDE_ACCESS_MODE_USER,
    SHOW_MODAL_CONFIRMATION_USER,
    CLOSE_MODAL_CONFIRMATION_USER,
} from "../../Constants/Utilisateur/UtilisateurAccessAside";


const initialState = {
    isOpenAccess: false,
    modeAsideAccess: '',
    selectedUtilisateur: null

};



const UtilisateurAccessAsideReducer = (state = initialState, action) => {
    switch (action.type) {

        case SHOW_ASIDE_ACCESS_MODE_USER:
            return {
                ...state,
                modeAsideAccess: 'ACCESS',
                isOpenAccess: true,
                selectedUtilisateur: action.payload.selectedUtilisateur,
                successCallback: action.payload.successCallback
            };

        case CLOSE_ASIDE_ACCESS_USER:
            return {
                ...state,
                isOpenAccess: false,
                selectedUtilisateur: null,
            };
        case RESET_ASIDE_ACCESS_USER:
            return {
                ...state,
                button: {
                    codeSaisie: 'test'
                },
                selectedUtilisateur: null,
            };

        case SHOW_MODAL_CONFIRMATION_USER:
            return {
                ...state,
                isConfirmationOpen: true,
                messageToShow: action.messageToShow,
                actionBtnModalConfirmation: action.actionBtnModalConfirmation
            };
        case CLOSE_MODAL_CONFIRMATION_USER:
            return {
                ...state,
                isConfirmationOpen: false,
            };


        default:
            return state;
    }
};

export default UtilisateurAccessAsideReducer;