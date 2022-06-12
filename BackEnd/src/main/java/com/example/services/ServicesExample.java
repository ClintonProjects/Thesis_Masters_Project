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

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.bitcoinprice.dataparsing.coindata.ExchangeDataRecieved;
import com.bitcoinprice.dataparsing.coindata.RealTimeBTCData;
import com.bitcoinprice.dataparsing.exchanges.AllDataList;
import com.bitcoinprice.dataparsing.exchanges.ApiExchangeToData;
import com.example.repository.BitcoinPriceData;
import com.neovisionaries.ws.client.WebSocketException;

@Service
@ComponentScan({ "com.example", "com.example.repository" })
public class ServicesExample {

	@Autowired
	private BitcoinPriceData bitcoinPriceData;

	@Autowired
	CustomWebSocket customWebSocket;

	@Autowired
	BainaceWebsocket bainaceWebsocket;

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
		customWebSocket.getWebSocket("wss://ws-feed.pro.coinbase.com/", false,
				"{\"type\": \"subscribe\", \"channels\": [{\"name\":\"matches\",\"product_ids\":[\"BTC-USD\", \"BTC-GBP\" , \"BTC-EUR\", \"ETH-GBP\" , \"ETH-USD\"  ,  \"ETH-EUR\"  , \"LTC-USD\" , \"LTC-GBP\" , \"LTC-EUR\"]}]}");
		customWebSocket.getWebSocket("wss://ws.bitmex.com/realtime?subscribe=trade:XBTUSD", true, "");
		customWebSocket.getWebSocket("wss://ws.bitmex.com/realtime?subscribe=trade:XBTEUR", true, "");
		customWebSocket.getWebSocket("wss://ws.bitmex.com/realtime?subscribe=trade:ETHUSD", true, "");
		customWebSocket.getWebSocket("wss://ws.bitmex.com/realtime?subscribe=trade:LTCUSD", true, "");
		bainaceWebsocket.getData(6, "btcusdt");
		bainaceWebsocket.getData(7, "ethusdt");
		bainaceWebsocket.getData(8, "ltcusdt");
		bainaceWebsocket.getData(9, "btceur");
		bainaceWebsocket.getData(10, "etheur");
		bainaceWebsocket.getData(11, "ltceur");
		bainaceWebsocket.getData(12, "btcgbp");
		bainaceWebsocket.getData(13, "ltcgbp");
		bainaceWebsocket.getData(14, "ethgbp");
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

		long buyCount = previousMinList.stream().filter(i -> i.getSide().equalsIgnoreCase("Buy")).count();
		long totalCount = previousMinList.size();

		return new Double((buyCount * 100.0f) / totalCount);
	}

//	public List<ExchangeDataRecieved> getAllTranactions() {
//		List<ExchangeDataRecieved> currentDB = bitcoinPriceData.findAll();
//		currentDB.sort(Comparator.comparing(ExchangeDataRecieved::getTimestamp1).reversed());
//		return currentDB;
//	}

	public List<ExchangeDataRecieved> getTenLatestTranactions() {
		List<ExchangeDataRecieved> currentDB = bitcoinPriceData.findAll();

		// lets pretend this really bad code doesn't exist xD
		List<ExchangeDataRecieved> newList = new ArrayList<ExchangeDataRecieved>();

		List<String> currencies = currentDB.stream().map(ExchangeDataRecieved::getCurrency)
				.collect(Collectors.toList());
		List<String> exchange = currentDB.stream().map(ExchangeDataRecieved::getExchange).collect(Collectors.toList());
		List<String> cypto = currentDB.stream().map(ExchangeDataRecieved::getCypto).collect(Collectors.toList());

		for (int i = 0; i < currentDB.size(); i++) {
			boolean itemAvaiable = true;
			for (ExchangeDataRecieved j : newList) {
				if (currencies.get(i).equalsIgnoreCase(j.getCurrency())
						&& exchange.get(i).equalsIgnoreCase(j.getExchange())
						&& cypto.get(i).equalsIgnoreCase(j.getCypto())) {
					itemAvaiable = false;
				}
			}
			if (itemAvaiable)
				newList.add(currentDB.get(i));
		}

		List<ExchangeDataRecieved> resultList = new ArrayList<ExchangeDataRecieved>();

		currentDB.sort(Comparator.comparing(ExchangeDataRecieved::getTimestamp1).reversed());

		for (ExchangeDataRecieved i : newList) {
			resultList.addAll(currentDB.stream()
					.filter(j -> j.getCurrency().equalsIgnoreCase(i.getCurrency())
							&& j.getExchange().equalsIgnoreCase(i.getExchange())
							&& j.getCypto().equalsIgnoreCase(i.getCypto()))
					.limit(5000).collect(Collectors.toList()));
		}

		resultList.sort(Comparator.comparing(ExchangeDataRecieved::getTimestamp1).reversed());

		// resultList.forEach(i -> System.out.println(i.getTimestamp()));

		// System.out.println("resultList size: " + resultList.get(0).getTimestamp1());

		return resultList;
	}

	// this list is are previous exchange data from the last cycle.
	public static List<ExchangeDataRecieved> previousExchangeDataRecieved;
	// checks if list has changed.
	public static boolean matchingData = true;

	// This method is used mainly for the websocket, it's basically just verifyes
	// that previous list is not the same as newest list.
	public void checkForNewEntries() {
		matchingData = true;

		// set the list incase it's the first run, stop bugs in other parts of the code.
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

		if (exchangeDataRecieved.size() != previousExchangeDataRecieved.size()) {
			previousExchangeDataRecieved = exchangeDataRecieved;
			return;
		}
		
		// check if the 2 lists have all the same elemtns
		for (int i = 0; i < previousExchangeDataRecieved.size(); i++) {
			if (!exchangeDataRecieved.get(i).getTranactionId()
					.equals(previousExchangeDataRecieved.get(i).getTranactionId())) {
				previousExchangeDataRecieved = exchangeDataRecieved;
				return;
			}
		}
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

	public void dropData() {
		LocalDateTime now = LocalDateTime.ofInstant(Instant.now(), ZoneOffset.UTC);
		List<ExchangeDataRecieved> resultToClear = bitcoinPriceData.findAll().stream().filter(
				i -> Duration.between(LocalDateTime.ofInstant(i.getTimestamp1(), ZoneOffset.UTC), now).toMinutes() > 3)
				.collect(Collectors.toList());
		bitcoinPriceData.deleteAll(resultToClear);
	}
	
	@Async
	@Scheduled(fixedRate = 1000 * 125)
	public void scheduledUpdate() throws Exception {
		//drops the db
		dropData();
	}
	
	
	//drop table

}
