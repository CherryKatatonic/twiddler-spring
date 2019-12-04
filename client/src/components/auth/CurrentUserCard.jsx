import React, { Component } from 'react';

export default class CurrentUserCard extends Component {
    constructor(props) {
        super(props);
        this.state = {
            user: this.props.user
        }
    }

    render() {
        const { user } = this.state;

        return (
            <div className="mb-3">
                <div className="card card-user">
                    <div className="row no-gutters mx-auto">
                        <div className="col-4 d-flex align-items-center p-1 pl-2">
                            <img className="w-100"
                                 src={user.imageUrl != null ? user.imageUrl : "/images/profile.png"}
                                 alt="Profile"
                            />
                        </div>
                        <div className="col-8">
                            <div className="card-body">
                                <h5 className="card-text font-weight-bold">@{user.username}</h5>
                            </div>
                        </div>
                    </div>
                    <div className="card-footer bg-twiddler-dark text-center">
                        <button className="btn btn-block btn-twiddler-light" onClick={this.props.logout}>Logout</button>
                    </div>
                </div>
            </div>
        )
    }
}