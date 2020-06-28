package com.ubs.frds.model;

public class CountryAndCreditRating {

	private String country;
	private String creditRating;
	
	public CountryAndCreditRating(String country, String creditRating) {
		super();
		this.country = country;
		this.creditRating = creditRating;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((creditRating == null) ? 0 : creditRating.hashCode());
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CountryAndCreditRating other = (CountryAndCreditRating) obj;
		if (creditRating == null) {
			if (other.creditRating != null)
				return false;
		} else if (!creditRating.equals(other.creditRating))
			return false;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		return true;
	}

	public String getCountry() {
		return country;
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

}
