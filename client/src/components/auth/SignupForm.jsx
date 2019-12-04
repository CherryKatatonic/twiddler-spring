import React, { Component } from 'react';
import axios from 'axios';
import setJWTToken from "../../security/setJwt";
import jwt_decode from 'jwt-decode';

export default class SignupForm extends Component {
    constructor(props) {
        super(props);
        this.initState = {
            username: '',
            password: '',
            confirmPassword: '',
            errors: {}
        };
        this.state = this.initState;
    }

    handleChange = e => {
        this.setState({
            [e.target.id]: e.target.value
        })
    };

    handleSubmit = e => {
        e.preventDefault();
        const { username, password, confirmPassword } = this.state;

        axios.post('http://localhost:8080/api/users/signup', {username, password, confirmPassword}, {
            headers: {'content-type': 'application/json'}
        })
            // Send request to login with provided credentials after signing up because for some reason
            // Spring Boot refuses to create a new user and authenticate them in the same request.
            .then(() => this.props.login(username, password))
            .catch(err => this.setState({ errors: err.response.data }))
    };

    render() {
        const { errors } = this.state;
        return (
            <form onSubmit={this.handleSubmit}>
                <div className="card bg-twiddler-light border-0">
                    <div className="card-header bg-twiddler-dark text-center">
                        <h3 className="mb-0 font-weight-bold">Signup</h3>
                    </div>
                    <div className="card-body pb-0">
                        <div className="form-group">
                            <label htmlFor="username">Username</label>
                            <input id="username" type="text" onChange={this.handleChange}
                                   className={"form-control " + (errors.username && "is-invalid")} />
                            {errors.username && (
                                <div className="invalid-feedback">{errors.username}</div>
                            )}
                        </div>
                        <div className="form-group">
                            <label htmlFor="password">Password</label>
                            <input id="password" type="password" onChange={this.handleChange}
                                   className={"form-control " + (errors.password && 'is-invalid')}/>
                            {errors.password && (
                                <div className="invalid-feedback">{errors.password}</div>
                            )}
                        </div>
                        <div className="form-group">
                            <label htmlFor="confirmPassword">Confirm Password</label>
                            <input id="confirmPassword" type="password" onChange={this.handleChange}
                                   className={"form-control " + (errors.confirmPassword && 'is-invalid')}/>
                            {errors.confirmPassword && (
                                <div className="invalid-feedback">{errors.confirmPassword}</div>
                            )}
                        </div>
                    </div>
                    <div className="card-footer bg-twiddler-dark text-center">
                        <button className="btn btn-block btn-twiddler-light" type={"submit"} name={"action"}>Sign Up</button>
                    </div>
                </div>
            </form>
        )
    }
}