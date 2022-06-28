import React, { Component, PropTypes } from 'react';
import './TVGraph.css';
import backgroundImage from './Images/hellhound.jpg';
import { Button } from 'react-bootstrap';
import {Helmet} from "react-helmet";
import TradingViewWidget, { Themes } from 'react-tradingview-widget';

class TVGraph extends Component {
    constructor(props) {
        super(props);
        this.state = {
        };

        this.TradingView = this.TradingView.bind(this);
    }


    TradingView() {
        return (  
            <TradingViewWidget
            symbol="BINANCE:BTCUSDT"
            theme={Themes.DARK}
            locale="fr"
            autosize
          />
            )
    }



    render() {
        return (
            <div class="HeaderBackground">
            <div class="chart">
            {this.TradingView()}
            </div>
            </div>
        );
    }

    
}

export default TVGraph;