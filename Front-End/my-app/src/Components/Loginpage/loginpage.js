import React, { Component } from 'react';
import './Login.css';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faLongArrowAltLeft } from '@fortawesome/free-solid-svg-icons';
import { Route, BrowserRouter as Router, Switch, Link } from "react-router-dom";

class loginpage extends Component {
    constructor(props) {
        super(props);
        this.state = {
        };
    }


    Login() {
        var email = document.getElementById('exampleInputEmail1').value;
        var password = document.getElementById('exampleInputPassword1').value;

        let data = {
            username: email,
            password: password,
            credentialId : ""
        };

        (async () => {
            const requestOptions = {
                method: "POST",
                headers: {
                    'Content-Type': 'application/json',
                    "Access-Control-Allow-Origin": "*",
                },
                body: JSON.stringify(data)

            };
            fetch('http://localhost:8180/auth/realms/Pika.gold/login-actions/authenticate?session_code=1hRXCoJ8NgQasNvfsNoTxv2sio71V06-uLeIp3jcQp0&execution=0782da67-7f74-44c2-972a-457786e216bc&client_id=pika.gold&tab_id=1bkEWfDys2A', requestOptions)
        }
        )();
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
                                <Link to="/">

                                    <p><FontAwesomeIcon icon={faLongArrowAltLeft} /> Back To Homepage</p>
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