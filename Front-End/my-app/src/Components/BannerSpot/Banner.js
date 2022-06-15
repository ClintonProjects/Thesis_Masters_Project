import React, { Component, PropTypes } from 'react';
import './Banner.css';
import banner from './BTCInfoBannerv2.gif';

export default class Banner extends Component {

    constructor(props) {
        super(props);
        this.state = {
            userActive: false,
        };
        this.isVerifyUserSignedIn = this.isVerifyUserSignedIn.bind(this);
    }

    isVerifyUserSignedIn() {
        console.log("[NAV BAR] id is checked " + this.state.userActive + " " + localStorage.getItem('SessionId'));
        // || localStorage.getItem('SessionId').length === 0
        if (localStorage.getItem('SessionId') != null || localStorage.getItem('SessionId'))
            return true;
        else
            return false;
    }



    render() {
        return (
            <div class="BannerSpotHomePage">
                <div class="container">
                    <div class="row py-1">
                        <div class="col-12  no-gutters">
                            <img class="mx-auto d-block" src={banner} width={"50%"} />
                            {this.isVerifyUserSignedIn ? "" : <h1 class="textUnderBanner">Sign up today for fastest realtime data</h1>}
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}


