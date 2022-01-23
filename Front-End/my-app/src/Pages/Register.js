import React, { Component } from 'react';
import '../App.css';
import RegisterPage from '../Components/Loginpage/Register.js';

class Register extends Component {
    constructor(props) {
        super(props);
        this.state = {
        };

    }
    render() {
        return (
            <div className="App">
              <RegisterPage/>
            </div>
        );
    }
}

export default Register;

