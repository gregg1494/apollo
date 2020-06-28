import React, {Component} from 'react';
import {Player, ControlBar} from 'video-react';
import axios from "axios";

export default class Movie extends Component {

    constructor(props) {
        super(props);
        this.state = {
            url: null,
            errors: []
        }
    }

    componentDidMount() {

        const query = `query getMovie($id: Long!) {
                            movie(id: $id) {
                                url
                            }
                       }`;

        const variables = {id: this.props.location.state.key}

        let headers = {
            headers: {
                'Content-Type': 'application/json',
                'Authorization' : `Bearer ${JSON.parse(localStorage.getItem('login')).token}`
            }
          };

        this.getMovie(query, variables, headers).catch(error => console.log(error));
    }

    componentWillUnmount() {
        clearInterval(this.interval);
        this.setState({url: null});
    }

    getMovie = async (query, variables, headers) => {
        try {
            await axios.post(process.env.REACT_APP_API_ENDPOINT, {query, variables}, {headers: headers})
            .then(response => {
                this.setState({url: response.data.data.movie.url});
            });
        } catch (error) {
            this.state(() => ({ error }));
        }
    }

    render() {
        return (
            <Player autoPlay src={this.state.url}>
                <ControlBar autoHide={true} className="my-class" />
            </Player>
        );
    }
}