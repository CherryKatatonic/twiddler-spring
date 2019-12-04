import React, { Component } from 'react';
import axios from "axios";

export default class TwiddleForm extends Component{
    constructor(props) {
        super(props);
        this.initState = {
            user: props.user,
            content: '',
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
        const {user, content} = this.state;

        axios.post('http://localhost:8080/api/twiddles', {user, content}, {
            headers: {'content-type': 'application/json'}
        })
            .then(() => window.location.reload())
            .catch(err => this.setState({ errors: err.response.data }))
    };

    render() {
        const { user, errors } = this.state;

        return (
            <div className="card card-user">
                <div className="row mx-auto mb-3">
                    <div className="col-2 d-flex align-items-center justify-content-end pl-2 pt-2 pr-0">
                        <img className="w-100"
                             src={user.imageUrl != null ? user.imageUrl : "/images/profile.png"}
                             alt="Profile"
                        />
                    </div>
                    <div className="col-10 d-flex align-items-end">
                        <h3 className="font-weight-bold mb-0">@{user.username}</h3>
                    </div>
                </div>
                <div className="row px-4">
                    <form className="w-100" onSubmit={this.handleSubmit}>
                        <div className="form-group">
                            <textarea className={"form-control font-weight-bold text-dark" + (errors.content && "is-invalid")}
                                      id="content"
                                      name="content"
                                      onChange={this.handleChange}
                                      placeholder="Got somethin' to say?"/>
                            {errors.content && (
                                <div className="invalid-feedback">{errors.content}</div>
                            )}
                        </div>
                        <div className="form-group">
                            <button className="btn btn-block btn-twiddler-light" type={"submit"} name={"action"}>Submit</button>
                        </div>
                    </form>
                </div>
            </div>
        )
    }
};