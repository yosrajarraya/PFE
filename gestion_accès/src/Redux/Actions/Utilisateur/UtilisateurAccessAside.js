import {
    CLOSE_ASIDE_ACCESS_USER,
    RESET_ASIDE_ACCESS_USER,
    SHOW_ASIDE_ACCESS_MODE_USER,
    SHOW_MODAL_CONFIRMATION_USER,
    CLOSE_MODAL_CONFIRMATION_USER,
} from "../../Constants/Utilisateur/UtilisateurAccessAside";
import axios from "axios";
import Ressources from '../../../Helper/Ressources';

export const handleOpenAccessMode = (selectedUtilisateur, successCallback) => {
    return dispatch => {
        dispatch({
            type: SHOW_ASIDE_ACCESS_MODE_USER,
            payload: { selectedUtilisateur: selectedUtilisateur, successCallback: successCallback }
        });
    }
}

export const handleClose = () => {
    return dispatch => {
        dispatch({
            type: CLOSE_ASIDE_ACCESS_USER
        });
    }
}

export const clearForm = () => {
    return dispatch => {
        dispatch({
            type: RESET_ASIDE_ACCESS_USER
        });
    }
}



export const handleOpenModalConfirmation = (messageToShow, handleBtnCancelModalConfirmation, handleBtnConfirmerModalConfirmation) => {
    return dispatch => {
        dispatch({
            type: SHOW_MODAL_CONFIRMATION_USER,
            messageToShow: messageToShow,
            actionBtnModalConfirmation: { handleBtnCancelModalConfirmation, handleBtnConfirmerModalConfirmation }
        });
    }
}

export const handleCloseModalConfirmation = (successCallback) => {
    return dispatch => {
        dispatch({
            type: CLOSE_MODAL_CONFIRMATION_USER,
            payload: successCallback
        });
    }
}









