package com.bitcoinprice.dataparsing.coindata;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class RealTimeBTCData {
	
	public double currentPrice;
	public double previousPrice;
	public double priceChange;
	
	
	public RealTimeBTCData() {
		super();
	}
	
	public RealTimeBTCData(double currentPrice, double previousPrice) {
		super();
		this.currentPrice = new BigDecimal(currentPrice).setScale(2, RoundingMode.HALF_EVEN).doubleValue();
		this.previousPrice = new BigDecimal(previousPrice).setScale(2, RoundingMode.HALF_EVEN).doubleValue();
	}
	
	public double getCurrentPrice() {
		return currentPrice;
	}
	public void setCurrentPrice(double currentPrice) {
		this.currentPrice = currentPrice;
	}
	public double getPreviousPrice() {
		return previousPrice;
	}
	public void setPreviousPrice(double previousPrice) {
		this.previousPrice = previousPrice;
	}
	public double getPriceChange() {
//		return 1;
		return new BigDecimal((currentPrice * 100 / previousPrice) - 100).setScale(4, RoundingMode.HALF_EVEN).doubleValue();
	}
}
