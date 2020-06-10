import React, {Component} from 'react';
import axios from "axios";

export default class Login extends Component {

    constructor(props) {
        super(props);
        this.state = {
            username: null,
            password: null,
            login: false,
            store: null
        };
    }

    componentDidMount() {
        this.storeCollector()
    }

    storeCollector() {
        let store = JSON.parse(localStorage.getItem('login'));
            if(store && store.login) {
                this.setState({
                    login: true,
                    store: store
                });
            }
    }

    login() {
        const query = `mutation login($username: String!, $password: String!){
                            login(username: $username, password: $password)
                       }`;

        const variables = {username: this.state.username, password: this.state.password}

        this.doLogin(query, variables).catch(error => console.log(error));
    }

    doLogin = async (query, variables) => {
        try {
            await axios.post(process.env.REACT_APP_API_ENDPOINT, {
                query,
                variables
            }).then(response => {
                console.log("token: " + response.data.data.login);
                localStorage.setItem('login', JSON.stringify({
                    login: true,
                    token: response.data.data.login
                }));
                this.setState({login: true});
                this.props.history.push('/');
            });
        } catch (error) {
            this.state(() => ({ error }));
        }
    }

    render() {
        return (
            <div>
                <input required type="text" onChange={(event => {this.setState({username:event.target.value})})}/>
                <br/>
                <input required type="password" onChange={(event => {this.setState({password:event.target.value})})}/>
                <br/>
                <button onClick={()=>{this.login()}}>Login</button>
            </div>
        )
    }
}