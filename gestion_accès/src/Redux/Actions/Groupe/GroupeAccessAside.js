import {
    CLOSE_ASIDE_ACCESS_GROUPE,
    RESET_ASIDE_ACCESS_GROUPE,
    SHOW_ASIDE_ACCESS_MODE_GROUPE,
    SHOW_MODAL_CONFIRMATION_GROUPE,
    CLOSE_MODAL_CONFIRMATION_GROUPE,
    GET_ACCESS_MODULE_GRP
} from "../../Constants/Groupe/GroupeAccessAside";
import axios from "axios";
import Ressources from '../../../Helper/Ressources';

export const handleOpenAccessMode = (selectedGroupe, successCallback) => {
    return dispatch => {
        dispatch({
            type: SHOW_ASIDE_ACCESS_MODE_GROUPE,
            payload: { selectedGroupe: selectedGroupe, successCallback: successCallback }
        });
    }
}

export const handleClose = () => {
    return dispatch => {
        dispatch({
            type: CLOSE_ASIDE_ACCESS_GROUPE
        });
    }
}

export const clearForm = () => {
    return dispatch => {
        dispatch({
            type: RESET_ASIDE_ACCESS_GROUPE
        });
    }
}



export const handleOpenModalConfirmation = (messageToShow, handleBtnCancelModalConfirmation, handleBtnConfirmerModalConfirmation) => {
    return dispatch => {
        dispatch({
            type: SHOW_MODAL_CONFIRMATION_GROUPE,
            messageToShow: messageToShow,
            actionBtnModalConfirmation: { handleBtnCancelModalConfirmation, handleBtnConfirmerModalConfirmation }
        });
    }
}

export const handleCloseModalConfirmation = (successCallback) => {
    return dispatch => {
        dispatch({
            type: CLOSE_MODAL_CONFIRMATION_GROUPE,
            payload: successCallback
        });
    }
}



export const getAccessModuleGrp = () => {
    return dispatch => {
        axios.get("http://localhost:9011/gestion_acces/api/accessmodulegrps/all").then(res => {
            dispatch({
                type: GET_ACCESS_MODULE_GRP,
                payload: res.data
            })
        })
    }
}





