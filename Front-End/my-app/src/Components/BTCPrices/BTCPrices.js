import React, { Component, PropTypes } from 'react';
import './BTCPrices.css';
import btc from './bitcoin-icon.png';
import eth from './eth.png';
import ltc from './ltc.png';
import coinbase from './coinbase.png';
import binance from './binance.png';
import bitmex from './bitmex.png';
import SockJS from "sockjs-client";
import Stomp from "stompjs";
import { TextField, validator } from 'react-textfield';

var stompClient;
var result;
export default class BTCPrices extends Component {
    constructor(props) {
        super(props);
        this.state = {
            items: [{}],
            result: null,
            btcFilter: false,
            ethFilter: false,
            ltcFilter: false,
            cointype: "all".toUpperCase(),
            filterAmountByCurrency: 0,
            filterAmountByCoinSize: 0,
        }

        this.BoxColour = this.BoxColour.bind(this);
        this.replace = this.replace.bind(this);
        this.setCoinFilter = this.setCoinFilter.bind(this);
        // this.set = this.set.bind(this);
    }

    connect = () => {
        const socket = new SockJS("http://localhost:8081/simulator");
        stompClient = Stomp.over(socket);
        stompClient.connect({}, function (frame) {
            console.log("Connected " + frame);
            stompClient.subscribe("/endpoint/greeting", function (greeting) {
                if (typeof greeting.body !== undefined) {
                    result = greeting.body;
                }
            });
        });
    };




    removeFromArray = function (array, value) {
        var idx = array.indexOf(value);
        if (idx !== -1) {
            array.splice(idx, 1);
        }
        return array;
    }

    componentDidMount() {
        this.connect();

        // for (var i = 0; i < result.lenght; i++) {
        //     if (i.cypto == "ETH")
        //         result = this.removeFromArray(result, result[i]);
        // }
        this.interval = setInterval(() => this.getData(), 100);
    }


    getData() {
        try {
            this.setState({ items: JSON.parse(result) })
            if (this.state.btcFilter)
                this.setState({ items: this.state.items.filter(i => i.cypto.toUpperCase() != "BTC") })
            if (this.state.ltcFilter)
                this.setState({ items: this.state.items.filter(i => i.cypto.toUpperCase() != "LTC") })
            if (this.state.ethFilter)
                this.setState({ items: this.state.items.filter(i => i.cypto.toUpperCase() != "ETH") })
            if (this.state.cointype == "USD".toUpperCase())
                this.setState({ items: this.state.items.filter(i => i.currency.toUpperCase() == "USD") })
            if (this.state.cointype == "EUR".toUpperCase())
                this.setState({ items: this.state.items.filter(i => i.currency.toUpperCase() == "EUR") })
            if (this.state.cointype == "GBP".toUpperCase())
                this.setState({ items: this.state.items.filter(i => i.currency.toUpperCase() == "GBP") })

            // this.setState({ items: this.state.items.filter(i => i.price > this.state.filterAmountByCurrency) })
            // this.setState({ items: this.state.items.filter(i => i.size > this.state.filterAmountByCoinSize) })
            // this.setState({ items: this.state.items.filter(i => i.exchange.toUpperCase() == "bitmex".toUpperCase()) })
        } catch (err) {   //should never be called, just stop the console from being spammed if backend not on 
        }
    }

    setCoinFilter(flipCoin) {
        //displays only these coins
        if (flipCoin == "BTC") this.setState({ btcFilter: !this.state.btcFilter });
        if (flipCoin == "ETH") this.setState({ ethFilter: !this.state.ethFilter });
        if (flipCoin == "LTC") this.setState({ ltcFilter: !this.state.ltcFilter });
    }


    currency(type) {
        //displays only currencies in these types
        if (this.this.state.cointype == "usd") this.setState({ cointype: "usd" });
        else if (this.this.state.cointype == "eur") this.setState({ cointype: "eur" });
        else if (this.this.state.cointype == "gbp") this.setState({ cointype: "gbp" });
        else this.setState({ cointype: "all" });
    }

    displayCurrency(obj) {
        //converts all entries to another currenies, this needs to be added to state, it's not fully implemeneted.
        var coin = "eur".toUpperCase();
        if (coin == "usd".toUpperCase()) return obj.priceInUSD;
        if (coin == "eur".toUpperCase()) return obj.priceInEUR;
        if (coin == "gbp".toUpperCase()) return obj.priceInGBP;
        //return normal type.
        return obj.price;
    }


