package com.ubs.frds.model;

import java.math.BigDecimal;

import com.ubs.frds.javatest.MainProgram;

public class CompanyInfo {

	private String compCode;
	private String account;
	private String city;
	private String country;
	private String creditRating;
	private String currency;
	private BigDecimal amount;
	
	// Inner class to keep count
	public static class AvgAmount {
		private BigDecimal avgAmount;
		private int count;
		
		public BigDecimal getAvgAmount() {
			return avgAmount;
		}
		public void setAvgAmount(BigDecimal avgAmount) {
			this.avgAmount = avgAmount;
		}
		public int getCount() {
			return count;
		}
		public void setCount(int count) {
			this.count = count;
		}
	}
	
	public String getCompCode() {
		return compCode;
	}
	public void setCompCode(String compCode) {
		this.compCode = compCode;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		if(this.country == null || this.country.equals(MainProgram.nullChar)){
			return city;
		}else {
			return country;
		}
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCreditRating() {
		return creditRating;
	}
	public void setCreditRating(String creditRating) {
		this.creditRating = creditRating;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
}
