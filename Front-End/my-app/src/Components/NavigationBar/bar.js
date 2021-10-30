import './bar.css';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faUser, faTicketAlt, faSignInAlt, faAngleDown } from '@fortawesome/free-solid-svg-icons';
import pikachu from './coin.png';
import Homepage from '../../Pages/Homepage.js';
import { Route, BrowserRouter as Router, Switch, Link } from "react-router-dom";
import eu from './eu.png';
import usa from './usa.png';

function App() {
    return (
        <div className="navigationbar">
            <div className="smallspace" />
            <div className="LeftOfNav">
                <div class="nav-brand">
                    <Link to="/">
                        <h1><img src={pikachu} alt="HeadImage" class="pikachuimage" /><b>BTCInfo</b></h1>
                    </Link>
                </div>
            </div>
            <div class="nav-support-button">
            <li class="dropdown">
                <a href="javascript:void(0)" class="dropbtn"><img src={usa} alt="HeadImage" class="euflag"/> USD </a>
                <div class="dropdown-content">
                    <a href="#"><img src={eu} alt="HeadImage" class="euflag"/> Euro</a>
                    <a href="#"><img src={usa} alt="HeadImage" class="euflag"/> USD</a>
                    {/* <a href="#">Link 2</a>
                    <a href="#">Link 3</a> */}
                </div>
            </li>
            </div>
        </div>
    );
}

export default App;

