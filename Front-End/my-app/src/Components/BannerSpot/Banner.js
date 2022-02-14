import React, { Component, PropTypes } from 'react';
import './Banner.css';
import banner from './BTCInfoBannerv2.gif';

export default class Banner extends Component {



    render() {
        return (
            <div class="BannerSpotHomePage">
                <div class="container">
                    <div class="row py-1">
                        <div class="col-12">
                            <img class="mx-auto d-block" src={banner} width={"50%"}/>
                            <h1 class="textUnderBanner">Sign up today for fastest realtime data</h1>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}


