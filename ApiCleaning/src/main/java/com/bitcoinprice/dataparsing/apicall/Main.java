package com.bitcoinprice.dataparsing.apicall;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;
import com.bitcoinprice.dataparsing.exchanges.ApiExchangeToData;
import com.bitcoinprice.dataparsing.requests.NotePad;

public class Main {

	public static void main(String args[]) throws Exception {
		System.out.println(new NotePad().read("WebsiteToScrapJson"));
		JSONArray jsonArray = new JSONArray(new NotePad().read("WebsiteToScrapJson"));
		for (int i = 0; i < jsonArray.length(); i++) {
		JSONObject JsonData = (JSONObject) jsonArray.get(i);
		ApiExchangeToData apiExchangeToData = new ApiExchangeToData(JsonData);
		System.out.println(apiExchangeToData.getExchangeDataList().get(i).getPrice());
		}
	}
}
