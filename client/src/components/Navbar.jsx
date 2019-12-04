import React from 'react';
import '../styles/navbar.css';
import ReactSVG from "react-svg";

const Navbar = () => {
    return (
        <nav className="navbar navbar-expand-lg py-0 mb-3 w-100 px-0">
            <div className="nav-item col text-left d-none d-sm-block">
                <a href="/" className="btn navbar-brand py-0">Twiddler</a>
            </div>
            <div className="nav-item col h-100 text-sm-center">
                <a href="/">
                    <img className="h-100 navbar-logo" src="/images/twiddle.jpg" alt="Twiddler Logo"/>
                </a>
            </div>
            <div className="nav-item col text-right">
                <button className="btn btn-nav icon-btn"
                        data-toggle="popover"
                        title="Live Feed"
                        data-content="Live Twiddle Feed has not been implemented yet.">
                    <ReactSVG className="icon" src="/images/antenna.svg" width="20" alt="Live"/>
                    <span className="after-icon">Live</span>
                </button>
            </div>
        </nav>
    )
};

export default Navbar;