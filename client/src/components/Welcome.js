import React, {Component} from 'react';
import {Jumbotron} from "react-bootstrap";

class Welcome extends Component {
    render() {
        return (
            <Jumbotron className="bg-dark text-white">
                <h1>Welcome!</h1>
                <blockquote className="blockquote mb-0">
                    <p>
                        Many of life's failures are people who did not realize how close they were to success when they gave up.
                    </p>
                    <footer className="blockquote-footer">
                        Thomas A. Edison
                    </footer>
                </blockquote>
            </Jumbotron>
        );
    }
}

export default Welcome;