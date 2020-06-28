package com.ubs.frds.service.test;

import static org.junit.Assert.assertEquals;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.ubs.frds.javatest.SpringConfig;
import com.ubs.frds.model.CompanyInfo;
import com.ubs.frds.model.CountryAndCreditRating;
import com.ubs.frds.service.CompanyAccountManager;
import com.ubs.frds.util.Currency;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { SpringConfig.class })
public class AccountManagerTest {

	@Autowired
	CompanyAccountManager accountManager;

	@Test
	public void testConvertAmountToUsd() {
		List<CompanyInfo> compInfoList = new ArrayList<>();
		CompanyInfo companyInfo = new CompanyInfo();
		companyInfo.setAmount(new BigDecimal(123));
		companyInfo.setCurrency("GBP");
		compInfoList.add(companyInfo);
		accountManager.convertAmountToUsd(compInfoList);
		assertEquals(new BigDecimal(123).multiply(new BigDecimal(1.654)), compInfoList.get(0).getAmount());
	}

	@Test
	public void testCalculateDuplicateElements() {
		List<CompanyInfo> compInfoList = new ArrayList<>();
		compInfoList.add(getCompanyRecord("India", "A", null, 100l));
		compInfoList.add(getCompanyRecord("Australia", "B", null, 200l));
		compInfoList.add(getCompanyRecord(null, "A", "India", 400l));
		compInfoList.add(getCompanyRecord(null, "B", "Australia", 200l));
		Map<CountryAndCreditRating, CompanyInfo.AvgAmount> amountMap = accountManager
				.calculateDuplicateElements(compInfoList);
		// Check if they have been grouped properly
		assertEquals(2, amountMap.size());
	}

	@Test
	public void testCalculateAvgAmountInEURO() {
		List<CompanyInfo> compInfoList = new ArrayList<>();
		compInfoList.add(getCompanyRecord("India", "A", null, 100l));
		compInfoList.add(getCompanyRecord("Australia", "B", null, 200l));
		compInfoList.add(getCompanyRecord(null, "A", "India", 400l));
		compInfoList.add(getCompanyRecord(null, "B", "Australia", 200l));
		Map<CountryAndCreditRating, CompanyInfo.AvgAmount> amountMap = accountManager
				.calculateDuplicateElements(compInfoList);
		accountManager.calculateAvgAmount(amountMap);
		BigDecimal avgAmountForAus = new BigDecimal(200l).add(new BigDecimal(200l)).divide(new BigDecimal(2));
		avgAmountForAus = avgAmountForAus.divide(Currency.EUR.getConversionRateToUsd(), RoundingMode.HALF_UP);

		BigDecimal avgAmountForInd = new BigDecimal(100l).add(new BigDecimal(400l)).divide(new BigDecimal(2));
		avgAmountForInd = avgAmountForInd.divide(Currency.EUR.getConversionRateToUsd(), RoundingMode.HALF_UP);

		assertEquals(avgAmountForAus, (amountMap.get(new CountryAndCreditRating("Australia", "B"))).getAvgAmount());
		assertEquals(avgAmountForInd, (amountMap.get(new CountryAndCreditRating("India", "A"))).getAvgAmount());
	}

	private CompanyInfo getCompanyRecord(String country, String creditRating, String city, Long amount) {
		CompanyInfo companyInfo = new CompanyInfo();
		companyInfo.setCountry(country);
		companyInfo.setCreditRating(creditRating);
		companyInfo.setCity(city);
		companyInfo.setAmount(new BigDecimal(amount));
		return companyInfo;
	}

}
