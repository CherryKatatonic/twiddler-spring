import React, { Component } from 'react';
import SignupForm from "./SignupForm";
import LoginForm from "./LoginForm";

export default class AuthFormContainer extends Component {
    constructor(props) {
        super(props);
        this.state = {
            form: 'login'
        }
    }

    // Toggle between Login form and Signup form
    setForm = (form) => {
        this.setState({ form })
    };

    render() {
        const { login } = this.props;

        return (
            <div className="card bg-twiddler-light mb-3">
                <div className="card-header bg-twiddler-dark text-center">
                    <div className="row">
                        <div className="col">
                            <button className="btn btn-block btn-twiddler-light" onClick={() => this.setForm('signup')}>
                                Signup
                            </button>
                        </div>
                        <div className="col">
                            <button className="btn btn-block btn-twiddler-light" onClick={() => this.setForm('login')}>
                                Login
                            </button>
                        </div>
                    </div>
                </div>
                {this.state.form === 'signup'
                    ? <SignupForm login={login} />
                    : <LoginForm login={login} />
                }
            </div>
        )
    }
}