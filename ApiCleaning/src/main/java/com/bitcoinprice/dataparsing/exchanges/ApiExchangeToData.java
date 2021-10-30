package com.bitcoinprice.dataparsing.exchanges;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

import com.bitcoinprice.dataparsing.coindata.ExchangeDataRecieved;
import com.bitcoinprice.dataparsing.requests.Requests;
import com.bitcoinprice.dataparsing.requests.JsonConverter;

public class ApiExchangeToData {

	protected JSONArray dataExample;
	protected ArrayList<ExchangeDataRecieved> exchangeDataList;

	public ApiExchangeToData(JSONObject jsonData) throws JSONException, UnsupportedOperationException, IOException {
		String request = new Requests().getRequest(jsonData.getString("apiLink"));
		dataExample = new JsonConverter().stringToObjectArray(request);
		exchangeDataList = new ArrayList<ExchangeDataRecieved>();

		for (int i = 0; i < dataExample.length(); i++) {
			JSONObject firstResult = (JSONObject) dataExample.get(i);
			ExchangeDataRecieved exchangeDataRecieved = new ExchangeDataRecieved();

			exchangeDataRecieved.setApiLink(jsonData.getString("apiLink"));
			exchangeDataRecieved.setExchange(jsonData.getString("exchange"));
			exchangeDataRecieved.setCypto(jsonData.getString("cypto").toUpperCase());
			exchangeDataRecieved.setCurrency(jsonData.getString("currency").toUpperCase());
			
			if (firstResult.has(jsonData.getString("side")))
			exchangeDataRecieved.setSide(firstResult.getString(jsonData.getString("side")));
			
			exchangeDataRecieved.setTranactionId(firstResult.getString(jsonData.getString("tranactionId")));
			exchangeDataRecieved.setPrice(firstResult.getString(jsonData.getString("price")));
			exchangeDataRecieved.setSize(firstResult.getString(jsonData.getString("size")));
			exchangeDataRecieved.setSymbol(jsonData.getString("symbol"));
			exchangeDataRecieved.setTimestamp(firstResult.getString(jsonData.getString("timestamp")));
			
			exchangeDataList.add(exchangeDataRecieved);
		}
	}

	public JSONArray getDataExample() {
		return dataExample;
	}

	public ArrayList<ExchangeDataRecieved> getExchangeDataList() {
		return exchangeDataList;
	}
}
