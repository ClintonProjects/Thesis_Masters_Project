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

	public ApiExchangeToData() {
		Integer x = Integer.MAX_VALUE;
	}

	@Deprecated
	// This is only used for post requests, it's deprecated since it meant to be
	// avoided to be used.
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

	public void test(JSONArray getNotePadData, String apiData, String link, boolean JsonArrayRequired)
			throws JSONException, UnsupportedOperationException, IOException {

		// Json data from websocket.
		dataExample = new JsonConverter().stringToObjectArray("[" + apiData + "]");

		// exchange data list:
		exchangeDataList = new ArrayList<ExchangeDataRecieved>();

		for (int i = 0; i < getNotePadData.length(); i++) {
			// Note pad loop
			JSONObject dataConversationText = (JSONObject) getNotePadData.get(i);
			String apiLinkFromNotepad = String.valueOf(dataConversationText.get("apiLink"));

			if (!link.equalsIgnoreCase(apiLinkFromNotepad))

				continue;

			else {

				for (int j = 0; j < dataExample.length(); j++) {
					ExchangeDataRecieved exchangeDataRecieved = new ExchangeDataRecieved();

					boolean dataRequired = JsonArrayRequired;
					JSONArray listOfData;
					int loopSize = 0;

					if (dataRequired) {
						// This line may need to be removed for other exchanges:
						if (((JSONObject) dataExample.get(j)).has("data")) {
						String wekSocketDataToString = ((JSONObject) dataExample.get(j)).getString("data");
						// System.out.println(wekSocketDataToString);
						listOfData = new JSONArray(wekSocketDataToString);
						loopSize = listOfData.length();
						System.out.println("loopSize: " + loopSize);
						} else {
							//for error catching
							return;
						}
					} else {
						// This line may need to be removed for other exchanges:
						JSONObject wekSocketDataToString = ((JSONObject) dataExample.get(j));
						// System.out.println(wekSocketDataToString);
						listOfData = new JSONArray("[" + wekSocketDataToString + "]");
						loopSize = listOfData.length();
					}

					for (int k = 0; k < loopSize; k++) {

						try {
						JSONObject firstResult = (JSONObject) listOfData.get(k);

						exchangeDataRecieved.setApiLink(dataConversationText.getString("apiLink"));
						exchangeDataRecieved.setExchange(dataConversationText.getString("exchange"));
						
						if (firstResult.has("product_id") && firstResult.get("product_id").toString().split("-").length == 2)
						exchangeDataRecieved.setCypto(firstResult.get("product_id").toString().split("-")[0]);
						else
						exchangeDataRecieved.setCypto(dataConversationText.getString("cypto").toUpperCase());
						
						if (!firstResult.has("product_id") && firstResult.get("product_id").toString().split("-").length != 2) {
							exchangeDataRecieved.setCurrency(dataConversationText.getString("currency").toUpperCase());
						} else exchangeDataRecieved.setCurrency(firstResult.get("product_id").toString().split("-")[1]);
						
						
						exchangeDataRecieved.setSide(firstResult.getString(dataConversationText.getString("side")));

						if (firstResult.has(dataConversationText.getString("side")))
							exchangeDataRecieved.setSide(firstResult.getString(dataConversationText.getString("side")));

						exchangeDataRecieved
								.setTranactionId(firstResult.getString(dataConversationText.getString("tranactionId")));
						exchangeDataRecieved.setPrice(firstResult.getString(dataConversationText.getString("price")));
						exchangeDataRecieved.setSize(firstResult.getString(dataConversationText.getString("size")));
						exchangeDataRecieved.setSymbol(dataConversationText.getString("symbol"));
						exchangeDataRecieved
								.setTimestamp(firstResult.getString(dataConversationText.getString("timestamp")));

						exchangeDataList.add(exchangeDataRecieved);

					} catch (Exception e) {
						System.out.println(e);
					}
					}
				}
			}
		}
	}

	public JSONArray getDataExample() {
		return dataExample;
	}

	public ArrayList<ExchangeDataRecieved> getExchangeDataList() {
		return exchangeDataList;
	}
}
