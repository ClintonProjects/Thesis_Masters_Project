import './ExchangeActivePrices.css';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faTrademark } from '@fortawesome/free-solid-svg-icons';
import coinbaseLogo from './Coinbase.png';
import bainceLogo from './binance.png';
import bitmexLogo from './bitmex-logo.png';





function App() {
    return (
        <div class="ExchangeActivePrices">
            <div class="inside">
                <div class="coinbase">
                <div class="exchangeLogo">
                <img class="coinbaseImg" src={coinbaseLogo} width="50%" height="50%"/>
                </div>
                <div class="exchangeText">
                <h1>$43,000</h1>
                </div>
                </div>

                <div class="coinbase">
                <div class="exchangeLogo">
                <img class="coinbaseImg" src={bainceLogo} width="50%" height="50%"/>
                </div>
                <div class="exchangeText">
                <h1>$43,000</h1>
                </div>
                </div>


                <div class="coinbase">
                <div class="exchangeLogo">
                <img class="coinbaseImg" src={bitmexLogo} width="50%" height="50%"/>
                </div>
                <div class="exchangeText">
                <h1>$43,000</h1>
                </div>
                </div>
            
            </div>
        </div>
    );
}

export default App;
