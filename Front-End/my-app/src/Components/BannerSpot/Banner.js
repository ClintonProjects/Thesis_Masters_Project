import React, { Component, PropTypes } from 'react';
import './Banner.css';
import banner from './BTCInfoBannerv2.gif';

export default class Banner extends Component {
    


    render() {
        return (

        <div className="BannerSpotHomePage">
            <img src={banner} class="btcImage" />
            <div className="pad"> <h1>Sign up today for fastest realtime data</h1> </div>
           </div>

        );
    }
}


