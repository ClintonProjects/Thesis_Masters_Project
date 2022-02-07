import React, { Component } from 'react';
import '../App.css';
import TVGraph from '../Components/TVGraph/TVGraph.js';
import Nav from '../Components/NavigationBar/bar.js';
import Footer from '../Components/Footer/footer.js';
import BTCPrices from '../Components/BTCPrices/BTCPrices.js';
import BTCRealTimePrices from '../Components/BTCRealTimePrices/BTCRealTimePrices.js';
import ExchangeActivePrices from '../Components/ExchangeActivePrices/ExchangeActivePrices.js';


class PostloginHomepage extends Component {
    constructor(props) {
        super(props);
        this.state = {
        };
    }

    render() {
        console.log("currency: "+ this.props.currency);
        return (
            <div className="App">
                <Nav currency = {this.props.currency}/>
                <TVGraph />
                <ExchangeActivePrices/>
                <BTCPrices/>
                <Footer />
            </div>
        );
    }
}

export default PostloginHomepage;
