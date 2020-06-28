import React, {Component} from 'react';
import axios from 'axios';
import {Card, Table, Button, ButtonGroup} from 'react-bootstrap';
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faList, faThumbsUp} from '@fortawesome/free-solid-svg-icons'

export default class Movies extends Component {

    constructor(props) {
        super(props);
        this.state = {
            error: null,
            isLoaded: false,
            movies: []
        };
    }

    componentDidMount() {
        const query = `query {
                        movies {
                            id
                            title
                            length
                        }
                    }`;

        this.getMovies(query).catch(e => console.log(e));
    }

    getMovies = async (query) => {
        try {
            const response = await axios.post(process.env.REACT_APP_API_ENDPOINT, {
                query
            });

            this.setState(() => ({
                isLoaded: true,
                movies: response.data.data.movies
            }));

        } catch (error) {
            this.state(() => ({ error }));
        }
    }

    fetch = (movie) => {
        this.props.history.push({
            pathname:"/movie",
            state:{
                key: movie.id
             }
           });
    };

    render() {
        return (
            <Card className="boarder boarder-dark bg-dark text-white">
                <Card.Header><FontAwesomeIcon icon={faList}/> Movies </Card.Header>
                <Card.Body>
                    <Table striped bordered hover variant="dark">
                        <thead>
                        <tr>
                            <th>id</th>
                            <th>title</th>
                            <th>length</th>
                        </tr>
                        </thead>
                        <tbody>
                        {
                            this.state.movies.length === 0 ?
                                <tr align="center">
                                    <td colSpan="6">No Movies Available</td>
                                </tr> :
                                this.state.movies.map((movie) => (
                                    <tr key={movie.id}>
                                        <td>{movie.id}</td>
                                        <td>{movie.title}</td>
                                        <td>{movie.length}</td>
                                        <td>
                                            <ButtonGroup>
                                                <Button size="md" variant="outline-primary" onClick={this.fetch.bind(this, movie)}> <FontAwesomeIcon icon={faThumbsUp}/></Button>
                                            </ButtonGroup>
                                        </td>
                                        
                                    </tr>
                                ))
                        }
                        </tbody>
                    </Table>
                </Card.Body>
            </Card>
        )
    }
}