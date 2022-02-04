import React, { Component } from 'react';
import './Login.css';
import { Route, BrowserRouter as Router, Switch, Link, Redirect } from "react-router-dom";




var sessionId;
class loginpage extends Component {
    constructor(props) {
        super(props);
        this.state = {
            rememberMe: false,
        };
        this.Login = this.Login.bind(this);
        this.rememberMeOnChange = this.rememberMeOnChange.bind(this);
        this.userEmail = this.userEmail(this);
    }


    rememberMeOnChange() {
        this.setState({ rememberMe: !this.state.rememberMe });
    }

    userEmail() {
        return localStorage.getItem('Login email');
    }



    Login() {
        sessionId = "";
        const requestOptions = {
            method: "POST",
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json',
                "Access-Control-Allow-Origin": "*",
            },
            body: JSON.stringify({
                email: document.getElementById('email').value,
                password: document.getElementById('pass').value
                // password:document.getElementById('pass').value,
            })
        };
        try {
            fetch('http://localhost:8085/login/login', requestOptions)
                .then((response) => response.json())
                .then((messages) => {
                    localStorage.setItem('SessionId',messages.id);
                });
            if (localStorage.getItem('SessionId') !== "") {
                if (this.state.rememberMe)
                localStorage.setItem('Login email', document.getElementById('email').value);
                else localStorage.setItem('Login email', '');
                window.location.href = "http://localhost:3000/";
            }
        } catch (e) { }
    }

    render() {
        return (
            <div class="loginbody">
                <div class="SideSpacer" />

                <div class="mainbody">
                    <div class="TopSpacer" />
                    <div class="Loginbox">
                        <div class="classiclogin">
                            <div class="loginform">

                                <h4><b>Welcome back!</b></h4>
                                <p>We're so excited to see you again!</p>
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
                                <label>
                                    <input type="checkbox" onClick={this.rememberMeOnChange} />
                                    Remember my email
                                </label>
                                <button type="submit" class="btn btn-primary LoginButton" onClick={this.Login}>Login</button>
                                <Link to="/Register">
                                    <p>Register</p>
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

export default loginpage;