package com.ubs.frds.service;

import java.util.List;
import java.util.Map;

import com.ubs.frds.model.CompanyInfo;
import com.ubs.frds.model.CountryAndCreditRating;

public interface CompanyAccountManager {

	public void convertAmountToUsd(List<CompanyInfo> compInfoList);
	public Map<CountryAndCreditRating , CompanyInfo.AvgAmount> calculateDuplicateElements(List<CompanyInfo> compInfoList);
	Map<CountryAndCreditRating , CompanyInfo.AvgAmount> calculateAvgAmount(Map<CountryAndCreditRating , CompanyInfo.AvgAmount> amountMap );

}
