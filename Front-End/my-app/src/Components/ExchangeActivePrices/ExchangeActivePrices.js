import './ExchangeActivePrices.css';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faTrademark } from '@fortawesome/free-solid-svg-icons';


function componentDidMount() {
    this.interval = setInterval(() => this.getData(), 7000);
}


function getData() {
    // GET request using fetch with error handling
    const headers = {
        'Content-Type': 'application/json',
        "Access-Control-Allow-Origin": "*",

    }
    fetch('http://localhost:8081/apis/btcprice', { headers })
        .then(async response => {
            const data = await response.json();

            // check for error response
            if (!response.ok) {
                // get error message from body or default to response statusText
     //           console.log("not ok");
                const error = (data && data.message) || response.statusText;
                return Promise.reject(error);
            }
   //         console.log("ok");
            this.setState({ items: data })
            // console.log(this.state.items);
        })
        .catch(error => {
            this.setState({ errorMessage: error.toString() });
 //           console.error('There was an error!', error);
        });
}


function App() {
    return (
        <div class="ExchangeActivePrices">
            <div class="inside">
                <div class="coinbase">

                </div>
            </div>
        </div>
    );
}

export default App;
