import React, { Component } from 'react';
import '../App.css';
import TVGraph from '../Components/TVGraph/TVGraph.js';
import Nav2 from '../Components/NavigationBar/bar.js';
import Footer from '../Components/Footer/footer.js';
import BTCPrices from '../Components/BTCPrices/BTCPrices.js';
import Banner from '../Components/BannerSpot/Banner.js';
import BTCRealTimePrices from '../Components/BTCRealTimePrices/BTCRealTimePrices.js';
import BuySellBar from '../Components/BuySellBar/BuySellBar';

class PostloginHomepage extends Component {
    constructor(props) {
        super(props);
        this.state = {
        };
    }

    // displayes the user post-login home page (the page with the stats)
    render() {
        console.log("currency: " + this.props.currency);
        return (
            <div className="App">
                <Nav2 currency={this.props.currency} />
                <TVGraph />
                <BuySellBar />
                <BTCRealTimePrices />
                <BTCPrices />
                <Banner />
                <Footer />
            </div>
        );
    }
}

export default PostloginHomepage;
