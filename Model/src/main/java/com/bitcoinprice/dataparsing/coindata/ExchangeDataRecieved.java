package com.bitcoinprice.dataparsing.coindata;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.time.LocalDateTime;

public class ExchangeDataRecieved {

	
	public String apiLink = "";
	public String exchange = "";
	public String symbol = "";
	public String tranactionId = "";
	public String price = "";
	public String size = "";
	public String side = "";
	public String currency = "";
	public String cypto = "";
//	public LocalDateTime timestamp;
	public String timestamp = "";

	public ExchangeDataRecieved() {
		super();
	}

	public String getApiLink() {
		return apiLink;
	}

	public void setApiLink(String apiLink) {
		this.apiLink = apiLink;
	}

	public String getExchange() {
		return exchange;
	}

	public void setExchange(String exchange) {
		this.exchange = exchange;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getTranactionId() {
		return tranactionId;
	}

	public void setTranactionId(String tranactionId) {
		this.tranactionId = tranactionId;
	}

	public String getPrice() {
		return String.valueOf(new BigDecimal(price).setScale(2, RoundingMode.HALF_EVEN).doubleValue());
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getSide() {
		return side;
	}

	public void setSide(String side) {
		this.side = side;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getCypto() {
		return cypto;
	}

	public void setCypto(String cypto) {
		this.cypto = cypto;
	}

//	public LocalDateTime getTimestamp() {
//		return timestamp;
//	}
//
//	public void setTimestamp(LocalDateTime timestamp) {
//		this.timestamp = timestamp;
//	}

	public Instant getTimestamp1() {
		return Instant.parse(timestamp);
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String toString() {
		return exchange + " " + symbol + " " + tranactionId + " " + price + " " + size + " " + side + " " + currency
				+ " " + cypto + " " + timestamp.toString();
	}

}
