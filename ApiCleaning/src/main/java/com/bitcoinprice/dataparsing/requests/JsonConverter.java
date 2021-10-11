package com.bitcoinprice.dataparsing.requests;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonConverter {
	
	public static String objectToString(Object object) {
		JSONObject jsonObject = new JSONObject(object);
		String myJson = jsonObject.toString();
		return myJson;
	}

	public static JSONArray stringToObjectArray(String JsonString) throws JSONException {
		return new JSONArray(JsonString);
	}

	public static void printJson(JSONArray arr, int size) throws JSONException {
		System.out.println(arr.toString(size));
	}
}
