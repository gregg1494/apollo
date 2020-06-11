import React, { Component } from 'react';
import './App.css';

import NavigationBar from "./components/NavigationBar";
import Welcome from './components/Welcome';
import Footer from "./components/Footer";
import Movie from "./components/Movie";

import {Col, Container, Row} from "react-bootstrap";
import Movies from "./components/Movies";
import Latest from "./components/Latest";
import Login from "./components/Login";
import Logout from "./components/Logout";

import {BrowserRouter as Router, Route, Switch} from 'react-router-dom';

class App extends Component {
  render() {
    const marginTop = {
        marginTop:"20px"
    };

    return (
      <Router>
          <NavigationBar/>
          <Container>
              <Row>
                  <Col lg={12} style={marginTop}>
                      <Switch>
                          <Route path="/" exact component={Welcome}/>
                          <Route path="/movies" exact component={Movies}/>
                          <Route path="/latest" exact component={Latest}/>
                          <Route path="/movie" exact component={Movie}/>
                          <Route path="/login" exact component={Login}/>
                          <Route path="/logout" exact component={Logout}/>
                      </Switch>
                  </Col>
              </Row>
          </Container>
          <Footer/>
      </Router>
    );
  }
}

export default App;