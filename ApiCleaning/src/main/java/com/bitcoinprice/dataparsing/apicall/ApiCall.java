package com.bitcoinprice.dataparsing.apicall;

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
import java.net.URI;
import java.net.URISyntaxException;

public class ApiCall {

	public String getRequest(String link) throws UnsupportedOperationException, IOException {
		// Creating a HttpClient object
		CloseableHttpClient httpclient = HttpClients.createDefault();

		// Creating a HttpGet object
		HttpGet httpget = new HttpGet(link);

		// Printing the method used
		System.out.println("Request Type: " + httpget.getMethod());

		// Executing the Get request
		HttpResponse httpresponse = httpclient.execute(httpget);

		Scanner sc = new Scanner(httpresponse.getEntity().getContent());

		String result = "";

		while (sc.hasNext()) {
			result += sc.nextLine();
		}
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

	

}
