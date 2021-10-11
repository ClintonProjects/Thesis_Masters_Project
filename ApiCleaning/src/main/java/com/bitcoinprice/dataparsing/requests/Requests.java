package com.bitcoinprice.dataparsing.requests;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketAdapter;
import com.neovisionaries.ws.client.WebSocketException;
import com.neovisionaries.ws.client.WebSocketFactory;

public class Requests {

	public String getRequest(String link) throws UnsupportedOperationException, IOException {
		// Creating a HttpClient object
		CloseableHttpClient httpclient = HttpClients.createDefault();

		// Creating a HttpGet object
		HttpGet httpget = new HttpGet(link);

		// Printing the method used
		//System.out.println("Request Type: " + httpget.getMethod());

		// Executing the Get request
		HttpResponse httpresponse = httpclient.execute(httpget);

		Scanner sc = new Scanner(httpresponse.getEntity().getContent());
		String result = "";

		// Printing the status line
		//System.out.println(httpresponse.getStatusLine());
		while (sc.hasNext()) {
			result += sc.nextLine();
		}

		System.out.println(result);

		return result;
	}

	public static String sendPOST(String url, List<NameValuePair> httpHeaders) throws IOException {
		String result = "";
		HttpPost post = new HttpPost(url);

		// add request parameters or form parameters
//		List<NameValuePair> params = new ArrayList<NameValuePair>();
//		params.add(new BasicNameValuePair("key", "value"));

		post.setEntity(new UrlEncodedFormEntity(httpHeaders));

		try (CloseableHttpClient httpClient = HttpClients.createDefault();
				CloseableHttpResponse response = httpClient.execute(post)) {

			result = EntityUtils.toString(response.getEntity());
		}

		return result;
	}

	public String webSocket(String link) throws WebSocketException, IOException {
		// Connect to "wss://echo.websocket.org" and send "Hello." to it.
		// When a response from the WebSocket server is received, the
		// WebSocket connection is closed.
		new WebSocketFactory().createSocket(link).addListener(new WebSocketAdapter() {
			@Override
			public void onTextMessage(WebSocket ws, String message) {
				// Received a response. Print the received message.
				System.out.println(message);

				// Close the WebSocket connection.
				ws.disconnect();
			}
		}).connect().sendContinuation();
		return link;
	}

}
