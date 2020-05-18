import React, { Component } from 'react';
import './App.css';

import NavigationBar from './components/NavigationBar';
import Welcome from './components/Welcome';
import Footer from "./components/Footer";
import Favorites from "./components/Favorites";

import {Col, Container, Row} from "react-bootstrap";
import Movie from "./components/Movie";
import Latest from "./components/Latest";

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
                          <Route path="/movies" exact component={Movie}/>
                          <Route path="/latest" exact component={Latest}/>
                          <Route path="/favorites" exact component={Favorites}/>
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