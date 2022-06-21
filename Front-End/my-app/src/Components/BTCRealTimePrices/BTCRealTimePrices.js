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
            items: null,
            currency: "GBP".toUpperCase(),
        };
        this.priceColour = this.priceColour.bind(this);
        this.data = this.data.bind(this);
        this.displayResult = this.displayResult.bind(this);
        this.displayValue = this.displayValue.bind(this);
        this.currencySettings = this.currencySettings.bind(this);
    }

    componentDidMount() {
        this.connect();
        this.interval = setInterval(() => this.getData(), 1000);
    }

    getData() {
        try {
            this.currencySettings();
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



    displayResult(exchange, cypto) {
        try {
            if (this.state.items[exchange.toUpperCase() + "/" + cypto.toUpperCase() + "/" + this.state.currency.toUpperCase()]) {
                for (var i in this.state.items) {
                    if (i == exchange.toUpperCase() + "/" + cypto.toUpperCase() + "/" + this.state.currency.toUpperCase()) {
                        if (this.state.items[i].currentPrice.toFixed(2) > 0)
                            return this.state.items[i].currentPrice.toFixed(2);
                    }
                }
            }
        } catch (e) { }
        return "Not Enough Data";
    }



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

    displayValue(currency) {
        if (currency.toUpperCase() == "usd".toUpperCase())
            return "$";
        if (currency.toUpperCase() == "eur".toUpperCase())
            return "€";
        if (currency.toUpperCase() == "gbp".toUpperCase())
            return "£";
    }

    currencySettings() {
        try {
            if (localStorage.getItem('currency') !== null) {
                this.setState({ currency: localStorage.getItem('currency').toUpperCase() });
                return
            }
            this.setState({ currency: "USD" });
        } catch (e) { }
    }


    data(image, exchange, cypto) {

        return (
            <div class="row">
                {/* Blank Space, easier then using margin */}
                <div class="col-2" />

                <div class="col-2">
                    <p class="h6">
                        <img src={image} class="cbImage" />&ensp;{exchange == "All" ? "Whole market" : exchange}
                    </p>
                </div>
                <div class="col-2 py-1">
                    <p class="h6">
                        BTC: {this.displayValue(this.state.currency)} {this.displayResult(exchange, "BTC")}
                    </p>
                </div>
                <div class="col-2 py-1">
                    <p class="h6">
                        ETH: {this.displayValue(this.state.currency)} {this.displayResult(exchange, "ETH")}
                    </p>
                </div>
                <div class="col-2 py-1">
                    <p class="h6">
                        LTC: {this.displayValue(this.state.currency)} {this.displayResult(exchange, "LTC")}
                    </p>
                </div>

                {/* <div class="col-3" /> */}
            </div>
        )
    }

    render() {
        return (
            <div class="activeExchanges">
                <div class="container">
                    <div class=" no-gutters">
                        <p class="h6 text-center">
                            1 min moving averages
                        </p>
                    </div>
                    <div class="row py-2  no-gutters">
                        {this.data(null, "All", "BTC")}
                        {this.data(coinbaseLogo, "COINBASE", "BTC")}
                        {this.data(bainceLogo, "BINANCE", "BTC")}
                        {this.data(bitmexLogo, "BITMEX", "BTC")}
                    </div>
                </div>
            </div>
        );
    }
}

export default BTCRealTimePrices;
