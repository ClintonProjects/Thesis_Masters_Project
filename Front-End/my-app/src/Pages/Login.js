import React, { Component } from 'react';
import '../App.css';
import Nav from '../Components/NavigationBar/bar2.js';
import Footer from '../Components/Footer/footer2.js';
import Log from '../Components/Loginpage/loginpage.js';


class Login extends Component {
    constructor(props) {
        super(props);
        this.state = {
        };
    }

    render() {
        console.log("currency: "+ this.props.currency);
        return (
            <div className="App">
                <Nav currency = {this.props.currency}/>
                <Log/>
                <Footer />
            </div>
        );
    }
}

export default Login;
