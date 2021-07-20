import React, { Component } from 'react';

import {connect} from 'react-redux';
import {deleteExercise} from '../services/index';

import {Card, Table, Image, ButtonGroup, Button} from 'react-bootstrap';
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';
import {faList, faEdit, faTrash, faCheck, faCheckSquare} from '@fortawesome/free-solid-svg-icons';
import axios from 'axios';
import MyToast from './MyToast';
import {Link} from 'react-router-dom';

class Training extends Component {

    constructor(props) {
        super(props);
        this.state = {
            exercises : [],
            allcalories: []
        };
    }

    componentDidMount() {
        this.findAllExercises();
        this.findAllCalories();
    }

    sortData = () => {
        setTimeout(() => {
            this.state.sortDir === "asc" ? this.setState({sortDir: "desc"}) : this.setState({sortDir: "asc"});
            this.findAllExercises(this.state.currentPage);
        }, 500);
    };


    findAllExercises() {
        axios.get("http://localhost:8081/rest/exercise")
            .then(response => response.data)
            .then((data) => {
                this.setState({exercises: data});
            })
            .catch(error=>{
                console.log(error)
                localStorage.removeItem('jwtToken');
                this.props.history.push('/');
            })
    };

    findAllCalories() {
        axios.get("http://localhost:8081/rest/exercise/allcalories")
            .then(response => response.data)
            .then((data) => {
                this.setState({allcalories: data});
            })
            .catch(error=>{
                console.log(error)
                localStorage.removeItem('jwtToken');
                this.props.history.push('/');
            })
    };

    // findAllExercises() {
    //     fetch("http://localhost:8081/rest/exercise")
    //         .then(response => response.json)
    //         .then((data) => {
    //             this.setState({exercises: data});
    //         });
    // };

    // deleteExercise = (exerciseId) => {
    //     axios.delete("http://localhost:8081/rest/exercise/"+exerciseId)
    //         .then(response => {
    //             if(response.data != null) {
    //                 this.setState({"show":true});
    //                 setTimeout(() => this.setState({"show":false}), 3000);
    //                 this.setState({
    //                     exercises: this.state.exercises.filter(exercise => exercise.id !== exerciseId)
    //                 });
    //             } else {
    //                 this.setState({"show":false});
    //             }
    //         });
    // };

    deleteExercise = (exerciseId) => {
        this.props.deleteExercise(exerciseId);
        setTimeout(() => {
            if(this.props.exerciseObject != null) {
                this.setState({"show":true});
                setTimeout(() => this.setState({"show":false}), 3000);
                this.findAllExercises(this.state.currentPage);
            } else {
                this.setState({"show":false});
            }
        }, 1000);
    };

    render(){
        return(
        <div>
            <div style={{"display":this.state.show ? "block" : "none"}}>
            <MyToast show = {this.state.show} message = {"Exercise Deleted Successfully."} type = {"danger"}/>
            </div>
            <Card className={"border border-dark bg-dark text-white"}>
                <Card.Header className="d-flex justify-content-between align-items-center">
                    <div><FontAwesomeIcon icon={faList} /> Training</div>
                    <div class="row"><div className="col-xs-6">Training Calories: </div><div className="allCalories col-xs-6"> {Math.round(this.state.allcalories *10)/10}</div></div>
                </Card.Header>
                <Card.Body>
                    <Table striped bordered hover variant="dark">
                        <thead>
                            <tr>
                            <th>Name</th>
                            <th>Duration</th>
                            <th>Position</th>
                            <th>Calories</th>
                            <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                this.state.exercises.length === 0 ?
                                <tr align="center">
                                <td colSpan="5">No Exercises Available</td>
                                </tr> :
                                this.state.exercises.map((exercise) => (
                                <tr key={exercise.id}>
                                    <td>
                                        <FontAwesomeIcon icon={faCheck} /> {exercise.exId.exName}
                                    </td>
                                    <td>{exercise.trDuration}</td>
                                    <td>{exercise.trPosition}</td>
                                    <td>{exercise.totCalories}</td>
                                    <td>
                                        <ButtonGroup>
                                            <Link to={"edit/"+exercise.id} id="editBtn" className="btn btn-sm btn-outline-primary"><FontAwesomeIcon icon={faEdit} /></Link>{' '}
                                            <Button size="sm" variant="outline-danger" onClick={this.deleteExercise.bind(this, exercise.id)}><FontAwesomeIcon icon={faTrash} /></Button>
                                        </ButtonGroup>
                                    </td>
                                </tr>
                                ))
                            }
                        </tbody>
                    </Table>
                </Card.Body>
            </Card>
        </div>
        );
    }
}

const mapStateToProps = state => {
    return {
        exerciseObject: state.exercise
    };
};

const mapDispatchToProps = dispatch => {
    return {
        deleteExercise: (exerciseId) => dispatch(deleteExercise(exerciseId))
    };
};

export default connect(mapStateToProps, mapDispatchToProps)(Training);

