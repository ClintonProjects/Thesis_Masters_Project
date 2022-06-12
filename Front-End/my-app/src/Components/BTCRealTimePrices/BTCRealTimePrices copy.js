// import React, { Component, PropTypes } from 'react';
// import './BTCRealTimePrices.css';
// import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
// import { faCog, faArrowDown, faArrowUp } from '@fortawesome/free-solid-svg-icons';
// import coinbaseLogo from './Coinbase.png';
// import bainceLogo from './binance.png';
// import bitmexLogo from './bitmex-logo.png';
// import './ExchangeActivePrices.css';
// import SockJS from "sockjs-client";
// import Stomp from "stompjs";


// var stompClient;
// var result;
// class BTCRealTimePrices extends Component {
//     constructor(props) {
//         super(props);
//         this.state = {
//             items: {},
//         };
//         this.priceColour = this.priceColour.bind(this);
//     }

//     componentDidMount() {
//         this.connect();
//         this.interval = setInterval(() => this.getData(), 100);
//     }

//     getData() {
//         try {
//             this.setState({ items: JSON.parse(result) });
//             // console.log(this.state.items.currentPrice);
//         } catch (err) {   //should never be called, just stop the console from being spammed if backend not on 
//         }
//     }

//     connect = () => {
//         const socket = new SockJS("http://localhost:8080/simulator");
//         stompClient = Stomp.over(socket);
//         stompClient.connect({}, function (frame) {
//             console.log("Connected " + frame);
//             stompClient.subscribe("/endpoint/getExchangeData", function (greeting) {
//                 if (typeof greeting.body !== undefined) {
//                     result = greeting.body;
//                     // console.log("websocket result: " + result);
//                 }
//             });
//         });
//     };


//     priceColour() {
//         if (this.state.items.priceChange >= 0) {
//             return (<h2 className="greenTextColour">${(this.state.items.currentPrice).toFixed(2)}</h2>)
//         } else if (this.state.items.priceChange < 0) {
//             return (<h2 className="redTextColour">${(this.state.items.currentPrice).toFixed(2)}</h2>)
//         }

//     }

//     percentageColour() {
//         if (this.state.items.priceChange >= 0) {
//             console.log("price change " + this.state.items.priceChange);
//             return (<h2 className="greenTextColour">{this.state.items.priceChange.toFixed(2)}% <FontAwesomeIcon icon={faArrowUp} /></h2>)
//         } else if (this.state.items.priceChange < 0) {
//             return (<h2 className="redTextColour">{this.state.items.priceChange.toFixed(2)}% <FontAwesomeIcon icon={faArrowDown} /></h2>)
//         }
//     }


//     render() {
//         return (
//             // <div className="pad-top">
//             //     <div className="BTCRealTimePrices">
//             //         <div className="leftPadding" />
//             //         <div class="stopwatchspace">
//             //             <div className="pricedisplay">
//             //                 <h2>Bitcoin 1 Min</h2>
//             //                 {/* <h1>(10% <FontAwesomeIcon icon={faArrowDown}/>)</h1> */}
//             //             </div>
//             //         </div>

//             //         <div className="LeftPadding" />

//             //         <div class="stopwatchspace">
//             //             <div className="pricedisplay">
//             //                 {this.priceColour()}
//             //                 {/* <h1>(10% <FontAwesomeIcon icon={faArrowDown}/>)</h1> */}
//             //             </div>
//             //         </div>

//             //         <div className="LeftPadding" />

//             //         <div class="stopwatchspace">
//             //             <div className="pricedisplay">
//             //                 {this.percentageColour()}
//             //             </div>
//             //         </div>
//             //     </div>
//             //     <div className="pad-bottom" />
//             // </div>
//             <div class="activeExchanges">
//                 <div class="container">
//                     <div class="row py-2">
//                         {/* Blank Space, easier then using margin */}
//                         <div class="col-2" />
//                         <div class="col-10 py-2">
//                             <p1><img src={coinbaseLogo} class="cbImage" /> Coinbase</p1>
//                         </div>


//                         <div class="col-2" />
//                         <div class="col-3 text-start">
//                             <h3><img src={coinbaseLogo} class="cbImage" />{this.priceColour()}</h3>
//                             <h3><img src={coinbaseLogo} class="cbImage" />{this.priceColour()}</h3>
//                             <h3><img src={coinbaseLogo} class="cbImage" />{this.priceColour()}</h3>
//                             <h3><img src={coinbaseLogo} class="cbImage" />{this.priceColour()}</h3>
//                         </div>

//                         <div class="col-3 text-start">
//                             <h3><img src={bainceLogo} class="cbImage" />{this.priceColour()}</h3>
//                             <h3><img src={bainceLogo} class="cbImage" />{this.priceColour()}</h3>
//                             <h3><img src={bainceLogo} class="cbImage" />{this.priceColour()}</h3>
//                             <h3><img src={bainceLogo} class="cbImage" />{this.priceColour()}</h3>
//                         </div>

//                         <div class="col-2  text-end">
//                             <h3><img src={bitmexLogo} class="cbImage" />{this.priceColour()}</h3>
//                             <h3><img src={bitmexLogo} class="cbImage" />{this.priceColour()}</h3>
//                             <h3><img src={bitmexLogo} class="cbImage" />{this.priceColour()}</h3>
//                             <h3><img src={bitmexLogo} class="cbImage" />{this.priceColour()}</h3>
//                         </div>
//                     </div>
//                 </div>
//             </div>
//         );
//     }
// }

// export default BTCRealTimePrices;
