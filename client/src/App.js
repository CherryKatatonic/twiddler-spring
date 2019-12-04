import React from 'react';
import Dashboard from "./components/Dashboard";
import Navbar from "./components/Navbar";
// Import global styles
import './styles/buttons.css';

const App = () => {
    return (
        <div className="container-fluid px-0">
            <Navbar />
            <Dashboard />
        </div>
    );
};

export default App;