import axios from 'axios';
import Ressources from '../../../Helper/Ressources';
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





export const getModule = (username) => {
    return dispatch => {
        axios.get(`${Ressources.CoreUrlB}/${Ressources.Securite.api}/${Ressources.Securite.accessmoduleusers}/access?username=${username}`).then(res => {
            dispatch({
                type: GET_MODULE,
                payload: res.data
            })
        })
    }
}


export const getAllMenu = (username) => {
    return dispatch => {
        axios.get(`${Ressources.CoreUrlB}/${Ressources.Securite.api}/${Ressources.Securite.accessmenuusers}/access?username=${username}`).then(res => {
            dispatch({
                type: GET_LISTE_MENU,
                payload: res.data
            })
        })
    }
}
export const getAllForm = (username) => {
    return dispatch => {
        axios.get(`${Ressources.CoreUrlB}/${Ressources.Securite.api}/${Ressources.Securite.accessbuttonusers}/access?username=${username}`).then(res => {
            dispatch({
                type: GET_ALL_FORM,
                payload: res.data
            })
        })
    }
}

export const getAllUtilisateur = () => {
    return dispatch => {
        axios.get(`${Ressources.CoreUrlB}/${Ressources.Securite.api}/${Ressources.Securite.Utilisateur}/filtre`).then(res => {
            dispatch({
                type: GET_ALL_UTILISATEUR,
                payload: res.data
            })
        })
    }
}


export const addNewDelegation = (data) => {
    return dispatch => {
        return new Promise((resolve, reject) => {
            axios.post(`${Ressources.CoreUrlB}/${Ressources.Securite.api}/${Ressources.Securite.Delegation}`, data)
                .then(res => {
                    dispatch({
                        type: ADD_NEW_DELEGATION,
                        payload: res.data
                    });

                    resolve(res.data);
                }).catch(function (error) {
                    reject(error);
                });
        });
    }
};




export const getAllDelegations = (dataGrid) => {
    return dispatch => {
        dataGrid.instance.beginCustomLoading();
        axios.get(`${Ressources.CoreUrlB}/${Ressources.Securite.api}/${Ressources.Securite.Delegation}/filter`).then(res => {
            dispatch({
                type: GET_ALL_DELEGATION,
                payload: res.data
            });
            dataGrid.instance.endCustomLoading();
        })
    }
};
export const editeDelegation = (data) => {
    return dispatch => {
        return new Promise((resolve, reject) => {
            axios.put(`${Ressources.CoreUrlB}/${Ressources.Securite.api}/${Ressources.Securite.Delegation}`, data)
                .then(res => {
                    dispatch({
                        type: EDIT_DELEGATION,
                        payload: data
                    });
                    resolve(true);
                }).catch(function (error) {
                    reject(error);
                });
        });
    }
};

export const getDelegationbyCode = (numDelegation) => {
    return dispatch => {
        return new Promise((resolve, reject) => {
            axios.get(`${Ressources.CoreUrlB}/${Ressources.Securite.api}/${Ressources.Securite.Delegation}?delegationaccess=${numDelegation}`)
                .then(res => {
                    dispatch({
                        type: GET_DELEGATION_BY_CODE,
                        payload: res.data
                    });
                    resolve(res.data);
                });
        });
    }
};

export const deleteDelegation = (numDelegation) => {
    return dispatch => {
        return new Promise((resolve, reject) => {
            axios.delete(`${Ressources.CoreUrlB}/${Ressources.Securite.api}/${Ressources.Securite.Delegation}?delegationaccess=${numDelegation.numDelegation}`)
                .then(res => {
                    dispatch({
                        type: DELETE_DELEGATION,
                        payload: numDelegation
                    });
                    resolve(true);
                }).catch(function (error) {
                    reject(error);
                });
        });
    }
};