    setAmoutFilterByCurrency(num) {
        this.setState({ filtfilterAmountByCurrencyerAmount: num });
    }


    // setAmoutFilterByCoinSize(num) {
    //     this.setState({ filterAmountByCoinSize : num });
    // }

    replace(inc) {
        if (this.state.items[inc].cypto.toUpperCase() == "BTC")
            return (<p1><img src={btc} alt="HeadImage" className="btcImage" /></p1>)
        else if (this.state.items[inc].cypto.toUpperCase() == "ETH")
            return (<p1><img src={eth} alt="HeadImage" className="btcImage" /></p1>)
        else if (this.state.items[inc].cypto.toUpperCase() == "LTC")
            return (<p1><img src={ltc} alt="HeadImage" className="btcImage" /></p1>)
    }

    replaceExchange(inc) {
        if (this.state.items[inc].exchange.toUpperCase() == "COINBASE")
            return (<p1><img src={coinbase} alt="HeadImage" className="btcImage" />Coinbase</p1>)
        if (this.state.items[inc].exchange.toUpperCase() == "binance".toUpperCase())
            return (<p1><img src={binance} alt="HeadImage" className="btcImage" />Binance</p1>)
        if (this.state.items[inc].exchange.toUpperCase() == "bitmex".toUpperCase())
            return (<p1><img src={bitmex} alt="HeadImage" className="btcImage" />Bitmex</p1>)
    }

    BoxColour(inc) {
        try {
            return (
                <div class="row text-center">
                    <div class="col-2">
                        {this.replace(inc)} {this.state.items[inc].symbol.toUpperCase()}
                    </div>
                    <div class="col-1">
                        {this.replaceExchange(inc)}
                    </div>
                    <div class="col-2">
                        {(this.state.items[inc].size * this.state.items[inc].price).toFixed(2)}
                    </div>
                    <div class="col-1">
                        {this.state.items[inc].size}
                    </div>
                    <div class="col-2">
                        {this.displayCurrency(this.state.items[inc])}
                    </div>
                    <div class="col-1">
                        {this.state.items[inc].side.toUpperCase()}
                    </div>
                    <div class="col-2">
                        <p1>{new Date(this.state.items[inc].timestamp).getUTCDate() + "/" + new Date(this.state.items[inc].timestamp).getMonth() + "/" + new Date(this.state.items[inc].timestamp).getFullYear() + " " +
                            new Date(this.state.items[inc].timestamp).getHours() + ":" + new Date(this.state.items[inc].timestamp).getMinutes() + ":" + new Date(this.state.items[inc].timestamp).getSeconds()}</p1>
                    </div>
                </div>
            )
        } catch (error) {
            return (
                <div class="row text-center">
                    <div class="col-2">
                        {"Loading..."}
                    </div>
                    <div class="col-1 ">
                        {"Loading..."}
                    </div>
                    <div class="col-2">
                        {"Loading..."}
                    </div>
                    <div class="col-1">
                        {"Loading..."}
                    </div>
                    <div class="col-2">
                        {"Loading..."}
                    </div>
                    <div class="col-1">
                        {"Loading..."}
                    </div>
                    <div class="col-2">
                        {"Loading..."}
                    </div>
                </div>
            )
        }
    }

    render() {
        return (
            <div class="bgColour py-3">
                <div class="container ">
                    <div class="row text-center py-3">
                        <div class="col-2">
                            <p1>Symbol</p1>
                        </div>
                        <div class="col-1">
                            <p1>Exchange</p1>
                        </div>
                        <div class="col-2">
                            <p1>Value of Tranaction</p1>
                        </div>
                        <div class="col-1">
                            <p1>Size of Coin</p1>
                        </div>
                        <div class="col-2">
                            <p1>Value</p1>
                        </div>
                        <div class="col-1">
                            <p1>Side</p1>
                        </div>
                        <div class="col-2">
                            <p1>Time</p1>
                        </div>
                    </div>

                    {this.BoxColour(0)}
                    {this.BoxColour(1)}
                    {this.BoxColour(2)}
                    {this.BoxColour(3)}
                    {this.BoxColour(4)}
                    {this.BoxColour(5)}
                    {this.BoxColour(6)}
                    {this.BoxColour(7)}
                    {this.BoxColour(8)}
                    {this.BoxColour(9)}
                    {this.BoxColour(10)}
                </div>
            </div>
        );
    }
}


