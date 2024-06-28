import axios from 'axios';
import Ressources from '../../../Helper/Ressources';
import { LOGIN, LOGOUT } from "../../Constants/Login/Login";


export const signIn = (username, password) => {
    return dispatch => {
        return new Promise((resolve, reject) => {
            axios.post(`${Ressources.CoreUrlB}/gestion_acces/api/signin`, { username, password })
                .then(res => {
                    console.log(res.data);
                    const token = res.data.token;
                    localStorage.setItem('token', token);
                    setAuthToken(token);
                    dispatch({
                        type: LOGIN,
                        payload: { username, token }  
                    });
                    resolve(res.data);
                })
                .catch(error => {
                    reject(error); 
                });
        });
    };
};
const setAuthToken = token => {
    if (token) {
      axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;
    } else {
      delete axios.defaults.headers.common['Authorization'];
    }
  };


export const logOut = () => {
    return dispatch => {
        return new Promise((resolve, reject) => {
            axios.post(`${Ressources.CoreUrlB}/gestion_acces/api/utilisateurs/logout`)
                .then(res => {
                    dispatch({
                        type: LOGOUT,
                        payload: ""
                    });
                    resolve(true);
                }).catch(function (error) {
                    reject(error);
                });
        });
    }
}




