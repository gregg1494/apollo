import React, { Component } from 'react';
import './App.css';

import NavigationBar from './components/NavigationBar';

class App extends Component {
  render() {
    return (
      <div className="App">
          <NavigationBar/>
          <p>Hello World</p>
      </div>
    );
  }
}

export default App;
