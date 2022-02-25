package com.example.services;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.bitcoinprice.dataparsing.coindata.ExchangeDataRecieved;
import com.bitcoinprice.dataparsing.coindata.RealTimeBTCData;
import com.bitcoinprice.dataparsing.exchanges.AllDataList;
import com.bitcoinprice.dataparsing.exchanges.ApiExchangeToData;
import com.bitcoinprice.dataparsing.requests.JsonConverter;
import com.bitcoinprice.dataparsing.requests.Webhook;
import com.example.repository.BitcoinPriceData;
import com.neovisionaries.ws.client.WebSocketException;

@Service
@ComponentScan({ "com.example", "com.example.repository" })
public class ServicesExample {

	@Autowired
	private BitcoinPriceData bitcoinPriceData;

	@Autowired
	CustomWebSocket customWebSocket;

	@Deprecated
	public void addExchangesToDB() throws JSONException, IOException {
		ArrayList<ApiExchangeToData> getDataList = new AllDataList().getDataList();
		List<String> adresses = bitcoinPriceData.findAll().stream().map(ExchangeDataRecieved::getTranactionId)
				.collect(Collectors.toList());

		for (ApiExchangeToData i : getDataList) {
			for (ExchangeDataRecieved j : i.getExchangeDataList()) {
				if (!adresses.contains(j.getTranactionId())) {
					bitcoinPriceData.save(j);
				}
			}
		}
	}

	@Bean
	public void addWebSocketDataToDB() throws JSONException, IOException, WebSocketException {
		// Starts the websockets:
		//note: for self https://docs.binance.org/api-reference/dex-api/ws-connection.html
		// working:
		//coinbase:
		customWebSocket.getWebSocket("wss://ws-feed.pro.coinbase.com/", false, "{\"type\": \"subscribe\", \"channels\": [{\"name\":\"matches\",\"product_ids\":[\"ETH-USD\"]}]}");
		//bitmex
//		customWebSocket.getWebSocket("wss://ws.bitmex.com/realtime?subscribe=trade:XBTUSD", true, "");
//		customWebSocket.getWebSocket("wss://ws.bitmex.com/realtime?subscribe=trade:XBTEUR", true, "");
		
		
		

	}

	public Double buySellBar() {
		List<ExchangeDataRecieved> currentDB = bitcoinPriceData.findAll().stream().collect(Collectors.toList());
		List<ExchangeDataRecieved> previousMinList = new ArrayList<ExchangeDataRecieved>();

		LocalDateTime now = LocalDateTime.ofInstant(Instant.now(), ZoneOffset.UTC);
		LocalDateTime minAgo = now.minus(1, ChronoUnit.MINUTES);

		for (ExchangeDataRecieved i : currentDB) {
			Instant instant = Instant.parse(i.getTimestamp());
			LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneOffset.UTC);

			if (Duration.between(localDateTime, minAgo).toSeconds() <= 60)
				previousMinList.add(i);
		}

		long buyCount = previousMinList.stream().filter(i -> i.getSide().equals("Buy")).count();
		long totalCount = previousMinList.size();
		
		return new Double((buyCount * 100.0f) / totalCount);
		
		
	}

	public List<ExchangeDataRecieved> getTenLatestTranactions() {
		List<ExchangeDataRecieved> currentDB = bitcoinPriceData.findAll();
		currentDB = currentDB.stream()
				.filter(/* BTC SIZE FILTER */ i -> Double.parseDouble(i.price) * Double.parseDouble(i.getSize()) > 1)
//				.filter(i -> i.exchange.equalsIgnoreCase("coinbase pro"))
//				.filter(i -> i.cypto.equalsIgnoreCase("LTC"))
				.collect(Collectors.toList());
		currentDB.sort(Comparator.comparing(ExchangeDataRecieved::getTimestamp1).reversed());
		if (currentDB.size() >= 8)
			return currentDB.subList(0, 8);
		else
			return currentDB.subList(0, currentDB.size());
	}

	// this list is are previous exchange data from the last cycle.
	public static List<ExchangeDataRecieved> previousExchangeDataRecieved;
	// checks if list has changed.
	public static boolean matchingData = true;

	// This method is used mainly for the websocket, it's basically just verifyes
	// that previous list is not the same as newest list.
	public void checkForNewEntries() {
		matchingData = true;

		if (previousExchangeDataRecieved == null) {
			previousExchangeDataRecieved = new ArrayList<ExchangeDataRecieved>();
			return;
		}

		// This list contains are current exchange data.
		List<ExchangeDataRecieved> exchangeDataRecieved = getTenLatestTranactions();

		if (previousExchangeDataRecieved.isEmpty()) {
			previousExchangeDataRecieved = exchangeDataRecieved;
			return;
		}

		// check if the 2 lists have all the same elemtns
		for (int i = 0; i < exchangeDataRecieved.size(); i++)
			if (!exchangeDataRecieved.get(i).getTranactionId()
					.equals(previousExchangeDataRecieved.get(i).getTimestamp()))
				matchingData = false;

		if (!matchingData)
			previousExchangeDataRecieved = exchangeDataRecieved;
	}

	// This is deprecated because it will replaced with another method or rewrite of
	// this method in an comming update.
	@Deprecated
	public RealTimeBTCData getRealTimeBTCData() {
		List<ExchangeDataRecieved> currentDB = bitcoinPriceData.findAll().stream()
				.filter(/* Removes all non usd */i -> i.getCurrency().equalsIgnoreCase("usd"))
				.filter(i -> i.cypto.equalsIgnoreCase("BTC")).collect(Collectors.toList());
		;
		List<ExchangeDataRecieved> previousMinList = new ArrayList<ExchangeDataRecieved>();
		List<ExchangeDataRecieved> afterMinList = new ArrayList<ExchangeDataRecieved>();

		LocalDateTime now = LocalDateTime.ofInstant(Instant.now(), ZoneOffset.UTC);
		LocalDateTime minAgo = now.minus(1, ChronoUnit.MINUTES);
		LocalDateTime TwoMinAgo = now.minus(2, ChronoUnit.MINUTES);

		for (ExchangeDataRecieved i : currentDB) {
			Instant instant = Instant.parse(i.getTimestamp());
			LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneOffset.UTC);

			if (Duration.between(localDateTime, minAgo).toSeconds() <= 60)
				previousMinList.add(i);
			if (Duration.between(localDateTime, minAgo).toSeconds() >= 60
					&& Duration.between(localDateTime, TwoMinAgo).toSeconds() <= 60)
				afterMinList.add(i);
		}

		double cuurentMinPrice = previousMinList.stream().mapToDouble(i -> Double.parseDouble(i.getPrice())).sum()
				/ previousMinList.size();
		double twoMinAgoPrice = afterMinList.stream().mapToDouble(i -> Double.parseDouble(i.getPrice())).sum()
				/ afterMinList.size();

		RealTimeBTCData realTimeBTCData = new RealTimeBTCData(cuurentMinPrice, twoMinAgoPrice);

		return realTimeBTCData;
	}

}
