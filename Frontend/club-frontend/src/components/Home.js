import { render } from '@testing-library/react';
import React, { Component } from 'react';
import {Jumbotron} from 'react-bootstrap';
import authToken from '../utils/authToken';
import dacia from '../img/dacia.png';

export default function Home(){

    if(localStorage.jwtToken) {
        authToken(localStorage.jwtToken);
    }
   
    return(
        <Jumbotron id="jumbotron" className="bg-dark text-white">

            

            <div className="text-center home-h1">Training Manager</div>
            <div className="text-center homec mt-3">The Training Manager application offers you the posibility to form a training by adding exercises to it.</div>
            <div className="text-center homec mb-5">You can adjust the duration of each exercise and the application will display the calories that will be burned as well as the total calories of training.</div>
            <div><img className="dacia" src={dacia} /></div>
        </Jumbotron>
    ); 
}

