import './App.css';
import Nav from './Components/NavigationBar/bar.js';
import Footer from './Components/Footer/footer.js';
import Analytics from './Funuctions/InformationGather/Ipgrab.js';
import React, { Component, PropTypes, useEffect, useState } from 'react';
import { Route, BrowserRouter as Router, Switch } from "react-router-dom";
import PreHomepage from "./Pages/PreloginHomepage.js";
import PostHomepage from "./Pages/PostloginHomepage.js";
import Login from "./Pages/Login.js";
import AdminPannel from "./Pages/AdminPannel.js";
import Register from "./Pages/Register.js";
import VerifyUserLogin from "./Funuctions/HttpRequests/VerifyUserLogin.js"
// import SockJS from "sockjs-client";
// import Stomp from "stompjs";

// var stompClient;

var currency = "all";

class App extends Component {

  constructor(props) {
    super(props);
    this.state = {
      userActive: false,
    };

    this.isVerifyUserSignedIn = this.isVerifyUserSignedIn.bind(this);
  }

  componentDidMount() {
    this.livechat();
    // Analytics.functions.getIP(); 
    this.isVerifyUserSignedIn();
  }
  // Similar to componentDidMount and componentDidUpdate:

  handleStatusChange(satus) {
    this.isVerifyUserSignedIn();
  }


  // connect = () => {
  //   const socket = new SockJS("http://localhost:8081/simulator");
  //   stompClient = Stomp.over(socket);
  //   stompClient.connect({}, function (frame) {
  //     console.log("Connected " + frame);
  //     stompClient.subscribe("/endpoint/greeting", function (greeting) {
  //       console.log("hi " + greeting);
  //     });
  //     // stompClient.send("/app/hello", {});
  //   });
  // };



  livechat() {
    var Tawk_API = Tawk_API || {}, Tawk_LoadStart = new Date();
    (function () {
      var s1 = document.createElement("script"), s0 = document.getElementsByTagName("script")[0];
      s1.async = true;
      s1.src = 'https://embed.tawk.to/613c1a4925797d7a89fe609b/1ff9cdcfv';
      s1.charset = 'UTF-8';
      s1.setAttribute('crossorigin', '*');
      s0.parentNode.insertBefore(s1, s0);
    })();;
  }

  isVerifyUserSignedIn() {
    console.log("id is checked " + this.state.userActive + " " + localStorage.getItem('SessionId'));
    if (!localStorage.getItem('SessionId') || localStorage.getItem('SessionId').length === 0)
      this.setState({ userActive: true });
    else
      this.setState({ userActive: false });
  }



  render() {
    return (
      <Router>
        <div>
          {/* <Nav /> */}
          <Switch>
            <Route path="/login" component={Login} />
            {this.state.userActive ?
              <Route exact path="/" render={(props) => <PreHomepage userActive={this.state.userActive} {...props} />} />
              : <Route exact path="/" render={(props) => <PostHomepage userActive={this.state.userActive} {...props} />} />}
            <Route path="/admin" exact component={AdminPannel} />
            <Route path="/register" exact component={Register} />
          </Switch>
        </div>
      </Router>


    );
  }
}

export default App;
