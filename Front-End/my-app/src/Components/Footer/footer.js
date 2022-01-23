import './footer.css';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faTrademark } from '@fortawesome/free-solid-svg-icons';

function App() {
    return (
            <div class="footer">
                {/* <div class="footertop">


                    <div class="spacerleft" />
                    <div class="footercenter">
                        <div class="Feedback">
                            <div class="header" />
                            <h1> <b>Feedback</b></h1>
                            <p>&#62; Sythe<br /></p>
                            <p>&#62; Osbot<br /></p>
                            <p>&#62; Dreambot<br /></p>
                        </div>
                    </div>
                    <div class="SpaceBetweenFeedback" />
                    <div class="footercenter">
                        <div class="Feedback">
                            <div class="header" />
                            <h1><b>Support</b></h1>
                            <p>&#62; Livechat<br/></p>
                            <p>abrokengold@outlook.ie<br /></p>
                        </div>
                    </div>

                </div> * */}


                <div class="footercopyright">
                    All rights reserved to BTCinfo LTD @ 2021.<FontAwesomeIcon icon={faTrademark} />
                </div>
            </div>
    );
}

export default App;
