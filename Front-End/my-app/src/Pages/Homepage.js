import React, { Component } from 'react';
import '../App.css';
import TVGraph from '../Components/TVGraph/TVGraph.js';
import Quote from '../Components/Quote/Quote.js';
import Text from '../Components/Text/text.js';
import Nav from '../Components/NavigationBar/bar.js';
import Footer from '../Components/Footer/footer.js';
import BTCPrices from '../Components/BTCPrices/BTCPrices.js';
import Banner from '../Components/BannerSpot/Banner.js';
import Spacers from '../Components/Spacers/Spacer.js';
import BTCRealTimePrices from '../Components/BTCRealTimePrices/BTCRealTimePrices.js';


class Homepage extends Component {
    constructor(props) {
        super(props);
        this.state = {
        };
    }
    render() {
        return (
            <div className="App">
                <Nav />
                <TVGraph />
                <Text/>
                <Banner/>
                {/* <BTCPrices />
                <BTCRealTimePrices /> */}
                <Footer />
            </div>
        );
    }
}

export default Homepage;
