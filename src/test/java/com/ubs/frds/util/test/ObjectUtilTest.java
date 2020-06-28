package com.ubs.frds.util.test;

import java.util.ArrayList;
import java.util.List;
import com.ubs.frds.model.CompanyInfo;
import com.ubs.frds.util.ObjectUtil;
import junit.framework.TestCase;

public class ObjectUtilTest extends TestCase {

	public void testCreateCompaniesInfo() {
		String fileStr = "2303" + "	2954026" + " Atlanta " + " USA	" + "	AAA+ " + " CHF " + " 65484231.44 ";
		List<CompanyInfo> compInfoList = new ArrayList<>();
		ObjectUtil.createCompaniesInfo(fileStr, compInfoList);
		assertEquals(1, compInfoList.size());
		assertEquals("2303", compInfoList.get(0).getCompCode());
		assertEquals("2954026", compInfoList.get(0).getAccount());
		assertEquals("Atlanta", compInfoList.get(0).getCity());
	}

}