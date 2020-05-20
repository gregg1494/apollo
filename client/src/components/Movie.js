import React, {Component} from 'react';
import {Card, Col, Form, Button} from "react-bootstrap";

export default class Movie extends Component {

    constructor(props) {
        super(props);
        this.state = {id: '', title: '', length: ''};
        this.movieChange = this.movieChange.bind(this);
        this.submitForm = this.submitForm.bind(this);
    }

    submitForm(event) {
        alert('id: ' + this.state.id + ', title: ' + this.state.title + ', length: ' + this.state.length);
        event.preventDefault();
    }

    movieChange(event) {
        this.setState({
            [event.target.name]:event.target.value
        });
    }

    render() {
        return (
            <Card className={"boarder boarder-dark bg-dark text-white"}>
                <Card.Header>Movie</Card.Header>
                <Form onSubmit={this.submitForm} id="movieFormId">
                    <Card.Body>
                        <Form.Row>
                            <Form.Group as={Col} controlId={"formGroupId"}>
                                <Form.Label>Id</Form.Label>
                                <Form.Control required
                                    type="test"
                                    name="id"
                                    value={this.state.id}
                                    onChange={this.movieChange}
                                    className={"bg-dark text-white"}/>
                            </Form.Group>

                            <Form.Group as={Col} controlId={"formGroupTitle"}>
                                <Form.Label>Title</Form.Label>
                                <Form.Control required
                                    type="test"
                                    name="title"
                                    value={this.state.title}
                                    onChange={this.movieChange}
                                    className={"bg-dark text-white"}/>
                            </Form.Group>

                            <Form.Group as={Col} controlId={"formGroupLength"}>
                                <Form.Label>Length</Form.Label>
                                <Form.Control required
                                    type="test"
                                    name="length"
                                    value={this.state.length}
                                    onChange={this.movieChange}
                                    className={"bg-dark text-white"}/>
                            </Form.Group>
                        </Form.Row>
                    </Card.Body>
                    <Card.Footer style={{"textAlign":"right"}}>
                        <Button variant="success" type="submit">
                            Submit
                        </Button>
                    </Card.Footer>
                </Form>
            </Card>
        )
    }
}