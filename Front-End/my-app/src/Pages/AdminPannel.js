import React, { Component } from 'react';
import '../App.css';
import Nav from '../Components/NavigationBar/bar.js';
import AdminPannelSideBar from '../Components/AdminPanelSideBar/AdminPannelSideBar';


class AdminPannel extends Component {
    constructor(props) {
        super(props);
        this.state = {
        };
    }

    // displayes the Admin page (this was later replaced with the Anaylitic page)
    render() {
        return (
            <div className="App">
                <Nav />
                <AdminPannelSideBar />
            </div>
        );
    }
}

export default AdminPannel;
