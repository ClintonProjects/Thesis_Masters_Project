import React, { Component } from 'react';
import '../App.css';
import Nav2 from '../Components/NavigationBar/bar2.js';
import Footer from '../Components/Footer/footer.js';
import Map from '../Components/Analytics/Analytics.js';
import axios, * as others from 'axios';

class AnalyticsPage extends Component {
    constructor(props) {
        super(props);
        this.state = {
        };
    }

    async AuthUser() {
        const res = await axios.get('https://localhost:8081/AnalyticsService/AnaylticsRedirect/' + localStorage.getItem('SessionId'));
        let val = await res.data;
        if (val != "a")
        window.location.href = val;
    }

    componentDidMount() {
        this.AuthUser();
    }

    render() {
        return (
            <div className="App">
                <Nav2 currency={this.props.currency} />
                <Map/>
                <Footer />
            </div>
        );
    }
}

export default AnalyticsPage;
