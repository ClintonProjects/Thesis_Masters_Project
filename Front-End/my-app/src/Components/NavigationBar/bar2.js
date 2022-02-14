import React, { Component } from 'react';
import './bar.css';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faUser, faTicketAlt, faSignInAlt, faAngleDown } from '@fortawesome/free-solid-svg-icons';
import pikachu from './coin.png';
import Homepage from '../../Pages/PreloginHomepage.js';
import { Route, BrowserRouter as Router, Switch, Link } from "react-router-dom";
import eu from './eu.png';
import usa from './usa.png';
import globe from './globe.png';
import { Navbar, Nav, NavDropdown, Form, FormControl, Button, Container, Dropdown, DropdownButton } from 'react-bootstrap'

class Bar extends Component {

    constructor(props) {
        super(props);
        this.state = {
            flag: 'All',
            userActive: false,
        };
        this.displayFlagOnNav = this.displayFlagOnNav.bind(this);
        this.toggleFlag = this.toggleFlag.bind(this);
        this.signOut = this.signOut.bind(this);
        this.isVerifyUserSignedIn = this.isVerifyUserSignedIn.bind(this);
        this.redriectToPage = this.redriectToPage.bind(this);
    }


    displayFlagOnNav(flag) {
        console.log('Display flag = ' + flag);
        if (flag === "All") {
            return (<a href="javascript:void(0)" onClick={this.toggleFlag('All')}><img src={globe} alt="HeadImage" class="euflag" /> All</a>);
        } else if (flag === "EU") {
            return (<a href="javascript:void(0)" onClick={this.toggleFlag('EU')}  ><img src={eu} alt="HeadImage" class="euflag" /> Euro</a>);
        } else if (flag === "USA")
            return (<a href="javascript:void(0)" onClick={this.toggleFlag('USA')}><img src={usa} alt="HeadImage" class="euflag" /> USD</a>);
    }

    toggleFlag(flag) {
        console.log('Update flag = ' + flag);
        this.state.flag = flag;
        //this.props.currency = flag;
    }

    signOut() {
        localStorage.removeItem('SessionId');
        window.location.href = "http://localhost:3000/";
    }

    redriectToPage(link) {
        if (!this.state.userActive)
            window.location.href = "http://localhost:3000" + link;
    }

    isVerifyUserSignedIn() {
        console.log("[NAV BAR] id is checked " + this.state.userActive + " " + localStorage.getItem('SessionId'));
        // || localStorage.getItem('SessionId').length === 0
        if (localStorage.getItem('SessionId') != null || localStorage.getItem('SessionId'))
            this.setState({ userActive: true });
        else
            this.setState({ userActive: false });
    }

    handleStatusChange(satus) {
        this.isVerifyUserSignedIn();
    }

    componentDidMount() {
        this.isVerifyUserSignedIn();
    }



    render() {
        const isVerifyUserSignedIn = this.state.isVerifyUserSignedIn;
        return (
            <header class="nav-colour">
                <div class="container py-1">
                    <a class="navbar-brand text-white" href="#">
                        <img src={pikachu} alt="HeadImage" height={"30px"} width={"30px"} />
                        <b>BTCInfo</b>
                    </a>
                    <div class="float-end">
                        <NavDropdown
                            id="nav-dropdown-dark-example"
                            title={this.displayFlagOnNav(this.state.flag)}
                            menuVariant="dark"
                            align="end">
                            <NavDropdown.Item onClick={(e) => this.toggleFlag('All')}><img src={globe} alt="HeadImage" class="euflag" /> All</NavDropdown.Item>
                            <NavDropdown.Item onClick={(e) => this.toggleFlag('EU')}><img src={eu} alt="HeadImage" class="euflag" /> Euro</NavDropdown.Item>
                            <NavDropdown.Item onClick={(e) => this.toggleFlag('USA')}><img src={usa} alt="HeadImage" class="euflag" /> USD</NavDropdown.Item>
                            <NavDropdown.Divider />
                            {!this.state.userActive ? <NavDropdown.Item onClick={(e) => this.redriectToPage('/Register')}>
                                <FontAwesomeIcon icon={faUser} />
                                &nbsp; Register
                            </NavDropdown.Item> : ''}

                            {!this.state.userActive ?
                                <NavDropdown.Item onClick={(e) => this.redriectToPage('/login')}>
                                    <FontAwesomeIcon icon={faSignInAlt} />
                                    &nbsp; Sign In
                                </NavDropdown.Item>
                                :
                                <NavDropdown.Item onClick={(e) => this.signOut()}>
                                    <FontAwesomeIcon icon={faSignInAlt} />
                                    &nbsp; Sign Out
                                </NavDropdown.Item>}
                        </NavDropdown>
                    </div>
                </div>
            </header>
        )
    }
}

export default Bar;

