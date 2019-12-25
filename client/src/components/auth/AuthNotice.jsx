import React from 'react';

const AuthNotice = () => {
    return (
        <div className="card card-user mb-1">
            <div className="row mx-auto mb-3">
                <div className="col-2 d-flex align-items-center justify-content-end pl-2 pt-2 pr-0">
                    <img className="w-100"
                         src={"/images/profile.png"}
                         alt="Profile"
                    />
                </div>
                <div className="col-10 d-flex align-items-end">
                    <h3 className="font-weight-bold mb-0">@you</h3>
                </div>
            </div>
            <div className="row px-4">
                <h4 className="p-2 font-weight-bold">
                    Sign up or log in to start twiddling
                </h4>
            </div>
        </div>
    )
};

export default AuthNotice;