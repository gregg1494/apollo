import React from 'react';
import {Navbar, Nav} from "react-bootstrap";

class NavigationBar extends React.Component {
    render() {
        return (
            <Navbar bg="dark" variant="dark">
                <Navbar.Brand href="#home"> <img src="http://www.emoji.co.uk/files/phantom-open-emojis/objects-phantom/12785-movie-camera.png"  width="30" height="30" alt="brand"/> Apollo </Navbar.Brand>
                <Nav className="mr-auto">
                    <Nav.Link href="#movies">Movies</Nav.Link>
                    <Nav.Link href="#latest">Latest</Nav.Link>
                    <Nav.Link href="#favorites">Favorites</Nav.Link>
                </Nav>
            </Navbar>
        );
    }
}

export default NavigationBar;