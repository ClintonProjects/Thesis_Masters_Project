package com.bitcoinprice.dataparsing.exchanges;

import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.bitcoinprice.dataparsing.requests.NotePad;

public class AllDataList {

	public ArrayList<ApiExchangeToData> dataList;

	public AllDataList() {
		try {
			allApiData();
		} catch (UnsupportedOperationException | JSONException | IOException e) {
			e.printStackTrace();
		}
		}

	public void allApiData()  {
		JSONArray jsonArray = null;
		try {
			jsonArray = new JSONArray(new NotePad().read("WebsiteToScrapJson"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject JsonData = (JSONObject) jsonArray.get(i);
			ApiExchangeToData apiExchangeToData = new ApiExchangeToData(JsonData);
			dataList.add(apiExchangeToData);
		}
	}
}
