import React, { Component } from 'react';
import './Login.css';
import { Route, BrowserRouter as Router, Switch, Link } from "react-router-dom";


class loginpage extends Component {
    constructor(props) {
        super(props);
        this.state = {
        };
        this.Login = this.Login.bind(this);
    }


    Login() {
        const requestOptions = {
            method: "POST",
            headers: {
                'Content-Type':'application/json',
                'Accept': 'application/json',
                "Access-Control-Allow-Origin": "*",
            },
            body: JSON.stringify({
                email:document.getElementById('email').value,
                password:document.getElementById('pass').value,
            })
        };

        fetch('http://localhost:8085/login/login', requestOptions)
        .then((response) => response.json())
        .then((messages) => {console.log("messages");
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
                                    <input type="email" class="form-control" id="email" aria-describedby="emailHelp" placeholder="" />
                                </div>

                                <div class="form-group">
                                    <label for="exampleInputPassword1" class="passwordtexts"><b>Password</b></label>
                                    <input type="password" class="form-control" id='pass' placeholder="" />
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