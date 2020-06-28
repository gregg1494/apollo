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
                    <Link to={"/latest"} className="nav-link">Latest</Link>
                    <Link to={"/favorites"} className="nav-link">Favorites</Link>
                    <IsLoggedIn></IsLoggedIn>   
                </Nav>
            </Navbar>
        );
    }
}

function IsLoggedIn() {
    var t = localStorage.getItem('token')
    if(t == null) {
        return <Link to={"/login"} className="nav-link">Login</Link> 
   } else {
       return <Link to={"/logout"} className="nav-link">Logout</Link>
   }    
}