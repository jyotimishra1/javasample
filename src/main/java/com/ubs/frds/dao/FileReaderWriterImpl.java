package com.ubs.frds.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ubs.frds.model.CompanyInfo;
import com.ubs.frds.model.CountryAndCreditRating;
import com.ubs.frds.util.ObjectUtil;

/**
 * Here data is contained in files. This class is used to read/write data from/to file. 
 */
@Repository
public class FileReaderWriterImpl implements FileReaderWriter {

	public List<CompanyInfo> readFile(File file) throws IOException {
		List<CompanyInfo> compInfoList = new ArrayList<>();
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		try {
			fileReader = new FileReader(file);
			bufferedReader = new BufferedReader(fileReader);
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				// check to skip first row
				if(!line.startsWith("Company")){
					ObjectUtil.createCompaniesInfo(line , compInfoList);
				}
			}
		} finally {
			fileReader.close();
			bufferedReader.close();
		}
		return compInfoList;
	}
	
	public void writeResultToFile(Map<CountryAndCreditRating , CompanyInfo.AvgAmount> amountMap) throws IOException {
		File dir = new File("F:\\testCode\\");
		dir.mkdirs();
		File file = new File(dir, "OUPUT.DAT");
		try (Writer writer = Files.newBufferedWriter(file.toPath())) {
			  writer.write(String.format("%-20s %-20s %-20s%n", "Country", "Credit-Rating" , "Avg Amount in EURO"));
			  Iterator<CountryAndCreditRating> amountIter = amountMap.keySet().iterator();
			  while(amountIter.hasNext()) {
			  CountryAndCreditRating key = amountIter.next();
			  writer.write(String.format("%-20s %-20s %-20s%n", key.getCountry() , key.getCreditRating() , 
					                                   amountMap.get(key).getAvgAmount())); 
			  }
			} 
	}
}
