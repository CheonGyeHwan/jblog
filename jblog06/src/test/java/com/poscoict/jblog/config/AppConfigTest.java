package com.poscoict.jblog.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes=AppConfig.class)
public class AppConfigTest {
	@Autowired
	private AppConfig appConfig;
	
	@Test
	public void encryptTest() {
		String username = "jblog";
		String password = "jblog";
		
		String encryptUsername = appConfig.stringEncryptor().encrypt(username);
		String encryptPassword = appConfig.stringEncryptor().encrypt(password);
		
		System.out.println(encryptUsername);
		System.out.println(encryptPassword);
	}
	
	@Test
	public void decryptTest() {
		String encryptUsername = "FPcO8TsYyv6cO11f5oACZg==";
		String encryptPassword = "MUKyuRSWOMXIOeF2a+AN8w==";
		
		String decryptUsername = appConfig.stringEncryptor().decrypt(encryptUsername);
		String decryptPassword = appConfig.stringEncryptor().decrypt(encryptPassword);
		
		System.out.println(decryptUsername);
		System.out.println(decryptPassword);
	}
	
}
