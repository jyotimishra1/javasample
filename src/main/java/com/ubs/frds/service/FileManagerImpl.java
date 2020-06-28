package com.ubs.frds.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ubs.frds.dao.FileReaderWriter;
import com.ubs.frds.model.CompanyInfo;
import com.ubs.frds.model.CountryAndCreditRating;

@Service
public class FileManagerImpl implements FileManager {

	@Autowired
	FileReaderWriter fileReaderWrite;
	
	@Override
	public List<CompanyInfo> readFile(String fileName) throws IOException {
		List<CompanyInfo> companyList = null;
		if(fileName != null){
			ClassLoader classLoader = getClass().getClassLoader();
			File file = new File(classLoader.getResource(fileName).getFile());
			companyList = fileReaderWrite.readFile(file);
		}
		return companyList;
	}

	@Override
	public void writeResultToFile(Map<CountryAndCreditRating, CompanyInfo.AvgAmount> amountMap) throws IOException {
		fileReaderWrite.writeResultToFile(amountMap);
	}

	
}
