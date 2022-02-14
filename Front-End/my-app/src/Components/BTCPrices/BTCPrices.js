import React, { Component, PropTypes } from 'react';
import './BTCPrices.css';
import btc from './bitcoin-icon.png';
import eth from './eth.png';
import ltc from './ltc.png';
import SockJS from "sockjs-client";
import Stomp from "stompjs";
// import ProgressBar from 'react-bootstrap/ProgressBar'

var stompClient;
var result;
export default class BTCPrices extends Component {
    constructor(props) {
        super(props);
        this.state = {
            items: [{}],
            result: null
        }

        this.BoxColour = this.BoxColour.bind(this);
        this.replace = this.replace.bind(this);
        // this.set = this.set.bind(this);
    }

    connect = () => {
        const socket = new SockJS("http://localhost:8081/simulator");
        stompClient = Stomp.over(socket);
        stompClient.connect({}, function (frame) {
            console.log("Connected " + frame);
            stompClient.subscribe("/endpoint/greeting", function (greeting) {
                if (typeof greeting.body !== undefined)
                    result = greeting.body;
            });
        });
    };

    componentDidMount() {
        this.connect();
        this.interval = setInterval(() => this.getData(), 100);
    }


    getData() {
        //for currency settings.
        // console.log(this.props.currency);
        try {
            this.setState({ items: JSON.parse(result) })
        } catch (err) {   //should never be called, just stop the console from being spammed if backend not on 
        }
    }

    BoxHeader() {
        return (
            <div className="BTCPrice1">

                <div className="Headers">
                    <p1><b>Cypto</b></p1>
                </div>

                <div className="Headers">
                    <p1><b>Currency</b></p1>
                </div>

                <div className="Headers">
                    <p1><b>Price</b></p1>
                </div>

                <div className="Headers">
                    <p1><b>Exchange</b></p1>
                </div>

                <div className="Headers">
                    <p1><b>Time</b></p1>
                </div>

                <div className="Headers">
                    <p1><b>Tranaction Size</b></p1>
                </div>
            </div>
        )
    }


    replace(inc) {
        if (this.state.items[inc].cypto.toUpperCase() == "BTC")
            return (<p1><img src={btc} alt="HeadImage" className="btcImage" /> {this.state.items[inc].cypto.toUpperCase()}</p1>)
        else if (this.state.items[inc].cypto.toUpperCase() == "ETH")
            return (<p1><img src={eth} alt="HeadImage" className="btcImage" /> {this.state.items[inc].cypto.toUpperCase()}</p1>)
        else if (this.state.items[inc].cypto.toUpperCase() == "LTC")
            return (<p1><img src={ltc} alt="HeadImage" className="btcImage" /> {this.state.items[inc].cypto.toUpperCase()}</p1>)
    }


    BoxColour(e, inc) {
        // console.log("I see: " + this.state.items.length);
        try {
            return (
                <div className={e}>
                    <div className="Section">
                        {this.replace(inc)}
                    </div>

                    <div className="Section">
                        <p1>{this.state.items[inc].currency.toUpperCase()}</p1>
                    </div>

                    <div className="Section">
                        <p1>{this.state.items[inc].price}</p1>
                    </div>

                    <div className="Section">
                        <p1>{this.state.items[inc].exchange.toUpperCase()}</p1>
                    </div>

                    <div className="Section">
                        <p1>{new Date(this.state.items[inc].timestamp).getUTCDate() + "/" + new Date(this.state.items[inc].timestamp).getMonth() + "/" + new Date(this.state.items[inc].timestamp).getFullYear() + " " +
                            new Date(this.state.items[inc].timestamp).getHours() + ":" + new Date(this.state.items[inc].timestamp).getMinutes() + ":" + new Date(this.state.items[inc].timestamp).getSeconds()}</p1>
                    </div>

                    <div className="Section">
                        <p1>{this.state.items[inc].size}</p1>
                    </div>
                </div>
            )
        } catch (error) {
            <div className={e}>
                <div className="Section">
                    <p1>Loading...</p1>
                </div>

                <div className="Section">
                    <p1>Loading...</p1>
                </div>

                <div className="Section">
                    <p1>Loading...</p1>
                </div>

                <div className="Section">
                    <p1>Loading...</p1>
                </div>

                <div className="Section">
                    <p1>Loading...</p1>
                </div>

                <div className="Section">
                    <p1>Loading...</p1>
                </div>
            </div>
        }
    }

    render() {
        return (
            <div className="bgColour">


                <div className="BTCPrices2">
                    <div className="filters">
                        {this.BoxHeader()}
                    </div>
                </div>

                <div className="BTCPrices">
                    <div className="LeftPad" />
                    <div className="BTCPricesContainer">
                        {this.BoxColour("BTCPrice2", 0)}
                        {this.BoxColour("BTCPrice3", 1)}
                        {this.BoxColour("BTCPrice2", 2)}
                        {this.BoxColour("BTCPrice3", 3)}
                        {this.BoxColour("BTCPrice2", 4)}
                        {this.BoxColour("BTCPrice3", 5)}
                        {this.BoxColour("BTCPrice2", 6)}
                        {this.BoxColour("BTCPrice3", 7)}
                    </div>
                </div>


                <div className="BTCRealTimePricesFilter">
                    {/* <button type="button" class="btn btn-primary btn-sm dropupbtn">Price Table Filters</button>
                        <div class="dropup-content">
                            <a href="#">Link 1</a>
                            <a href="#">Link 2</a>
                            <a href="#">Link 3</a>
                    </div> */}

                    <div class="dropup">
                        <button type="button" class="btn btn-primary btn-sm dropbtn">Price Table Filters</button>
                        <div class="dropup-content">
                            <a>
                                <div class="form-check form-switch">
                                    <input class="form-check-input" type="checkbox" id="flexSwitchCheckChecked" />
                                    <label class="form-check-label" for="flexSwitchCheckChecked">Bitcoin</label>
                                </div>
                            </a>
                            <a>
                                <div class="form-check form-switch">
                                    <input class="form-check-input" type="checkbox" id="flexSwitchCheckChecked" />
                                    <label class="form-check-label" for="flexSwitchCheckChecked">Ethereum</label>
                                </div>
                            </a>
                            <a>
                                <div class="form-check form-switch">
                                    <input class="form-check-input" type="checkbox" id="flexSwitchCheckChecked" />
                                    <label class="form-check-label" for="flexSwitchCheckChecked">Litecoin</label>
                                </div>
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}


