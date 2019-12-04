import React, { Component } from 'react';
import SingleUser from "./SingleHashtag";

export default class HashtagList extends Component {
    constructor(props) {
        super(props);
        this.state = {
            hashtags: [
                {content: 'trending'},
                {content: 'hashtags'},
                {content: 'NotYetImplemented'}
            ]
        }
    }

    // Trending hashtags have not been implemented yet
    // Implementation may include parsing each new Twiddle upon creation for strings beginning with '#'
    // and saving those strings in a database table with a number value column to keep track of how many Twiddles
    // contain that hashtag, and sorting the results by number of Twiddles they're mentioned in.

    render() {
        return (
            <div className="mb-3">
                <div className="card bg-twiddler-dark text-center p-4">
                    <h3 className="mb-0 font-weight-bold">Trending</h3>
                </div>
                <div id={"hashtagList"} className="scroll-container">
                    { this.state.hashtags.length && this.state.hashtags.map((hashtag, i) => {
                        return <SingleUser key={i} hashtag={hashtag} />
                    })}
                </div>
            </div>
        )
    }
}