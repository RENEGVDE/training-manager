import React, { Component } from 'react'

import {Card, Form, Button, Col} from 'react-bootstrap';
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome'
import {faPlusSquare, faSave, faUndo, faList, faEdit} from '@fortawesome/free-solid-svg-icons'
import MyToast from './MyToast';
import axios from 'axios';
import {connect} from 'react-redux';
import {saveExercise} from '../services/index';

class Exercise extends Component {

    constructor(props){
        super(props);
        this.state = this.initialState;
        this.state = {
            exnames:[],
            show:false
        };
        this.exerciseChange=this.exerciseChange.bind(this);
        this.submitExercise=this.submitExercise.bind(this);
    }

    // submitExercise(event){
    //     alert(
    //         'Name: '+this.state.nameEx+ 
    //         ', Duration: '+this.state.durationEx+
    //         ', Position: '+this.state.positionEx+
    //         ', Coach: '+this.state.coachEx    
    //     );
    //     event.preventDefault();
    // }

    initialState={
        id:'',
        exId:'',
        trDuration:'',
        trPosition:''
        // totCalories:''
    };

    componentDidMount() {
        const exerciseId = +this.props.match.params.id;
        if(exerciseId) {
            this.findExerciseById(exerciseId);
        }
        this.findAllExnames();
    }

    findAllExnames = () => {
        axios.get("http://localhost:8081/rest/ex/exnames")
            .then(response => response.data)
            .then((data) => {
                this.setState({
                    exnames: [{value:'', display:'Select Exercise'}]
                        .concat(data.map(ex => {
                            return {value:ex.exId, display:ex.exName}
                        }))
                });
            });
    };

    findExerciseById=(exerciseId)=>{
        axios.get("http://localhost:8081/rest/exercise/"+exerciseId)
            .then(response => {
                if(response.data != null) {
                    this.setState({
                        id: response.data.id,
                        exId: response.data.exId.exId,
                        trDuration: response.data.trDuration,
                        trPosition: response.data.trPosition
                    });
                }
            }).catch((error) => {
                console.error("Error - "+error);
            });
    };

    // findExerciseById=(exerciseId)=>{
    //     fetch("http://localhost:8081/rest/exercise/"+exerciseId)
    //         .then(response=>response.json())
    //         .then((exercise) => {
    //             if(exercise) {
    //                 this.setState({
    //                     id: exercise.id,
    //                     exName: exercise.exName,
    //                     trDuration: exercise.trDuration,
    //                     trPosition: exercise.trPosition,
    //                     totCalories: exercise.totCalories
    //                 });
    //             }
    //         }).catch((error) => {
    //             console.error("Error - "+error);
    //         });
    // };

    resetExercise = () => {
        this.setState(() => this.initialState);
    };

    submitExercise = event => {
        event.preventDefault();

        const exercise = {
            id: this.state.id,
            exId: this.state.exId,
            trDuration: this.state.trDuration,
            trPosition: this.state.trPosition
            // totCalories: this.state.totCalories
        };

        // axios.post("http://localhost:8081/rest/exercise", exercise)
        //     .then(response => {
        //         if(response.data != null) {
        //             this.setState({"show":true, "method":"post"});
        //             setTimeout(() => this.setState({"show":false}), 3000);
        //         } else {
        //             this.setState({"show":false});
        //         }
        //     });

        this.props.saveExercise(exercise);
        setTimeout(() => {
            if(this.props.exerciseObject.exercise != null) {
                this.setState({"show":true, "method":"post"});
                setTimeout(() => this.setState({"show":false}), 3000);
            } else {
                this.setState({"show":false});
            }
        }, 2000);

        this.setState(this.initialState);
    };

    // submitExercise = event => {
    //     event.preventDefault();

    //     const exercise = {
    //         exId: this.state.exId,
    //         trDuration: this.state.trDuration,
    //         trPosition: this.state.trPosition,
    //         totCalories: this.state.totCalories
    //     };

    //     const headers= new Headers();
    //     headers.append('Content-Type', 'application/json')

    //     fetch("http://localhost:8081/rest/exercise", {
    //         method: 'POST',
    //         body: JSON.stringify(exercise),
    //         headers
    //     })
    //         .then(response=>response.json())
    //         .then(exercise => {
    //             if(exercise != null) {
    //                 this.setState({"show":true, "method":"post"});
    //                 setTimeout(() => this.setState({"show":false}), 3000);
    //             } else {
    //                 this.setState({"show":false});
    //             }
    //         });

    //     this.setState(this.initialState);
    // };

