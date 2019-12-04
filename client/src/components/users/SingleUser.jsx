import React from 'react';
import Moment from 'react-moment';

const SingleUser = ({ user }) => {
    return (
        <div className="card card-user">
            <div className="row no-gutters mx-auto">
                <div className="col-3 d-flex align-items-center p-1 pl-2">
                    <img className="w-100"
                         src={user.imageUrl != null ? user.imageUrl : "/images/profile.png"}
                         alt="Profile"
                    />
                </div>
                <div className="col-9">
                    <div className="p-2">
                        <h5 className="font-weight-bold">@{user.username}</h5>
                        <p className="card-text text-muted"><Moment fromNow>{user.createdAt}</Moment></p>
                    </div>
                </div>
            </div>
        </div>
    )
};

export default SingleUser;