import './bar.css';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faUser, faTicketAlt, faSignInAlt } from '@fortawesome/free-solid-svg-icons';
import pikachu from './coin.png';
import Homepage from '../../Pages/Homepage.js';
import { Route, BrowserRouter as Router, Switch,Link } from "react-router-dom";

function App() {
    return (
            <div className="navigationbar">
                <div className="smallspace" />
                <div className="LeftOfNav">
                    <div class="nav-brand">
                        <Link to="/">
                            <h1><img src={pikachu} alt="HeadImage" class="pikachuimage"/><b>BTCInfo</b></h1>
                        </Link>
                    </div>
                </div>
                <div class="nav-support-button">
                    <a href="javascript:void(Tawk_API.toggle())"> <p1 class="dropbtn"><FontAwesomeIcon icon={faUser} /> Support</p1> </a>
                </div>
            </div>
    );
}

export default App;

