import React, {Component} from 'react';
import {Card, Col, Form, Button} from "react-bootstrap";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faPlusCircle, faSave, faUndo} from '@fortawesome/free-solid-svg-icons'
import axios from 'axios';

export default class Movie extends Component {

    constructor(props) {
        super(props);
        this.state = this.initialState;
        this.movieChange = this.movieChange.bind(this);
        this.submitForm = this.submitForm.bind(this);
    }

    initialState = {
        id: '',
        title: '',
        length: ''
    }

    resetState = () => {
        this.setState(() => this.initialState);
    }

    submitForm = (event) => {
        event.preventDefault();

        const movie = {
            id: this.state.id,
            title: this.state.title,
            length: this.state.length
        };

        const query = `mutation createMovie($title:String!, $length:Int!) {
                            createMovie(title:$title ,length:$length) {
                                id
                              }
                            }`

        const variables = {title: movie.title, length: movie.length};

        this.createMovie(query, variables).catch(error => console.log(error));

        this.resetState();

    }

    movieChange = (event) => {
        this.setState({
            [event.target.name]:event.target.value
        });
    }

    createMovie = async (query, variables) => {
        try {
            const response = await axios.post(process.env.REACT_APP_API_ENDPOINT, {
                query,
                variables
            });

            if(response.data != null) {
                console.log("Movie saved!")
            }

        } catch (error) {
            console.log(error);
        }
    }

    render() {
        const {id, title, length} = this.state;

        return (
            <Card className={"boarder boarder-dark bg-dark text-white"}>
                <Card.Header><FontAwesomeIcon icon={faPlusCircle}/> Movie </Card.Header>
                <Form onSubmit={this.submitForm} onReset={this.resetState} id="movieFormId">
                    <Card.Body>
                        <Form.Row>
                            <Form.Group as={Col} controlId="formGroupId">
                                <Form.Label>Id</Form.Label>
                                <Form.Control required autoComplete="off"
                                    type="test"
                                    name="id"
                                    value={id} onChange={this.movieChange}
                                    className={"bg-dark text-white"}/>
                            </Form.Group>

                            <Form.Group as={Col} controlId="formGroupTitle">
                                <Form.Label>Title</Form.Label>
                                <Form.Control required autoComplete="off"
                                    type="test"
                                    name="title"
                                    value={title} onChange={this.movieChange}
                                    className={"bg-dark text-white"}/>
                            </Form.Group>

                            <Form.Group as={Col} controlId="formGroupLength">
                                <Form.Label>Length</Form.Label>
                                <Form.Control required autoComplete="off"
                                    type="test"
                                    name="length"
                                    value={length} onChange={this.movieChange}
                                    className={"bg-dark text-white"}/>
                            </Form.Group>
                        </Form.Row>
                    </Card.Body>
                    <Card.Footer style={{"textAlign":"right"}}>
                        <Button size={"sm"} variant="success" type="submit">
                            <FontAwesomeIcon icon={faSave}/> Submit
                        </Button>{' '}
                        <Button size={"sm"} variant="info" type="reset">
                            <FontAwesomeIcon icon={faUndo}/> Reset
                        </Button>
                    </Card.Footer>
                </Form>
            </Card>
        )
    }
}