package com.ubs.frds.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import com.ubs.frds.model.CompanyInfo;
import com.ubs.frds.model.CountryAndCreditRating;

public interface FileManager {

	public List<CompanyInfo> readFile(String fileName) throws IOException;
	public void writeResultToFile(Map<CountryAndCreditRating , CompanyInfo.AvgAmount> amountMap) throws IOException;
}
