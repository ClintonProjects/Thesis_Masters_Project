package com.bitcoinprice.dataparsing.exchanges;

import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.bitcoinprice.dataparsing.coindata.ExchangeDataRecieved;
import com.bitcoinprice.dataparsing.coindata.ExchangeObjRefNames;
import com.bitcoinprice.dataparsing.requests.Requests;
import com.bitcoinprice.dataparsing.requests.JsonConverter;

public class ApiExchangeToData {
	
	protected JSONArray dataExample;
	protected ArrayList<ExchangeDataRecieved> exchangeDataList;

	public ApiExchangeToData(JSONObject jsonData) throws JSONException, UnsupportedOperationException, IOException {
	
		String request = new Requests().getRequest(jsonData.getString("apilink"));
		dataExample = new JsonConverter().stringToObjectArray(request);
		exchangeDataList = new ArrayList<ExchangeDataRecieved>();

		for (int i = 0; i < dataExample.length(); i++) {
			JSONObject firstResult = (JSONObject) dataExample.get(i);
			ExchangeDataRecieved exchangeDataRecieved = new ExchangeDataRecieved();
			// Setters, just perfer doing it this way over doing it through constructor,
			// because of amount of fields
			exchangeDataRecieved.setExchange(jsonData.getString("exchange"));
			exchangeDataRecieved.setCypto(jsonData.getString("cypto"));
			exchangeDataRecieved.setCurrency(jsonData.getString("currency"));
			exchangeDataRecieved.setSide(firstResult.get("side").toString());
			exchangeDataRecieved.setTranactionId(firstResult.get("trdMatchID").toString());
			exchangeDataRecieved.setPrice(firstResult.get("price").toString());
			exchangeDataRecieved.setSize(firstResult.get("size").toString());
			exchangeDataRecieved.setSymbol(firstResult.get("symbol").toString());
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
