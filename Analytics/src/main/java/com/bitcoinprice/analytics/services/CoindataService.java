package com.bitcoinprice.analytics.services;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.bitcoinprice.analytics.repository.BardataRepository;
import com.bitcoinprice.analytics.repository.BitcoinPriceData;
import com.bitcoinprice.dataparsing.analytic.Bardata;
import com.bitcoinprice.dataparsing.coindata.ExchangeDataRecieved;
import com.bitcoinprice.dataparsing.coindata.RealTimeBTCData;

@Service
@ComponentScan({ "com.bitcoinprice", "com.bitcoinprice.repository" })
public class CoindataService {

	@Autowired
	private BitcoinPriceData bitcoinPriceData;

	@Autowired
	private BardataRepository bardataRepository;

	// Stores data about the bar in database.
	public void buySellBar(String exchange, String currency, String cointype) throws Exception {
		// could just return here, but rather have the error message.
		if (currency == null || cointype == null || exchange == null)
			throw new Exception("needs all 3 exchanges to not be null to work");
		// filters the data so we only get the content we need.
		List<ExchangeDataRecieved> previousMinList = bitcoinPriceData.findAll().stream()
				.filter(i -> i.getCurrency().equalsIgnoreCase(currency))
				.filter(i -> i.getCypto().equalsIgnoreCase(cointype))
				.filter(i -> i.getExchange().equalsIgnoreCase(exchange))
				.filter(i -> Duration.between(LocalDateTime.ofInstant(i.getTimestamp1(), ZoneOffset.UTC),
						LocalDateTime.ofInstant(Instant.now(), ZoneOffset.UTC)).toSeconds() <= 60)
				.collect(Collectors.toList());
		// Just to keep easier to read.
		long buyCount = previousMinList.stream().filter(i -> i.getSide().equalsIgnoreCase("Buy")).count();
		// Builder, just neater then 100 setters.
		Bardata barData = new Bardata.Builder().exchange(exchange).currency(currency).cypto(cointype)
				.buyPercentage((buyCount * 100.0f) / previousMinList.size())
				.sellPercentage(100 - ((buyCount * 100.0f) / previousMinList.size()))
				.realTimeBTCData(averagePrice(exchange, currency, cointype)).dataSize(previousMinList.size()).build();

//		this.totalBuys = totalBuys;
//		this.totalSells = totalSells;
		barData.addExchangeDataRecieved(new ArrayList<ExchangeDataRecieved>(previousMinList));

		// doesn't save if we have a count of 0, this is because we sometimes have times
		// were low cap coins have no good data. example ltc
		if (barData.getDataSize() > 0)
			bardataRepository.save(barData);
	}

	public RealTimeBTCData averagePrice(String exchange, String currency, String cointype) throws Exception {
		if (currency == null || cointype == null || exchange == null)
			throw new Exception("needs all 3 exchanges to not be null to work");

		List<ExchangeDataRecieved> previousMinList = bitcoinPriceData.findAll().stream()
				.filter(i -> i.getCurrency().equalsIgnoreCase(currency))
				.filter(i -> i.getCypto().equalsIgnoreCase(cointype))
				.filter(i -> i.getExchange().equalsIgnoreCase(exchange))
				.filter(i -> Duration.between(LocalDateTime.ofInstant(i.getTimestamp1(), ZoneOffset.UTC),
						LocalDateTime.ofInstant(Instant.now(), ZoneOffset.UTC)).toSeconds() <= 60)
				.collect(Collectors.toList());

		if (previousMinList.size() == 0)
			return null;

		List<ExchangeDataRecieved> afterMinList = bitcoinPriceData.findAll().stream()
				.filter(i -> i.getCurrency().equalsIgnoreCase(currency))
				.filter(i -> i.getCypto().equalsIgnoreCase(cointype))
				.filter(i -> i.getExchange().equalsIgnoreCase(exchange))
				.filter(i -> Duration
						.between(LocalDateTime.ofInstant(i.getTimestamp1(), ZoneOffset.UTC),
								LocalDateTime.ofInstant(Instant.now(), ZoneOffset.UTC))
						.toSeconds() >= 60
						&& Duration.between(LocalDateTime.ofInstant(i.getTimestamp1(), ZoneOffset.UTC),
								LocalDateTime.ofInstant(Instant.now(), ZoneOffset.UTC)).toSeconds() <= 120)
				.collect(Collectors.toList());

		if (afterMinList.size() == 0)
			return null;

		double cuurentMinPrice = previousMinList.stream().mapToDouble(i -> Double.parseDouble(i.getPrice())).sum()
				/ previousMinList.size();
		double twoMinAgoPrice = afterMinList.stream().mapToDouble(i -> Double.parseDouble(i.getPrice())).sum()
				/ afterMinList.size();

		RealTimeBTCData realTimeBTCData = new RealTimeBTCData(cuurentMinPrice, twoMinAgoPrice,
				previousMinList.stream().filter(i -> i.getSide().equalsIgnoreCase("Buy")).count(),
				previousMinList.stream().filter(i -> i.getSide().equalsIgnoreCase("sell")).count());

		return realTimeBTCData;
	}
	
	@Async
//	@Scheduled(fixedRate = 1000 * 15, initialDelay = 1000 * 60)
	public void scheduledUpdate() throws Exception {
		// This data is used for analytics part of project it's more like script then
		// anything else.
		// Updates the currency conversation holder.
		buySellBar("coinbase pro", "usd", "btc");
		buySellBar("coinbase pro", "usd", "ltc");
		buySellBar("coinbase pro", "usd", "eth");
		buySellBar("coinbase pro", "eur", "btc");
		buySellBar("coinbase pro", "eur", "ltc");
		buySellBar("coinbase pro", "eur", "eth");
		buySellBar("coinbase pro", "gbp", "btc");
		buySellBar("coinbase pro", "gbp", "ltc");
		buySellBar("coinbase pro", "gbp", "eth");
		buySellBar("bitmex", "usd", "btc");
		buySellBar("bitmex", "eur", "btc");
		buySellBar("bitmex", "usd", "eth");
		buySellBar("bitmex", "usd", "ltc");
		buySellBar("binance", "usd", "btc");
		buySellBar("binance", "usd", "eth");
		buySellBar("binance", "usd", "ltc");
		buySellBar("binance", "EUR", "btc");
		buySellBar("binance", "EUR", "eth");
		buySellBar("binance", "EUR", "ltc");
		buySellBar("binance", "gbp", "btc");
		buySellBar("binance", "gbp", "eth");
		buySellBar("binance", "gbp", "ltc");
	}
}
