import axios from 'axios';

import {
    GET_ALL_MODULES_REQUEST,
    GET_ALL_MODULES_SUCCESS,
    GET_ALL_MODULES_FAILURE,
  } from '../../Constants/Module/Module';

export const getAllModulesRequest = () => ({
  type: GET_ALL_MODULES_REQUEST,
});

export const getAllModulesSuccess = (modules) => ({
  type: GET_ALL_MODULES_SUCCESS,
  payload: modules,
});

export const getAllModulesFailure = (error) => ({
  type: GET_ALL_MODULES_FAILURE,
  payload: error,
});
export const getAllModules = () => {
    return (dispatch) => {
      dispatch(getAllModulesRequest());
      axios.get('http://localhost:9011/gestion_acces/api/modules/filtre') 
        .then((response) => {
          const modules = response.data; 
          dispatch(getAllModulesSuccess(modules));
        })
        .catch((error) => {
          dispatch(getAllModulesFailure(error.message));
        });
    };
  };