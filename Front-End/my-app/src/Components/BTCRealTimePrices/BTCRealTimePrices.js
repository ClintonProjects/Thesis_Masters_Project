import React, { Component, PropTypes } from 'react';
import './BTCRealTimePrices.css';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faCog, faArrowDown, faArrowUp } from '@fortawesome/free-solid-svg-icons';
import coinbaseLogo from './Coinbase.png';
import bainceLogo from './binance.png';
import bitmexLogo from './bitmex-logo.png';
import './ExchangeActivePrices.css';
import SockJS from "sockjs-client";
import Stomp from "stompjs";
import btc from './bitcoin-icon.png';
import eth from './eth.png';
import ltc from './ltc.png';

var stompClient;
var result;
class BTCRealTimePrices extends Component {
    constructor(props) {
        super(props);
        this.state = {
            items: {},
        };
        this.priceColour = this.priceColour.bind(this);
        this.data = this.data.bind(this);
    }

    componentDidMount() {
        this.connect();
        this.interval = setInterval(() => this.getData(), 100);
    }

    getData() {
        try {
            this.setState({ items: JSON.parse(result) });
            // console.log(this.state.items.currentPrice);
        } catch (err) {   //should never be called, just stop the console from being spammed if backend not on 
        }
    }

    connect = () => {
        const socket = new SockJS("http://localhost:8080/simulator");
        stompClient = Stomp.over(socket);
        stompClient.connect({}, function (frame) {
            // console.log("Connected " + frame);
            stompClient.subscribe("/endpoint/getExchangeData", function (greeting) {
                if (typeof greeting.body !== undefined) {
                    result = greeting.body;
                    // console.log("websocket result: " + result);
                }
            });
        });
    };


    priceColour() {
        if (this.state.items.priceChange >= 0) {
            return (<h2 className="greenTextColour">${(this.state.items.currentPrice).toFixed(2)}</h2>)
        } else if (this.state.items.priceChange < 0) {
            return (<h2 className="redTextColour">${(this.state.items.currentPrice).toFixed(2)}</h2>)
        }

    }

    percentageColour() {
        if (this.state.items.priceChange >= 0) {
            // console.log("price change " + this.state.items.priceChange);
            return (<h2 className="greenTextColour">{this.state.items.priceChange.toFixed(2)}% <FontAwesomeIcon icon={faArrowUp} /></h2>)
        } else if (this.state.items.priceChange < 0) {
            return (<h2 className="redTextColour">{this.state.items.priceChange.toFixed(2)}% <FontAwesomeIcon icon={faArrowDown} /></h2>)
        }
    }


    data(image, exchange) {
        return (
            <div class="row">
                {/* Blank Space, easier then using margin */}
                <div class="col-3"/>

                <div class="col-1">
                    <p2>
                        <img src={image} class="cbImage" />{exchange}
                    </p2>
                </div>
                <div class="col-2 py-1">
                    <h3>
                        {/* <img src={btc} class="cbImage" /> */}
                        BTC: {this.priceColour()}
                    </h3>
                </div>
                <div class="col-2 py-1">
                    <h3>
                        {/* <img src={eth} class="cbImage"/> */}
                        ETH: {this.priceColour()}
                    </h3>
                </div>
                <div class="col-2 py-1">
                    <h3>
                        {/* <img src={ltc} class="cbImage" /> */}
                        LTC: {this.priceColour()}
                    </h3>
                </div>

                {/* <div class="col-3" /> */}
            </div>
        )
    }





    render() {
        return (
            <div class="activeExchanges">
                <div class="container">
                    <div class="row py-2">
                        {this.data(coinbaseLogo, "Coinbase")}
                        {this.data(bainceLogo, "Binance")}
                        {this.data(bitmexLogo, "Bitmex")}
                    </div>
                </div>
            </div>
        );
    }
}

export default BTCRealTimePrices;
