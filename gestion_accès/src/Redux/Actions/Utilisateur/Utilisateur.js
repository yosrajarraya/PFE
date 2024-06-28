import axios from 'axios';
import Ressources from '../../../Helper/Ressources';
import { GET_ALL_UTILISATEUR,GET_GROUP_USER,GET_IMPRIME, ADD_NEW_UTILISATEUR, DELETE_UTILISATEUR, EDIT_UTILISATEUR, GET_UTILISATEUR_BY_USER_NAME, GET_MODULE, GET_MENU, GET_BUTTON, GET_UTILISATEUR } from "../../Constants/Utilisateur/Utilisateur";

export const getAllUtilisateurs = (dataGrid) => {
    return dispatch => {
        dataGrid.instance.beginCustomLoading();
        axios.get(`${Ressources.CoreUrlB}/${Ressources.Securite.api}/${Ressources.Securite.Utilisateur}/filtre`).then(res => {
            dispatch({
                type: GET_ALL_UTILISATEUR,
                payload: res.data
            });
            dataGrid.instance.endCustomLoading();
        })
    }
};
export const getModule = () => {
    return dispatch => {
    
        axios.get("http://localhost:9011/gestion_acces/api/modules/filtre").then(res => {
            dispatch({
                type: GET_MODULE,
                payload: res.data
            })
        })
    }
}
export const getMenu = () => {
    return dispatch => {
        axios.get("http://localhost:9011/gestion_acces/api/menus/all").then(res => {
            dispatch({
                type: GET_MENU,
                payload: res.data
            })
        })
    }
}
export const getButton = () => {
    return dispatch => {
        axios.get("http://localhost:9011/gestion_acces/api/buttons/all").then(res => {
            dispatch({
                type: GET_BUTTON,
                payload: res.data
            })
        })
    }
}
export const getGroup = () => {
    return dispatch => {
        axios.get("http://localhost:9011/gestion_acces/api/groupes").then(res => {
            dispatch({
                type: GET_UTILISATEUR,
                payload: res.data
            })
        })
    }
}
export const getUtilisateurByCode = (username) => {
    return dispatch => {
        return new Promise((resolve, reject) => {
            axios.get(`${Ressources.CoreUrlB}/${Ressources.Securite.api}/${Ressources.Securite.Utilisateur}?username=${username}`)
                .then(res => {
                    dispatch({
                        type: GET_UTILISATEUR_BY_USER_NAME,
                        payload: res.data
                    });
                    resolve(res.data);
                });
        });
    }
};

//ajouterDemande
//supprimerDemande
//getdemandebycode

export const imprime = () => {
    return dispatch => {
        return axios.get("http://localhost:9011/gestion_acces/api/imprimerUtilisateur", {
            responseType: 'blob'
        }).then(res => {
            dispatch({
                type: GET_IMPRIME,
                payload: res.data // Use res.data to access the blob
            });
            return res.data; // Return the blob so it can be used in the component
        });
    }
}
export const addNewUtilisateur = (data) => {
    return dispatch => {
        return new Promise((resolve, reject) => {
            axios.post(`${Ressources.CoreUrlB}/${Ressources.Securite.api}/${Ressources.Securite.Utilisateur}`, data)
                .then(res => {
                    dispatch({
                        type: ADD_NEW_UTILISATEUR,
                        payload: res.data
                    });

                    resolve(res.data);
                }).catch(function (error) {
                    reject(error);
                });
        });
    }
};
export const addgroupusers = (group) => {
    return dispatch => {
        return new Promise((resolve, reject) => {
            axios.post(`${Ressources.CoreUrlB}/${Ressources.Securite.api}/${Ressources.Securite.GroupUser}`, group)
                .then(res => {
                    dispatch({
                        type: GET_GROUP_USER,
                        payload: res.group
                    });

                    resolve(res.group);
                }).catch(function (error) {
                    reject(error);
                });
        });
    }
};
export const editeUtilisateur = (data) => {
    return dispatch => {
        return new Promise((resolve, reject) => {
            axios.put(`${Ressources.CoreUrlB}/${Ressources.Securite.api}/${Ressources.Securite.Utilisateur}`, data)
                .then(res => {
                    dispatch({
                        type: EDIT_UTILISATEUR,
                        payload: data
                    });
                    resolve(true);
                }).catch(function (error) {
                    reject(error);
                });
        });
    }
};
export const deleteUtilisateur = (username) => {
    return dispatch => {
        return new Promise((resolve, reject) => {
            axios.delete(`${Ressources.CoreUrlB}/${Ressources.Securite.api}/${Ressources.Securite.Utilisateur}?username=${username.username}`)
                .then(res => {
                    dispatch({
                        type: DELETE_UTILISATEUR,
                        payload: username
                    });
                    resolve(true);
                }).catch(function (error) {
                    reject(error);
                });
        });
    }
};

