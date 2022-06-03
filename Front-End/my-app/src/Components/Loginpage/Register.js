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
            <div class="loginbody center1">
                <div class="mainbody ">
                    <div class="Loginbox">
                        <div class="classiclogin">
                            <div class="loginform ">
                                <h4><b>Welcome back!</b></h4>
                                <p>We're so excited for you to sign up!</p>
                                <div class="form-group">
                                    <label class="InputEmail"><b>Email address</b></label>
                                    <input type="email" class="form-control" id="email" aria-describedby="emailHelp" placeholder="Email" />
                                    {/* value={this.userEmail} */}
                                </div>
                                <div class="form-group">
                                    <label for="exampleInputPassword1" class="passwordtexts"><b>Password</b></label>
                                    <input type="password" class="form-control" id='pass' placeholder="Password" />
                                    {/* <p1>Forgot your password?</p1> */}
                                </div>
                                {/* <label>
                                    <input type="checkbox" onClick={this.rememberMeOnChange} />
                                    Remember my email
                                </label> */}
                                <button type="submit" class="btn btn-primary LoginButton" onClick={this.Login}>Login</button>
                                <Link to="/Login">
                                    <p>Sign in</p>
                                </Link>
                            </div>
                        </div>
                    </div>
                </div>
                {/* <div class="SideSpacer" /> */}
            </div>
        );
    }
}

export default Register;