    updateExercise = event => {
        event.preventDefault();

        const exercise = {
            id: this.state.id,
            exId: this.state.exId,
            trDuration: this.state.trDuration,
            trPosition: this.state.trPosition
            // totCalories: this.state.totCalories
        };

        axios.put("http://localhost:8081/rest/exercise", exercise)
            .then(response => {
                if(response.data != null) {
                    this.setState({"show":true, "method":"put"});
                    setTimeout(() => this.setState({"show":false}), 3000);
                    setTimeout(() => this.exerciseList(), 3000);
                } else {
                    this.setState({"show":false});
                }
            });

        this.setState(this.initialState);
    };

    exerciseChange=event=>{
        this.setState({
            [event.target.name]:event.target.value
        });
    };

    exerciseList = () => {
        return this.props.history.push("/list");
    };

    render(){
        const{ex, exId, trDuration, trPosition, totCalories}=this.state

        return(
            <div>
                <div style={{"display":this.state.show ? "block" : "none"}}>
                <MyToast show = {this.state.show} message = {this.state.method === "put" ? "Exercise Updated Successfully." : "Exercise Saved Successfully."} type = {"success"}/>
                </div>
                <Card className={"border border-dark bg-dark text-white"}>
                    <Card.Header>
                    <FontAwesomeIcon icon={this.state.id ? faEdit : faPlusSquare} /> {this.state.id ? "Update Exercise" : "Add Exercise"}
                    </Card.Header>
                    <Form onReset={this.resetExercise} onSubmit={this.state.id ? this.updateExercise : this.submitExercise} id="exerciseFormId"> 
                        <Card.Body>
                            <Form.Row>
                                <Form.Group as={Col} controlId="formGridName">
                                    <Form.Label>Exercise Name</Form.Label>
                                    {/* <Form.Control required autoComplete="off" type="text" name="exName"
                                    value={exName}
                                    onChange={this.exerciseChange} 
                                    className={"bg-dark text-white"} placeholder="Exercise Name" /> */}
                                    <Form.Control required as="select"
                                        custom onChange={this.exerciseChange}
                                        name="exId" value={exId}
                                        className={"bg-dark text-white"}>
                                        {this.state.exnames.map(ex =>
                                            <option value={ex.value} key={ex.value}>
                                                {ex.display}
                                            </option>
                                        )}
                                    </Form.Control>
                                    <Form.Text className="text-muted">
                                    Enter the exercise name.
                                    </Form.Text>
                                </Form.Group>
                                <Form.Group as={Col} controlId="formGridDuration">
                                    <Form.Label>Exercise Duration</Form.Label>
                                    <Form.Control required autoComplete="off" type="number" name="trDuration" 
                                    value={trDuration}
                                    onChange={this.exerciseChange}
                                    className={"bg-dark text-white"} placeholder="Exercise Duration" />
                                    <Form.Text className="text-muted">
                                    Enter the exercise duration.
                                    </Form.Text>
                                </Form.Group>
                            </Form.Row>
                            <Form.Row>
                                <Form.Group as={Col} controlId="formGridPosition"></Form.Group>
                                <Form.Group as={Col} controlId="formGridPosition">
                                    <Form.Label>Player Position</Form.Label>
                                    <Form.Control required autoComplete="off" type="text" name="trPosition" 
                                    value={trPosition}
                                    onChange={this.exerciseChange}
                                    className={"bg-dark text-white"} placeholder="Player Position" />
                                    <Form.Text className="text-muted">
                                    Enter the player position for this exercise.
                                    </Form.Text>
                                </Form.Group>
                                {/* <Form.Group as={Col} controlId="formGridCoach">
                                    <Form.Label>Coach Name</Form.Label>
                                    <Form.Control required autoComplete="off" type="text" name="totCalories" 
                                    value={totCalories}
                                    onChange={this.exerciseChange}
                                    className={"bg-dark text-white"} placeholder="Coach Name" />
                                    <Form.Text className="text-muted">
                                    Enter the name of the coach who will lead the exercise.
                                    </Form.Text>
                                </Form.Group> */}
                            </Form.Row>
                        </Card.Body>
                        <Card.Footer style={{"textAlign":"right"}}>
                            <Button variant="success" type="submit">
                                <FontAwesomeIcon icon={faSave} /> {this.state.id ? "Update" : "Save"}
                            </Button>{' '}
                            <Button variant="info" type="reset" onClick={this.exerciseList.bind()}>
                                <FontAwesomeIcon icon={faList} /> Training
                            </Button>
                        </Card.Footer>
                    </Form>
                </Card>
            </div>
        );
    }
};

const mapStateToProps = state => {
    return {
        exerciseObject: state.exercise
    };
};

const mapDispatchToProps = dispatch => {
    return {
        saveExercise: (exercise) => dispatch(saveExercise(exercise))
    };
};

export default connect(mapStateToProps, mapDispatchToProps)(Exercise); 

