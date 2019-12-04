import React, { Component } from 'react';
import UserList from "./users/UserList";
import HashtagList from "./hashtags/HashtagList";
import CurrentUserCard from "./auth/CurrentUserCard";
import setJWTToken from "../security/setJwt";
import jwt_decode from "jwt-decode";
import TwiddleList from "./twiddles/TwiddleList";
import TwiddleForm from "./twiddles/TwiddleForm";
import AuthFormContainer from "./auth/AuthFormContainer";
import axios from "axios";

export default class Dashboard extends Component {
    constructor(props) {
        const jwt = localStorage.jwt;
        let auth = null;

        if (jwt) {
            setJWTToken(jwt);
            auth = jwt_decode(jwt);
        }

        super(props);
        this.state = {
            auth: auth
        }
    }

    // Auth is handled here in the Dashboard component because it currently contains all other components that
    // depend on the status of Auth, and because I'm lazy and didn't feel like integrating Redux for global
    // state management, so there is some prop-drilling in this app, but it's been kept to a minimum.

    login = (username, password) => {
        axios.post('http://localhost:8080/api/users/login', {username, password}, {
            headers: {'content-type': 'application/json'}
        })
            .then(res => {
                // Get token string from server response
                const token = res.data;
                // Set token in local storage (Chrome DevTools > Application > Local Storage)
                localStorage.setItem("jwt", token);
                // Set the Authorization header for future HTTP requests
                setJWTToken(token);
                // Decode the token to get the info from it
                const decoded = jwt_decode(token);
                // Set the token info in state
                this.setState({ auth: decoded });
            })
            .then(() => window.location.reload())
            .catch(err => this.setState({ errors: err.response.data }));
    };

    logout = () => {
        // Delete token from local storage
        localStorage.removeItem("jwt");
        // Delete Authorization request header
        setJWTToken(false);
        // Set auth in state to null
        this.setState({ auth: null });
    };

    render() {
        return (
            <main className="row mx-auto">
                <div className="col-sm-4">
                    {this.state.auth
                        ? <CurrentUserCard user={this.state.auth} login={this.login} logout={this.logout} />
                        : <AuthFormContainer login={this.login} />
                    }
                    <HashtagList />
                </div>
                <div className="col-sm-4 p-sm-0">
                    {this.state.auth && <TwiddleForm user={this.state.auth} />}
                    <TwiddleList live={false} />
                </div>
                <div className="col-sm-4">
                    <UserList />
                </div>
            </main>
        )
    }
}