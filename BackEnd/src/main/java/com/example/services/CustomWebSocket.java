package com.example.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import com.bitcoinprice.dataparsing.coindata.ExchangeDataRecieved;
import com.bitcoinprice.dataparsing.exchanges.AllDataList;
import com.bitcoinprice.dataparsing.exchanges.ApiExchangeToData;
import com.example.repository.BitcoinPriceData;
import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketAdapter;
import com.neovisionaries.ws.client.WebSocketException;
import com.neovisionaries.ws.client.WebSocketFactory;

@Service
@ComponentScan({ "com.example", "com.example.repository" })
public class CustomWebSocket {

	@Autowired
	private BitcoinPriceData bitcoinPriceData;

	private String link;

	private List<String> allTranactionsIds;

	public CustomWebSocket() {
	}

	public CustomWebSocket(String a) {
		super();
		this.link = a;
		System.out.println("link= " + a);
	}

//	@PostConstruct
	public void getWebSocket(String link, boolean JsonArrayRequired) throws WebSocketException, IOException {
		System.out.println("getWeb");
		new WebSocketFactory().createSocket(link).addListener(new WebSocketAdapter() {
			@Override
			public void onTextMessage(WebSocket ws, String message)
					throws UnsupportedOperationException, JSONException, IOException {
				try {
					List<String> adresses = bitcoinPriceData.findAll().stream()
							.map(ExchangeDataRecieved::getTranactionId).collect(Collectors.toList());

					JSONArray getNotePadData = new AllDataList().getNotePadDataJsonArray();
					ApiExchangeToData apiExchangeToData = new ApiExchangeToData();
					System.out.println(message);
					apiExchangeToData.test(getNotePadData, message, link, JsonArrayRequired);
					for (int i = 0; i < apiExchangeToData.getExchangeDataList().size(); i++) {
						//if (!adresses.contains(apiExchangeToData.getExchangeDataList().get(i).getTranactionId())) {
							System.out.println("Saved");
							bitcoinPriceData.save(apiExchangeToData.getExchangeDataList().get(i));
						//}
					}
					System.out.println(apiExchangeToData.getExchangeDataList().size());
					System.out.println("Ffff");
				} catch (Exception e) {
					System.out.println(e);
				}
			}
		}).connect();
	}

}
