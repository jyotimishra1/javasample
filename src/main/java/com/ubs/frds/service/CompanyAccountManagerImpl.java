package com.ubs.frds.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ubs.frds.model.CompanyInfo;
import com.ubs.frds.model.CountryAndCreditRating;
import com.ubs.frds.util.Currency;

@Service
public class CompanyAccountManagerImpl implements CompanyAccountManager {

	@Override
	public void convertAmountToUsd(List<CompanyInfo> compInfoList) {
		compInfoList.forEach(compInfo -> {
			compInfo.setAmount(compInfo.getAmount().multiply(Currency.getCurrencyFromCurrencyName(
															compInfo.getCurrency()).getConversionRateToUsd()));
		});
	}

	@Override
	public Map<CountryAndCreditRating , CompanyInfo.AvgAmount> calculateDuplicateElements(List<CompanyInfo> compInfoList) {
		Map<CountryAndCreditRating , CompanyInfo.AvgAmount> amountMap = new HashMap<>();
		compInfoList.forEach(compInfo -> {
			CountryAndCreditRating key = new CountryAndCreditRating(compInfo.getCountry() , compInfo.getCreditRating());
			if(amountMap.containsKey(key)){
				CompanyInfo.AvgAmount avgAmount = amountMap.get(key);
				BigDecimal amount = avgAmount.getAvgAmount().add(compInfo.getAmount());
				avgAmount.setAvgAmount(amount); // Add the amount
				avgAmount.setCount(avgAmount.getCount()+1); // Increment the count
				amountMap.put(key, avgAmount); // Override the current value
			}
			else {
				CompanyInfo.AvgAmount avgAmount = new CompanyInfo.AvgAmount();
				avgAmount.setAvgAmount(compInfo.getAmount());
				avgAmount.setCount(1);
				amountMap.put(key, avgAmount);
			}
		});

		return amountMap;
	}

	@Override
	public Map<CountryAndCreditRating , CompanyInfo.AvgAmount> calculateAvgAmount(
			                     Map<CountryAndCreditRating , CompanyInfo.AvgAmount> amountMap ){
		Map<CountryAndCreditRating , CompanyInfo.AvgAmount> finalAmountMap = new HashMap<>();
		Iterator<CountryAndCreditRating> amountIter = amountMap.keySet().iterator();
		while(amountIter.hasNext()){
			CountryAndCreditRating key = amountIter.next();
			CompanyInfo.AvgAmount avgAmountWithCount = amountMap.get(key);
			// totalAmount/numberOfDuplicateElements
			BigDecimal avgAmount = avgAmountWithCount.getAvgAmount().divide(new BigDecimal(avgAmountWithCount.getCount()));
			// Convert amount to EURO 
			avgAmount = avgAmount.divide(Currency.EUR.getConversionRateToUsd() , RoundingMode.HALF_UP);
			avgAmountWithCount.setAvgAmount(avgAmount);
			finalAmountMap.put(key, avgAmountWithCount);
		}
		return finalAmountMap;
	}

}
