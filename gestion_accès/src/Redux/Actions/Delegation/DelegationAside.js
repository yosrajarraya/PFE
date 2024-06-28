import {
    CLOSE_ASIDE_DELEGATION,
    RESET_ASIDE_DELEGATION,
    SHOW_ASIDE_ADD_MODE_DELEGATION,
    SHOW_ASIDE_EDIT_MODE_DELEGATION,
    SHOW_ASIDE_DELETE_MODE_DELEGATION,
    SHOW_ASIDE_CONSULT_MODE_DELEGATION,
    SHOW_MODAL_CONFIRMATION_DELEGATION,
    CLOSE_MODAL_CONFIRMATION_DELEGATION,
    DATA_TREE_LIST,
     GET_GROUP,
     GET_ALL_MOTIF,
     GET_COMPTEUR_DELEGATION,
     GET_MODULES_FOR_USER,
     FETCH_MODULES_FOR_USER
} from "../../Constants/Delegation/DelegationAside";
import axios from "axios";
import Ressources from '../../../Helper/Ressources';

export const handleOpenAddMode = (successCallback) => {
    return dispatch => {
        dispatch({
            type: SHOW_ASIDE_ADD_MODE_DELEGATION,
            payload: successCallback
        });
    }
}

export const handleOpenConsultMode = (selectedDelegation) => {
    return dispatch => {
        dispatch({
            type: SHOW_ASIDE_CONSULT_MODE_DELEGATION,
            payload: selectedDelegation
        });
    }
}

export const handleOpenDeleteMode = (selectedDelegation, successCallback) => {
    return dispatch => {
        dispatch({
            type: SHOW_ASIDE_DELETE_MODE_DELEGATION,
            payload: {selectedDelegation: selectedDelegation, successCallback: successCallback}
        });
    }
}

export const handleOpenEditMode = (selectedDelegation, successCallback) => {
    return dispatch => {
        dispatch({
            type: SHOW_ASIDE_EDIT_MODE_DELEGATION,
            payload: {selectedDelegation: selectedDelegation, successCallback: successCallback}
        });
    }
}

export const handleClose = () => {
    return dispatch => {
        dispatch({
            type: CLOSE_ASIDE_DELEGATION
        });
    }
}

export const clearForm = () => {
    return dispatch => {
        dispatch({
            type: RESET_ASIDE_DELEGATION
        });
    }
}



export const handleOpenModalConfirmation = (messageToShow, handleBtnCancelModalConfirmation, handleBtnConfirmerModalConfirmation) => {
    return dispatch => {
        dispatch({
            type: SHOW_MODAL_CONFIRMATION_DELEGATION,
            messageToShow: messageToShow,
            actionBtnModalConfirmation: {handleBtnCancelModalConfirmation, handleBtnConfirmerModalConfirmation}
        });
    }
}

export const handleCloseModalConfirmation = (successCallback) => {
    return dispatch => {
        dispatch({
            type: CLOSE_MODAL_CONFIRMATION_DELEGATION,
            payload: successCallback
        });
    }
}


export const handleUpdateDataTreeList = (data) => {
    return dispatch => {
        dispatch({
            type: DATA_TREE_LIST,
            payload: data
        });
    }
}

 export const getGroup = () => {
     return dispatch => {
         axios.get(`${Ressources.CoreUrlB}/${Ressources.Securite.api}/${Ressources.Securite.Groupes}`).then (res =>{
             dispatch({
                 type:GET_GROUP,
                 payload:res.data            })
         })
     }
 }


 
 export const getMotifs = () => {
    return dispatch => {
        axios.get(`http://localhost:9011/gestion_acces/api/delegationaccess/motif`).then (res =>{
            dispatch({
                type:GET_ALL_MOTIF,
                payload:res.data            })
        })
    }
}

export const fetchModulesForUser = (username) => {
    return async (dispatch) => {
        try {
            const response = await axios.get(`http://localhost:9011/gestion_acces/api/utilisateurs?username=${username}`);
            const accessModuleUsers = response.data.accessModuleUsers;
            dispatch({ type: FETCH_MODULES_FOR_USER, payload: accessModuleUsers });
        } catch (error) {
            console.error('Error fetching modules for user:', error);
        }
    };
};
