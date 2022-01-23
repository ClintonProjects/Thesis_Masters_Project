package com.bitcoinprice.dataparsing.requests;

import java.io.IOException;

import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketAdapter;
import com.neovisionaries.ws.client.WebSocketException;
import com.neovisionaries.ws.client.WebSocketFactory;

public class Webhook {

	String returnMessage;

	public String getWebSocket(String link) throws WebSocketException, IOException {
		new WebSocketFactory().createSocket("wss://testnet.bitmex.com/realtime?subscribe=trade:XBTUSD")
				.addListener(new WebSocketAdapter() {
					@Override
					public void onTextMessage(WebSocket ws, String message) {
						returnMessage = message;
					}
				}).connect();
		return link;
	}

	public String getReturnMessage() {
		return returnMessage;
	}
}
