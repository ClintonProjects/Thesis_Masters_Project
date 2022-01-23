import React, { Component } from 'react';
import '../App.css';
import LoginBody from '../Components/Loginpage/loginpage.js';

class Login extends Component {
    constructor(props) {
        super(props);
        this.state = {
        };

    }
    render() {
        return (
            <div className="App">
                <LoginBody/>
            </div>
        );
    }
}

export default Login;
