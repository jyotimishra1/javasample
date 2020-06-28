package com.ubs.frds.service.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import java.io.IOException;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.ubs.frds.javatest.SpringConfig;
import com.ubs.frds.model.CompanyInfo;
import com.ubs.frds.service.FileManager;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { SpringConfig.class })
public class FileManagerTest {

	@Autowired
	FileManager fileManager;
	
	@Test
	public void testReadFileWithActualContent() throws IOException{
		List<CompanyInfo> comapnyList  = fileManager.readFile("FILEActual.DAT");
		assertEquals(19 , comapnyList.size());
		CompanyInfo companyInfo = comapnyList.get(0);
		assertNotEquals("Country" , companyInfo.getCountry());
		assertEquals("2300" , companyInfo.getCompCode());
		assertEquals("9917319" , companyInfo.getAccount());
		assertEquals("USA" , companyInfo.getCountry());
		assertEquals("AAA+" , companyInfo.getCreditRating());
	}
	
	@Test
	public void testReadFileWithSingleRecord() throws IOException{
		List<CompanyInfo> comapnyList  = fileManager.readFile("FILEWithSingleRecord.DAT");
		assertEquals(1 , comapnyList.size());
	}
	
	@Test
	public void testReadFileWithNoRecord() throws IOException {
		List<CompanyInfo> comapnyList  = fileManager.readFile("FILEWithNoRecord.DAT");
		assertEquals(0 , comapnyList.size());
	}
	
	@Test
	public void testReadFileWithInvalidFile() throws IOException {
		List<CompanyInfo> comapnyList  = fileManager.readFile(null);
		assertEquals( null , comapnyList);
	}
}
