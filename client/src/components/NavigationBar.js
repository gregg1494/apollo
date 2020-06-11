import React, {Component} from 'react';
import {Navbar, Nav} from "react-bootstrap";
import {Link} from 'react-router-dom';

export default class NavigationBar extends Component {
    render() {
        return (
            <Navbar bg="dark" variant="dark">
                <Link to={""} className="navbar-brand">
                    <img src="http://www.emoji.co.uk/files/phantom-open-emojis/objects-phantom/12785-movie-camera.png"  width="30" height="30" alt="brand"/> Apollo
                </Link>
                <Nav className="mr-auto">
                    <Link to={"/movies"} className="nav-link">Movies</Link>
                    <Link to={"/latest"} className="nav-link">Latest</Link>
                    <Link to={"/movie"} className="nav-link">Movie</Link>
                    <Link to={"/login"} className="nav-link">Login</Link>
                    <Link to={"/logout"} className="nav-link">Logout</Link>
                </Nav>
            </Navbar>
        );
    }
}