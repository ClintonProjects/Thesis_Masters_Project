import React, { Component, PropTypes } from 'react';
import ProgressBar from 'react-bootstrap/ProgressBar'
import './BuySellBar.css';
import SockJS from "sockjs-client";
import Stomp from "stompjs";
import server from "../../Funuctions/Server.js";

var stompClient;
var result;
export default class BuySellBar extends Component {
    constructor(props) {
        super(props);
        this.state = {
        }
    }

    connect = () => {
        const socket = new SockJS("https://localhost:8080/simulator");
        stompClient = Stomp.over(socket);
        stompClient.connect({}, function (frame) {
            // console.log("Connected " + frame);
            stompClient.subscribe("/endpoint/wow", function (greeting) {
                if (typeof greeting.body !== undefined)
                    result = Math.round(greeting.body * 100) / 100;
            });
        });
    };

    componentDidMount() {
        this.connect();
        this.interval = setInterval(() => this.getData(), 1000);
    }

    getData() {
        //for currency settings.
        // console.log(this.props.currency);
        try {
            this.setState({ items: JSON.parse(result) })
        } catch (err) {   //should never be called, just stop the console from being spammed if backend not on 
        }
    }

    render() {
        return (

            <div class="BuySellBar no-gutters">
                <div class="container no-gutters">
                    <p class="h6 text-center">
                        1 Min average of whole market
                    </p>
                    <div class="row py-1">
                        {/* Blank Space, easier then using margin */}
                        <div class="col-2" />
                        <div class="col-4 text-start">
                            <h3>Buy: {result - 0}%</h3>
                        </div>
                        <div class="col-4 text-end">
                            <h3>Sell:  {100 - result}%</h3>
                        </div>
                        {/* Blank Space, easier then using margin */}
                        <div class="col-1" />

                        {/* Blank Space, easier then using margin */}
                        <div class="col-2" />
                        <div class="col-8">
                            <ProgressBar>
                                {/* 0.1 to fix bug were the bar wasn't filling */}
                                <ProgressBar striped variant="success" now={result + 0.1} key={1} />
                                <ProgressBar variant="danger" now={100 - result} key={2} />
                            </ProgressBar>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}


