import React, { Component } from 'react';
import './Login.css';
import { Route, BrowserRouter as Router, Switch, Link } from "react-router-dom";
import Hash from '../../Funuctions/Hash/Password.js';


class Register extends Component {
    constructor(props) {
        super(props);
        this.state = {
        };
        this.Login = this.Login.bind(this);
    }


    Login() {
        console.log("wow");
        var email = document.getElementById('exampleInputEmail1').value;
        var password = document.getElementById('exampleInputPassword1').value;

        let data = {
            username: email,
            password: password,
        };



        const requestOptions = {
            method: "POST",
            headers: {
                'Content-Type': 'application/json',
                "Access-Control-Allow-Origin": "*",
            },
            body: JSON.stringify(data)
        };

        fetch('http://localhost:8085/login/Register', requestOptions)
            .then(async response => {
                const data = await response.json();

                console.log("working?");

                if (!response.ok) {
                    console.error('There was an error!', error);
                    const error = (data && data.message) || response.statusText;
                    return Promise.reject(error);
                }
            })
            .catch(error => {
                this.setState({ errorMessage: error.toString() });
                console.error('There was an error!', error);
            });
    }









    render() {
        return (
            <div class="container">
                <div class="row">
                    <div class="col">
                        1 of 5
                    </div>
                    <div class="col">
                        2 of 5
                    </div>
                    <div class="col">
                        3 of 5
                    </div>
                    <div class="col">
                        4 of 5
                    </div>
                    <div class="col">
                        5 of 5
                    </div>
                </div>
            </div>
        );
    }
}

export default Register;