import React, { Component } from 'react';
import './BTCRealTimePrices.css';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faCog, faArrowDown, faArrowUp } from '@fortawesome/free-solid-svg-icons';

class BTCRealTimePrices extends Component {
    constructor(props) {
        super(props);
        this.state = {
            items: "",
        };
        this.priceColour = this.priceColour.bind(this);
    }

    componentDidMount() {
        this.interval = setInterval(() => this.getData(), 7000);
    }


    getData() {
        // GET request using fetch with error handling
        const headers = {
            'Content-Type': 'application/json',
            "Access-Control-Allow-Origin": "*",

        }
        fetch('http://localhost:8081/apis/btcprice', { headers })
            .then(async response => {
                const data = await response.json();

                // check for error response
                if (!response.ok) {
                    // get error message from body or default to response statusText
                    //           console.log("not ok");
                    const error = (data && data.message) || response.statusText;
                    return Promise.reject(error);
                }
                //         console.log("ok");
                this.setState({ items: data })
                // console.log(this.state.items);
            })
            .catch(error => {
                this.setState({ errorMessage: error.toString() });
                //           console.error('There was an error!', error);
            });
    }


    priceColour() {
        if (this.state.items.priceChange >= 0) {
            return (<h2 className="greenTextColour">${this.state.items.currentPrice}</h2>)
        } else if (this.state.items.priceChange < 0) {
            return (<h2 className="redTextColour">${this.state.items.currentPrice}</h2>)
        }

    }

    percentageColour() {
        if (this.state.items.priceChange >= 0) {
            console.log("price change " + this.state.items.priceChange);
            return (<h2 className="greenTextColour">{this.state.items.priceChange}% <FontAwesomeIcon icon={faArrowUp} /></h2>)
        } else if (this.state.items.priceChange < 0) {
            return (<h2 className="redTextColour">{this.state.items.priceChange}% <FontAwesomeIcon icon={faArrowDown} /></h2>)
        }
    }


    render() {
        return (
            <div className="pad-top">
                <div className="BTCRealTimePrices">
                    <div className="leftPadding" />
                    <div class="stopwatchspace">
                        <div className="pricedisplay">
                            <h2>Bitcoin 1 Min</h2>
                            {/* <h1>(10% <FontAwesomeIcon icon={faArrowDown}/>)</h1> */}
                        </div>
                    </div>

                    <div className="LeftPadding" />

                    <div class="stopwatchspace">
                        <div className="pricedisplay">
                            {this.priceColour()}
                            {/* <h1>(10% <FontAwesomeIcon icon={faArrowDown}/>)</h1> */}
                        </div>
                    </div>

                    <div className="LeftPadding" />

                    <div class="stopwatchspace">
                        <div className="pricedisplay">
                            {this.percentageColour()}
                        </div>
                    </div>
                </div>
                <div className="pad-bottom" />
            </div>

        );
    }
}

export default BTCRealTimePrices;
