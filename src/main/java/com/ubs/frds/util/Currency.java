package com.ubs.frds.util;

import java.math.BigDecimal;
import java.util.HashMap;

public enum Currency {
	
	GBP("GBP" , new BigDecimal(1.654)),
	CHF("CHF" , new BigDecimal(1.10)),
	EUR("EUR" , new BigDecimal(1.35)),
	NOT_DEFINED("NOT_DEFINED" , new BigDecimal(1)); // if invalid currency is passed
	
	public String getCurrency() {
		return currency;
	}
	
	public BigDecimal getConversionRateToUsd() {
		return conversionRateToUsd;
	}

	private static HashMap<String , Currency> currencyMap;
	
	// Load time initialization because of seed data
	static {
		currencyMap =  new HashMap<>();
		for(Currency currency : Currency.values()){
			currencyMap.put(currency.getCurrency(), currency);
		}
	}
	
	private String currency;
	private BigDecimal conversionRateToUsd;
	
	private Currency(String currency, BigDecimal conversionRateToUsd) {
		this.currency = currency;
		this.conversionRateToUsd = conversionRateToUsd;
	}
	
	public static Currency getCurrencyFromCurrencyName(String currencyStr) {
		Currency currency = currencyMap.get(currencyStr);
		return currency == null ? Currency.NOT_DEFINED : currency;
	}
}
