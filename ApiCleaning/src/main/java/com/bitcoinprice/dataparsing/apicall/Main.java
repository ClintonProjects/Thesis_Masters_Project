package com.bitcoinprice.dataparsing.apicall;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.bitcoinprice.dataparsing.exchanges.AllDataList;
import com.bitcoinprice.dataparsing.exchanges.ApiExchangeToData;
import com.bitcoinprice.dataparsing.requests.NotePad;
import com.bitcoinprice.dataparsing.requests.Requests;
import com.neovisionaries.ws.client.*;
public class Main {

	public static void main(String[] args) throws Exception
    {
        // Connect to "wss://echo.websocket.org" and send "Hello." to it.
        // When a response from the WebSocket server is received, the
        // WebSocket connection is closed.
        new WebSocketFactory()
            .createSocket("wss://testnet.bitmex.com/realtime?subscribe=trade:XBTUSD")
            .addListener(new WebSocketAdapter() {
                @Override
                public void onTextMessage(WebSocket ws, String message) {
                    // Received a response. Print the received message.
                    System.out.println(message);

                    // Close the WebSocket connection.
//                    ws.disconnect();
                }
            })
            .connect();
    }
}
