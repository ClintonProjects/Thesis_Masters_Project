package com.example.services;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
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
		customWebSocket.getWebSocket("wss://ws-feed.pro.coinbase.com/", false, "{\"type\": \"subscribe\", \"channels\": [{\"name\":\"matches\",\"product_ids\":[\"BTC-USD\", \"BTC-GBP\" , \"BTC-EUR\", \"ETH-GBP\" , \"ETH-USD\"  ,  \"ETH-EUR\"  , \"LTC-USD\" , \"LTC-GBP\" , \"LTC-EUR\"]}]}");
//		customWebSocket.getWebSocket("wss://ws.bitmex.com/realtime?subscribe=trade:XBTUSD", true, "");
//		customWebSocket.getWebSocket("wss://ws.bitmex.com/realtime?subscribe=trade:XBTEUR", true, "");
//		customWebSocket.getWebSocket("wss://ws.bitmex.com/realtime?subscribe=trade:ETHUSD", true, "");
//		customWebSocket.getWebSocket("wss://ws.bitmex.com/realtime?subscribe=trade:LTCUSD", true, "");
//		bainaceWebsocket.getData(6, "btcusdt");
//		bainaceWebsocket.getData(7, "ethusdt");
//		bainaceWebsocket.getData(8, "ltcusdt");
//		bainaceWebsocket.getData(9, "btceur");
//		bainaceWebsocket.getData(10, "etheur");
//		bainaceWebsocket.getData(11, "ltceur");
//		bainaceWebsocket.getData(12, "btcgbp");
//		bainaceWebsocket.getData(13, "ltcgbp");
//		bainaceWebsocket.getData(14, "ethgbp");
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
					.limit(100).collect(Collectors.toList()));
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

	public HashMap<String, Object> averagePrice() {

		// Id data / result
		HashMap<String, Object> exchangeData = new HashMap<String, Object>();
		// All Data
		exchangeData.put("ALL/BTC/USD", getRealTimeBTCData("BTC", "USD", "all"));
		exchangeData.put("ALL/BTC/EUR", getRealTimeBTCData("BTC", "EUR", "all"));
		exchangeData.put("ALL/BTC/GBP", getRealTimeBTCData("BTC", "GBP", "all"));

		exchangeData.put("ALL/LTC/USD", getRealTimeBTCData("LTC", "USD", "all"));
		exchangeData.put("ALL/LTC/EUR", getRealTimeBTCData("LTC", "EUR", "all"));
		exchangeData.put("ALL/LTC/GBP", getRealTimeBTCData("LTC", "GBP", "all"));

		exchangeData.put("ALL/ETH/USD", getRealTimeBTCData("ETH", "USD", "all"));
		exchangeData.put("ALL/ETH/EUR", getRealTimeBTCData("ETH", "EUR", "all"));
		exchangeData.put("ALL/ETH/GBP", getRealTimeBTCData("ETH", "GBP", "all"));

		// All Bitmex Data
		exchangeData.put("BITMEX/BTC/USD", getRealTimeBTCData("BTC", "USD", "BITMEX"));
		exchangeData.put("BITMEX/BTC/EUR", getRealTimeBTCData("BTC", "EUR", "BITMEX"));
		exchangeData.put("BITMEX/BTC/GBP", getRealTimeBTCData("BTC", "GBP", "BITMEX"));

		exchangeData.put("BITMEX/LTC/USD", getRealTimeBTCData("LTC", "USD", "BITMEX"));
		exchangeData.put("BITMEX/LTC/EUR", getRealTimeBTCData("LTC", "EUR", "BITMEX"));
		exchangeData.put("BITMEX/LTC/GBP", getRealTimeBTCData("LTC", "GBP", "BITMEX"));

		exchangeData.put("BITMEX/ETH/USD", getRealTimeBTCData("ETH", "USD", "BITMEX"));
		exchangeData.put("BITMEX/ETH/EUR", getRealTimeBTCData("ETH", "EUR", "BITMEX"));
		exchangeData.put("BITMEX/ETH/GBP", getRealTimeBTCData("ETH", "GBP", "BITMEX"));

		// All Binance Data
		exchangeData.put("BINANCE/BTC/USD", getRealTimeBTCData("BTC", "USD", "binance"));
		exchangeData.put("BINANCE/BTC/EUR", getRealTimeBTCData("BTC", "EUR", "binance"));
		exchangeData.put("BINANCE/BTC/GBP", getRealTimeBTCData("BTC", "GBP", "binance"));

		exchangeData.put("BINANCE/LTC/USD", getRealTimeBTCData("LTC", "USD", "binance"));
		exchangeData.put("BINANCE/LTC/EUR", getRealTimeBTCData("LTC", "EUR", "binance"));
		exchangeData.put("BINANCE/LTC/GBP", getRealTimeBTCData("LTC", "GBP", "binance"));

		exchangeData.put("BINANCE/ETH/USD", getRealTimeBTCData("ETH", "USD", "binance"));
		exchangeData.put("BINANCE/ETH/EUR", getRealTimeBTCData("ETH", "EUR", "binance"));
		exchangeData.put("BINANCE/ETH/GBP", getRealTimeBTCData("ETH", "GBP", "binance"));

		// All Coinbase pro Data
		exchangeData.put("COINBASE/BTC/USD", getRealTimeBTCData("BTC", "USD", "COINBASE PRO"));
		exchangeData.put("COINBASE/BTC/EUR", getRealTimeBTCData("BTC", "EUR", "COINBASE PRO"));
		exchangeData.put("COINBASE/BTC/GBP", getRealTimeBTCData("BTC", "GBP", "COINBASE PRO"));

		exchangeData.put("COINBASE/LTC/USD", getRealTimeBTCData("LTC", "USD", "COINBASE PRO"));
		exchangeData.put("COINBASE/LTC/EUR", getRealTimeBTCData("LTC", "EUR", "COINBASE PRO"));
		exchangeData.put("COINBASE/LTC/GBP", getRealTimeBTCData("LTC", "GBP", "COINBASE PRO"));

		exchangeData.put("COINBASE/ETH/USD", getRealTimeBTCData("ETH", "USD", "COINBASE PRO"));
		exchangeData.put("COINBASE/ETH/EUR", getRealTimeBTCData("ETH", "EUR", "COINBASE PRO"));
		exchangeData.put("COINBASE/ETH/GBP", getRealTimeBTCData("ETH", "GBP", "COINBASE PRO"));

		return exchangeData;
	}

	// This is deprecated because it will replaced with another method or rewrite of
	// this method in an comming update.
//	@Deprecated
	public RealTimeBTCData getRealTimeBTCData(String cypto, String currency, String exchange) {
		List<ExchangeDataRecieved> currentDB = bitcoinPriceData.findAll().stream().collect(Collectors.toList());
		if (cypto != null)
			currentDB = currentDB.stream().filter(i -> i.getCypto().equalsIgnoreCase(cypto))
					.collect(Collectors.toList());

		if (currency != null)
			currentDB = currentDB.stream()
					.filter(i -> i.getCurrency().equalsIgnoreCase(currency))
					.collect(Collectors.toList());

		if (exchange != null && !exchange.equalsIgnoreCase("all"))
			currentDB = currentDB.stream()
					.filter(i -> i.getExchange().equalsIgnoreCase(exchange.toUpperCase()))
					.collect(Collectors.toList());

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
		// drops the db
		dropData();
	}

	// drop table

}
