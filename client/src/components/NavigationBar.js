import React from 'react';
import {Navbar, Nav} from "react-bootstrap";
import {Link} from 'react-router-dom';

class NavigationBar extends React.Component {
    render() {
        return (
            <Navbar bg="dark" variant="dark">
                <Link to={""} className="navbar-brand">
                    <img src="http://www.emoji.co.uk/files/phantom-open-emojis/objects-phantom/12785-movie-camera.png"  width="30" height="30" alt="brand"/> Apollo
                </Link>
                <Nav className="mr-auto">
                    <Link to={"/movies"} className="nav-link">Movies</Link>
                    <Link to={"/latest"} className="nav-link">Latest</Link>
                    <Link to={"/favorites"} className="nav-link">Favorites</Link>
                </Nav>
            </Navbar>
        );
    }
}

export default NavigationBar;