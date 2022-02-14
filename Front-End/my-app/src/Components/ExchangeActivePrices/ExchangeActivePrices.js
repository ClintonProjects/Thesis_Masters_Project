import './ExchangeActivePrices.css';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faTrademark } from '@fortawesome/free-solid-svg-icons';
import coinbaseLogo from './Coinbase.png';
import bainceLogo from './binance.png';
import bitmexLogo from './bitmex-logo.png';





function App() {
    return (
        <div class="activeExchanges">
            <div class="container">
                <div class="row py-2">
                    {/* Blank Space, easier then using margin */}
                    <div class="col-2" />
                    <div class="col-3 text-start">
                        <h3>  <img src={coinbaseLogo} class="cbImage" /> $43,000</h3>
                    </div>

                    <div class="col-2 text-start">
                        <h3>  <img src={bainceLogo} class="cbImage" /> $43,000</h3>
                    </div>

                    <div class="col-3  text-end">
                        <h3>  <img src={bitmexLogo} class="cbImage" /> $43,000</h3>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default App;
