import React, { Component } from 'react';
import axios from "axios";
import { API_URL } from "../../util/axiosConfig";


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

    handleContentChange = e => {
        const content = e.target.value;
        let errors = {};

        if (content.length > 140) {
            errors = {content: 'Twiddle cannot be more than 140 characters.'}
        }

        this.setState({ content, errors });
    };

    handleSubmit = e => {
        e.preventDefault();
        const {user, content} = this.state;

        axios.post(`${API_URL}/twiddles`, {user, content}, {
            headers: {'content-type': 'application/json'}
        })
            .then(() => window.location.reload())
            .catch(err => this.setState({ errors: err.response.data }))
    };

    render() {
        const { user, content, errors } = this.state;

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
                            <textarea className={"form-control font-weight-bold text-dark " + (errors.content && "is-invalid")}
                                      id="content"
                                      name="content"
                                      onChange={this.handleContentChange}
                                      placeholder="Got somethin' to say?"/>
                            {errors.content
                                ? <div className="invalid-feedback">{content.length}/140 characters | {errors.content}</div>
                                : <div className="valid-feedback">{content.length}/140 characters</div>
                            }
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