import {SAVE_EX_REQUEST, FETCH_EX_REQUEST, UPDATE_EX_REQUEST, DELETE_EX_REQUEST, EX_SUCCESS, EX_FAILURE} from "./exerciseTypes";
import axios from 'axios';

export const saveExercise = exercise => {
    return dispatch => {
        dispatch({
            type: SAVE_EX_REQUEST
        });
        axios.post("http://localhost:8081/rest/exercise", exercise)
            .then(response => {
                dispatch(exerciseSuccess(response.data));
            })
            .catch(error => {
                dispatch(exerciseFailure(error))
            });
    };
};

// const saveExerciseRequest = () => {
//     return {
//         type: SAVE_EX_REQUEST
//     };
// };

// const saveExerciseSuccess = EX => {
//     return {
//         type: SAVE_EX_SUCCESS,
//         payload: EX
//     };
// };

// const saveExerciseFailure = error => {
//     return {
//         type: SAVE_EX_FAILURE,
//         payload: error
//     };
// }; 

const saveExerciseRequest = () => {
    return {
        type: SAVE_EX_REQUEST
    };
};

const fetchExerciseRequest = () => {
    return {
        type: FETCH_EX_REQUEST
    };
};

export const fetchExercise = exerciseId => {
    return dispatch => {
        dispatch({
            type: FETCH_EX_REQUEST
        });
        axios.get("http://localhost:8081/rest/exercise/"+exerciseId)
            .then(response => {
                dispatch(exerciseSuccess(response.data));
            })
            .catch(error => {
                dispatch(exerciseFailure(error));
            });
    };
};

const updateExerciseRequest = () => {
    return {
        type: UPDATE_EX_REQUEST
    };
};

export const updateExercise = exercise => {
    return dispatch => {
        dispatch({
            type: UPDATE_EX_REQUEST
        });
        axios.put("http://localhost:8081/rest/exercise", exercise)
            .then(response => {
                dispatch(exerciseSuccess(response.data));
            })
            .catch(error => {
                dispatch(exerciseFailure(error));
            });
    };
};

const deleteExerciseRequest = () => {
    return {
        type: DELETE_EX_REQUEST
    };
};

export const deleteExercise = exerciseId => {
    return dispatch => {
        dispatch({
            type: DELETE_EX_REQUEST
        });
        axios.delete("http://localhost:8081/rest/exercise/"+exerciseId)
            .then(response => {
                dispatch(exerciseSuccess(response.data));
            })
            .catch(error => {
                dispatch(exerciseFailure(error));
            });
    };
};

const exerciseSuccess = exercise => {
    return {
        type: EX_SUCCESS,
        payload: exercise
    };
};

const exerciseFailure = error => {
    return {
        type: EX_FAILURE,
        payload: error
    };
};
