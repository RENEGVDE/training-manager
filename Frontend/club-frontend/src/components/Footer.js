import React, { Component } from 'react'
import { Navbar, Container, Row, Col } from 'react-bootstrap';

export default class Footer extends Component {
    render() {
        let fullYear = new Date().getFullYear();

        return (
            <Navbar fixed="bottom" bg="dark" variant="dark">
                <Container>
                    <Col lg={12} className="text-center text-muted">
                        <div>{fullYear} Made by Rusnac</div>
                    </Col>
                </Container>
            </Navbar>
        )
    }
}

