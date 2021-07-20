import logo from './logo.svg';
import './App.css';
import { Container, Row, Col } from 'react-bootstrap';
import {BrowserRouter as Router, Switch, Route} from 'react-router-dom';
import {Provider} from 'react-redux';
import store from './services/store';

import NavigationBar from './components/NavigationBar';
import Home from './components/Home';
import Footer from './components/Footer';
import Exercise from './components/Exercise';
import Training from './components/Training';
import Players from './components/Players';
import Login from './components/player/Login';
import Register from './components/player/Register';
import authToken from './utils/authToken';


function App() {

  window.onbeforeunload=(event)=>{
    const e = event || window.event;
    e.preventDefault();
    if(e){
      e.returnValue='';
    }
    return;
  }

  const marginTop = {
    marginTop:"20px"
  };

  return (
    
    <Router>
      <NavigationBar />
      <Container>
        <Row>
          <Col lg={12} style={marginTop}>
            <Switch>
              <Route exact path="/" exact component={Home}/>
              <Route exact path="/add" exact component={Exercise}/>
              <Route path="/edit/:id" exact component={Exercise}/>
              <Route exact path="/list" exact component={Training}/>
              <Route path="/players" exact component={Players}/>
              {/* <Route path="/players" exact component={() =>
                <Provider store={store}><Players/></Provider>}/> */}
              <Route exact path="/register" exact component={Register}/>
              <Route exact path="/login" exact component={Login}/>
              <Route exact path="/logout" exact component={Login}/>
            </Switch>
          </Col>
        </Row>
      </Container>
      <Footer />
    </Router>
  );
}

export default App;
