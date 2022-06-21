import React, { Component } from 'react';
import '../App.css';
import Analytics from '../Components/Analytics/Analytics.js';
import Nav2 from '../Components/NavigationBar/bar2.js';
import Footer from '../Components/Footer/footer.js';

class AnalyticsPage extends Component {
    constructor(props) {
        super(props);
        this.state = {
        };
    }

    render() {
        return (
            <div className="App">
                <Nav2 currency={this.props.currency} />
                <Analytics/>
                <Footer />
            </div>
        );
    }
}

export default AnalyticsPage;
