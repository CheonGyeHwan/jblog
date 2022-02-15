package com.poscoict.jblog.config;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootConfiguration
public class AppConfig {
	
	@Bean(name="encryptor")
	public StringEncryptor stringEncryptor() {
		String key = "DBAccountEncryptKey";
		PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
		SimpleStringPBEConfig config = new SimpleStringPBEConfig();
		config.setPassword(key);
		config.setAlgorithm("PBEWithMD5AndDES");
		config.setKeyObtentionIterations("500");
		config.setPoolSize("1");
		config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
		config.setStringOutputType("base64");
		encryptor.setConfig(config);
		
		return encryptor;
	}
	
}
