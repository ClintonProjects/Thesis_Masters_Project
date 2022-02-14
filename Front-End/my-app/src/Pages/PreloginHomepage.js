import React, { Component } from 'react';
import '../App.css';
import TVGraph from '../Components/TVGraph/TVGraph.js';
import Quote from '../Components/Quote/Quote.js';
import Text from '../Components/Text/text.js';
import Nav from '../Components/NavigationBar/bar2.js';
import Footer from '../Components/Footer/footer.js';
import BTCPrices from '../Components/BTCPrices/BTCPrices.js';
import Banner from '../Components/BannerSpot/Banner.js';
import Spacers from '../Components/Spacers/Spacer.js';
import BTCRealTimePrices from '../Components/BTCRealTimePrices/BTCRealTimePrices.js';


class PreloginHomepage extends Component {
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
                <Text/> 
                {/* <BTCPrices currency = {this.props.currency}/> */}
                <Banner/> 
                {/* <BTCRealTimePrices /> */}
                <Footer />
            </div>
        );
    }
}

export default PreloginHomepage;
