import React, { Component } from 'react';
import SingleTwiddle from "./SingleTwiddle";
import InfiniteScroll from "react-infinite-scroller";
import axios from "axios";

export default class TwiddleList extends Component {
    constructor(props) {
        super(props);
        this.state = {
            twiddles: [],
            lastPage: false,
            nextPage: 0
        }
    }

    fetchTwiddles = () => {
        axios(`http://localhost:8080/api/twiddles?page=${this.state.nextPage}`)
            .then(res => {
                const { data } = res;
                console.log('Response Content: ', res.data.content);

                this.setState({
                    twiddles: [...this.state.twiddles, ...data.content],
                    lastPage: data.last,
                    nextPage: data.number + 1
                });
            })
            .catch(err => console.log(err));
    };

    componentDidMount() {
        this.fetchTwiddles();
    }

    // 9999 is added to each `key` value in the list of Twiddles because many of the Twiddles and Users have
    // the same IDs in the database. This is a temporary and hacky workaround. Ideally, we would like to either
    // find a better way to make each React element key on the page unique, or use a different method of generating
    // IDs for objects in the database so that no two objects (even from different tables) will have the same ID.
    render() {
        const { twiddles } = this.state;
        const twiddleBuffer = [];

        twiddles.length && twiddles.forEach(twiddle => {
            twiddleBuffer.push(<SingleTwiddle key={twiddle.id + 9999} twiddle={twiddle} />);
        });

        if (twiddleBuffer.length) {
            return (
                <div className="mb-3">
                    <div className="card bg-twiddler-dark text-center p-4 mb-1">
                        <h3 className="mb-0 font-weight-bold">Latest Twiddles</h3>
                    </div>
                    <div className="scroll-container">
                        <InfiniteScroll
                            key={99999}
                            pageStart={0}
                            loadMore={this.fetchTwiddles}
                            hasMore={!this.state.lastPage}
                            loader={<div className="loader" key={9999}>Loading...</div>}
                            useWindow={false}
                        >
                            {twiddleBuffer}
                        </InfiniteScroll>
                    </div>
                </div>
            )
        } else {
            return (
                <div className="card bg-twiddler-light text-center p-3">
                    <h2 className="mb-0 font-weight-bold">No twiddles yet :(</h2>
                </div>
            )
        }
    }
};