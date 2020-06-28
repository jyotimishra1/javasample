package com.ubs.frds.util;

import java.math.BigDecimal;
import java.util.List;
import com.ubs.frds.model.CompanyInfo;

public class ObjectUtil {

	public static void createCompaniesInfo(String fileContent, List<CompanyInfo> compInfoList) {
		String[] splitStr = fileContent.split("\\s+");
		if (splitStr != null && splitStr.length > 0) {
			CompanyInfo compInfo = new CompanyInfo();
			compInfo.setCompCode(splitStr[0]);
			compInfo.setAccount(splitStr[1]);
			compInfo.setCity(splitStr[2]);
			compInfo.setCountry(splitStr[3]);
			compInfo.setCreditRating(splitStr[4]);
			compInfo.setCurrency(splitStr[5]);
			compInfo.setAmount(new BigDecimal(splitStr[6]));
			compInfoList.add(compInfo);
		}
	}
}
