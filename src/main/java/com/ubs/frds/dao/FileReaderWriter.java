package com.ubs.frds.dao;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import com.ubs.frds.model.CompanyInfo;
import com.ubs.frds.model.CountryAndCreditRating;

public interface FileReaderWriter {

	public List<CompanyInfo> readFile(File file) throws IOException;
	public void writeResultToFile(Map<CountryAndCreditRating , CompanyInfo.AvgAmount> amountMap) throws IOException;
}
