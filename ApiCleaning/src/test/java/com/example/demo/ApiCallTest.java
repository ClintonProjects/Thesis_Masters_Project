package com.example.demo;

import java.io.IOException;

import org.junit.Test;
import static org.junit.Assert.*;

import com.bitcoinprice.dataparsing.apicall.ApiCall;

class apiCallTest {

	public void apiTestMethod(String site, String conversation, String link)
			throws UnsupportedOperationException, IOException {
		System.out.println("Api Code test [" + site + "] " + conversation);
		String TestResult = new ApiCall().getRequest(link);
		assertTrue(TestResult != null && !TestResult.isEmpty());

	}

	@Test
	public void ApiTest() throws IOException {
		apiTestMethod("Google.com", "API TEST NO CONVERSATION", "https://www.google.com/");
	}

	@Test
	public void ApiTestBitmexBTCUSD() throws IOException {
		apiTestMethod("bitmex.com", "USD/BTC",
				"https://www.bitmex.com/api/v1/trade?count=200&reverse=true&symbol=XBTUSD");
	}

	@Test
	public void ApiTestBitmexETHUSD() throws IOException {
		apiTestMethod("bitmex.com", "USD/ETH",
				"https://www.bitmex.com/api/v1/trade?count=200&reverse=true&symbol=ETHUSD");
	}

	@Test
	public void ApiTestBitmexBTCEUR() throws IOException {
		apiTestMethod("bitmex.com", "USD/BTC",
				"https://www.bitmex.com/api/v1/trade?count=200&reverse=true&symbol=XBTEUR");
	}

	@Test
	public void ApiTestOkexBTCUSDT() throws IOException {
		apiTestMethod("okex.com", "USDT/BTC",
				"https://www.okex.com/priapi/v5/market/trades?t=1633514252717&instId=BTC-USDT");
	}

	@Test
	public void ApiTestOkexETHUSDT() throws IOException {
		apiTestMethod("okex.com", "USDT/ETH",
				"https://www.okex.com/priapi/v5/market/trades?t=1633514252717&instId=ETH-USDT");
	}

	@Test
	public void kraken() throws IOException {
		// karken api:https://docs.kraken.com/rest/#operation/getServerTime
	}

	@Test
	public void ApiTestHuobiBTCUSDT() throws IOException {
		//// https://www.huobi.com/support/en-us/detail/900001603466
	}

	@Test
	public void FTX() {
		// https://ftx.com/api/markets/BTC-PERP/trades?limit=100
	}

}