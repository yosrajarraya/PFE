import {
    GET_ALL_MODULES_REQUEST,
    GET_ALL_MODULES_SUCCESS,
    GET_ALL_MODULES_FAILURE,
  } from '../../Constants/Module/Module';
  
  const initialState = {
    modules: [],
    loading: false,
    error: null,
  };
  
  const ModuleReducer = (state = initialState, action) => {
    switch (action.type) {
      case GET_ALL_MODULES_REQUEST:
        return {
          ...state,
          loading: true,
          error: null,
        };
      case GET_ALL_MODULES_SUCCESS:
        return {
          ...state,
          loading: false,
          modules: action.payload,
        };
      case GET_ALL_MODULES_FAILURE:
        return {
          ...state,
          loading: false,
          error: action.payload,
        };
      default:
        return state;
    }
  };
  
  export default ModuleReducer;
  