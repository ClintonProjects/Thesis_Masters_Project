import './App.css';
import Nav from './Components/NavigationBar/bar.js';
import Footer from './Components/Footer/footer.js';
import Analytics from './Funuctions/InformationGather/Ipgrab.js';
import React, { Component, PropTypes } from 'react';
import { Route, BrowserRouter as Router, Switch } from "react-router-dom";
import Homepage from "./Pages/Homepage.js";
import Login from "./Pages/Login.js";
import AdminPannel from "./Pages/AdminPannel.js";

class App extends Component {
  // constructor(props) {
  //   super(props);
  //   this.state = {
  //   };
  // }


  componentDidMount() {
    this.livechat();
    // Analytics.functions.getIP();
  }

  livechat() {
    var Tawk_API=Tawk_API||{}, Tawk_LoadStart=new Date();
    (function(){
    var s1=document.createElement("script"),s0=document.getElementsByTagName("script")[0];
    s1.async=true;
    s1.src='https://embed.tawk.to/613c1a4925797d7a89fe609b/1ff9cdcfv';
    s1.charset='UTF-8';
    s1.setAttribute('crossorigin','*');
    s0.parentNode.insertBefore(s1,s0);
    })();;
  }

  render() {
    return (

      <Router>
        <div>
          {/* <Nav /> */}
          <Switch>
          <Route path="/login" component={Login} />
          <Route path="/" exact component={Homepage} />
          <Route path="/admin" exact component={AdminPannel} />
          </Switch>
          {/* <Footer /> */}
        </div>
      </Router>

      
    );
  }
}

export default App;
