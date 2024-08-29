package com.mini.banking.demo.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = { "com.mini.banking.demo.core", "com.mini.banking.demo.service" })
public class MiniBankingApplication {

	public static void main(String[] args) {
		SpringApplication.run(MiniBankingApplication.class, args);
	}

}
