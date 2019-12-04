import React, { Component } from 'react';

export default class LoginForm extends Component {
    constructor(props) {
        super(props);
        this.initState = {
            username: '',
            password: '',
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
        const { username, password } = this.state;
        this.props.login(username, password);
    };

    render() {
        const { errors } = this.state;

        return (
            <form onSubmit={this.handleSubmit}>
                <div className="card bg-twiddler-light border-0">
                    <div className="card-header bg-twiddler-dark text-center">
                        <h3 className="mb-0 font-weight-bold">Login</h3>
                    </div>
                    <div className="card-body pb-0">
                        {errors === 'Unauthorized' && (
                            <div className="invalid-feedback d-block">Invalid credentials</div>
                        )}
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
                    </div>
                    <div className="card-footer bg-twiddler-dark text-center">
                        <button className="btn btn-block btn-twiddler-light" type={"submit"} name={"action"}>Log In</button>
                    </div>
                </div>
            </form>
        )
    }
}