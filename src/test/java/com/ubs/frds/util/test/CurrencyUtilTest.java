package com.ubs.frds.util.test;

import com.ubs.frds.util.Currency;

import junit.framework.TestCase;

public class CurrencyUtilTest extends TestCase {

	public void testCurrencyConversionRate() {
		assertEquals( 1.0 , Currency.NOT_DEFINED.getConversionRateToUsd().doubleValue() );
		assertEquals( 1.654 , Currency.GBP.getConversionRateToUsd().doubleValue() );
		assertEquals( 1.10 , Currency.CHF.getConversionRateToUsd().doubleValue() );
		assertEquals( 1.35 , Currency.EUR.getConversionRateToUsd().doubleValue() );
	}

	public void testGetCurrencyName() {
		assertEquals( "GBP", Currency.GBP.getCurrency() );
		assertEquals( "CHF", Currency.CHF.getCurrency() );
		assertEquals( "EUR", Currency.EUR.getCurrency() );
		assertEquals( "NOT_DEFINED", Currency.NOT_DEFINED.getCurrency() );
	}

	public void testGetCurrencyFromCurrencyName() throws Exception {
		assertEquals( Currency.GBP, Currency.getCurrencyFromCurrencyName( "GBP" ) );
		assertEquals( Currency.CHF, Currency.getCurrencyFromCurrencyName( "CHF" ) );
		assertEquals( Currency.EUR, Currency.getCurrencyFromCurrencyName( "EUR" ) );
		assertEquals( Currency.NOT_DEFINED, Currency.getCurrencyFromCurrencyName( "NOT_DEFINED" ) );
	}

}
