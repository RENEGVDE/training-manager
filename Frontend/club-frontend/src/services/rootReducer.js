import {combineReducers} from 'redux';
import userReducer from './user/userReducer';
import exerciseReducer from './exercise/exerciseReducer';
import authReducer from './user/auth/authReducer';

const rootReducer = combineReducers({
    user: userReducer,
    exercise: exerciseReducer,
    auth: authReducer
});

export default rootReducer;