/*
    This class needs is for testing
 */

import React, {Component} from 'react';
import axios from "axios";

export default class Login extends Component {

    logout() {
        const query = `mutation logout($username: String!){
                            logout(username: $username)
                       }`;

        const variables = {username: this.props.username}

        this.doLogout(query, variables).catch(error => console.log(error));
    }

    doLogout = async (query, variables) => {
        try {
            await axios.post(process.env.REACT_APP_API_ENDPOINT, {
                query,
                variables
            }).then(response => {
                localStorage.clear();
                this.props.history.push('/');
            });
        } catch (error) {
            this.state(() => ({ error }));
        }
    }

    render() {
        return (
            <div>
                <button onClick={()=>{this.logout()}}>Log out</button>
            </div>
        )
    }
}