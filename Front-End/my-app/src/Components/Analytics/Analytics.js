import React, { Component } from 'react';
import './information.css';
import GoogleMapComponent from './Map.js';

class Analytics extends Component {

    constructor(props) {
        super(props);
        this.state = {
        };
    }

    render() {
        return (
            <div class="infoColour">
                <div class="container py-2 infoColour no-gutters">
                {/* <GoogleMapComponent/> */}
                </div>
            </div >
        )
    }
}


export default Analytics;

