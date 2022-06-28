import React, { Component } from 'react';
import '../App.css';
import TVGraph from '../Components/TVGraph/TVGraph.js';
import Text from '../Components/Text/text.js';
import Nav from '../Components/NavigationBar/bar.js';
import Footer from '../Components/Footer/footer.js';
import Banner from '../Components/BannerSpot/Banner.js';
import 'react-pro-sidebar/dist/css/styles.css';

class PreloginHomepage extends Component {
    constructor(props) {
        super(props);
        this.state = {
        };
    }

   // displayes the user prelogin home page (the page without the stats)
    render() {
        console.log("currency: " + this.props.currency);
        return (
            <div className="App">
                <Nav currency={this.props.currency} />
                <TVGraph />
                <Text />
                <Banner />
                <Footer />
            </div>
        );
    }
}

export default PreloginHomepage;
