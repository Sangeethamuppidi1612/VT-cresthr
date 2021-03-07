import {CREATE_DEPARTMENT, GET_DEPARTMENTS, SET_DEPARTMENTS, UPDATE_DEPARTMENT} from "../constants";

const defaultState = {
  loaded : false,
  data : null,
  saving : false,
};

export default (state = defaultState, action) => {
  switch (action.type) {
    case GET_DEPARTMENTS :
      return Object.assign({}, state, { loaded : false });

    case SET_DEPARTMENTS :
      return Object.assign({}, state, { loaded : true, data : action.payload.departments,  });

    case CREATE_DEPARTMENT :
      return Object.assign({}, state, { saving : true });

    case UPDATE_DEPARTMENT :
      if (state.data === null)
        return state;
        
      let index = -1;
      if(action.payload.department && action.payload.department.id) {

      index = state.data.findIndex(e => e.id === action.payload.department.id);
      const newDepartments = (index === -1) ?
          [...state.data, action.payload.department] :
          [...state.data.slice(0, index), action.payload.department, ...state.data.slice(index + 1)];
      return Object.assign({}, state, { data : newDepartments, saving : false });
      } 
      else return state;

    default :
      return state;
  }
};