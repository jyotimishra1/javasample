package com.ubs.frds.javatest;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import com.ubs.frds.model.CompanyInfo;
import com.ubs.frds.model.CountryAndCreditRating;
import com.ubs.frds.service.CompanyAccountManager;
import com.ubs.frds.service.FileManager;

/**
 * Entry point for the program.
 *
 */
public class MainProgram {

	public static final String nullChar = "-";

	public static void main(String[] args) throws IOException {
		// Resource must be closed thus "try with resources"
		try (AbstractApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class)) {
			CompanyAccountManager accountManager = (CompanyAccountManager) context.getBean("accountManager");
			FileManager fileManager = (FileManager) context.getBean("fileManager");
			// Read the file and create objects
			List<CompanyInfo> compInfoList = fileManager.readFile("FILE.DAT");
			// Convert the amounts to USD
			accountManager.convertAmountToUsd(compInfoList);
			// Calculate duplicate elements
			Map<CountryAndCreditRating, CompanyInfo.AvgAmount> amountMap = accountManager
					.calculateDuplicateElements(compInfoList);
			// Calculate avg amount
			amountMap = accountManager.calculateAvgAmount(amountMap);
			// Display average amount
			fileManager.writeResultToFile(amountMap);
		}

	}
}
