import {SAVE_EX_REQUEST, FETCH_EX_REQUEST, UPDATE_EX_REQUEST, DELETE_EX_REQUEST, EX_SUCCESS, EX_FAILURE} from "./exerciseTypes";

const initialState = {
    exercise: '', error: ''
};

const reducer = (state = initialState, action) => {
    switch(action.type) {
        case SAVE_EX_REQUEST:
        case FETCH_EX_REQUEST:
        case UPDATE_EX_REQUEST:
        case DELETE_EX_REQUEST:
            return {
                ...state
            };
        case EX_SUCCESS:
            return {
                exercise: action.payload,
                error: ''
            };
        case EX_FAILURE:
            return {
                exercise: '',
                error: action.payload
            };
        default: return state;
    }
};

export default reducer; 