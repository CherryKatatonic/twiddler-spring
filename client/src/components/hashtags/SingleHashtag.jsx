import React from 'react';

const SingleHashtag = ({ hashtag }) => {
    return (
        <div className="card card-user">
            <div className="row no-gutters mx-auto">
                <div className="card-body">
                    <h5 className="font-weight-bold text-muted text-break">#{hashtag.content}</h5>
                </div>
            </div>
        </div>
    )
};

export default SingleHashtag;