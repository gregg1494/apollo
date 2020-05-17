import React, { Component } from 'react';
import './App.css';

import NavigationBar from './components/NavigationBar';
import Welcome from './components/Welcome';
import Footer from "./components/Footer";

import {Col, Container, Row} from "react-bootstrap";
import Movie from "./components/Movie";
import MovieList from "./components/MovieList";

class App extends Component {
  render() {
    const marginTop = {
        marginTop:"20px"
    };

    return (
      <div className="App">
          <NavigationBar/>
          <Container>
              <Row>
                  <Col lg={12} style={marginTop}>
                      <Welcome/>
                      <Movie/>
                      <MovieList/>
                  </Col>
              </Row>
          </Container>
          <Footer/>
      </div>
    );
  }
}

export default App;