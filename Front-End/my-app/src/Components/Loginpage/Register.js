import React, { Component } from 'react';
import './Login.css';
import { Route, BrowserRouter as Router, Switch, Link } from "react-router-dom";
import Hash from '../../Funuctions/Hash/Password.js';


class Register extends Component {
    constructor(props) {
        super(props);
        this.state = {
            attempts: 0,
        };
        this.Login = this.Login.bind(this);
    }

    async Login() {
        console.log("Login");
        var sessionId = "";
        const requestOptions = {
            method: "POST",
            headers: {
                'Content-Type': 'application/json',
                "Access-Control-Allow-Origin": "*",
            },

            body: JSON.stringify({
                email: document.getElementById('email').value,
                password: document.getElementById('pass').value
            })
        };

        await fetch('http://localhost:8085/login/Register', requestOptions)
            .then((response) => response.json())
            .then((messages) => {
                localStorage.setItem('SessionId', messages.id);
                window.location.href = "http://localhost:3000/";
            }).catch(error => {
                console.error('There was an error!', error);
                this.setState({ attempts: this.state.attempts+1});
                console.log(this.state.attempts);
            });

        console.log("ran");
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
                                {this.state.attempts > 0 ? "User found or password or email is already found" : ""}
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