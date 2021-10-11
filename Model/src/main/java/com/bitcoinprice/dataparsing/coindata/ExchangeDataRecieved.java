package com.bitcoinprice.dataparsing.coindata;

public class ExchangeDataRecieved {
	
	public String exchange;
	public String symbol;
	public String tranactionId;
	public String price;
	public String size;
	public String side;
	public String currency;
	public String cypto;
	
	public ExchangeDataRecieved() {
		super();
	}

	public ExchangeDataRecieved(String exchange, String symbol, String tranactionId, String price, String size,
			String side, String currency, String cypto) {
		super();
		this.exchange = exchange;
		this.symbol = symbol;
		this.tranactionId = tranactionId;
		this.price = price;
		this.size = size;
		this.side = side;
		this.currency = currency;
		this.cypto = cypto;
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
		return price;
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
		
}
