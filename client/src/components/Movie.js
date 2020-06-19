import React, {Component} from 'react';
import { Player, ControlBar } from 'video-react';
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

        const query = `query getMovie {
                            movie(id: 5) {
                                title
                                url
                            }
                       }`;

        const variables = {id: 4}

        this.getMovie(query, variables).catch(error => console.log(error));
    }

    componentWillUnmount() {
        clearInterval(this.interval);
        this.setState({url: null});
    }

    getMovie = async (query, variables) => {
        try {
            await axios.post(process.env.REACT_APP_API_ENDPOINT, {
                query,
                variables
            }).then(response => {
                console.log(response.data.data.movie.title);
                console.log(response.data.data.movie.url);

                this.setState({url: response.data.data.movie.url});
            });
        } catch (error) {
            this.state(() => ({ error }));
        }
    }

    render () {
        return (
            <Player autoPlay src={this.state.url}>
                <ControlBar autoHide={true} className="my-class" />
            </Player>
        );
    }
}
