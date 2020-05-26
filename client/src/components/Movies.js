import React, {Component} from 'react';
import axios from 'axios';
import {Card, Table} from 'react-bootstrap';
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faList} from '@fortawesome/free-solid-svg-icons'

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
            const response = await axios.post('http://localhost:8080/graphql', {
                query
            })

            this.setState(() => ({
                isLoaded: true,
                movies: response.data.data.movies
            }));
            console.log(response.data);
        } catch (error) {
            this.state(() => ({ error }));
        }
    }

    render() {
        return (
            <Card className={'boarder boarder-dark bg-dark text-white'}>
                <Card.Header><FontAwesomeIcon icon={faList}/> Movies </Card.Header>
                <Card.Body>
                    <Table striped bordered hover variant={'dark'}>
                        <thead>
                        <tr>
                            <th>id</th>
                            <th>title</th>
                            <th>length</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr align={'center'}>
                            <td colSpan={'6'}>{this.state.movies.length}</td>
                        </tr>
                        </tbody>
                    </Table>
                </Card.Body>
            </Card>
        )
    }



}