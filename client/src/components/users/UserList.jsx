import React, { Component } from 'react';
import SingleUser from "./SingleUser";
import InfiniteScroll from 'react-infinite-scroller';
import axios from "axios";
import { API_URL } from "../../util/axiosConfig";

export default class UserList extends Component {
    constructor(props) {
        super(props);
        this.state = {
            users: [],
            lastPage: false,
            nextPage: 0
        }
    }

    fetchUsers = () => {
        axios(`${API_URL}/users?page=${this.state.nextPage}`)
            .then(res => {
                const { data } = res;

                this.setState({
                    users: [...this.state.users, ...data.content],
                    lastPage: data.last,
                    nextPage: data.number + 1
                })
            })
            .catch(err => console.log(err))
    };

    render() {
        const { users } = this.state;
        const userBuffer = [];

        users.length && users.forEach(user => {
            userBuffer.push(<SingleUser key={user.id} user={user} />);
        });

        return (
            <div className="mb-3">
                <div className="card bg-twiddler-dark text-center p-4 mb-1">
                    <h3 className="mb-0 font-weight-bold">New Twiddlers</h3>
                </div>
                <div className="scroll-container">
                    <InfiniteScroll
                        pageStart={0}
                        loadMore={this.fetchUsers}
                        hasMore={!this.state.lastPage}
                        useWindow={false}
                    >
                        {userBuffer}
                    </InfiniteScroll>
                    {!userBuffer.length &&
                        <div className="card bg-twiddler-light text-center p-3">
                            <h3 className="mb-0 font-weight-bold">No users yet :(</h3>
                        </div>
                    }
                </div>
            </div>
        )
    }
}