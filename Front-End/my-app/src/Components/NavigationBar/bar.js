import React, { Component } from 'react';
import './bar.css';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faUser, faTicketAlt, faSignInAlt, faAngleDown } from '@fortawesome/free-solid-svg-icons';
import pikachu from './coin.png';
import Homepage from '../../Pages/Homepage.js';
import { Route, BrowserRouter as Router, Switch, Link } from "react-router-dom";
import eu from './eu.png';
import usa from './usa.png';
import globe from './globe.png';


class Bar extends Component {

    constructor(props) {
        super(props);
        this.state = {
            flag: 'All',
        };
        this.displayFlagOnNav = this.displayFlagOnNav.bind(this);
        this.toggleFlag=this.toggleFlag.bind(this);
    }


    displayFlagOnNav(flag) {
        console.log('Display flag = ' + flag);
        if (flag === "All") {
            return (<a href="javascript:void(0)" class="dropbtn" onClick={this.toggleFlag('All')}><img src={globe} alt="HeadImage" class="euflag" /> All</a>);
        } else if (flag === "EU") {
            return (<a href="javascript:void(0)" class="dropbtn" onClick={this.toggleFlag('EU')}  ><img src={eu} alt="HeadImage" class="euflag" /> Euro</a>);
        } else if (flag === "USA")
            return (<a href="javascript:void(0)" class="dropbtn" onClick={this.toggleFlag('USA')}><img src={usa} alt="HeadImage" class="euflag" /> USD</a>);
    }

    toggleFlag(flag) {
        console.log('Update flag = ' + flag);
        this.state.flag = flag;
        //this.props.currency = flag;
    }


    render() {
        return (
            <div className="navigationbar">
                <div className="smallspace" />
                <div className="LeftOfNav">
                    <div class="nav-brand">
                        <Link to="/">
                            <h1><img src={pikachu} alt="HeadImage" class="pikachuimage" /><b>BTCInfo</b></h1>
                        </Link>
                    </div>
                </div>
                <div class="nav-support-button">
                <h1><Link to="/login">Sign Up/In</Link></h1>
                    {/* <li class="dropdown">
                        <a href="javascript:void(0)" class="dropbtn">{this.displayFlagOnNav(this.state.flag)}</a>
                        <div class="dropdown-content">
                            <a href="#"><img src={globe} alt="HeadImage" class="euflag" onClick={(e) => this.toggleFlag('All')}/> All</a>
                            <a href="#"><img src={eu} alt="HeadImage" class="euflag" onClick={(e) => this.toggleFlag('EU')}/> Euro</a>
                            <a href="#"><img src={usa} alt="HeadImage" class="euflag" onClick={(e) => this.toggleFlag('USA')}/> USD</a>
                        </div>
                    </li> */}
                </div>
            </div>
        );
    }
}

export default Bar;

