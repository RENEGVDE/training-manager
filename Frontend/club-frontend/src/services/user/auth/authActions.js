import * as AT from './authTypes';
import axios from 'axios';

export const authenticateUser = (email, password) => {
    const credentials = {
        email: email,
        password: password
    };
    return dispatch => {
        dispatch({
            type: AT.LOGIN_REQUEST
        });
        axios.post("http://localhost:8081/rest/user/authenticate", credentials)
            .then(response => {
                let token = response.data.token;
                localStorage.setItem('jwtToken', token);
                dispatch(success(true));
            })
            .catch(error => {
                dispatch(failure());
            });
    };
};

export const logoutUser = () => {
    return dispatch => {
        dispatch({
            type: AT.LOGOUT_REQUEST
        });
        localStorage.removeItem('jwtToken');
        dispatch(success(false));
    };
};

const success = isLoggedIn => {
    return {
        type: AT.SUCCESS,
        payload: isLoggedIn
    };
};

const failure = () => {
    return {
        type: AT.FAILURE,
        payload: false
    };
};

// import {LOGIN_REQUEST, LOGOUT_REQUEST, SUCCESS, FAILURE} from './authTypes';


// export const authenticateUser = (username, password) => {
//     return dispatch => {
//         dispatch(loginRequest());
//         if(username === "test" && password === "test") {
//             dispatch(success(true));
//         } else {
//             dispatch(failure());
//         }
//     };
// };

// const loginRequest = () => {
//     return {
//         type: LOGIN_REQUEST
//     };
// };

// export const logoutUser = () => {
//     return dispatch => {
//         dispatch(logoutRequest());
//         dispatch(success(false));
//     };
// };

// const logoutRequest = () => {
//     return {
//         type: LOGOUT_REQUEST
//     };
// };

// const success = isLoggedIn => {
//     return {
//         type: SUCCESS,
//         payload: isLoggedIn
//     };
// };

// const failure = () => {
//     return {
//         type: FAILURE,
//         payload: false
//     };
// }; 