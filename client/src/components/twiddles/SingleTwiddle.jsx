import React from 'react';
import Moment from 'react-moment';

const SingleTwiddle = ({ twiddle }) => {
    return (
        <div className="card card-user mb-3">
            <div className="row mx-auto">
                <div className="col-2 d-flex align-items-center justify-content-end pl-2 pt-2 pr-0">
                    <img className="w-100"
                         src={twiddle.user.imageUrl != null ? twiddle.user.imageUrl : "/images/profile.png"}
                         alt="Profile"
                    />
                </div>
                <div className="col-10 d-flex align-items-end">
                    <h3 className="font-weight-bold mb-0">@{twiddle.user.username}</h3>
                </div>
            </div>
            <div className="row px-3">
                <div className="card-body">
                    <p className="font-weight-bold text-dark text-break">{twiddle.content}</p>
                    <p className="card-text text-muted"><Moment fromNow>{twiddle.createdAt}</Moment></p>
                </div>
            </div>
        </div>
    )
};

export default SingleTwiddle;