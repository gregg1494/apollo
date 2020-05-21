import React, {Component} from 'react';
import axios from 'axios';
import {Card, Table} from 'react-bootstrap';
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faList} from '@fortawesome/free-solid-svg-icons'

export default class Movies extends Component {

    constructor(props) {
        super(props);
        this.state = {
            movies: []
        };
    }

    componentDidMount() {
        axios({
            url: 'http://localhost:8080/graphql',
            method: 'get',
            data: {
                query: `
                  query movies {
                        id
                        title
                        length
                    }
                  `
            }
        }).then((result) => {
            console.log(result.data)
        });
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
                            <td colSpan={'6'}>No Movies Available</td>
                        </tr>
                        </tbody>
                    </Table>
                </Card.Body>
            </Card>
        )
    }
}