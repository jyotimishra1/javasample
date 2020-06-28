package com.ubs.frds.javatest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import com.ubs.frds.service.CompanyAccountManager;
import com.ubs.frds.service.CompanyAccountManagerImpl;
import com.ubs.frds.service.FileManager;
import com.ubs.frds.service.FileManagerImpl;

@Configuration
@ComponentScan(basePackages={"com.ubs.frds"})
public class SpringConfig {
	
	@Bean("accountManager")
	public CompanyAccountManager companyAccountManager(){
		return new CompanyAccountManagerImpl();
	}

	@Bean("fileManager")
	public FileManager fileManager(){
		return new FileManagerImpl();
	}
}
