import React, { Component } from 'react';
import './Login.css';
import { Route, BrowserRouter as Router, Switch, Link } from "react-router-dom";
import Hash from '../../Funuctions/Hash/Password.js';


class loginpage extends Component {
    constructor(props) {
        super(props);
        this.state = {
        };
        this.Login = this.Login.bind(this);
    }


    Login() {
        let email = "a";
        let password= "a";

        const requestOptions = {
            method: "POST",
            headers: {
                'Content-Type': 'application/json',
                "Access-Control-Allow-Origin": "*",
            },
            body: JSON.stringify(
                { 
                "email": email, 
                "password": password }
                )
        };

        fetch('http://localhost:8085/login/Login', requestOptions)
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
                console.error('There was an error!', error);
            });
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
                                    <input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="" />
                                </div>

                                <div class="form-group">
                                    <label for="exampleInputPassword1" class="passwordtexts"><b>Password</b></label>
                                    <input type="password" class="form-control" id="exampleInputPassword1" placeholder="" />
                                    {/* <p1>Forgot your password?</p1> */}
                                </div>
                                <button type="submit" class="btn btn-primary LoginButton" onClick={this.Login}>Login</button>
                                <Link to="/Register">
                                    <p>Register</p>

                                </Link>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="SideSpacer" />
            </div>



        );
    }
}

export default loginpage;